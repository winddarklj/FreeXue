package services.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetCommonTags
 */
@WebServlet(description = "GetCommonSrcServlet", urlPatterns = { "/GetCommonTags" })
public class GetCommonTags extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCommonTags() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void Process(HttpServletRequest request, HttpServletResponse response) {
    	String level1 = request.getParameter("level1");
    	String level2 = request.getParameter("level2");
    	String level3 = request.getParameter("level3");
    	String level4 = request.getParameter("level4");
    	String level5 = request.getParameter("level5");
    	String level6 = request.getParameter("level6");
    	
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Process(request, response);
	}

}
