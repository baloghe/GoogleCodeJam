package rq2016;

import java.util.ArrayList;
import java.util.HashSet;

import util.RawInput;
import util.Util;

public class JamCoinSolver implements util.CaseSolver {

	private int jcLength;
	private int jcNum;
	private int runmode;
	
	public JamCoinSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	@Override
	public String solveCase(RawInput inCase) {
		init(inCase);
		
		String ret = "";
		
		/*
		 * Trick: if J is a jamcoin then JJ is also a jamcoin (that is: the digits just repeated)
		 * Unfortunately for N=8 there is only 32 different jamcoins, so for N=16 and J=50 the trick will not work
		 * But for N=16 there is more than 500 different jamcoins, therefore N=32 and J=500 could be solved like that
		 */
		int genLen;
		if(runmode == JamCoin.RUNMODE_SMALL){
			genLen = jcLength;
		} else {
			genLen = jcLength / 2;
		}
		
		HashSet<JamcoinObject> jamcoins = JamcoinObject.buildJamcoins(genLen, jcNum);
		for(JamcoinObject jc : jamcoins){
			String retline = jc.getLiteral() + " " + Util.longArrayToString(jc.getDivisors() , " ");
			if(runmode == JamCoin.RUNMODE_LARGE){
				retline = jc.getLiteral() + retline;
			}
			ret += ("\n" + retline);
		}
		
		return ret;
	}
	
	private void init(RawInput inCase){
		//store raw data
		ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		this.jcLength = xx.get(0);
		this.jcNum = xx.get(1);
	}

}
