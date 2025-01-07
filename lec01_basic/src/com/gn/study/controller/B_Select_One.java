package com.gn.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gn.study.model.vo.Test;

public class B_Select_One {
	public static void main(String[] args) {
		// 1. Map 추출
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String ur1 = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pwd = "tiger";
			
			conn = DriverManager.getConnection(ur1, id, pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT t_no ,t_name ,t_date"
					+ " FROM test"
					+ " WHERE t_no = 1");
			
//			Map<String, Object> map = new HashMap<>();
//			if(rs.next()) {
//				map.put("번호", rs.getInt("t_no"));
//				map.put("이름", rs.getString("t_name"));
//				map.put("날짜", rs.getTimestamp("t_date"));
//			}
//			System.out.println(map);
			
			// 2. VO 추출
			Test t = new Test();
			if(rs.next()) {
				t.setTestNo(rs.getInt("t_no"));
				t.setTestName(rs.getString("t_name"));
				t.setTestDate(rs.getTimestamp("t_date").toLocalDateTime());
			}
			System.out.println(t);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
