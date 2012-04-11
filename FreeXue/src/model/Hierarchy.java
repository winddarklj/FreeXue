package model;

import java.util.ArrayList;

public class Hierarchy {
  public static SysTag head;
  public static String hierarchyStr;
  /**
   * 
   * @param parent
   * @return
   */
  public static SysTag getChildren(String parent, SysTag node) {  
	  if(node.tagid.equals(parent))
	  {
		 return node;
	  }
	  else {
		  ArrayList<SysTag> slist = node.getChildren();
		  for(int i =0; i< slist.size(); i++)
		  {
			  SysTag st = getChildren(parent, slist.get(i));
			  if(st!=null)
			  return  st;
		  }
	  }
	  return null;
  }
  
  
  
 public static String getTagid(SysTag node, ArrayList<String> tagnames,int position, int target, int level) {
	  
	
	 String tagname =tagnames.get(position);
	 
	 if((position == target)||(position >=6))
	 {
		 System.out.println("WENT TOS "+ node.getTagid()+"  "
                 +node.getTagname()+"  "
                 +position+"  "
                 +target+" "
                 +tagnames.get(target));
		 if(position>=6)
			 tagname = tagnames.get(target);
		 System.out.println("node.tagid.substring(1, 2)    "+node.tagid.substring(1, 2));
		 System.out.println("String.valueOf(level))    "+String.valueOf(level));
		 if(node.tagname.equals(tagname)&&(node.tagid.substring(1, 2).equals(String.valueOf(level))))
		 { 
			 System.out.println("HIT!1");
			 return node.tagid;
			 }
		 else
		 {
			 ArrayList<SysTag> slist = node.getChildren();
			  for(int i =0; i< slist.size(); i++)
			  {   
				  System.out.println("WENT TO1 "+ node.getChildren().get(i).getTagid()+"  "
			                                                          +node.getChildren().get(i).getTagname()+"  "
			                                                          +position+"  "
			                                                          +target+" "
			                                                          +tagnames.get(target));
						  	
				  String tagid = getTagid(node.getChildren().get(i),tagnames,position, target, level);
				  
				  if(!tagid.equals("NOT FOUND"))
				  {  
					  return tagid;
				  }
			  }
		 }
	 }
	 else {
			  if(node.tagname.equals(tagname))
			  {
				  System.out.println("HIT!");
				  
				  ArrayList<SysTag> slist = node.getChildren();
				  for(int i =0; i< slist.size(); i++)
				  {   
					  int np = position+1;
					  System.out.println("WENT TO2 "+ node.getChildren().get(i).getTagid()+"  "
                              +node.getChildren().get(i).getTagname()+"  "
                              +(np)+"  "
                              +target+" "
                              +tagnames.get(target));
					  String tagid;
					  tagid = getTagid(node.getChildren().get(i),tagnames,position+1, target, level);
					  if(!tagid.equals("NOT FOUND"))
					  {  
						  return tagid;
					  }
				  }
			  }
			  else{
				  ArrayList<SysTag> slist = node.getChildren();
				  for(int i =0; i< slist.size(); i++)
				  {   
					  
					  System.out.println("WENT TO3 "+ node.getChildren().get(i).getTagid()+"  "
                              +node.getChildren().get(i).getTagname()+"  "
                              +position+"  "
                              +target+" "
                              +tagnames.get(target));
					  String tagid = getTagid(node.getChildren().get(i),tagnames,position, target, level);
					  if(!tagid.equals("NOT FOUND"))
					  {  
						  return tagid;
					  }
				  }
			  }
	 }
	  return "NOT FOUND";
  }
 
  
  public static String getTagname(SysTag node, String tagid) {
	  
	  System.out.println("node: "+node.tagid+"   "+node.tagname+"         search:  "+tagid );
	  if(node.tagid.equals(tagid))
	  {
		  System.out.println("HIT!");
		 return node.tagname;
	  }
	  else {
		  ArrayList<SysTag> slist = node.getChildren();
		  for(int i =0; i< slist.size(); i++)
		  {
			  String name = getTagname(node.getChildren().get(i), tagid);
			  if(!name.equals("not set"))
			  return  name;
		  }
	  }
	  return "not set";
  }
}
