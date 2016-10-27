package r1a2015.b;

import java.util.ArrayList;

import util.RawInput;
import util.Util;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int numBarber;
	private int placeInLine;
	private ArrayList<Integer> cutTimes;
	
	/**
	 * constructor. The expected size of the problem might require different approaches, therefore runmode is required as a parameter   
	 * @param inRunMode expected RUNMODE_SMALL or RUNMODE_LARGE (see ProblemSolution)
	 */
	public ProblemSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	
	@Override
	public String solveCase(RawInput inCase){
		return solveCaseBinarySearch( inCase );
		//return solveCaseLarge( inCase );
		//return solveCaseBF( inCase );
	}
	
	public String solveCaseBF(RawInput inCase){
		init(inCase);
		
		if(placeInLine <= numBarber){
			System.out.println("Shortcut: placeInLine=" + placeInLine + " <= numBarber=" + numBarber);
			return Integer.toString(placeInLine);
		}
		
		// Do the waiting...
		int goesToBarber = -1;
		int[] remTime = new int[numBarber];
		int actWaitTime = -1;
		//upload remTime with the first B customers
		for(int i=0; i<numBarber && placeInLine > 0; i++){
			remTime[i] = cutTimes.get(i);
			goesToBarber = i;
			placeInLine--;
		}
		//System.out.println("numBarber=" + numBarber + ", after fillup: placeInLine=" + placeInLine);
		
		//in each round
		//  1. wait minimum time
		//  2. fill empty places from queue
		while(placeInLine > 0){
			actWaitTime = Integer.MAX_VALUE;
			int barberMinTime = -1;
			//find min{remTime}
			for(int i=0; i<numBarber; i++){
				if(actWaitTime > remTime[i]){
					actWaitTime = remTime[i];
				}
			}
			//System.out.println("New Round :: barber with MIN remaining time=" + barberMinTime + " -> remTime=" + Util.intArrayToString(remTime, ","));
			
			//decrease remTime 
			//  + fill in emtpy place from queue
			//  + keep track of last Barber used
			for(int i=0; i<numBarber && placeInLine > 0; i++){
				remTime[i] -= actWaitTime;
				if( remTime[i]==0 ){
					remTime[i] = cutTimes.get(i);
					goesToBarber = i;
					placeInLine--;
					//System.out.println("  FILLUP: goesToBarber=" + goesToBarber + " -> placeInLine=" + placeInLine );
				}
			}
			//
		}
		
		
		return Integer.toString(goesToBarber + 1);
	}
	
	public String solveCaseBinarySearch(RawInput inCase) {
		init(inCase);
		
		int ret = -1;
		
		//get max(cutTimes)
		int maxCutTime = Integer.MIN_VALUE;
		for(Integer i : cutTimes){
			if(i > maxCutTime){
				maxCutTime = i;
			}
		}
		
		//binary search for the time we are served
		long thlow = -1;
		long thup = ( (long)placeInLine ) * ( (long)maxCutTime );
		//System.out.println("BEFORE:: thlow=" + thlow + ", thup=" + thup + ", placeInLine=" + placeInLine);
		long actTime = (thup - thlow) / 2;
		long serveTime = -1;
		while( thlow +1 < thup ){  //cycle runs until the thresholds are unequal
			long served=getServedUpToT(actTime);
			
			if(served >= placeInLine ){
				thup = actTime;
				//System.out.println("       served > placeInLine");
			} else {
				thlow = actTime;
				//System.out.println("       else");
			}
			
			//step ahead
			actTime = thlow + (thup - thlow) / 2;
			//System.out.println("    thlow=" + thlow + ", actTime=" + actTime + ", thup=" + thup + ", served=" + served + ", placeInLine=" + placeInLine);
		}
		serveTime = thup;
		
		//number of customers served before:
		long servedBefore = getServedUpToT(serveTime - 1);
		long custsToBeServedBeforeMe = placeInLine - servedBefore;
		
		//System.out.println("binSearch :: maxCutTime=" + maxCutTime + ", serveTime=" + serveTime + ", servedBefore=" + servedBefore + ", custsToBeServedBeforeMe=" + custsToBeServedBeforeMe);
		
		for(int i=0; i<numBarber; i++){
			if( serveTime % ( (long)cutTimes.get(i) ) == 0 ){
				custsToBeServedBeforeMe--;
			}
			if( custsToBeServedBeforeMe==0 ){
				ret = i+1;
				break;
			}
		}
		
		return Integer.toString(ret);
	}
	
	private long getServedUpToT(long inTime){
		if(inTime < 0) return 0;
		long ret;
		ret = 0;
		for(int i=0; i<numBarber; i++){
			ret += (inTime / ( (long)cutTimes.get(i) ) + 1);
		}
		return ret;
	}
	
	public String solveCaseLarge(RawInput inCase) {
		init(inCase);
		
		/* shortcut: when a barber is available in the first round, only our position in queue counts
		 */
		if(placeInLine <= numBarber){
			//System.out.println("Shortcut: placeInLine=" + placeInLine + " <= numBarber=" + numBarber);
			return Integer.toString(placeInLine);
		}
		/* otherwise we have to wait...
		 * we keep track of actual remaining times at each barber in remTime[B]
		 * 	at beginning it is [0,...,0]
		 * then we take the first customer from the queue (== our place in queue diminishes by 1) and put it in seat no 1
		 *  ... we repeat it (B-1) times for the first B customers to take their seats => remTime=[C_1,...,C_B]
		 * then we wait min{remTime} minutes until at least one barber has finished
		 *    -> it could happen that [1..B] barbers finish at the same time
		 *    the empty seats will be filled with the next customer in line
		 *    	->  (== our place in queue diminishes by 1)
		 *    		-> if our place diminishes to 0 then the number of the barber is the result
		 */
		
		/* Potential shortening of calculation time: within SCM := Smallest_Common_Multiple{C_1, ..., C_B} minutes all chairs will become empty at the same time
		 *  ==> calculate how many clients will be done witnin SCM minutes =: numClient_in_SCM
		 *  substract numClient_in_SCM from placeInLine as possible
		 *  
		 *  IF: placeInLine % numClient_in_SCM == 0 then we will receive the last Barber
		 *  ELSE: placeInLine(new) := placeInLine(orig) % numClient_in_SCM
		 */
		//calc SCM
		long[] tmparr = new long[numBarber];
		for(int i=0; i<numBarber; i++){
			tmparr[i] = cutTimes.get(i);
		}
		int SCM = (int)( Util.lcm(tmparr) );
		//System.out.println("SCM=" + SCM);
		
		//calc numClient_in_SCM
		int numClient_in_SCM = 0;
		for(int i=0; i<numBarber; i++){
			numClient_in_SCM += (SCM / cutTimes.get(i));
			//System.out.println("  i=" + i + " -> " + ( SCM / cutTimes.get(i) ));
		}
		//System.out.println("numClient_in_SCM=" + numClient_in_SCM);
		
		//take modulo and EITHER shortcut2 OR decrease queue to (modulo + numClient_in_SCM)
		int new_placeInLine = placeInLine / numClient_in_SCM;
		new_placeInLine = placeInLine - (new_placeInLine * numClient_in_SCM);
		//System.out.println("modulo=" + new_placeInLine);
		if(new_placeInLine == 0){
			placeInLine = numClient_in_SCM;
		} else {
			placeInLine = new_placeInLine;
		}
		
		// Do the waiting...
		int goesToBarber = -1;
		int[] remTime = new int[numBarber];
		int actWaitTime = -1;
		//upload remTime with the first B customers
		for(int i=0; i<numBarber && placeInLine > 0; i++){
			remTime[i] = cutTimes.get(i);
			goesToBarber = i;
			placeInLine--;
		}
		//System.out.println("numBarber=" + numBarber + ", after fillup: placeInLine=" + placeInLine);
		
		//in each round
		//  1. wait minimum time
		//  2. fill empty places from queue
		while(placeInLine > 0){
			actWaitTime = Integer.MAX_VALUE;
			int barberMinTime = -1;
			//find min{remTime}
			for(int i=0; i<numBarber; i++){
				if(actWaitTime > remTime[i]){
					actWaitTime = remTime[i];
				}
			}
			//System.out.println("New Round :: barber with MIN remaining time=" + barberMinTime + " -> remTime=" + Util.intArrayToString(remTime, ","));
			
			//decrease remTime 
			//  + fill in emtpy place from queue
			//  + keep track of last Barber used
			for(int i=0; i<numBarber && placeInLine > 0; i++){
				remTime[i] -= actWaitTime;
				if( remTime[i]==0 ){
					remTime[i] = cutTimes.get(i);
					goesToBarber = i;
					placeInLine--;
					//System.out.println("  FILLUP: goesToBarber=" + goesToBarber + " -> placeInLine=" + placeInLine );
				}
			}
			//
		}
		
		
		return Integer.toString(goesToBarber + 1);
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		numBarber = xx.get(0);
		placeInLine = xx.get(1);
		cutTimes = Util.splitStringToInt(inCase.getData()[1], null);
		
		//System.out.println(numBarber + ", " + placeInLine + "  -->  [" + Util.iterableToString(cutTimes, ",") + "]");
	}

}
