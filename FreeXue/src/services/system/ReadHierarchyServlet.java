package services.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import json.JSONFactory;

import model.Hierarchy;
import model.SysTag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ReadHierarchyServlet
 */
@WebServlet(value="/ReadHierarchyServlet", loadOnStartup=1)
public class ReadHierarchyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadHierarchyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("------------------------------Come into Init ----------------------------------------------------");
		String classDir = this.getClass().getResource("/").getPath();
		System.out.println(classDir);
		classDir = classDir.substring(0,classDir.length() - 8);
		
		 try {
			 String s1 = ""; 
			 String s;
			 File f = new File(classDir+"Hierarchy.txt");
			
			 BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
			 
		       while ((s = input.readLine()) != null) {
		        s1 += s + "\n";
		       }
		       
		       input.close();
		       
		       try {
		    	   
				SysTag stag = JSONFactory.ParseToStructure(s1);
				Hierarchy.head = stag;
				Hierarchy.hierarchyStr = s1;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		       
			 /*	    
			    JSONObject level0 = new JSONObject();
			    JSONObject level0name = new JSONObject();
			    JSONArray level0arr = new JSONArray();
				JSONObject level1 = new JSONObject();
				JSONObject level1name = new JSONObject();
				JSONArray level1arr = new JSONArray();
				for(int k=0; k<1; k++){
						for(int i=0; i<1; i++)
						{
							JSONObject level2 = new JSONObject();
						    JSONArray level2arr = new JSONArray();
						         for(int j=0; j<4; j++)
						         {
						        	 JSONObject level3 = new JSONObject();
						        	 
						        	 level3.put("name", "3XXX");
									 level3.put("id", "3s_?");
						        	  level2arr.put(j,level3);
						         }
						       level2.put("hierarchy2", level2arr);
						       level2.put("name", "2XXX");
							   level2.put("id", "2_?");
						       level1arr.put(i,level2);
						}
				level1.put("hierarchy1",level1arr );
				level1.put("name", "1XXX");
				level1.put("id", "1_?");
				level0arr.put(k,level1);
				}
				
				level0.put("hierarchy0", level0arr);
				level0.put("name", "0XXX");
				level0.put("id", "0_?");
				System.out.println(level0); */
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
