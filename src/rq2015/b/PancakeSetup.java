package rq2015.b;

import java.util.ArrayList;
import java.util.HashSet;

import util.Util;

public class PancakeSetup {

	private ArrayList<Integer> plates;
	private int waitingTime;
	
	public PancakeSetup(ArrayList<Integer> inPlates, int inWaitingTime){
		plates = inPlates;
		waitingTime = inWaitingTime;
	}
	
	public ArrayList<Integer> getPlates(){return plates;}
	public int getWaitingTime(){return waitingTime;}
	
	public void incWaitingTime(int inAddMinutes){
		waitingTime += inAddMinutes;
	}
	
	public String toString(){
		return "[" + Util.iterableToString(plates, ",") + "]::" + waitingTime;
	}
	
	public int hashCode(){return this.toString().hashCode();}
	
	public boolean equals(Object inObj){
		if(!(inObj instanceof PancakeSetup))return false;
		return ( this.hashCode()==inObj.hashCode() );
	}
	
	/**
	 * Splits up the current Setup in all possible ways and returns possible scenarios (all being copies of the existing Setup)
	 * @return all possible splitups in an array 
	 */
	public HashSet<PancakeSetup> split(){
		int MAX_0 = getMaxNumber();
		int maxPieces = 0;
		HashSet<PancakeSetup> ret = new HashSet<PancakeSetup>();
		
		//decide how many pieces to split in
		if( MAX_0 % 3 == 0 ){
			maxPieces = MAX_0 / 3; //naturally floor(.)
		} else {
			maxPieces = MAX_0 / 3 + 1;
		}
		
		//perform splits
		for(int i=2; i<= maxPieces; i++){
			PancakeSetup newPS = this.copy();
			splitOneSetup(newPS, i);
			ret.add(newPS);
		}
		
		//return the result set
		return ret;
	}
	
	public void splitOneSetup(PancakeSetup inSetup, int inNumPieces){
		ArrayList<Integer> inEaters = inSetup.getPlates();
		
		int MAX_0 = Integer.MIN_VALUE;
		int POS_0 = -1;
		
		for(int idx=0; idx < inEaters.size(); idx++){
			int i = inEaters.get(idx);
			//Max_0 == simple max
			if( i > MAX_0 ){
				MAX_0 = i;
				POS_0 = idx;
			} 
		}
		/*
		int new_MAX_0;
		int new_plate;
		if( (MAX_0 % 2)==0 ){
			new_MAX_0 = MAX_0 / 2;
			new_plate = new_MAX_0;
		} else {
			new_MAX_0 = MAX_0 / 2;
			new_plate = new_MAX_0 + 1;
		}
		*/
		int[] newPieces = new int[inNumPieces];
		int new_MAX_0 = MAX_0 / inNumPieces; //naturally floor(.)
		newPieces[0] = new_MAX_0;
		int splitAlready = new_MAX_0;
		int rest = MAX_0 - splitAlready;
		for(int i=1; i<inNumPieces-1; i++){
			newPieces[i] = new_MAX_0;
			splitAlready += splitAlready;
			rest -= new_MAX_0;
		}
		//add the rest
		newPieces[inNumPieces-1] = rest;
		
		//update former MAX_0
		inEaters.set(POS_0, new Integer(newPieces[0]) );
		//add pankaces to empty plates
		for(int i=1; i<inNumPieces; i++){
			inEaters.add(new Integer(newPieces[i]));
		}
		//update accumulated waiting minutes
		inSetup.incWaitingTime( inNumPieces-1 );
	}
	
	public PancakeSetup copy(){
		ArrayList<Integer> plateCopy = new ArrayList<Integer>();
		for(int i : plates){
			plateCopy.add(new Integer(i));
		}
		int wtCopy = waitingTime;
		return new PancakeSetup( plateCopy , wtCopy );
	}
	
	public int getMaxNumber(){
		int ret = Integer.MIN_VALUE;
		for(int i : plates){
			if( i > ret ){
				ret = i;
			}
		}
		return ret;
	}
}
