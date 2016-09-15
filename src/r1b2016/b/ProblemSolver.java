package r1b2016.b;

import util.RawInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private String[] origCJ;
	private int arrLen;
	private int[] arrCorig;
	private int[] arrJorig;
	
	/**
	 * constructor. The expected size of the problem might require different approaches, therefore runmode is required as a parameter   
	 * @param inRunMode expected RUNMODE_SMALL or RUNMODE_LARGE (see ProblemSolution)
	 */
	public ProblemSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	
	@Override
	public String solveCase(RawInput inCase) {
		init(inCase);
		
		//Build a tree from what we know
		RootNode root = new RootNode();
		
		ArrayList<TreeNode> lastLevel;
		//build direct children of Root
		lastLevel = new ArrayList<TreeNode>();
		if(arrCorig[0] > -1 && arrJorig[0] > -1){          //DD  -- simply add them
			root.addChild( new TreeNode( arrCorig[0] , arrJorig[0] ) );
		} else if(arrCorig[0] == -1 && arrJorig[0] > -1) { //?D  -- D-1,D,D+1,0D,9D
			root.addChild( new TreeNode( arrJorig[0] , arrJorig[0] ) );
			root.addChild( new TreeNode( 0 , arrJorig[0] ) );
			root.addChild( new TreeNode( 9 , arrJorig[0] ) );
			if(arrJorig[0] > 0) root.addChild( new TreeNode( arrJorig[0]-1 , arrJorig[0] ) );
			if(arrJorig[0] < 9) root.addChild( new TreeNode( arrJorig[0]+1 , arrJorig[0] ) );
		} else if(arrCorig[0] > -1 && arrJorig[0] == -1) { //D?  -- D-1,D,D+1,D0,D9
			root.addChild( new TreeNode( arrCorig[0] , arrCorig[0] ) );
			root.addChild( new TreeNode( arrCorig[0] , 0 ) );
			root.addChild( new TreeNode( arrCorig[0] , 9 ) );
			if(arrCorig[0] > 0) root.addChild( new TreeNode( arrCorig[0] , arrCorig[0]-1 ) );
			if(arrCorig[0] < 9) root.addChild( new TreeNode( arrCorig[0] , arrCorig[0]+1 ) );
		} else {                                           //??  -- 01,10,00
			root.addChild( new TreeNode( 0 , 1 ) );
			root.addChild( new TreeNode( 1 , 0 ) );
			root.addChild( new TreeNode( 0 , 0 ) );
		}
		lastLevel.addAll(root.getChildren());
		
		//build further levels
		int pos=1;
		while(pos < arrLen){
			ArrayList<TreeNode> tmpLevel = new ArrayList<TreeNode>();
			//evaluate the position just as at the root
			//append candidates to the previous level
			if(arrCorig[pos] > -1 && arrJorig[pos] > -1){          //DD  -- simply add them. LEAD inherited from parent
				for(TreeNode pn : lastLevel){
					int plead = pn.getLead();
					TreeNode n = new TreeNode( arrCorig[pos] , arrJorig[pos], plead );
					pn.addChild( n );
					tmpLevel.add( n );
					//System.out.println("TreeBuild DD: " + pn + " -> " + n);
				}
			} else if(arrCorig[pos] == -1 && arrJorig[pos] > -1) { //?D  -- D-1,D,D+1,0D,9D
				for(TreeNode pn : lastLevel){
					int plead = pn.getLead(); //0: C=J, -1: C<J, +1: C>J
					
					if(plead==0){
						TreeNode n = new TreeNode( arrJorig[pos] , arrJorig[pos], plead ); //LEAD inherited from parent and remains unchanged
						pn.addChild( n );
						tmpLevel.add( n );
						//System.out.println("TreeBuild ?D: " + pn + " -> " + n);
					}
					if(plead > 0){  
						TreeNode n2 = new TreeNode( 0 , arrJorig[pos], plead ); //LEAD inherited from parent
						pn.addChild( n2 );
						tmpLevel.add( n2 );
					}
					//System.out.println("TreeBuild ?D: " + pn + " -> " + n2);
					if(plead < 0){
						TreeNode n3 = new TreeNode( 9 , arrJorig[pos], plead ); //LEAD inherited from parent
						pn.addChild( n3 );
						tmpLevel.add( n3 );
					}
					//System.out.println("TreeBuild ?D: " + pn + " -> " + n3);
					if(plead == 0 && arrJorig[pos] > 0){  //LEAD to be calculated
						TreeNode x = new TreeNode( arrJorig[pos]-1 , arrJorig[pos] ); //LEAD to be calculated
						pn.addChild( x );
						tmpLevel.add( x );
						//System.out.println("TreeBuild ?D: " + pn + " -> " + x);
					}
					if(plead == 0 && arrJorig[pos] < 9){  //LEAD to be calculated
						TreeNode x = new TreeNode( arrJorig[pos]+1 , arrJorig[pos] ); //LEAD to be calculated
						pn.addChild( x );
						tmpLevel.add( x );
						//System.out.println("TreeBuild ?D: " + pn + " -> " + x);
					}
				}
			} else if(arrCorig[pos] > -1 && arrJorig[pos] == -1) { //D?  -- D-1,D,D+1,D0,D9
				for(TreeNode pn : lastLevel){
					int plead = pn.getLead(); //0: C=J, -1: C<J, +1: C>J
					
					if(plead==0){
						TreeNode n = new TreeNode( arrCorig[pos] , arrCorig[pos], plead );
						pn.addChild( n );
						tmpLevel.add( n );
						//System.out.println("TreeBuild D?: " + pn + " -> " + n);
					}
					if(plead < 0){
						TreeNode n2 = new TreeNode( arrCorig[pos] , 0, plead );
						pn.addChild( n2 );
						tmpLevel.add( n2 );
						//System.out.println("TreeBuild D?: " + pn + " -> " + n2);
					}
					if(plead > 0){
						TreeNode n3 = new TreeNode( arrCorig[pos] , 9, plead );
						pn.addChild( n3 );
						tmpLevel.add( n3 );
						//System.out.println("TreeBuild D?: " + pn + " -> " + n3);
					}
					if(plead == 0 && arrCorig[pos] > 0){
						TreeNode x = new TreeNode( arrCorig[pos] , arrCorig[pos]-1 );
						pn.addChild( x );
						tmpLevel.add( x );
						//System.out.println("TreeBuild D?: " + pn + " -> " + x);
					}
					if(plead == 0 && arrCorig[pos] < 9){
						TreeNode x = new TreeNode( arrCorig[pos] , arrCorig[pos]+1 );
						pn.addChild( x );
						tmpLevel.add( x );
						//System.out.println("TreeBuild D?: " + pn + " -> " + x);
					}
				}
			} else {                                           //??  -- 01,10,00,09,90
				for(TreeNode pn : lastLevel){
					int plead = pn.getLead(); //0: C=J, -1: C<J, +1: C>J
					
					if(plead==0){
						TreeNode n1 = new TreeNode( 0 , 1 , plead );
						pn.addChild( n1 );
						tmpLevel.add( n1 );
						//System.out.println("TreeBuild ??: " + pn + " -> " + n1);
						TreeNode n2 = new TreeNode( 1 , 0 , plead );
						pn.addChild( n2 );
						tmpLevel.add( n2 );
						//System.out.println("TreeBuild ??: " + pn + " -> " + n2);
						TreeNode n3 = new TreeNode( 0 , 0 , plead );
						pn.addChild( n3 );
						tmpLevel.add( n3 );
						//System.out.println("TreeBuild ??: " + pn + " -> " + n3);
					}
					if(plead > 0){
						TreeNode n4 = new TreeNode( 0 , 9 , plead );
						pn.addChild( n4 );
						tmpLevel.add( n4 );
						//System.out.println("TreeBuild ??: " + pn + " -> " + n4);
					}
					if(plead < 0){
						TreeNode n5 = new TreeNode( 9 , 0 , plead );
						pn.addChild( n5 );
						tmpLevel.add( n5 );
						//System.out.println("TreeBuild ??: " + pn + " -> " + n5);
					}
				}
			}
			//step ahead
			pos++;
			//lastLevel = copyList( tmpLevel );
			lastLevel = tmpLevel;
		}
		
		ArrayList<ScorePair> candidates = root.DepthWalkOutput();
		/*System.out.println("   candidates:");
		for(ScorePair sp : candidates){
			System.out.println("     " + sp);
		}
		*/
		//Choose the best one
		Collections.sort(candidates);
		return candidates.get(0).getSolutionString();
	}
	
	private ArrayList<TreeNode> copyList(ArrayList<TreeNode> inList){
		ArrayList<TreeNode> ret = new ArrayList<TreeNode>();
		for(TreeNode n : inList){
			ret.add(n);
		}
		return ret;
	}
	
	public String complicated_dead_end(RawInput inCase) {
		init(inCase);
		//System.out.println("init done. arrLen=" + arrLen);
		//match arrays
		int lead1 = 0; //C>J => -1; J>C: +1, equal: 0
		int lead2 = 0; //lead1=0 and C=J=? => try 2nd version: C<J
		int lead3 = 0; //lead1=0 and C=J=? => try 3rd version: C>J
		int[] arrC1 = new int[arrLen];
		int[] arrJ1 = new int[arrLen];
		int[] arrC2 = new int[arrLen];
		int[] arrJ2 = new int[arrLen];
		int[] arrC3 = new int[arrLen];
		int[] arrJ3 = new int[arrLen];
		for(int i=0; i<arrLen; i++){
			arrC1[i] = arrCorig[i];
			arrC2[i] = arrCorig[i];
			arrC3[i] = arrCorig[i];
			arrJ1[i] = arrJorig[i];
			arrJ2[i] = arrJorig[i];
			arrJ3[i] = arrJorig[i];
		}
		for(int i=0; i<this.arrLen; i++){
			/*
			String o = "i=" + i + ", before: C1=" + arrC1[i] + ", J1=" + arrJ1[i] 
					 + ", C2=" + arrC2[i] + ", J2=" + arrJ2[i]
					 + ", C3=" + arrC3[i] + ", J3=" + arrJ3[i]
					 + ", after: ";
			*/
			//catch first occasion when still both leads are 0 
			//  and both C[.] and J[.] contain ?
			if(   lead1==0 && lead2==0 && lead3==0
			   && (arrCorig[i] < 0 || arrJorig[i] < 0) ){
				//version 1: C:=J:=0
				lead1 = 0;
				if(arrCorig[i] < 0 && arrJorig[i] < 0){ 
					arrC1[i] = 0;
					arrJ1[i] = 0;
				} else if(arrCorig[i] < 0){
					arrC1[i] = arrJ1[i];
				} else{
					arrJ1[i] = arrC1[i];
				}
				//version 2: C<J => C:=9, J:=0
				lead2 = 1;
				if(arrCorig[i] < 0 && arrJorig[i] < 0){ 
					arrC2[i] = 0;
					arrJ2[i] = 1;
				} else if(arrCorig[i] < 0){
					arrC2[i] = Math.min(arrJ2[i]-1,0);
				} else{
					arrJ2[i] = Math.max(arrC2[i]+1,9);
				}
				//version 3: C>J => C:=0, J:=9
				lead3 = -1;
				if(arrCorig[i] < 0 && arrJorig[i] < 0){ 
					arrC3[i] = 1;
					arrJ3[i] = 0;
				} else if(arrCorig[i] < 0){
					arrC3[i] = Math.max(arrJ3[i]+1,9);
				} else if(arrC3[i]>0){
					arrJ3[i] = Math.min(arrC3[i]-1,0);
				} 
			} else {
			
				//version 1
				if(arrC1[i] < 0 && arrJ1[i] < 0){
					if(lead1==0){ 
						arrC1[i] = 0;
						arrJ1[i] = 0;
					} else if(lead1 < 0){
						arrC1[i] = 0;
						arrJ1[i] = 9;
					} else if(lead1 > 0){
						arrC1[i] = 9;
						arrJ1[i] = 0;
					}
				} else if(arrC1[i] >= 0 && arrJ1[i] < 0){
					if(lead1==0){
						arrJ1[i] = arrC1[i];
					} else if(lead1<0){
						arrJ1[i] = 9;
					} else {
						arrJ1[i] = 0;
					}
				} else if(arrC1[i] < 0 && arrJ1[i] >= 0){
					if(lead1==0){
						arrC1[i] = arrJ1[i];
					} else if(lead1<0){
						arrC1[i] = 0;
					} else {
						arrC1[i] = 9;
					}
				} else {
					//OK as is
				}
				
				//version 2
				if(arrC2[i] < 0 && arrJ2[i] < 0){
					if(lead2==0){ 
						arrC2[i] = 0;
						arrJ2[i] = 0;
					} else if(lead2 < 0){
						arrC2[i] = 0;
						arrJ2[i] = 9;
					} else if(lead2 > 0){
						arrC2[i] = 9;
						arrJ2[i] = 0;
					}
				} else if(arrC2[i] >= 0 && arrJ2[i] < 0){
					if(lead2==0){
						arrJ2[i] = arrC2[i];
					} else if(lead2<0){
						arrJ2[i] = 9;
					} else {
						arrJ2[i] = 0;
					}
				} else if(arrC2[i] < 0 && arrJ2[i] >= 0){
					if(lead2==0){
						arrC2[i] = arrJ2[i];
					} else if(lead2<0){
						arrC2[i] = 0;
					} else {
						arrC2[i] = 9;
					}
				} else {
					//OK as is
				}
				
				//version 3
				if(arrC3[i] < 0 && arrJ3[i] < 0){
					if(lead3==0){ 
						arrC3[i] = 0;
						arrJ3[i] = 0;
					} else if(lead3 < 0){
						arrC3[i] = 0;
						arrJ3[i] = 9;
					} else if(lead3 > 0){
						arrC3[i] = 9;
						arrJ3[i] = 0;
					}
				} else if(arrC3[i] >= 0 && arrJ3[i] < 0){
					if(lead3==0){
						arrJ3[i] = arrC3[i];
					} else if(lead3<0){
						arrJ3[i] = 9;
					} else {
						arrJ3[i] = 0;
					}
				} else if(arrC3[i] < 0 && arrJ3[i] >= 0){
					if(lead3==0){
						arrC3[i] = arrJ3[i];
					} else if(lead3<0){
						arrC3[i] = 0;
					} else {
						arrC3[i] = 9;
					}
				} else {
					//OK as is
				}
	
				//update lead only when it is still zero AND the two arrays' prefixes differ
				//version 1
				if(lead1==0){
					if(arrC1[i] > arrJ1[i]){
						lead1 = -1;
					} else if(arrC1[i] < arrJ1[i]){
						lead1 = 1;
					}
				}
				//version 2
				if(lead2==0){
					if(arrC2[i] > arrJ2[i]){
						lead2 = -1;
					} else if(arrC2[i] < arrJ2[i]){
						lead2 = 1;
					}
				}
				//version 3
				if(lead3==0){
					if(arrC3[i] > arrJ3[i]){
						lead3 = -1;
					} else if(arrC3[i] < arrJ3[i]){
						lead3 = 1;
					}
				}
			}//END IF: catch first occasion when still both leads are 0 ...
			/*
			System.out.println(o + "C1=" + arrC1[i] + ", J1=" + arrJ1[i] + ", lead1=" + lead1
					+ ", C2=" + arrC2[i] + ", J2=" + arrJ2[i] + ", lead2=" + lead2
					+ ", C3=" + arrC3[i] + ", J3=" + arrJ2[i] + ", lead3=" + lead3
					);
			*/
		}
		
		//print versions
		/*
		System.out.println("Normal out");
		System.out.println("  v1: " + util.Util.intArrayToString(arrC1, "") + " " + util.Util.intArrayToString(arrJ1, ""));
		System.out.println("  v2: " + util.Util.intArrayToString(arrC2, "") + " " + util.Util.intArrayToString(arrJ2, ""));
		System.out.println("  v3: " + util.Util.intArrayToString(arrC3, "") + " " + util.Util.intArrayToString(arrJ3, ""));
		*/
		
		//eval cases:
		//	1st by diff
		//  2nd by C->min
		int res = 0;
		long outC1 = Long.parseLong(util.Util.intArrayToString(arrC1, ""));
		long outJ1 = Long.parseLong(util.Util.intArrayToString(arrJ1, ""));
		long outC2 = Long.parseLong(util.Util.intArrayToString(arrC2, ""));
		long outJ2 = Long.parseLong(util.Util.intArrayToString(arrJ2, ""));
		long outC3 = Long.parseLong(util.Util.intArrayToString(arrC3, ""));
		long outJ3 = Long.parseLong(util.Util.intArrayToString(arrJ3, ""));
		
		Long diff1 = Math.abs(outC1 - outJ1);
		Long diff2 = Math.abs(outC2 - outJ2);
		Long diff3 = Math.abs(outC3 - outJ3);
		
		if(   (diff1 < Math.min(diff2, diff3))
		   || (diff1 == diff2 && diff1 < diff3 && outC1 < outC2)
		   || (diff1 == diff2 && diff1 < diff3 && outC1 == outC2 && outJ1 < outJ2)
		   || (diff1 == diff3 && diff1 < diff2 && outC1 < outC3)
		   || (diff1 == diff3 && diff1 < diff2 && outC1 == outC3 && outJ1 < outJ3)){
			res = 1;
		} else if(   (diff2 < Math.min(diff1, diff3))
				  || (diff2 == diff1 && diff2 < diff3 && outC2 < outC1)
				  || (diff2 == diff1 && diff2 < diff3 && outC2 == outC1 && outJ2 < outJ1)
				  || (diff2 == diff3 && diff2 < diff1 && outC2 < outC3)
				  || (diff2 == diff3 && diff2 < diff1 && outC2 == outC3 && outJ2 < outJ3)){
			res = 2;
		} else if(   (diff3 < Math.min(diff1, diff2))
			      || (diff3 == diff1 && diff3 < diff2 && outC3 < outC1)
			      || (diff3 == diff1 && diff3 < diff2 && outC3 == outC1 && outJ3 < outJ1)
			      || (diff3 == diff2 && diff3 < diff1 && outC3 < outC2)
			      || (diff3 == diff2 && diff3 < diff1 && outC3 == outC2 && outJ3 < outJ2)){
			res = 3;
		}
		
		//compose output
		String ret = "";
		if(res==1){
			ret = util.Util.intArrayToString(arrC1, "")
				+ " "
				+ util.Util.intArrayToString(arrJ1, "")
				;
		} else if(res==2){
			ret = util.Util.intArrayToString(arrC2, "")
				+ " "
				+ util.Util.intArrayToString(arrJ2, "")
				;
		} else {
			ret = util.Util.intArrayToString(arrC3, "")
				+ " "
				+ util.Util.intArrayToString(arrJ3, "")
				;
		}
				  
		return ret;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		origCJ = inCase.getData()[0].split(" ");
		
		//ArrayList<Integer> xx = Util.splitStringToInt(inCase.getData()[0], null);
		arrLen = origCJ[0].length();
		arrCorig = new int[arrLen];
		arrJorig = new int[arrLen];
		
		arrCorig = fillArray(arrCorig, origCJ[0]);
		arrJorig = fillArray(arrJorig, origCJ[1]);
	}
	
	public int[] fillArray(int[] inArr, String inStr){
		for(int i=0; i<inArr.length; i++){
			String s = inStr.substring(i, i+1);
			if(s.equalsIgnoreCase("?")){
				inArr[i] = -1;
			} else {
				int d = Integer.parseInt(s);
				inArr[i] = d;
			}
		}
		return inArr;
	}
	
	public int[] copyIntArr(int[] inIntArr){
		if(inIntArr==null) return null;
		if(inIntArr.length==0) return new int[0];
		int[] ret = new int[inIntArr.length];
		for(int i=0; i<inIntArr.length; i++){
			ret[i] = inIntArr[i];
		}
		return ret;
	}
	
	public String solveBF(RawInput inCase){
		init(inCase);
		String repC = origCJ[0].replaceAll("\\?", "\\\\d{1}");
		String repJ = origCJ[1].replaceAll("\\?", "\\\\d{1}");
		//System.out.println("BF :: repC=" + repC);
		Pattern pC = Pattern.compile(repC);
		Pattern pJ = Pattern.compile(repJ);
		
		Long minC = Long.parseLong( origCJ[0].replace('?', '0') );
		Long minJ = Long.parseLong( origCJ[1].replace('?', '0') );
		Long maxC = Long.parseLong( origCJ[0].replace('?', '9') );
		Long maxJ = Long.parseLong( origCJ[1].replace('?', '9') );
		
		Long ifrom = (minC > minJ ? minJ : minC);
		Long ito = (maxC < maxJ ? maxJ : maxC);
		
		//System.out.println("BF :: ifrom=" + ifrom + ", ito=" + ito);
		
		String pfx = "";
		for(int i=0; i<origCJ[0].length(); i++){
			pfx += "0";
		}
		//System.out.println("BF :: pfx=" + pfx);
		
		Matcher mC, mJ;
		ArrayList<Long> list = new ArrayList<Long>();
		for(long i = ifrom; i<=ito; i++){
			String s = pfx + i;
			s = s.substring(s.length() - pfx.length());
			//System.out.println("BF :: i=" + i + " -> s=" + s);
			mC = pC.matcher(s);
			mJ = pJ.matcher(s);
			if(mC.matches() || mJ.matches()){
				list.add(new Long(i));
				//System.out.println("BF :: Added=" + i);
			}
		}
		
		long myC = 0;
		long myJ = 0;
		long cmax = Long.MAX_VALUE;
		long scorediff = Long.MAX_VALUE;
		for(Long c : list){
			for(Long j : list){
				String sC = pfx + c;
				sC = sC.substring(sC.length() - pfx.length());
				String sJ = pfx + j;
				sJ = sJ.substring(sJ.length() - pfx.length());
				
				mC = pC.matcher(sC);
				mJ = pJ.matcher(sJ);
				
				if(mC.matches() && mJ.matches()){
					long locSDiff = Math.abs(c - j);
					if(locSDiff < scorediff
					   || (locSDiff == scorediff && c < cmax) ){
						myC = c;
						myJ = j;
						scorediff = locSDiff;
						cmax = c;
						//System.out.println("BF :: found c=" + c + ", j=" + j + " -> sC=" + sC + ", sJ=" + sJ + ", scorediff=" + scorediff + ", cmax=" + cmax);
					}
				}//both match => maxker
			}//next j
		}//next c
		
		String outC = pfx + myC;
		outC = outC.substring(outC.length() - pfx.length());
		String outJ = pfx + myJ;
		outJ = outJ.substring(outJ.length() - pfx.length());
		
		String ret = outC + " " + outJ;
		return ret;
	}

}
