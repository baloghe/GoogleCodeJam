package test.r1b2015.b;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1b2015.b.*;

public class TestSolver {

	
	@Ignore
	public void t_Solver(){
		
		String[][] strs = new String[][]{
				 {"2 3 6"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1b2015.b:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Ignore
	public void t_CombEnum(){
		
		ArrayList<Integer> lst = new ArrayList<Integer>();
		lst.add(new Integer(1));
		lst.add(new Integer(2));
		lst.add(new Integer(3));
		lst.add(new Integer(4));
		lst.add(new Integer(5));
		lst.add(new Integer(6));
		
		System.out.println("t_CombEnum for r1b2015.b:");
		ArrayList<ArrayList<Integer>> cmbs = (new CombinationsEnumerator<Integer>(lst)).getCombinations(3);
		System.out.println( "Combinations (3):\n" + Util.iterableToString(cmbs, "\n") );
		
		cmbs = (new CombinationsEnumerator<Integer>(lst)).getCombinations(5);
		System.out.println( "Combinations (5):\n" + Util.iterableToString(cmbs, "\n") );
	}
	
	@Ignore
	public void t_HouseFlat(){
		
		HouseFlat f00 = new HouseFlat(0,0);
		HouseFlat f01 = new HouseFlat(0,1);
		HouseFlat f02 = new HouseFlat(0,2);
		HouseFlat f10 = new HouseFlat(1,0);
		HouseFlat f11 = new HouseFlat(1,1);
		HouseFlat f12 = new HouseFlat(1,2);
		HouseFlat f20 = new HouseFlat(2,0);
		HouseFlat f21 = new HouseFlat(2,1);
		HouseFlat f22 = new HouseFlat(2,2);
		
		f10.occupy();
		f22.occupy();
		
		System.out.println("t_HouseFlat for r1b2015.b:");
		System.out.println("  " + f20 + " " + f21 + " " + f22);
		System.out.println("  " + f10 + " " + f11 + " " + f12);
		System.out.println("  " + f00 + " " + f01 + " " + f02);
		System.out.println();
		System.out.println("f00 neighbour to f10 : " + f00.isNextTo(f10));
		System.out.println("f00 neighbour to f01 : " + f00.isNextTo(f01));
		System.out.println("f00 neighbour to f11 : " + f00.isNextTo(f11));
		System.out.println("f11 neighbour to f00 : " + f11.isNextTo(f00));
		System.out.println("f11 neighbour to f21 : " + f11.isNextTo(f21));
	}
	
	@Ignore
	public void t2_CombEnum(){
		
		ArrayList<HouseFlat> lst = new ArrayList<HouseFlat>();
		for(int r = 1; r <= 4; r++){
			for(int c = 1; c <= 4; c++){
				lst.add(new HouseFlat(r,c));
			}
		}
		
		System.out.println("t2_CombEnum for r1b2015.b:");
		ArrayList<ArrayList<HouseFlat>> cmbs = (new CombinationsEnumerator<HouseFlat>(lst)).getCombinations(3);
		System.out.println( "Num of combinations:" + cmbs.size() );
		System.out.println( "Combinations:\n" + Util.iterableToString(cmbs, "\n") );
	}
	@Ignore
	public void t_BF(){
		String[][] strs = new String[][]{
			 {"5 5 13"}
		};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_BF for r1b2015.b:");
		System.out.println(solver.solveBF(raw));
	}
	
	@Ignore
	public void t_HouseGrid(){
		HouseGrid grid = new HouseGrid(3,3);
		
		for(int i=0; i<7; i++){
			grid.addResidentToMin();
		}
		
		System.out.println("t_HouseGrid for r1b2015.b:");
		System.out.println("Occupation map:");
		System.out.println(grid.occupiedToString());
		System.out.println("Neighbours number:");
		System.out.println(grid.numNeighboursToString());
		System.out.println("Loudness = " + grid.getGridLoudness());
	}
	
	@Ignore
	public void t_BF_to_Greedy(){
		String[][] strs = new String[][]{
				 {"3 6 15"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_BF_to_Greedy for r1b2015.b:");
		System.out.println("BF:" + solver.solveBF(raw));
		System.out.println("Greedy:" + solver.solveGreedy(raw));
	}
	
	@Test
	public void t_Large_to_Greedy(){
		String[][] strs = new String[][]{
				 {"15 15 219"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Large_to_Greedy for r1b2015.b:");
		System.out.println("Large:" + solver.solveLarge(raw));
		System.out.println("Greedy:" + solver.solveGreedy(raw));
	}
}
