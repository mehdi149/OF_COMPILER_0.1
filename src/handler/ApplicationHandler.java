package handler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.netty.buffer.Unpooled.*;
import net.floodlightcontroller.core.internal.OFConnection;
import net.floodlightcontroller.packetstreamer.thrift.Message.*;
import net.floodlightcontroller.packetstreamer.thrift.PacketStreamer.Client.Factory;

import org.openflow.protocol.action.OFActionOutput;
import org.projectfloodlight.openflow.types.OFPort ;
import org.projectfloodlight.openflow.protocol.OFFactories;
import org.projectfloodlight.openflow.protocol.OFFactory;
import org.projectfloodlight.openflow.protocol.OFFlowAdd;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFVersion;
import org.projectfloodlight.openflow.protocol.action.OFAction;
import org.projectfloodlight.openflow.protocol.action.OFActions;
import org.projectfloodlight.openflow.protocol.instruction.OFInstruction;
import org.projectfloodlight.openflow.protocol.instruction.OFInstructionApplyActions;
import org.projectfloodlight.openflow.protocol.instruction.OFInstructionGotoTable;
import org.projectfloodlight.openflow.protocol.instruction.OFInstructions;
import org.projectfloodlight.openflow.protocol.match.Match;
import org.projectfloodlight.openflow.protocol.match.Match.Builder;
import org.projectfloodlight.openflow.types.EthType;
import org.projectfloodlight.openflow.types.IPv4AddressWithMask;
import org.projectfloodlight.openflow.types.MacAddress;
import org.projectfloodlight.openflow.types.OFBufferId;
import org.projectfloodlight.openflow.types.TableId;
import org.projectfloodlight.openflow.protocol.match.MatchField;

import com.google.common.collect.ImmutableList;


import struct.Application;
import struct.AttributeType;
import struct.Entry;
import struct.Lat_Attribute;
import struct.Rule;
import struct.SubsetTable;
import struct.Table;



public class ApplicationHandler {
	private Application app;
	private OFFactory my13Factory;
	
