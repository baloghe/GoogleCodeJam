package r1c2015.a;

import java.util.ArrayList;

import util.RawInput;
import util.Util;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int r;
	private int c;
	private int w;
	
	/**
	 * constructor. The expected size of the problem might require different approaches, therefore runmode is required as a parameter   
	 * @param inRunMode expected RUNMODE_SMALL or RUNMODE_LARGE (see ProblemSolution)
	 */
	public ProblemSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	
	@Override
	public String solveCase(RawInput inCase) {
		init(inCase);
		int ret = 0;		
		
		//shortcut: if w=1 then all cells must be tried!
		if(w==1) return Integer.toString(r*c);
		
		//if r>1 then all rows should be tried, however   
		//   for the first (r-1) rows only all Wth cells should be tried
		//		if c%w=0 => c/w tries needed per row
		//		if c%w>0 => int(c/w) tries needed per row
		//	 for the last row:
		//		all but the last Wth cells should be tried
		//		then comes a BF
		if(r>1){
			ret = (r-1) * (c/w);
		}
		
		//another shortcut: if w==c we have another w tries to make (all being HITs)
		if(c==w) return Integer.toString(ret + w);
		
		//Now let's turn our attention to the last line...
		//number of MISSes: ROOT tries every Wth cell in the row -> Opponent will report a MISS for all but the last one
		int search = c/w-1;
		//the last Wth cell must be a HIT
		search++;
		//if c mod w == 0, ROOT has to make another (w-1) guesses
		//otherwise another guess has to be made in order to locate one end of the ship
		if( c%w==0 ){
			search += (w-1);
		} else {
			search += w;
		}
		
		return Integer.toString(ret + search);
	}
	
	public String solveBF(RawInput inCase){
		init(inCase);
		
		//shortcut: if w=1 then all cells must be tried!
		if(w==1) return Integer.toString(r*c);
		
		//if r>1 then all rows should be tried, however   
		//   for the first (r-1) rows only all Wth cells should be tried
		//		if c%w=0 => c/w tries needed per row
		//		if c%w>0 => int(c/w) tries needed per row
		//	 for the last row:
		//		all but the last Wth cells should be tried
		//		then comes a BF
		int ret = 0;
		if(r>1){
			ret = (r-1) * (c/w);
		}
		
		//another shortcut: if w==c we have another w tries to make (all being HITs)
		if(c==w) return Integer.toString(ret + w);
		
		GcjGameState gs0 = new GcjGameState(1,c,w).setShip(0, c-w);
		GcjGameState gs = null;
		if(c >= 2*w){
			gs = gs0.setCell(0, w-1, 2);
			
			gs0 = gs;
			for(int i=2; i<c/w; i++){
				gs = gs0.setCell(0, i*w-1, 2);
				gs0 = gs;
			}
		} else {
			gs = gs0.setCell(0, w-1, 1);
		}
		System.out.println("  gs=" + gs);
		
		GcjABTreeNode.EVALUATOR = new GcjGameStateEvaluator();
		
		GcjABTreeNode nd = new GcjABTreeNode(gs, GcjABTreeNode.PLAYER_ROOT, 0);
		nd.createChildren();
		
		return Integer.toString( ret + nd.evalChildren() );
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		r = xx.get(0);
		c = xx.get(1);
		w = xx.get(2);
		System.out.println("init :: r=" + r + ", c=" + c + ", w=" + w);
	}

}
