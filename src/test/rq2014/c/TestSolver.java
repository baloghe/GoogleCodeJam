package test.rq2014.c;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import rq2014.c.*;

public class TestSolver {
	
	@Test
	public void t_printArrangement1(){
		
		int R=1, C=1, M=0;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c";
		String act = solver.printArrangement(R, C, M);
		//System.out.println("   ->" + act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement2(){
		
		int R=1, C=2, M=1;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c*";
		String act = solver.printArrangement(R, C, M);
		//System.out.println("   ->" + act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement3(){
		
		int R=1, C=2, M=0;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c.";
		String act = solver.printArrangement(R, C, M);
		//System.out.println("   ->" + act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement4(){
		
		int R=2, C=1, M=0;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c\n.";
		String act = solver.printArrangement(R, C, M);
		//System.out.println("   ->" + act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement5(){
		
		int R=2, C=1, M=1;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c\n*";
		String act = solver.printArrangement(R, C, M);
		//System.out.println("   ->" + act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement6(){
		
		int R=2, C=3, M=5;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c**\n***";
		String act = solver.printArrangement(R, C, M);
		//System.out.println(act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement7(){
		
		int R=4, C=3, M=11;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c**\n***\n***\n***";
		String act = solver.printArrangement(R, C, M);
		//System.out.println(act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement8(){
		
		int R=2, C=3, M=0;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c..\n...";
		String act = solver.printArrangement(R, C, M);
		//System.out.println(act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement9(){
		
		int R=2, C=3, M=1;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c..\n..*";
		String act = solver.printArrangement(R, C, M);
		//System.out.println(act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement10(){
		
		int R=2, C=3, M=2;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c.*\n..*";
		String act = solver.printArrangement(R, C, M);
		//System.out.println(act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement11(){
		
		int R=3, C=3, M=5;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c.*\n..*\n***";
		String act = solver.printArrangement(R, C, M);
		//System.out.println(act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_printArrangement12(){
		
		int R=4, C=4, M=7;
		
		String[] strs = new String[]{
				R + " " + C + " " + M
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		String exp = "c...\n....\n.***\n****";
		String act = solver.printArrangement(R, C, M);
		//System.out.println(act);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void t_Solver1(){
		
		String[] strs = new String[]{
				"5 5 23"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver1 for rq2014.c:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver2(){
		
		String[] strs = new String[]{
				"3 1 1"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver2 for rq2014.c:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver3(){
		
		String[] strs = new String[]{
				"2 2 1"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver3 for rq2014.c:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver4(){
		
		String[] strs = new String[]{
				"4 7 3"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver3 for rq2014.c:");
		System.out.println(solver.solveCase(raw));
	}
		
}