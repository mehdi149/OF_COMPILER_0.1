package handler;
import io.netty.buffer.ByteBuf;
import static io.netty.buffer.Unpooled.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import net.floodlightcontroller.core.internal.OFMessageEncoder;
import net.floodlightcontroller.packetstreamer.thrift.Message;

import org.projectfloodlight.openflow.exceptions.OFParseError;
import org.projectfloodlight.openflow.protocol.OFFactories;
import org.projectfloodlight.openflow.protocol.OFFactory;
import org.projectfloodlight.openflow.protocol.OFFlowMod;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFMessageReader;
import org.projectfloodlight.openflow.protocol.OFType;
import org.projectfloodlight.openflow.protocol.OFVersion;
import org.projectfloodlight.openflow.protocol.action.OFAction;
import org.projectfloodlight.openflow.protocol.match.Match;
import org.projectfloodlight.openflow.protocol.match.MatchField;

/*import org.apache.derby.tools.sysinfo;
import org.apache.thrift.protocol.TProtocol;
import org.openflow.io.OFMessageAsyncStream;
import org.openflow.io.OFMessageInStream;
import org.openflow.protocol.OFEchoReply;
import org.openflow.protocol.OFFlowMod;
import org.openflow.protocol.OFMatch;
import org.openflow.protocol.OFEchoRequest;*/
//import org.openflow.protocol.OFMessage;
/*import org.openflow.protocol.OFPacketIn;
import org.openflow.protocol.OFPacketOut;
import org.openflow.protocol.OFPort;
import org.openflow.protocol.OFType;
import org.openflow.protocol.action.OFAction;
import org.openflow.protocol.action.OFActionOutput;
import org.openflow.protocol.factory.BasicFactory;
import org.openflow.protocol.factory.OFActionFactoryAware;
import org.openflow.protocol.factory.OFMessageFactoryAware;
import org.openflow.protocol.factory.OFStatisticsFactoryAware;
import org.openflow.util.LRULinkedHashMap;
import org.openflow.util.U16;
import static io.netty.buffer.Unpooled.*;*/

