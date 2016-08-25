package r1a2016;

import java.util.ArrayList;
import java.util.Iterator;

import util.RawInput;
import util.Util;

public class ProblemASolver implements util.CaseSolver {

	private int runmode;
	
	private ArrayList<Character> list;
	
	public ProblemASolver(int inRunMode){
		runmode = inRunMode;
	}
	
	@Override
	public String solveCase(RawInput inCase) {
		init(inCase);
		String ret = "";
		
		Iterator<Character> it = list.iterator();
		Character firstLetter = it.next();
		ret = Character.toString(firstLetter);
		while(it.hasNext()){
			Character actLetter = it.next();
//			System.out.println("firstLetter=" + firstLetter
//					+ ", actLetter=" + actLetter 
//					+ ", comp=" + actLetter.compareTo(firstLetter)
//					);
			if(actLetter.compareTo(firstLetter) < 0){
				ret += Character.toString(actLetter);
			} else {
				ret = Character.toString(actLetter) + ret;
				//update firstLetter
				firstLetter = actLetter;
			}
		}
		
		return ret;
	}
	
	private void init(RawInput inCase){
		//store raw data
		//list = Util.splitStringToCharacter(inCase.getData(0), null);
		list = new ArrayList<Character>();
		for(char c : inCase.getData(0).toCharArray()){
			list.add(c);
		}
		//ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		
	}

}
