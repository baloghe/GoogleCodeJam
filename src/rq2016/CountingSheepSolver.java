package rq2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;

import util.RawInput;
import util.Util;

public class CountingSheepSolver implements util.CaseSolver {
	
	private long rootnum;
	
	private HashSet<String> digits;
	
	private static int countcap=100;
	
	public CountingSheepSolver(){
		
	}
	
	public String solveCase(RawInput inCase){
		String ret = "";
		
		//init local vars based on case
		this.init(inCase);
		
		//zero leads to INSOMNIA for sure
		if(rootnum==0) return "INSOMNIA";
		
		int cnt = 1;
		boolean found=false;
		long actnumber = 0;
		while(cnt <= countcap && !found){
			actnumber += rootnum;
			String str = Long.toString(actnumber);
			char[] chrs = str.toCharArray();
			for(char c : chrs){
				String key = String.valueOf(c);
				digits.remove(key);
			}
			if(digits.size()==0){
				found=true;
			}
			cnt++;
		}
		
		if(found){
			ret = "" + actnumber;
		} else {
			ret = "INSOMNIA";
		}
		
		return ret;
	}
	
	private void init(RawInput inCase){
		
		//store raw data
		this.rootnum = new Long(inCase.getData()[0]).longValue();
		
		//transform as needed
		this.digits = new HashSet<String>();
		for(int i=0; i<10; i++){
			digits.add("" + i);
		}
	}
	
}