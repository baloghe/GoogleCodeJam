package test.rq2015.b;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import rq2015.b.*;

public class TestSolver {

	
	@Ignore
	public void t_Solver(){
		
		String[][] strs = new String[][]{
				 new String[]{"5","9 9 9 9 9"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for rq2015.b:");
		System.out.println(strs[0][0] + " | " + strs[0][1] + " -> " + solver.solveCase(raw));
	}
	
	@Test
	public void t2_Solver(){
		
		String[][] strs = new String[][]{
				 new String[]{"1","3"}
				,new String[]{"4","1 2 1 2"}
				,new String[]{"1","4"}
				,new String[]{"1","10"}
				,new String[]{"2","10 10"}
				,new String[]{"1","100"}
				,new String[]{"1","9"}
			};
		
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t2_Solver for rq2015.b:");
		for(String[] s : strs){
			RawInput raw = new RawInput( s );
			System.out.println(s[0] + " | " + s[1] + " -> " + solver.solveCase(raw));
		}
	}
	
	@Ignore/*@Test*/
	public void t3_Solver(){
		
		ArrayList<Integer> lst = new ArrayList<Integer>();
		lst.add(new Integer(10));
		lst.add(new Integer(10));
		
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t3_Solver for rq2015.b:");
		System.out.println("  before: " + Util.iterableToString(lst, ",") + " -> remainingTime1=" + solver.getRemainingTimeWithTakeOff(lst) + ", maxNum=" + solver.getMaxNumber(lst) );
				
		solver.updateEaters(lst,2);
		System.out.println("  1st: " + Util.iterableToString(lst, ",") + " -> remainingTime1=" + solver.getRemainingTimeWithTakeOff(lst) + ", maxNum=" + solver.getMaxNumber(lst) );
		
		solver.updateEaters(lst,2);
		System.out.println("  2nd: " + Util.iterableToString(lst, ",") + " -> remainingTime1=" + solver.getRemainingTimeWithTakeOff(lst) + ", maxNum=" + solver.getMaxNumber(lst) );
		
		solver.updateEaters(lst,2);
		System.out.println("  3rd: " + Util.iterableToString(lst, ",") + " -> remainingTime1=" + solver.getRemainingTimeWithTakeOff(lst) + ", maxNum=" + solver.getMaxNumber(lst) );
	}
}
