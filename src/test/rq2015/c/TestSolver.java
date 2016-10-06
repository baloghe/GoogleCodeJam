package test.rq2015.c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import rq2015.c.*;

public class TestSolver {

	
	@Ignore
	public void t_Solver(){
		
		String[][] strs = new String[][]{
				 new String[]{"2 1","ik"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		System.out.println("t_Solver for rq2015.c:");
		System.out.println(strs[0][0] + " | " + strs[0][1] + " -> " + solver.solveCase(raw));
	}
	
	@Ignore/*@Ignore*/
	public void t2_Solver(){
		
		System.out.println("t_Solver for rq2015.c:");
		
		Quaternion q = new Quaternion(Quaternion.I, -3);
		System.out.println( "q=" + q );
		
		Quaternion q1 = new Quaternion(Quaternion.I);
		Quaternion q2 = new Quaternion(Quaternion.J);
		
		System.out.println(q1 + " * " + q2 + " = " + q1.mult(q2));
		System.out.println(q2 + " * " + q1 + " = " + q2.mult(q1));
		System.out.println(q1 + "^2 = " + q1.mult(q1));
		System.out.println(q2 + "^2 = " + q2.mult(q2));
		
		System.out.println("MultTbl:");
		Quaternion qe = new Quaternion(Quaternion.E);
		Quaternion qi = new Quaternion(Quaternion.I);
		Quaternion qj = new Quaternion(Quaternion.J);
		Quaternion qk = new Quaternion(Quaternion.K);
		ArrayList<Quaternion> tbl = new ArrayList<Quaternion>();
		tbl.add(qe);
		tbl.add(qi);
		tbl.add(qj);
		tbl.add(qk);
		for(Quaternion left : tbl){
			String str = "";
			for(Quaternion right : tbl){
				str += (left.mult(right) + "|");
			}
			System.out.println(str);
		}
		
		ArrayList<Quaternion> lst = new ArrayList<Quaternion>();
		lst.add(q1);
		lst.add(q2);
		System.out.println("chainMult " + q1 + " * " + q2 + " = " + Quaternion.multiplyChain(lst));
		
		System.out.println("chainMult jij   =" + Quaternion.parseList("jij") );
		System.out.println("chainMult iji   =" + Quaternion.parseList("iji") );
		System.out.println("chainMult jijiji=" + Quaternion.parseList("jijiji") );
		
	}
	
	@Ignore
	public void t3_Solver(){
		
		String generatedString = "abc";
		
		int len = generatedString.length();
		for(int s1=1; s1 < len-1; s1++){
			for(int s2=s1+1; s2 < len; s2++){
				System.out.println("s1=" + s1 + ", s2=" + s2);
				String act1 = generatedString.substring(0,s1);
				String act2 = generatedString.substring(s1,s2);
				String act3 = generatedString.substring(s2,len);
				System.out.println( "   " + s1 + "," + s2 + " -> " + act1 + "|" + act2 + "|" + act3 );
			}//next s2
		}//next s1
	}
	
	@Ignore
	public void t4_Solver(){
		Quaternion ijk = Quaternion.parseList("ijk");
		
		String[] strs = new String[]{
				"ik"
				,"ijk"
				,"kji"
				,"jijijijijiji"
				,"iiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"
				,"iiiiijjjjjkkkkk"
		};
		
		for(String s : strs){
			Quaternion qFullStr = Quaternion.parseList( s );
			System.out.println( s + " can possibly reduce to ijk : " + ( qFullStr.equals(ijk) ? "YES" : "NO") );
		}
	}
	
	
	@Ignore
	public void t5_Solver(){
		
		String[][] strs = new String[][]{
				 new String[]{"2 18","ji"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_LARGE);
		
		System.out.println("t_Solver for rq2015.c:");
		System.out.println(strs[0][0] + " | " + strs[0][1] + " -> " + solver.solveCase(raw));
	}
	
	
	@Test
	public void t6_Solver(){
		
		//generate long string
		String[] ijk = new String[]{"i","j","k"};
		int strlen=10000;
		String outstr = "";
		
		Random randomGenerator = new Random();
		for(int i=0; i<strlen;i++){
			outstr += ijk[randomGenerator.nextInt(3)];
		}
		
		//do it as usual...
		String[][] strs = new String[][]{
				 //new String[]{Integer.toString(strlen) + " " + Long.toString( ((long)Integer.MAX_VALUE) * ((long)60) ), outstr}
				new String[]{"2 " + Long.toString( ((long)Integer.MAX_VALUE) * ((long)54) ), "ji"}
				//new String[]{"2 54","ji"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_LARGE);
		
		System.out.println("t_Solver for rq2015.c:");
		System.out.println(strs[0][0] + " | " + (strs[0][1] + "          ").substring(0, 10) + "..." + " -> " + solver.solveCase(raw));
	}
	
}
