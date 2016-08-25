package test.rq2016;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import rq2016.*;
import util.*;

public class TstJamcoinObject {

	@Test
	public void t_isJamcoin1(){
		String[] numbers = new String[]{"101" , "1001", "10101", "110" };
		
		System.out.println("t_isJamcoin1:");
		for(String num : numbers){
			System.out.println("   " + num + ": " + (JamcoinObject.isJamcoin(num) == null ? "FALSE" : "TRUE" ) );
		}
		
	}
	
	@Test
	public void t_isJamcoin2(){
		String[] numbers = new String[]{"101" , "1001", "10101", "110" };
		
		System.out.println("t_isJamcoin1:");
		for(String num : numbers){
			String num2 = num + num;
			System.out.println("   " + num + ": " + (JamcoinObject.isJamcoin(num) == null ? "FALSE" : "TRUE" )
					+ " -> num || num : " + (JamcoinObject.isJamcoin(num2) == null ? "FALSE" : "TRUE")
					);
		}
		
	}
	
	@Test
	public void t_isJamcoin3(){
		int jcnum = 32;
		HashSet<JamcoinObject> jamcoins = JamcoinObject.buildJamcoins(8, jcnum);
		System.out.println("t_isJamcoin1: jamcoins.size=" + jamcoins.size());
		JamcoinObject[] kkk = new JamcoinObject[jcnum];
		jamcoins.toArray(kkk);
		String[] numbers = new String[jcnum];
		for(int i=0; i<jcnum; i++){
			numbers[i] = kkk[i].getLiteral();
		}
		
		System.out.println("t_isJamcoin1:");
		for(String num : numbers){
			String num2 = num + num;
			System.out.println("   " + num + ": " + (JamcoinObject.isJamcoin(num) == null ? "FALSE" : "TRUE" )
					+ " -> num || num : " + (JamcoinObject.isJamcoin(num2) == null ? "FALSE" : "TRUE")
					);
		}
		
	}
	
	@Test
	public void t_bildJamcoin1(){
		int[] lens = new int[]{2, 3, 4, 5, 16};
		
		System.out.println("t_isJamcoin1:");
		System.out.println("   max long=" + Long.MAX_VALUE);
		System.out.println("   toInt(1000000000000001)=" + Long.parseLong("1000000000000001",5));
		for(int len : lens){
			System.out.println("   length " + len + ":");
			HashSet<JamcoinObject> set = JamcoinObject.buildJamcoins(len, 10);
			for(JamcoinObject jc : set){
				System.out.println("      " + jc.getLiteral() + " -> base" + len + "=" + Long.parseLong(jc.getLiteral(), len));
			}
		}
		
	}
	
	@Test
	public void t_getDivisors1(){
		String[] numbers = new String[]{"100011" , "111111", "111001" };
		
		System.out.println("t_getDivisors1:");
		for(String num : numbers){
			JamcoinObject jc = new JamcoinObject(num , JamcoinObject.isJamcoin(num, 2, 10));
			System.out.println("   " + num + ": " + Util.longArrayToString(jc.getDivisors() , " ")  );
		}
		
	}
	
}
