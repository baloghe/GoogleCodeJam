package rq2014.c;

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
	
	private int R, C, M;
	
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

		int minRC = (R > C ? C : R);
		int freeNum = R * C - M;
		
		if(minRC == 1){
			if( freeNum > 0){
				return "\n" + printArrangement(R,C,M);
			} else {
				return "\nImpossible";
			}
		} else {
			if( freeNum==1 || freeNum >= 4 ){
				return "\n" + printArrangement(R,C,M);
			} else {
				return "\nImpossible";
			}
		}
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		R = xx.get(0);
		C = xx.get(1);
		M = xx.get(2);
		System.out.println("init :: R=" + R + ", C=" + C + ", M=" + M);
	}

	
	public String printArrangement(int inR, int inC, int inM){
		
		if(inR==1){
			String ret = "c";
			int used=1;
			for(int i=1; i<inR*inC-inM; i++){
				ret += ".";
				used++;
			}
			for(int i=used; i<inR*inC; i++){
				ret += "*";
			}
			return ret;
		}//endif inR==1
		
		if(inC==1){
			String ret = "c";
			int used=1;
			for(int i=1; i<inR*inC-inM; i++){
				ret += "\n.";
				used++;
			}
			for(int i=used; i<inR*inC; i++){
				ret += "\n*";
			}
			return ret;
		}//endif inC==1
		
		/* so now R,C > 1 */
		int freeNum = inR*inC - inM;
		String ret = "c";
		int freeUsed = 1;
		//only 1 unoccupied space
		if(freeNum == 1){
			//first row, other columns
			for(int i=1; i<inC; i++){
				ret += "*";
			}
			//other rows
			for(int i=1; i<inR; i++){
				ret +="\n";
				for(int j=0; j<inC; j++){
					ret += "*";
				}
			}
		} else {
			ret = "c.";
			freeUsed = 4;//1 for c + 3 for reserved places around it
			//first row, other columns
			for(int i=2; i<inC; i++){
				if(freeUsed < freeNum){
					ret += ".";
					freeUsed++;
				} else {
					ret += "*";
				}
			}
			//second row
			ret += "\n..";
			for(int i=2; i<inC; i++){
				if(freeUsed < freeNum){
					ret += ".";
					freeUsed++;
				} else {
					ret += "*";
				}
			}
			//other rows
			for(int i=2; i<inR; i++){
				ret +="\n";
				for(int j=0; j<inC; j++){
					if(freeUsed < freeNum){
						ret += ".";
						freeUsed++;
					} else {
						ret += "*";
					}
				}//next col
			}//next row
		}
		
		return ret;
	}
}
