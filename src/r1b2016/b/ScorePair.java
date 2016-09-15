package r1b2016.b;

public class ScorePair implements Comparable<ScorePair> {

	private int[] C;
	private int[] J;
	private long longC;
	private long longJ;
	
	public ScorePair(int[] inC, int[] inJ){
		C = inC;
		J = inJ;
		
		//convert to long
		longC = Long.parseLong(util.Util.intArrayToString(C, ""));
		longJ = Long.parseLong(util.Util.intArrayToString(J, ""));
	}
	
	public ScorePair(String inCStr, String inJStr){
		C = new int[inCStr.length()];
		J = new int[inJStr.length()];
		for(int i=0; i<inCStr.length(); i++){
			C[i] = Integer.parseInt(inCStr.substring(i, i+1));
		}
		for(int i=0; i<inJStr.length(); i++){
			J[i] = Integer.parseInt(inJStr.substring(i, i+1));
		}
		//convert to long
		longC = Long.parseLong(inCStr);
		longJ = Long.parseLong(inJStr);
	}
	
	public long getC(){
		return this.longC;
	}
	
	public long getJ(){
		return this.longJ;
	}
	
	@Override
	public int compareTo(ScorePair p){
		long plc = p.getC();
		long plj = p.getJ();
		long pdiff = Math.abs(plj - plc);
		
		long thisdiff = Math.abs(longJ - longC);
		
		int ret = 0;		
		if(thisdiff > pdiff){
			ret= 1;
		} else if(thisdiff < pdiff){
			ret = -1;
		} else { //thisdiff == pdiff
			if(longC > plc){
				ret= 1;
			} else if(longC < plc) {
				ret= -1;
			} else { //longC == plc
				if(longJ > plj){
					ret= 1;
				} else if(longJ < plj){
					ret = -1;
				}
			}
		}
		
		/*
		System.out.println(this.toString() + " vs p=" + p.toString() + " :: " 
				  + "thisdiff=" + thisdiff + ", pdiff=" + pdiff
				  + ", longC=" + longC + ", plc=" + plc
				  + ", longJ=" + longJ + ", plj=" + plj
				  + " --> ret=" + ret);
		*/
		return ret;
	}
	
	public String toString(){
		String ret = util.Util.intArrayToString(C, "") + "|" + util.Util.intArrayToString(J, "")
				   + "  diff=" + Math.abs(longJ - longC)
				   ;
		return ret;
	}
	
	public String getSolutionString(){
		return util.Util.intArrayToString(C, "") + " " + util.Util.intArrayToString(J, "");
	}
	
}
