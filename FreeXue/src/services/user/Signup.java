package services.user;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sqlbase.DBOperaion;
import sqlbase.DBOperaionTest;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private String account;
    private String password;
    private String username;
	
    public Signup() {
        super();
    }
    
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	 account = request.getParameter("user");
		 password = request.getParameter("pwd");
		 username = request.getParameter("username");
		 String verify = request.getParameter("verify");
		 HttpSession session =  request.getSession();
		 
		 String vcode = (String) session.getAttribute("vcode");
		 System.out.println("verify: "+verify+"   vcode:"+vcode);
		 
		 DBOperaion db = new DBOperaion();
		 db.connet();
		 
		 long ID = UUID.randomUUID().getLeastSignificantBits();
		 if(ID<0)
			 ID = -ID;
		 
		 boolean result = true;
		 
		 if(!verify.equals(vcode))
		 {
			 response.getWriter().write("Verify Code Error!");
		 }
		 else 
		 {
			 result = db.AddUser(account, password, username, String.valueOf(ID));
			 if(result)
			 {
				 session.setAttribute("account", account);
				 response.getWriter().write("Signup Successfully!");
				 
				 //////////////////////////////////////////Add a folder by ID
				 String classDir = this.getClass().getResource("/").getPath();
					
				  classDir = classDir.substring(0,classDir.length() - 8);
				  String filepath = classDir+ID+"/";
				  System.out.println("filepath: "+filepath);
				   PrintWriter  out = response.getWriter();
				   File uploadPath = new File(filepath);//上传文件目录
				    if (!uploadPath.exists()) {
				       uploadPath.mkdirs();
				    }
			 }
			 else 
				 response.getWriter().write("Error!");
		 }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

}
