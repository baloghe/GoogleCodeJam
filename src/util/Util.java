package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Util {
	
	/**
	 * reads the number of cases to be processed (to be found as a single integer in the first line of the file)
	 * @param inFileName full path of the file to be processed
	 * @return number of cases to be processed
	 */
	public static int readTestNumber(String inFileName) {
		int ret = -1;
		try{
			BufferedReader input = new BufferedReader(new FileReader(inFileName));
			
			//number of test cases
			String line = input.readLine();
			ret = (new Integer(line)).intValue();
			
			//close input file
			input.close();
			
		} catch(java.io.FileNotFoundException e) {
			System.out.println("Main.readInput :: Input file does not exist! file was: " + inFileName);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Main.readInput :: Input file could not be read! file was: " + inFileName);
			e.printStackTrace();
		} 
		
		return ret;
	}
	
	/**
	 * reads the given input file and translates its content into a list of RawInput objects.
	 * The first line of the file is discarded.
	 * The following lines are processed in tuples (tuple length provided in inLinesNum)
	 * @param inFileName full path of the file to be processed
	 * @param inLinesNum number of lines forming a single case together
	 * @return list of RawInput objects
	 */
	public static ArrayList<RawInput> readInputFile(String inFileName, int inLinesNum){
		
		ArrayList<RawInput> ret = new ArrayList<RawInput>();
		RawInput.setLinesNum(inLinesNum);
		
		try{
			BufferedReader input = new BufferedReader(new FileReader(inFileName));
			
			String line = input.readLine(); //drop header
			
			int caseCnt = 0;
			int lineCnt = 0;
			String[] rawLines = null;
			
			while (( line = input.readLine()) != null){
				if(lineCnt % inLinesNum  == 0){
					//add existing testcase
					if(rawLines != null){
						ret.add(new RawInput(rawLines));
					}
					//new testcase
					rawLines = new String[inLinesNum];
					lineCnt = 0;
					caseCnt++;
				}
				rawLines[lineCnt] = line;
				lineCnt++;
			}
			//add last input as well
			ret.add(new RawInput(rawLines));
			
			System.out.println("Util.readInputFile :: " + (caseCnt * inLinesNum) + " lines read.");
			input.close();
		} catch(Exception e) {
			System.out.println("Util.readInputFile :: File could not be opened!");
			System.out.println("   inFileName=" + inFileName);
			e.printStackTrace();
		}
		
		return ret;
	}
	
	/**
	 * splits the given string into an Integer array. The splitting is executed by a java.util.Scanner object
	 * @param inStr string to be split up
	 * @param inDelim delimiter to be passed to the Scanner object (without modification)
	 * @return null if the input was null, array of Integers otherwise
	 */
	public static ArrayList<Integer> splitStringToInt(String inStr, String inDelim){
		
		if(inStr==null || inStr.equalsIgnoreCase(""))
			return null;
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		
		Scanner sc = new Scanner(inStr);
		
		if(inDelim != null){
			sc.useDelimiter(inDelim);
		}
		
		while (sc.hasNextInt()){
			ret.add( sc.nextInt() );
		}
		sc.close();
		
		return ret;
	}
	
	/**
	 * creates an empty file (or a file with the given header) in the target directory (and closes it immediately). File with the same name will be overwritten
	 * @param targetFileName name of the file to be created
	 * @param header header to be written out into the file. In case of NULL no header will be written to the file
	 * @return reference to the File object
	 */
	public static File createFile(String targetFileName, String header){
		try{
			File ret = new File(targetFileName);
			PrintWriter writer = new PrintWriter(ret, "UTF-8");
			if(header != null){
				writer.println(header);
			}
			writer.close();
			return ret;
		} catch(Exception e){
			return null;
		}
	}
	
	/**
	 * writes an array of solutions into the target file, prefixed with Case #i: ,
	 * where i is the index of the solution in the array
	 * @param inSolutions solutions to be printed out
	 * @param outFile file to be submitted
	 */
	public static void writeOutFile(ArrayList<String> inSolutions, File outFile){
		try{
			PrintWriter writer = new PrintWriter(new FileOutputStream(outFile, true));
			int i=0;
			for(String s : inSolutions){
				i++;
				writer.println("Case #" + i + ": " + s);
			}
			writer.close();
		} catch(Exception e){
			System.out.println("Util.writeOutFile :: ERROR while writing submission file!");
			e.printStackTrace();
		}
	}
	
	/**
	 * replace consecutive repeated characters with a single character in the input String
	 * @param inStr string to be inspected
	 * @return string with no two consecutive characters being identical
	 */
	public static String zipString(String inStr){
		if(inStr.equalsIgnoreCase(""))
			return "";
		
		String ret = "";
		
		char[] chrs = inStr.toCharArray();
		char last = chrs[0];
		ret += String.valueOf(last);
		for(int i=1;i < chrs.length; i++){
			char act = chrs[i];
			if(act != last){
				ret += String.valueOf(act);
				last = act;
			}
		}
			
		return ret;
	}
	
	/**
	 * determines if a positive integer is prime or not. Taken from http://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/   (2016.05.06)
	 * @param n number to be decided upon
	 * @return TRUE if the number is prime, FALSE otherwise
	 */
	public static boolean isPrime(int n) {
		//0 and 1 are not primes
		if(n==0) return false;
		if(n==1) return false;
		if(n==2) return true;
	    //check if n is a multiple of 2
	    if (n%2==0) return false;
	    //if not, then just check the odds
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
	/**
	 * determines if a positive (long) integer is prime or not. Taken from http://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/   (2016.05.06)
	 * @param n number to be decided upon
	 * @return TRUE if the number is prime, FALSE otherwise
	 */
	public static boolean isPrime(long n) {
	    //check if n is a multiple of 2
	    if (n%2==0) return false;
	    //if not, then just check the odds
	    for(long i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
	public static long getSmallestDivisor(long n){
		//check if n is a multiple of 2
	    if (n%2==0) return 2;
	    //if not, then just check the odds
	    for(long i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return i;
	    }
	    return 1;
	}
	
	/**
	 * prime sieve calculation, taken from http://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/   (2016.05.06)   --> juanmf's solution
	 * @param inMaxPNum max number of primes to be found
	 * @return set of Integers all being prime numbers, starting from 2
	 */
	public static HashSet<Integer> intPrimeSieve(int inMaxPNum) {
		//the result will contain 2 however the calculation entirely omits even numbers
		int[] primes = new int[inMaxPNum-1];
		primes[0] = 3;
		primes[1] = 5;
		primes[2] = 7;
		int index = 3;

		//calc sieve
		for (int n = 11; index < inMaxPNum-1; n += 2) {
			boolean prime = true;
			for (int j : primes) {
				int top = j * j;
				if (n >= top) {
					// System.out.println("j(prime): " + j + "; i(test): " + n + "; Top: " + top);
					if ((j != 0) && (n % j == 0)) {
						prime = false;
						break;
					}
				} else {
					break;
				}
			}//next
			if (prime) {
				//System.out.println(n);
				primes[index++] = n;
			}
		}
		
		//convert to HashSet<Integer>
		HashSet<Integer> ret = new HashSet<Integer>();
		//2 is not present above, so add it
		ret.add( new Integer(2) );
		for(int i : primes){
			ret.add( new Integer(i) );
		}
		
		//return
		return ret;
	}
	
		
	public static HashSet<Integer> intPrimeSieveThreshold(int inThreshold) {
		//the result will contain 2 however the calculation entirely omits even numbers
				int[] primes = new int[Integer.MAX_VALUE-1];
				primes[0] = 3;
				primes[1] = 5;
				primes[2] = 7;
				int index = 3;

				//calc sieve
				for (int n = 11; index < Integer.MAX_VALUE-1 && n < inThreshold; n += 2) {
					boolean prime = true;
					for (int j : primes) {
						int top = j * j;
						if (n >= top) {
							// System.out.println("j(prime): " + j + "; i(test): " + n + "; Top: " + top);
							if ((j != 0) && (n % j == 0)) {
								prime = false;
								break;
							}
						} else {
							break;
						}
					}//next
					if (prime) {
						//System.out.println(n);
						primes[index++] = n;
					}
				}
				
				//convert to HashSet<Integer>
				HashSet<Integer> ret = new HashSet<Integer>();
				//2 is not present above, so add it
				//but stop at the first 0!
				ret.add( new Integer(2) );
				for(int i : primes){
					if(i > 0)
						ret.add( new Integer(i) );
					else break;
				}
				
				//return
				return ret;
	}
	
	public static HashSet<Long> longPrimeSieveThreshold(long inThreshold) {
		//the result will contain 2 however the calculation entirely omits even numbers
				int primArrSize=1000000;
				long[] primes = new long[primArrSize];
				primes[0] = 3;
				primes[1] = 5;
				primes[2] = 7;
				int index = 3;

				//calc sieve
				for (long n = 11; index < primArrSize-1 && n < inThreshold; n += 2) {
					boolean prime = true;
					for (long j : primes) {
						long top = j * j;
						if (n >= top) {
							// System.out.println("j(prime): " + j + "; i(test): " + n + "; Top: " + top);
							if ((j != 0) && (n % j == 0)) {
								prime = false;
								break;
							}
						} else {
							break;
						}
					}//next
					if (prime) {
						//System.out.println(n);
						primes[index++] = n;
					}
				}
				
				//convert to HashSet<Integer>
				HashSet<Long> ret = new HashSet<Long>();
				//2 is not present above, so add it
				//but stop at the first 0!
				ret.add( new Long(2) );
				for(long i : primes){
					if(i > 0)
						ret.add( new Long(i) );
					else break;
				}
				
				//return
				return ret;
	}
	
	/*
	public static int getValueInBase(String inValue, int inBase){
		return Integer.parseInt(inValue, inBase);
	}
	*/
	/*
	public static String getStringInBase(int inValue, int inBase){
		return java.lang.Integer.toString(inValue, inBase);
	}
	*/
	
	public static String longArrayToString(long[] inArr, String sep){
		if(inArr == null) return "NULL";
		if(inArr.length == 0) return "EMPTY";
		//otherwise...
		String ret = "" + inArr[0];
		for(int i=1; i<inArr.length; i++){
			ret += (sep + inArr[i]);
		}
		return ret;
	}
	
	public static String intArrayToString(int[] inArr, String sep){
		if(inArr == null) return "NULL";
		if(inArr.length == 0) return "EMPTY";
		//otherwise...
		String ret = "" + inArr[0];
		for(int i=1; i<inArr.length; i++){
			ret += (sep + inArr[i]);
		}
		return ret;
	}
	
	public static String objArrayToString(Object[] inArr, String sep){
		if(inArr == null) return "NULL";
		if(inArr.length == 0) return "EMPTY";
		//otherwise...
		String ret = "" + inArr[0];
		for(int i=1; i<inArr.length; i++){
			ret += (sep + inArr[i]);
		}
		return ret;
	}
	
	public static String objMatrixToString(Object[][] inMat, String sep){
		if(inMat == null) return "NULL";
		if(inMat.length == 0) return "EMPTY";
		
		String ret = Util.objArrayToString(inMat[0], sep);
		
		for(int r=1; r<inMat.length; r++){
			ret += ("\n" + Util.objArrayToString(inMat[r], sep));
		}
		
		return ret;
	}
	
	public static String iterableToString(Iterable<?> it, String sep){
		if(it == null) return "NULL";
		String ret = "";
		int cnt = 0;
		for(Object elem : it){
			if(cnt>0) 
				ret += sep;
			ret += (elem==null ? "NULL" : elem);
			cnt++;
		}
		return ret;
	}
	
	/**
	 * Greatest Common Divisor of 2 long values, solution of Jeffrey Hantin
	 *   taken from http://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers at 2016.10.17 
	 * @param a first number
	 * @param b second number
	 * @return greatest common divisor of a and b
	 */
	public static long gcd(long a, long b){
	    while (b > 0)
	    {
	        long temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}

	/**
	 * Greatest Common Divisor of many long values, solution of Jeffrey Hantin
	 *   taken from http://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers at 2016.10.17 
	 * @param input array of numbers 
	 * @return greatest common divisor of the given numbers
	 */
	public static long gcd(long[] input){
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
	    return result;
	}
	
	/**
	 * Greatest Common Multiple of 2 long values, solution of Jeffrey Hantin
	 *   taken from http://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers at 2016.10.17 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long lcm(long a, long b){
	    return a * (b / gcd(a, b));
	}

	/**
	 * Greatest Common Multiple of many long values, solution of Jeffrey Hantin
	 *   taken from http://stackoverflow.com/questions/4201860/how-to-find-gcd-lcm-on-a-set-of-numbers at 2016.10.17 
	 * @param input
	 * @return
	 */
	public static long lcm(long[] input){
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
	    return result;
	}
	
	/**
	 * generates random integer numbers within a predefined range
	 * @param inNum number of integers to be generated
	 * @param inLowerThrs lower threshold of the range
	 * @param inUpperThrs upper threshold of the range
	 * @return array of generated random numbers
	 */
	public static int[] randInt(int inNum, int inLowerThrs, int inUpperThrs){
		int[] ret = new int[inNum];
		
		if(inUpperThrs == inLowerThrs){       //return the same number
			for(int i=0; i<inNum; i++){
				ret[i] = inLowerThrs; 
			}
			return ret;
		} else if(inUpperThrs < inLowerThrs){ //swap lower and upper around
			int x = inLowerThrs;
			inLowerThrs = inUpperThrs;
			inUpperThrs = x;
		} 
		
		Random r = new Random();
		for(int i=0; i<inNum; i++){
			ret[i] = inLowerThrs + r.nextInt(inUpperThrs - inLowerThrs);
		}
		
		return ret;
	}
	
	public static int[] randPrimes(int inNum, int inUpperThrs){
		int[] ret = new int[inNum];
		//trivial cases
		if(inUpperThrs < 2){
			return null;
		} else if(inUpperThrs == 2){
			for(int i=0; i<inNum; i++){
				ret[i] = 2;
			}
			return ret;
		}
		
		//inUpperThrs > 2
		int foundAlready = 0; //number of primes found already
		int numTrials = 10;
		int[] test_a;
		while(foundAlready < inNum){
			//get some random numbers
			int[] cands = randInt( (inNum > 10 ? inNum : 10) , 2 , inUpperThrs );
			//select out primes
			for(int i=0; i<cands.length && (foundAlready < inNum); i++){
				//choose bases for small Fermat theorem
				test_a = randInt( (numTrials > inUpperThrs-1 ? inUpperThrs-1 : numTrials) , 2, inUpperThrs );
				boolean isPrime=true;
				for(int j=0; j<test_a.length; j++){
					int a = test_a[j];
					int remainder = 1;
					for(int k=1; k<=cands[i]-1; k++){
						remainder = (a * remainder) % cands[i];
					}//next k (power)
					isPrime = isPrime && (remainder==1);
				}//next j (power base)
				if(isPrime && (foundAlready < inNum) ){
					//prime found => add it to the results
					ret[foundAlready++] = cands[i];
				}
			}//next i (candidate)
		}//wend
		
		return ret;
	}
}
