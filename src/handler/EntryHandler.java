package handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.*;
import java.util.Iterator;

import struct.Application;
import struct.Entry;
import struct.AttributeType;
import struct.Index;
import struct.Lat_Attribute;
import struct.Lat_Object;
import struct.Lattice;
import struct.MatchType;
import struct.Pipeline;
import struct.Rule;
import struct.SubsetTable;
import struct.Table;
import struct.Trie;
/**
 * 
 * @author root
 * The EntryHandler class implements funciton to manipulate the entries to read.
 */
public class EntryHandler {
	/**
	 * 
	 * @param lattice
	 * @param indexList
	 * @param rulesList
	 * @return The Arraylist of subsettables. Each subset table will include :
	 *  The rules of the subject.
	 */
	public ArrayList<SubsetTable> generateSubsetTables( Lattice lattice, ArrayList<Index> indexList, Map<Integer, Rule> rulesList ){
		ArrayList<SubsetTable> subsetTableList = new ArrayList<SubsetTable>();
		ArrayList<Lat_Attribute> allAttributsCache = new ArrayList<Lat_Attribute>();
		for (Map.Entry<Integer,Lat_Attribute> e : lattice.attributes.entrySet()){
			allAttributsCache.add(e.getValue());
		}
		
		for (int i=0; i<indexList.size(); i++){
			/** Get list of attribute of the index**/
			Set<Lat_Attribute> attrIndexCache = new HashSet<Lat_Attribute>();
			for (int j=0; j<indexList.get(i).key.size(); j++){
				attrIndexCache.add(indexList.get(i).key.get(j));
			}
			//System.out.println(attrIndexCache);
			SubsetTable subsetTable = new SubsetTable (i+100);
			// Recuperer les attributs pour completer la regle
			ArrayList<AttributeType> fieldTypeStack = new ArrayList<AttributeType>();
			ArrayList<Lat_Object> ruleSubset = indexList.get(i).ruleSubset;
			//System.out.println(ruleSubset.get(0) + " " + ruleSubset.get(0).id);
			for (int j=0; j<ruleSubset.size(); j++){
				//System.out.println(ruleSubset.get(j).id);
				Rule rule = rulesList.get( ruleSubset.get(j).id-1 );
				//System.out.println(rule.matchfields.size());
				//System.out.println(rule.matchfields);
				for (int k=0; k<allAttributsCache.size(); k++){
					Lat_Attribute attribut = allAttributsCache.get(k);
					//System.out.println(attribut);
					if ( !fieldTypeStack.contains(attribut.type) )
					{
						fieldTypeStack.add(attribut.type);
						//System.out.println(fieldTypeStack);
						//System.out.println(attribut);
						if (attrIndexCache.contains(attribut)){
							rule.matchfields.remove(attribut.id);
							
						} else {
							Lat_Attribute newAttribut = new Lat_Attribute(attribut.id);
							newAttribut.type = attribut.type;
							newAttribut.value = "*";
							rule.matchfields.put(newAttribut.id, newAttribut);
						}
					}
					
				}
				
				subsetTable.rules.add(rule);
			}
			
			subsetTableList.add(subsetTable);
		}
		//System.out.println(subsetTableList.get(0));
		return subsetTableList;
	}
	
