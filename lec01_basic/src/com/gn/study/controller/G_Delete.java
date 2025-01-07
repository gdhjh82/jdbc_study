package com.gn.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class G_Delete {
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
			
			String sql = "DELETE FROM test WHERE t_no = 2";
			int result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				System.out.println("삭제 성공 : 정상적으로 삭제되었습니다.");
			} else {
				System.out.println("삭제 실패 : 삭제 중 오류가 발생하였습니다.");
			}
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
