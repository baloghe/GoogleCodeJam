package r1c2015.b;

import java.util.ArrayList;
import java.util.Locale;

import util.RawInput;
import util.Util;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int K; //length of alphabet
	private int L; //length of target word
	private int S; //length of string to be typed by the monkey
	private String targetWord;
	private String alphabet;
	
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
		
		//generate all possible words of length S, typed by the monkey
		PermutationsEnumerator<String> pe = new PermutationsEnumerator<String>( stringToArrList(alphabet), S );
		//System.out.println("  alphabet=" + stringToArrList(alphabet) );
		int permCnt = 0;
		int[] occurences = new int[8];
		while( pe.hasNext() ){
			ArrayList<String> perm = pe.next();
			String s = "";
			for(String d : perm){
				s += d;
			}
			occurences[ countTWOccurence(s, targetWord) ]++;
			//permutation counter
			permCnt++;
		}
		
		//determine maximum number of bananas to have
		int maxBananas = 0;
		for(int i=occurences.length-1; i>0; i--){
			if( occurences[i] > 0 ){
				maxBananas = i;
				break;
			}
		}
		
		//calc E[bananas to give]
		double numerator = 0.0;
		for(int i=1; i<occurences.length; i++){
			numerator += ( (double)i * (double)occurences[i] );
		}
		double expBananaToGive = numerator / (double)permCnt ;
		
		double bananasToKeep = (double)maxBananas - expBananaToGive;
		
		//System.out.println("BF: permCnt=" + permCnt + ", maxBananas=" + maxBananas + ", numerator=" + numerator + ", expBananaToGive=" + expBananaToGive);
		
		return String.format(Locale.US,"%.7f",bananasToKeep);
	}
	
	public static int countTWOccurence(String s, String tw){
		if( s.indexOf(tw) >= 0){
			int cnt = 0;
			String stmp = s;
			int idx = stmp.indexOf(tw);
			while(idx >= 0){
				cnt++;
				stmp = stmp.substring(idx+1);
				idx = stmp.indexOf(tw);
			}
			return cnt;
		}
		else return 0;
	}
	
	public static ArrayList<String> stringToArrList(String inS){
		ArrayList<String> ret = new ArrayList<String>();
		for(int i=0; i<inS.length(); i++){
			ret.add(inS.substring(i, i+1));
		}
		return ret;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		ArrayList<Integer> kls = Util.splitStringToInt(inCase.getData()[0], null);
		K = kls.get(0);
		L = kls.get(1);
		S = kls.get(2);
		alphabet = inCase.getData()[1];
		targetWord = inCase.getData()[2];
		//System.out.println("init :: K=" + K + ", L=" + L + ", S=" + S + ", targetWord=" + targetWord + ", alphabet=" + alphabet);
	}

}
