package r1a2014.a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import util.RawInput;
import util.Util;

/**
 * Solver class template, intended to be used for a specific Google Code Jam problem.
 * The class solves exactly one case at a time and returns the result in String format, ready to be appended into the submission file for the given case number
 *
 */
public class ProblemSolver implements util.CaseSolver {

	private int runmode;
	
	private int N;
	private int L;
	private boolean[][] outlets;
	private boolean[][] devices;
	
	/**
	 * constructor. The expected size of the problem might require different approaches, therefore runmode is required as a parameter   
	 * @param inRunMode expected RUNMODE_SMALL or RUNMODE_LARGE (see ProblemSolution)
	 */
	public ProblemSolver(int inRunMode){
		runmode = inRunMode;
	}
	
	
	@Override
	public String solveCase(RawInput inCase) {
		//init(inCase);
		// TODO Auto-generated method stub
		//return solveBF(inCase);
		return solveLarge(inCase);
		//return null;
	}
	
	public String solveLarge(RawInput inCase){
		init(inCase);
		/* Try all devices into all outlets and at each try calculate which changes in switches would be needed to do it. 
		 * At each try, keep in mind which Device was matched against which Outlet.
		 * 	Num of all tries: N^2
		 *  Num of resulting different switch arrangements: between 0 and N^2.
		 *  
		 *  If there is a switch arrangement that is 
		 *  	repeated at least N times 
		 *  	AND is a good match for Outlets and Devices (i.e. all Devices and all Outlets are matched to it exactly once)
		 *  THEN there is a solution => the smallest number of switch changes should be returned
		 *  If there is no such switch arrangement => NOT POSSIBLE
		 */
		
		HashMap<String,HashSet<SwitchArrangement>> sws = new HashMap<String,HashSet<SwitchArrangement>>();
		HashMap<String, Integer> swscount = new HashMap<String, Integer>();
		HashMap<String, Integer> swnums = new HashMap<String, Integer>();
		int maxSwsCnt = 0;
		for(int i=0; i<N; i++){
			boolean[] device = devices[i];
			for(int j=0; j<N; j++){
				boolean[] outlet = outlets[j];
				Boolean[] swarr = getSwitchModifier(device, outlet);
				SwitchArrangement x = new SwitchArrangement(i,j,swarr);
				
				//System.out.println("Large :: i=" + i + ", j=" + j + ", swarr=" + booleanArrToString(swarr));
				
				//manage sws
				HashSet<SwitchArrangement> swset = new HashSet<SwitchArrangement>();
				if(sws.containsKey(x.getKey())){
					swset = sws.get(x.getKey());
				}
				swset.add(x);
				sws.put(x.getKey(), swset);
				//manage swscount
				Integer newCnt = 1;
				if(swscount.containsKey(x.getKey())){
					newCnt = swscount.get(x.getKey()) + 1;					
				}
				swscount.put(x.getKey(), newCnt);
				//manage maxSwsCnt
				if(newCnt > maxSwsCnt){
					maxSwsCnt = newCnt;
				}
				//manage swnums
				swnums.put(x.getKey(), x.getSwitchNumber());
			}//next j
		}//next i
		
		//System.out.println("Large :: sws.size=" + sws.size() + ", swscount.size=" + swscount.size() + ", swnums.size=" + swnums.size());
		//System.out.println("Large :: swscount=" + swscount.entrySet());
		
		//see if there is anything to report...
		if(maxSwsCnt < N){
			return "NOT POSSIBLE";
		}
		
		//otherwise: inspect all cases where count = N
		int switchNum = L+1;
		for(Entry<String, Integer> e : swscount.entrySet()){
			if(e.getValue() >= N){
				HashSet<SwitchArrangement> actSet = sws.get(e.getKey());
				//System.out.println("Large :: e=" + e + ", actSet=" + actSet);
				if( isFullMatch(actSet) ){
					int actSWNum = swnums.get(e.getKey());
					if(actSWNum < switchNum){
						switchNum = actSWNum;
					}
				}
			}
		}
		
		return (switchNum > L ? "NOT POSSIBLE" : Integer.toString(switchNum) );
	}
	
	public boolean isFullMatch(HashSet<SwitchArrangement> inSet){
		HashSet<Integer> devIds = new HashSet<Integer>();
		HashSet<Integer> outIds = new HashSet<Integer>();
		for(int i=0; i<N; i++){
			devIds.add(i);
			outIds.add(i);
		}
		
		for(SwitchArrangement sw : inSet){
			//System.out.println("isFullMatch :: sw=" + sw + " ==> remove dev=" + sw.getDeviceId() + ", out=" + sw.getOutletId());
			devIds.remove(sw.getDeviceId());
			outIds.remove(sw.getOutletId());
		}
		//System.out.println("isFullMatch :: inSet=" + inSet + " ==> devIds.size=" + devIds.size() + ", outIds.size=" + outIds.size());
		
		return ( devIds.isEmpty() && outIds.isEmpty() );
	}
	
