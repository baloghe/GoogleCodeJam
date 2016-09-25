package test.r1b2016.a;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1b2016.a.*;

public class TestSolver {

	
	@Ignore
	public void t_Solver(){
		
		String[] strs = new String[]{
				"OZONETOWER"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1b2016.a:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t2_Solver(){
		
		String[] strs = new String[]{
				"OZONETOWER"
				,"WEIGHFOXTOURIST"
				,"OURNEONFOE"
				,"ETHER"
			};
		
		System.out.println("t_Solver for r1b2016.a:");
		for(String s : strs){
			RawInput raw = new RawInput( new String[]{s} );
			ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
			System.out.println(raw.toString() + " -> " + solver.solveCase(raw));
		}
		
	}
	
	@Ignore
	public void t_tt(){
		
		String s = "OZONETOWER";
		
		StringBuilder sb = new StringBuilder(s);
		int[] digits = new int[10];
		//TWO
		int digitFound = 0;
		while(sb.indexOf("W") >= 0){
			digitFound++;
			sb.deleteCharAt(sb.indexOf("T"));
			sb.deleteCharAt(sb.indexOf("W"));
			sb.deleteCharAt(sb.indexOf("O"));
		}
		
		System.out.println("t_tt for OZONETOWER - TWO:" + sb.toString());
		
		while(sb.indexOf("Z") >= 0){
			digitFound++;
			sb.deleteCharAt(sb.indexOf("Z"));
			sb.deleteCharAt(sb.indexOf("E"));
			sb.deleteCharAt(sb.indexOf("R"));
			sb.deleteCharAt(sb.indexOf("O"));
		}
		
		System.out.println("t_tt for OZONETOWER - ZERO:" + sb.toString());
		
		while(sb.indexOf("O") >= 0){
			digitFound++;
			sb.deleteCharAt(sb.indexOf("O"));
			sb.deleteCharAt(sb.indexOf("N"));
			sb.deleteCharAt(sb.indexOf("E"));
		}
		
		System.out.println("t_tt for OZONETOWER - ONE:" + sb.toString());
	}
	
	
}
