package test.rq2014.a;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import rq2014.a.*;

public class TestSolver {

	
	@Test
	public void t_Solver1(){
		
		String[] strs = new String[]{
				"2"
				,"1 2 3 4"
				,"5 6 7 8"
				,"9 10 11 12"
				,"13 14 15 16"
				,"3"
				,"1 2 5 4"
				,"3 11 6 15"
				,"9 10 7 12"
				,"13 14 8 16"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver1 for rq2014.a:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver2(){
		
		String[] strs = new String[]{
				"2"
				,"1 2 3 4"
				,"5 6 7 8"
				,"9 10 11 12"
				,"13 14 15 16"
				,"2"
				,"1 2 3 4"
				,"5 6 7 8"
				,"9 10 11 12"
				,"13 14 15 16"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver2 for rq2014.a:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver3(){
		
		String[] strs = new String[]{
				"2"
				,"1 2 3 4"
				,"5 6 7 8"
				,"9 10 11 12"
				,"13 14 15 16"
				,"3"
				,"1 2 3 4"
				,"5 6 7 8"
				,"9 10 11 12"
				,"13 14 15 16"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver3 for rq2014.a:");
		System.out.println(solver.solveCase(raw));
	}
	
}