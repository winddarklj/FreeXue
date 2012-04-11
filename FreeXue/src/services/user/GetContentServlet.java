package services.user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.htmlparser.Attribute;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;
import org.htmlparser.visitors.TextExtractingVisitor;

/**
 * Servlet implementation class GetContentServlet
 */
@WebServlet("/GetContentServlet")
public class GetContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private class RexLoc {
		int start;
		int end;
		String src; 
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 读取结果网页
        StringBuffer buffer = new StringBuffer();
        System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
        System.setProperty("sun.net.client.defaultReadTimeout", "5000");
        
        try {
        	String urlStr = "http://bbs.taisha.org/thread-539961-1-1.html";//http://bbs.taisha.org/forumdisplay.php?fid=107
			 URL url = new URL(urlStr);
			 int num = 0;
			 int position = 0;
			 String domain = null;
			 String father;
			 for(int i=0; i<urlStr.length(); i++)
			 {
				 if(urlStr.charAt(i) == '/')
				 {
					 num++;
					 position = i;
					 if(num==3)
					 {
						 domain = urlStr.substring(0, i);
					 }
				 }
			 }
			 father = urlStr.substring(0,position);
			 
			 System.out.println("father:  "+father);
			 System.out.println("domain: "+domain);
			 
			 URLConnection conn = url.openConnection();
			 conn.setDoOutput(true);
			 InputStream in = null;
			 in = url.openStream();
			 String content = pipe(in,"gbk");
			 
			
			 System.out.println(content);
			 response.setContentType("text/html; charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 
			 String ns = new String(content.getBytes("utf-8"),"utf-8");
			 ns = processImg(ns, father);
             ns =processStyle(ns,father);		
             ns = processHref(ns,father);
			 ns = processLink(ns,father);  
			  response.getWriter().write(ns);
		
        } catch (Exception e) {
    		e.printStackTrace();
    	}
	}
	
	public String processStyle(String ns, String father){
		try{
			
		      Pattern p1 = Pattern.compile("url\\(\"?(.*?)\"?\\)");
	                Matcher m1 = p1.matcher(ns); 
	                ArrayList<RexLoc> ms =new ArrayList<RexLoc>();
	               
	                while (m1.find()) { 
	                	    int is = m1.start(1);
	                        String s0 = m1.group(); 
	                        String s1 = m1.group(1); 
	                        System.out.println(s0 + "||" + s1 +"||" + is); 
	                        RexLoc rl = new RexLoc();
	                        rl.start = m1.start(1);
	                        rl.end = m1.end(1);
	                        rl.src = m1.group(1);
	                        ms.add(rl);
	                } 
	                  
	                
	                for(int i=ms.size()-1; i>=0; i--)
	                {	 System.out.println("XX");
	                	 RexLoc rl = ms.get(i);
	                	 String src = rl.src;
	                	 int end = rl.end;
	                	 int start = rl.start;
	                	 if(src.startsWith("./"))
	                     {
	                     	src =  father+"/"+src.substring(1);
	                     }
	                     else if(!src.startsWith("http://"))
	                     {
	                     	src = father+"/"+src;
	                     }
	                	 ns = ns.substring(0,start) + src + ns.substring(end);
	                	System.out.println("src:  " + src);
	                }
	            
	                
	                
	                
	                
			    return ns;
			    /////////////////////////////////////////////////////////Do another stuff
			   
				} catch (Exception e) {
			e.printStackTrace();
			}
			return ns;
	}
	
	public String processLink(String ns, String father){
		try{	 
			 System.out.println(ns);
			 Parser parser = Parser.createParser(ns  , "utf-8");
		     TagNameFilter ifilter = new TagNameFilter("link");
			  
			  NodeList nodelist = parser.parse(ifilter);
		      
			  NodeIterator it = nodelist.elements();
			  ArrayList<Tag> tags = new ArrayList<Tag>();
			  while (it.hasMoreNodes()) {

	               Node node = (Node) it.nextNode();
	               Tag tag = (Tag)node;
	               int ps =  tag.getStartPosition();
	               int pe = tag.getEndPosition();
	               String src = tag.getAttribute("href");
	               if(src.startsWith("./"))
	               {
	               	tag.setAttribute("href", father+"/"+src.substring(1));
	               }
	               else if(!src.startsWith("http://"))
	               {
	               	tag.setAttribute("href", father+"/"+src);
	               }
	   	             System.out.print(tag.getAttribute("href") + " ==>> ");
			       
	               //
	               tags.add(tag);
	           }

			    for(int i=tags.size()-1; i>=0; i--)
			    {
			    	Tag tag = tags.get(i);
			    	  int ps =  tag.getStartPosition();
		              int pe = tag.getEndPosition();
			    	ns = ns.substring(0,ps)+tag.toHtml()+ns.substring(pe);
			    }
			    return ns;
			    /////////////////////////////////////////////////////////Do another stuff
			   
				} catch (Exception e) {
			e.printStackTrace();
			}
			return ns;
	}
	
