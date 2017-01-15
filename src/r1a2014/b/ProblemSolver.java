package r1a2014.b;

import java.util.ArrayList;
import java.util.HashSet;

import util.RawInput;
import util.Util;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	int N;
	int[][] rawConnections;
	int globalMIN;
	
	/**
	 * constructor. The expected size of the problem might require different approaches, therefore runmode is required as a parameter   
	 * @param inRunMode expected RUNMODE_SMALL or RUNMODE_LARGE (see ProblemSolution)
	 */
	public ProblemSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	
	@Override
	public String solveCase(RawInput inCase) {
		//init(inCase);
		// TODO Auto-generated method stub
		return solveQuadratic(inCase);
	}
	
	public String solveBF(RawInput inCase){
		init(inCase);
		
		Graph g = new Graph(N, rawConnections);
		/*	dropping an inner node from a tree would lead to an isolated graph => it is much enough to drop nodes from the front...
			recursively:
				1) drop one Front node at a time
				2) inspect the resulting graph if it is a full binary tree. If not, go back to 1)
			select the biggest remaining tree in the end
		*/
		globalMIN = N+1;
		int cntNodeToDrop = recDropNodeUntilFullBTree(g, 0);
		
		return Integer.toString(cntNodeToDrop);
	}
	
	public String solveBF2(RawInput inCase){
		init(inCase);
		
		Graph g = new Graph(N, rawConnections);
		if(g.isFullBinaryTree())
			return "0";
		/* Google solution:
		 * take all possible combinations of nodes ranging from 1 to N-1
		 * 	--> at least 1 node should remain, so N nodes cannot be dropped. Moreover, as g is not full binary tree, at least 1 node should be dropped
		 * in each case: inspect the resulting tree if it is full binary tree or not
		 * select the biggest remaining tree in the end
		 */
		globalMIN = N+1;
		HashSet<HashSet<Integer>> sets = getAllNodeCombinations();
		for(HashSet<Integer> st : sets){
			int cntToBeDropped = st.size();
			if(cntToBeDropped < globalMIN){
				Graph tmp = g.removeNodes(st);
				if(tmp.isFullBinaryTree()){
					globalMIN = cntToBeDropped;
					//System.out.println("solveBF2 :: new min=" + globalMIN + " for dropSet=" + st);
				}
			}
		}//next nodeSet
		
		return Integer.toString(globalMIN);
	}
	
	public String solveQuadratic(RawInput inCase){
		init(inCase);
		
		Graph g = new Graph(N, rawConnections);
		/* Google solution:
		 * Starting from an arbitrary node: build the largest full binary tree you can, by using the available nodes and edges
		 * Try it starting from all nodes
		 * the biggest tree built yields the least number of nodes to be dropped 
		 */
		globalMIN = N+1;
		for(int root=1; root<=N; root++){
			int nn = g.getMaxFullBTSize(root);
			if(N - nn < globalMIN){
				globalMIN = N - nn;
			}
		}//next root
		
		return Integer.toString(globalMIN);
	}
	
	public String printMaxDegree(RawInput inCase){
		init(inCase);
		Graph g = new Graph(N, rawConnections);
		return Integer.toString(g.getMaxDegree());
	}
	
	private int recDropNodeUntilFullBTree(Graph inG, int inDroppedNum){
		if(inDroppedNum > globalMIN){
			return N+1;
		}
		
		if(inG.isFullBinaryTree()){
			globalMIN = inDroppedNum;
			return inDroppedNum;
		}
		
		int minToDrop = N+1;
		Graph gChosen=null;
		HashSet<Integer> front = inG.getFront();
		for(Integer nd : front){
			Graph oneDropped=inG.removeNode(nd);
			//System.out.println("rec :: dropped " + nd + " -> " + oneDropped);
			int oneDrop = recDropNodeUntilFullBTree(oneDropped, inDroppedNum+1);
			if(oneDrop < minToDrop){
				minToDrop = oneDrop;
				gChosen = oneDropped;
			}
		}
		
		//System.out.println("rec :: inDroppedNum=" + inDroppedNum + " + chosen=" + gChosen + " -> minToDrop=" + minToDrop);
		
		return minToDrop;
	}
	
	public HashSet<HashSet<Integer>> getAllNodeCombinations(){
		HashSet<HashSet<Integer>> ret = new HashSet<HashSet<Integer>>();
		
		int lpow = Util.twoPow(N);
		for(int i=1; i<lpow; i++){
			Boolean[] sw = Util.longToBoolArray((long)i, N);
			HashSet<Integer> tmpSet = new HashSet<Integer>();
			for(int j=0; j<sw.length; j++){
				if(sw[j]){
					tmpSet.add(j);
				}
			}
			ret.add(tmpSet);
		}
		
		return ret;
	}
	
	
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		N = Integer.parseInt(inCase.getData()[0]);
		rawConnections = new int[N-1][2];
		for(int i=1; i<N; i++){
			ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[i], null);
			rawConnections[i-1][0] = xx.get(0);
			rawConnections[i-1][1] = xx.get(1);
		}
		Graph g = new Graph(N, rawConnections);
		System.out.println("init :: N=" + N + ", " + (N-1) + "th edge: " + rawConnections[N-2][0] + " <-> " + rawConnections[N-2][1] + "  -> max(deg)=" + g.getMaxDegree());
	}

}
