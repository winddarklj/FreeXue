package sqlbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import json.JSONFactory;


import model.Hierarchy;
import model.SysTag;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;


public class ReadXLSX {
	
	public  void read(String strPath) throws IOException, InvalidFormatException {
	
		InputStream inp = new FileInputStream(strPath);

	    Workbook wb = WorkbookFactory.create(inp);
	   
	    Sheet sheet = wb.getSheetAt(0);
	    
	    for (Row row : sheet) {
	    	if(row.getRowNum() != 0){
	    		
			    	ArrayList<String> rowlist = new ArrayList<String>();
			        for (Cell cell : row) {
			            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
			            switch (cell.getCellType()) {
			                case Cell.CELL_TYPE_STRING:
			                    //System.out.println(cell.getRichStringCellValue().getString());
			                    rowlist.add(cell.getRichStringCellValue().getString());
			                    break;
			                case Cell.CELL_TYPE_NUMERIC:
			                    if (DateUtil.isCellDateFormatted(cell)) {
			                        //System.out.println(cell.getDateCellValue());
			                        rowlist.add(cell.getDateCellValue().toString());
			                    } else {
			                        //System.out.println(cell.getNumericCellValue());
			                        rowlist.add(String.valueOf(cell.getNumericCellValue()));
			                    }
			                    break;
			                case Cell.CELL_TYPE_BOOLEAN:
			                    //System.out.println(cell.getBooleanCellValue());
			                    break;
			                case Cell.CELL_TYPE_FORMULA:
			                   //System.out.println(cell.getCellFormula());
			                    break;
			                default:
			                    //System.out.println();
			            }
			        }
			        DBOperaion db =new DBOperaion();
			        db.connet();
			        
			       
			        ////////////////////////////////////////////////
			           
			        String tagID = Hierarchy.getTagid(Hierarchy.head, rowlist, 3, 3, 1);
			        SysTag tag1 = new SysTag(rowlist.get(3),tagID);
			        String tagID2 = Hierarchy.getTagid(Hierarchy.head, rowlist, 3, 4, 2);
			        SysTag tag2 = new SysTag(rowlist.get(4),tagID2);
			        String tagID3 = Hierarchy.getTagid(Hierarchy.head, rowlist, 3, 5, 3);
			        SysTag tag3 = new SysTag(rowlist.get(5),tagID3);
			        String tagID4 = Hierarchy.getTagid(Hierarchy.head, rowlist, 3, 6, 4);
			        SysTag tag4 = new SysTag(rowlist.get(6),tagID4);
			        String tagID7 = Hierarchy.getTagid(Hierarchy.head, rowlist, 3, 7, 5);
			        SysTag tag5 = new SysTag(rowlist.get(7),tagID7);
			        String tagID8 = Hierarchy.getTagid(Hierarchy.head, rowlist, 3, 8, 6);
			        SysTag tag6 = new SysTag(rowlist.get(8),tagID8);
			        
			        ArrayList<SysTag> tags = new ArrayList<SysTag>();
			        tags.add(tag1);
			        tags.add(tag2);
			        tags.add(tag3);
			        tags.add(tag4);
			        tags.add(tag5);
			        tags.add(tag6);
			        JSONObject jtags;
			       try {
					 jtags = JSONFactory.ParseTagToJSON(tags, 0, tags.size());
					 db.addSources(rowlist.get(0), rowlist.get(1), jtags.toString(), rowlist.get(3));
				} catch (JSONException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			       
	        	}
	        
	    }
}
	
	public static void main(String[] args) {  
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS");  
	    TimeZone t = sdf.getTimeZone();  
	    t.setRawOffset(0);  
	    sdf.setTimeZone(t);  
	    Long startTime = System.currentTimeMillis();  
	    String fileName = "C:\\Users\\Lynx\\Downloads\\toefl_peiwen.xlsx";  
	   
	    try {  
	        ReadXLSX er = new ReadXLSX();  
	       
	        er.read(fileName);  
	    } catch (Exception ex) {  
	    	Logger.getLogger(ReadXLSX.class.getName()).log(Level.SEVERE, null, ex);
	    }  
	    Long endTime = System.currentTimeMillis();  
	    System.out.println("Time" + sdf.format(new Date(endTime - startTime)));  
	}  

}