public class OpenFlow_Handler {
	public static ThreadSockets thread_sockets;
    public OpenFlow_Handler() {
    	
    	String[] args;
    	args = new String[3];
    	args[0]="localhost";
    	// Controller Port
    	args[1]="6633";
    	//socket Port 
    	
    	args[2]="9992";
        try {
            if (args.length != 3)
                throw new IllegalArgumentException("insuficient arguments");
            String host = args[0];
            int remoteport = Integer.parseInt(args[1]);
            int localport = Integer.parseInt(args[2]);
            System.out.println("Starting socket for " + host + ":" + remoteport
                    + " on port " + localport);
            ServerSocket server = new ServerSocket(localport);
            while (true) {
                thread_sockets = new ThreadSockets(server.accept(), host, remoteport);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

class ThreadSockets extends Thread {
    public  Socket sClient;
    public static LinkedHashMap<String,List<OFAction>> RuleAction = new LinkedHashMap<String,List<OFAction>>() ;
    public OutputStream outToClient;
    private final String SERVER_URL;
    private final int SERVER_PORT;
    public static PrintWriter writerRcf;
    static int nbrOFRules=0;
    ThreadSockets(Socket sClient, String ServerUrl, int ServerPort) throws FileNotFoundException, UnsupportedEncodingException {
        this.SERVER_URL = ServerUrl;
        this.SERVER_PORT = ServerPort;
        this.sClient = sClient;
        PrintWriter writer = new PrintWriter("RuleFile.rcf", "UTF-8");
        this.writerRcf = writer;
        // add initial line to file
        writer.println("[Relational Context]");
        writer.println("Default Name");
        writer.println("[Binary Relation]");
        writer.println("Default Name");
        writer.close();
        this.start();
    }
    public static void OFHandler(List<OFMessage> msgs) throws IOException{

        for (OFMessage msg : msgs){
        	System.out.println(msgs.size());
        	
        	if( msg.getType() == OFType.FLOW_MOD){
        		// get of flow mod rule and add it to rcf 
        	
        		
        		OFFlowMod flow_mod_msg = (OFFlowMod)msg;
        		Match match = flow_mod_msg.getMatch();
        		
        		// get Action 
        		
        		String match_string = match.toString();
        		if(!match_string.equals("OFMatchV3Ver13()")){
        	    nbrOFRules+=1;
                System.out.println("===============Number of Rules=============== : "+nbrOFRules);
                System.out.println("of flow mod");
        		List<OFAction> actions =  flow_mod_msg.getActions();
        		System.out.println("actions : "+actions);
        		System.out.println("match :"+match);
        		match_string =  match_string.replace("OFMatchV3Ver13(","");
        		match_string =  match_string.replace(")","");
        		List<String>Attributes = Arrays.asList(match_string.split(","));
        		System.out.println(Attributes.size());
        		System.out.println("Attributes : "+Attributes);
        		String Attribute_rcf_delimited = String.join(" | ",Attributes);
        		System.out.println(Attribute_rcf_delimited);
        		// Open the file
        	
        		
        		FileInputStream fstream = new FileInputStream("RuleFile.rcf");
       
        		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        		String strLine;
        		int RulePosition = 4;
        		int AttrPosition=5;

        		//Read File Line By Line
        		int cpt=0;
        		String New_Rules=""; 
        		 int nbrOfRules=0;
        		while ((strLine = br.readLine()) != null)   {
        			System.out.println(" in");
        		
        		  String RuleLine;
        		  List<String> NewMatrice=new ArrayList<String>();
        		  String NewMatriceLine="";
        		 
        		  if (cpt == RulePosition){
        			  System.out.println("in Rule position");
        			  RuleLine = (String)strLine;
        			  System.out.println(RuleLine);
        		      System.out.println("RuleSize > 0");
        			  System.out.println(strLine.split("\\|")[0]);
        			  System.out.println(RuleLine.split("\\|").length);
        			  nbrOfRules = Arrays.asList(RuleLine.split("\\|")).size();
        			  String RuleName = "R"+(nbrOfRules-1);
        			  RuleAction.put(RuleName, actions);
        			  New_Rules= strLine.concat(RuleName+" | ");
        			  System.out.println("New_Rules");
        			  System.out.println(New_Rules);
        		  }                   			  
        			  //br.write(strLine);                    			  
        		  if(cpt == AttrPosition){
        			  Attribute_rcf_delimited=strLine;
        			  System.out.println("in Attr Position");
        			  List<String> attrs = Arrays.asList(strLine.split("\\|"));
        			  for(int tmp=0;tmp<attrs.size();tmp++){
        				 
        				 attrs.set(tmp,attrs.get(tmp).trim());
        			  }
        			  for(int tmp=0;tmp<Attributes.size();tmp++){
        				  Attributes.set(tmp, Attributes.get(tmp).trim());
        			  }
        			  // Adding New Attributes  to  Rcf File 
        			  System.out.println(attrs);
        			  for(String attr :Attributes){
        				  System.out.println(attr);
        				  
    					  // get  matrice from file
    					  if(NewMatrice.size()==0){
    						  br.mark(5);
    					  for(int i=0;i<nbrOfRules-1;i++){
    					  
    						  String strLine1 = br.readLine(); 
    						  String lineMatrice = strLine1;
    						  System.out.println("lineMatrice: "+lineMatrice);
    						  NewMatrice.add(lineMatrice);
    					  }
    					  br.reset();
    					  }
    					
        				  if(!attrs.contains(attr.trim())){
        					  //Concat 
        					  System.out.println(attr);
        					  attrs = new ArrayList<String>(attrs);
        					  attrs.add(attr);
        					  System.out.println("strline : "+strLine);
        					  
        					  Attribute_rcf_delimited =strLine.concat(" | "+attr.trim());
        					  strLine=Attribute_rcf_delimited ;
        					  System.out.println("Delimited Attr :"+Attribute_rcf_delimited);
        					 
        					  System.out.println("nbrOfRules:  "+nbrOfRules);
        					
        					  System.out.println("size matrice : "+NewMatrice.size());
        					  System.out.println();
        					  // Add Column to matrice
        					  for(int i=0;i<nbrOfRules-1;i++){
        							  System.out.println("line index : "+i);
        							  String lineMatrice= NewMatrice.get(i);
        							  
        							  NewMatrice.set(i,lineMatrice.concat("0 "));	  
        					  }
        					  
        				  }
        			  }
        			  // New matrice line for the new rule 
        			  System.out.println("Attribute size :"+attrs.size());
        			  for (String attr : attrs){
        				  if (!Attributes.contains(attr)) NewMatriceLine+="0 ";
        				  else NewMatriceLine+="1 ";
        				  
        			  }
        			  NewMatrice.add(NewMatriceLine);
        			  System.out.println("New_Rules");
        			  System.out.println(New_Rules);
        			  ThreadSockets.generateRcfFile(New_Rules, Attribute_rcf_delimited ,NewMatrice);
        			  
        		  }
        		 
        		cpt++;		  
        		}
        		System.out.println(cpt);
        		if (cpt == RulePosition){
        		  System.out.println("Doesn't find");
  				  String RuleName = "R"+"0"+" | ";
  				  RuleAction.put("R0", actions);
  				  List<String> Matrice=new ArrayList<String>();
  				  String line = "";
  				  for(int i=0 ;i<Attributes.size();i++){
  					  line+="1 ";
  				  }
  				  Matrice.add(line);
  				  // Generate New Rcf File 
  				  ThreadSockets.generateRcfFile(RuleName, Attribute_rcf_delimited, Matrice);
        		}
        		//Close the input stream
        		br.close(); 

        		}
        		
        	}
        	
        		
        		
        		
        	}
    
    }
    public static  void generateRcfFile(String Rules , String Attributes , List<String> matrices ) throws IOException{

    	System.out.println("generate RCF FILE");
        PrintWriter writer = new PrintWriter("tmp_RuleFile.rcf", "UTF-8");
        // add initial line to file
        writer.println("[Relational Context]");
        writer.println("Default Name");
        writer.println("[Binary Relation]");
        writer.println("Default Name");
        System.out.println("Rules");
        System.out.println(Rules);
        writer.println(Rules);
        System.out.println(Rules);
        System.out.println(matrices);
        writer.println(Attributes);
        for (String line : matrices){
        	writer.println(line);
        }
        writer.println("");
        writer.println("[END Relational Context]");
        writer.close();
        File oldFile = new File("RuleFile.rcf");;

        // And rename tmp file's name to old file name
        File newFile = new File("tmp_RuleFile.rcf");
        newFile.renameTo(oldFile);
    	
    }

		
    
    public  void replyToSwitch(byte[] reply,int index ,int readable_bytes) throws IOException{
    	 final Object lock = new Object();
	        synchronized(lock){
	        	outToClient.write(reply, index, readable_bytes);
	        	
	        }
    	
    }
    public void run() {
        try {
            final byte[] request = new byte[1024];
            byte[] reply = new byte[4096];
            final InputStream inFromClient = sClient.getInputStream();
            this.outToClient = sClient.getOutputStream();
            Socket client = null, server = null;
            // connects a socket to the server
            try {
                server = new Socket(SERVER_URL, SERVER_PORT);
            } catch (IOException e) {
                PrintWriter out = new PrintWriter(new OutputStreamWriter(
                        outToClient));
                out.flush();
                throw new RuntimeException(e);
            }
            final InputStream inFromServer = server.getInputStream();
            final OutputStream outToServer = server.getOutputStream();
            int nbrOfRules=0;
            new Thread() {
                public void run() {
                    int bytes_read;
                    try {
                        while ((bytes_read = inFromClient.read(request)) != -1) {
                            outToServer.write(request, 0, bytes_read);
                            outToServer.flush();
                            ByteBuffer bb = ByteBuffer.wrap(request);
                            bb.order(ByteOrder.BIG_ENDIAN);
                            short short1 = bb.getShort();
                            short short2 = bb.getShort();
                            long long1 = bb.getLong();
                            /*BasicFactory factory = new BasicFactory();
                            List<OFMessage> msg = factory.parseMessages(bb);
                            for (OFMessage ofmessage:msg){
                            	System.out.println("type of message");
                            	System.out.println(ofmessage.getType());
                            }
                            System.out.println(msg);*/

                        }
                    } catch (IOException e) {
                    }
                    try {
                        outToServer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

            int bytes_read;
            try {
                while ((bytes_read = inFromServer.read(reply)) != -1) {
                	 System.out.println("serialize");
                    //outToClient.write(reply, 0, bytes_read);
                    replyToSwitch(reply, 0, bytes_read);
                    ByteBuf bb= wrappedBuffer(reply,0,bytes_read);
                    bb.order(ByteOrder.BIG_ENDIAN);
                    OFMessageReader<OFMessage> reader= OFFactories.getGenericReader();
                    final List<OFMessage> results = new ArrayList<OFMessage>();
                    OFMessage demux;
                    OFMessage ofm;
                    int limit =0;
                    while (bb.isReadable()) {
                        ofm = reader.readFrom(bb);
                       ByteBuf temp_buf = buffer();
                       ofm.writeTo(temp_buf);
                       int index= temp_buf.readableBytes();
                       bb.getBytes(index, bb);
                       results.add(ofm);
                        
                    }
                   
				System.out.println(results);
                    // get of flow Mod packet type and store it to rcf file 
				        final Object lock = new Object();
				        synchronized(lock){
						ThreadSockets.OFHandler(results);
				        }
				
                    }        
                
            } catch (IOException e) {
                e.printStackTrace();
            } catch (OFParseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                try {
                    if (server != null)
                        server.close();
                    if (client != null)
                        client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outToClient.close();
            sClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}