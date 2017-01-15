package r1a2014.b;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Graph {

	private HashSet<Integer> nodes;
	private HashSet<Edge> edges;
	private HashMap<Integer,HashSet<Integer>> children;
	
	public Graph(int inNodeNum, int[][] inEdges){
		
		nodes = new HashSet<Integer>();
		for(int i=1; i<=inNodeNum; i++){
			nodes.add(i);
		}
		edges = new HashSet<Edge>();
		children = new HashMap<Integer,HashSet<Integer>>();
		for(int[] e : inEdges){
			//edges
			edges.add(new Edge(e[0],e[1]));
			//children
			buildChildren(e[0],e[1]);
			buildChildren(e[1],e[0]);
		}
	}
	
	private void buildChildren(int inParentId, int inChildId){
		HashSet<Integer> tmp = null;
		if(children.containsKey(inParentId)){
			tmp = children.get(inParentId);
		} else {
			tmp = new HashSet<Integer>();
		}
		tmp.add(inChildId);
		children.put(inParentId, tmp);
	}
	
	public Graph(HashSet<Integer> inNodes, HashSet<Edge> inEdges){
		nodes = inNodes;
		edges = inEdges;
	}
	
	private Graph deepCopy(){
		HashSet<Integer> nodeCopy = new HashSet<Integer>();
		for(Integer i : nodes){
			nodeCopy.add(i.intValue());
		}
		HashSet<Edge> edgeCopy = new HashSet<Edge>();
		for(Edge e : edges){
			edgeCopy.add(new Edge(e.getFrom(),e.getTo()));
		}
		return new Graph(nodeCopy, edgeCopy);
	}
	
	public Graph removeNode(int inNodeId){
		Graph ret = this.deepCopy();
		//remove node
		ret.nodes.remove(inNodeId);
		//remove connected edges
		HashSet<Edge> edgesToRem = new HashSet<Edge>();
		for(Edge e : ret.edges){
			if(e.isConnectedTo(inNodeId)){
				edgesToRem.add(e);
			}
		}//next
		ret.edges.removeAll(edgesToRem);
		
		return ret;
	}
	
	public Graph removeNodes(HashSet<Integer> inNodeIds){
		Graph ret = this.deepCopy();
		//remove node
		ret.nodes.removeAll(inNodeIds);
		//remove connected edges
		HashSet<Edge> edgesToRem = new HashSet<Edge>();
		for(Edge e : ret.edges){
			for(Integer nd : inNodeIds){
				if(e.isConnectedTo(nd)){
					edgesToRem.add(e);
				}
			}
		}//next
		ret.edges.removeAll(edgesToRem);
		
		return ret;
	}
	
	public boolean isFullBinaryTree(){
		//trivial case: single-noded tree is full
		if(nodes.size()==1)
			return true;
		//Full => must have odd number of edges
		if(nodes.size() % 2 != 1)
			return false;
		//EdgeNum == NodeNum - 1
		if(nodes.size() - edges.size() != 1)
			return false;
		//degree of any edge can be 1, 2 or 3, but 2 occurs once only
		HashMap<Integer,Integer> chkDeg = getNodeDegrees();
		int cntDeg2 = 0;
		boolean deg13Violated = false;
		for(Integer i : chkDeg.values()){
			if(i==2)
				cntDeg2++;
			if(i>3){
				deg13Violated = true;
				break;
			}
		}
		return (cntDeg2==1) && (!deg13Violated);
	}
	
	public static HashMap<Integer, Integer> incCounter(HashMap<Integer, Integer> inMap, Integer inKey){
		int cnt = 0;
		if(inMap.containsKey(inKey)){
			cnt = inMap.get(inKey);
		}
		cnt++;
		inMap.put(inKey, cnt);
		return inMap;
	}
	
	public HashMap<Integer,Integer> getNodeDegrees(){
		HashMap<Integer,Integer> ret = new HashMap<Integer,Integer>();
		for(Edge e : edges){
			incCounter(ret,e.getFrom());
			incCounter(ret,e.getTo());
		}
		return ret;
	}
	
	public HashSet<Integer> getFront(){
		HashSet<Integer> ret = new HashSet<Integer>();
		HashMap<Integer,Integer> degs = getNodeDegrees();
		for(Entry<Integer,Integer> e : degs.entrySet()){
			if(e.getValue()==1)
				ret.add(e.getKey());
		}
		return ret;
	}
	
	public int getMaxDegree(){
		HashMap<Integer,Integer> degs = getNodeDegrees();
		int ret = 0;
		//Integer node = null;
		for(Entry<Integer,Integer> e : degs.entrySet()){
			if(e.getValue() > ret){
				ret = e.getValue();
			}
		}
		return ret;
	}
	
	public HashSet<Integer> getChildren(int inNodeId){
		return children.get(inNodeId);
	}
	
	public int getNodeNum(){
		return nodes.size();
	}
	
	public int getMaxFullBTSize(int inRootId){
		return recTreeSize(-1, inRootId);
	}
	
	private int recTreeSize(int inParentId, int inRootId){
		//children := neighbors except for Parent
		HashSet<Integer> chn = new HashSet<Integer>();
		HashSet<Integer> xxx = children.get(inRootId);
		if(xxx==null)
			return 1;
		for(Integer id : xxx){
			if(id != inParentId)
				chn.add(id);
		}
		Integer[] c = chn.toArray(new Integer[0]);
		//System.out.println("recTreeSize :: chn=" + chn);
		
		if(chn.size()<=1) //no children or a single one => it is a full binary tree in itself, without the eventual child
			return 1;
		
		if(c.length==2){ //2 children => it is a root of a potential full binary tree
			return 1 + recTreeSize(inRootId, c[0]) + recTreeSize(inRootId, c[1]);
		}
		
		//in any other case, the two children having the biggest subtrees should be chosen
		int maxs1 = 0, maxs2 = 0;
		for(int i=0; i<c.length-2; i++){
			for(int j=i+1; j<c.length-1; j++){
				int tmps1 = recTreeSize(inRootId, c[i]);
				int tmps2 = recTreeSize(inRootId, c[j]);
				int[] tmparr = new int[]{maxs1,maxs2,tmps1,tmps2};
				Arrays.sort(tmparr);
				maxs1 = tmparr[tmparr.length-1];
				maxs2 = tmparr[tmparr.length-2];
			}//next j
		}//next i
		return 1 + maxs1 + maxs2;
	}
	
	@Override
	public String toString(){
		return nodes.toString() + " :: " + edges.toString(); 
	}
}
