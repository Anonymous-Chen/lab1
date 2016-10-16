package lab1;
//Ϊдʵ��ѧϰJava�����ʵ���ļ�
import java.util.regex.Matcher; //ƥ��
import java.util.regex.Pattern; //ģʽ

public class lab_REtest {

	private static final String REGEX = "foo";
    private static final String INPUT = "fooooooooooooooooo";
    private static Pattern pattern;
    private static Matcher matcher;
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name =  "12+x+3*y+21*x*z";
		 // ��ָ��ģʽ���ַ�������
	      String line = "This order 979 was placed for QT3000! 2342 OK?";
	      String pattern = "(.*)(\\d+)(.*)";
	      

	      // ���� Pattern ����
	      Pattern r = Pattern.compile(pattern);

	      // ���ڴ��� matcher ����
	      Matcher m = r.matcher(line);
	      if (m.find( )) {
	         System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	         System.out.println("Found value: " + m.group(2) );
	      } else {
	         System.out.println("NO MATCH");
	      }
	      
	      // 12+x+3*y+21*x*z      ([0-9]|[a-z])+
	      //String pattern2 = "(  [0-9a-z]+          [+|*])+[0-9a-z]+";
	      String pattern2 = "(([0-9]+|[a-z])[\\+|\\*])+([0-9]+|[a-z])";
	      Pattern r2 = Pattern.compile(pattern2);
	      Matcher m2 = r2.matcher(name);
	      if (m2.find( )) {
	         System.out.println("Found value:  "  + m2.group(0));
	      } else {
	         System.out.println("NO MATCH");
	      }
	      
	      //�ֽ�
	      Pattern pattern1 = Pattern.compile("[+]+");
	      
	      String[] strs = pattern1.split( name );
	      for (int i=0;i<strs.length;i++) {
	          System.out.println(strs[i]);
	      } 
	      
	      /*
	      String result = "${jboss.home}\\image"; 
	      java.util.regex.Matcher m = 
	      java.util.regex.Pattern.compile("\\$\\{.*\\}")
	      .matcher(result); 
	      m.find()����true 
	       
	      java.util.regex.Pattern.matches("\\$\\{.*\\}"
	      , result)����false 
	      */
	      
	      String result = "foo";
	      
	      //pattern =Pattern.matches("foo", "foo") ;
	  
	      matcher = Pattern.compile("foo").matcher("foo");
	     
	      
	      System.out.println("Current REGEX is: "+REGEX);
	      System.out.println("Current INPUT is: "+INPUT);

	      System.out.println("lookingAt(): "+matcher.lookingAt());
	      System.out.println("matches(): "+matcher.matches());
	      
	      //
	    //test common;
	      String line1 = "!simplify x=2 y=3";
	      String pattern3 = "!simplify( [a-z]=[0-9]+)*";
	      Matcher m1 = Pattern.compile(pattern3).matcher(line1);
	      if (m1.matches()) {
	    	  String pattern4 = "[a-z]=[0-9]+";
	    	  Pattern p = Pattern.compile( pattern4 );
	          Matcher m3 = p.matcher( line1 ); // ��ȡ matcher ����
	          int count = 0;

	          while(m3.find()) {
	            count++;
	            System.out.println("Match number "+count);
	            System.out.println("Match number "+ m3.group(0));
	            System.out.println("start(): "+m3.start());
	            System.out.println("end(): "+m3.end());
	         }
	    	  
	      }
		
	}

}
