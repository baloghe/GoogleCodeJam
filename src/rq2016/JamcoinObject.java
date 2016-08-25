
package rq2016;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;

import util.Util;

public class JamcoinObject {

	private static Pattern pattern = Pattern.compile("^[1][01]*[1]$");	
	private static HashSet<Long> sieve = Util.longPrimeSieveThreshold(1000000000);
	
	private String jcstring;
	private long[] divisors;
	
	public JamcoinObject(String inStr, long[] inDivisors){
		jcstring = inStr;
		divisors = new long[inDivisors.length];
		for(int i=0; i<inDivisors.length; i++){
			divisors[i] = inDivisors[i];
		}
	}
	
	public String getLiteral(){
		return jcstring;
	}
	
	public long[] getDivisors(){
		return divisors;
	}
	
	public static long[] isJamcoin(String inNumber, int inBaseFrom, int inBaseTo){
		
		if(inNumber.length() < 2) return null;
		
		//every digit is either 0 or 1
		//the first digit is 1 and the last digit is 1.
		Matcher matcher = pattern.matcher(inNumber);
		if(!matcher.find()) return null;
		
		//if you interpret the string in any base between 2 and 10, inclusive, the resulting number is not prime
		long[] ret = new long[inBaseTo - inBaseFrom + 1];
		for(int b=inBaseFrom; b<=inBaseTo; b++){
			long div=1;
			try{
				div = Util.getSmallestDivisor( Long.parseLong(inNumber, b) );
			} catch(Exception e){
				System.out.println("JamcoinObject.isJamcoin :: error while parsing inNumber=" + inNumber + " in base=" + b);
				e.printStackTrace();
			}
			if( div <= 1 ) {
				return null;
			} else {
				ret[b - inBaseFrom] = div;
			}
		}
		
		//fulfilled all criteria => must be a jamcoin!
		return ret;
	}
	
	public static long[] isJamcoin(String inNumber){
		return isJamcoin(inNumber, 2, 10);
	}
	
	public static HashSet<JamcoinObject> buildJamcoins(int inLen, int inMaxCnt){
		int maxnum = (int)Math.pow(2.0, (double)inLen);
		int minnum = (int)Math.pow(2.0, (double)(inLen-1));
		HashSet<JamcoinObject> ret = new HashSet<JamcoinObject>();
		
		int cnt = 0;
		for(long i=minnum+1; i<maxnum && cnt < inMaxCnt; i++){
			String candidate = Long.toString(i, 2);
			//System.out.println("i=" + i + ", candidate=" + candidate);
			long[] divs = isJamcoin(candidate);
			if(divs != null){
				ret.add( new JamcoinObject(candidate, divs) );
				cnt++;
			}//endif
		}//next i
		return ret;
	}
	
	public static long getJCDivisor(String inJamcoin, int inBase){
		//interpret jamcoin in base
		long jcvalue = Long.parseLong(inJamcoin, inBase);
		
		//get a suitable prime sieve
		long thrs = (long) Math.sqrt(jcvalue);
		
		//search for the first nontrivial divisor
		long ret = -1;
		for(Long i : sieve){
			if(jcvalue % i.longValue() == 0){
				ret = i.longValue();
				break;
			}
		}
		return ret;
	}
	
	public static String getJCDivisors(String inJamcoin, int inBaseFrom, int inBaseTo){
		String ret = "";
		for(int i=inBaseFrom; i<= inBaseTo; i++){
			long div = JamcoinObject.getJCDivisor(inJamcoin, i);
			ret += (" " + div);
		}
		return ret;
	}
	
}