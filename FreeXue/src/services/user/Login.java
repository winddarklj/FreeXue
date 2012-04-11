package services.user;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sqlbase.DBOperaion;
import sqlbase.DBOperaionTest;
import sqlbase.SessionFactory;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String account;
       String password;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    
    void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
          HttpSession session = request.getSession();
    	 account = (String)session.getAttribute("account");
    	 if(account == null)
    	 {
    		 account = request.getParameter("mail");
    		 password = request.getParameter("password");
    		 String verify = request.getParameter("verify");
    		 DBOperaion db = new DBOperaion();
    		 db.connet();
    		 boolean result = db.checkUser(account, password);
    		 String vcode = (String) session.getAttribute("vcode");
    		 System.out.println("verify: "+verify+"   vcode:"+vcode);
    		 System.out.println();
    		 if(!verify.equals(vcode))
    			 result = false;
    		 
    		 if(result) 
    		 {
    			 session.setAttribute("account", account);
    			 response.getWriter().write("Login Successfully!");
    		 }
    		 else 
    			 response.getWriter().write("Error!");
    	 }
    	 else response.getWriter().write("Login Successfully!"); 
    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
