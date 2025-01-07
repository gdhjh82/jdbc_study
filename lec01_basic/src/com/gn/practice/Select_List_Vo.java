package com.gn.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Select_List_Vo {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/jdbc_basic", "scott", "tiger");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT t_no ,t_name ,t_date FROM test");
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
			
			List<Map<String, Object>> list = new ArrayList<>();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("번호", rs.getInt("t_no"));
				map.put("이름", rs.getString("t_name"));
				map.put("등록일", rs.getTimestamp("t_date").toLocalDateTime().format(dtf));
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
