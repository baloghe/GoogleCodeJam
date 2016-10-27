package r1a2015.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import util.RawInput;
import util.Util;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int ptsNum;
	private ArrayList<Point2D> points;
	
	/**
	 * constructor. The expected size of the problem might require different approaches, therefore runmode is required as a parameter   
	 * @param inRunMode expected RUNMODE_SMALL or RUNMODE_LARGE (see ProblemSolution)
	 */
	public ProblemSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	
	@Override
	public String solveCase(RawInput inCase) {
		return solveCaseSmall(inCase);
	}
	
	public String solveCaseSmall(RawInput inCase){
		init(inCase);
		
		/* If a tree P should be on the convex hull of the remaining trees, then it is 
		 *    EITHER a vertex of the hull 
		 *    OR lies on the border of the hull (and is collinear with at least two other trees)
		 * Whichever is true, there is such a straight line that all the remaining trees fall either on the line or on one side of the line
		 * 
		 * Take point P
		 * Take all the other points X one by one
		 * count number of points lying strictly
		 *    LEFT from PX  -> toLeft
		 *    RIGHT from PX -> toRight
		 * numToFall(P, X) <- min{ toLeft , toRight }
		 * 
		 * numToFall(P) <- min(over X) {numToFall(P, X)}
		 */
		
		ArrayList<Integer> numToFall = new ArrayList<Integer>();
		
		for(Point2D P : points){
			int numToFall_P = ptsNum-1; //at least P should be preserved!
			//System.out.println("Point inspected: P=" + P);
			for(Point2D X : points){
				if(!(P.equals(X))){
					//System.out.println("  line segment end: X=" + X);
					int toLeft = 0;
					int toRight = 0;
					for(Point2D Q : points){
						if(   !(Q.equals(P))
						   && !(Q.equals(X))){
							int whichside = ProblemSolver.isLeftFromSegment(X, P, Q);
							if(whichside > 0){
								toRight++;
							} else if(whichside < 0){
								toLeft++;
							}
						}
					}//next Q in points\{P,X}
					int numToFall_P_X = (toLeft < toRight ? toLeft : toRight);
					//System.out.println("    toLeft=" + toLeft + ", toRight=" + toRight + " => toFall=" + numToFall_P_X);
					
					//find min(over X)
					if(numToFall_P > numToFall_P_X){
						//System.out.println("      new mini: old=" + numToFall_P + " <- " + numToFall_P_X + "=new");
						numToFall_P = numToFall_P_X;
					}
				}
			}//next X in points\{P}
			
			//add minimum
			numToFall.add(new Integer(numToFall_P));
		}//next P in points
		
		return Util.iterableToString(numToFall, " ");
	}
	
	public static int isLeftFromSegment(Point2D inTargetPt, Point2D inStartPt, Point2D inEndPt){
		//Taken from https://en.wikipedia.org/wiki/Graham_scan
		// 1 if Start -> Target - > End constitutes a Left Turn
		// 0 if the points are collinear
		//-1 otherwise
		
		long res = ( inTargetPt.x() - inStartPt.x() ) * ( inEndPt.y() - inStartPt.y() )
				 - ( inTargetPt.y() - inStartPt.y() ) * ( inEndPt.x() - inStartPt.x() )
				 ;
		if(res > 0) return 1;
		else if(res == 0) return 0;
		else return -1;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		ptsNum = Integer.parseInt(inCase.getData()[0]);
		
		points = new ArrayList<Point2D>();
		
		for(int j=1; j<=ptsNum; j++){
			ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[j], " ");
			//System.out.println("   j=" + j + " -> inCase.getData()[" + j + "]=" + inCase.getData()[j] );
			long x = xx.get(0);
			long y = xx.get(1);
			points.add( new Point2D(x,y) );
		}
		
		//System.out.println("init :: ptsNum=" + ptsNum + ", points.size=" + points.size());
		//System.out.println("  points=" + Util.objArrayToString(lines, ";"));
	}

}
