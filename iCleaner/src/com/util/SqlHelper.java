package com.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class SqlHelper {

	private static Connection conn = null;

	public static Connection getConn() {
		return conn;
	}

	public static java.sql.PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

	private static java.sql.PreparedStatement ps = null;
	private static ResultSet rs = null;

	static String url = "";
	static String user_name = "";
	static String password = "";
	static String driver = "";

	static Properties pp = null;
	static InputStream fis = null;

	static {

		try {

			pp = new Properties();
			fis = SqlHelper.class.getClassLoader().getResourceAsStream("com/util/dbinfo.properties");
			pp.load(fis);
			url = pp.getProperty("url");
			user_name = pp.getProperty("user_name");
			password = pp.getProperty("password");
			driver = pp.getProperty("driver");

			Class.forName(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {

		try {
			conn = DriverManager.getConnection(url, user_name, password);
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;
	}

	public static ArrayList executeQuery2(String selected_database, String sql,
			String[] parameters) {
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			if (parameters != null && !("".equals(parameters))) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}

			}
			ps.execute(selected_database);
			rs = ps.executeQuery();
			ArrayList al = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();
			while (rs.next()) {
				Object[] ob = new Object[column];
				for (int i = 1; i <= column; i++) {
					ob[i - 1] = rs.getObject(i);
				}
				al.add(ob);
			}
			return al;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			closeResource(rs, ps, conn);
		}

		return null;
	}

	public static ResultSet executeQuery(String selected_database, String sql,
			String[] parameters) {
		conn = getConnection();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.execute(selected_database);
			if (parameters != null && !("".equals(parameters))) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}

			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			// closeResource(rs, ps, conn);

		}
		return rs;

	}

	public static void executeUpdate2(String selected_database, String[] sql,
			String[][] parameters) {
		conn = getConnection();

		try {

			conn.setAutoCommit(false);

			for (int i = 0; i < sql.length; i++) {
				if (parameters[i] != null) {
					ps = conn.prepareStatement(sql[i]);
					ps.execute(selected_database);
					for (int j = 0; j < parameters[i].length; j++) {
						ps.setObject(j + 1, parameters[i][j]);
					}
					ps.executeUpdate();
				}
			}
			conn.commit();

		} catch (Exception e) {
			// TODO: handle exception
			try {
				conn.rollback();

			} catch (Exception e2) {
				// TODO: handle exception
				e.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			closeResource(rs, ps, conn);
		}

	}

	public static void executeUpdate(String selected_database, String sql,
			String[] parameters) {
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.execute(selected_database);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);

				}
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			closeResource(rs, ps, conn);
		}

	}

	public static void closeResource(ResultSet rs, java.sql.Statement ps,
			Connection conn) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}

	}

}
