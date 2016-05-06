package rq2016;

import java.io.File;
import java.util.ArrayList;

import util.CaseSolver;
import util.RawInput;
import util.Util;

public class Jamcoin {

	public static String INPUT_DIRECTORY;
	public static String INPUT_FILENAME_SMALL = "C-small-practice.in";
	public static String INPUT_FILENAME_LARGE = "C-large-practice.in";
	
	public static int RUNMODE_SMALL = 0;
	public static int RUNMODE_LARGE = 1;
	
	public static File SUBMISSION_FILE;
	
	private static long startTime;
	private static int testNum;
	private static int rawLinesNum;
	private static CaseSolver solver;
	
	private static ArrayList<RawInput> rawInputs;
	
	public static void main(String[] args){
		//raw data spans this many consecutive lines
		rawLinesNum = 1;
		
		//Small or Large to be processed?
		int runmode = RUNMODE_SMALL;
		//int runmode = RUNMODE_LARGE;
				
		//init the whole thing
		init(runmode);
		
		//init solver
		solver = new JamCoinSolver();
		
		//solve...
		ArrayList<String> solution = new ArrayList<String>();
		for(RawInput r : rawInputs){
			solution.add(solver.solveCase(r));
		}
		
		//print last solution
		System.out.println("Last solution:\n" + solution.get(solution.size()-1));
		
		//write out
		Util.writeOutFile(solution, SUBMISSION_FILE);
		System.out.println("Submission written out to " + SUBMISSION_FILE.getAbsolutePath());
		
		//output
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime) / 60000;
		System.out.println("Main :: duration = " + duration + " min"); 
	}
	
	public static void init(int inRunMode){
		//start clock
		startTime = System.currentTimeMillis();
		
		//determine input folder
		String username=System.getProperty("user.name");
		System.out.println("USER=" + username);
		if(username.equalsIgnoreCase("baloghend")){
			INPUT_DIRECTORY = "../../GoogleCodeJam/RoundQuali2016"; //at work
		} else {
			INPUT_DIRECTORY = "../../../GoogleCodeJam/RoundQuali2016";   //at home
		}
		
		String infname = (inRunMode==RUNMODE_SMALL ? INPUT_FILENAME_SMALL : INPUT_FILENAME_LARGE);
		String outfname = INPUT_DIRECTORY + "/" + infname.replace(".in", ".out");
		SUBMISSION_FILE = Util.createFile(outfname, null);
		
		Util.readTestNumber(INPUT_DIRECTORY + "/" + infname );
		
		//read raw data
		rawInputs = Util.readInputFile(INPUT_DIRECTORY + "/" + (inRunMode==RUNMODE_SMALL ? INPUT_FILENAME_SMALL : INPUT_FILENAME_LARGE) , rawLinesNum);
		
		//print last input
		System.out.println("Last raw input: " + rawInputs.get(rawInputs.size()-1));
		
	}
	
	
}
