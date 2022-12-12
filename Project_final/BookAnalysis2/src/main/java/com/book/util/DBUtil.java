package com.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	public static final String driverClass = "com.mysql.jdbc.Driver";
	public static final String dbUrl = "jdbc:mysql://localhost:3306/book_analysis";
	public static final String userName = "root";
	public static final String passwd = "root";
	public static Connection conn = null;
	public static Connection getConnection() {
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(dbUrl,userName,passwd);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection c, java.sql.Statement s, ResultSet r)
			throws SQLException {
		if (c != null) {
			c.close();
		}
		if (s != null) {
			s.close();
		}
		if (r != null) {
			r.close();
		}
	}
	
}
