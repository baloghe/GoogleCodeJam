package r1b2015.a;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import util.RawInput;

public class ProblemReader {

	public static final int FIX_NUM_INPUT_LINES = 0;
	public static final int VARIABLE_NUM_INPUT_LINES = 1;
	
	public static int INPUT_LINE_TYPE = FIX_NUM_INPUT_LINES;
	public static int FIXED_INPUT_LINE_NUM = 2;
	
	public static ArrayList<RawInput> readInputFile(String inFileName){
		
		ArrayList<RawInput> ret = new ArrayList<RawInput>();
		
		try{
			BufferedReader input = new BufferedReader(new FileReader(inFileName));
			
			String line = input.readLine(); //drop header == number of test cases
			
			int caseCnt = 0;
			int lineCnt = 0;
			String[] rawLines = null;
			int inLinesNum = 0; //number of lines to be run in the current Case (according to Google)
			                    //in reality, the RawInput will be a line longer, as it includes the number of lines as well
			
			while (( line = input.readLine()) != null){
				
				//System.out.println("ProblemReader :: line=" + line + "||     --> lineCnt=" + lineCnt + ", caseCnt=" + caseCnt);
				
				//first line: calculate number of input rows
				if(lineCnt == 0){
					//line = input.readLine(); //N
					if(INPUT_LINE_TYPE == VARIABLE_NUM_INPUT_LINES){
						int N = Integer.parseInt(line);
						inLinesNum = N;
					} else {
						inLinesNum = FIXED_INPUT_LINE_NUM - 1; //don't ask why...
					}
					
					//System.out.println("   first line: inLinesNum=" + inLinesNum);
					
					RawInput.setLinesNum(inLinesNum);
					rawLines = new String[inLinesNum+1];
					rawLines[0] = line;//number of lines that follow
					lineCnt=1;
				}
				
				//last line: output new RawInput
				else if(lineCnt == (inLinesNum+1) ){
					//add existing testcase
					ret.add(new RawInput(rawLines));
					
					//new testcase
					lineCnt = 0;
					caseCnt++;
					
					if(INPUT_LINE_TYPE == VARIABLE_NUM_INPUT_LINES){
						int N = Integer.parseInt(line);
						inLinesNum = N;
					} else {
						inLinesNum = FIXED_INPUT_LINE_NUM - 1;
					}
					
					//System.out.println("   new first line: inLinesNum=" + inLinesNum);
					
					RawInput.setLinesNum(inLinesNum);
					rawLines = new String[inLinesNum+1];
					rawLines[0] = line;
					lineCnt=1;
				}
				
				//any other line: just add to rawLines
				else {
					rawLines[lineCnt] = line;
					lineCnt++;
				}
				
			}
			//add last input as well
			ret.add(new RawInput(rawLines));
			
			System.out.println("Util.readInputFile :: " + (caseCnt ) + "  cases read.");
			input.close();
		} catch(Exception e) {
			System.out.println("Util.readInputFile :: File could not be opened!");
			System.out.println("   inFileName=" + inFileName);
			e.printStackTrace();
		}
		
		return ret;
	}
	
}
