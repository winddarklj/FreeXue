package services.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hierarchy;

import org.json.JSONArray;
import org.json.JSONObject;

import sqlbase.DBOperaion;
import sqlbase.DBOperaionTest;

/**
 * Servlet implementation class InputURL
 */
@WebServlet(
		description = "(User, Admin): Input url of resources into Database", 
		urlPatterns = { "/InputURL" }, 
		initParams = { 
				@WebInitParam(name = "source_url", value = ""), 
				@WebInitParam(name = "userid", value = "")
		})
public class InputURL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputURL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.setCharacterEncoding("utf-8");
		
		String sourceTitle = request.getParameter("sourceTitle");
		String sourceURL = request.getParameter("sourceURL");
		String sourceDescription = request.getParameter("description");
		
		String HTTP = sourceURL.substring(0, 3);
		
		if(!sourceURL.substring(0, 3).equals("http")) {
			sourceURL = "http://"+sourceURL;
		}

		System.out.println(sourceTitle);
		
		
		String level1 = request.getParameter("level1");
		String level2 = request.getParameter("level2");
		String level3 = request.getParameter("level3");
		String level4 = request.getParameter("level4");
		String level5 = request.getParameter("level5");
		String level6 = request.getParameter("level6");
	
		
		JSONObject json = new JSONObject();  
		 try{  
			 	
			    JSONArray arr = new JSONArray();
		           	
					if(level1 != null)
					{
						String name1 = Hierarchy.getTagname(Hierarchy.head, level1);
						JSONObject jtag= new JSONObject();
						jtag.put("name", name1);
						jtag.put("id", level1);
						arr.put(jtag);
					}
					if(level2 != null)
					{
						String name2 = Hierarchy.getTagname(Hierarchy.head, level2);
						JSONObject jtag= new JSONObject();
						jtag.put("name", name2);
						jtag.put("id", level2);
						arr.put(jtag);
					}
					if(level3 != null)
					{
						String name3 = Hierarchy.getTagname(Hierarchy.head, level3);
						JSONObject jtag= new JSONObject();
						jtag.put("name", name3);
						jtag.put("id", level3);
						arr.put(jtag);
					}
					if(level4 != null)
					{
						String name4 = Hierarchy.getTagname(Hierarchy.head, level4);
						JSONObject jtag= new JSONObject();
						jtag.put("name", name4);
						jtag.put("id", level4);
						arr.put(jtag);
					}
					if(level5 != null)
					{
						String name5 = Hierarchy.getTagname(Hierarchy.head, level5);
						JSONObject jtag= new JSONObject();
						jtag.put("name", name5);
						jtag.put("id", level5);
						arr.put(jtag);
					}
					if(level6 != null)
					{
						String name6 = Hierarchy.getTagname(Hierarchy.head, level6);
						JSONObject jtag= new JSONObject();
						jtag.put("name", name6);
						jtag.put("id", level6);
						arr.put(jtag);
					}
	      json.put("tags", arr);  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        
        response.setContentType("text/html;charset=utf-8"); 
        //response.getWriter().write(json.toString());   
        
        DBOperaion db = new DBOperaion();
        db.connet();
        try {
			db.addSources(sourceTitle, sourceURL, json.toString(),sourceDescription);
			response.getWriter().write("Success!");
		} catch (SQLException e) {
			if(e.getSQLState().equals("23000"))
			response.getWriter().write("Input Source Error: "+"The data has in the data base");
			else 
				response.getWriter().write("Input Source Error: "+e.getSQLState());	
			e.printStackTrace();
		}
        
    }  
		
	

}