	public ArrayList<Index> SortIndexList (ArrayList<Index> indexList)
	{
		ArrayList<Index> Cache = new ArrayList<Index>();
		Trie T = new Trie();
		//System.out.println(indexList);
		for(int i=0;i<indexList.size();i++){
			for(int j=i;j<indexList.size();j++){
				Index index1 = indexList.get(i);
				Index index2 = indexList.get(j);
				for(int e=0;e<index1.key.size();e++){					
					for(int f=0;f<index2.key.size();f++){
						if(index1.key.get(e).equals(index2.key.get(f))){
							index1.key.get(e).weight++;
							if ( index1.key.get(e).type.equals(AttributeType.IpDst) | index1.key.get(e).type.equals(AttributeType.IpSrc))
								{	
								System.out.println("changed");// search type = Longest Prefix Match 
								index1.key.get(e).priority = T.ComputePriority(index1.key.get(e).weight, "LPM");
								System.out.println(index1.key.get(e).priority );
								}
							if ( index1.key.get(e).type.equals(AttributeType.EtherDst) | index1.key.get(e).type.equals(AttributeType.EtherSrc))
								{// search type = HASH 
								index1.key.get(e).priority = T.ComputePriority(index1.key.get(e).weight, "HASH");
								System.out.println(index1.key.get(e).priority );
								}
							else 
								{// search type = EXACT 
								index1.key.get(e).priority = T.ComputePriority(index1.key.get(e).weight, "EXACT");
								System.out.println(index1.key.get(e).priority );
								}}}}}}
		System.out.println(indexList);
		return indexList;
	}
	
