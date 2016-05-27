package handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
     
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import struct.ConstraintType;
import struct.Constraints;



public class ConstraintHandler {
	
	public String ConstraintXML;
	public HashMap<ConstraintType,HashMap<Constraints,Object>> MapConstraint = new  HashMap<ConstraintType,HashMap<Constraints,Object>>();
	public ConstraintHandler(String ConstraintXML){
		 this.ConstraintXML=ConstraintXML;
		
	}
	public void parse_ConstraintXML() throws ParserConfigurationException, SAXException, IOException{
		  HashMap<Constraints,Object> constraints_map = new HashMap<Constraints,Object>(); 
	      File inputFile = new File(this.ConstraintXML);
	      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	      Document doc = dBuilder.parse(inputFile);
	      doc.getDocumentElement().normalize();
	      System.out.println("Root element :" 
	              + doc.getDocumentElement().getNodeName());
	      List<Constraints> constraints = Arrays.asList(Constraints.values());
	      System.out.println(constraints);
	      for(int cpt=0 ; cpt<constraints.size();cpt++){
	        NodeList nList = doc.getElementsByTagName(constraints.get(cpt).toString());
	        for(int i=0;i<nList.getLength();i++){
          	   Node node = nList.item(i);
	    	   if(node.getNodeType() == Node.ELEMENT_NODE ){
	    	     System.out.println(node);
	    	     Element element = (Element) node;
	    	     NodeList constraints_node = element.getElementsByTagName("constraint");
	    	     for(int j=0;j<constraints_node.getLength();j++){
	    	    	 if(constraints_node.item(j).getParentNode()== node ){
	    	    	 Node constraint_node = constraints_node.item(j);
	    	    	  if(constraint_node.getNodeType() == Node.ELEMENT_NODE ){
	    	    	    Element element_constraint = (Element) constraint_node;
	    	    	    // get Type constraint and referenced value
	    	    	     String type_constraint = element_constraint.getAttribute("type");
	    	    	     System.out.println("type constraint");
	    	    	     ConstraintType constraint= null;
	    	    	     if(type_constraint.trim().equals("RESSOURCE")){
	    	    	    	constraint = ConstraintType.RESSOURCES;
	    	    	    	
	    	    	     }
	    	    	     else if(type_constraint.trim().equals("PERFORMANCE")){
	    	    	    	constraint = ConstraintType.PERFORMANCE;
	    	    	     }
	    	    	     if(!this.MapConstraint.containsKey(constraint)){
	    	    	    	    System.out.println("I'm in");
	    	    	    		this.MapConstraint.put(constraint, new HashMap<Constraints,Object>());
	    	    	    		System.out.println(this.MapConstraint.get(constraint));
	    	    	    }
	    	    	     System.out.println(type_constraint);
	    	    	     System.out.println(element_constraint.getTextContent().trim());
	    	    	    NodeList constraints_value = element.getElementsByTagName(element_constraint.getTextContent().trim());
	    	    	    
	    	    	    for(int k=0;k<constraints_value.getLength();k++){
	    	    	    Element node_const_val = (Element)constraints_value.item(k);
	    	    	    if(node_const_val.getNodeType()== Node.ELEMENT_NODE){
	    	    	      System.out.println("entreer");
	    	    	      if(constraints.get(cpt).toString()==Constraints.path_processing.toString()){
	    	    	    	  ArrayList<String>  tablesID ;
	    	    	    	    if((this.MapConstraint.get(constraint)).containsKey(Constraints.path_processing)){
	    	    	    	      tablesID = (ArrayList<String>)(this.MapConstraint.get(constraint)).get(Constraints.path_processing);  
	    	    	    	
	    	    	    	  }
	    	    	    	  else{
	    	    	    	      tablesID= new ArrayList<String>();
	    	    	    	  }
	    	    	    	  String tableid = ((Element)constraints_value.item(k)).getAttribute("id");
	    	    	    	  System.out.println(tableid);
	    	    	    	  tablesID.add(tableid);
	    	    	    	  (this.MapConstraint.get(constraint)).put(Constraints.path_processing,tablesID);
	    	    	    	  System.out.println("constraints_map");
	    	    	    	  System.out.println(constraints_map);
	    	    	    	  
	    	    	      }
	    	    	      if(constraints.get(cpt).toString()==Constraints.table.toString()){
	    	    	    	// matching field
	    	    	    	// number entry
	    	    	    	  String id_table=((Element)constraints_value.item(k).getParentNode()).getAttribute("id");
	    	    	    	  HashMap<String,HashMap<Constraints,Object>> constraint_table;
	    	    	    	  HashMap<Constraints,Object> constraint_tables_id;
	    	    	    	  if((this.MapConstraint.get(constraint)).containsKey(Constraints.table)){
	    	    	    		 constraint_table = (HashMap<String, HashMap<Constraints,Object>>) (this.MapConstraint.get(constraint)).get(Constraints.table);
	    	    	    		 if(constraint_table.containsKey(((Element)constraints_value.item(k).getParentNode()).getAttribute("id"))){
	    	    	    			 constraint_tables_id = constraint_table.get(((Element)constraints_value.item(k).getParentNode()).getAttribute("id"));
	    	    	    			 
	    	    	    		 }
	    	    	    		 else{
	    	    	    			 constraint_tables_id = new HashMap<Constraints,Object>();
	    	    	    		 }
	    	    	    		
	    	    	    		 
	    	    	    	    }
	    	    	    	    else{
	    	    	    	      constraint_table = new HashMap<String,HashMap<Constraints,Object>>();
	    	    	    	      constraint_tables_id=new HashMap<Constraints,Object>();
	    	    	    	    	
	    	    	    	    }
	    	    	    	  if(((Element)constraints_value.item(k)).getNodeName()=="matchField"){
	    	    	    		System.out.println(((Element)constraints_value.item(k)).getTextContent());
	    	    	    		constraint_tables_id.put(Constraints.matching,((Element)constraints_value.item(k)).getTextContent().trim());
	    	    	    	    System.out.println("constraint_table");
	    	    	    	    System.out.println(constraint_table);
	    	    	    	  }
	    	    	    	  if(((Element)constraints_value.item(k)).getNodeName()=="number_entry"){
	    	    	    		  constraint_tables_id.put(Constraints.number_entry,((Element)constraints_value.item(k)).getTextContent().trim());
	    	    	    		  System.out.println("constraint_table");
		    	    	    	  System.out.println(constraint_table);
	    	    	    	  }
	    	    	    	  constraint_table.put(id_table,constraint_tables_id);
	    	    	    	  System.out.println(constraint_table);
	    	    	    	  (this.MapConstraint.get(constraint)).put(Constraints.table,constraint_table);
	    	    	    	  System.out.println(constraints_map);
	    	    	    	  
	    	    	      }
	    	    	      
	    	    	      
	    	    	    	
	    	    	    }
	    	    	   }
	  
	    	    	    
	    	    	  }
	    	    	 }
	    	     }
	    	     
	    	  }
	      }
	      }
	      
	        
	      }		
	
	public void  get_constraints(ConstraintType constraint_type ,Constraints constraint){
		
		
	}
	public void get_spec_from_profile(){
		
	}
	
	
	

}
