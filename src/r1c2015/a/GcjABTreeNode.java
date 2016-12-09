package r1c2015.a;

import java.util.HashSet;

public class GcjABTreeNode {

	public static final int PLAYER_ROOT = 0;
	public static final int PLAYER_OPPONENT = 1;
	
	public static final int ROOT_MIN = 0;
	public static final int ROOT_MAX = 1;
	
	public static int ROOT_MIN_MAX = ROOT_MIN;
	public static GcjGameStateEvaluator EVALUATOR;
	public static int MAX_DEPTH = 100;
	
	
	private final GcjGameState gameState;
	private final int evalType;
	private final int level;
	private HashSet<GcjABTreeNode> children;
	
	public GcjABTreeNode(GcjGameState inGS, int inEvalType, int inLev){
		gameState = inGS;
		evalType = inEvalType;
		level = inLev;
		children = new HashSet<GcjABTreeNode>();
	}
	
	/**
	 * creates children of the actual node
	 */
	public void createChildren(){
		//create children only when the game is still running!
		if(EVALUATOR.isEndState(gameState)) return;
		
		//otherwise: generate new game states from the given one
		HashSet<GcjGameState> states = createNextStates();
		for(GcjGameState tmpgs : states){
			//add result as new child
			children.add( new GcjABTreeNode(tmpgs, 1-evalType, level+1) );
		}//next i		
	}
	
	/**
	 * create a set of meaningful next states, depending on the actual state, and whether it belongs to ROOT's or OPPONENT's turn
	 * from ROOT's point of view, a guaranteed MISS is not meaningful, these are left out 
	 * @return set of meaningful next states
	 */
	public HashSet<GcjGameState> createNextStates(){
		if(evalType==PLAYER_ROOT)
			return createNextStatesRoot();
		else return createNextStatesOpponent();
	}
	
	private HashSet<GcjGameState> createNextStatesRoot(){
		HashSet<GcjGameState> ret = new HashSet<GcjGameState>();
		
		int rn = gameState.getRowNum();
		int cn = gameState.getColNum();
		
		for(int r=0; r<rn; r++){
			for(int c=0; c<cn; c++){
				if(   ( !gameState.guessedAlready(r, c) ) //exclude former guesses
				   && ( gameState.guess(r, c)!=2 ) ){     //exclude unmeaningful guesses
					GcjGameState gs = gameState.setCell(r, c, 3); //make a new guess
					ret.add(gs); //add it as possible next state
				}
			}//next c
		}//next r
		
		return ret;
	}
	
	private HashSet<GcjGameState> createNextStatesOpponent(){
		HashSet<GcjGameState> ret = new HashSet<GcjGameState>();
		
		int rn = gameState.getRowNum();
		int cn = gameState.getColNum() - gameState.getShipLen() + 1;
		int[] ug = gameState.getLastUnansweredGuessPos();
		
		//System.out.println("createNextStatesOpponent :: ug=[" + ug[0] + ", " + ug[1] + "]");
		
		for(int r=0; r<rn; r++){
			for(int c=0; c<cn; c++){
				//System.out.println("    inspect: r=" + r + ", c=" + c);
				if(gameState.shipPositionValid(r, c)){
					GcjGameState gs0 = gameState.setShip(r, c); //set ship if possible in view of former guesses
					//System.out.println("  Ship to: r=" + r + ", c=" + c);
					GcjGameState gs;
					if(gs0.isHit(ug[0], ug[1])){
						gs = gs0.setCell(ug[0], ug[1], 1); //HIT
						//System.out.println("    HIT added: ug[0]=" + ug[0] + ", ug[1]=" + ug[1] + ", gs=" + gs);
					} else {
						gs = gs0.setCell(ug[0], ug[1], 2); //MISS
						//System.out.println("    MISS added: ug[0]=" + ug[0] + ", ug[1]=" + ug[1] + ", gs=" + gs);
					}
					ret.add(gs); //add it as possible next state
				}
			}//next c
		}//next r
		
		return ret;
	}
	
	private int evalLeaf(){
		int ret = EVALUATOR.evaluate(gameState);
		//System.out.println(" evalLeaf :: gs=" + gameState + "  -> " + ret);
		return ret;
	}
	
	/**
	 * from ROOT perspective: ship must be sunk with the MINIMUM of obligatory guesses made
	 *  => in ROOT's turn:
	 *      at top-down pass, all possible guesses are made
	 *      at bottom-up pass, the branch with MINIMUM number of guesses is selected
	 *      
	 * from OPPONENT perspective: ship must be sunk with the MAXIMUM of obligatory guesses made
	 *  => in ROOT's turn:
	 *  	at top-down pass, provided former guesses (along with their results) AND a cell asked by ROOT, all possible ship positions (with the respective answer for the last guess) are tried out
	 *  	at bottom-up pass, the branch with MAXIMUM number of guesses is selected
	 * @return
	 */
	public int evalChildren(){
		if(   children.size() == 0
		   || this.level >= MAX_DEPTH) return evalLeaf();
		
		if(evalType==PLAYER_ROOT)
			return evalNodeRoot();
		else return evalNodeOpponent();
	}
	
	private int evalNodeRoot(){
		int ret = (ROOT_MIN_MAX==ROOT_MIN ? Integer.MAX_VALUE : Integer.MIN_VALUE );
		
		for(GcjABTreeNode ch : children){
			ch.createChildren();
			int tmp = ch.evalChildren();
			if(   (ROOT_MIN_MAX==ROOT_MIN && tmp < ret)
			   || (ROOT_MIN_MAX==ROOT_MAX && tmp > ret)
			  ) ret = tmp;
		}
		
		return ret;
	}
	
	private int evalNodeOpponent(){
		int ret = (ROOT_MIN_MAX==ROOT_MIN ? Integer.MIN_VALUE : Integer.MAX_VALUE );
		
		for(GcjABTreeNode ch : children){
			ch.createChildren();
			int tmp = ch.evalChildren();
			if(   (ROOT_MIN_MAX==ROOT_MIN && tmp > ret)
			   || (ROOT_MIN_MAX==ROOT_MAX && tmp < ret)
			  ) ret = tmp;
		}
		
		return ret;
	}
}
