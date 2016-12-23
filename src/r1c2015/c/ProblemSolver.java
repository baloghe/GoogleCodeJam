package r1c2015.c;

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
	
	private int C;
	private int D;
	private int intV;
	private long longV;
	
	private ArrayList<Integer> denoms;
	
	/**
	 * constructor. The expected size of the problem might require different approaches, therefore runmode is required as a parameter   
	 * @param inRunMode expected RUNMODE_SMALL or RUNMODE_LARGE (see ProblemSolution)
	 */
	public ProblemSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	
	@Override
	public String solveCase(RawInput inCase) {
		//init(inCase);
		// TODO Auto-generated method stub
		return solveBF(inCase);
	}
	
	public String solveBF(RawInput inCase) {
		init(inCase);
		
		int denomsToAdd = 0;
		//copy original
		//if denominations do not contain 1 we have to add it anyway
		ArrayList<Integer> tmpDenoms = new ArrayList<Integer>();
		boolean found1 = false;
		for(int d : denoms){
			if(d==1) found1=true;
			tmpDenoms.add(d);
		}
		if(!found1){
			denomsToAdd++;
			tmpDenoms.add(1);
		}
		
		//OK... then try all target values starting from 1, all the way up to intV
		//  GREEDY: whenever a target value cannot be paid, we add it as a new denomination
		for(int tv=1; tv<=intV; tv++){
			
			ArrayList<Integer> u = getCombination(tmpDenoms, 1, tv);
			//if no such combination exists, add TV as a new denomination needed
			if(u.size()==0){
				denomsToAdd++;
				tmpDenoms.add(tv);
			}
			
		}//next tv
		/*
		System.out.println("number of denoms added: " + denomsToAdd);
		System.out.println("orig  denoms: " + denoms);
		System.out.println("final denoms: " + tmpDenoms);
		*/
		return Integer.toString(denomsToAdd);
	}
	
	public static ArrayList<Integer> getCombination(ArrayList<Integer> inDenoms, int inMaxUseOne, int inTargetValue){
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		ArrayList<Integer> bigDenom = new ArrayList<Integer>();
		for(int d : inDenoms){
			if(d<=inTargetValue){
				for(int i=0; i<inMaxUseOne; i++){
					bigDenom.add(d);
				}//next i
			}//endif
		}//next d
		
		if(bigDenom.size()==0) return ret;
		
		int[] values = new int[bigDenom.size()];
		for(int i=0; i<values.length; i++){
			values[i] = bigDenom.get(i);
		}
		Knapsack ks = new Knapsack(values, values);
		int sol = ks.solve(inTargetValue);
		if(sol==inTargetValue){
			boolean[] b = ks.getUsed();
			for(int i=0; i<b.length; i++){
				if(b[i]) ret.add(values[i]);
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
		ArrayList<Long> xx = Util.splitStringToLong(inCase.getData()[0], null);
		
		C = (int)xx.get(0).longValue();
		D = (int)xx.get(1).longValue();
		intV = (int)xx.get(2).longValue();
		longV = xx.get(2).longValue();
		
		denoms = Util.splitStringToInt(inCase.getData()[1], null);
		
		System.out.println("init :: C=" + C + ", D=" + D + ", intV=" + intV + ", longV=" + longV + ", denoms=" + Util.iterableToString(denoms, ",") );
	}

}
