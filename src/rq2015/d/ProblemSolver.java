package rq2015.d;

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
	
	int origX;
	int origR;
	int origC;
	
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
		
		return this.solveIt() ? "GABRIEL" : "RICHARD" ;
	}
	
	private boolean solveIt(){
		/* trivial checks: 
		 *    if X is not a divisor of (R*C) then Gabriel has no chances
		 *    if X can be split up to MxN so that min{M,N} > min{R,C} then Gabriel has no chance at all
		 *    for X>=7 Richard can always choose an X-omino containing a hole. Bad luck again for Gabriel
		 */
		if( ( (origR * origC) % origX ) != 0 ) return false;
		if( origX >= 7 ) return false;
		
		int minRC = origR < origC ? origR : origC;
		int maxRC = origR < origC ? origC : origR;
		
		/* advanced checks
		 *  
		 * */
		if(origX==3 && minRC <= 1) return false;
		if(origX==4 && minRC <= 2) return false;
		if(origX==5 && (minRC <= 2  || (minRC==3 && maxRC==5) ) ) return false;
		if(origX==6 && minRC <= 3) return false;
		
		return true;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		origX = xx.get(0);
		origR = xx.get(1);
		origC = xx.get(2);
		//System.out.println("raw: X=" + origX + ", R=" + origR + ", C=" + origC);
	}

}
