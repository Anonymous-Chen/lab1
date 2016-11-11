package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class Lab1Test2 {
	
	String strs1 = "2*x+65+2*x*y+55*x*x*x";
	String strs2 = "z";

	@Test
	public void testDerivative() {
		String strs3 = Lab1.derivative(strs1,strs2 );
		String strs4 = "求导结果为：0";
		assertEquals(strs3, strs4);
	}

}