	public ApplicationHandler(Application app){
		this.app = app;
		this.my13Factory= OFFactories.getFactory(OFVersion.OF_13);
		
	}
	public Match matchfields_to_OFMatch(ArrayList<String> match_fields){
		Match match =null;
		return match;
	}
	public void add_flow (int table_id ,Match myMatch ,List<OFInstruction> myInstructionLists,ArrayList<OFAction> myActionList) throws IOException{
        if(myActionList.size()!=0){
        OFInstructions instructions = my13Factory.instructions();
        OFInstructionApplyActions applyActions = instructions.buildApplyActions()
        	    .setActions(myActionList)
        	    .build();
        myInstructionLists.addAll(ImmutableList.<OFInstruction> of(
                applyActions));
        }
        OFFlowAdd flowAdd = my13Factory.buildFlowAdd()
                 .setBufferId(OFBufferId.NO_BUFFER)
                 .setMatch(myMatch)
                 .setInstructions(myInstructionLists)
                 .setOutPort(OFPort.CONTROLLER)
                 .setTableId(TableId.of(table_id))
               .build();
        
        OutputStream outToClient = OpenFlow_Handler.thread_sockets.outToClient;
        byte[] bytes = new byte[4096];
        ByteBuf buffer = buffer() ;
        flowAdd.writeTo(buffer);    
        bytes = buffer.array();
        System.out.println(bytes);
        System.out.println(bytes.length);
        outToClient.write(bytes,0,buffer.readableBytes());
	}
	public void deliver_pipeline_to_switch() throws IOException{
		Map<Integer,Table> MapTable = this.app.getAppTable();
		Map<Integer,SubsetTable> MapSubTab = this.app.getSubSetTable();
		int[][] tableChaining= this.app.TableChaining;
		OFFactory my13Factory = OFFactories.getFactory(OFVersion.OF_13);
		//System.out.println(MapTable);
		//System.out.println(MapSubTab);
		
		for(int i=0 ;i<MapSubTab.size();i++){
			SubsetTable subset_table = MapSubTab.get(i);
			ArrayList<Rule> rules =  subset_table.rules;
			int id_subset_table = subset_table.id;
			/**
			 * Add subset table to switch
			 */
			 OFActions actions = my13Factory.actions();
			OFAction output = actions.buildOutput()
					    .setPort(OFPort.CONTROLLER)
					    .build();
			Match myMatch = my13Factory.buildMatch().build();
			ArrayList<OFAction> listActions = new ArrayList<OFAction>();
			listActions.add((OFAction)output);
			add_flow(id_subset_table,myMatch,new ArrayList<OFInstruction>(),listActions);
			//Install rules
			for(Rule rule : rules){
				Map<Integer,Lat_Attribute> matchfields =  rule.matchfields;
				System.out.println(matchfields);
			     String Actions = rule.action;
			     Builder buildMatch = my13Factory.buildMatch();
			     Match match;
			    for(Lat_Attribute matchfield :matchfields.values()){
			    	String Field = matchfield.type.toString();
			    	String Value = matchfield.value.toLowerCase().trim();
			    	System.out.println(Value);
			    	if (Value != "*"){
					switch(Field){
					case "EtherType":
						  buildMatch.setExact(MatchField.ETH_TYPE, EthType.of(Integer.parseInt(Value)));
						break;
					case "EtherSrc":
					      buildMatch.setExact(MatchField.ETH_SRC, MacAddress.of(Value)).build();
						break;
					case "EtherDst":
						   buildMatch.setExact(MatchField.ETH_DST, MacAddress.of(Value)).build();
						break;
					case  "PortDst":
						  buildMatch.setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value))).build();
						break;
					case "PortSrc":
						 buildMatch.setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value))).build();
						break;
					case  "IpDst":
						 buildMatch.setMasked(MatchField.IPV4_DST,IPv4AddressWithMask.of(Value)).build();
						break;
					case "IpSrc":
						 buildMatch.setMasked(MatchField.IPV4_SRC,IPv4AddressWithMask.of(Value)).build();
						break;
					case "IngressPort":
						 buildMatch.setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value))).build();
						break;
					case "IngressVPort":
						break;
					case "VlanId":
						break;
					
					}
			    	}
			    }
			    match = buildMatch.build();
				 OFActions actions_subset = my13Factory.actions();
				 OFAction output_subset = actions_subset.buildOutput()
							    .setPort(OFPort.NORMAL)
							    .build();
				ArrayList<OFAction> list_Actions = new ArrayList<OFAction>();
				list_Actions.add((OFAction)output_subset);
				add_flow(id_subset_table,match,new ArrayList<OFInstruction>(),list_Actions);
				
			}
		}
		for (int j=0 ; j<MapTable.size();j++){
			 Table table = MapTable.get(j);
			 int id_table = table.id;
		     AttributeType match_field = table.matchField;
			 ArrayList<Entry> entries = table.entry;
			 
			 /*
			  *  Install miss flow entry to table id: id_table
			  *  send entry flow mod to table id:id_table
			 */
			 OFActions actions = my13Factory.actions();
			OFAction output = actions.buildOutput()
					    .setPort(OFPort.CONTROLLER)
					    .build();
			Match myMatch = my13Factory.buildMatch().build();
			ArrayList<OFAction> listActions = new ArrayList<OFAction>();
			listActions.add((OFAction)output);
			add_flow(id_table,myMatch,new ArrayList<OFInstruction>(),listActions);
			
			for (int k=0 ; k< entries.size();k++){
				Entry  entry= entries.get(k);
				String lat_attribute = entry.matchfield.toString(); 
				String action = entry.action;
			    int next_table_id = entry.nextTableId;
				System.out.println(action);
				System.out.println(next_table_id);
				// parse matchfield and create Ofmatch
				String Field = Arrays.asList(lat_attribute.split("\\=")).get(0);
				Field = Field.replace("[", "");
				String Value = Arrays.asList(lat_attribute.split("\\=")).get(1);
				Value = Value.replace("]","");
				System.out.println(Field);
				System.out.println(Value);	
			     
				Builder buildMatch = my13Factory.buildMatch();
				Match match = null;
				switch(Field){
				case "EtherType":
					 match = buildMatch.setExact(MatchField.ETH_TYPE, EthType.of(Integer.parseInt(Value))).build();
					break;
				case "EtherSrc":
				      match = buildMatch.setExact(MatchField.ETH_SRC, MacAddress.of(Value)).build();
					break;
				case "EtherDst":
					   match = buildMatch.setExact(MatchField.ETH_DST, MacAddress.of(Value)).build();
					break;
				case  "PortDst":
					  match = buildMatch.setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value))).build();
					break;
				case "PortSrc":
					match = buildMatch.setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value))).build();
					break;
				case  "IpDst":
					 match = buildMatch.setMasked(MatchField.IPV4_DST,IPv4AddressWithMask.of(Value)).build();
					break;
				case "IpSrc":
					 match = buildMatch.setMasked(MatchField.IPV4_SRC,IPv4AddressWithMask.of(Value)).build();
					break;
				case "IngressPort":
					 match = buildMatch.setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value))).build();
					break;
				case "IngressVPort":
					break;
				case "VlanId":
					break;
				
				}
				System.out.println();
				 OFActions actions_subset = my13Factory.actions();
			     OFInstructionGotoTable instructions = my13Factory.instructions().gotoTable(TableId.of(next_table_id));
			     List<OFInstruction> listInstruction =ImmutableList.<OFInstruction> of(
                         instructions);
			     add_flow(id_table,match,listInstruction,new ArrayList<OFAction>());
				 				
			}
			
			
		}

		
	}
	
	

}
