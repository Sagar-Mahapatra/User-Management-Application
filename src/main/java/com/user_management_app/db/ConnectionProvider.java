package com.user_management_app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	private static Connection con;

//DB connection
	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_management_app", "root", "mysql20apr2021");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;

	}

}
