package rq2015.c;

import util.RawInput;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private long origLen;
	private long origRep;
	private String origStr;
	//private String generatedString;  //could be too big...
	
	public static Quaternion ijk = Quaternion.parseList("ijk");
	public static Quaternion qi = new Quaternion(Quaternion.I);
	public static Quaternion qj = new Quaternion(Quaternion.J);
	public static Quaternion qk = new Quaternion(Quaternion.K);
	
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
		
		
		//otherwise solve by brute force
		//return (runmode != ProblemSolution.RUNMODE_LARGE) ?  solveSmallProblem() : solveLargeProblem();
		return solveLargeProblem();
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		String[] first = inCase.getData()[0].split(" ");
		origLen = Long.parseLong(first[0]);
		origRep = Long.parseLong(first[1]);
		origStr = inCase.getData()[1];
		
		//System.out.println( "(" + inCase.getData()[0] + " ; " + inCase.getData()[1] + ") -> generatedString=" + generatedString + " --> len=" + generatedString.length() );
	}
	
	private String generateFullString(){
		return generateString(origRep);
	}
	
	private String generateString(long inTimes){
		String generatedString = origStr;
		for(int i=1; i<inTimes; i++){
			generatedString += origStr;
		}
		return generatedString;
	}
	
	public String solveLargeProblem(){
		
		//generated String fitting into SmallProblem => solve it with that...
		if( origLen * origRep < Integer.MAX_VALUE ) return solveSmallProblem();
		//if( origLen * origRep < 9 ) return solveSmallProblem();  //to test LARGE behaviour on relatively small problem...
		
		//Look for posI left->right
		String left4 = generateString(4);
		int lenLeft4 = left4.length();
		//Obtain I from left to right
		int posI = 0;
		boolean foundI = false;
		Quaternion candI = new Quaternion(Quaternion.E);
		while( (!foundI) && posI<lenLeft4 ){
			Quaternion act = Quaternion.parseList(left4.substring(posI,posI+1));
			candI = candI.mult(act);
			if(candI.equals(qi)){
				foundI = true;
				break;
			}
			//step ahead
			posI++;
		}
		//posI was set to be _before_ the last character processed!
		posI++;
		//say goodbye if I could not be found
		if(posI >= lenLeft4) return "NO";
		//remainder of first part
		String left4Remainder = left4.substring(posI);
		Quaternion leftQ = Quaternion.parseList(left4Remainder);
		//System.out.println("solveLargeProblem :: I found by posI=" + posI + " == " + left4.substring(0,posI));
		
		//Look for posK right->left
		String right4 = generateString(4);
		int lenRight4 = right4.length();
		int posK = lenRight4;
		boolean foundK = false;
		Quaternion candK = new Quaternion(Quaternion.E);
		while( (!foundK) && posK > 0 ){
			Quaternion act = Quaternion.parseList(right4.substring(posK-1,posK));
			candK = act.mult(candK);
			if(candK.equals(qk)){
				foundK = true;
				break;
			}
			//step back
			posK--;
		}
		//posK was set to be _after_ the last character processed!
		posK--;
		//say goodbye if I could not be found
		if(posK <= 0) return "NO";
		//remainder of third part
		String right4Remainder = right4.substring(posK);
		Quaternion rightQ = Quaternion.parseList(right4Remainder);
		//System.out.println("solveLargeProblem :: K found by posK=" + posK + " == " + right4.substring(posK,lenRight4));
		
		
		//evaluate the middle
		//  but the 4th power of a quaternion is always 1 ==> enough to take [(origRep - 8) % 4]th power
		String midStr = origStr;
		Quaternion midQ = Quaternion.parseList(midStr);
		long powerToTake = (origRep - (long)8) % (long)4;
		for(long i = 1; i<powerToTake; i++){
			midQ = midQ.mult(midQ);
		}
		
		//See if (leftQ * midQ * midQ) reduces to J
		Quaternion candJ = leftQ.mult(midQ).mult(rightQ);
		if(   foundI
		   && candJ.equals(qj) 
		   && foundK          ) return "YES";
		
		//otherwise there is no solution
		return "NO";
	}
	
	public String solveSmallProblem(){
		//generate String
		String generatedString = generateFullString();
		
		//i*j*k reduces to ijk
		//=> generatedString must reduce to the same Quaternion otherwise it cannot possibly be reduced to i|j|k
		Quaternion qFullStr = Quaternion.parseList(generatedString);
		if( (!qFullStr.equals(ijk)) || generatedString.length() < 3 ) return "NO";
		
		int len = generatedString.length();
		
		//Obtain I from left to right
		int posI = 0;
		boolean foundI = false;
		Quaternion candI = new Quaternion(Quaternion.E);
		while( (!foundI) && posI<len-2 ){
			Quaternion act = Quaternion.parseList(generatedString.substring(posI,posI+1));
			candI = candI.mult(act);
			if(candI.equals(qi)){
				foundI = true;
				break;
			}
			//step ahead
			posI++;
		}
		//posI was set to be _before_ the last character processed!
		posI++;
		//System.out.println("solveSmallProblem :: I found by posI=" + posI + " == " + generatedString.substring(0,posI));
		
		
		//Obtain K from right to left
		int posK = len;
		boolean foundK = false;
		Quaternion candK = new Quaternion(Quaternion.E);
		while( (!foundK) && posK > posI+1 ){
			Quaternion act = Quaternion.parseList(generatedString.substring(posK-1,posK));
			candK = act.mult(candK);
			if(candK.equals(qk)){
				foundK = true;
				break;
			}
			//step back
			posK--;
		}
		//posK was set to be _after_ the last character processed!
		posK--;
		//System.out.println("solveSmallProblem :: K found by posK=" + posK + " == " + generatedString.substring(posK,len));
		
		//in the middle must stay a J! otherwise it cannot be solved
		if(posI>=posK) return "NO";
		Quaternion candJ = Quaternion.parseList(generatedString.substring(posI,posK));
		//System.out.println("solveSmallProblem ::candJ=" + candJ + " == " + generatedString.substring(posI,posK));
		if( foundI && foundK && candJ.equals(qj) ) return "YES";
		
		return "NO";
	}
	
	public String solveBruteForce(){
		
		//generate String
		String generatedString = generateFullString();
		
		//i*j*k reduces to ijk
		//=> generatedString must reduce to the same Quaternion otherwise it cannot possibly be reduced to i|j|k
		Quaternion qFullStr = Quaternion.parseList(generatedString);
		if( (!qFullStr.equals(ijk)) || generatedString.length() < 3 ) return "NO";
			
		
		boolean possible = false;
		
		int len = generatedString.length();
		for(int s1=1; s1 < len-1; s1++){
			for(int s2=s1+1; s2 < len; s2++){
				//split up
				//System.out.println("s1=" + s1 + ", s2=" + s2);
				String act1 = generatedString.substring(0,s1);
				String act2 = generatedString.substring(s1,s2);
				String act3 = generatedString.substring(s2,len);
				//System.out.println( "   " + s1 + "," + s2 + " -> " + act1 + "|" + act2 + "|" + act3 );
				
				//reduce
				Quaternion a1 = Quaternion.parseList(act1);
				Quaternion a2 = Quaternion.parseList(act2);
				Quaternion a3 = Quaternion.parseList(act3);
				
				//compare
				if(   a1.equals(qi) 
				   && a2.equals(qj)
				   && a3.equals(qk) ) possible = true;
				
				if(possible) break;
			}//next s2
			if(possible) break;
		}//next s1
		
		return possible ? "YES" : "NO";
	}

}
