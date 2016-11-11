package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class Lab1Test {
	
	String strs1[][] = new String[27][2];


	
	@Test
	public void testExpression1() {
		String strs2[][] = new String[27][2];
		String strs3[][] = new String[27][2];
		strs2 = Lab1.expression1(strs1, "");
		strs3[26][0] = "0";
		
		assertEquals(strs2, strs3);
	}

}
