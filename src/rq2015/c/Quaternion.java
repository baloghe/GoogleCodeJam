package rq2015.c;

import java.util.ArrayList;

public class Quaternion {

	public static int E=0;
	public static int I=1;
	public static int J=2;
	public static int K=3;
	
	private int type;
	private int sign;
		
	public Quaternion(int inType) {
		type = inType;
		sign = 1;
	}
	
	public Quaternion(int inType, int inSign) {
		type = inType;
		sign = inSign >= 0 ? 1 : -1;
	}
	
	public int getType(){return type;}
	public int getSign(){return sign;}
	
	public Quaternion setSign(int inSign){
		sign = inSign >= 0 ? 1 : -1;
		return this;
	}
	
	private Quaternion multSign( int inSign ){
		return this.setSign( this.sign * inSign );
	}
	
	public Quaternion copy(){
		return new Quaternion(this.type, this.sign);
	}
	
	public Quaternion mult(Quaternion inOther){
		Quaternion ret = null;
		if(this.type == Quaternion.E){
			ret = inOther.copy();
		} else if(this.type == Quaternion.I) {
			if(inOther.getType() == Quaternion.E) ret = this.copy();
			else if(inOther.getType() == Quaternion.I) ret = new Quaternion( Quaternion.E , -1 );
			else if(inOther.getType() == Quaternion.J) ret = new Quaternion( Quaternion.K , +1 );
			else if(inOther.getType() == Quaternion.K) ret = new Quaternion( Quaternion.J , -1 );
		} else if(this.type == Quaternion.J) {
			if(inOther.getType() == Quaternion.E) ret = this.copy();
			else if(inOther.getType() == Quaternion.I) ret = new Quaternion( Quaternion.K , -1 );
			else if(inOther.getType() == Quaternion.J) ret = new Quaternion( Quaternion.E , -1 );
			else if(inOther.getType() == Quaternion.K) ret = new Quaternion( Quaternion.I , +1 );
		} else if(this.type == Quaternion.K) {
			if(inOther.getType() == Quaternion.E) ret = this.copy();
			else if(inOther.getType() == Quaternion.I) ret = new Quaternion( Quaternion.J , +1 );
			else if(inOther.getType() == Quaternion.J) ret = new Quaternion( Quaternion.I , -1 );
			else if(inOther.getType() == Quaternion.K) ret = new Quaternion( Quaternion.E , -1 );
		}
		return ret.multSign( this.sign * inOther.getSign() );
	}
	
	public String toString(){
		String ret = (sign >= 0 ? "" : "-");
		if(type == Quaternion.E) ret += "1";
		else if(type == Quaternion.I) ret += "i";
		else if(type == Quaternion.J) ret += "j";
		else if(type == Quaternion.K) ret += "k";
		return ret;
	}
	
	public int hashCode(){return this.toString().hashCode();}
	
	public boolean equals(Object obj){
		if( !(obj instanceof Quaternion) ) return false;
		return (this.hashCode() == obj.hashCode() );
	}
	
	public static Quaternion multiplyChain(ArrayList<Quaternion> inLst){
		Quaternion ret = new Quaternion(Quaternion.E);
		for(Quaternion q : inLst){
			//String str = "mult :: ret=" + ret + ", q=" + q;
			ret = ret.mult(q);
			//System.out.println(str + "  -> ret*q=" + ret);
		}
		return ret;
	}
	
	public static Quaternion parseList(String inStr){
		Quaternion q = inStr.startsWith("-") ? new Quaternion(Quaternion.E,-1) :  new Quaternion(Quaternion.E);
		
		int startPos = 0;
		if(q.getSign() < 0) startPos++;
		
		char[] tmp = inStr.toCharArray();
		for(int i=startPos; i<tmp.length; i++){
			Quaternion act = null;
			if( String.valueOf(tmp[i]).equalsIgnoreCase("1") ) act = new Quaternion(Quaternion.E);
			else if( String.valueOf(tmp[i]).equalsIgnoreCase("I") ) act = new Quaternion(Quaternion.I);
			else if( String.valueOf(tmp[i]).equalsIgnoreCase("J") ) act = new Quaternion(Quaternion.J);
			else if( String.valueOf(tmp[i]).equalsIgnoreCase("K") ) act = new Quaternion(Quaternion.K);
			//String s = q + "*" + act;
			q = q.mult(act);
			//System.out.println(s + "=" + q);
		}
		return q;
	}
}
