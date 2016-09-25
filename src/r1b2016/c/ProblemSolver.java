package r1b2016.c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import util.RawInput;
import util.Util;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int rownum;
	private HashMap<String,Integer> titleList;
	private HashMap<String,Integer> firstWordCount;
	private HashMap<String,Integer> secondWordCount;
	
	
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
		//Build a bipartite graph from First and Second words as vertices, Titles as edges.
		//Find a match between First and Second words 
		//           --> that would use some (but not necessarily all) of the edges
		//           --> and it may result some isolated edges as well
		//1. Edges in the match cannot be fakes
		//2. We can suppose that the first edge connected to an isolated vertex (resp. to the match) s also not a fake
		//All remaining edges represent potentially fake titles
		
		//Build a bipartite graph and obtain a match
		ArrayList<String[]> edgeList = new ArrayList<String[]>();
		for(String s : titleList.keySet()){
			String[] sa = s.split(" ");
			edgeList.add(sa);
		}
		BipartiteGraphMatcher matcher = new BipartiteGraphMatcher(edgeList);
		HashMap<GraphNode, GraphNode> result = matcher.match();
		
		//Identify isolated vertices in the match
		HashSet<GraphNode> isolFirst = new HashSet<GraphNode>();
		for(String s : firstWordCount.keySet()){
			isolFirst.add( new GraphNode(0,s) );
		}
		isolFirst.removeAll(result.keySet());
		HashSet<GraphNode> isolSecond = new HashSet<GraphNode>();
		for(String s : secondWordCount.keySet()){
			isolSecond.add( new GraphNode(0,s) );
		}
		isolSecond.removeAll(result.values());
		
		//System.out.println("isolFirst=" + Util.iterableToString(isolFirst, ","));
		//System.out.println("isolSecond=" + Util.iterableToString(isolSecond, ","));
		
		//Suppose all titles are faked...
		ArrayList<String> fakeList = new ArrayList<String>();
		for(String[] sa : edgeList){
			String title = sa[0] + " " + sa[1];
			fakeList.add(title);
		}
		
		//...then eliminate "generator" items...
		for(Map.Entry<GraphNode, GraphNode> e : result.entrySet()){
			String title = e.getKey().getLabel() + " " + e.getValue().getLabel();
			fakeList.remove(title);
			//System.out.println("generator removed: " + title);
		}
		
		//...then eliminate first edges connected to an isolated vertex
		HashMap<String, String> edgeFirstIsolated = new HashMap<String, String>();
		HashMap<String, String> edgeSecondIsolated = new HashMap<String, String>();
		for(String s : fakeList){
			String[] sa = s.split(" ");
			GraphNode g1 = new GraphNode(0,sa[0]);
			GraphNode g2 = new GraphNode(0,sa[1]);
			if( isolFirst.contains(g1) && (!edgeFirstIsolated.containsKey(g1)) ){
				edgeFirstIsolated.put(sa[0], s);
			} else if( isolSecond.contains(g2) && (!edgeSecondIsolated.containsKey(g2)) ){
				edgeSecondIsolated.put(sa[1], s);
			}
		}
		for(String title : edgeFirstIsolated.values()){
			fakeList.remove(title);
			//System.out.println("First-isolated title removed: " + title);
		}
		for(String title : edgeSecondIsolated.values()){
			fakeList.remove(title);
			//System.out.println("Second-isolated title removed: " + title);
		}
		
		return Integer.toString( fakeList.size() );
	}
	
	public String unfortunately_not_working(RawInput inCase) {
		init(inCase);
		ArrayList<String> fwords = this.getMultipleOccurences(firstWordCount);
		ArrayList<String> swords = this.getMultipleOccurences(secondWordCount);
		ArrayList<String> noFakeList = new ArrayList<String>();
		ArrayList<String> fakeCandidates = new ArrayList<String>();
		
		//First Pass: identify fake candidates => a list that should be shortened in later steps
		//  walk through the {First words} x {Second words} Descartes product 
		//    the title is not on the list => discard
		//    the title is on the list & either First or Second word occured once only => add to noFakeList
		//    the title is on the list and both First and Second word occurs more than once => add to fakeCandidates
		for(Map.Entry<String, Integer> eFirst : firstWordCount.entrySet()){
			for(Map.Entry<String, Integer> eSecond : secondWordCount.entrySet()){
				String title = eFirst.getKey() + " " + eSecond.getKey();
				if(titleList.containsKey(title)){
					if(eFirst.getValue() == 1 || eSecond.getValue() == 1){
						noFakeList.add(title);
					} else {
						fakeCandidates.add(title);
					}
				}
			}//next Second word
		}//next First word
		System.out.println("ProblemSolver :: fakeCandidates=" + Util.iterableToString(fakeCandidates, ",") + ", noFakeList=" + Util.iterableToString(noFakeList, ",") );
		
		//Greedy algorithm:
		//  take again the F={set of First words} and the S={set of Second words}
		//  and try to cover them with titles in the following manner:
		//    1. take all titles in noFakeList => that will cover some of the elements in both sets (F and S) unless noFakeList was empty
		//         -- by 'covering' we mean the element is removed from the set
		//    Repeat until both F and S are covered (=empty)
		//      2. take titles from fakeCandidates that cover 1-1 element from both sets
		//      3. take titles from fakeCandidates that cover either an element from F or from S
		
		//Step 1.
		Set<String> F = firstWordCount.keySet();
		Set<String> S = secondWordCount.keySet();
		System.out.println("ProblemSolver :: BEFORE Step 1 : F=" + Util.iterableToString(F, ",") + "   , S=" + Util.iterableToString(S, ",") );
		for(String title : noFakeList){
			String[] sf = title.split(" ");
			F.remove(sf[0]);
			S.remove(sf[1]);
		}
		System.out.println("ProblemSolver :: AFTER Step 1 : F=" + Util.iterableToString(F, ",") + "   , S=" + Util.iterableToString(S, ",") );
		
		//Hopefully both S and F do still have some elements. If both have become empty, there is no fake
		if(F.size()==0 && S.size()==0){
			return Integer.toString( fakeCandidates.size() );
		} else {
			//Step 2.
			ArrayList<String> tempRemovedCandidates = new ArrayList<String>();
			for(String title : fakeCandidates){
				String[] sf = title.split(" ");
				if(F.contains(sf[0]) && S.contains(sf[1])){
					tempRemovedCandidates.add(title);
					F.remove(sf[0]);
					S.remove(sf[1]);
				}
			}//fakeCandidates analysed for Step 2.
			fakeCandidates.removeAll(tempRemovedCandidates);
			
			System.out.println("ProblemSolver :: Step 2 : F=" + Util.iterableToString(F, ",") + "   , S=" + Util.iterableToString(S, ",") 
					           + "\n" + "tempRemovedCandidates=" + Util.iterableToString(tempRemovedCandidates, ",") 
					           + "\n" + "fakeCandidates=" + Util.iterableToString(fakeCandidates, ",")
					           );
			
			//Step 3.
			boolean goOn = (F.size() > 0 || S.size() > 0);
			while( goOn ){
				//remove a candidate if and only when it covers at least an element in any of the sets
				for(String title : fakeCandidates){
					String[] sf = title.split(" ");
					if(F.contains(sf[0]) || S.contains(sf[1])){
						tempRemovedCandidates.add(title);
						F.remove(sf[0]);
						S.remove(sf[1]);
					}
				}//
				fakeCandidates.removeAll(tempRemovedCandidates);
				
				goOn = (F.size() > 0 || S.size() > 0);
				//evaluate cycle var
			}//fakeCandidates analysed for Step 3.
			System.out.println("ProblemSolver :: Step 3 : F=" + Util.iterableToString(F, ",") + "   , S=" + Util.iterableToString(S, ",") 
			           + "\n" + "tempRemovedCandidates=" + Util.iterableToString(tempRemovedCandidates, ",") 
			           + "\n" + "fakeCandidates=" + Util.iterableToString(fakeCandidates, ",")
			           );
			
			return Integer.toString( fakeCandidates.size() );
		}
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		titleList = new HashMap<String,Integer>();
		firstWordCount = new HashMap<String,Integer>();
		secondWordCount = new HashMap<String,Integer>();
		//store raw data
		String[] strarr = inCase.getData();
		rownum = Integer.parseInt(strarr[0]);
		for(int i=1; i<strarr.length; i++){
			addCounter(titleList,strarr[i]);
			String[] words = strarr[i].split(" ");
			addCounter(firstWordCount,words[0]);
			addCounter(secondWordCount,words[1]);
		}
		//ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		
	}
	
	private void addCounter(HashMap<String, Integer> inMap, String inWord){
		int cnt = 0;
		if(inMap.containsKey(inWord)){
			cnt = inMap.get(inWord);
		}
		cnt++;
		inMap.put(inWord, Integer.valueOf(cnt));
	}
	
	private ArrayList<String> getMultipleOccurences(HashMap<String,Integer> inMap){
		ArrayList<String> ret = new ArrayList<String>();
		for(Map.Entry<String, Integer> e : inMap.entrySet()){
			if(e.getValue() > 1) ret.add(e.getKey());
		}
		return ret;
	}
	
}
