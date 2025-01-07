package com.gn.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.gn.study.model.vo.Test;

public class Insert_Scanner {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/jdbc_basic";
		    String id = "scott";
		    String pwd = "tiger";
		    conn = DriverManager.getConnection(url, id, pwd);
		    
		    conn.setAutoCommit(false);
		    
		    stmt = conn.createStatement();
		    
		    String sql1 = "SELECT COUNT(*) FROM test WHERE t_name = '" + name + "'";
		    rs = stmt.executeQuery(sql1);
		    
		    int cnt = 0;
		    if(rs.next()) {
		    	cnt = rs.getInt(1);
		    }
		    
		    if(cnt == 0) {
		    	String sql2 = "INSERT INTO test (t_name) VALUES ('" + name + "')";
		    	int result = stmt.executeUpdate(sql2);
		    	
		    	if(result > 0)
		    		System.out.println("성공");		    		
		    	else
		    		System.out.println("실패");
		    } else {
		    	System.out.println("이미 존재하는 이름입니다.");
		    }
		    
		    String sql3 = "SELECT t_no ,t_name ,t_date FROM test WHERE t_name = '"+name+"'";
		    rs = stmt.executeQuery(sql3);

		    Test t = new Test();
		    if(rs.next()) {
		    	t.setTestNo(rs.getInt("t_no"));
		    	t.setTestName(rs.getString("t_name"));
	            t.setTestDate(rs.getTimestamp("t_date").toLocalDateTime());
		    }
		    
		    System.out.println("===== test =====");
		    System.out.println(t);
		    
		    conn.commit();
		    conn.setAutoCommit(true);
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
				sc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
