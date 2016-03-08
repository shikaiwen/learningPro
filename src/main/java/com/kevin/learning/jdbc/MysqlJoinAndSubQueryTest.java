package com.kevin.learning.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 子查询对性能稍微有提升
 * @author Administrator
 *
 */

public class MysqlJoinAndSubQueryTest {

	
	
	public static void main(String[] args) throws Exception{
		long start = System.currentTimeMillis();
		for(int i = 0; i < 1000;i++){
			test2();
		}
		long end = System.currentTimeMillis();
		System.out.println("time cost : " + (end - start));
	}
	
	public static void test1() throws Exception{
		
		
		Connection conn = getConn();
		String sql = " 		SELECT A.* , "
				+ " 		B.RQ_CSCQPQ_NAME ,  "
				+ " 		B.RQ_HSL, 			 "
				+ " 		C.PHONE_TEL			 "
				+ " 		FROM KY_CUSTOMER_COOPERATE A  "
				+ " 		LEFT JOIN KY_CUSTOMER_REQUIRE B ON A.CS_RQ_ID = B.ID "
				+ " 		left join HR_WORKER C on A.CS_COOPERATE_ID = C.WORKER_ID "
				+ " 		WHERE A.CS_WORKER_ID = '01010540'   ";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			System.out.println(rs.getString(1) + ","
		+rs.getString(2) + ","
		+rs.getString(3) + ","
		+ rs.getString(4));
		}
		
		conn.close();
		
		
		
	}
	
	public static void test2() throws Exception{
		Connection conn = getConn();
		String sql = " 		SELECT A.* , "
				+ " 		B.RQ_CSCQPQ_NAME ,  "
				+ " 		B.RQ_HSL, 			 "
				+ " 		C.PHONE_TEL			 "
				+ " 		FROM KY_CUSTOMER_COOPERATE A  "
				+ " 		LEFT JOIN KY_CUSTOMER_REQUIRE B ON A.CS_RQ_ID = B.ID "
				+ " 		LEFT JOIN (SELECT * FROM HR_WORKER WHERE WORKER_ID = '01010540')   C on A.CS_COOPERATE_ID = C.WORKER_ID "
				+ " 		WHERE A.CS_WORKER_ID = '01010540'   ";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			System.out.println(rs.getString(1) + ","
		+rs.getString(2) + ","
		+rs.getString(3) + ","
		+ rs.getString(4));
		}
		
		conn.close();
	}
	
	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://172.16.7.232:33096/jjsky";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "passwd32");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
