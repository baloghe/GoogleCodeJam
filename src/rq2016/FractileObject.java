package rq2016;

import java.util.HashSet;

public class FractileObject {

	private String origin;
	
	public FractileObject(String inOrigString){
		origin = inOrigString;
	}
	
	public String getBasePattern(){
		return origin;
	}
	
	public String getResultPattern(int inIterNum){
		String ret = origin;
		
		String rpl_G = origin.replace('L', 'G');
		
		for(int i=0; i<inIterNum; i++){
			ret = ret.replaceAll("G", rpl_G);
			ret = ret.replaceAll("L", origin);
		}
		
		return ret;
	}
	
	public static HashSet<FractileObject> buildFractileObjects(int inLen){
		
		HashSet<FractileObject> ret = new HashSet<FractileObject>();
		String pad = "";
		for(int i=0; i<inLen; i++){
			pad += "0";
		}
		
		for(int i=0; i< (int)Math.pow(2.0, (double)inLen); i++){
			//String s = String.format("%010d", Integer.toString(i, 2)  );
			String s = Integer.toString(i,2);
			String s2 = pad.substring(s.length()) + s;
			s2 = s2.replaceAll("0", "G").replaceAll("1", "L");
			ret.add( new FractileObject(s2) );
		}
		
		return ret;
	}
}
