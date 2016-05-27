package handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import struct.MatchType;
import struct.Pipeline;
import struct.ServiceCurves;
import struct.Table;

import unikl.disco.curves.MaxServiceCurve;
import unikl.disco.curves.ServiceCurve;
import unikl.disco.curves.ArrivalCurve;
import unikl.disco.nc.PmooAnalysis;
import unikl.disco.nc.SeparateFlowAnalysis;
import unikl.disco.nc.TotalFlowAnalysis;
import unikl.disco.network.Link;
import unikl.disco.network.Flow;
import unikl.disco.network.Network;
import unikl.disco.network.Server;

public class ClassificationValidator {

	private ArrayList<Pipeline> PipeliningList;
	private String profileXML;
	private ArrayList<Table> listTables;
	
	


	public ClassificationValidator(ArrayList<Pipeline> PipeliningList,String profileXML,ArrayList<Table> tables){
		
		this.PipeliningList=PipeliningList;
		this.profileXML=profileXML;
		this.listTables = tables;
	}
	
	public void parse_ProfileXML(){
      // get Port Specificity
		
		
	  // get Matching Operation specificity
				
		
	}	
	public Network Define_Network() throws Exception{
		Network network = new Network();
		/*
		 * Define servers
		 * server is a table in the pipelining of a specific switch
		 * 
		 */
		Server[] servers = new Server[this.listTables.size()];
		HashMap<MatchType,ServiceCurves> tables_service = this.define_service_curves();
		HashMap<MatchType,Server> server_matchType = new HashMap<MatchType,Server>();
		int cpt = 0;
		for (Table table : listTables){
			    System.out.println(table.matchType);
				ServiceCurves service_curves =tables_service.get(table.matchType);
				servers[cpt] = network.addServer(service_curves.min_service,service_curves.max_service);
				server_matchType.put(table.matchType, servers[cpt]);
			}
		/*
		 * Define link between servers 
		 * this links is established for each pipeline in the list of pipelining 
		 */
		ArrivalCurve arrival_curve = this.define_arrival_curves();
		for (Pipeline pipeline :this.PipeliningList){
			System.out.println("Define flows");
			LinkedList<Link> path = new LinkedList<Link>();
			System.out.println(pipeline.tables.size());
            ArrayList<Integer> keys = new ArrayList<Integer>();
			for(int key : pipeline.tables.keySet()){
				keys.add(key);
			}
			for(int i=0;i<pipeline.tables.size();i++){
				Server server1 = server_matchType.get(pipeline.tables.get(keys.get(i)).matchType);
				Iterator iter = keys.iterator();
				if (iter.hasNext()){
					int key = (int)iter.next();
					System.out.println("Different de null");
					Server server2 = server_matchType.get(pipeline.tables.get(key).matchType);
					Link link = network.addLink( server1, server2 );
					path.add(link);
				}else{
					System.out.println("next == null");
					network.addFlow( arrival_curve, server1 );	
					continue;
				}
				network.addFlow( arrival_curve, path);
				
				
				
			}
			
		}
			
			
		
		return network;
	}
	public void total_flows_analysis() throws Exception{
		
		System.out.println("total_flow_analysis");
		Network network = this.Define_Network();
		System.out.println(network.getFlows());
		
		for ( Flow flow_of_interest : network.getFlows() ) {
			
			System.out.println( "Flow of interest : " + flow_of_interest.toString() );
			System.out.println();

//			Analyze the network	
//			TFA
			System.out.println( "--- Total Flow Analysis ---" );
			TotalFlowAnalysis tfa = new TotalFlowAnalysis( network );
			
			try {
				tfa.performAnalysis( flow_of_interest );
				System.out.println( "delay bound     : " + tfa.getDelayBound() );
				System.out.println( "     per server : " + tfa.getServerDelayBoundMapString() );
				System.out.println( "backlog bound   : " + tfa.getBacklogBound() );
				System.out.println( "     per server : " + tfa.getServerBacklogBoundMapString() );
				System.out.println( "alpha per server: " + tfa.getServerAlphasMapString() );
			} catch (Exception e) {
				System.out.println( "TFA analysis failed" );
				System.out.println( e.toString() );
			}
			
			System.out.println();
	
//			SFA
			System.out.println( "--- Separated Flow Analysis ---" );
			SeparateFlowAnalysis sfa = new SeparateFlowAnalysis( network );
	
			try {
				sfa.performAnalysis( flow_of_interest );
				System.out.println( "e2e SFA SCs     : " + sfa.getLeftOverServiceCurves() );
				System.out.println( "     per server : " + sfa.getServerLeftOverBetasMapString() );
				System.out.println( "xtx per server  : " + sfa.getServerAlphasMapString() );
				System.out.println( "delay bound     : " + sfa.getDelayBound() );
				System.out.println( "backlog bound   : " + sfa.getBacklogBound() );
			} catch (Exception e) {
				System.out.println( "SFA analysis failed" );
				System.out.println( e.toString() );
			}
			
			System.out.println();
	
//			PMOO
			System.out.println( "--- PMOO Analysis ---" );
			PmooAnalysis pmoo = new PmooAnalysis( network );
			
			try {
				pmoo.performAnalysis( flow_of_interest );
				System.out.println( "e2e PMOO SCs    : " + pmoo.getLeftOverServiceCurves() );
				System.out.println( "xtx per server  : " + pmoo.getServerAlphasMapString() );
				System.out.println( "delay bound     : " + pmoo.getDelayBound() );
				System.out.println( "backlog bound   : " + pmoo.getBacklogBound() );
			} catch (Exception e) {
				System.out.println( "PMOO analysis failed" );
				System.out.println( e.toString() );
			}

			System.out.println();
			System.out.println();
		}
		
		
	}
	public HashMap<MatchType,ServiceCurves> define_service_curves(){
		
		// define service curves for each type of tables in pipelining
		HashMap<MatchType,ServiceCurves> tables_service = new  HashMap<MatchType,ServiceCurves>();
		for (Table table : this.listTables){
			System.out.println(table.matchType);
			if(table.matchType.toString() == "EXACT"){
				// get rate and latency from xml profile 
				// let it static for now 
				ServiceCurve min_service = ServiceCurve.createRateLatency( 10.0e6, 0.01 );
				MaxServiceCurve max_service_curve = MaxServiceCurve.createRateLatency( 100.0e6, 0.001 );
                ServiceCurves service_curves = new ServiceCurves(min_service, max_service_curve);
				tables_service.put(table.matchType,service_curves);
						
				
			}
			else if(table.matchType.toString() == "LPM"){
				// get rate and latency from xml profile 
				// let it static for now 
				ServiceCurve min_service = ServiceCurve.createRateLatency( 10.0e6, 0.01 );
				MaxServiceCurve max_service_curve = MaxServiceCurve.createRateLatency( 100.0e6, 0.001 );
			    ServiceCurves service_curves = new ServiceCurves(min_service, max_service_curve);
			    tables_service.put(table.matchType,service_curves);
			}
			
		}
		return tables_service;
		
		
	}
	public ArrivalCurve  define_arrival_curves(){
		
		// static Arrival curves 
		//let suppose to have for each port transmission per sec is 1GBPS
		// get bandwidth from xml file
		// let it static for now
		ArrivalCurve arrival_curve = ArrivalCurve.createTokenBucket( 0.1e6, 0.1 * 0.1e6 );
		
		return arrival_curve;
		
	}
	
		
	}
	
	
	

