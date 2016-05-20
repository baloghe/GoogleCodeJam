package test.r1a2016;

import org.junit.Test;

import r1a2016.*;
import util.*;

public class TstProblemA {

	@Test
	public void t_TstProblemA1(){
		String orig = "LGL";
		
		System.out.println("t_TstProblemA1:");
		RawInput raw = new RawInput( new String[]{ orig } );
		ProblemASolver solver = new ProblemASolver( ProblemASolution.RUNMODE_SMALL );
		System.out.println("   " + orig + " -> " + solver.solveCase(raw));
	}
	
	@Test
	public void t_TstProblemA2(){
		String[] origs = new String[]{
				"CAB",
				"JAM",
				"CODE",
				"ABAAB",
				"CABCBBABC",
				"ABCABCABC",
				"ZXCASDQWE"
		};
		
		System.out.println("t_TstProblemA2:");
		for(String orig : origs){
			RawInput raw = new RawInput( new String[]{ orig } );
			ProblemASolver solver = new ProblemASolver( ProblemASolution.RUNMODE_SMALL );
			System.out.println("   " + orig + " -> " + solver.solveCase(raw));
		}
	}
	
}
