package test;

import org.junit.Test;

import rq2016.*;

public class TstFractileObject {

	@Test
	public void t_Fractile1(){
		String orig = "LGL";
		
		System.out.println("t_Fractile1:");
		for(FractileObject f : FractileObject.buildFractileObjects(3)){
			System.out.println("   " + f.getBasePattern() + " -> " + f.getResultPattern(1));
		}
		
	}
	
}
