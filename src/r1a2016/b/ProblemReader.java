package r1a2016.b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import util.RawInput;

public class ProblemReader {

	public static ArrayList<RawInput> readInputFile(String inFileName){
		
		
		
		ArrayList<RawInput> ret = new ArrayList<RawInput>();
		
		
		try{
			BufferedReader input = new BufferedReader(new FileReader(inFileName));
			
			String line = input.readLine(); //drop header == number of test cases
			
			int caseCnt = 0;
			int lineCnt = 0;
			String[] rawLines = null;
			int inLinesNum = 0;
			
			while (( line = input.readLine()) != null){
				
				//first line: calculate number of input rows
				if(lineCnt == 0){
					//line = input.readLine(); //N
					int N = Integer.parseInt(line);
					
					inLinesNum = 2*N;
					RawInput.setLinesNum(inLinesNum);
					rawLines = new String[inLinesNum];
					rawLines[0] = line;
					lineCnt=1;
				}
				
				//last line: output new RawInput
				else if(lineCnt == inLinesNum){
					//add existing testcase
					ret.add(new RawInput(rawLines));
					//new testcase
					lineCnt = 0;
					caseCnt++;
					
					int N = Integer.parseInt(line);
					
					inLinesNum = 2*N;
					RawInput.setLinesNum(inLinesNum);
					rawLines = new String[inLinesNum];
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
