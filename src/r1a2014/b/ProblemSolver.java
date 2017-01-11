package r1a2014.b;

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
	
	int N;
	int[][] rawConnections;
	
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
		return null;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		N = Integer.parseInt(inCase.getData()[0]);
		rawConnections = new int[N-1][2];
		for(int i=1; i<N; i++){
			ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[i], null);
			rawConnections[i-1][0] = xx.get(0);
			rawConnections[i-1][1] = xx.get(1);
		}
		System.out.println("init :: N=" + N + ", " + (N-1) + "th edge: " + rawConnections[N-2][0] + " <-> " + rawConnections[N-2][1]);
	}

}
