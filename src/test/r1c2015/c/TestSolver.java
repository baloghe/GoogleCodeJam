package test.r1c2015.c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.*;
import util.*;
import r1c2015.c.*;

public class TestSolver {
	
	@Test/*@Test*/
	public void t_Solver(){
		
		String[][] strs = new String[][]{
				 {"1 2 20" , "2 3"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver1 for r1c2015.c:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test/*@Test*/
	public void t_Solver2(){
		
		String[][] strs = new String[][]{
				 {"2 2 20" , "2 3"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver2 for r1c2015.c:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test/*@Test*/
	public void t_max1(){
		
		String[][] strs = new String[][]{
				 {"1 5 100" , "1 5 10 25 50"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_max1 for r1c2015.c:");
		System.out.println(solver.solveCase(raw));
	}
	
}
