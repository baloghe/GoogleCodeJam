package r1c2015.b;

import java.util.ArrayList;

import util.Util;

public class PermutationsEnumerator<T> {

	private ArrayList<T> elems;
	private int idxLimit;
	private int[] idx;
	
	private boolean allDone;
	
	public PermutationsEnumerator(ArrayList<T> inElems, int inPermLen){
		elems = inElems;
		idxLimit = elems.size();
		idx = new int[inPermLen];
		allDone = false;
	}
	
	public boolean hasNext(){
		return (!allDone);
	}
	
	public ArrayList<T> next(){
		ArrayList<T> ret = new ArrayList<T>();
		
		//create new permutation
		for(int i=0; i<idx.length; i++){
			ret.add( elems.get( idx[i] ) );
		}
			
		//step indices
		idx[idx.length-1]++;
		stepIndices(idx.length-1);
		
		//System.out.println("next :: " + ret + ", " + Util.intArrayToString(idx, ","));
		return ret;
	}
	
	private void stepIndices(int inPointOfLastMod){
		//all index value must be in range [0..idxLimit-1]
		//any kind of overflow should be pushed to the LEFT, that is:
		//	1) index value increased by one
		//	2) recursive call for propagation
		//=> that is: recursion is called by a maximum number of idx.length
		
		if(idx[inPointOfLastMod] >= idxLimit){
			idx[inPointOfLastMod] = 0;
			if(inPointOfLastMod > 0){
				idx[inPointOfLastMod-1]++;
				//!!! Recursive Call !!!
				stepIndices( inPointOfLastMod-1 );
			} else {
				//total overflow reached => set everything to 0
				idx = new int[idx.length];
				allDone = true;
			}
		}
	}
}
