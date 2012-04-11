package services.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hierarchy;
import model.SysTag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sqlbase.DBOperaion;

/**
 * Servlet implementation class GetHierarchy
 */
@WebServlet("/GetHierarchy")
public class GetHierarchy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHierarchy() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
		
 	   String parent = request.getParameter("parent");
 	   response.setContentType("text/html;charset=UTF-8");
 	   
 	  System.out.println("come in");
 	   
 	   if(parent==null)
 	   {
 		   response.getWriter().write("No values");
 	   }
 	   else {
		 	  JSONObject jh = new JSONObject(Hierarchy.hierarchyStr);
		 	   SysTag stag = Hierarchy.getChildren(parent, Hierarchy.head); 
		 	   if(stag==null) {
		 		   System.out.println("null");
		 	   }
		 	   else {
		 		  
		 		  JSONObject tag = new JSONObject();
		 		  tag.put("name", stag.getTagname());
		 		  tag.put("id", stag.getTagid());
		 		  tag.put("num", stag.getChildren().size());
		 		  JSONArray arr = new JSONArray();
		 		 for(int i=0; i<stag.getChildren().size(); i++)
		 		 {
		 			JSONObject ctag = new JSONObject();
			 		  ctag.put("name", stag.getChildren().get(i).getTagname());
			 		  ctag.put("id", stag.getChildren().get(i).getTagid());
			 		  arr.put(i,ctag);
		 		 }
		 		  tag.put("children", arr); 
		 		  
		 		 System.out.println(tag.toString());
		 		 response.getWriter().write(tag.toString());
		 		
		 	   }
		 	
 	   }
 	 
 }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request,response);
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
			process(request,response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
