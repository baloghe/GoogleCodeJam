package rq2016;

import java.util.ArrayList;
import java.util.Vector;
import java.util.HashSet;
import java.util.Collections;

import util.RawInput;
import util.Util;

public class PancakeRevengeSolver implements util.CaseSolver {
	
	private PancakeRevengeStack origin;
	
	private static int countcap=100;
	
	private HashSet<PRDyntableRecord> actSet;
	private HashSet<PRDyntableRecord> childrenSet;
	
	private HashSet<PancakeRevengeStack> alreadyReachedSet;
	
	public PancakeRevengeSolver(){
		
	}
	
	//Strictly works on ZIPPED input!!
	public String solveCase(RawInput inCase){
		this.init(inCase);
		
		//if last ZIPPED element is '-' :: len(ZIPPED input)
		//otherwise: len(ZIPPED input)-1
		String zippedStack=origin.toString();
		String lastChar=zippedStack.substring(zippedStack.length() - 1);
		//System.out.println("lastChar=" + lastChar);
		if(lastChar.equalsIgnoreCase("-"))
			return "" + zippedStack.length();
		else return "" + (zippedStack.length() - 1);
	}
	
	public String solveCaseBF(RawInput inCase){
		String ret = "";
		
		//System.out.println("solveCase :: new case=" + inCase.toString());
		
		//init local vars based on case
		this.init(inCase);
		
		//check if the stack is already a solution
		if(origin.isOK())
			return "0";
		
		actSet = new HashSet<PRDyntableRecord>();
		childrenSet = new HashSet<PRDyntableRecord>();
		
		alreadyReachedSet = new HashSet<PancakeRevengeStack>();		
		PRDyntableRecord rec = new PRDyntableRecord(origin, 1, alreadyReachedSet);
		
		//check if any of the first flips provided a solution
		if(rec.hasSolution())
			return "1";
		
		//If no solution found in the 0. and 1st step...
		//Add the original stack to the set
		//At any point in time:
		//	remove a stack from the set
		//	calculate all potential flip results 
		//	add these beck to the set
		//UNTIL a result [++...+] is found somewhere
		actSet.add(rec);
		int roundcnt = 0;
		boolean found=false;
		int foundFlipNumber = -1;
		while(roundcnt <= countcap && !found){
			//take out actual records from actSet one-by-one and put their children into childrenSet
			childrenSet = new HashSet<PRDyntableRecord>();
			for(PRDyntableRecord p : actSet){
				
				int actLevel = p.getDescendantsLevel();
				for(PancakeRevengeStack c : p.getDescendants()){
					alreadyReachedSet.add(c);
					PRDyntableRecord nr = new PRDyntableRecord(c, actLevel+1, alreadyReachedSet);
					//immediately check if a solution is found
					if(nr.hasSolution()){
						found = true;
						foundFlipNumber = nr.getDescendantsLevel();
						break;
					}
					
					//if not...
					if(nr.size() > 0){
						childrenSet.add(nr);
					}
				}
				if(found) break;
			}
			if(found) break;
			//swap sets
			HashSet<PRDyntableRecord> temp = childrenSet;
			childrenSet = actSet;
			actSet = temp;
			//System.out.println("round " + roundcnt + ": actSet.size=" + actSet.size() + " , childrenSet.size=" + childrenSet.size() + " , alreadyReachedSet.size=" + alreadyReachedSet.size());
			
			//increase counter
			roundcnt++;
		}//wend
		
		ret = Integer.toString(foundFlipNumber);		
		return ret;
	}
	
	private void init(RawInput inCase){
		
		//store raw data
		
		//transform as needed
		origin = new PancakeRevengeStack( Util.zipString(inCase.getData()[0]) );
	}
	
}