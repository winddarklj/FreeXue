package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import json.JSONFactory;

public class source {

	
	String  title;
	ArrayList<SysTag>  tags;
	
	String  URL;
	String rank;
	String owner;
	String description;
	int like;
	int dislike;
	
	
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	public source(String title,String URL, String tags,  String rank ,String owner, int likeit, int dislike, String description) throws JSONException {

		this.title = title;
		if(tags!=null)
		{
		JSONObject obj = new JSONObject(tags);
		JSONArray array =obj.getJSONArray("tags"); 
		this.tags = JSONFactory.ToArrayList(array);
		}
		this.URL = URL;
		this.rank = rank;
		this.like = likeit;
		this.dislike = dislike;
		this.description = description;
	}
	
	public source() {
		
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setTags(String jsonStr) throws JSONException {
		JSONObject obj = new JSONObject();
		JSONArray arr = obj.getJSONArray("tags");
		this.tags = JSONFactory.ToArrayList(arr);
	}
	
	public void setTags(JSONArray arr) throws JSONException {
		this.tags = JSONFactory.ToArrayList(arr);
	}
	
	public ArrayList<SysTag> getTags() {
		return tags;
	}
	public void setTags(ArrayList<SysTag> tags) {
		this.tags = tags;
	}

	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
}
