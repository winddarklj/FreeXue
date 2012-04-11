package services.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sqlbase.DBOperaion;

/**
 * Servlet implementation class SetHierarchy
 */
@WebServlet("/SetHierarchy")
public class SetHierarchy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetHierarchy() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
    		
    	   String parent = request.getParameter("parent");
    	   String children = request.getParameter("children");
    	   
    	   
    	   JSONObject object =new JSONObject(children);
    	   
    	   JSONArray childrenArr = object.getJSONArray("children");
    	   
    	   //Write a synchronized way to do that
    	   /*
    	   for(int i=0; i<childrenArr.length(); i++)
    	   {
    		   String child = childrenArr.getString(i); 
    		   System.out.println(child);
    		   db.SetHierarchy(parent, child);
    	   }
    	   */
    	   
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
