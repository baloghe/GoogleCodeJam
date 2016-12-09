package test.r1c2015.a;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1c2015.a.*;

public class TestSolver {
	
	@Test/*@Test*/
	public void t_Solver(){
		
		String[][] strs = new String[][]{
				 /*{"2101"}*/
				{"1 4 2"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1c2015.a:");
		System.out.println(solver.solveCase(raw));
	}
	
}
