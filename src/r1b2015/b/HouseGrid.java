package r1b2015.b;

public class HouseGrid {

	private int row;
	private int col;
	private HouseFlat[][] grid;
	private int[][] numNeighbours;
	
	private HouseFlat minNeighboursFlat;
	
	public HouseGrid(int inRow, int inCol){
		row = inRow;
		col = inCol;
		numNeighbours = new int[row][col];
		grid = new HouseFlat[row][col];
		for(int r=0; r<row; r++){
			for(int c=0; c<col; c++){
				grid[r][c] = new HouseFlat(r,c);
				numNeighbours[r][c] = 0;
			}
		}
		minNeighboursFlat = grid[0][0];
	}
	
	public void occupyFlat(int inRow, int inCol){
		grid[inRow][inCol].occupy();
		updateNumNeighbours();
	}
	
	public void addResidentToMin(){
		minNeighboursFlat.occupy();
		updateNumNeighbours();
	}
	
	private void updateNumNeighbours(){
		int minNN = 5;
		//System.out.println("updateNumNeighbours()");
		for(int r = 0; r < row; r++){
			for(int c = 0; c < col; c++){
				int nn = 0;
				if(c>0) nn += (grid[r][c-1].occupied() ? 1 : 0);      //left
				if(c<col-1) nn += (grid[r][c+1].occupied() ? 1 : 0);  //right
				if(r>0) nn += (grid[r-1][c].occupied() ? 1 : 0);      //up
				if(r<row-1) nn += (grid[r+1][c].occupied() ? 1 : 0);  //down
				numNeighbours[r][c] = nn;
				//System.out.println("  " + r + "," + c + ": " + nn);
				//minSearch on number of neighbours
				if(nn < minNN && !(grid[r][c].occupied())){
					minNN = nn;
					minNeighboursFlat = grid[r][c];
					//System.out.println("    new MIN: " + minNeighboursFlat + " -> minNN=" + minNN);
				}
			}
		}
	}
	
	public String occupiedToString(){
		String ret = "";
		for(int r = 0; r < row; r++){
			String srow = "";
			for(int c = 0; c < col; c++){
				srow += (grid[r][c].occupied() ? "X " : "0 ");
			}
			ret += (srow + "\n");
		}
		return ret;
	}
	
	public String numNeighboursToString(){
		String ret = "";
		for(int r = 0; r < row; r++){
			String srow = "";
			for(int c = 0; c < col; c++){
				srow += (numNeighbours[r][c] + " ");
			}
			ret += (srow + "\n");
		}
		return ret;
	}
	
	public int getGridLoudness(){
		int ret = 0;
		for(int r = 0; r < row; r++){
			for(int c = 0; c < col; c++){
				ret += (grid[r][c].occupied() ? numNeighbours[r][c] : 0);
			}
		}
		return (ret / 2);
	}
}
