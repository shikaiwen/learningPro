package com.kevin.learning.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class GetTableInDB {

	
	
	public static void main(String[] args) {
		
	}
	
	
	String searchTableName = "USER_INFO";
	
	@Test
	public void t1(){
		
//		Connection conn = getConn();
		try {
		
			
			String rootUrl = "jdbc:mysql://172.16.7.232:33096";
			String username = "root";
			String password = "passwd32";
			
			List<String> dbList = new ArrayList<String>();
			Connection rootConn = getConn(rootUrl, username, password);
			
			DatabaseMetaData metaData = rootConn.getMetaData();
			ResultSet rs = metaData.getCatalogs();
			while(rs.next()){
				String dbname = rs.getString("TABLE_CAT");
//				System.out.println(dbname);
				dbList.add(dbname);
			}
			
			
			
			for(Iterator<String> iter=dbList.iterator();iter.hasNext();){
				String dbname = iter.next();
				Connection con = getConn(rootUrl + "/" + dbname , username,password);
				Statement stmt = con.createStatement();
				ResultSet tableResultSet = stmt.executeQuery("show tables");
				
				List<String> tableList = new ArrayList<String>();
				while(tableResultSet.next()){
					String name = tableResultSet.getString(1);
					tableList.add(name);
				}
				
				
				if(tableList.contains(searchTableName)){
					System.out.println( "found " +  searchTableName + " in " + dbname);
				}
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConn(String url,String name,String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		String url = "jdbc:mysql://172.16.7.232:33096";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://172.16.7.232:33096";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "passwd32");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
