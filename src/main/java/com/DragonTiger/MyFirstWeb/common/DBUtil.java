package com.DragonTiger.MyFirstWeb.common;

import java.sql.*;
public class DBUtil {
	
	static String url="jdbc:oracle:thin:@localhost:1521:XE";
	static String user="scott", pwd="tiger";
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getCon(){

		Connection con=null;

		try {
			System.out.println("DB연결 성공");
			return DriverManager.getConnection(url, user, pwd);
		}catch(SQLException e) {
			System.out.println("DB연결 실패");
		}
		return con;
	}

}