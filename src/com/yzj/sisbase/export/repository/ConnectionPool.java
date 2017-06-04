package com.yzj.sisbase.export.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {

	private static Connection conn = null;

	public static Connection getConnection() throws Exception{
			if (conn == null || conn.isClosed()) {
				ResourceBundle bundle = ResourceBundle.getBundle("exportAccInfo");
				Class.forName(bundle.getString("driverclass"));
				String url = bundle.getString("url");
				String username = bundle.getString("username");
				String password = bundle.getString("password");
				conn = DriverManager.getConnection(url, username, password);
			}

		return conn;
	}

	public static void close(Connection conn, PreparedStatement ps,
			ResultSet res) {
		try {
			if (res != null) {
				res.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
