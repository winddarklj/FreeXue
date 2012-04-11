package services.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sqlbase.DBOperaion;

/**
 * Servlet implementation class SetCommentServlet
 */
@WebServlet("/SetCommentServlet")
public class SetCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
   public void process(HttpServletRequest request, HttpServletResponse response) {
	   
	   int like =Integer.parseInt(request.getParameter("like"));
	   int dislike = Integer.parseInt(request.getParameter("dislike"));
	   String url = request.getParameter("url");
	   
	   DBOperaion db = new DBOperaion();
	   db.connet();
	   
	   
	   
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
