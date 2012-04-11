package services.user;

import java.io.IOException;
import java.util.ArrayList;

import model.SysTag;
import model.source;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import sqlbase.DBOperaion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Servlet implementation class GetSourceServlet
 */
@WebServlet("/GetSourceServlet")
public class GetSourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSourceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void Process(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
    	
    	String level1 = request.getParameter("level1");
		String level2 = request.getParameter("level2");
		String level3 = request.getParameter("level3");
		String level4 = request.getParameter("level4");
		String level5 = request.getParameter("level5");
		String level6 = request.getParameter("level6");
		
		String pageStr = request.getParameter("page");
		int page = Integer.parseInt(pageStr);
	
    	DBOperaion db = new DBOperaion();
    	db.connet();
    	/////////////////////////////////////////////////////////////////////////
    	ArrayList<String> tagList = new ArrayList<String>();
    	if(level1!=null)
			 tagList.add(level1);
    	if(level2!=null)
			 tagList.add(level2);
    	if(level3!=null)
			 tagList.add(level3);
    	if(level4!=null&&!"".equals(level4))
			 tagList.add(level4);
     	if(level5!=null&&!"".equals(level5))
			 tagList.add(level5);
    	if(level6!=null&&!"".equals(level6))
			 tagList.add(level6);
	
			ArrayList<source> list = db.GetSources(tagList);
			if(list == null) 
			{
				System.out.println("null");
			}
			 JSONArray arr = new JSONArray();
			 
			  int start = (page -1)*10;
				int end  = page*10 -1;
				if(list.size()<end)
					end = list.size();
				
				System.out.println("page:"+page +"  end: "+end+ "  start:  "+start+ "   listsize: "+list.size());
			for(int i=start; i<end; i++)
			{
				source src = list.get(i);
				
				JSONObject srcele = new JSONObject();
				JSONArray tags = new JSONArray();
				
				
				for(int j=0; j<src.getTags().size();j++)
				{
					JSONObject jtag = new JSONObject();
					SysTag tag = src.getTags().get(j);
					jtag.put("id", tag.getTagid());
					jtag.put("name", tag.getTagname());
					tags.put(jtag);
				}
				srcele.put("title", src.getTitle());
				srcele.put("url", src.getURL());
				srcele.put("like", src.getLike());
				srcele.put("dislike", src.getDislike());
				srcele.put("tags", tags);
				srcele.put("description",src.getDescription());
				
				arr.put(srcele);
			}
			////////////////////////////////////////////////////////////////
			int allpage =0;
			float apxpage = (float)list.size();
			
			if(apxpage/10 > list.size()/10)
				allpage = list.size()/10+1;
			else if(apxpage/10 == list.size()/10)
				 allpage = list.size()/10;
			
			/////////////////////////////////////////////////////////////////
		
			JSONObject output = new  JSONObject();
			output.put("sources", arr);
			output.put("allpage",allpage);
			response.setContentType("text/html;charset=UTF-8");
		
		  response.getWriter().write(output.toString());
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Process(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Process(request, response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
