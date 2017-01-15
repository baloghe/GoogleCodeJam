package riow2016.a;

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
	
	private int N;
	private ArrayList<Integer> prices;
	
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
		
		/*
		HashSet<Integer> salePrices = new HashSet<Integer>();
		HashSet<Integer> regPrices = new HashSet<Integer>();
		*/
		
		ArrayList<Integer> salePrices = new ArrayList<Integer>();
		ArrayList<Integer> regPrices = new ArrayList<Integer>();
		
		//first goes to salePrices for sure
		salePrices.add(prices.get(0));
		//System.out.println("to Sale: " + prices.get(0));
		int idxS = 0;
		int idxR = -1;
		
		for(int i=1; i<2*N; i++){
			int act = prices.get(i);
			//find first regular price
			if(idxR<0 && act==(salePrices.get(0)/3*4 ) ){
				regPrices.add(act);
				idxR = 0;
				//System.out.println("to Reg: " + act + ", idxR=" + idxR);
			} else {
				//if(idxR<idxS)System.out.println("  salePrices.get(idxR+1)*4/3=" + salePrices.get(idxR+1)*4/3);
				if(idxR<idxS && act==salePrices.get(idxR+1)/3*4){
					regPrices.add(act);
					idxR++;
					//System.out.println("to Reg: " + act + ", idxR=" + idxR);
				} else {
					salePrices.add(act);
					idxS++;
					//System.out.println("to Sale: " + act + ", idxS=" + idxS);
				}
			}
		}
		
		return Util.iterableToString(salePrices, " ");
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		N = Integer.parseInt(inCase.getData()[0]);
		prices = Util.splitStringToInt(inCase.getData()[1], null);
		
		System.out.println("init :: N=" + N + ", price["+(2*N)+"]="+prices.get(2*N-1));
	}

}
