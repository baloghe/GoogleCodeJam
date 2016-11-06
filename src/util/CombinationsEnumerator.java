package util;

import java.util.ArrayList;

/**
 * based on: http://stackoverflow.com/questions/127704/algorithm-to-return-all-combinations-of-k-elements-from-n
 *
 * @param <T> type of objects
 */
public class CombinationsEnumerator<T> {

	private ArrayList<T> elems;
	ArrayList<ArrayList<T>> combs;
	
	public CombinationsEnumerator(ArrayList<T> inElements){
		elems = inElements;
	}
	
	public ArrayList<ArrayList<T>> getCombinations(int numToChoose){
		combs = new ArrayList<ArrayList<T>>();
		
		if(numToChoose > 0
				&& numToChoose <= elems.size())
			recBuilder(numToChoose,0,numToChoose,null);
		
		return combs;
	}
	
	public void recBuilder(int numToChoose, int startPos, int lenToComplete, ArrayList<T> prefix){
		/*
		System.out.println(" rec CALLED :: numToChoose=" + numToChoose + ", startPos=" + startPos 
				          + ", lenToComplete=" + lenToComplete 
				          + ", prefix=" + (prefix==null? "NULL" : Util.iterableToString(prefix, ",")) );
		*/
		//shortcuts
		if(prefix == null){
			//a new (empty) combination prefix has to be created
			prefix = new ArrayList<T>();
			//System.out.println("   shortcut: prefix == null");
		} else if(lenToComplete==0) {
			//prefix is a complete combination => add its copy to the collection
			combs.add(copyArray(prefix));
			//System.out.println("   shortcut: lenToComplete==0");
			return;
		}
		
		//otherwise: take all possible elems at current position and branch
		//System.out.println("  rec iter :: " + startPos + " -> " + (elems.size() - lenToComplete) );
		for(int i=startPos; i < elems.size() - lenToComplete+1; i++){
			ArrayList<T> pfxcopy = copyArray(prefix);
			pfxcopy.add(elems.get(i));
			/*
			System.out.println("      i=" + i + ". rec call WITH :: numToChoose=" + numToChoose + ", startPos=" + (i+1) 
			          + ", lenToComplete=" + (lenToComplete-1) 
			          + ", prefix=" + (prefix==null? "NULL" : Util.iterableToString(pfxcopy, ",")) );
			*/
			recBuilder(numToChoose, i+1, lenToComplete-1, pfxcopy);
		}
		
	}
	
	public ArrayList<T> copyArray(ArrayList<T> orig){
		ArrayList<T> ret = new ArrayList<T>();
		for(T elem : orig){
			ret.add(elem);
		}
		return ret;
	}
	
	public void addToCollection(ArrayList<T> comb){
		combs.add(comb);
		System.out.println("comb added: " + Util.iterableToString(comb, ","));
	}
}
