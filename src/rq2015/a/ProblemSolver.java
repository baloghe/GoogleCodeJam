package rq2015.a;

import util.RawInput;
import util.Util;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int maxShyness;
	private int[] origAudience;
	
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
		// TODO Auto-generated method stub
		
		int friendNeeded=0;
		int[] alreadyStanding = new int[maxShyness+1];
		
		//iterate through the shyness levels
		for(int i=0; i<(maxShyness+1); i++){
			//update how many people are already standing
			if(i==0){
				alreadyStanding[0] = 0;
			} else {
				alreadyStanding[i] = alreadyStanding[i-1] + origAudience[i-1];
			}
			
			//whenever a standing ovation cannot be guaranteed, we push the necessary amount of friends with shynessLevel(i-1) in
			if(   i>0
			   && (alreadyStanding[i] + friendNeeded) < i 
			   && (origAudience[i] > 0) ){
				friendNeeded += ( i - alreadyStanding[i] - friendNeeded );
				//System.out.println("   new friends needed=" + ( i - alreadyStanding[i] ) );
			}
			//System.out.println("i=" + i + ", alreadyStanding[i]=" + alreadyStanding[i] + ", friendNeeded=" + friendNeeded);
		}//next i
		
		return Integer.toString(friendNeeded);
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		//ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		String[] sa = inCase.getData()[0].split(" ");
		maxShyness = Integer.parseInt(sa[0]);
		origAudience = new int[maxShyness+1];
		for(int i=0; i<maxShyness+1; i++){
			origAudience[i] = Integer.parseInt(sa[1].substring(i, i+1));
		}
		//System.out.println("ProblemSolver :: maxShyness=" + maxShyness + ", origAudience=" + Util.intArrayToString(origAudience, ","));
	}

}
