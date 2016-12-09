package r1c2015.a;


/**
 * Game state object for Google Code Jam 2015 / Round 1C / Problem A: Brattleship
 * 
 * An Battleship game is played with a single horizontal ship. ROOT guesses a cell. OPPONENT might move the ship before reporting whether it was a HIT or a MISS.
 * However, OPPONENT can only move the ship to a position which conforms to previous guesses and their reported results.
 * The game ends when the ship is sunk (== number of reported HITs equals to the ship length).
 */
public final class GcjGameState {

	/* board cell values:
	 *   0  at startup
	 *   1  for guess answered as HIT
	 *   2  for guess answered as MISS
	 *   3  for guess still unanswered
	 */
	private int[][] table;
	private int shipLen;
	private int rowNum;
	private int colNum;
	private int shipRow;
	private int shipCol;
	
	
	/**
	 * 1 by inColNum board and a ship with given inShipLen
	 * @param inColNum number of board columns
	 * @param inShipLen length of ship
	 */
	public GcjGameState(int inColNum, int inShipLen){
		table = new int[1][inColNum];
		rowNum = 1;
		colNum = inColNum;
		shipLen = inShipLen;
		shipRow = 0;
		shipCol = 0;
	}
	
	/**
	 * inRowNum by inColNum board and a ship with given inShipLen
	 * @param inRowNum number of board rows
	 * @param inColNum number of board columns
	 * @param inShipLen length of ship
	 */
	public GcjGameState(int inRowNum, int inColNum, int inShipLen){
		table = new int[inRowNum][inColNum];
		rowNum = inRowNum;
		colNum = inColNum;
		shipLen = inShipLen;
		shipRow = 0;
		shipCol = 0;
	}
	
	/**
	 * static constructor
	 * @param inTable board
	 * @param inShipLen length of ship
	 * @param inShipR ship position (row)
	 * @param inShipC ship position (column)
	 * @return new GcjGameState instance
	 */
	public static GcjGameState generateFromTable(int[][] inTable, int inShipLen, int inShipR, int inShipC){
		int rn = inTable.length;
		int cn = inTable[0].length;
		GcjGameState ret = new GcjGameState(rn,cn,inShipLen);
		ret.table = inTable;
		ret.shipLen = inShipLen;
		ret.shipRow = inShipR;
		ret.shipCol = inShipC;
		return ret;
	}
	
	/**
	 * set value of a cell on the board (=guess result)
	 * @param inR row position of the cell
	 * @param inC column position of the cell
	 * @param inVal value to be set
	 * @return new GcjGameState instance cloned from THIS, cell value set
	 */
	public GcjGameState setCell(int inR, int inC, int inVal){
		int[][] tbl = tableDeepCopy();
		tbl[inR][inC] = inVal;
		//System.out.println("setCell :: orig["+inR+","+inC+"]="+table[inR][inC]+" vs cloned["+inR+","+inC+"]="+tbl[inR][inC]);
		return GcjGameState.generateFromTable(tbl, this.shipLen, this.shipRow, this.shipCol);
	}
	
	private int[][] tableDeepCopy(){
		int[][] ret = new int[rowNum][colNum];
		for(int r=0; r<rowNum; r++)
			for(int c=0; c<colNum; c++)
				ret[r][c] = table[r][c];
		return ret;
	}
	
	public int[] getLastUnansweredGuessPos(){
		int[] ret = new int[]{-1,-1};
		
		for(int r=0; r<rowNum; r++)
			for(int c=0; c<colNum; c++)
				if(table[r][c]==3){
					ret[0] = r;
					ret[1] = c;
					return ret;
				}
		
		return ret;
	}
	
	/**
	 * set ship position on the board
	 * @param inR new row position of the ship 
	 * @param inC new column position of the ship
	 * @return if new ship position is valid: new GcjGameState instance cloned from THIS, ship repositioned; otherwise: NULL 
	 */
	public GcjGameState setShip(int inR, int inC){
		int[][] tbl = tableDeepCopy();
		if(this.shipPositionValid(inR, inC)){
			return GcjGameState.generateFromTable(tbl, this.shipLen, inR, inC);
		} else return null;
	}
	
	/**
	 * inspect a cell whether it can or cannot be a HIT (taking into account that our little brother is able to move the ship as long as new ship position conforms with previous guesses made)
	 * @param inR row position of the cell
	 * @param inC column position of the cell
	 * @return 0 if both a HIT and a MISS can occur; 1 if it can only be a MISS; 2 if it can only be a HIT; -1 if the ship cannot be placed in a valid position on the board
	 */
	public int guess(int inR, int inC){
		int ret = -1;
		boolean canBeHIT = false;
		boolean canBeMISS = false;
		for(int r=0; r<rowNum; r++){
			for(int c=0; c<colNum-shipLen+1; c++){
				if(shipPositionValid(r,c)){
					//first valid ship position found => at least the guess will be either a HIT or a MISS
					if(ret<0) ret = 0;
					
					//check if the guess overlaps with the ship
					if(r==inR && c <= inC && inC <= c+shipLen-1){
						canBeHIT = true;
					} else {
						canBeMISS = true;
					}
					//System.out.println("guess :: r=" + r + ", c=" + c + ", canBeHIT=" + canBeHIT + ", canBeMISS=" + canBeMISS);
				}//valid ship position found
			}//next c
		}//next r
		if(ret >= 0 && canBeHIT && (!canBeMISS)) ret = 1;
		else if(ret >= 0 && canBeMISS && (!canBeHIT)) ret = 2;
		
		return ret;
	}
	
