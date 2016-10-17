
package lab1;b1
//为最后提交正式版的文件
c4
import java.util.Scanner; //输入
import java.util.regex.Matcher; //匹配
import java.util.regex.Pattern; //模式

public class lab_1 {
	
	private static String pattern1 = "(([0-9]+|[a-z])[\\+|\\*])+([0-9]+|[a-z])";
	private static String pattern2 = "!simplify( [a-z]=[0-9]+)*";
	private static String pattern3 = "[a-z]=[0-9]+";
	private static String pattern4_1 = "[a-z]";
	private static String pattern4_2 = "[0-9]+";
	private static String pattern5 = "!d/d[a-z]";
	private static String pattern6 = "[a-z]$";


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int flag = 0;
		System.out.println("请输入表达式:");
		
		//接受String型
		/*
		Scanner input = new Scanner(System.in);
		String line = input.next();
		System.out.println("输入为:"+ line );
		*/
		
		// 12+x+3*y+21*x*z
		String line1_1 = "12+x+3*y+21*x*z+z";
		String line1_2 = line1_1;
		
		
		Pattern r = Pattern.compile(pattern1);
		Matcher m = r.matcher(line1_1);
		if (m.matches()) {
	         System.out.println("Found value:  "  + m.group(0));
	         flag = 1 ;
	      } else {
	         System.out.println("NO MATCH");
	      }
		
		if( flag == 1){
			
		    //save message of "!simplify"
		    String strs1[][] = new String[27][2];
		    strs1[0][0] = "-1";
		    
		    //test common;
		    String line2_1 = "!simplify x=2 y=30";
		    
		    Matcher m1 = Pattern.compile(pattern2).matcher(line2_1);
		    
		    if (m1.matches()) {
		    	//首次分解common；
		    	
		        Matcher m2 = Pattern.compile( pattern3 ).matcher( line2_1 ); // 获取 matcher 对象
		        int count = 0;
		        int sum = 0;

		        while(m2.find()) {
		        			        	
		        	//System.out.println("start(): "+m2.start());
		        	//System.out.println("end(): "+m2.end());
		           
		        	//二次分解common
		        	
		          	Matcher m3 = Pattern.compile(pattern4_1).matcher(m2.group(0));
		          	Matcher m4 = Pattern.compile(pattern4_2).matcher(m2.group(0));
		          	if(m3.find()){
		          		strs1[count][0] = m3.group(0);
		          		System.out.println("strs1[count][0] =  "+ strs1[count][0]);
		          		strs1[count+1][0] = "-1" ;
		          	}
		          	if(m4.find()){
		          		strs1[count][1] = m4.group(0);
		          		System.out.println("strs1[count][1] =  "+ strs1[count][1]);
		          		
		          	}
		          	
		            count++;
		        	System.out.println("Match number "+count);
		        	System.out.println("Match number "+ m2.group(0));   
		        }
		        
			    // 获取 matcher 对象
		        for ( int i=0 ; i<count ; i++){
		        	Matcher m5 = Pattern.compile(strs1[i][0]).matcher(line1_2);
		        	line1_2 = m5.replaceAll(strs1[i][1]); //替换后的字符串
		        }
		        
		        System.out.println(line1_2);
		        
		        String strs2[][] = new String[27][2];
		        
		        //分解
		        Pattern pattern2 = Pattern.compile("[+]+");
				String[] strs = pattern2.split( line1_2 );
				
			    System.out.println("Found part:  ");
			    for (int i=0;i<strs.length;i++) {
			    	
			    	//System.out.println("strs[i]"+strs[i]);
			    	
			    	int num = 1;
			    	count = 0 ;
			    	String str2 = null ;
			    	Matcher m6 = Pattern.compile(pattern4_1).matcher(strs[i]);
			    	Matcher m7 = Pattern.compile(pattern4_2).matcher(strs[i]);
			    	
			   		while(m7.find()){
			   			num *= Integer.parseInt( m7.group(0) ) ;
			   		}
			   		
			    	if(!m6.find()){
			    		sum += num;
			    	}else{ //String strs2[][] = new String[27][5];
			    		Matcher m8 = Pattern.compile(pattern4_1).matcher(strs[i]);
			    		m8.find();
			    		str2 = m8.group(0);
			    		while(m8.find()){
			    			str2 = str2 + "*" + m8.group(0) ;
				   		}
			    		//System.out.println( "cc" + str2 );
			    		for( int j=0 ; j<=27 ; j++ )
			    		{
			    			if( strs2[j][0]==null ){
			    				strs2[j][0] = str2 ;
			    				strs2[j][1] = String.valueOf( num ) ;
			    				//System.out.println( strs2[j][0] + "  " + strs2[j][1] + j );
			    				count ++;
			    				break;
			    			}else{
			    				if(str2.compareTo( strs2[j][0] ) == 0 ){
			    					
			    					if( strs2[j][1] != null ){
			    						int num1 = Integer.parseInt( strs2[j][1] ) + num ;
			    						strs2[j][1] = String.valueOf( num1 );
			    						
			    					}else{
			    						strs2[j][1] = String.valueOf( num ) ;
			    					}
			    					
			    					//System.out.println( j+" "+"bb" + strs2[j][1] );
			    				}
			    			}
			    		}
			    	}	
			    }
			    
			    System.out.print( sum );
			    for( int i=0 ; i<count ; i++){
			    	System.out.print( "+" + strs2[i][1] + "*" + strs2[i][0] );
			    }
			    System.out.println();
			    
		    }	
		    System.out.println("d");
		    
		    String line3_1 = "!d/dx";
		    String strs3[][] = new String[27][2];
		    int count = 0 ;
		    Matcher m9_1 = Pattern.compile(pattern5).matcher(line3_1);
		    Matcher m9_2 = Pattern.compile(pattern6).matcher(line3_1);
		    System.out.println("d");
		    if(m9_1.matches()){
		    	System.out.println("d");
		    	String str2 = m9_2.group(0);
		    	System.out.println("m9_2.group(0)" + str2);
		    	Pattern pattern2 = Pattern.compile("[+]+");
				String[] strs = pattern2.split( line1_1 );
				
				for (int i=0;i<strs.length;i++) {
					Matcher m10 = Pattern.compile(str2).matcher(strs[i]);
					
					if(m10.find()){
						int count1 = 1 ;
						
						while(m10.find()){
							count1 ++;
						}
						Matcher m11 = Pattern.compile(str2).matcher(strs[i]);
						strs[i] = m11.replaceFirst(String.valueOf( count1 )); //替换后的字符串
				        int num = 1;
				        Matcher m12 = Pattern.compile(pattern4_2).matcher(strs[i]);
				   		while(m12.find()){
				   			num *= Integer.parseInt( m12.group(0) ) ;
				   		}
				   		strs3[count][0] = String.valueOf( num ) ;
				   		System.out.println("strs3[count][0]"+ strs3[count][0]);
				   		Matcher m13 = Pattern.compile(pattern4_1).matcher(strs[i]);
			    		m13.find();
			    		str2 = m13.group(0);
			    		while(m13.find()){
			    			str2 = str2 + "*" + m13.group(0) ;
				   		}
			    		strs3[count][1] = str2 ;
			    		count++;
			    		System.out.println("strs3[count]"+strs3[count][0]+strs3[count][1] );
					}
				}
		    	
		    }
		    
		    
		}
	}
}
