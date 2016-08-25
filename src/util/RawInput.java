package util;

public class RawInput {
	
	protected static int linesNum;
	
	protected String[] rawData;
	
	public RawInput(String[] inLines){
		rawData = inLines;
	}
	
	public String[] getData(){
		return rawData;
	}
	
	public String getData(int idx){
		return rawData[idx];
	}
	
	public static void setLinesNum(int inLinesNum){
		RawInput.linesNum = inLinesNum;
	}
	
	public static int getLinesNum(){
		return RawInput.linesNum;
	}
	
	public String toString(){
		if(rawData==null) 
			return "NULL";
		String ret = rawData[0];
		for(int i=1; i<rawData.length; i++){
			ret += ("\n" + rawData[i]);
		}
		return ret;
	}
}
