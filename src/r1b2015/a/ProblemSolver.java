package r1b2015.a;

import util.RawInput;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private long N;
	private static long[] tenPowers = null;
	
	/**
	 * constructor. The expected size of the problem might require different approaches, therefore runmode is required as a parameter   
	 * @param inRunMode expected RUNMODE_SMALL or RUNMODE_LARGE (see ProblemSolution)
	 */
	public ProblemSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	@Override
	public String solveCase(RawInput inCase) {
		//return solveGreedy(inCase);
		return solveLarge(inCase);
	}
	
	public String solveLarge(RawInput inCase){
		init(inCase);
		/*
		 * 
		 */
		long cumDiff;
		long act;
		
		cumDiff = 0;
		act = N;
		
		/* A reversal of digits makes sense if the actual number (ACT) is of form D..D00..01
		 *    -> rev(ACT) = 10..00D..D  and the gain would be (D..D00..01 - 10..00D..D - 1)
		 * 
		 * So while looking for maximal gain, we should inspect all possible numbers meeting the D..D00..01 pattern in the 
		 * neighbourhood of ACT. However 
		 * 		
		 *  
		 * There is always a k, for which: 10^(k+1) < ACT <= 10^k holds
		 * So we look for each lower bound {10^j+1 : j=2..k} of ACT
		 * 
		 * E.g. N=49000 => to be inspected:
		 * 		100:   48901  -> gain = 48901 - 10984 - 1 = 37916
		 *      1000:  48001  -> gain = 48001 - 10084 - 1 = 37916
		 *      10000: 30001  -> gain = 30001 - 10003 - 1 = 19997
		 */
		
		while(act > 10){
			long  cand = getReverseCandidate2(act);
			long lowerLimit = getLowerPowerTen(act);
			//System.out.println("act=" + act + ", cand=" + cand + ", lowerLimit=" + lowerLimit);
			
			if(cand > -1 && cand <= act){
				act = cand;
				long rev = reverseLong(act);
				cumDiff += (act - rev - 1);
				//System.out.println("  Step 2 REVERSE: " + act + "->" + rev + ", cumDiff = " + cumDiff);
				act = rev;
			} else {
				
				if(cand > -1 || act > lowerLimit){
					act = lowerLimit;
				} else {
					act--;
				}
				
				//act = lowerLimit;
			}
		}
		
		return Long.toString(N - cumDiff);
	}
	
	public String solveGreedy(RawInput inCase) {
		init(inCase);
		/*
		 * 
		 */
		long cumDiff;
		long act;
		
		cumDiff = 0;
		act = N;
		
		while(act > 10){
			//establish a lower limit
			long lowerLimit = getLowerPowerTen(act);
			long revCand = getReverseCandidate(act,lowerLimit);
			
			if(   revCand > -1){
				long rev = reverseLong(revCand);
				cumDiff += (revCand - rev - 1);
				//System.out.println("REVERSE: " + act + "->" + revCand + "->" + rev + ", cumDiff = " + cumDiff);
				act = rev;
			} else {
				if(revCand > -1){
					act = lowerLimit;
				} else {
					act--;
				}
			}
		}//wend
		
		return Long.toString(N - cumDiff);
	}
	
	public static long getReverseCandidate(long inLong, long inLowerLimit){
				
		//search for the biggest gain by reversal
		long maxGain = 0;
		long ret = -1;
		for(long l = inLong; l >= inLowerLimit; l-- ){
			long rev = reverseLong(l);
			long lastDigit = l % 10L;
			long actGain = l - rev;
			if(   lastDigit != 0
			   && l > rev
			   && actGain > maxGain){
				maxGain = actGain;
				ret = l;
			}//end if
		}//next l
		
		return ret;
	}
	
	public static long getReverseCandidate2(long inLong){
		
		if(inLong < 10) return inLong;
		if(inLong % 10L == 0) return getReverseCandidate2(inLong - 1L);
		
		//search for the biggest gain by reversal
		long maxGain = 0;
		long ret = -1;
		
		int maxTenPow = 0;
		long maxTenPowNum = 1L;
		while(maxTenPowNum < inLong){
			maxTenPow++;
			maxTenPowNum *= 10L;
		}
		maxTenPow--;
		maxTenPowNum /= 10L;
		
		for(int i=0; i<=maxTenPow; i++ ){
			
			long diffToNext = inLong % tenPowers[i];
			long actCand = inLong /*- tenPowers[i]*/ - diffToNext + 1;
			long rev = reverseLong(actCand);
			long actGain = actCand - rev;
			long lastDigit = actCand % 10L;
			//System.out.println("i=" + i + ", candidate=" + actCand + ", tenPowers[i]=" + tenPowers[i] + ", rev=" + rev);
			if(   lastDigit != 0
			   && inLong  >= actCand
			   && actCand > rev
			   && actCand > 10
			   && actGain > maxGain){
				maxGain = actGain;
				//System.out.println("   new MAX gain=" + maxGain);
				ret = actCand;
			}//end if
		}//next l
		
		return ret;
	}
	
	public static long getLowerPowerTen(long inLong){
		long lowerLimit = 1;
		while(lowerLimit <= inLong){
			lowerLimit *= 10L;
		}
		lowerLimit /= 10L;
		return lowerLimit;
	}
	
	public static long reverseLong(long inLong){
		
		long act = inLong;
		long ret = 0;
		
		while(act != 0){
			long rem = act % 10L;
			act /= 10;
			ret *= 10;
			ret += rem;
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
		N = Long.parseLong(inCase.getData()[0]);
		//System.out.println("N=" + N);
		
		initTenPowers();
	}
	
	public static void initTenPowers(){
		if(tenPowers == null){
			tenPowers = new long[15];
			tenPowers[0] = 1;
			for(int i=1; i<14; i++){
				tenPowers[i] = tenPowers[i-1] * 10;
			}
		}
	}

}
