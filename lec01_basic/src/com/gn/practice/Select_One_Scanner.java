package com.gn.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Select_One_Scanner {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("조회하고자 하는 행의 번호를 입력하세요.");
		System.out.print("번호 : ");
		String input = sc.nextLine();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
			String id = "scott";
			String pwd = "tiger";
			
			conn = DriverManager.getConnection(url, id, pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT t_no ,t_name ,t_date"
					+ " FROM test"
					+ " WHERE t_no = " + input);
			
			Test t = new Test();
			if(rs.next()) {
				t.setTest_no(rs.getInt("t_no"));
				t.setTest_name(rs.getString("t_name"));
				t.setTest_date(rs.getTimestamp("t_date").toLocalDateTime());
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
				sc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
