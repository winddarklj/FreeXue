package sqlbase;


import  java.sql.*;
import java.util.ArrayList;

import json.JSONFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.SysTag;
import model.source;
import com.google.gson.Gson;


public class DBOperaion
{
	
     Connection conn;
     Statement stmt;
     ResultSet rs;
     
     public boolean ModifyTag(String oldtag, String newtag) {
    	 
    	 return false;
     }
     
     public void UpdateHierarchy(String parent, String oldchild, String newchild) {
    	 
     try {
			stmt = conn.createStatement();
			String sql = "UPDATE sourcehierarchy SET ChildTag = '"+newchild+"' WHERE ChildTag = '"+oldchild+"' AND FatherTag ='"+parent+"'";
			stmt.executeUpdate(sql);
			
			conn.commit();
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     
     public void SetComment(String url, int like, int dislike) {
    	 
    		 try {
				stmt = conn.createStatement();
				String sql = "UPDATE sources SET likeit = "+like+" , dislike = "+dislike+" WHERE url = '"+url+"'";
				System.out.println(sql);
				
				stmt.executeUpdate(sql);
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     }
     
     public ArrayList<String> AllTags() {
    	 
    	 try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM ( SELECT DISTINCT(ChildTag) FROM sourcehierarchy "
						+"WHERE ChildTag NOT IN (SELECT DISTINCT(FatherTag) FROM sourcehierarchy) "
						+"UNION "
						+"SELECT DISTINCT(FatherTag) FROM sourcehierarchy ) AS ALLUNION "
						+"ORDER BY ChildTag";
			
			rs = stmt.executeQuery(sql);
			
			ArrayList<String> taglist = new ArrayList<String>();
			
			while(rs.next()){
				String tag = rs.getString(1);
				taglist.add(tag);
				
			}
			
			return taglist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return null;
     }
     
     public ArrayList<source> GetSources(ArrayList<String> tags) {
    	
    	 try {
			stmt= conn.createStatement();
			String sql = "SELECT * FROM sources";
			rs = stmt.executeQuery(sql);
            
			ArrayList<source> sources = new ArrayList<source>();
			while(rs.next()){
				System.out.println("Come in"+ rs.getString(1) +"   "+ rs.getString(2)); 
				
				source src = new source(
						 rs.getString(1),
						 rs.getString(2),
						 null,
						 rs.getString(4),
						 rs.getString(5),
						 rs.getInt(6),
						 rs.getInt(7),
						 rs.getString(8)
						 );
				     System.out.println("rs.getString(3)  "+rs.getString(3));
				     JSONObject jsontags = new JSONObject(rs.getString(3));
				     JSONArray arr= new JSONArray(jsontags.getString("tags"));
				     
				     src.setTags(JSONFactory.ToArrayList(arr));
				 	ArrayList<SysTag> srctags = src.getTags();
					
			        boolean isContained =   this.ContainsAll(srctags, tags);
			        System.out.println("Title: "+ src.getTitle()+ " ------------------------URL:   "+ src.getURL());
			        System.out.println("Value: "+ isContained);
			        if(isContained) {
						   sources.add(src);
				    }
			 }
			
			return sources;
	    	 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			
		}
 
    	 return null;
     }
     
     /**
      * 
      * @param list1  resource
      * @param tags  searched tags
      * @return
      */
     public boolean ContainsAll(ArrayList<SysTag> list1, ArrayList<String> tags ) {
    	int sum = 0;
    
    	 for(int j=0; j<tags.size(); j++)
    	 {   int i;
    	 for(i=0; i<list1.size(); i++)
    		 {
    			 String str2 = tags.get(j);
    			 String str1 = list1.get(i).getTagid();

    			 if(str2.equals(str1))
    			 {  System.out.println("EQUAL");
    			     sum++;
    			 }
    			 else {
    				 System.out.println("String:  "+str1+"substring: "+str1.substring(2));
    				boolean issametype = ( str1.charAt(1) == str2.charAt(1));
    				boolean isothers = (str2.substring(2).equals("9999"));
    				System.out.println("str1.charAt(1):  "+ str1.charAt(1) + "  str2.charAt(1)" + str2.charAt(1));
    				
    						if(issametype&&isothers)
    						 {  System.out.println("EQUAL");
    	    			     	sum++;
    						 }
    			 }
    		 }
    	 }
    	 System.out.println("Sum"+sum + "list2.size()" + tags.size());
    	if(sum == tags.size())
    		return true;
    	 return false;
     }
     
     public void SetHierarchy(String parent, String child) {
    	 try {
    		 stmt= conn.createStatement();
    		 String sql = "SELECT * FROM sourcehierarchy  WHERE FatherTag= '" + parent + "' AND ChildTag = '"+ child +"'";
    		 
    		 rs = stmt.executeQuery(sql);
    		
    		 
    		 if(!rs.next())
    		 {
    			
    			 //Do Insert
    			 stmt = conn.createStatement();
 				sql = "INSERT INTO sourcehierarchy(FatherTag, ChildTag) values ('"
 				                   +parent+"','"
 						           +child+ "'"
 				                   +" )"; 
 	
 				stmt.executeUpdate(sql);
 			    conn.commit();
    		 }
    		 else {
    			 //Update
    			 stmt = conn.createStatement();
  				sql = "UPDATE  sourcehierarchy SET ChildTag = '"
  				                   +child+"' WHERE FatherTag = '"
  						           +parent+ "'"
  				                   ; 
  				
  				stmt.executeUpdate(sql);
  			    conn.commit();
    			
    		 }
    		
    		 
    	 } catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		
     }
     
     
     public Object[] GetHierarchy(String parent) {
    	 
    	 try {
    			stmt= conn.createStatement();
    			String sql = "SELECT * FROM sourcehierarchy  WHERE FatherTag= '" + parent + "'   ";
    			rs = stmt.executeQuery(sql);
    			
    			ArrayList children =new ArrayList();
    			
    			while(rs.next()) {
    				String child = rs.getString(2);
    				children.add(child);
    			}
    			Object[] carray =  children.toArray();
    			for(int i=0; i<carray.length; i++) {
    			
    			}
    		
    			return children.toArray();
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		   return null;
    	 	
     }
     
     
     public boolean AddUser(String User, String Psd, String Name, String UserID) {
    	 try {
    			stmt = conn.createStatement();
    			String sql = "INSERT INTO user(email, password, authority, username,userID) values ('"
    			                   +User+"','"
    					           +Psd+ "',"
    			                   +"'777'  , '" 
    			                   +Name+"','" 
    			                   +UserID+"')"; 
    		
    			stmt.executeUpdate(sql);
    			System.out.println(sql);
    		    conn.commit();
    		    return true;
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			return false;
    		}
     }

   public boolean checkUser(String User, String Psd) {
	   try {
		stmt= conn.createStatement();
		String sql = "SELECT * FROM user WHERE email = '"+User +"'";
		rs = stmt.executeQuery(sql);
		if(!rs.first())
			return false;
		
		String dbpsd = rs.getString(0);
		ConnectionPool.returnConnection(conn);
		if(dbpsd.equals(Psd)) {
			return true;
		}
		else return false;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return false;
	   
   }
     
   public void addSources(String title, String URL, String tags, String description) throws SQLException {
	
		stmt = conn.createStatement();
		String sql = "INSERT INTO sources(title, url, tags, rank, ownerid, description) values ('"
						   +title+"', '"
		                   +URL+"','"
				           +tags+ "',"
		                   +"'1'  ," 
		                   +" 'Jacky',"
		                   +"'"+description+"')"; 
		System.out.println(sql);
		stmt.executeUpdate(sql);
	    conn.commit();
	    ConnectionPool.returnConnection(conn);
   }
 
    public void connet()
    {
       /*
    	try
        {
    		try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				System.out.println("加载驱动器失败");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.out.println("加载驱动器失败");
				e.printStackTrace();
			}
        }
        catch(ClassNotFoundException ce)
        {
        	System.out.println("加载驱动器失败");
        } */
        try
        {
           //String url = "jdbc:mysql://23.21.146.44:3306/freexue?user=root&password=eagle"; 
  	       //conn = DriverManager.getConnection(url);  
            conn = ConnectionPool.getConnection(); 
            conn.setAutoCommit(false);
            
        }
        catch (Exception e)
        {
        	System.out.println("Come in to connection");
        	e.printStackTrace();
        	
            if(null != conn)
            {
                try
                {
                    conn.rollback();
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally
        {
            if (null != rs)
            {
                try
                {
                    rs.close();
                    rs = null;
                }
                catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
 
            if (null != stmt)
            {
                try
                {
                    stmt.close();
                    stmt = null;
                }
                catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
        }
    }
}