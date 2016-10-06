package rq2015.b;

import java.util.ArrayList;
import java.util.HashSet;

import util.RawInput;
import util.Util;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int numOrigEaters;
	private ArrayList<Integer> eaters;
	private int MIN_RemainingTime;
	
	/**
	 * constructor. The expected size of the problem might require different approaches, therefore runmode is required as a parameter   
	 * @param inRunMode expected RUNMODE_SMALL or RUNMODE_LARGE (see ProblemSolution)
	 */
	public ProblemSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	
	@Override
	public String solveCase(RawInput inCase) {
		init(inCase);
		
		//Startup setting == doNothing
		MIN_RemainingTime = getMaxNumber( eaters );
		
		/* A list of to-be-inspected setups (=array on non-empty plates + accumulated waiting times) are maintained
		 * at each minute:
		 *   - a setup is taken out for inspection
		 *   - if its greatest number MAX_0 of pancakes is less than the actual MIN_RemainingTime, then MIN_RemainingTime := MAX_0
		 *   - anyway, try all possible splits on it:
		 *            MAX_0 >=6 ? split into 2 - costing 1 minute waiting time
		 *                        MAX_0 >=9 ? split into 3 - costing 2 minutes waiting time
		 *                                    etc.  -- floor(MAX_0 / 3) being the utmost split to be tried, costing floor(MAX_0 / 3)-1 minutes waiting time
		 *   - each split yields a new setup that 
		 *         EITHER should be added to the queue for further inspection if at least one of its plates has more than 3 pankaces on it
		 *         OR discarded with agreeing that it would need ( AccumulatedWaitingTime + max(Array) ) to be eaten
		 * */
		
		PancakeSetup origPS = new PancakeSetup( eaters , 0 );
		HashSet<PancakeSetup> toBeInspected = new HashSet<PancakeSetup>();
		toBeInspected.add(origPS);
		
		int cnt = 0;
		while(!toBeInspected.isEmpty()){
			
			//System.out.println("cnt=" + cnt + ", toBeInspected.size=" + toBeInspected.size());
			
			HashSet<PancakeSetup> toBeRemoved = new HashSet<PancakeSetup>();
			HashSet<PancakeSetup> toBeAdded = new HashSet<PancakeSetup>();
			//iterate through all elements of the actual inspection set
			for( PancakeSetup ps : toBeInspected ){
				
				//inspect the actual element
				int actRemT = ps.getWaitingTime() + ps.getMaxNumber();
				update_MIN_RemainingTime( actRemT );
				
				//perform potential splits on the actual element
				HashSet<PancakeSetup> potentialSplits = ps.split();
				//inspect each offspring
				for( PancakeSetup potPS : potentialSplits ){
					int potMAX_0 = potPS.getMaxNumber();
					if( potMAX_0 <= 3 ){ //don't add them back just evaluate
						int potActRemT = potPS.getWaitingTime() + potMAX_0;
						update_MIN_RemainingTime( potActRemT );
						toBeRemoved.add(potPS);
					} else {
						toBeAdded.add(potPS);
					}
				}//next potentiel offspring
				
				//inspected element should be removed from inspection list
				toBeRemoved.add(ps);
				
			}//next ps in actual inspection set
			
			//add elements gained in current round
			toBeInspected.addAll(toBeAdded);
			//remove elements that are not needed anymore
			toBeInspected.removeAll(toBeRemoved);
		}//wend
		
		//return global minimum
		return Integer.toString(MIN_RemainingTime);
	}
	
	private void update_MIN_RemainingTime( int inRemT ){
		if(MIN_RemainingTime > inRemT){
			MIN_RemainingTime = inRemT;
		}
	}
	
	public String not_working_but_good_idea(RawInput inCase) {
		init(inCase);
		/* Idea: 
		 * - it makes no sense to put pancakes on a nonempty plate
		 * - it makes no sense to lift less than half of the pancakes
		 * - it makes no sense to deal with a plate with 3 pancakes on: halving would result 1+2 pancakes + 1 minute extra time == 3 minutes altogether!
		 * Therefore the waiter has two potential outcome to ponder:
		 * 	1) to take half of the pancakes from the MAX() diner's plate (=:MAX_0) and put it on an empty plate 
		 *        => waitTime++ and the remainingTime = max{ ceil(MAX_0/2) ; MAX_1 } + waitTime
		 *             ---> here MAX_0 is the maximal number of pancakes on a diner's plate (BEFORE the operation) 
		 *                       MAX_1 is the second greatest number of pancakes on a diner's plate (BEFORE the operation)
		 *  2) OR to leave everything as is => waitTime does not change and remainingTime = MAX_0
		 *  The waiter must choose whichever of these two is lower.
		 *  The algorithm stops when the waiter opts for 2) (in that case, the waiter has nothing more to do, except for collecting the empty plates in the end...)
		 * */
		
		int choice=0;
		int remainingTime = -1;
		int waitTime = 0;
		int cnt = 0;
		
		//Startup setting == doNothing
		int MIN_RemainingTime = getMaxNumber( eaters );
		
		//See if any better solution might turn out
		while(choice < 2){
			//remaining time for 1
			int remainingTime1 = getRemainingTimeWithTakeOff( eaters );
			//remaining time for 2
			int remainingTime2 = getMaxNumber( eaters );
			
			//System.out.println("cnt=" + cnt + "  before: [" + Util.iterableToString(eaters, ",") + "]  -> rTm1=" + remainingTime1 + ", rTm2=" + remainingTime2 + ", waitTm=" + waitTime);
			
			if(   /*remainingTime2 >= (remainingTime1 + waitTime)
					   &&*/ remainingTime2 >= 9 ){ //we opt for split in 3 the biggest heap of pankaces (=+2 minutes waiting)
						choice = 1;
						updateEaters( eaters ,3 );
						waitTime+=2;
						remainingTime = waitTime + getMaxNumber( eaters );
			} else if(   /*remainingTime2 >= (remainingTime1 + waitTime)
			   &&*/ remainingTime2 > 3 ){ //we opt for halving the biggest heap of pankaces
				choice = 1;
				updateEaters( eaters ,2 );
				waitTime++;
				remainingTime = waitTime + getMaxNumber( eaters );
			} else {
				choice = 2;
				remainingTime = waitTime + remainingTime2;
			}
			
			if(MIN_RemainingTime > remainingTime){
				MIN_RemainingTime = remainingTime;
			}
			
			//System.out.println("  after: [" + Util.iterableToString(eaters, ",") + "]  -> choice=" + choice + ", remainingTime=" + remainingTime + ", waitTm=" + waitTime);
		}
		
		return Integer.toString( MIN_RemainingTime );
	}
	
	public void updateEaters(ArrayList<Integer> inEaters, int inNumPieces){
		int MAX_0 = Integer.MIN_VALUE;
		int POS_0 = -1;
		
		for(int idx=0; idx < inEaters.size(); idx++){
			int i = inEaters.get(idx);
			//Max_0 == simple max
			if( i > MAX_0 ){
				MAX_0 = i;
				POS_0 = idx;
			} 
		}
		/*
		int new_MAX_0;
		int new_plate;
		if( (MAX_0 % 2)==0 ){
			new_MAX_0 = MAX_0 / 2;
			new_plate = new_MAX_0;
		} else {
			new_MAX_0 = MAX_0 / 2;
			new_plate = new_MAX_0 + 1;
		}
		*/
		int[] newPieces = new int[inNumPieces];
		int new_MAX_0 = MAX_0 / inNumPieces; //naturally floor(.)
		newPieces[0] = new_MAX_0;
		int splitAlready = new_MAX_0;
		int rest = MAX_0 - splitAlready;
		for(int i=1; i<inNumPieces-1; i++){
			newPieces[i] = new_MAX_0;
			splitAlready += splitAlready;
			rest -= new_MAX_0;
		}
		//add the rest
		newPieces[inNumPieces-1] = rest;
		
		//update former MAX_0
		inEaters.set(POS_0, new Integer(newPieces[0]) );
		//add pankaces to empty plates
		for(int i=1; i<inNumPieces; i++){
			inEaters.add(new Integer(newPieces[i]));
		}
	}
	
	public int getRemainingTimeWithTakeOff(ArrayList<Integer> inEaters){
		int MAX_0 = Integer.MIN_VALUE;
		int MAX_1 = Integer.MIN_VALUE;
		
		for(int i : inEaters){
			//Max_0 == simple max
			if( i > MAX_0 ){
				MAX_0 = i;
			} else if( i > MAX_1 ) { //Max_1 == second max
				MAX_1 = i;
			}
		}
		
		int ret = MAX_1;
		if( (MAX_0 % 2)==0 ){
			ret = MAX_0 / 2;
		} else {
			ret = MAX_0 / 2 + 1;
		}
		
		return ret > MAX_1 ? ret : MAX_1;
	}
	
	public int getMaxNumber(ArrayList<Integer> inEaters){
		int ret = Integer.MIN_VALUE;
		for(int i : inEaters){
			if(i > ret){
				ret = i;
			}
		}
		return ret;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		//ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		numOrigEaters = Integer.parseInt(inCase.getData()[0]);
		eaters = Util.splitStringToInt(inCase.getData()[1], null);
		
		//System.out.println("Raw Input: numOrigEaters=" + numOrigEaters + ", eaters=[" + Util.iterableToString(eaters, ",") + "]");
	}

}
