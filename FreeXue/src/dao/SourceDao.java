package dao;

import model.source;

public interface SourceDao {
	
	 void AddSource(String title, String URL, String tags, String rank, int like, int dislike); 
	 source GetSource(String URL); 
	 void DeleteSource(String URL);
	 void UpdateSource(String title, String URL, String tags, String rank, int like, int dislike);	 
}
