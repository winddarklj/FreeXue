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
 * Servlet implementation class updateHierarchy
 */
@WebServlet("/UpdateHierarchy")
public class UpdateHierarchy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateHierarchy() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void process(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
		
  	   String parent = request.getParameter("parent");
  	   String old_child = request.getParameter("oldchild");
  	   String new_child = request.getParameter("newchild");
  	   
  	   DBOperaion db = new DBOperaion();
  	   db.connet();
  	   Object[] children = db.GetHierarchy(parent);
  	   
  	   JSONObject object =new JSONObject();
  	   object.put("Parent", parent);
  	   
  	   JSONArray arr = new JSONArray();
 
		db.UpdateHierarchy("1_1", "2_17", "2_18");
  	   
  	  
  }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		try {
			process(request,response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
