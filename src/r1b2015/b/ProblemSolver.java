package r1b2015.b;

import java.util.ArrayList;

import util.RawInput;
import util.Util;
import util.CombinationsEnumerator;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int R;
	private int C;
	private int N;
	
	/**
	 * constructor. The expected size of the problem might require different approaches, therefore runmode is required as a parameter   
	 * @param inRunMode expected RUNMODE_SMALL or RUNMODE_LARGE (see ProblemSolution)
	 */
	public ProblemSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	
	@Override
	public String solveCase(RawInput inCase) {
		return solveBF(inCase);
	}
	
	public String solveBF(RawInput inCase){
		init(inCase);
		
		//shortcuts
		if(N<=1) return "0";
		
		/* Brute Force
		 * 1) create an RxC grid of unoccupied flats
		 * 2) create all possible combinations of N flats and occupy them
		 * 3) for each combination: calculate Loudness based on flat adjacency in the grid
		 */
		HouseFlat[][] grid = new HouseFlat[R][C];
		ArrayList<HouseFlat> elems = new ArrayList<HouseFlat>();
		//create grid
		for(int r = 0; r < R; r++){
			for(int c = 0; c < C; c++){
				grid[r][c] = new HouseFlat(r,c);
				elems.add(grid[r][c]);
			}
		}
		//System.out.println("Number of elems=" + elems.size());
		//System.out.println(" elems=" + Util.iterableToString(elems, ",") );
		
		//obtain combinations
		ArrayList<ArrayList<HouseFlat>> combs = (new CombinationsEnumerator<HouseFlat>(elems)).getCombinations(N);
		//System.out.println("Number of combinations=" + combs.size());
		//System.out.println("  First comb=" + Util.iterableToString(combs.get(0), ",") );
		
		//calculate Loudness
		int ret = Integer.MAX_VALUE;
		ArrayList<HouseFlat> winningComb = null;
		for(ArrayList<HouseFlat> comb : combs){
			//occupie the flats appearing in the combination
			for(HouseFlat hf : comb){
				hf.occupy();
			}
			//System.out.println(" act comb=" + Util.iterableToString(comb, ","));
			//calculate Loudness in the grid
			int loudness = 0;
			//System.out.println("  Start comp: R=" + R + ", C=" + C);
			for(int r = 0; r < R; r++){
				for(int c = 0; c < C; c++){
					HouseFlat thisFlat = grid[r][c];
					HouseFlat nbRight = (c<(C-1) ? grid[r][c+1] : null);
					HouseFlat nbDown = (r<(R-1) ? grid[r+1][c] : null);
					loudness += (thisFlat.occupied() && (nbRight!=null && nbRight.occupied() ) ? 1 : 0);
					loudness += (thisFlat.occupied() && (nbDown!=null && nbDown.occupied() ) ? 1 : 0);
					/*
					System.out.println("   comp :: thisFlat=" + thisFlat + ", nbRight=" + nbRight + ", nbDown=" + nbDown
							     + ", this & right=" + (thisFlat.occupied() && (nbRight!=null && nbRight.occupied() ) ? 1 : 0)
							     + ", this & down=" + (thisFlat.occupied() && (nbDown!=null && nbDown.occupied() ) ? 1 : 0)
							     );
					*/
				}
			}
			if(loudness < ret){
				ret = loudness;
				winningComb = comb;
				//System.out.println("  BF: new min=" + ret + ", comb=" + Util.iterableToString(winningComb, ","));
			}
			//clear up for the next combination
			for(int r = 0; r < R; r++){
				for(int c = 0; c < C; c++){
					grid[r][c].empty();
				}
			}
		}//next combination
		
		return Integer.toString(ret);
	}
	
	public String solveGreedy(RawInput inCase){
		init(inCase);
		/* Greedy
		 * 1) create an RxC grid of unoccupied flats
		 * 2) Occupy them gradually as follows:
		 *    a) first 1..ceil(R*C/2) owner would occupy the grid in a chessboard fashion, yielding Loudness==0
		 *          a number OccupiedNeighbourhoodCount would be maintained at each insertion for each unoccupied flat
		 *    b) then new owners would be moved in the flat with the lowest OccupiedNeighbourhoodCount
		 *          and OccupiedNeighbourhoodCount would be maintained at each insertion for each unoccupied flat
		 *    --> in fact, this functionality is implemented in HouseGrid class
		 * 3) calculate Loudness based on flat adjacency in the grid
		 */
		HouseGrid grid = new HouseGrid(R,C);
		for(int i=0; i<N; i++){
			grid.addResidentToMin();
		}
		return Integer.toString(grid.getGridLoudness());
	}
	
	public String solveLarge(RawInput inCase){
		init(inCase);
		/* Large solution
		 * 1) N <= ceil(R*C/2) yields Loudness==0
		 * 2) if(min(R,C))==1 then 
		 *       N is odd => any new owner above ceil(R*C/2) would increase Loudness by 2
		 *       N is even =>
		 *          one new owner above ceil(R*C/2) would increase Loudness by 1
		 *          any other owner would increase Loudness by 2
		 * 3) if(min(R,C))==2 then 
		 * 		 2 owner could be added with an increase in Loudness by 2 (into the 2 empty corners)
		 *       any new owner could be added with an increase in Loudness by 3 (into empty slots in the grid border)
		 *       	
		 * 4) otherwise: min(R,C) > 2
		 * 		both min(R,C) and max(R,C) are odd =>
		 *       	all corners are occupied => addition with an increase in Loudness by 2 is excluded
		 *          any new owner could be added with an increase in Loudness by 3 (into empty slots in the grid border)
		 *          	number of such empty slots = 2 * floor(min(R,C)/2) + 2 * floor(max(R,C)/2)
		 *       	any further owner could be added with an increase in Loudness by 4 (into empty slots in the middle of the grid)
		 *      both min(R,C) and max(R,C) are even =>
		 *      	2 empty corners could be occupied with an increase in Loudness by 2
		 *      		the resulting "occupation plan" is symmetric with respect to all 4 borders
		 *      	any new owner could be added with an increase in Loudness by 3 (into empty slots in the grid border)
		 *       		number of such empty slots = 2 * ( min(R,C)/2 - 1) + 2 * ( max(R,C)/2 - 1)
		 *          any further owner could be added with an increase in Loudness by 4 (into empty slots in the middle of the grid)
		 *      otherwise: one of min(R,C) and max(R,C) is odd and the other is even
		 *          let's call them EVEN and ODD respectively
		 *          2 empty corners could be occupied with an increase in Loudness by 2
		 *          	but both of them are on the same ODD side
		 *          any new owner could be added with an increase in Loudness by 3 (into empty slots in the grid border)
		 *       		number of such empty slots = 2 * ( EVEN/2 - 1 )
		 *                                         + floor(ODD/2) - 2
		 *                                         + floor(ODD/2)
		 *                                         = 2 * ( EVEN/2 - 1 + floor(ODD/2) - 1 )
		 *                                         = 2 * ( EVEN/2 + floor(ODD/2) - 2 )
		 *          any further owner could be added with an increase in Loudness by 4 (into empty slots in the middle of the grid)
		 */
		int ret = -1;
		
		//shortcuts
		if(N<=1) ret = 0;
		
		int minRC = (R > C ? C : R);
		int maxRC = (R > C ? R : C);
		
		return Integer.toString(ret);
	}
			
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], " ");
		R = xx.get(0);
		C = xx.get(1);
		N = xx.get(2);
		
		//System.out.println("R=" + R + ", C=" + C + ", N=" + N);
	}

}
