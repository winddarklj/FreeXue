package services.system;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

  
public class RegularExpressionTest {  
     
  
    public static void main(String[] args) {  
    
    
    	
    	
        Pattern p = Pattern.compile("^(http|www|ftp|)?(://)?(//w+(-//w+)*)(//.(//w+(-//w+)*))*((://d+)?)(/(//w+(-//w+)*))*(//.?(//w)*)(//?)?(((//w*%)*(//w*//?)*(//w*:)*(//w*//+)*(//w*//.)*(//w*&)*(//w*-)*(//w*=)*(//w*%)*(//w*//?)*(//w*:)*(//w*//+)*(//w*//.)*(//w*&)*(//w*-)*(//w*=)*)*(//w*)*)$",Pattern.CASE_INSENSITIVE );   
        Matcher m = p.matcher("http://www.qqgb.com/Program/Java/JavaFAQ/JavaJ2SE/Program_146959.html");    
          
          if(m.find()){  
              System.out.println(m.group());  
          }  
          
          m = p.matcher("http://baike.baidu.com/view/230199.htm?fr=ala0_1");  
          
          if(m.find()){  
              System.out.println(m.group());  
          }  
            
          m = p.matcher("http://www.google.cn/gwt/x?u=http%3A%2F%2Fanotherbug.blog.chinajavaworld.com%2Fentry%2F4550%2F0%2F&btnGo=Go&source=wax&ie=UTF-8&oe=UTF-8");  
              
          if(m.find()){  
              System.out.println(m.group());  
          }  
            
          m = p.matcher("zh.wikipedia.org:80/wiki/Special:Search?search=tielu&go=Go");  
          
          if(m.find()){  
              System.out.println(m.group());  
          }         
            
    }  
  
}  