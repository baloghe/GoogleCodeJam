package r1a2014.b;

public class Edge {

	private Integer from;
	private Integer to;
	
	public Edge(int inFrom, int inTo){
		to = (inFrom > inTo ? inFrom : inTo);
		from = (inFrom > inTo ? inTo : inFrom);
	}
	
	public int getFrom(){
		return from;
	}
	public int getTo(){
		return to;
	}
	
	public boolean isConnectedTo(Integer inNode){
		return (from==inNode || to==inNode);
	}
	
	@Override
	public String toString(){
		return Integer.toString(from) + "-" + Integer.toString(to);
	}
	
	@Override
	public int hashCode(){
		return (17 * 31 + from) * 31 + to;
	}
	
	@Override
	public boolean equals(Object o){
		if(o==this)
			return true;
		if(!(o instanceof Edge))
			return false;
		Edge e = (Edge)o;
		return (this.from==e.from && this.to==e.to)
			|| (this.to==e.from && this.from==e.to)
			;
	}
}