	public String solveBF(RawInput inCase){
		init(inCase);
		/* Try all switch positions(2^L)
		 * 	at each position: try to match as many devices as possible
		 *  count unmatchable devices -> if ZERO, store switch change number
		 *  
		 *  if no ZERO count => return NO POSSIBLE
		 *  otherwise return min(switch change number)
		 */
		
		//a perfect match at startup solves everything...
		if(matchDevicesOutlets(outlets)){
			return "0";
		}
		
		//otherwise let's look all possible setups
		int ret = L+1;
		int lpow = twoPow(L);
		for(int chnum=0; chnum < lpow; chnum++){
			Boolean[] sw = Util.longToBoolArray((long)chnum, L);
			int swNum = countTrue(sw);
			boolean[][] modOutlets = modifySwitch(sw);
			//System.out.println(booleanArrToString(modOutlets));
			boolean tmp = matchDevicesOutlets(modOutlets);
			//System.out.println("solveBF :: chnum=" + chnum + " -> swNum=" + swNum + ", tmp=" + tmp);
			if(tmp){
				if(swNum < ret){
					ret = swNum;
				}
			}
		}
		
		return ( ret > L ? "NOT POSSIBLE" : Integer.toString(ret) );
	}
	
	public static int countTrue(Boolean[] inArr){
		int ret = 0;
		for(boolean b : inArr){
			if(b) ret++;
		}
		return ret;
	}
	
	public static int twoPow(int inNum){
		int ret = 1;
		for(int i=0; i<inNum; i++) ret *= 2;
		return ret;
	}
	
	public boolean matchDevicesOutlets(boolean[][] inOutlets){
		
		HashSet<String> tmpOutlets = new HashSet<String>();
		for(boolean[] o : inOutlets){
			tmpOutlets.add( ProblemSolver.booleanArrToString(new boolean[][]{o}) );
		}
		
		boolean ret = true;
		String tmpdev = "";
		for(boolean[] d : devices){
			tmpdev = ProblemSolver.booleanArrToString(new boolean[][]{d});
			ret = ret && tmpOutlets.contains(tmpdev);
		}
		
		return ret;
	}
	
	public boolean[][] modifySwitch(Boolean[] inMods){
		boolean[][] ret = new boolean[outlets.length][outlets[0].length];
		//System.out.println("modifySwitch :: inMods.length=" + inMods.length);
		for(int r=0; r<outlets.length; r++){
			for(int c=0; c<outlets[r].length; c++){
				//System.out.println("modifySwitch :: r=" + r + ", c=" + c);
				ret[r][c] = ( inMods[c] ? !outlets[r][c] : outlets[r][c] );
			}//next c
		}//next r
		
		return ret;
	}
	
	public static Boolean[] getSwitchModifier(boolean[] inDevice, boolean[] inOutlet){
		//System.out.println("getSwitchModifier :: dev=" + booleanArrToString(inDevice) + ", out=" + booleanArrToString(inOutlet));
		Boolean[] ret = new Boolean[inOutlet.length];
		for(int i=0; i<inOutlet.length; i++){
			ret[i] = ( inDevice[i]!=inOutlet[i] );
			//System.out.println("   i=" + i + " -> dev=" + inDevice[i] + ", out=" + inOutlet[i] + " => ret=" + ret[i]);
		}
		return ret;
	}
	
	/**
	 * initializer code, called immediately by solveCase
	 * @param inCase case to be solved
	 */
	private void init(RawInput inCase){
		//store raw data
		ArrayList<Integer> nl = Util.splitStringToInt(inCase.getData()[0], null);
		N = nl.get(0);
		L = nl.get(1);
		outlets = readInput(inCase.getData()[1], N, L);
		devices = readInput(inCase.getData()[2], N, L);
		
		System.out.println("init :: N=" + N + ", L=" + L);
		//System.out.println("outlets=\n" + bArrToString(outlets));
		//System.out.println("devices=\n" + bArrToString(devices));
	}
	
	public static boolean[][] readInput(String inStr, int inRow, int inCol){
		boolean[][] ret = new boolean[inRow][inCol];
		
		String[] tmp = inStr.split(" ");
		//System.out.println("tmp len=" + tmp.length);
		for(int r=0; r<tmp.length; r++){
			//System.out.println("tmp["+r+"] len=" + tmp[r].length());
			for(int c=0; c<tmp[r].length(); c++){
				//System.out.println("r="+r+"c="+c);
				ret[r][c] = (tmp[r].substring(c, c+1).equalsIgnoreCase("1") ? true : false);
				//System.out.println("ret["+r+"]["+c+"]=" + ret[r][c]);
			}
		}
		
		return ret;
	}
	
	public static String booleanArrToString(boolean[][] inArr){
		String ret = "";
		
		for(int r=0; r<inArr.length; r++){
			if(r > 0) ret += "\n";
			for(int c=0; c<inArr[r].length; c++){
				ret += ( inArr[r][c] ? "T" : "F" );
			}
		}
		
		return ret;
	}
	
	public static String booleanArrToString(Boolean[] inArr){
		String ret = "";
		
		for(int c=0; c<inArr.length; c++){
			ret += ( inArr[c] ? "T" : "F" );
		}
		
		return ret;
	}
	
	public static String booleanArrToString(boolean[] inArr){
		String ret = "";
		
		for(int c=0; c<inArr.length; c++){
			ret += ( inArr[c] ? "T" : "F" );
		}
		
		return ret;
	}

}
