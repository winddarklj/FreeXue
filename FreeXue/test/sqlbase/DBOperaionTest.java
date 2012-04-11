package sqlbase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.source;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBOperaionTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void testGetHierarchy() {
		DBOperaion  db = new DBOperaion();
		db.connet();
		db.GetHierarchy("1_2");
	}
	
	
	
	@Test
	public void testSetComment() {
		DBOperaion db = new DBOperaion();
		db.connet();
		db.SetComment("www.baidu.com", 3, 2);
		
	}
	
	@Test
	public void updateHierarchy() {
		DBOperaion  db = new DBOperaion();
		db.connet();
		db.UpdateHierarchy("1_1", "2_13", "2_14");
	}
	
	@Test
	public void testSetHierarchy() {
		DBOperaion  db = new DBOperaion();
		db.connet();
		System.out.println("SetHierarchy");
		db.SetHierarchy("2_7", "3_1");
	}

	@Test
	public void testSetSources() {
		
		DBOperaion db = new DBOperaion();
		db.connet();
		ArrayList<String> sarr = new ArrayList<String>();
		sarr.add("s10000");
		ArrayList<source> srcarr = new ArrayList<source>();
		String page = "1";
		srcarr = db.GetSources(sarr);
	
	}
	
}
