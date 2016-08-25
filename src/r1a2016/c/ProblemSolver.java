package r1a2016.c;

import java.util.ArrayList;

import util.*;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int numKids;
	private ArrayList<Integer> bffRaw;
	
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
		
		/* Raw input suggests "directed" and, perhaps, isolated graph.
		 * 1) Directed edges should be taken as undirected
		 * 2) The longest possible chain should be found within the graph 
		 * The length of the longest chain should be returned
		 * */
		
		return null;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		numKids = Integer.parseInt(inCase.getData()[0]);
		bffRaw = Util.splitStringToInt(inCase.getData()[0], null);
		
	}

}

