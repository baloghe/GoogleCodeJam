package test.r1a2015.a;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1a2015.a.*;

public class TestSolver {

	
	@Ignore
	public void t_Solver(){
		
		String[][] strs = new String[][]{
				 {"4" , "10 5 15 5"}
				,{"2" , "100 100"}
				,{"8" , "81 81 81 81 81 81 81 0"}
				,{"6" , "23 90 40 0 100 9"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1a2015.a:");
		System.out.println(solver.solveCase(raw));
	}
	
	
	@Test
	public void t_Solver2(){
		
		String[][] strs = new String[][]{
				 {"4" , "10 5 15 5"}
				,{"2" , "100 100"}
				,{"8" , "81 81 81 81 81 81 81 0"}
				,{"6" , "23 90 40 0 100 9"}
			};
		
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver2 for r1a2015.a:");
		for(String[] s : strs){
			RawInput raw = new RawInput(s);
			System.out.println(solver.solveCase(raw));
		}
	}
}
