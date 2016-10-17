package lab1;b1
//²âÊÔÎÄ¼ş1
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
	
	private static String pattern6 = "[a-z]$";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	      
	      String line1_1 = "asq1d";

	      Matcher m7 = Pattern.compile(pattern6).matcher(line1_1);
	      
	      if(m7.find()){
	    	  System.out.println("Found value: "  );
	    	}
	      
	}
	
	
}
