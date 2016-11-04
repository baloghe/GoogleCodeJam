package test.r1a2015.c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1a2015.c.*;

public class TestSolver {

	@Test
	public void t_Solver(){
		
		String[][] strs = new String[][]{
				/*{"5" , "0 0","10 0","10 10","0 10","5 5"}*/
				{"4","1 1","2 4","3 9","4 16"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1a2015.c:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Ignore
	public void t_Solver1(){
		Point2D p1 = new Point2D(10,10);
		Point2D p2 = new Point2D(10,20);
		
		Point2D pX = new Point2D(5,5);
		Point2D pY = new Point2D(10,0);
		Point2D pZ = new Point2D(20,50);
		
		System.out.println("Szakasz: " + p1 + " -> " + p2);
		System.out.println(pX + " :: " + ProblemSolver.getPositionToSegment(pX, p1, p2));
		System.out.println(pY + " :: " + ProblemSolver.getPositionToSegment(pY, p1, p2));
		System.out.println(pZ + " :: " + ProblemSolver.getPositionToSegment(pZ, p1, p2));
	
	}
	
	@Ignore
	public void t_Solver_gen1(){
		Random rand = new Random();
		int numPt = 15;
		
		String[] ss = new String[numPt+1];
		ss[0] = Integer.toString(numPt);
		
		for(int i=1; i<=numPt; i++){
			long x = (rand.nextLong() % 2000000) - 1000000;
			long y = (rand.nextLong() % 2000000) - 1000000;
			String s = x + " " + y;
			ss[i] = s;
		}
		
		RawInput raw = new RawInput( ss );
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		System.out.println("t_Solver_GEN for r1a2015.c:");
		System.out.println("Result: " + solver.solveCase(raw));
	}
	
	@Ignore
	public void t_Solver_sort1(){
		ArrayList<Point2D> lst = new ArrayList<Point2D>();
		lst.add( new Point2D( 10, 60 ) );
		lst.add( new Point2D( 30, 40) );
		lst.add( new Point2D( 35, 40) );
		lst.add( new Point2D( 25, 40) );
		lst.add( new Point2D( 50, 20 ) );
		Collections.sort(lst);
		
		System.out.println("t_Solver_sort1 for r1a2015.c:");
		for(Point2D p : lst){
			System.out.println( p );
		}
	}
	
	@Ignore
	public void t_Solver_comp1(){
		ArrayList<Point2D> lst = new ArrayList<Point2D>();
		lst.add( new Point2D( 5, -2 ) );
		lst.add( new Point2D( 5, 2 ) );
		lst.add( new Point2D( 2, 5) );
		lst.add( new Point2D( -5, 2) );
		lst.add( new Point2D( -5, -2 ) );
		lst.add( new Point2D( -2, 5) );
		lst.add( new Point2D( -2, -5 ) );
		lst.add( new Point2D( 2, -5 ) );		
		Collections.sort(lst, new Point2DXAxisComparator() );
		
		System.out.println("t_Solver_comp1 for r1a2015.c:");
		for(Point2D p : lst){
			System.out.println( p );
		}
	}
	
}
