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
import org.projectfloodlight.openflow.protocol.OFFlowDelete;
import org.projectfloodlight.openflow.protocol.OFFlowModify;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFVersion;
import org.projectfloodlight.openflow.protocol.action.OFAction;
import org.projectfloodlight.openflow.protocol.action.OFActions;
import org.projectfloodlight.openflow.protocol.instruction.OFInstruction;
import org.projectfloodlight.openflow.protocol.instruction.OFInstructionApplyActions;
import org.projectfloodlight.openflow.protocol.instruction.OFInstructionGotoTable;
import org.projectfloodlight.openflow.protocol.instruction.OFInstructionWriteMetadata;
import org.projectfloodlight.openflow.protocol.instruction.OFInstructions;
import org.projectfloodlight.openflow.protocol.match.Match;
import org.projectfloodlight.openflow.protocol.match.Match.Builder;
import org.projectfloodlight.openflow.types.EthType;
import org.projectfloodlight.openflow.types.IPAddressWithMask;
import org.projectfloodlight.openflow.types.IPv4Address;
import org.projectfloodlight.openflow.types.IPv4AddressWithMask;
import org.projectfloodlight.openflow.types.MacAddress;
import org.projectfloodlight.openflow.types.Masked;
import org.projectfloodlight.openflow.types.OFBufferId;
import org.projectfloodlight.openflow.types.OFMetadata;
import org.projectfloodlight.openflow.types.TableId;
import org.projectfloodlight.openflow.types.U64;
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
    public void delete_all_flows(int table_id) throws IOException{
    	List<OFInstruction> myInstructionLists = new ArrayList<OFInstruction>();
    	Match myMatch = my13Factory.buildMatch().wildcard(MatchField.ETH_DST).wildcard(MatchField.IN_PORT).build();
    	OFFlowDelete flowdelete = my13Factory.buildFlowDelete()
    			     .setBufferId(OFBufferId.NO_BUFFER)
    			     .setMatch(myMatch)
    			     .setInstructions(myInstructionLists)
    			     .setTableId(TableId.of(table_id))
    			     .setPriority(1)
    			     .build();
    	   OutputStream outToClient = OpenFlow_Handler.thread_sockets.outToClient;
           byte[] bytes = new byte[4096];
           ByteBuf buffer = buffer() ;
           flowdelete.writeTo(buffer);    
           bytes = buffer.array();
           //system.out.println(bytes);
           //system.out.println(bytes.length);
           outToClient.write(bytes,0,buffer.readableBytes());
    	
    }
    public void add_flow (int table_id ,Match myMatch ,List<OFInstruction> myInstructionLists,List<OFAction> myActionList,int priority) throws IOException{
    	final Object lock = new Object();
        synchronized(lock){
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
                 .setPriority(priority)
               .build();
        
       
        byte[] bytes = new byte[4096];
        ByteBuf buffer = buffer() ;
        flowAdd.writeTo(buffer);    
        bytes = buffer.array();
        //system.out.println(bytes);
        //system.out.println(bytes.length);
        OpenFlow_Handler.thread_sockets.replyToSwitch(bytes, 0, buffer.readableBytes());
        }
    }
    public void deliver_pipeline_to_switch() throws IOException{
        Map<Integer,Table> MapTable = this.app.getAppTable();
        Map<Integer,SubsetTable> MapSubTab = this.app.getSubSetTable();
        int[][] tableChaining= this.app.TableChaining;
        OFFactory my13Factory = OFFactories.getFactory(OFVersion.OF_13);
        int rulesSize=0;
        ////system.out.println(MapTable);
        ////system.out.println(MapSubTab);
        
         /*
          * Delete all flows in table 0
          */
        
         delete_all_flows(0);
        
        for(int i=0 ;i<MapSubTab.size();i++){
            SubsetTable subset_table = MapSubTab.get(i);
            ArrayList<Rule> rules =  subset_table.rules;
            System.out.println(rules);
            int id_subset_table = subset_table.id;
            /**
             * Add subset table to switch
             */
            if(id_subset_table!=0){
             OFActions actions = my13Factory.actions();
            OFAction output = actions.buildOutput()
                        .setPort(OFPort.CONTROLLER)
                        .build();
            Match myMatch = my13Factory.buildMatch().build();
            ArrayList<OFAction> listActions = new ArrayList<OFAction>();
            listActions.add((OFAction)output);
            add_flow(id_subset_table,myMatch,new ArrayList<OFInstruction>(),listActions,0);
            }
            //Install rules
            rulesSize+=rules.size();
            System.out.println("===============Rules Size================ : "+rulesSize);
            for(Rule rule : rules){
                Map<Integer,Lat_Attribute> matchfields =  rule.matchfields;
                ////system.out.println(matchfields);
                 String Actions = rule.action;
                 Builder buildMatch = my13Factory.buildMatch();
                 Match match;
                 String rule_name = rule.name.trim();
                for(Lat_Attribute matchfield :matchfields.values()){
                	System.out.println("Match : "+matchfield);
                    String Field = matchfield.type.toString().trim();
                    String Value = matchfield.value.toLowerCase().trim();
                    //system.out.println(Value);
                    if (Value != "*"){
                    switch(Field){
                    case "eth_type":
                          buildMatch.setExact(MatchField.ETH_TYPE, EthType.of(Integer.parseInt(Value)));
                        break;
                    case "eth_src":
                          buildMatch.setExact(MatchField.ETH_SRC, MacAddress.of(Value));
                        break;
                    case "eth_dst":
                           buildMatch.setExact(MatchField.ETH_DST, MacAddress.of(Value));
                        break;
                    case  "udp_src":
                          buildMatch.setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value)));
                        break;
                    case "udp_dst":
                         buildMatch.setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value)));
                        break;
                    case  "ipv4_dst":
                          System.out.println("sub table ip DST");
                          IPv4AddressWithMask ip_dst = IPv4AddressWithMask.of(Value);
                          buildMatch.setExact(MatchField.ETH_TYPE,EthType.IPv4).setMasked(MatchField.IPV4_DST,ip_dst);
                          System.out.println(buildMatch);
                        break;
                    case "ipv4_src":
                        IPv4AddressWithMask ip_src = IPv4AddressWithMask.of(Value);
                        buildMatch.setExact(MatchField.ETH_TYPE,EthType.IPv4).setMasked(MatchField.IPV4_SRC,ip_src).build();
                        break;
                    case "in_port":
                         buildMatch.setExact(MatchField.ETH_TYPE,EthType.IPv4).setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value)));
                        break;
                    case "IngressVPort":
                        break;
                    case "VlanId":
                        break;
                    
                    }
                    }
                }
                //get actions 
              
                
               System.out.println("##########################MATCH#############################");
               System.out.println(rule_name);
                 match = buildMatch.build();
                System.out.println(match);
                System.out.println("HASH MAP ACTIONS : "+OpenFlow_Handler.thread_sockets.RuleAction);
                List<OFAction> list_Actions = OpenFlow_Handler.thread_sockets.RuleAction.get(rule_name);
                int priority;
                System.out.println(match);
                if(match.toString().equals("OFMatchV3Ver13()"))
                	priority=0;
                else
                	priority=1;
                add_flow(id_subset_table,match,new ArrayList<OFInstruction>(),list_Actions,priority);

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
              *  */
             if(id_table!=0){
             OFActions actions = my13Factory.actions();
            OFAction output = actions.buildOutput()
                        .setPort(OFPort.CONTROLLER)
                        .build();
            Match myMatch = my13Factory.buildMatch().build();
            ArrayList<OFAction> listActions = new ArrayList<OFAction>();
            listActions.add((OFAction)output);
           add_flow(id_table,myMatch,new ArrayList<OFInstruction>(),listActions,0);
            }
            
            for (int k=0 ; k< entries.size();k++){
                Entry  entry= entries.get(k);
                String lat_attribute = entry.matchfield.toString(); 
                String action = entry.action;
                int next_table_id = entry.nextTableId;
                //system.out.println(action);
                //system.out.println(next_table_id);
                // parse matchfield and create Ofmatch
                String Field = Arrays.asList(lat_attribute.split("\\=")).get(0);
                Field = Field.replace("[", "").trim();
                String Value = Arrays.asList(lat_attribute.split("\\=")).get(1);
                Value = Value.replace("]","").trim();
                //system.out.println(Field);
                //system.out.println(Value);    
                 
                Builder buildMatch = my13Factory.buildMatch();
                Match match = null;
                switch(Field){
                case "eth_type":
                     match = buildMatch.setExact(MatchField.ETH_TYPE, EthType.of(Integer.parseInt(Value))).build();
                    break;
                case "eth_src":
                      match = buildMatch.setExact(MatchField.ETH_SRC, MacAddress.of(Value)).build();
                    break;
                case "eth_dst":
                       match = buildMatch.setExact(MatchField.ETH_DST, MacAddress.of(Value)).build();
                    break;
                case  "udp_src":
                      match = buildMatch.setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value))).build();
                    break;
                case "udp_dst":
                    match = buildMatch.setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value))).build();
                    break;
                case  "ipv4_dst":
                    System.out.println("ip DST");
                     IPv4AddressWithMask ip_dst = IPv4AddressWithMask.of(Value);
                     match=buildMatch.setExact(MatchField.ETH_TYPE,EthType.IPv4).setMasked(MatchField.IPV4_DST,ip_dst).build();
                     System.out.println(match);
                    break;
                case "ipv4_src":
                    System.out.println("ip src");
                    IPv4AddressWithMask ip_src = IPv4AddressWithMask.of(Value);
                     match=buildMatch.setExact(MatchField.ETH_TYPE,EthType.IPv4).setMasked(MatchField.IPV4_SRC,ip_src).build();
                    break;
                case "in_port":
                     match = buildMatch.setExact(MatchField.ETH_TYPE,EthType.IPv4).setExact(MatchField.IN_PORT, OFPort.of(Integer.parseInt(Value))).build();
                    break;
                case "IngressVPort":
                    break;
                case "VlanId":
                    break;
                
                }
                //system.out.println();
                // set metadata match , setInstructionsWriteMetadata
                
                int in_metadata = entry.in_metadata;
                
                int out_metadata = entry.out_metadata;
                match.createBuilder().setMasked(MatchField.METADATA,OFMetadata.of(U64.of(in_metadata)), OFMetadata.NO_MASK);
                 OFActions actions_subset = my13Factory.actions();
                 OFInstruction instructions = my13Factory.instructions().gotoTable(TableId.of(next_table_id));
                 OFInstructionWriteMetadata instruction_metadata = my13Factory.instructions().writeMetadata(U64.of(out_metadata),U64.ZERO);
                 ArrayList<OFInstruction> listInstruction  = new ArrayList<OFInstruction>();
   
                 listInstruction.add(instruction_metadata);
                 listInstruction.add(instructions);
                 add_flow(id_table,match,listInstruction,new ArrayList<OFAction>(),1);
                                 
            }
            
            
        }

        
    }
    
    

}