package rq2014.b;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	
	double C;
	double F;
	double X;
	
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
		/* User has the following "decision tree":
		 *                                                                                                Total Wait Time
		 * EITHER wait X/2                                                                                X/2
		 * OR wait C/2      ->  EITHER wait X/(2+F)                                                       C/2 + X/(2+F)
		 *    buy farm          OR wait C/(2+F)      -> EITHER wait X/(2+2F)                              C/2 + C/(2+F) + X/(2+2F)
		 *                         buy farm             OR wait C/(2+2F)     -> EITHER wait X/(2+3F)      C/2 + C/(2+F) + C/(2+2F) + X/(2+3F)
		 *                                                 buy farm
		 * etc.
		 * in fact, buying k farms would lead to a total waiting time of TWT(k) := X/(2+k*F) + C/2 + C/(2+F) + ... + C/(2+k*F) -> min (by k)
		 * An alternative is not to build farms, leading to total waiting time of X/2
		 * 		=> if TWT(1) > X/2 then k=0 is the solution and X/2 to be returned
		 * 		   otherwise try new k values as long as TWT(k) < TWT(k-1) holds. As long as TWT(k) > TWT(k-1) is experienced, (k-1) is the solution and TWT(k-1) to be returned
		 * */
		double ret = -1.0;
		
		double twt0 = X / 2.0;
		double twt1 = C/2.0 + X/(2.0+F);
		
		if(twt1 > twt0) ret = twt0;
		
		double runningSum = C/2.0;
		double twtlag = twt0;
		double twtk = twt1;
		int k = 1;
		while(   twtk < twt0
		      && twtk < twtlag){
			//step ahead
			twtlag = twtk;
			runningSum += C/(2.0+k*F);
			k++;
			twtk = runningSum + X/(2.0+k*F);
			//System.out.println("k=" + k + "  -> rsum=" + runningSum + ", twtk=" + twtk + ", twtlag=" + twtlag);
		}//wend
		ret = twtlag;
		
		return Double.toString(
				BigDecimal.valueOf(ret)
				    .setScale(7, RoundingMode.HALF_UP)
				    .doubleValue()
			    );
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		ArrayList<Double> xx = Util.splitStringToDouble(inCase.getData()[0], " ");
		C = xx.get(0);
		F = xx.get(1);
		X = xx.get(2);
		
		System.out.println("init :: C=" + C + ", F=" + F + ", X=" + X);
	}

}
