package test.r1a2015.b;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1a2015.b.*;

public class TestSolver {

	
	@Ignore
	public void t_Solver(){
		
		String[][] strs = new String[][]{
				 /*{"25 1000000000" , "5 5 5 5 5 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25"}*/
				{"5 137" , "1 2 3 4 5"}
				/*{"5 15653" , "13 22 19 7 2"}*/
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1a2015.b:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver4(){
		
		String[][] strs = new String[][]{
				 {"25 1000000000" , "5 5 5 5 5 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25 25"}
				/*{"5 137" , "1 2 3 4 5"}*/
				/*{"2 15" , "2 3"}*/
				/*{"5 42" , "1 2 1 1 3"}*/
				/*{"5 15653" , "13 22 19 7 2"}*/
				/*{"3 12" , "7 7 7"}*/
				/*{"2 4" , "10 5"}*/
				/*{"3 12" , "7 7 7"}*/
				/*{"3 8" , "4 2 1"}*/
				/*{"5 1000000000" , "25 25 25 25 25"}*/
				/*{"5 229887443" , "13 22 19 7 2"}*/
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver COMPARE for r1a2015.b:");
		//System.out.println("Brute Force:");
		//System.out.println(solver.solveCaseBF(raw));
		System.out.println("Large:");
		System.out.println(solver.solveCaseLarge(raw));
		System.out.println("Binary Search:");
		System.out.println(solver.solveCaseBinarySearch(raw));
	}
	
	
	@Ignore
	public void t_Solver2(){
		
		String[][] strs = new String[][]{
				 {"2 4" , "10 5"}
				,{"3 12" , "7 7 7"}
				,{"3 8" , "4 2 1"}
				,{"5 229887443" , "13 22 19 7 2"}
			};
		
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver2 for r1a2015.b:");
		for(String[] s : strs){
			RawInput raw = new RawInput(s);
			System.out.println("Result: " + s[0] + "|" + s[1] + " -> " + solver.solveCase(raw));
		}
	}
	
	@Ignore
	public void t_Solver3(){
		System.out.println("Integer.Max=" + Integer.MAX_VALUE);
	}
	
}
