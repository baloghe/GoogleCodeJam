package r1a2015.c;

public class Point2D {
	private long x;
	private long y;
	
	public Point2D(long inX, long inY){
		x = inX;
		y = inY;
	}
	
	public long x(){return x;}
	public long y(){return y;}
	
	public String toString(){
		return "(" + x + "," + y + ")";
	}
	
	public int hashCode(){return this.toString().hashCode();}
	
	public boolean equals(Object inObj){
		return    ((Point2D)inObj).x == this.x
		       && ((Point2D)inObj).y == this.y
		       ;
	}
}
