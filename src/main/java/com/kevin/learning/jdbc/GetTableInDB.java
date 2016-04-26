package com.kevin.learning.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GetTableInDB {

	
	String searchTableName = "USER_INFO".toUpperCase();
	
	String rootUrl = "jdbc:mysql://localhost:3306";
	String username = "root";
	String password = "root";
//	String rootUrl = "jdbc:mysql://172.16.7.232:33096";
//	String username = "root";
//	String password = "passwd32";
	
	Connection rootConn = null;
	
	@Before
	public void before(){
		rootConn = getConn(rootUrl,username,password) ;
	}
	
	
	@Test@Ignore
	public void getTable(){
		String tableName = "t_product";
		searchTable(tableName);
	}
	
	
	
	
	@Test
	public void searchCol(){
		searchCol("email");
	}
	
	
	
	@Test@Ignore
	public void getColWithVal(){
		getColWithVal(null,null,"email","googlevsbing@126.com");
	}
	
	public void getColWithVal(String searchDB,String table,String searchCol , String val){
		searchDB = searchDB == null ? null : searchDB.toUpperCase();
		table = table == null ? null : table.toUpperCase();
		
		searchCol = searchCol.toUpperCase();
		
		List<String> dbList = getAllDB(rootUrl+"/information_schema",username,password);
		
		if(searchDB!= null && !dbList.contains(searchDB)) {
			System.out.println(this.rootUrl +" does not contain " + searchDB);
			return;
		}
		
		for(Iterator<String>iter=dbList.iterator();iter.hasNext();){
			
			String dbName = iter.next();
			if(searchDB != null && !dbName.equals(searchDB)){
				//如果传了searchDb，则只查找一个库
				continue;
			}
			
			List<String> tableList = getTablesInDB(dbName);
			
			//对某个库的连接
			Connection conn  = getConn(rootUrl + "/"+dbName, username, password);
			
			for(Iterator<String> tbIter=tableList.iterator();tbIter.hasNext();){
				String tbName = tbIter.next();
				
				if(table != null && !tbName.equals(table)) continue;
				
//				Connection conn = getConn(rootUrl + "/" + dbName, username, password);
				
				List<String> colsList = getColsOfTable(tbName,dbName);
				if( table != null && !colsList.contains(searchCol) ){
					System.out.println(" table " + table + " does not contain col " + searchCol);
					continue;
				}
				if(!colsList.contains(searchCol)) continue;
				
				
				StringBuilder sql = new StringBuilder();
				sql.append(" select * from " + tbName +" where " + searchCol + " = '" + val  + "'");
				
				PreparedStatement pstmt;
				try {
					pstmt = conn.prepareStatement(sql.toString());
//					pstmt.setObject(1, val);
					pstmt.setFetchSize(Integer.MAX_VALUE);
					
					ResultSet rs = pstmt.executeQuery();
					int count = 0;
					ResultSetMetaData rsMeta =   rs.getMetaData();
					int colCount = rsMeta.getColumnCount();
					List<String> rsColList = new ArrayList<String>();
					for(int i=1;i<=colCount;i++){
//						System.out.println(rsMeta.getColumnLabel(1));
						rsColList.add( rsMeta.getColumnLabel(i) );
					}
					
					boolean hasResult = rs.next();
					if(!hasResult) continue;
					
					System.out.println("============FOUND MATCHED VAL IN TABLE " + tbName + " OF DB "+ dbName+" ============");
					System.out.println("SEARCH COL :" + searchCol + " ,SEARCH VAL: " + val);
					do {
						
						for(Iterator<String> rsColIter = rsColList.iterator();rsColIter.hasNext();){
							
							if ( rsColList.size()<count  || count >= 5 ) break;
							String colLabel = rsMeta.getColumnLabel(count+1);
							System.out.println( rsColList.get(count) + " : " + rs.getObject(colLabel));
							count++;
						}
						
					}while(rs.next());
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				}
				
			}
		}
		
		
	
	public void searchCol(String searchCol){
		searchCol(searchCol,true);
	}
	
	//找列
	public void searchCol(String searchCol,boolean definite){
		
		searchCol = searchCol.toUpperCase();
		
		List<String> dbList = getAllDB(rootUrl,username,password);
		for(Iterator<String>iter=dbList.iterator();iter.hasNext();){
			String dbName = iter.next();
			List<String> tableList = getTablesInDB(dbName);
			
			//对某个库的连接
//			Connection tableConn  = getConn(rootUrl + "/"+dbName, username, password);
			switchToDB(dbName);
			for(Iterator<String> tbIter=tableList.iterator();tbIter.hasNext();){
				String tbName = tbIter.next();
				
//				Connection conn = getConn(rootUrl + "/" + dbName, username, password);
				
				List<String> colsList = getColsOfTable(tbName,dbName);
				
				List<String> matchColList = new ArrayList<String>();
				//将逻辑改成字符串的包含
				for(Iterator<String> colIter=colsList.iterator();colIter.hasNext();){
					String col = colIter.next();
					
					if(definite){
						if(col.equals(searchCol)){
							System.out.println("FOUND COLUMN LIKE " + searchCol + 
									","+ col  +" IN TABLE " + tbName + " OF DB " + dbName);
						}
					}else{
						if(col.contains(searchCol)){
							System.out.println("FOUND COLUMN LIKE " + searchCol + 
									","+ col  +" IN TABLE " + tbName + " OF DB " + dbName);
//							matchColList.add(col);
						} 
						
					}
					
					
				}
				
			}
		}
		
	}
	
	// 获取一个表的所有列，这里一定要提供dbName,之前想通过 conn.getCatalog()的方式获取dbname，但发现，switchDB后
	//获取不到catalog，不知道是mysql驱动的bug还是本身就这样
	public List<String> getColsOfTable(String tableName ,String catalog){
		
		try{
//			String catalog = rootConn.getCatalog();
			String sql = "select COLUMN_NAME from information_schema.COLUMNS where table_name = ? and table_schema = ? ";
			PreparedStatement stmt = rootConn.prepareStatement(sql);
			
			stmt.setString(1, tableName);
			stmt.setString(2, catalog);
			
			ResultSet rs = stmt.executeQuery();
			List<String> colsList = new ArrayList<String>();
			while(rs.next()){
				colsList.add(rs.getString(1));
			}
			return colsList;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
		
	}

	
	public void searchTable(String tableName){
		searchTable(tableName,true);
	}
	
	public void searchTable(String tableName,boolean definite){
		
		tableName = tableName.toUpperCase();
		List<String> dbList = getAllDB(rootUrl,username,password);
		for(Iterator<String>iter=dbList.iterator();iter.hasNext();){
			String dbName = iter.next();
			List<String> tableList = getTablesInDB(dbName);
			
			for(Iterator<String>tbIter=tableList.iterator();tbIter.hasNext();){
				String tb = tbIter.next();
				
				if(definite){
					if(tb.equals( tableName )){
						System.out.println(" FOUND TABLE " + tableName + " IN DB " + dbName );
					}
				}else{
					if(tb.toUpperCase().contains(tableName)){
						System.out.println(" FOUND TABLE LIKE " + tableName + ","+ tb +" IN DB " + dbName );
					}
				}
			}
		}
	}
	
	
	public List<String> getTablesInDB(String dbName){
		try{
//			Connection conn = getConn(rootUrl + "/" + dbName, username, password);
//			Statement stmt = rootConn.createStatement();
//			stmt.execute("use  " + dbName);
			switchToDB(dbName);
			ResultSet tableResultSet = rootConn.createStatement().executeQuery("show tables");
			List<String> tableList = new ArrayList<String>();
			while(tableResultSet.next()){
				String name = tableResultSet.getString(1);
				tableList.add(name.toUpperCase());
			}
			return tableList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getAllDB(String rootUrl,String username,String password){
		try{
			
			List<String> dbList = new ArrayList<String>();
//			Connection rootConn = getConn(rootUrl, username, password);
			
			ResultSet showDbRs = rootConn.createStatement().executeQuery("show databases");
			
			while(showDbRs.next()){
				
				dbList.add(  showDbRs.getString(1)  );
				
			}
			
			DatabaseMetaData metaData = rootConn.getMetaData();
			ResultSet rs = metaData.getCatalogs();
			
			
			//这种方式无论connection是指向rootUrl还是具体的DB都可以获取所有库
//			while(rs.next()){
//				String dbname = rs.getString("TABLE_CAT");
//				dbList.add(dbname);
//			}
			return dbList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void switchToDB(String dbName){
		try {
			boolean re = rootConn.createStatement().execute("use " + dbName);
//			System.out.println("siwtch to " + dbName + ", result " + re);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//找表
	@Test@Ignore
	public void searchTable(){
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
					tableList.add(name.toUpperCase());
				}
				
				
				if(tableList.contains(searchTableName)){
					System.out.println( "found " +  searchTableName + " in " + dbname);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Throwable {

		
		GetTableInDB d = new GetTableInDB();
		Connection rootConn = d.getConn(d.rootUrl + "/information_schema", d.username, d.password);
		Statement stmt = rootConn.createStatement();
		
		boolean res = stmt.execute("use ogms");
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from member_recharge_record where 1=1 ");
		sql.append(" and ( member_id like  CONCAT(CONCAT('%',?) ,'%')" );
		sql.append(" or bank_id like  CONCAT(CONCAT('%',?) ,'%')"
				+ ")");
		
		PreparedStatement pstmt = rootConn.prepareStatement(sql.toString());
		pstmt.setObject(1, "20150816091054100001");
		pstmt.setString(2, "20150916161252100002");
		
		
		
		ResultSet tableResultSet = pstmt.executeQuery();//stmt.executeQuery("show tables");
		  
		List<String> tableList = new ArrayList<String>();
		while(tableResultSet.next()){
			String name = tableResultSet.getString(1);
			tableList.add(name.toUpperCase());
		}
		

	}
	
	
	public  Connection getConn(String url,String name,String password) {
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
