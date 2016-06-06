package handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import struct.*;

/**
 * 
 * @author root
 * The main class for executing the compilation and the update process. It follows the logic :
 * 1- Read the parameters of the program.
*                2- Generate the lattice using coron program. Coron uses the algorithm dtouch and the method snow for lattice computation.
*                3- The compiler read the rules from ().RCF) file it will store them in a Map structure. The read process is implemented in RuleHandler.java
*                4- Extract the relevant concepts from the lattice. The process of extraction is implemented in TrieHandler.java
*                5- Generate the pipeline using the chosen concepts. The function is implemented in EntryHandler.java
*                6-  [*] If the first argument is "-compile". The compiler will build the Trie using CreateTrie() Methods. And it will serialize the object Trie in order to                                     use for update. The serialized object is stored under. /tmp/ 
*                    [*] If the first argument is "-update". The compiler will first deserialize the original trie. After that It will call the update function                                     UpdateTrie() implemented in Trie.java class.
 */


public class Compiler{
    public static void main(String[] args) throws Exception  {
    	
    	  new Thread() {
              public void run(){
                  OpenFlow_Handler openFlow_handler = new OpenFlow_Handler();
              }
           }.start();
         Thread.sleep(40000); 
    	
        //Input Params for the program
        String [] s = "-compile RuleFile.rcf input/test_up.rcf 3 serial".split(" ");
        args = s;
        String cmdline = args[0];
        String inputFilenameRCF = args[1];
        String inputFilenameRCF_up = args[2];
        int threshold = Integer.parseInt(args[3]);
        int objIdOff = 0;
        String ofcompilerpath = System.getProperty("user.dir")+"/";
        String tmpTrie = ofcompilerpath + "tmp/" + args[4];
        String adressedufichier = ofcompilerpath + "output/"+ args[4] +".json";
    
        ConstraintHandler constraint_handler = new ConstraintHandler("input/constraint.xml");
        
        constraint_handler.parse_ConstraintXML();
        //system.out.println("#################MAP CONSTRAINT########################");
        //system.out.println(constraint_handler.MapConstraint);
       
        

        /********* Temporary files**************/
        int LatticeId = 0;
        String latticeName = "Lattice";
        String LatticeXMLFileName = "lattice/latticeT.xml"; 
        
        /****We start by generating the Lattice*********/

        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        
        String coron = " -names -order -alg:dtouch -method:snow -ext -xml -of:";// /coron-0.8/Myexample/tmp0.xml
        String generatexml = new StringBuilder().append(ofcompilerpath).append("coron-0.8/core03_leco.sh ").append(ofcompilerpath).append(inputFilenameRCF+" ").append        (threshold).append(coron).append(ofcompilerpath).append(LatticeXMLFileName).toString();
        
        try {        
            Process p = rt.exec(generatexml);
            p.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        Lattice lattice = new Lattice(LatticeId, latticeName, threshold);
        LatticeHandler latticeHandler = new LatticeHandler();
        lattice = latticeHandler.ReadLatticeXML(LatticeXMLFileName, LatticeId, latticeName, threshold, objIdOff);
        ////system.out.println(lattice);
        /********Get Rules from Lattice***********/ 
        RuleHandler rulehander = new RuleHandler();
        Map<Integer, Rule> ruleList = new HashMap<Integer, Rule>();
        ruleList = rulehander.GetRulesFromRCF(inputFilenameRCF);
        
        

        Switch S = new Switch();
        ConstraintHandler C = new ConstraintHandler("input/constraint.xml");
        C.parse_ConstraintXML();
        S.getConstraint(C.MapConstraint);
        
        /** Extract significant concept from the set of all concepts **/
        ArrayList<Lat_Concept> favoriteConceptList = latticeHandler.getSignificantConcept(lattice);
        
       
        /** Generate Indexes **/
        TrieHandler trieHandler = new TrieHandler();
        ArrayList<Index> indexList = trieHandler.generateIndex(favoriteConceptList);
        //system.out.println("###############################IndexList#####################################");
        for(int i=0;i<indexList.size();i++){
        //system.out.println(indexList.get(i));
        }
        /**************** Generate Pipelines **************/
        EntryHandler entryHandler = new EntryHandler();
        ArrayList<Pipeline> pipelines= entryHandler.generateIndexPipeline(indexList, S); 
        //ArrayList<Pipeline> p = entryHandler.SortPipeline(pipelines, S);
        Application app = entryHandler.appGenerator(lattice, indexList, ruleList,pipelines);
        ArrayList<Table> tables = new ArrayList<Table>(app.getAppTable().values());
        //system.out.println("/////////////////////||||||||||||||\\\\\\\\\\\\\\\\\\");
        System.out.println(pipelines);
        System.out.println(app);
        /*ClassificationValidator validator = new ClassificationValidator(pipelines,"/input/ovs.xml",tables);
        validator.total_flows_analysis();*/
         ApplicationHandler app_handler = new ApplicationHandler(app);
         app_handler.deliver_pipeline_to_switch();
        //System.out.println(p);
        
        /** Update of lattice 
         * First we generate a temporary lattice
         * The second phase would be to fusion the two lattice
         * The last part would be to update the trie **/
        // Lattice Initialization
        int lat_id = 1;
        Trie trie = new Trie();

        if (cmdline.equals("-compile") ) {
            /*** In the case of the first parameter equals -compile **/
            // Compute trie
            trie.CreateTrie( indexList);
            trie.CompressTrie(trie.root);
            ////system.out.println(trie.root);
            
            // Save trie
            File file = new File(tmpTrie);
             
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(trie);
            } catch (FileNotFoundException fnfe) {
                //system.out.println("Could not find file : " + tmpTrie);
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                //system.out.println("I/O Exception while writing to file");
                ioe.printStackTrace();
            } finally {
                if (fos != null) {
                    fos.close();
                }
              
            }
        
        }
        
        
        if (cmdline.equals("-update") ) {
        /*** In the case of the first parametre equals -update **/    
            //Load trie
            File file = new File(tmpTrie);
            
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                 
                trie = (Trie) ois.readObject();
              } catch (FileNotFoundException fnfe) {
                  //system.out.println("Could not find file: "+ tmpTrie);
                  fnfe.printStackTrace();
              } catch (ClassNotFoundException cnfe) {
                  //system.out.println("File format is wrong :(");
                  cnfe.printStackTrace();
              } catch (IOException ioe) {
                  //system.out.println("I/O Exception while reading file");
                  ioe.printStackTrace();
              } finally {
                  if (fis != null) {
                      fis.close();
                }
              }
            
            // Update trie
            RuleHandler rulehander2 = new RuleHandler();
            Map<Integer,Rule> updateList =  rulehander2.GetRulesFromRCF(inputFilenameRCF_up);
            for (Map.Entry<Integer,Rule> r_up : updateList.entrySet()){
                trie.UpdateTrie(r_up.getValue());
            }
        }
        
        String trieToText = trie.formatTrieJson(trie.root);//trie.formatTrie(trie.root);
        ////system.out.println(trieToText);
        try
        {
            FileWriter fw = new FileWriter(adressedufichier, false);
            
            BufferedWriter output = new BufferedWriter(fw);
            String header = "{\n\t\"chart\": {\n\t\t\"rootOrientation\": \"WEST\",\n\t\t\"container\": \"#tree-simple\",\n\t\t\"padding\": 0," +
                    "\n\t\t\"connectors\": {\n\t\t\t\"type\": \"curve\"\n\t\t},\n\t\t\"node\": {\n\t\t\t\"HTMLclass\": \"nodeExample1\"\n\t\t}\n\t},\n\n";
            //header+="\tnodeStructure:{\n";
            output.write(header);
            output.write(trieToText);
            String foot = "}";
            output.write(foot);
            
            output.flush();
            output.close();
            
//            //system.out.print(header);
//            //system.out.print(trieToText);
//            //system.out.print(foot);
            
            
        }
        catch(IOException ioe){System.out.println("erreur : " + ioe );}
        
    }
}