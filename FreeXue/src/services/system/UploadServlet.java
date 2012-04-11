package services.system;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sqlbase.ReadXLSX;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().println("请以POST方式传输文件");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
		  String classDir = this.getClass().getResource("/").getPath();
		
		  classDir = classDir.substring(0,classDir.length() - 8);
		  String filepath = classDir+"XLSXData/";
		  
		   PrintWriter  out = response.getWriter();
		   File uploadPath = new File(filepath);//上传文件目录
		    if (!uploadPath.exists()) {
		       uploadPath.mkdirs();
		    }
		    // 临时文件目录
		    File tempPathFile = new File(filepath+"Temp");
		    if (!tempPathFile.exists()) {
		       tempPathFile.mkdirs();
		    }
		    try {
		       // Create a factory for disk-based file items
		       DiskFileItemFactory factory = new DiskFileItemFactory();
		 
		       // Set factory constraints
		       factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
		       factory.setRepository(tempPathFile);//设置缓冲区目录
		 
		       // Create a new file upload handler
		       ServletFileUpload upload = new ServletFileUpload(factory);
		 
		       // Set overall request size constraint
		       upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
		 
		       List<FileItem> items = upload.parseRequest(request);//得到所有的文件
		       Iterator<FileItem> i = items.iterator();
		     
		       
		       while (i.hasNext()) {
		           FileItem fi = (FileItem) i.next();
		           if(!i.hasNext())
		           break;
		           String fileName = fi.getName();
		           
		           if (fileName != null) {
		        	   String   suffix   =   fileName.substring(fileName.lastIndexOf( '.')   +   1);
		        	   if(suffix.equals("xlsx"))
		        	   {
		        		   File fullFile = new File(fi.getName());
			        	   File savedFile = new File(uploadPath, fullFile
			              .getName());
			        	   fi.write(savedFile);
			        	   out.println("upload succeed!");
			        	   
			        	   ReadXLSX rd = new ReadXLSX();
			        	   System.out.println("Name: "+filepath+"\\"+fileName);
			        	   rd.read(filepath+"\\"+fileName);
			        	   
		        	   }
		        	   else out.print("wrong format!");
		           }
		           else out.print("no file!");
		       }
		    } catch (Exception e) {
		       e.printStackTrace();
		    }
	}

}
