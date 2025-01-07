package com.gn.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class D_Insert {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pwd = "tiger";
			conn = DriverManager.getConnection(url, id, pwd);
			
			stmt = conn.createStatement();
			
//			String sql = "INSERT INTO test (t_name) VALUES ('테스트4')";
			String sql = "INSERT INTO test "
					+ "VALUES(5,'테스트5',now())";
			int result = stmt.executeUpdate(sql);
			
			if(result > 0)
				System.out.println("INSERT 성공");
			else
				System.out.println("INSERT 실패");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
