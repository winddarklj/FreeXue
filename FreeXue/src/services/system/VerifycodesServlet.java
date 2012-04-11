package services.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VerifycodesServlet
 */
@WebServlet("/VerifycodesServlet")
public class VerifycodesServlet extends HttpServlet {
private static final long serialVersionUID = 1L;


	public static final char[] CHARS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',         
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',         
        'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };    
	
	public static Random random = new Random();
	/**
	 *  Generate 6 characters verify code;
	 * @return 
	 */
	public static String getRandomString() {
		 StringBuffer buffer = new StringBuffer();
		 for( int i = 0; i< 6; i++)
		 {
			 buffer.append(CHARS[random.nextInt(CHARS.length)]);
		 }
		 return buffer.toString();
	}
	
	/**
	 *  Generate  random color
	 * @return
	 */
	public static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	
	public static Color getReverseColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255- c.getBlue());
	}
	
	/**
	 * @throws IOException 
	 *  
	 */
	private void GenerateVerifycodeImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String vcode = getRandomString();
		request.getSession().setAttribute("vcode",vcode );
		
		response.setContentType("image/jpeg");
		
		int width = 100, height = 30;
		
		Color color = getRandomColor();
		Color reverse = getReverseColor(color);
		
		BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = bi.createGraphics();
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.setColor(reverse);
		g.drawString(vcode, 18, 20);
		
		
		// 禁止图像缓存。      
        response.setHeader("Pragma", "no-cache");      
        response.setHeader("Cache-Control", "no-cache");      
        response.setDateHeader("Expires", 0);  
		
		for(int i=0, n= random.nextInt(100); i<n; i++)
		{
			g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		
		ServletOutputStream out= response.getOutputStream();
		g.dispose();  
        ImageIO.write(bi, "JPEG", response.getOutputStream());
		 // 禁止图像缓存。         
        System.out.println("YES, comes in");
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifycodesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GenerateVerifycodeImg(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GenerateVerifycodeImg(request, response);
	}

}