	public String processHref(String ns, String father){
		 try
		    {
		      // 通过过滤器过滤出<A>标签
			 Parser parser = Parser.createParser(ns  , "utf-8");
		      NodeList nodeList = parser.extractAllNodesThatMatch(new NodeFilter()
		          {
		            // 实现该方法,用以过滤标签
		            public boolean accept(Node node)
		            {
		              if (node instanceof LinkTag)// 标记
		                return true;
		              return false;
		            }
		          });
		      // 打印
		      ArrayList<LinkTag> tags = new ArrayList<LinkTag>();
		      for (int i = 0; i < nodeList.size(); i++)
		      {
		        LinkTag tag= (LinkTag) nodeList.elementAt(i);
		        
		        String src = tag.extractLink();
		        
		        if(src.startsWith("./"))
	               {
	               	tag.setLink(father+"/"+src.substring(1));
	               }
	               else if(!src.startsWith("http://"))
	               {
	               	tag.setLink(father+"/"+src);
	               }
	
		        tags.add(tag);
		      }
		      //////////////////////////////////////////Process
		      for(int i=tags.size()-1; i>=0; i--)
			    {
			    	Tag tag = tags.get(i);
			    	  int ps =  tag.getStartPosition();
		              int pe = tag.getEndPosition();
			    	ns = ns.substring(0,ps)+tag.toHtml()+ns.substring(pe);
			    }
			    return ns;
		      
		    }
		    catch (Exception e)
		    {
		      e.printStackTrace();
		    }

		  return ns;

	}

	public String processImg(String ns, String father){
		try{	 
		 System.out.println(ns);
		 Parser parser = Parser.createParser(ns  , "utf-8");
	     TagNameFilter ifilter = new TagNameFilter("img");
		  
		  NodeList nodelist = parser.parse(ifilter);
	      
		  NodeIterator it = nodelist.elements();
		  ArrayList<Tag> tags = new ArrayList<Tag>();
		  while (it.hasMoreNodes()) {

               Node node = (Node) it.nextNode();
               Tag tag = (Tag)node;
               int ps =  tag.getStartPosition();
               int pe = tag.getEndPosition();
               String src = tag.getAttribute("src");
               if(src.startsWith("./"))
               {
               	tag.setAttribute("src", father+"/"+src.substring(1));
               }
               else if(!src.startsWith("http://"))
               {
               	tag.setAttribute("src", father+"/"+src);
               }
               
               //
               tags.add(tag);
           }

		    for(int i=tags.size()-1; i>=0; i--)
		    {
		    	Tag tag = tags.get(i);
		    	  int ps =  tag.getStartPosition();
	              int pe = tag.getEndPosition();
		    	ns = ns.substring(0,ps)+tag.toHtml()+ns.substring(pe);
		    }
		    return ns;
		    /////////////////////////////////////////////////////////Do another stuff
		   
			} catch (Exception e) {
		e.printStackTrace();
		}
		return ns;
	}
	 
	public String decode(String source, String errorSourceCharset,
	        String rightCharset) throws UnsupportedEncodingException {
	    return new String(source.getBytes(errorSourceCharset), rightCharset);
	}
	
	static String pipe(InputStream in,String charset) throws IOException {
        StringBuffer s = new StringBuffer();
        if(charset==null||"".equals(charset)){
        	charset="utf-8";
        }
        String rLine = null;
        BufferedReader bReader = new BufferedReader(new InputStreamReader(in,charset));
        PrintWriter pw = null;
        File f = new File("../indexT.html");
		FileOutputStream fo = new FileOutputStream(f);
		OutputStreamWriter writer = new OutputStreamWriter(fo, "utf-8");
		pw = new PrintWriter(writer);
        while ( (rLine = bReader.readLine()) != null) {
            String tmp_rLine = rLine;
            int str_len = tmp_rLine.length();
            if (str_len > 0) {
              s.append(tmp_rLine);
              pw.println(tmp_rLine);
              pw.flush();
            }
            tmp_rLine = null;
       }
        in.close();
        pw.close();
        return s.toString();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
