package rq2014.a;

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
	
	private int[][] firstArr;
	private int firstAns;
	private int[][] secondArr;
	private int secondAns;
	
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
		/* F := set of numbers in the {firstAns}th row of the first arrangement
		 * S := set of numbers in the {secondAns}th row of the second arrangement
		 * 
		 * F intersect S == 1 number => that is the solution
		 *                  0 number => Volunteer cheated!
		 *                  >1 number => Bad magician!
		 * */
		HashSet<Integer> F = new HashSet<Integer>();
		for(int i=0; i<4; i++){
			F.add(firstArr[firstAns-1][i]);
		}
		int cntFound = 0;
		int foundNum = -1;
		for(int i=0; i<4; i++){
			int cand = new Integer(secondArr[secondAns-1][i]);
			if(F.contains(cand)){
				cntFound++;
				foundNum = cand;
			}
		}
		String ret = "";
		if(cntFound==1){
			ret = Integer.toString(foundNum);
		} else if(cntFound > 1){
			ret = "Bad magician!";
		} else {
			ret = "Volunteer cheated!";
		}
		return ret;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		firstAns = Integer.parseInt(inCase.getData()[0]);
		firstArr = new int[4][4];
		for(int i=0; i<4; i++){
			//System.out.println("  i1=" + i + " -> " + inCase.getData()[i+1]);
			ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[i+1], null);
			for(int j=0; j<xx.size(); j++){
				firstArr[i][j] = xx.get(j);
			}
		}
		secondAns = Integer.parseInt(inCase.getData()[5]);
		secondArr = new int[4][4];
		for(int i=0; i<4; i++){
			//System.out.println("  i2=" + i + " -> " + inCase.getData()[i+6]);
			ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[i+6], null);
			for(int j=0; j<xx.size(); j++){
				secondArr[i][j] = xx.get(j);
			}
		}
		
		System.out.println("init :: firstAns=" + firstAns + ", secondAns=" + secondAns + ", firstArr(2,2)=" + firstArr[2][2] + ", secondArr(1,3)=" + secondArr[1][3]);
	}

}
