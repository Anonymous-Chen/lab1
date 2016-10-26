
package lab1;
//²âÊÔÎÄ¼ş2 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test2 {
	private static String pattern6 = "[a-z]$";
	
	public static void main(String[] args) {
		
		String line3_1 = "!d/dx";

	      
	      Matcher m9_2 = Pattern.compile(pattern6).matcher(line3_1);
	      if(m9_2.find()){
	    	  System.out.println("m9_2.group(0)" + m9_2.group(0));
	    	}
	}
}
/*
 * String.valueOf( num )
 * Integer.parseInt( strs2[j][1] ) 
 * public static void getResult(int num)
 {
   //return num *3+5;
   System.out.println(num*3+5);
   return;
 }
 // 12+x+3*y+21*x*z
		//String line[] = new String[2] ;
		//line[1] = "12+x+3*y+21*x*y*z+z";
		//String line2_1 = "!simplify x=2 y=30";
		//String line3_1 = "!d/dx";
		//String line1_2 = line1_1;
		 12+x+3*y+21*x*z+z
		 !simplify
12+x+3*y+21*x*y*z+z
!simplify
!simplify x=2 y=30
!simplify y=30
!d/dx
 */

