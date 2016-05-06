package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import practice.*;
import rq2016.CountingSheepSolver;
import util.*;

public class TstStoreCreditSolver {

private CaseSolver solver;
	
	@Before
	public void init(){
		solver = new StoreCreditSolver();
	}
	
	@Test
	public void t_solve(){
		
		String[] str = new String[]{
						"100" , "3" , "5 75 25"
					};
		
		RawInput r = new RawInput(str);
		
		System.out.println(solver.solveCase(r));
	}
	
	@Test
	public void t_solve2(){
		
		String[] str = new String[]{
						"200" , "7" , "150 24 79 50 88 345 3"
					};
		
		RawInput r = new RawInput(str);
		
		System.out.println(solver.solveCase(r));
	}
	
	@Test
	public void t_solve3(){
		
		String[] str = new String[]{
						"8" , "8" , "2 1 9 4 4 56 90 3"
					};
		
		RawInput r = new RawInput(str);
		
		System.out.println(solver.solveCase(r));
	}
	
}
