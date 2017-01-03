package test.rq2014.b;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import rq2014.b.*;

public class TestSolver {
	
	@Test
	public void t_Solver1(){
		
		String[] strs = new String[]{
				"500.0 4.0 2000.0"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver1 for rq2014.b:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver2(){
		
		String[] strs = new String[]{
				"10000.0 100.0 100000.0"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver1 for rq2014.b:");
		System.out.println(solver.solveCase(raw));
	}
		
}