package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collections;

import util.RawInput;
import util.Util;

public class StoreCreditSolver implements util.CaseSolver {
	
	private int credit;
	private int itemNum;
	private ArrayList<Integer> itemPrices;
	
	private HashMap<Integer, HashSet<Integer>> map;
	
	public StoreCreditSolver(){
		
	}
	
	public String solveCase(RawInput inCase){
		String ret = "";
		
		//init local vars based on case
		this.init(inCase);
		
		//general solution: credit == a + b must hold
		//  for each h in map.set: 
		//		look if (credit - h) is also available  -> if YES then {h , (credit - h)} are the winners...
		//
		//shortcut: if accidentally credit is even and (credit/2) has two occurences 
		boolean found=false;
		ArrayList<Integer> outIdx = new ArrayList<Integer>();
		for(Integer key : map.keySet()){
			Integer antikey = new Integer(this.credit - key.intValue());
			if(!found){
				HashSet<Integer> hk = map.get(key);
				if( key.intValue() == antikey.intValue() ){
					if(hk.size() > 1){
						outIdx.addAll(hk);
						found = true;
					}
				} else {
					HashSet<Integer> hak = map.get(antikey);
					if(hak != null && hak.size() > 0){
						outIdx.addAll(hk);
						outIdx.addAll(hak);
						found = true;
					}
				}
			}//end if (!found)
		}
		
		//Sort and output --> warning! index start here with 0 but 1 is expected 
		//	=> add 1 everywhere
		Collections.sort(outIdx);
		ret = (outIdx.get(0)+1) + " " + (outIdx.get(1)+1);
		
		return ret;
	}
	
	private void init(RawInput inCase){
		
		//store raw data
		this.credit = new Integer(inCase.getData()[0]).intValue();
		this.itemNum = new Integer(inCase.getData()[1]).intValue();
		this.itemPrices = Util.splitStringToInt(inCase.getData()[2], null);
		
		//transform as needed
		map = new HashMap<Integer, HashSet<Integer>>();
		for(int i = 0; i < itemPrices.size(); i++){
			Integer c = itemPrices.get(i);
			if(map.containsKey(c)){   //increase counter if already exists
				HashSet<Integer> x = map.get(c);
				x.add(new Integer(i));
			} else {  //counter=1 at first occurence
				HashSet<Integer> x = new HashSet<Integer>();
				x.add(new Integer(i));
				map.put(c, x);
			}
			
		} // next c
	}
	
}
