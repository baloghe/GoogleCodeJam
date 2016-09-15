package r1b2016.b;

import java.util.ArrayList;
import java.util.HashSet;

public class RootNode {

	private HashSet<TreeNode> children;
	
	public RootNode(){
		init();
	}
	
	public RootNode(TreeNode inStartChild){
		init();
		children.add(inStartChild);
	}
	
	private void init(){
		children = new HashSet<TreeNode>();
	}
	
	public void addChild(TreeNode inChild){
		children.add(inChild);
	}
	
	public HashSet<TreeNode> getChildren(){return children;}

	public ArrayList<ScorePair> DepthWalkOutput(){
		//Collect candidates
		ArrayList<String[]> candidates = new ArrayList<String[]>();
		for(TreeNode n : this.children){
			candidates.addAll(this.recDepthWalk(n));
		}
		//System.out.println("DepthWalkOutput :: candidates.size()=" + candidates.size());
		//Build a sortable collection of them
		ArrayList<ScorePair> ret = new ArrayList<ScorePair>();
		for(String[] a : candidates){
			//System.out.println("DepthWalkOutput :: a={" + a[0] + "," + a[1] + "}");
			ret.add( new ScorePair( a[0] , a[1] ) );
		}
		return ret;
	}
	
	private ArrayList<String[]> recDepthWalk(TreeNode inNode){
		ArrayList<String[]> ret = new ArrayList<String[]>();
		
		if(inNode.hasChildren()){
			for(TreeNode n : inNode.getChildren()){
				ArrayList<String[]> actList = this.recDepthWalk(n);
				for(String[] actCJ : actList){
					actCJ[0] = Integer.toString(inNode.getDigitC()) + actCJ[0];
					actCJ[1] = Integer.toString(inNode.getDigitJ()) + actCJ[1];
					ret.add(actCJ);
					//System.out.println("recDepthWalk :: node=" + inNode + " -> CHILD=" + n + " -> added: {" + actCJ[0] + "," + actCJ[1] + "}");
				}
			}
		} else {
			String[] cj = new String[]{ Integer.toString(inNode.getDigitC()) , Integer.toString(inNode.getDigitJ()) };
			ret.add(cj);
			//System.out.println("recDepthWalk :: node=" + inNode + " -> no children -> added: {" + cj[0] + "," + cj[1] + "}");
		}
		
		return ret;
	}
}
