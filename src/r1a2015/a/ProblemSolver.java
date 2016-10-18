package r1a2015.a;

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
	
	private int obsNum;
	private ArrayList<Integer> origObs;
	
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
		int numMethod1; //num of mushrooms eaten according to method no 1.
		int numMethod2; //num of mushrooms eaten according to method no 2.
		
		/* First method
		 * Starting from obs num 2:
		 *   if obs(i) < obs(i-1) then Kaylin has eaten [obs(i) - obs(i-1)] pieces
		 *   in any other case she hasn't eaten any mushrooms
		 */
		numMethod1 = 0;
		int max10secEaten = 0;  //for later use in Method 2
		int prev = origObs.get(0);
		for(int i=1; i<obsNum; i++){
			int act = origObs.get(i);
			if(act < prev){
				numMethod1 += (prev - act);
				if(prev - act > max10secEaten){
					max10secEaten = prev - act;
				}
			}
			//step ahead
			prev = act;
		}
		
		/* Second method
		 * 1. define Kaylin's eating rate per 10sec => must be max{over i} :: [obs(i) - obs(i-1)]
		 *       --> done parallel with method 1
		 * 2. Starting from obs num 1: 
		 *    calculate how many of obs(i) could Kaylin have eaten during her 10 seconds timeframe
		 */
		//find maximal rate
		//   -> done in method 1
		//apply rate
		numMethod2 = 0;
		prev = origObs.get(0);
		for(int i=1; i<obsNum; i++){
			int act = origObs.get(i);
			
			numMethod2 += (max10secEaten > prev ? prev : max10secEaten);
			
			//step ahead
			prev = act;
		}
		
		return numMethod1 + " " + numMethod2;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		obsNum = Integer.parseInt(inCase.getData()[0]);
		origObs = Util.splitStringToInt(inCase.getData()[1], null);
		//System.out.println(obsNum + "  -->  [" + Util.iterableToString(origObs, ",") + "]");
	}

}
