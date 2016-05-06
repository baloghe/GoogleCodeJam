package test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;

import util.*;

public class TstUtil {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void t_readTestNumber_pos(){
		String fname = "../../GoogleCodeJam/RoundQuali2010/A-small-practice.in";
		int casenum_expected = 10;
		int casenum_actual = Util.readTestNumber(fname);
		Assert.assertEquals("Number of cases misspelled: ", casenum_expected, casenum_actual);
	}
	
	@Test
	public void t_readTestNumber_neg(){
		String fname = "../../GoogleCodeJam/RoundQuali2010/RoundQuali2010_Problems.docx";
		thrown.expect(NumberFormatException.class);
		Util.readTestNumber(fname);
	}
	
	@Test
	public void t_splitStringToInt_pos(){
		String s = "  10 9   8 678";
		
		ArrayList<Integer> list_expected = new ArrayList<Integer>();
		list_expected.add(new Integer(10));
		list_expected.add(new Integer(9));
		list_expected.add(new Integer(8));
		list_expected.add(new Integer(678));
		
		ArrayList<Integer> list_actual = Util.splitStringToInt(s, null);
		
		Assert.assertArrayEquals(list_expected.toArray(new Integer[0]), list_actual.toArray(new Integer[0]));
	}
	
	@Test
	public void t_splitStringToInt_empty(){
		String s = "  ";
		
		ArrayList<Integer> list_expected = new ArrayList<Integer>();
		ArrayList<Integer> list_actual = Util.splitStringToInt(s, null);
		
		Assert.assertArrayEquals(list_expected.toArray(new Integer[0]), list_actual.toArray(new Integer[0]));
	}
	
	@Test
	public void t_zipString(){
		String s = "+++--+-++++------+-";
		
		Assert.assertEquals("+-+-+-+-", Util.zipString(s));
	}
	
	@Test
	public void t_zipString2(){
		String s = "+";
		
		Assert.assertEquals("+", Util.zipString(s));
	}
	
	@Test
	public void t_zipString3(){
		String s = "";
		
		Assert.assertEquals("", Util.zipString(s));
	}
	
	@Test
	public void t_isprime1(){
		boolean[] act = {Util.isPrime(1) , Util.isPrime(2) , Util.isPrime(3) 
				, Util.isPrime(4) , Util.isPrime(5) , Util.isPrime(6)
				, Util.isPrime(7) , Util.isPrime(8) , Util.isPrime(9)
				};
		boolean[] exp = {false, true, true
				, false, true, false
				,true, false, false};
		/*
		for(int i=0; i<10; i++)
			System.out.println(i + "->" + Util.isPrime(i));
		*/
		Assert.assertArrayEquals(exp, act);
	}
	
	@Test
	public void t_primesieve1(){
		for(Integer i : Util.intPrimeSieve(10)){
			System.out.println(i);
		}
	}
	
}