	public ArrayList<Index> ma_fonction(ArrayList <Index> indexList)
	{
		ArrayList<Index> Cache = new ArrayList<Index>();
		float max=0;
		int cpt = 0;
		int i=0,k=0;
		//System.out.println(indexList);
		   while(i<indexList.size())
		   { 
			 for (int j=0; j<indexList.get(i).key.size();j++)
				 indexList.get(i).prio += indexList.get(i).key.get(j).priority;
			
			 //System.out.println(indexList.get(i).id +" "+ indexList.get(i).prio);
			 i++;
		  }
		 while (k<=indexList.size() | indexList.size()!=0){
		  for(int j=0;j<indexList.size();j++)
		  {
			  if(indexList.get(j).prio>=max){
				  max=indexList.get(j).prio;
				  cpt=j;
			  }
		  }	 
		 // System.out.println(cpt);
		 //System.out.println("======");
		 // System.out.println(indexList.get(cpt).id);
		  Cache.add(k,indexList.get(cpt));
		  //System.out.println(k);
		  indexList.remove(indexList.get(cpt));
		  //System.out.println(indexList);
		  max=-1;
		  
		 k++;
		  
		  }
		  //System.out.println(Cache);
		   
		return Cache;
	}
	/** 
	 * @param indexList
	 * @return The arrayList of the pipelines.
	 *  
	 */
	public ArrayList<Pipeline> generateIndexPipeline(ArrayList<Index> indeks, Switch S){
		ArrayList<Index> indexList = new ArrayList<Index>();
		indexList = SortIndexList(indeks);
		//System.out.println(indexList);
		ArrayList<Pipeline> pipelineList = new ArrayList<Pipeline>();
		Map<AttributeType, Table> tableList = new HashMap<AttributeType, Table>();
		int idTable = 0;
		for(int i=0;i<indexList.size();i++){
			Index index = indexList.get(i);
			Pipeline pipeline = new Pipeline(index.id);
			for(int j=0;j<index.key.size();j++){
				Lat_Attribute matchfield = index.key.get(j);
				Table table;
				if(tableList.containsKey(matchfield.type)){
					table = tableList.get(matchfield.type);
				}
				else{
					table = new Table(idTable, matchfield.type);
				
					idTable++;
				}
				// Create Entry
				String action;
				int tempId ;
				int in_metadata = 'N';
				int out_metadata = 'X';
				if(j+1<index.key.size()){					
					action ="GO_TO_TABLE";
					if(tableList.containsKey(index.key.get(j+1))){
						tempId	= tableList.get(index.key.get(j+1)).id; 						
					}
					else{
						tempId = idTable; 						
					}
					out_metadata = index.id;
				}
				else {
					tempId = index.id+100; 
					action = "GO_TO_SUBTABLE";
				} 
				  if(j!=0) in_metadata = index.id;				  
					Entry entry = new Entry(index.id, matchfield, action, tempId, in_metadata,out_metadata);
					//System.out.println(entry.matchfield);
			
				boolean reached = false;
				if (table.entry.size()<table.number_entry)
					table.entry.add(entry);
				else{
					MatchType match_type = table.matchType;
					reached = true;
					/*
					 *  if number entry in table is reached 
					 *  balance new entry in other table with the same type of search
					 */
					  
					// search table in pipeline with the same type of matching and the maximum number of entry available
					Table t=null;
					int max_entry_available = 10000;
					
					for (Map.Entry<AttributeType, Table> e : tableList.entrySet()){
						
						if (match_type == e.getValue().matchType && max_entry_available >  (e.getValue().entry.size()- e.getValue().number_entry)){
							
						     t  = e.getValue();
							max_entry_available = e.getValue().entry.size()- e.getValue().number_entry;	
							
						}
						
					}
						t.entry.add(entry);	
					
					
					
					
				
					
					
				}
				
				if(!tableList.containsKey(matchfield.type)){
					tableList.put(matchfield.type, table);
					table.setTableMatchType(matchfield.type);
				}
					if(pipeline.tables.containsKey(table.id)) pipeline.tables.remove(table.id);
					pipeline.tables.put(table.id, table);
			} pipelineList.add(pipeline);
		}
		//System.out.println(pipelineList.get(0).tables);
		return pipelineList;
	}
	/**
	 * 
	 * @param pipelineList
	 * @return The matrix of chaining between the tables
	 */
	public int[][] generateMatrix(ArrayList<Pipeline> pipelineList){
		int[][] matrixTabTab = new int[32][32];
		int[][] matrixTabSub = new int[32][100];
		Set<Integer> setTab = new HashSet<Integer>() ;
		Set<Integer> setSub = new HashSet<Integer>() ;
		Iterator<Pipeline> pipeIter = pipelineList.iterator();
		while(pipeIter.hasNext()){
			Pipeline pipeline = pipeIter.next();
			for(Map.Entry<Integer, Table> Element : pipeline.tables.entrySet()){
				Table table = Element.getValue();
				Iterator<Entry> entryIter = table.entry.iterator(); 
				while(entryIter.hasNext()){
					Entry entry = entryIter.next();
					String action = entry.action;
					if(action == "GO_TO_TABLE"){
						matrixTabTab[table.id][entry.nextTableId]=1;
						setTab.add(table.id);
						setTab.add(entry.nextTableId);
					}
					if(action == "GO_TO_SUBTABLE"){
						matrixTabSub[table.id][entry.nextTableId-100]=1;
						setTab.add(table.id);
						setSub.add(entry.nextTableId);
					}
				}
			}
		}
		int[][] finalMatrix = new int[setTab.size()][setTab.size()+setSub.size()];
		for(int i=0;i<setTab.size();i++){
			for(int j=0;j<setSub.size()+setTab.size();j++){
				if(j<setTab.size()){
					finalMatrix[i][j]=matrixTabTab[i][j];
				}
				else{
					finalMatrix[i][j]=matrixTabSub[i][j-setTab.size()];
				}
			}
		}
		return finalMatrix;		
	}
	
	public ArrayList<Pipeline> SortPipeline(ArrayList<Pipeline> pipelines, Switch S)
	{
		ArrayList<Pipeline> cachePipeline = new ArrayList<Pipeline>();
		for(int i=0; i<S.path_processing.size();i++){
			
			for(int j=0; j<pipelines.size();j++){
				System.out.println("............");
				for(Map.Entry<Integer,Table> e : pipelines.get(j).tables.entrySet()){
					if(e.getValue().matchField.equals(S.path_processing.get(i).matchField)){
						//e.getValue().number_entry=S.path_processing.get(i).number_entry;
						if (!cachePipeline.contains(pipelines.get(j))){
							
							cachePipeline.add(pipelines.get(j));
						}
					}
				}
			}
		}
		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
		System.out.println("-----------------------------");
		//System.out.println(cachePipeline);
		return cachePipeline;
	}
	
