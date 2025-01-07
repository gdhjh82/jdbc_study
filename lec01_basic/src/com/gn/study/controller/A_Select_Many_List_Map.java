package com.gn.study.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A_Select_Many_List_Map {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1. DriverManager
			Class.forName("org.mariadb.jdbc.Driver");
			// 2. Connection :: DB 연결 정보 : url, 계정명, 비밀번호
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/jdbc_basic", "scott", "tiger");
			// 3. Statement
			stmt = conn.createStatement();
			// 4. ResultSet 에 담기
			rs = stmt.executeQuery("SELECT t_no ,t_name ,t_date FROM test");
			// 5. 데이터 추출
			List<Map<String,Object>> list = new ArrayList<>();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("t_no", rs.getInt("t_no"));
				map.put("t_name", rs.getString("t_name"));
				map.put("t_date", rs.getTimestamp("t_date"));
				list.add(map);
			}
			if(list.isEmpty())
				System.out.println("조회된 결과가 없습니다.");
			else
				for(Map<String,Object> m : list) {
					System.out.println(m);
				}
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
