package r1a2015.c;

public class Point2D implements Comparable<Point2D>{
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

	@Override
	public int compareTo(Point2D o) {
		if(   this.y < o.y()
		   || (this.y == o.y() && this.x < o.x() ) ) return -1;
		else if(   this.y == o.y() 
				&& this.x == o.x() ) return 1;
		else return 1;
	}
	
}
