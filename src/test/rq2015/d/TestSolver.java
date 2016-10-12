package test.rq2015.d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import rq2015.d.*;

public class TestSolver {

	
	@Test
	public void t_Solver(){
		
		String[] strs = new String[]{
				 "6 4 6"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for rq2015.d:");
		System.out.println(strs[0] + " -> " + solver.solveCase(raw));
	}
	
}
