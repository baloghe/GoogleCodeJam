package r1b2016.a;

import util.RawInput;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private String inStr;
	StringBuilder sb;
	
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
		
		int[] digits = new int[10];
		digits[0] = removeStrSeq(sb,"ZERO","Z");
		digits[2] = removeStrSeq(sb,"TWO","W");
		digits[6] = removeStrSeq(sb,"SIX","X");
		digits[8] = removeStrSeq(sb,"EIGHT","G");
		digits[4] = removeStrSeq(sb,"FOUR","U");
		digits[3] = removeStrSeq(sb,"THREE","H");
		digits[5] = removeStrSeq(sb,"FIVE","F");
		digits[1] = removeStrSeq(sb,"ONE","O");
		digits[7] = removeStrSeq(sb,"SEVEN","V");
		digits[9] = removeStrSeq(sb,"NINE","I");
		
		String ret = "";
		for(int i=0; i<digits.length; i++){
			int n = digits[i];
			if(n>0){
				for(int j=0; j<n; j++){
					ret += Integer.toString(i);
				}
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
		inStr = inCase.getData()[0];
		//ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		sb = new StringBuilder(inStr);
	}
	
	private int removeStrSeq(StringBuilder inSB, String inStrSeq, String inIdStr){
		int ret = 0;
		
		char[] chrSeq = inStrSeq.toCharArray();
		
		while(inSB.indexOf( inIdStr ) >= 0){
			ret++;
			for(char c : chrSeq){
				inSB.deleteCharAt(inSB.indexOf( String.valueOf(c) ));
			}
		}
		
		return ret;
	}

}
