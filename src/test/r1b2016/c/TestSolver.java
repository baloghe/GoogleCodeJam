package test.r1b2016.c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Ignore;

import util.*;
import r1b2016.c.*;

public class TestSolver {

	
	@Ignore/*@Test*/
	public void t2_Solver(){
		
		String[] strs = new String[]{"3"
						,"HYDROCARBON COMBUSTION"
						,"QUAIL COMBUSTION"
						,"QUAIL BEHAVIOR"
					  };
		
		System.out.println("t_Solver for r1b2016.c:");
		RawInput raw = new RawInput( strs );
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		System.out.println(raw.toString() + " -> " + solver.solveCase(raw));
		
	}
	
	@Ignore/*@Test*/
	public void t3_Solver(){
		
		String[] strs = new String[]{"3"
						,"CODE JAM"
						,"SPACE JAM"
						,"PEARL JAM"
					  };
		
		System.out.println("t_Solver for r1b2016.c:");
		RawInput raw = new RawInput( strs );
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		System.out.println(raw.toString() + " -> " + solver.solveCase(raw));
		
	}
	
	@Ignore/*@Test*/
	public void t4_Solver(){
		
		String[] strs = new String[]{"2"
						,"INTERGALACTIC PLANETARY"
						,"PLANETARY INTERGALACTIC"
					  };
		
		System.out.println("t_Solver for r1b2016.c:");
		RawInput raw = new RawInput( strs );
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		System.out.println(raw.toString() + " -> " + solver.solveCase(raw));
		
	}
	
	@Ignore/*@Test*/
	public void t5_Solver(){
		
		String[] strs = new String[]{"7"
						,"A A"
						,"B B"
						,"C C"
						,"D D"
						,"A B"
						,"A C"
						,"B D"
					  };
		
		System.out.println("t_Solver for r1b2016.c:");
		RawInput raw = new RawInput( strs );
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		System.out.println(raw.toString() + " -> " + solver.solveCase(raw));
		
	}
	
	@Ignore
	public void t6_Solver(){
		
		String[] strs = new String[]{"8"
				,"AD DW"
				,"UG DW"
				,"UG FH"
				,"TJ HI"
				,"AV JL"
				,"OD PI"
				,"AI RM"
				,"EL SV"
					  };
		
		System.out.println("t_Solver for r1b2016.c:");
		RawInput raw = new RawInput( strs );
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		System.out.println(raw.toString() + " -> " + solver.solveCase(raw));
		
	}
	
	@Ignore
	public void t7_Solver(){
		
		String[] strs = new String[]{"15"
				,"BJ CY"
				,"BJ OU"
				,"EB YM"
				,"EB ZO"
				,"FM CY"
				,"FM YM"
				,"LN PI"
				,"LN QW"
				,"MS QL"
				,"MS QW"
				,"NG PI"
				,"SS AU"
				,"SS OU"
				,"YS QL"
				,"YS ZO"
					  };
		
		System.out.println("t_Solver for r1b2016.c:");
		RawInput raw = new RawInput( strs );
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		System.out.println(raw.toString() + " -> " + solver.solveCase(raw));
		
	}
	
	@Ignore
	public void t_genLargeProblem(){
		String[] f = new String[]{"A","B","C","D","E","F"};
		String[] s = new String[]{"A","B","C","D","E","F"};
		int rownum = f.length * s.length;
		String[] rawInput = new String[rownum+1];
		rawInput[0] = Integer.toString(rownum);
		int idx=1;
		for(String ff : f){
			for(String ss : s){
				rawInput[idx] = ff + " " + ss;
				idx++;
			}
		}
		
		RawInput raw = new RawInput( rawInput );
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		System.out.println("t_genLargeProblem for r1b2016.c:");
		System.out.println(raw.toString() + " -> " + solver.solveCase(raw));
	}
	
	@Ignore
	public void t_BPMatcher(){
		String[][] rawList = new String[][]{
			 {"A","Q"}
			,{"A","R"}
			,{"C","P"}
			,{"C","S"}
			,{"D","R"}
			,{"E","R"}
			,{"E","S"}
			,{"F","U"}
		};
		ArrayList<String[]> edgeList = new ArrayList<String[]>();
		for(String[] ss : rawList){
			edgeList.add(ss);
		}
		BipartiteGraphMatcher matcher = new BipartiteGraphMatcher(edgeList);
		
		HashMap<GraphNode, GraphNode> result = matcher.match();
		System.out.println("t_BPMatcher for r1b2016.c:");
		for(Map.Entry<GraphNode, GraphNode> e : result.entrySet()){
			System.out.println("  " + e.getKey() + "->" + e.getValue());
		}
	}
	
	@Ignore
	public void t2_BPMatcher(){
		String[] rawList = new String[]{
				 "BJ CY"
				,"BJ OU"
				,"EB YM"
				,"EB ZO"
				,"FM CY"
				,"FM YM"
				,"LN PI"
				,"LN QW"
				,"MS QL"
				,"MS QW"
				,"NG PI"
				,"SS AU"
				,"SS OU"
				,"YS QL"
				,"YS ZO"
		};
		ArrayList<String[]> edgeList = new ArrayList<String[]>();
		for(String ss : rawList){
			String[] sa = ss.split(" ");
			edgeList.add(sa);
		}
		BipartiteGraphMatcher matcher = new BipartiteGraphMatcher(edgeList);
		
		HashMap<GraphNode, GraphNode> result = matcher.match();
		System.out.println("t_BPMatcher for r1b2016.c:");
		for(Map.Entry<GraphNode, GraphNode> e : result.entrySet()){
			System.out.println("  " + e.getKey() + "->" + e.getValue());
		}
	}
	
	@Test
	public void t_GCJExample(){
		String[] strs = new String[]{"7"
				,"HYDROCARBON COMBUSTION"
				,"BIOMASS COMBUSTION"
				,"QUAIL COMBUSTION"
				,"QUAIL BEHAVIOR"
				,"QUAIL CONTAMINATION"
				,"GROUNDWATER CONTAMINATION"
				,"GROUNDWATER HYDROLOGY"
					  };
		
		System.out.println("t_GCJExample for r1b2016.c:");
		RawInput raw = new RawInput( strs );
		ProblemSolver solver = new ProblemSolver(ProblemSolution.RUNMODE_SMALL);
		System.out.println(raw.toString() + " -> " + solver.solveCase(raw));
	}
}
