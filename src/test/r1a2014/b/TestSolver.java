package test.r1a2014.b;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1a2014.b.*;

public class TestSolver {

	@Test
	public void t_ArrSort1(){
		int[] arr = new int[]{1,4,3,6,2};
		Arrays.sort(arr);
		
		String act = Integer.toString(arr[0]);
		for(int i=1; i<arr.length; i++){
			act += ("," + arr[i]);
		}
		System.out.println("t_ArrSort1() :: act=" + act);
		
	}
	
	@Test
	public void t_Edge1(){
		Edge e1 = new Edge(1,2);
		Edge e2 = new Edge(1,2);
		
		boolean exp = true;
		boolean act = e1.equals(e2);
		
		assertTrue(exp==act);
	}
	
	@Test
	public void t_Edge2(){
		Edge e1 = new Edge(1,2);
		Edge e2 = new Edge(2,1);
		
		boolean exp = true;
		boolean act = e1.equals(e2);
		
		assertTrue(exp==act);
	}
	
	@Test
	public void t_Edge3(){
		Edge e1 = new Edge(1,2);
		Edge e2 = new Edge(2,3);
		
		boolean exp = false;
		boolean act = e1.equals(e2);
		
		assertTrue(exp==act);
	}
	
	@Test
	public void t_Edge4(){
		Edge e1 = new Edge(1,2);
		
		String exp = "1-2";
		String act = e1.toString();
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_Edge5(){
		Edge e1 = new Edge(2,1);
		
		String exp = "1-2";
		String act = e1.toString();
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_Graph1(){
		int[][] edges = new int[][]{ new int[]{1,2}, new int[]{2,3} };
		int edgeNum = edges.length+1;
		
		Graph g = new Graph(edgeNum, edges);
		
		String exp1 = "[1, 2, 3] :: [1-2, 2-3]";
		String exp2 = "[1, 2, 3] :: [2-3, 1-2]";
		String act = g.toString();
		
		System.out.println("t_Graph1 :: g=" + g);
		
		assertTrue(act.equalsIgnoreCase(exp1) || act.equalsIgnoreCase(exp2));
	}
	
	@Test
	public void t_Graph2(){
		int[][] edges = new int[][]{ new int[]{1,2}, new int[]{2,3} };
		int edgeNum = edges.length+1;
		
		Graph g0 = new Graph(edgeNum, edges);
		Graph g = g0.removeNode(1);
		
		String exp1 = "[2, 3] :: [2-3]";
		String act = g.toString();
		
		System.out.println("t_Graph2 :: g=" + g);
		
		assertTrue(act.equalsIgnoreCase(exp1));
	}
	
	@Test
	public void t_Graph3(){
		int[][] edges = new int[][]{ new int[]{1,2}, new int[]{2,3} };
		int edgeNum = edges.length+1;
		
		Graph g0 = new Graph(edgeNum, edges);
		Graph g = g0.removeNode(2);
		
		String exp1 = "[1, 3] :: []";
		String act = g.toString();
		
		System.out.println("t_Graph3 :: g=" + g);
		
		assertTrue(act.equalsIgnoreCase(exp1));
	}
	
	@Test
	public void t_Graph4(){
		int[][] edges = new int[][]{ new int[]{1,2}, new int[]{2,3}, new int[]{3,4}, new int[]{3,5} };
		int edgeNum = edges.length+1;
		
		Graph g0 = new Graph(edgeNum, edges);
		
		HashSet<Integer> toBeRem = new HashSet<Integer>();
		toBeRem.add(4);
		toBeRem.add(5);
		
		Graph g = g0.removeNodes(toBeRem);
		
		String exp1 = "[1, 2, 3] :: [1-2, 2-3]";
		String act = g.toString();
		
		System.out.println("t_Graph4 :: g=" + g);
		
		assertTrue(act.equalsIgnoreCase(exp1));
	}
	
	@Test
	public void t_MaxFullBTSize1(){
		int[][] edges = new int[][]{};
				
		Graph g = new Graph(1, edges);
		
		int exp1 = 1;
		int act1 = g.getMaxFullBTSize(1);
		
		System.out.println("t_MaxFullBTSize1 :: getMaxFullBTSize(1)=" + act1);
		
		assertEquals(exp1, act1);
	}
	
	@Test
	public void t_MaxFullBTSize2(){
		int[][] edges = new int[][]{ new int[]{1,2}, new int[]{2,3} };
		int edgeNum = edges.length+1;
		
		Graph g = new Graph(edgeNum, edges);
		
		int exp1 = 3;
		int act1 = g.getMaxFullBTSize(2);
		
		System.out.println("t_MaxFullBTSize2 :: getMaxFullBTSize(2)=" + act1);
		
		assertEquals(exp1, act1);
	}
	
	@Test
	public void t_MaxFullBTSize3(){
		int[][] edges = new int[][]{ new int[]{1,2}, new int[]{2,3}, new int[]{3,4}, new int[]{3,5} };
		int edgeNum = edges.length+1;
		
		Graph g = new Graph(edgeNum, edges);
		
		int exp1 = 5;
		int act1 = g.getMaxFullBTSize(2);
		
		System.out.println("t_MaxFullBTSize3 :: getMaxFullBTSize(2)=" + act1);
		
		assertEquals(exp1, act1);
	}
	
	@Test
	public void t_MaxFullBTSize4(){
		int[][] edges = new int[][]{ new int[]{1,2}, new int[]{2,3}, new int[]{3,4}, new int[]{3,5} };
		int edgeNum = edges.length+1;
		
		Graph g = new Graph(edgeNum, edges);
		
		int exp1 = 3;
		int act1 = g.getMaxFullBTSize(3);
		
		System.out.println("t_MaxFullBTSize4 :: getMaxFullBTSize(3)=" + act1);
		
		assertEquals(exp1, act1);
	}
	
	@Test
	public void t_MaxFullBTSize5(){
		int[][] edges = new int[][]{ new int[]{1, 3}, new int[]{2, 3}, new int[]{3, 4}, new int[]{4, 6}, new int[]{5, 6}
						,new int[]{6, 7}, new int[]{7, 8}, new int[]{8, 9}, new int[]{9, 10}, new int[]{10, 11}
						,new int[]{10, 12}, new int[]{12, 13}, new int[]{13, 14}, new int[]{13, 15}
					};
		int edgeNum = edges.length+1;
		
		Graph g = new Graph(edgeNum, edges);
		
		int exp1 = 3;
		int act1 = g.getMaxFullBTSize(8);
		
		System.out.println("t_MaxFullBTSize5 :: getMaxFullBTSize(3)=" + act1);
		
		assertEquals(exp1, act1);
	}
	
	@Test
	public void t_isFullBTree1(){
		int[][] edges = new int[][]{};
		int edgeNum = 1;
		
		Graph g = new Graph(edgeNum, edges);
		
		boolean act = g.isFullBinaryTree();
		boolean exp = true;
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_isFullBTree2(){
		int[][] edges = new int[][]{new int[]{1,2}};
		int edgeNum = edges.length+1;
		
		Graph g = new Graph(edgeNum, edges);
		
		boolean act = g.isFullBinaryTree();
		boolean exp = false;
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_isFullBTree3(){
		int[][] edges = new int[][]{new int[]{1,2}, new int[]{2,3}};
		int edgeNum = edges.length+1;
		
		Graph g = new Graph(edgeNum, edges);
		
		boolean act = g.isFullBinaryTree();
		boolean exp = true;
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_isFullBTree4(){
		int[][] edges = new int[][]{ new int[]{1,2}, new int[]{2,3}, new int[]{4,5} };
		int edgeNum = 5;
		
		Graph g = new Graph(edgeNum, edges);
		
		boolean act = g.isFullBinaryTree();
		boolean exp = false;
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_isFullBTree5(){
		int[][] edges = new int[][]{new int[]{1,2}, new int[]{2,3}, new int[]{3,4}, new int[]{4,5}};
		int edgeNum = edges.length+1;
		
		Graph g = new Graph(edgeNum, edges);
		
		boolean act = g.isFullBinaryTree();
		boolean exp = false;
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_isFullBTree6(){
		HashSet<Integer> nodes = new HashSet<Integer>();
		nodes.add(1);
		nodes.add(2);
		nodes.add(4);
		nodes.add(5);
		nodes.add(6);
		HashSet<Edge> edges = new HashSet<Edge>();
		edges.add(new Edge(1,2));
		edges.add(new Edge(2,4));
		edges.add(new Edge(4,5));
		edges.add(new Edge(4,6));
		
		Graph g = new Graph(nodes, edges);
		
		boolean act = g.isFullBinaryTree();
		boolean exp = true;
		
		assertEquals(exp, act);
	}
	
	@Test 
	public void t_isFullBTree7(){
		int[][] edges = new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{3, 5}, new int[]{3, 6}, new int[]{3, 7}};
		int edgeNum = edges.length+1;
		
		Graph g = new Graph(edgeNum, edges);
		
		boolean act = g.isFullBinaryTree();
		boolean exp = false;
		
		assertEquals(exp, act);
	}
	
	@Test 
	public void t_front1(){
		int[][] edges = new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}, new int[]{3, 5}, new int[]{3, 6}, new int[]{3, 7}};
		int edgeNum = edges.length+1;
		
		Graph g = new Graph(edgeNum, edges);
		
		HashSet<Integer> act = g.getFront();
		HashSet<Integer> exp = new HashSet<Integer>();
		exp.add(1);
		exp.add(4);
		exp.add(5);
		exp.add(6);
		exp.add(7);
		
		assertEquals(exp, act);
	}
	
	@Test 
	public void t_Quad1(){
		
	}
	
	@Ignore
	public void t_BF1(){
		String[][] strs = new String[][]{
				 {"2" , "1 2"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("t_BF1(): " + act);
		
		String exp="1";
		
		assertEquals(exp, act);
	}
	
	@Ignore
	public void t_BF2(){
		String[][] strs = new String[][]{
				 {"3" , "1 2", "2 3"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("t_BF2(): " + act);
		
		String exp="0";
		
		assertEquals(exp, act);
	}
	
	@Ignore
	public void t_BF3(){
		String[][] strs = new String[][]{
				 {"4" , "1 2", "2 3", "3 4"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("t_BF3(): " + act);
		
		String exp="1";
		
		assertEquals(exp, act);
	}
	
	@Ignore
	public void t_BF4(){
		String[][] strs = new String[][]{
				 {"7" , "1 2", "2 3", "3 4", "3 5", "3 6", "3 7"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("t_BF4(): " + act);
		
		String exp="2";
		
		assertEquals(exp, act);
	}
	
	@Ignore
	public void t_BF5(){
		String[][] strs = new String[][]{
				 {"7", "4 5", "4 2", "1 2", "3 1", "6 4", "3 7"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("t_BF5(): " + act);
		
		String exp="2";
		
		assertEquals(exp, act);
	}
	
	@Ignore
	public void t_BF6(){
		String[][] strs = new String[][]{
				 {"8", "1 2", "2 3", "2 4", "4 5", "5 6", "6 7", "6 8"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("t_BF6(): " + act);
		
		String exp="3";
		
		assertEquals(exp, act);
	}
	
	@Ignore
	public void t_BF7(){
		String[][] strs = new String[][]{
				 {"14", "1 2", "2 3", "2 4", "5 7", "6 7", "7 8", "4 9", "8 9", "9 10", "11 10", "12 11", "12 13", "12 14"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("t_BF7(): " + act);
		
		String exp="7";
		
		assertEquals(exp, act);
	}
	
	@Ignore
	public void t_BF8(){
		String[][] strs = new String[][]{
				 {"15", "1 3", "2 3", "3 4", "5 6", "4 6", "6 7", "7 8", "8 9", "9 10", "10 11", "10 12", "12 13", "13 14", "13 15"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("t_BF8(): " + act);
		
		String exp="8";
		
		assertEquals(exp, act);
	}
	
	@Ignore
	public void t_BF9(){
		String[][] strs = new String[][]{
				 {"15", "1 2", "2 3", "3 4", "4 5", "4 6", "4 7", "3 8", "8 9", "8 10", "8 11", "3 12", "12 13", "12 14", "12 15"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF(raw);
		System.out.println("t_BF9(): " + act);
		
		String exp="6";
		
		assertEquals(exp, act);
	}
	
	@Ignore
	public void t_BF10(){
		String[][] strs = new String[][]{
				 {"15", "1 2", "1 3", "1 4", "1 5", "1 6", "1 7", "1 8", "1 9", "1 10", "1 11", "1 12", "1 13", "1 14", "1 15"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveBF2(raw);
		System.out.println("t_BF10(): " + act);
		
		String exp="12";
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_Quadratic1(){
		String[][] strs = new String[][]{
				 {"15", "1 2", "1 3", "1 4", "1 5", "1 6", "1 7", "1 8", "1 9", "1 10", "1 11", "1 12", "1 13", "1 14", "1 15"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveQuadratic(raw);
		System.out.println("t_Quadratic1(): " + act);
		
		String exp="12";
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_Quadratic2(){
		String[][] strs = new String[][]{
				 {"14", "1 2", "2 3", "2 4", "5 7", "6 7", "7 8", "4 9", "8 9", "9 10", "11 10", "12 11", "12 13", "12 14"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveQuadratic(raw);
		System.out.println("t_Quadratic2(): " + act);
		
		String exp="7";
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_Quadratic3(){
		String[][] strs = new String[][]{
				 {"15", "1 3", "2 3", "3 4", "5 6", "4 6", "6 7", "7 8", "8 9", "9 10", "10 11", "10 12", "12 13", "13 14", "13 15"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveQuadratic(raw);
		System.out.println("t_Quadratic3(): " + act);
		
		String exp="8";
		
		assertEquals(exp, act);
	}
	
	@Test
	public void t_Quadratic4(){
		String[][] strs = new String[][]{
				 {"15", "1 3", "2 3", "3 4", "5 6", "4 6", "6 7", "7 8", "8 9", "9 10", "10 11", "10 12", "12 13", "13 14", "13 15"}
			};
		RawInput raw = new RawInput(strs[0]);
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		
		String act=solver.solveQuadratic(raw);
		System.out.println("t_Quadratic4(): " + act);
		
		String exp="8";
		
		assertEquals(exp, act);
	}
	
}
