package com.gn.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class E_Insert_Transaction {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pwd = "tiger";
			conn = DriverManager.getConnection(url, id, pwd);
			// AutoCommit 해제
			conn.setAutoCommit(false);
			// Statement 생성
			stmt = conn.createStatement();
			// SQL문 실행 : 데이터 존재 여부 -> 개수
			String str = "테스트6";
			String sql1 = "SELECT COUNT(*) FROM test WHERE t_name = '" + str + "'";
			rs = stmt.executeQuery(sql1);
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt(1); // 1은 인덱스임 - 조건에 맞는 열이 나타나면 1을 더해주는 차원
			}
			
			if(cnt == 0) {
				String sql2 = "INSERT INTO test (t_name) VALUES ('" + str + "')";
				int result = stmt.executeUpdate(sql2);
				
				if(result > 0)
					System.out.println("성공");
				else
					System.out.println("실패");
			} else {
				System.out.println("이미 존재하는 이름 입니다.");
			}
			
			conn.commit();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
