package test.riow2016;

import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

import riow2016.a.*;
import util.*;

public class TestSolver {

	@Test/*@Test*/
	public void t_Solver1(){
		
		String[][] strs = new String[][]{
				new String[]{"2" , "9 12 15 20"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver1 for riow2016.a:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test/*@Test*/
	public void t_Solver2(){
		
		String[][] strs = new String[][]{
				new String[]{"4" , "9 12 12 12 15 16 16 20"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver2 for riow2016.a:");
		System.out.println(solver.solveCase(raw));
	}
	
}
