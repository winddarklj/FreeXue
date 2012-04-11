package model;

import java.util.ArrayList;

public class SysTag {
	
    String tagname;
    String tagid;
	ArrayList<SysTag> children;
	
	public SysTag() {
		
	}
	
	SysTag( String tagname, String tagid, ArrayList<SysTag> children) {
		this.tagname = tagname;
		this.tagid = tagid;
		this.children = children;
	}
	
	public SysTag( String tagname, String tagid) {
		this.tagname = tagname;
		this.tagid = tagid;
		this.children = new ArrayList<SysTag>();
	}
	
	public ArrayList<SysTag> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<SysTag> children) {
		this.children = children;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}

}
