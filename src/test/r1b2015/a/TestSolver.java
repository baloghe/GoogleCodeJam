package test.r1b2015.a;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1b2015.a.*;

public class TestSolver {

	
	@Ignore/*@Test*/
	public void t_Solver(){
		
		String[][] strs = new String[][]{
				 /*{"2101"}*/
				{"100000000000000"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1b2015.a:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Comp_Solver(){
		
		String[][] strs = new String[][]{
				 /*{"2101"}*/
				/*{"998999"}*/
				{"990000"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Comp_Solver for r1b2015.a:");
		System.out.println("Greedy");
		System.out.println("  Greedy out = " + solver.solveGreedy(raw));
		System.out.println("Large");
		System.out.println("  Large out = " + solver.solveLarge(raw));
	}
	
	@Ignore
	public void t_reverseLong(){
		
		long a = 123456789123456789L;
		
		System.out.println("reverseLong for r1b2015.a:");
		System.out.println( "rev(" + a + ")=" + ProblemSolver.reverseLong(a) );
	}
	
	@Ignore
	public void t_reverseCand(){
		
		long a = 1201L;
		
		System.out.println("t_reverseCand for r1b2015.a:");
		System.out.println( "reverse candidate for " + a + "=" + ProblemSolver.getReverseCandidate(a,10) );
	}
	
	@Ignore
	public void t_reverseCand2(){
		
		long a = 20000L;
		
		ProblemSolver.initTenPowers();
		System.out.println("t_reverseCand2 for r1b2015.a:");
		System.out.println( "reverse candidate 2 for " + a + "=" + ProblemSolver.getReverseCandidate2(a) );
	}
}
