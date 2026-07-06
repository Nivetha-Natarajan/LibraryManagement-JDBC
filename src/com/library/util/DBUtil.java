package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil{
	public static Connection getDBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/librarydb";
			String user="root";
			String pass="root";
			Connection connection=DriverManager.getConnection(url,user,pass);
			return connection;
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}
}
