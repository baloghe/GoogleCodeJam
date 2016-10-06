package test.rq2015.a;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import rq2015.a.*;

public class TestSolver {

	
	@Ignore
	public void t_Solver(){
		
		String[] strs = new String[]{
				"20 000009000000000000009"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for rq2015.a:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t2_Solver(){
		
		String[] strs = new String[]{
				 "4 11111"
				,"1 09"
				,"5 110011"
				,"0 1"
				,"2 012"
				,"5 000009"
				,"10 00000900009"
				,"20 000009000000000000009"
			};
		
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t2_Solver for rq2015.a:");
		for(String s : strs){
			RawInput raw = new RawInput( new String[]{s} );
			System.out.println(s + " -> " + solver.solveCase(raw));
		}
	}
	
}
