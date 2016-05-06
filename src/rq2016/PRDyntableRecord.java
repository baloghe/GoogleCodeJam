package rq2016;

import java.util.ArrayList;
import java.util.HashSet;

public class PRDyntableRecord {

	private PancakeRevengeStack origin;
	
	private ArrayList<PancakeRevengeStack> descendants;
	
	private int descendantlevel;
	
	public PRDyntableRecord(PancakeRevengeStack inStack, int inDescLev, HashSet<PancakeRevengeStack> inAlreadyVisited){
		origin = inStack;
		descendants = new ArrayList<PancakeRevengeStack>();
		descendantlevel = inDescLev;
		
		for(int i=0; i<origin.size(); i++){
			PancakeRevengeStack s = origin.copy();
			s.flipStackPart(i+1);
			if(inAlreadyVisited != null && !inAlreadyVisited.contains(s)){
				descendants.add(s);
			}
		}
	}
	
	public ArrayList<PancakeRevengeStack> getDescendants(){
		return descendants;
	}
	
	public int getDescendantsLevel(){
		return descendantlevel;
	}
	
	public boolean hasSolution(){
		boolean ret = false;
		
		for(PancakeRevengeStack s : descendants){
			if(s.isOK()) return true;
		}
		
		return ret;
	}
	
	public String toString(){
		String ret = (origin==null ? "NULL" : origin.toString());
		
		ret += " -> ";
		
		ret += (descendants==null ? "NULL" : ("[" + descendants.get(0).toString()) );
		for(int i=1; i<descendants.size(); i++){
			ret += (", " + descendants.get(i).toString() );
		}
		
		ret += "]";
		
		return ret;
	}
	
	public int size(){
		return descendants.size();
	}
}