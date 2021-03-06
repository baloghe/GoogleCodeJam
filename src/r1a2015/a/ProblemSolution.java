package r1a2015.a;

import java.io.File;
import java.util.ArrayList;

import util.CaseSolver;
import util.RawInput;
import util.Util;

/**
 * Google Code Jam 2015 Round1A Problem A: 
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
	 * Input file name for TEST problem
	 * WARNING!!! RENAMING REQUIRED!!!
	 */
	public static String INPUT_FILENAME_TEST = "A-test-practice.in";
	
	/**
	 * Input file name for Small problem
	 * WARNING!!! RENAMING REQUIRED!!!
	 */
	public static String INPUT_FILENAME_SMALL = "A-small-practice.in";
	
	/**
	 * Input file name for Large problem
	 * WARNING!!! RENAMING REQUIRED!!!
	 */
	public static String INPUT_FILENAME_LARGE = "A-large-practice.in";
		
	/**
	 * Test input mode - to see if ProblemReader works fine when You need it
	 */
	public static int RUNMODE_TEST = -1;
	
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
		//int runmode = RUNMODE_TEST;
		//int runmode = RUNMODE_SMALL;
		int runmode = RUNMODE_LARGE;
				
		//init the whole thing
		init(runmode);
		
		//init solver
		solver = new ProblemSolver(runmode);
		
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
	
	/**
	 * initializer, called by main(). Parameter made explicit for JUnit testing reasons
	 * @param inRunMode small or large problem
	 */
	public static void init(int inRunMode){
		//start clock
		startTime = System.currentTimeMillis();
		
		//determine input folder
		String username=System.getProperty("user.name");
		System.out.println("USER=" + username);
		if(username.equalsIgnoreCase("baloghend")){
			INPUT_DIRECTORY = "../../GoogleCodeJam/Round1A2015"; //at work
		} else if(username.equalsIgnoreCase("user")){
			INPUT_DIRECTORY = "../../GoogleCodeJam/Round1A2015"; //at home/desktop
		} else {
			INPUT_DIRECTORY = "../../../GoogleCodeJam/Round1A2015";   //at home/laptop
		}
		
		String infname = (inRunMode==RUNMODE_SMALL ? INPUT_FILENAME_SMALL : ( inRunMode==RUNMODE_TEST ? INPUT_FILENAME_TEST : INPUT_FILENAME_LARGE ) );
		String outfname = INPUT_DIRECTORY + "/" + infname.replace(".in", ".out");
		SUBMISSION_FILE = Util.createFile(outfname, null);
		
		Util.readTestNumber(INPUT_DIRECTORY + "/" + infname );
		
		//read raw data -- ONE LINE OF INPUT PER CASE
		//rawInputs = Util.readInputFile(INPUT_DIRECTORY + "/" + (inRunMode==RUNMODE_SMALL ? INPUT_FILENAME_SMALL : (inRunMode==RUNMODE_TEST ? INPUT_FILENAME_TEST : INPUT_FILENAME_LARGE) ) , rawLinesNum);
		//Alternatively: MULTIPLE LINE OF INPUT PER CASE
		rawInputs = ProblemReader.readInputFile( INPUT_DIRECTORY + "/" + (inRunMode==RUNMODE_SMALL ? INPUT_FILENAME_SMALL : (inRunMode==RUNMODE_TEST ? INPUT_FILENAME_TEST : INPUT_FILENAME_LARGE) ) );
		
		//print last input
		System.out.println("Last raw input: " + rawInputs.get(rawInputs.size()-1));
		
	}
	
	
	
}
