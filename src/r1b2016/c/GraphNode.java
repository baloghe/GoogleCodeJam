package r1b2016.c;

public class GraphNode implements Comparable<GraphNode> {
	private int orderNum;
	private String label;
	
	public GraphNode(int inOrdNum, String inLabel){
		orderNum = inOrdNum;
		label = inLabel;
	}
	
	public int getOrderNum(){return orderNum;}
	public String getLabel(){return label;}
	
	public String toString(){return label;}
	public int hashCode(){return label.hashCode();}

	@Override
	public int compareTo(GraphNode o) {
		if(this.getLabel().equalsIgnoreCase(o.getLabel())){
			return 0;
		} else return 1;
	}
	
	public boolean equals(Object s){
		return this.toString().equalsIgnoreCase(s.toString());
	}
	
}
