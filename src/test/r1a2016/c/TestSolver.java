package test.r1a2016.c;


import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1a2016.c.*;

public class TestSolver {
	
	@Test
	public void t_Solver1(){
		
		String[] strs = new String[]{
				"4"
				,"2 3 4 1"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver1 for r1a2016.c:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver2(){
		
		String[] strs = new String[]{
				"4"
				,"3 3 4 1"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver2 for r1a2016.c:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver3(){
		
		String[] strs = new String[]{
				"4"
				,"3 3 4 3"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver3 for r1a2016.c:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver4(){
		
		String[] strs = new String[]{
				"10"
				,"7 8 10 10 9 2 9 6 3 3"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver4 for r1a2016.c:");
		System.out.println(solver.solveCase(raw));
	}
	
}
