package r1b2016.c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Taken from http://www.geeksforgeeks.org/maximum-bipartite-matching/
 * with slight modifications
 *
 */
public class BipartiteGraphMatcher {

	private HashSet<GraphNode> vset1;
	private HashSet<GraphNode> vset2;
	private HashMap<String, GraphNode> labToNode1;
	private HashMap<String, GraphNode> labToNode2;
	private HashMap<String,Boolean> edges;
	
	private HashMap<GraphNode,GraphNode> matchResult;
	
	public BipartiteGraphMatcher(ArrayList<String[]> inEdgeList){
		
		vset1 = new HashSet<GraphNode>();
		vset2 = new HashSet<GraphNode>();
		labToNode1 = new HashMap<String, GraphNode>();
		labToNode2 = new HashMap<String, GraphNode>();
		edges = new HashMap<String,Boolean>();
		
		int v1=0, v2=0;
		for(String[] a : inEdgeList){
			//Add first word of the title to the nodeset
			if(!labToNode1.containsKey(a[0])){
				v1++;
				GraphNode n = new GraphNode( v1, a[0] );
				vset1.add(n);
				labToNode1.put(a[0], n);
			}
			//Add second word of the title to the nodeset
			if(!labToNode2.containsKey(a[1])){
				v2++;
				GraphNode n = new GraphNode( v2, a[1] );
				vset2.add(n);
				labToNode2.put(a[1], n);
			}
			//Add edge to edgeset
			String key = Integer.toString(labToNode1.get(a[0]).getOrderNum()) 
					+ "|" + Integer.toString(labToNode2.get(a[1]).getOrderNum());
			edges.put(key, Boolean.TRUE);
		}
		/*
		System.out.println("BipartiteGraphMatcher :: vset1=" + vset1.size() + ", vset2=" + vset2.size() + ", edges=" + edges.size());
		System.out.println("  vset1=" + util.Util.iterableToString(vset1, ","));
		System.out.println("  edges=" + util.Util.iterableToString(edges.entrySet(), ","));
		*/
	}
	
	public HashMap<GraphNode,GraphNode> match(){
		//tells if a vertex in vset2 
		matchResult = new HashMap<GraphNode,GraphNode>();
		
		//for all vertex in vset1:
		for(GraphNode n : vset1){
			//set every vertex in vset2 as unseen (by actual vertex n from vset1)
			HashMap<GraphNode,Boolean> seen = new HashMap<GraphNode,Boolean>();
			//see if it can be matched by anyone from vset2
			bpm(n, seen);
		}
		//at the end, matchResult should contain a vset2 -> vset1 match... We have to turn it back!
		HashMap<GraphNode,GraphNode> ret = new HashMap<GraphNode,GraphNode>();
		for(Map.Entry<GraphNode,GraphNode> e : matchResult.entrySet()){
			GraphNode v1 = e.getValue();
			GraphNode v2 = e.getKey();
			ret.put(v1, v2);
		}
		return ret;
	}
	
	public boolean bpm(GraphNode inVfromSet1, HashMap<GraphNode,Boolean> inSeen){
		//System.out.println("bpm called with " + inVfromSet1);
		//for each potential vertex in vset2:
		for(GraphNode v : vset2){
			//System.out.println("  edge to " + v + ": " + getNodeAdjacency(inVfromSet1, v) + ", isSeen:" + isSeen(v, inSeen));			
			if( getNodeAdjacency(inVfromSet1, v) && (!isSeen(v, inSeen)) ){
				inSeen.put(v, Boolean.TRUE);
				boolean b = false;
				if(matchResult.containsKey(v)){
					GraphNode tmpactres = matchResult.get(v);
					//recursive call!
					b = bpm(tmpactres, inSeen);
				} else {//v was NOT present in matchResult
					b = true;
				}
				if(b){
					matchResult.put(v, inVfromSet1);
					//matchResult.put(inVfromSet1, v);
					return true;
				}//recursive bpm() returned false
			}//there is an edge (inVfromSet1,v) and v has not seen yet
		}//next vertex from vset2
		return false;
	}
	
	public String getEdgeKey(GraphNode inFromNode, GraphNode inToNode){
		return Integer.toString(inFromNode.getOrderNum()) + "|" + Integer.toString(inToNode.getOrderNum());
	}
	
	public Boolean getNodeAdjacency(GraphNode inFromEdge, GraphNode inToEdge){
		String key = getEdgeKey(inFromEdge, inToEdge);
		if(!edges.containsKey(key)) return false;
		return edges.get(key);
	}
	
	public Boolean getNodeAdjacency(int inFromNode, int inToNode){
		String key = Integer.toString(inFromNode) + "|" + Integer.toString(inToNode);
		if(!edges.containsKey(key)) return false;
		return edges.get(key);
	}
	
	public boolean isSeen(GraphNode inNode, HashMap<GraphNode,Boolean> inSeen){
		if(!inSeen.containsKey(inNode)) return false;
		return inSeen.get(inNode);
	}
	
}
