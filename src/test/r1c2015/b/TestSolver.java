package test.r1c2015.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.*;
import util.*;
import r1c2015.b.*;

public class TestSolver {
	
	@Test/*@Test*/
	public void t_Solver(){
		
		String[][] strs = new String[][]{
				 {"2 1 2" , "AB" , "B"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1c2015.b:");
		System.out.println(solver.solveCase(raw));
	}
	
	@Test/*@Test*/
	public void t_Solver2(){
		
		String[][] strs = new String[][]{
				 {"7 6 6" , "BANANAS" , "MONKEY"}
				,{"2 3 4" , "AA" , "AAA"}
				,{"2 1 2" , "AB" , "B"}
				,{"6 2 2" , "GOOGLE" , "GO"}
				,{"7 1 7" , "GGGGRGS" , "G"}
			};
		
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for r1c2015.b:");
		for(int i=0; i<strs.length; i++){
			System.out.println("i=" + i);
			RawInput raw = new RawInput(strs[i]);
			System.out.println(solver.solveBF(raw));
		}
	}
	
	@Test
	public void t_PermEnum(){
		ArrayList<String> lst = new ArrayList<String>();
		lst.add("A");
		lst.add("B");
		
		HashSet<String> exp = new HashSet<String>();
		exp.add("AAA");
		exp.add("AAB");
		exp.add("ABA");
		exp.add("ABB");
		exp.add("BAA");
		exp.add("BAB");
		exp.add("BBA");
		exp.add("BBB");
		
		HashSet<String> act = new HashSet<String>();
		
		System.out.println("t_PermEnum:");
		
		PermutationsEnumerator<String> pe = new PermutationsEnumerator<String>( lst, 3 );
		while( pe.hasNext() ){
			ArrayList<String> perm = pe.next();
			String s = "";
			for(String d : perm){
				s += d;
			}
			act.add(s);
		}
		
		assertEquals(exp, act);
	}
	
	@Ignore
	public void t_PermEnum2(){
		ArrayList<String> lst = new ArrayList<String>();
		lst.add("A");
		lst.add("B");
		lst.add("A");
		
		ArrayList<String> act = new ArrayList<String>();
		
		System.out.println("t_PermEnum2:");
		
		PermutationsEnumerator<String> pe = new PermutationsEnumerator<String>( lst, 3 );
		while( pe.hasNext() ){
			ArrayList<String> perm = pe.next();
			String s = "";
			for(String d : perm){
				s += d;
			}
			act.add(s);
		}
		
		assertEquals( 27, act.size() );
	}
	
	@Test
	public void t_strArray(){
		String s = "ALMAFA";
		ArrayList<String> act = ProblemSolver.stringToArrList(s);
		ArrayList<String> exp = new ArrayList<String>();
		exp.add("A");
		exp.add("L");
		exp.add("M");
		exp.add("A");
		exp.add("F");
		exp.add("A");
		
		assertEquals( exp, act );
	}
	
	@Test
	public void t_occCounter(){
		String typed = "ABABABAB";
		String tw = "ABA";
		
		int act = ProblemSolver.countTWOccurence(typed, tw);
		int exp = 3;
		
		assertEquals( exp, act );
	}
	
	@Test
	public void t_occCounter2(){
		String typed = "BBBBBB";
		String tw = "B";
		
		int act = ProblemSolver.countTWOccurence(typed, tw);
		int exp = 6;
		
		assertEquals( exp, act );
	}
	
	@Test
	public void t_occCounter3(){
		String typed = "BBBBBB";
		String tw = "BB";
		
		int act = ProblemSolver.countTWOccurence(typed, tw);
		int exp = 5;
		
		assertEquals( exp, act );
	}
	
	@Test
	public void t_strFmt(){
		double d = 12345.987654321;
		
		String act = String.format(Locale.US, "%.7f", d);
		String exp = "12345.9876543";
		
		assertEquals( exp, act );
		//System.out.println("act="+act);
		//System.out.println("exp="+exp);
	}
}
