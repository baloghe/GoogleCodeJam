package r1b2015.b;

public class HouseFlat {

	private int row;
	private int col;
	
	private boolean occupied;
	
	public HouseFlat(int inRow, int inCol){
		row = inRow;
		col = inCol;
		occupied = false;
	}
	
	public HouseFlat occupy(){
		occupied = true;
		return this;
	}
	
	public HouseFlat empty(){
		occupied = false;
		return this;
	}
	
	public int row(){return row;}
	public int col(){return col;}
	public boolean occupied(){return occupied;}
	
	public boolean isNextTo(HouseFlat inFlat){
		return (
				this.row == inFlat.row()
				&& ( ( this.col == (inFlat.col() - 1) )
						|| ( this.col == (inFlat.col() + 1) )
			       )
			   ) || (
			    this.col == inFlat.col()
				 && ( ( this.row == (inFlat.row() - 1) )
					 	 || ( this.row == (inFlat.row() + 1) )
					)
		       );
	}
	
	public String toString(){
		return "(" + row + "," + col + ":" + (occupied ? "Y" : "N") + ")";
	}
	
	public int hashCode(){return this.toString().hashCode();}
	
	public boolean equals(Object obj){
		if(!(obj instanceof HouseFlat)) 
			return false;
		return (this.row == ((HouseFlat)obj).row()
				&& this.col == ((HouseFlat)obj).col()
				);
	}
}

