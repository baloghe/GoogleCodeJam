package r1b2016.b;

import java.util.HashSet;

public class TreeNode {

	private int digitC;
	private int digitJ;
	private HashSet<TreeNode> children;
	private int lead; //down from the root :: 0: C=J, -1: C<J, +1: C>J
	
	public TreeNode(int inDigitC, int inDigitJ){
		digitC = inDigitC;
		digitJ = inDigitJ;
		children = new HashSet<TreeNode>();
		countLead(0);
	}
	
	public TreeNode(int inDigitC, int inDigitJ, int inParentLead){
		digitC = inDigitC;
		digitJ = inDigitJ;
		children = new HashSet<TreeNode>();
		countLead(inParentLead);
	}
	
	private void countLead(int inParentLead){
		if(inParentLead != 0){
			lead = inParentLead;
		} else {
			if(digitC==digitJ){
				lead = 0;
			} else if(digitC < digitJ) {
				lead = -1;
			} else {
				lead = 1;
			}
		}
	}
	
	public void addChild(TreeNode inChild){
		children.add(inChild);
	}
	
	public int getDigitC(){return digitC;}
	public int getDigitJ(){return digitJ;}
	public HashSet<TreeNode> getChildren(){return children;}
	public int getLead(){return lead;}
	
	@Override
	public int hashCode(){
		return 10 * digitC + digitJ;
	}
	
	public boolean hasChildren(){
		return ( children.size() > 0 );
	}
	
	public String toString(){
		return "(" + Integer.toString(digitC) + "," + Integer.toString(digitJ) + ")";
	}
}
