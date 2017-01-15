package test.r1a2014.a;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;


import util.*;
import r1a2014.a.*;

public class TestSolver {

	@Test
	public void t_str2barr1(){
		
		String str ="101 110";
		boolean[][] act = ProblemSolver.readInput(str, 2, 3);
		boolean[][] exp = new boolean[][]{
			 new boolean[]{true,false,true}
			,new boolean[]{true,true,false}
			};
			
		System.out.println(act);
	}
	
	@Test
	public void t_str2barr2(){
		
		String str ="101 110";
		boolean[][] act0 = ProblemSolver.readInput(str, 2, 3);
			
		String act = ProblemSolver.booleanArrToString(act0);
		String exp = "TFT\nTTF";
		
		System.out.println(act);
			
		assertEquals(act, exp);
	}
	
	@Test
	public void t_Solver0(){
		
		String[][] strs = new String[][]{
				 {"2 3" , "101 110" , "111 000"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1a2014.a:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void longToBArr(){
		int[] nums = new int[]{0,1,2,3,4,7,19,32};
		
		for(int i : nums){
			System.out.println(i + " -> " + Util.objArrayToString( Util.longToBoolArray((long)i) , ","));
		}
	}
	
	@Test
	public void longToBArr2(){
		int[] nums = new int[]{0,1,2,3,4,7,19,32};
		
		for(int i : nums){
			System.out.println(i + " -> " + Util.objArrayToString( Util.longToBoolArray((long)i,6) , ","));
		}
	}
	
	@Test
	public void chgSwitch1(){
		
		String[][] strs = new String[][]{
				 {"3 3" , "111 000 101" , "001 010 101"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		Boolean[] chg = new Boolean[]{true,false,false};
		
		boolean[][] act0 = solver.modifySwitch(chg);
		boolean[][] exp0 = new boolean[][]{
								 new boolean[]{false,true,true}
								,new boolean[]{true,false,false}
								,new boolean[]{false,false,true}
							};
		
		String act = ProblemSolver.booleanArrToString(act0);
		String exp = ProblemSolver.booleanArrToString(exp0);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void chgSwitch2(){
		
		String[][] strs = new String[][]{
				 {"3 3" , "111 000 101" , "001 010 101"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		Boolean[] chg = new Boolean[]{false,true,false};
		
		boolean[][] act0 = solver.modifySwitch(chg);
		boolean[][] exp0 = new boolean[][]{
								 new boolean[]{true,false,true}
								,new boolean[]{false,true,false}
								,new boolean[]{true,true,true}
							};
		
		String act = ProblemSolver.booleanArrToString(act0);
		String exp = ProblemSolver.booleanArrToString(exp0);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void chgSwitch3(){
		
		String[][] strs = new String[][]{
				 {"3 3" , "111 000 101" , "001 010 101"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		solver.solveCase(raw);
		
		Boolean[] chg = new Boolean[]{false,false,true};
		
		boolean[][] act0 = solver.modifySwitch(chg);
		boolean[][] exp0 = new boolean[][]{
								 new boolean[]{true,true,false}
								,new boolean[]{false,false,true}
								,new boolean[]{true,false,false}
							};
		
		String act = ProblemSolver.booleanArrToString(act0);
		String exp = ProblemSolver.booleanArrToString(exp0);
		
		assertEquals(act, exp);
	}
	
	@Test
	public void twoPow1(){
		int act = Util.twoPow(0);
		int exp = 1;
		assertEquals(act, exp);
	}
	
	@Test
	public void twoPow2(){
		int act = Util.twoPow(1);
		int exp = 2;
		assertEquals(act, exp);
	}
	
	@Test
	public void twoPow3(){
		int act = Util.twoPow(3);
		int exp = 8;
		assertEquals(act, exp);
	}
	
	@Test
	public void countTrue1(){
		int act = ProblemSolver.countTrue(new Boolean[]{true, true, false, false, true});
		int exp = 3;
		assertEquals(act, exp);
	}
	
	@Test
	public void countTrue2(){
		int act = ProblemSolver.countTrue(new Boolean[]{false, false});
		int exp = 0;
		assertEquals(act, exp);
	}
	
	@Test
	public void BF1(){
		String[][] strs = new String[][]{
				 {"3 3" , "111 000 101" , "001 010 101"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("BF1(): " + act);
		
		String exp="NOT POSSIBLE";
		
		assertEquals(act, exp);
	}
	
	@Test
	public void BF2(){
		String[][] strs = new String[][]{
				 {"3 2" , "01 11 10" , "11 00 10"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("BF2(): " + act);
		
		String exp="1";
		
		assertEquals(act, exp);
	}
	
	@Test
	public void BF3(){
		String[][] strs = new String[][]{
				 {"2 3" , "101 111" , "010 001"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("BF3(): " + act);
		
		String exp="NOT POSSIBLE";
		
		assertEquals(act, exp);
	}
	
	@Test
	public void BF4(){
		String[][] strs = new String[][]{
				 {"2 2" , "01 10" , "10 01"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("BF4(): " + act);
		
		String exp="0";
		
		assertEquals(act, exp);
	}
	
	@Test
	public void BF5(){
		String[][] strs = new String[][]{
				 {"10 10" , "0000000000 0101010101 0011001100 1100110011 0111011100 1110011101 0111011110 1110111101 0000011111 1111111111" , "0000011111 1111100000 1110000111 0001111000 0101010101 0011001100 1100011000 0110001111 1111000110 0100110001"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("BF5(): " + act);
		
		String exp="NOT POSSIBLE";
		
		assertEquals(act, exp);
	}
	
	@Test
	public void Large1(){
		String[][] strs = new String[][]{
				 {"3 3" , "111 000 101" , "001 010 101"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveLarge(raw);
		System.out.println("Large1(): " + act);
		
		String exp="NOT POSSIBLE";
		
		assertEquals(act, exp);
	}
	
	@Test
	public void Large2(){
		String[][] strs = new String[][]{
				 {"2 3" , "101 111" , "010 001"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveLarge(raw);
		System.out.println("Large2(): " + act);
		
		String exp="NOT POSSIBLE";
		
		assertEquals(exp, act);
	}
	
	@Test
	public void Large3(){
		String[][] strs = new String[][]{
				 {"2 2" , "01 10" , "10 01"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveLarge(raw);
		System.out.println("Large3(): " + act);
		
		String exp="0";
		
		assertEquals(exp, act);
	}
	
	@Test
	public void Large4(){
		String[][] strs = new String[][]{
				 {"10 10" , "0000000000 0101010101 0011001100 1100110011 0111011100 1110011101 0111011110 1110111101 0000011111 1111111111" , "0000011111 1111100000 1110000111 0001111000 0101010101 0011001100 1100011000 0110001111 1111000110 0100110001"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveLarge(raw);
		System.out.println("Large4(): " + act);
		
		String exp="NOT POSSIBLE";
		
		assertEquals(exp, act);
	}
	
	@Test
	public void Large5(){
		String[][] strs = new String[][]{
				 {"1 5" , "00000" , "11111"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveLarge(raw);
		System.out.println("Large5(): " + act);
		
		String exp="5";
		
		assertEquals(exp, act);
	}
}
