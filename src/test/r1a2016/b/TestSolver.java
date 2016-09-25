package test.r1a2016.b;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1a2016.b.*;

public class TestSolver {

	
	@Ignore
	public void t_Solver(){
		
		String[] strs = new String[]{
				"3"
				,"1 2 3"
				,"2 3 5"
				,"3 5 6"
				,"2 3 4"
				,"1 2 3"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1a2016.b:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test
	public void t_Solver2(){
		
		String[] strs = new String[]{
				"7"
				,"9 11 14 15 17 18 20"
				,"10 13 14 16 18 20 22"
				,"2 3 5 8 9 11 13"
				,"8 9 12 13 14 17 18"
				,"8 10 11 13 14 17 19"
				,"1 2 4 6 8 9 10"
				,"9 11 13 15 17 18 21"
				,"1 2 4 6 8 9 11"
				,"6 8 10 12 13 15 16"
				,"4 5 6 10 11 13 14"
				,"11 13 16 17 19 21 22"
				,"2 3 5 8 10 11 13"
				,"4 5 6 10 12 14 16"
			};
		RawInput raw = new RawInput(strs);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1a2016.b:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Ignore
	public void t_Solver3(){
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		ListComparator<Integer> comp = new ListComparator<Integer>();
		
		ArrayList<Integer> l1 = new ArrayList<Integer>();
			l1.add(new Integer(10));
			l1.add(new Integer(13));
			l1.add(new Integer(15));
			
		ArrayList<Integer> l2 = new ArrayList<Integer>();
			l2.add(new Integer(10));
			l2.add(new Integer(14));
			l2.add(new Integer(15));
			
		ArrayList<Integer> l3 = new ArrayList<Integer>();
			l3.add(new Integer(9));
			l3.add(new Integer(13));
			l3.add(new Integer(15));
			
		ArrayList<Integer> l4 = new ArrayList<Integer>();
			l4.add(new Integer(10));
			l4.add(new Integer(14));
			l4.add(new Integer(16));
			
		ArrayList<Integer> l5 = new ArrayList<Integer>();
			l5.add(new Integer(10));
			l5.add(new Integer(14));
			l5.add(new Integer(15));
			
		System.out.println(Util.iterableToString(l1, " ") + " <-> " + Util.iterableToString(l2, " ") +" :: "+ comp.compare(l1, l2));
		System.out.println(Util.iterableToString(l2, " ") + " <-> " + Util.iterableToString(l3, " ") +" :: "+ comp.compare(l2, l3));
		System.out.println(Util.iterableToString(l2, " ") + " <-> " + Util.iterableToString(l4, " ") +" :: "+ comp.compare(l2, l4));
		System.out.println(Util.iterableToString(l2, " ") + " <-> " + Util.iterableToString(l5, " ") +" :: "+ comp.compare(l2, l5));
		
		System.out.println("LIST --------");
		list.add(l1);
		list.add(l2);
		list.add(l3);
		list.add(l4);
		list.add(l5);
		System.out.println("Orig order ---");
		for(ArrayList<Integer> e : list){
			System.out.println(Util.iterableToString(e, " "));
		}
		
		Collections.sort(list, comp);
		System.out.println("After sorting ---");
		for(ArrayList<Integer> e : list){
			System.out.println(Util.iterableToString(e, " "));
		}
	}
	
}
