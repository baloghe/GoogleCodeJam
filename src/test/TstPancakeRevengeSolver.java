package test;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashSet;
import java.util.Vector;

import practice.*;
import rq2016.*;
import util.*;

public class TstPancakeRevengeSolver {

	private CaseSolver solver;
	
	@Before
	public void init(){
		solver = new PancakeRevengeSolver();
	}
	
	@Test @Ignore
	public void t_stack1(){
		String base = "--+-++";
		PancakeRevengeStack s = new PancakeRevengeStack(base);
		PancakeRevengeStack c = s.copy();
		
		System.out.println("t_stack1:");
		System.out.println("   s=" + s.toString() + "  , size=" + s.size());
		System.out.println("   c=" + c.toString() + "  , size=" + c.size());
		
		s.flipStackPart(2);
		System.out.println("   s flipped=" + s.toString() + "  , size=" + s.size());
		System.out.println("   c        =" + c.toString() + "  , size=" + c.size());
	}
	
	@Test @Ignore
	public void t_stack2(){
		String base = "--+-++";
		PancakeRevengeStack s = new PancakeRevengeStack(base);
		PancakeRevengeStack c = s.copy();
		
		HashSet<PancakeRevengeStack> set = new HashSet<PancakeRevengeStack>();
		set.add(s);
		set.add(c);
		
		System.out.println("t_stack2:");
		System.out.println("   set.size=" + set.size());
		System.out.println("   s.hashCode=" + s.hashCode());
		System.out.println("   c.hashCode=" + c.hashCode());
		System.out.println("   s=?=c =" + s.equals(c));
		
	}
	
	@Test @Ignore
	public void t_dynrec1(){
		String base = "-+-";
		PancakeRevengeStack bs = new PancakeRevengeStack(base);
		PRDyntableRecord rec = new PRDyntableRecord(bs,1, null);
				
		System.out.println("t_dynrec1:");
		System.out.println("   s=" + bs.toString() + "  , size=" + bs.size());
		System.out.println("   rec=" + rec.toString());
		
		for(PancakeRevengeStack s : rec.getDescendants()){
			PRDyntableRecord drec = new PRDyntableRecord(s, rec.getDescendantsLevel()+1, null);
			System.out.println("      desc rec=" + drec.toString());
			
			for(PancakeRevengeStack s2 : drec.getDescendants()){
				PRDyntableRecord drec2 = new PRDyntableRecord(s2, drec.getDescendantsLevel()+1, null);
				System.out.println("         desc rec=" + drec2.toString());
			}
		}
	}
	
	@Test @Ignore
	public void t_flip1(){
		
		String str = "-";
		
		PancakeRevengeStack stack = new PancakeRevengeStack(str);
		
		System.out.println("t_flip1:");
		System.out.println("   " + stack.toString() + " -> OK=" + stack.isOK());
		
		stack.flipStackPart(10);
		System.out.println("   " + stack.toString() + " -> OK=" + stack.isOK());
		
	}
	
	@Test @Ignore
	public void t_flip2(){
		
		String str = "-++-";

		PancakeRevengeStack stack = new PancakeRevengeStack(str);
		
		System.out.println("t_flip2:");
		System.out.println("   " + stack.toString() + " -> OK=" + stack.isOK());
		
		stack.flipStackPart(3);
		System.out.println("   " + stack.toString() + " -> OK=" + stack.isOK());
		
		stack.flipStackPart(2);
		System.out.println("   " + stack.toString() + " -> OK=" + stack.isOK());
		
		stack.flipStackPart(3);
		System.out.println("   " + stack.toString() + " -> OK=" + stack.isOK());
		
		stack.flipStackPart(4);
		System.out.println("   " + stack.toString() + " -> OK=" + stack.isOK());
		
	}
	
	@Test
	public void t_solve1(){
		
		String[] str = new String[]{
				"+"
			};

		RawInput r = new RawInput(str);
		
		System.out.println("t_solve1:");
		System.out.println("   orig=" + r);
		System.out.println("   sol  =" + solver.solveCase(r));
		System.out.println("   solBF=" + ((PancakeRevengeSolver)solver).solveCaseBF(r));
		
	}
	
	@Test
	public void t_solve2(){
		
		String[] str = new String[]{
				"--"
			};

		RawInput r = new RawInput(str);
		
		System.out.println("t_solve2:");
		System.out.println("   orig=" + r);
		System.out.println("   sol  =" + solver.solveCase(r));
		System.out.println("   solBF=" + ((PancakeRevengeSolver)solver).solveCaseBF(r));
		
	}
	
	@Test
	public void t_solve3(){
		
		String[] str = new String[]{
				"+-"
			};

		RawInput r = new RawInput(str);
		
		System.out.println("t_solve3:");
		System.out.println("   orig=" + r);
		System.out.println("   sol  =" + solver.solveCase(r));
		System.out.println("   solBF=" + ((PancakeRevengeSolver)solver).solveCaseBF(r));
		
	}
	
	@Test
	public void t_solve4(){
		
		String[] str = new String[]{
				"--+-"
			};

		RawInput r = new RawInput(str);
		
		System.out.println("t_solve4:");
		System.out.println("   orig=" + r);
		System.out.println("   sol  =" + solver.solveCase(r));
		System.out.println("   solBF=" + ((PancakeRevengeSolver)solver).solveCaseBF(r));
		
	}
	
	@Test
	public void t_solve5(){
		
		String[] str = new String[]{
				"+-+-+"
			};

		RawInput r = new RawInput(str);
		
		System.out.println("t_solve5:");
		System.out.println("   orig=" + r);
		System.out.println("   sol  =" + solver.solveCase(r));
		System.out.println("   solBF=" + ((PancakeRevengeSolver)solver).solveCaseBF(r));
		
	}
	
}