	public void createRules(ArrayList<Pipeline> p) throws IOException {
		
		PrintWriter writer = new PrintWriter("output/OF_Actions.txt", "UTF-8");
		//BufferedReader br = new BufferedReader(f);
		String s = null;
		for (int i=0; i<p.size();i++){
			for(Map.Entry<Integer,Table> e : p.get(i).tables.entrySet()){
				Table T = e.getValue();
				for (int j=0; j<T.entry.size();j++){
					
						s = "priority=0, in_port="+T.entry.get(j).in_metadata+", match="+T.entry.get(j).matchfield.type+
								":"+T.entry.get(j).matchfield.value+", action=GO_TO_TABLE_"+T.entry.get(j).nextTableId;
						writer.println(s);
				
				}}}
		
		writer.close();
		remove_occurence();
		/*
		try {
			fos = new FileOutputStream(f);
			System.out.println("ok");
		}catch (IOException ioe) {
			  System.out.println("I/O Exception while reading file");
			  ioe.printStackTrace();
		  }
		if (fos != null) {
			fos.close();
		    }*/
	}
	public void remove_occurence() throws IOException{
	      BufferedReader br = new BufferedReader(new FileReader("output/OF_Actions.txt"));
	  	  ArrayList<String> S = new ArrayList<String>();
	  	  int i= 0;
	  	  String line = null;
	      while((line = br.readLine()) != null){
	    	  //System.out.println(";;;;;;;;;;;;;;");
	    	  S.add(line);
	    	  i++;
	      }
	      //System.out.println(S.size());
	      //System.out.println("========");
	      for(int k=0;k<S.size();k++){
	    	  line = S.get(k);
	    	  int cpt=0;
	    	  for(int e=0; e<S.size();e++){
	    		  if(line.equals(S.get(e))){
	    			  cpt++;
	    			  if(cpt > 1)S.remove(e);
	    		  }}}
	      br.close();
	      PrintWriter writer = new PrintWriter("output/OF_Actions.txt", "UTF-8");
	      writer.println("");
	      for(int k=0; k<S.size(); k++){
	    	  writer.println(S.get(k));
	      }
	      writer.close();
	      //System.out.println(S.size());
	     // System.out.println(S);
	}
	/**
	 * 
	 * @param lattice
	 * @param indexList
	 * @param rulesList
	 * @return The application. It includes :
	 *  The id of the application.
	 *	A map of tables.
	 *  The entries in each sub table	
	 *  The chaining between tables, it's a matrix that presents the 										
	 */
	public Application appGenerator( Lattice lattice, ArrayList<Index> indexList, Map<Integer, Rule> rulesList, ArrayList<Pipeline> pipelineList){
		ArrayList<SubsetTable> SubTab = generateSubsetTables(lattice,indexList,rulesList);
		//ArrayList<Pipeline> pipelineList = generateIndexPipeline(indexList);
		
		
		Map<Integer,SubsetTable> MapSubTab = new HashMap<Integer,SubsetTable>();
		Map<Integer,Table> MapTable = new HashMap<Integer,Table>();
		
		int e=0;
		Iterator<SubsetTable> subtTabIter = SubTab.iterator();
		while(subtTabIter.hasNext() && e<(SubTab.size()+1)){
			SubsetTable table = subtTabIter.next();
			MapSubTab.put(e, table);
			e++;
		}
		ArrayList<Pipeline> PipeList = pipelineList;
		e=0;
		Iterator<Pipeline> pipeIter = PipeList.iterator();
		while(pipeIter.hasNext() && e<100){
			e++;
			for(Map.Entry<Integer, Table> table : pipeIter.next().tables.entrySet()){
				MapTable.put(table.getValue().id, table.getValue());
			}
		}
		int[][] tableChaining = generateMatrix(pipelineList);
		Application app = new Application(0, MapTable, MapSubTab, tableChaining);
		return app;		
	}
}