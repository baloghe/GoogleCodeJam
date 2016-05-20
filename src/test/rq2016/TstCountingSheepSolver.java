package test.rq2016;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import practice.*;
import rq2016.*;
import util.*;

public class TstCountingSheepSolver {

	private CaseSolver solver;
	
	@Before
	public void init(){
		solver = new CountingSheepSolver();
	}
	
	@Test
	public void t_solve(){
		
		String[] str = new String[]{
						"0"
					};
		
		RawInput r = new RawInput(str);
		
		System.out.println(solver.solveCase(r));
	}
	
	@Test
	public void t_solve2(){
		
		String[] str = new String[]{
						"1"
					};
		
		RawInput r = new RawInput(str);
		
		System.out.println(solver.solveCase(r));
	}
	
	@Test
	public void t_solve3(){
		
		String[] str = new String[]{
						"2"
					};
		
		RawInput r = new RawInput(str);
		
		System.out.println(solver.solveCase(r));
	}
	
	@Test
	public void t_solve4(){
		
		String[] str = new String[]{
						"11"
					};
		
		RawInput r = new RawInput(str);
		
		System.out.println(solver.solveCase(r));
	}
	
	@Test
	public void t_solve5(){
		
		String[] str = new String[]{
						"1692"
					};
		
		RawInput r = new RawInput(str);
		
		System.out.println(solver.solveCase(r));
	}
	
}
