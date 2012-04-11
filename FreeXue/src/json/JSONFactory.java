package json;

import java.util.ArrayList;

import model.Hierarchy;
import model.SysTag;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONFactory {
	
	public static SysTag ParseToStructure(String json) throws JSONException {
		
	   JSONObject hierarchy = new JSONObject(json);
	   String name=  hierarchy.getString("name");
	   String id = hierarchy.getString("id");
	   System.out.println("--------------------------------------------");
	   System.out.println("name: "+name);
	   System.out.println("id:" + id);
	   SysTag stag = new SysTag(name, id);
	   JSONArray children;
	   if(hierarchy.has("hierarchy"))
	   {
		   System.out.println("Yes, it has hierarchy");
		   children = hierarchy.getJSONArray("hierarchy"); 
		   ArrayList<SysTag> clist = new ArrayList<SysTag>();
		   System.out.println("length:" + children.length());
		   
			for(int i=0; i< children.length(); i++)
			{
				JSONObject element = new JSONObject();
				element = children.getJSONObject(i);
				SysTag childtag= ParseToStructure(element.toString());
				clist.add(childtag);
			}
			
			stag.setChildren(clist);
	   }
	   
	 
		return stag;
	}
	
	public static JSONObject ParseTagToJSON(ArrayList<SysTag> tags, int start, int end) throws JSONException {
		
		JSONObject obj = new JSONObject();
		JSONArray arr =new JSONArray();
		for(int i=start; i<end; i++) {
			SysTag tag = tags.get(i);
			JSONObject jtag =new JSONObject();
			jtag.put("id",tag.getTagid());
		    jtag.put("name", tag.getTagname());
		    arr.put(jtag);
		}
		obj.put("tags", arr);
		return obj;
	}

	
	public static ArrayList<SysTag> ToArrayList(JSONArray arr) {
		
		ArrayList<SysTag> list = new ArrayList<SysTag>(); 
		try {
		
		for(int i=0; i<arr.length(); i++)
			{
			  String tagid="";
			  String tagname="";
			 
			  
				JSONObject jtag = arr.getJSONObject(i);
			    tagid = jtag.getString("id");
			  tagname = jtag.getString("name");
			  
		
			   System.out.println("in JSONFactory: tagid= "+tagid+"  name="+tagname);
			   if(tagid.charAt(0) == 's')
			   {
				   SysTag tag = new SysTag(tagname,tagid);
				   list.add(tag);
			   }else{
					SysTag tag = new SysTag();
					tag.setTagid(tagid);
					tag.setTagname(tagname);
					list.add(tag);
			   }
			  
			}	
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}
