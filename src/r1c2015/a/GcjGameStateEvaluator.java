package r1c2015.a;

/** Game state object evaluator for Google Code Jam 2015 / Round 1C / Problem A: Brattleship
 *  
 *  ROOT has to find the ship with the minimum amount of guesses made -> ROOT tries to minimize the number of guesses. 
 *  OPPONENT should move the ship in such a position that ROOT should make as many guesses as possible -> OPPONENT tries to maximize the number of guesses made.
 *  
 *  Therefore a game state at the leaf of a minimax tree (i.e. at the possible end-of-games) is characterized by the number of guesses made.
 */
public class GcjGameStateEvaluator {

	public GcjGameStateEvaluator(){
		
	}
	
	/**
	 * evaluates a given game state by simply returning the number of guesses made (regardless of whether the guess found anything or not)
	 * @param inGS state object of the game
	 * @return number of guesses so far
	 */
	public int evaluate(GcjGameState inGS){
		int ret = 0;
		int rn = inGS.getRowNum();
		int cn = inGS.getColNum();
		for(int r = 0; r < rn; r++){
			for(int c = 0; c < cn; c++){
				if(inGS.getCellValue(r, c) != 0) ret++;
			}
		}
		return ret;
	}
	
	/**
	 * the game ends if the number of HITs equals to the length of the ship
	 * @param inGS state object of the game
	 * @return TRUE if the game has ended; FALSE otherwise
	 */
	public boolean isEndState(GcjGameState inGS){
		int hit = 0;
		int rn = inGS.getRowNum();
		int cn = inGS.getColNum();
		for(int r = 0; r < rn; r++){
			for(int c = 0; c < cn; c++){
				if(inGS.getCellValue(r, c) == 1) hit++;
			}
		}
		return (hit==inGS.getShipLen());
	}
	
}