	public boolean isHit(int inR, int inC){
		return shipRow==inR && shipCol <= inC && inC < (shipCol+shipLen);
	}
	
	/**
	 * tells if a board cell has been guessed already
	 * @param inR row position of the cell
	 * @param inC column position of the cell
	 * @return TRUE if a guess has been made so far; FALSE otherwise
	 */
	public boolean guessedAlready(int inR, int inC){
		return (table[inR][inC] > 0);
	}
	
	/**
	 * a horizontal ship CAN HAVE its first cell at (inR, inC) IF
	 *   1) the ship fits into the board, that is: cell inC+ShipLen-1 <= colNum 
	 *   2) all cells in range [(inR, inC) .. (inR, inC+ShipLen-1)] are either a HIT or have not been guessed so far
	 * @param inR row position of the cell
	 * @param inC column position of the cell
	 * @return TRUE if it is a valid ship position; FALSE otherwise
	 */
	public boolean shipPositionValid(int inR, int inC){
		//System.out.println("shipPositionValid :: 1st=" + shipFitsBoard(inR, inC) + ", 2nd=" + shipOverlapMiss(inR, inC) + ", 3rd=" + freeCellOverlapHit(inR, inC) );
		return    shipFitsBoard(inR, inC)
		       && (!shipOverlapMiss(inR, inC))
		       && (!freeCellOverlapHit(inR, inC))
		       ;
	}
	
	private boolean shipFitsBoard(int inShipR, int inShipC){
		return colNum > (inShipC+shipLen-1);
	}
	
	private boolean shipOverlapMiss(int inShipR, int inShipC){
		for(int c=inShipC; c<=inShipC+shipLen-1; c++){
			if( table[inShipR][c] == 2 ) return true;
		}
		return false;
	}
	
	private boolean freeCellOverlapHit(int inShipR, int inShipC){
		for(int r=0; r<rowNum; r++){
			for(int c=0; c<colNum; c++){
				if(   table[r][c]==1  //it is a HIT 
				   && (   r!=inShipR      //ship is on a different row
				       || (c < inShipC || c >= (inShipC+shipLen) ) //column is out of ship column range
				      )
				  ) return true;
			}//next c
		}//next r
		return false;
	}
	
	/**
	 * get value of a board cell
	 * @param inRowNum row position of the cell
	 * @param inColNum column position of the cell
	 * @return cell value
	 */
	public int getCellValue(int inRowNum, int inColNum){
		return table[inRowNum][inColNum];
	}
	
	public int getRowNum(){return rowNum;}
	public int getColNum(){return colNum;}
	public int getShipLen(){return shipLen;}
	
	
	public String toString(){
		String ret = "";
		for(int c=0; c<colNum; c++){
			String s = "_";
			if(shipRow == 0 && c >= shipCol && c < (shipCol + shipLen) ){
				s = "S";
			}
			ret += ( (table[0][c]==0 ? s : cellToString(table[0][c])) );
		}//next c
		for(int r=1; r<rowNum; r++){
			ret += "\n";
			for(int c=0; c<colNum; c++){
				String s = "_";
				if(shipRow == r && c >= shipCol && c < (shipCol + shipLen) ){
					s = "S";
				}
				ret += ( (table[r][c]==0 ? s : cellToString(table[r][c])) );
			}//next c
		}//next r
		return ret;
	}
	
	private String cellToString(int cv){
		if(cv==1) return "H"; 
		else if(cv==2)return "M";
		else return "?";
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof GcjGameState)) return false;
		
		int[][] objt = ((GcjGameState)obj).table;
		int ro = objt.length;
		int co = objt[0].length;
		int sl = ((GcjGameState)obj).getShipLen();
		int sr = ((GcjGameState)obj).shipRow;
		int sc = ((GcjGameState)obj).shipCol;
		
		//check if board dimensions and ship lengths equal
		if(ro!=rowNum || co!=colNum || sl != shipLen || sr != shipRow || sc != shipCol) return false;
		
		//moreover: compare board cell-by-cell
		boolean ret = true;
		for(int r = 0; r < rowNum; r++){
			for(int c = 0; c < colNum; c++){
				ret = ret && ( table[r][c] == objt[r][c] );
			}
		}
		return ret;
	}
	
	@Override 
	public int hashCode() {
		int ret = 17;
		ret = 31 * ret + rowNum;
		ret = 31 * ret + colNum;
		ret = 31 * ret + shipLen;
		ret = 31 * ret + shipRow;
		ret = 31 * ret + shipCol;
		for(int r = 0; r < rowNum; r++){
			for(int c = 0; c < colNum; c++){
				ret = 31 * ret + table[r][c];
			}
		}
		return ret;
	}
	
}
