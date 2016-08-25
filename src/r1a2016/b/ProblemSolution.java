package r1a2016.b;

import java.io.File;
import java.util.ArrayList;

import util.CaseSolver;
import util.RawInput;
import util.Util;

/**
 * Boilerplate for Google Code Jam problems: 
 * 	reads the input file,
 * 	processes it case-by-case with a new ProblemSolver instance for each case, 
 * 	writes the solutions to the submission file
 *
 */
public class ProblemSolution {

	/**
	 * input directory relative to the project repo directory, dependent on actual username
	 */
	public static String INPUT_DIRECTORY;
	
	/**
	 * Input file name for Small problem
	 * WARNING!!! RENAMING REQUIRED!!!
	 */
	public static String INPUT_FILENAME_SMALL = "B-small-practice.in";
	
	/**
	 * Input file name for Large problem
	 * WARNING!!! RENAMING REQUIRED!!!
	 */
	public static String INPUT_FILENAME_LARGE = "B-large-practice.in";
	
	/**
	 * Small input mode
	 */
	public static int RUNMODE_SMALL = 0;
	
	/**
	 * Large input mode
	 */
	public static int RUNMODE_LARGE = 1;
	
	/**
	 * Submission file
	 */
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
		//int runmode = RUNMODE_SMALL;
		int runmode = RUNMODE_LARGE;
				
		//init the whole thing
		init(runmode);
		
		//init solver
		solver = new ProblemSolver(runmode);
		
		//solve...
		ArrayList<String> solution = new ArrayList<String>();
		int cnt = 0;
		for(RawInput r : rawInputs){
//			if(cnt==42) System.out.println("" + cnt + "th :: " + r.toString());
			solution.add(solver.solveCase(r));
			System.out.println("" + (++cnt) + ". done.");
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
	
	/**
	 * initializer, called by main(). Parameter made explicit for JUnit testing reasons
	 * @param inRunMode smapp or large problem
	 */
	public static void init(int inRunMode){
		//start clock
		startTime = System.currentTimeMillis();
		
		//determine input folder
		String username=System.getProperty("user.name");
		System.out.println("USER=" + username);
		if(username.equalsIgnoreCase("baloghend")){
			INPUT_DIRECTORY = "../../GoogleCodeJam/Round1A2016"; //at work
		} else {
			INPUT_DIRECTORY = "../../../GoogleCodeJam/Round1A2016";   //at home
		}
		
		String infname = (inRunMode==RUNMODE_SMALL ? INPUT_FILENAME_SMALL : INPUT_FILENAME_LARGE);
		String outfname = INPUT_DIRECTORY + "/" + infname.replace(".in", ".out");
		SUBMISSION_FILE = Util.createFile(outfname, null);
		
		Util.readTestNumber(INPUT_DIRECTORY + "/" + infname );
		
		//read raw data
		rawInputs = ProblemReader.readInputFile(INPUT_DIRECTORY + "/" + (inRunMode==RUNMODE_SMALL ? INPUT_FILENAME_SMALL : INPUT_FILENAME_LARGE) );
		
		//print last input
		System.out.println("Last raw input: " + rawInputs.get(rawInputs.size()-1));
		
	}
	
	
	
}
