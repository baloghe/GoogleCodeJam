package r1a2016.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import util.RawInput;
import util.Util;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int sidelen;
	private ArrayList<ArrayList<Integer>> rowscols;
	
	public static int UPDATE_ROW = 0;
	public static int UPDATE_COL = 1;
	
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
		//count all numbers in rows and cols
		//	observe smallest and largest value as well
		int smallestValue = Integer.MAX_VALUE;
		int largestValue = Integer.MIN_VALUE;
		int smallestValueIdx = -1;
		int largestValueIdx = -1;
		
		String ret;
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(ArrayList<Integer> list : rowscols){
			for(Integer i : list){
				if(i < smallestValue){
					smallestValue = i;
					smallestValueIdx = rowscols.indexOf(list);
				}
				if(i > largestValue){
					largestValue = i;
					largestValueIdx = rowscols.indexOf(list);
				}
				//put to map
				if(map.containsKey(i)){
					Integer num = Integer.valueOf(map.get(i).intValue() + 1);
					map.put(i, num);
				} else {
					map.put(i, Integer.valueOf(1));
				}
			}//next i
		}//next list
//		System.out.println("smallest: " + smallestValue + " at idx=" + smallestValueIdx);
//		System.out.println("   " + Util.iterableToString(rowscols.get(smallestValueIdx), " "));
//		
//		System.out.println("map: ");
//		for(Map.Entry<Integer, Integer> e : map.entrySet()){
//			System.out.println("   " + e.getKey().intValue() + " -> " + e.getValue().intValue());
//		}
		
		//select those that have occured odd times
		ArrayList<Integer> missingList = new ArrayList<Integer>();
		for(Integer i : map.keySet()){
			int num = map.get(i).intValue();
			if(num % 2 == 1){
				missingList.add(i);
			}
		}
		
		//form a row of them and put at the end of the list
		Collections.sort(missingList);
		rowscols.add(missingList);
		ret = Util.iterableToString(missingList, " ");
		//System.out.println("missingList: len=" + missingList.size() + ", content=" + ret);
		
		/////////////
		/*REMARK: 
		 * 	ret contain the string to be returned.
		 *  however, it would be interesting if, given the entire set of Rows + Cols, the final arrangement could be produced for Sergeant Argus
		 *   */
		/////////////
		
		//sort the whole thing
		Collections.sort(rowscols, new ListComparator<Integer>() );
		
		//build the arrangement
		Integer[][] arrangement = new Integer[sidelen][sidelen];
		//	find top-left corner 
		//  -> must be the first element in smallestValueIdx-th list in rowscols
		//  -> pretend this is going to be a row
		ArrayList<Integer> act = rowscols.remove(0);
//		System.out.println("first row will be:" + Util.iterableToString(act, " "));
		for(int i=0; i<sidelen; i++){
			arrangement[0][i] = act.get(i);
		}
//		System.out.println("Arrangement first row=" + Util.objArrayToString(arrangement[0], " "));
		
		//	build the rest 
		int colDone = -1;
		int rowDone = 0;
		int actTarget = ProblemSolver.UPDATE_ROW;
		
		while(rowDone < sidelen-1 || colDone < sidelen-1){
			actTarget = 1 - actTarget;
			arrangement = updateArrangement(
						 arrangement
						,actTarget
						,rowDone
						,colDone
					);
			if(actTarget == ProblemSolver.UPDATE_ROW){
				rowDone++;
			} else {
				colDone++;
			}
		}//wend
		
		//return Util.objMatrixToString(arrangement, " ");
		return ret;
	}
	
	public Integer[][] updateArrangement(Integer[][] inArr, int inTarget, int inRowDone, int inColDone){
		//select desired prefix for row or column
		Integer[] prefix=null;
		if(inTarget == ProblemSolver.UPDATE_COL){
			prefix = new Integer[inRowDone+1];
			for(int i=0; i<=inRowDone; i++){
				prefix[i] = inArr[i][inColDone+1];
			}
		} else {
			prefix = new Integer[inColDone+1];
			for(int i=0; i<=inColDone; i++){
				prefix[i] = inArr[inRowDone+1][i];
			}
		}
		
//		System.out.println("   prefix=" + Util.objArrayToString(prefix, " "));
		
		//find a suitable list
		for(ArrayList<Integer> act2 : rowscols){
			boolean b = true;
			//check list against expected prefix
			for(int i=0; i<prefix.length && b; i++){
				if( act2.get(i)==null || prefix[i]==null )
					break;
				b = ( act2.get(i).intValue() == prefix[i].intValue() );
			}
			//when OK then perform update
			if(b){
//				System.out.println("  " 
//						+ (inTarget == ProblemSolver.UPDATE_COL ? 
//								(inColDone+1) + "th column " :
//									(inRowDone+1) + "th row ")
//						+ "will be:" + Util.iterableToString(act2, " "));
				if(inTarget == ProblemSolver.UPDATE_COL){
					for(int i=1; i <sidelen; i++){
						inArr[i][inColDone+1] = act2.get(i);
					}
				} else {
					for(int i=1; i <sidelen; i++){
						inArr[inRowDone+1][i] = act2.get(i);
					}
				}
								
				rowscols.remove(act2);
				break;
			}//update done
		}//next list
		
		return inArr;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		sidelen = Integer.parseInt(inCase.getData()[0]);
		rowscols = new ArrayList<ArrayList<Integer>>();
		for(int i=1; i<2*sidelen; i++){
			ArrayList<Integer> list = Util.splitStringToInt(inCase.getData()[i], null);
			rowscols.add(list);
		}
		//ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
	}
}

