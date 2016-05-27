package handler;
import struct.*; 



import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class Switch 
{
	public int dpid;
	public int seuil_tcam;
	public HashMap<Integer, Table> path_processing;

	public Switch()
	{
		this.dpid = 0;
		this.seuil_tcam = 0;
		this.path_processing = new HashMap<Integer,Table>();
	}

	public Switch (int dpid, int seuil_tcam, HashMap<Integer, Table> path_processing)
	{
		this.dpid = dpid;
		this.seuil_tcam = seuil_tcam;
		this.path_processing = path_processing;
		
	}

	public void getConstraint(HashMap<ConstraintType,HashMap<Constraints,Object>> MapC){
		AttributeType AT=null;
		//System.out.println(MapC);
		ArrayList<String> PP = (ArrayList<String>)MapC.get(ConstraintType.RESSOURCES).get(Constraints.path_processing);
		for(int i=0; i<PP.size();i++){
			String var = PP.get(i);
			HashMap<String,HashMap<Constraints, String>> A = (HashMap<String, HashMap<Constraints, String>>) 
		MapC.get(ConstraintType.PERFORMANCE).get(Constraints.table);
			//System.out.println(A.get(var).get(Constraints.matching));
			if (AttributeType.EtherDst.toString().equals(A.get(var).get(Constraints.matching))){
				AT=AttributeType.EtherDst;
				//System.out.println("EtherDst");
			}
			if (AttributeType.EtherSrc.toString().equals(A.get(var).get(Constraints.matching))){
				AT=AttributeType.EtherSrc;
				//System.out.println("EtherSrc");
			}
			if (AttributeType.IngressPort.toString().equals(A.get(var).get(Constraints.matching))){
				AT=AttributeType.IngressPort;
				System.out.println("IngressPort");
			}
			if (AttributeType.IngressVPort.toString().equals(A.get(var).get(Constraints.matching))){
				AT=AttributeType.IngressVPort;
				System.out.println("IngressVPort");
			}
			if (AttributeType.IpSrc.toString().equals(A.get(var).get(Constraints.matching))){
				AT=AttributeType.IpSrc;
				//System.out.println("IpSrc");
			}
			if (AttributeType.IpDst.toString().equals(A.get(var).get(Constraints.matching))){
				AT=AttributeType.IpDst;
				System.out.println("IpDst");
			}
			if (AttributeType.EtherType.toString().equals(A.get(var).get(Constraints.matching))){
				AT=AttributeType.EtherType;
				System.out.println("EtherTyp");
			}
			if (AttributeType.VlanId.toString().equals(A.get(var).get(Constraints.matching))){
				AT=AttributeType.VlanId;
				System.out.println("VlanId");
			}
			if (AttributeType.PortDst.toString().equals(A.get(var).get(Constraints.matching))){
				AT=AttributeType.PortDst;
				System.out.println("PortDst");
			}
			if (AttributeType.PortSrc.toString().equals(A.get(var).get(Constraints.matching))){
				AT=AttributeType.PortSrc;
				//System.out.println("PortSrc");
			}
			Table T = new Table(i, AT);
		
				
			HashMap<String,HashMap<Constraints, String>> B = (HashMap<String, HashMap<Constraints, String>>) 
					MapC.get(ConstraintType.RESSOURCES).get(Constraints.table);
			//System.out.println(B.get(var).get(Constraints.number_entry));
			T.number_entry = Integer.parseInt(B.get(var).get(Constraints.number_entry));
			T.setTableMatchType(T.matchField);
			this.path_processing.put((Integer.parseInt(var)),T );
			//System.out.println(this.path_processing.size());
		}
		for (int i =0; i<this.path_processing.size();i++){
				//System.out.println(this.path_processing.get(i));
			}
		
	}
	@Override
	public String toString()
	{
		String s = "";
		s += "Switch id is :"+dpid+" accepts"+seuil_tcam+" TCAM entries, and support the list of matchfields: ";
		return s;
	}
}