package rq2016;

import java.util.Vector;

public class PancakeRevengeStack {

	private Vector<String> stack;
	
	public PancakeRevengeStack(String inStr){
		stack = new Vector<String>();
		char[] chrs = inStr.toCharArray();
		for(char c : chrs){
			String key = String.valueOf(c);
			stack.add(key);
		}
	}
	
	public PancakeRevengeStack copy(){
		return new PancakeRevengeStack(this.toString());
	}
	
	public void flipStackPart(int inMaxIdx){
		//stop at the end of the Vector
		int toIdx = inMaxIdx;
		if(inMaxIdx > stack.size() || inMaxIdx <= 0)
			toIdx = stack.size();
		
		//flip the starting part if the Vector into a temp Vector
		Vector<String> temp = new Vector<String>();
		for(int i=0; i<toIdx; i++){
			String s = stack.remove(0);
			if(s.equalsIgnoreCase("+")){
				s = "-";
			} else if(s.equalsIgnoreCase("-")){
				s = "+";
			}
			temp.add(0, s);
		}
		
		//pour the content of temp into the destination ('stackwise')
		stack.addAll(0, temp);
	}
	
	public boolean isOK(){
		boolean ret = true;
		for(String s : stack){
			if(s.equalsIgnoreCase("-")){
				ret = false;
			}
		}
		return ret;
	}
	
	public String toString(){
		String ret = "";
		for(String s : stack){
			ret += s;
		}
		return ret;
	}
	
	public int size(){
		return stack.size();
	}
	
	public int hashCode(){
		return this.toString().hashCode();
	}
	
	public boolean equals(Object o){
		return this.toString().equalsIgnoreCase(o.toString());
	}
	
}
