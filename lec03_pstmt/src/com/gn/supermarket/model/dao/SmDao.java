package com.gn.supermarket.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gn.supermarket.model.vo.SmBuy;
import com.gn.supermarket.model.vo.SmProduct;
import com.gn.supermarket.model.vo.SmUser;

public class SmDao {
	// 동일한 아이디가 있는지 체크하는 로직
	public SmUser chkUser(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		SmUser su = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "SELECT * FROM sm_user WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				su = new SmUser();
				su.setUserNo(rs.getInt("user_no"));
				su.setUserId(rs.getString("user_id"));
				su.setUserPw(rs.getString("user_pw"));
				su.setUserNickname(rs.getString("user_nickname"));
				su.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				su.setModDate(rs.getTimestamp("mod_date").toLocalDateTime());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return su;
	}
	
	// 회원가입 로직 - sm_uwer테이블에 레코드를 추가
	public int insertUser(String userId, String userPw, String userNickname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "INSERT INTO sm_user (user_id ,user_pw ,user_nickname) VALUES (? ,? ,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			pstmt.setString(3, userNickname);
			
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 아이디와 비밀번호로 조회해서 객체로 담아 반환 = 로그인하는 로직
	public SmUser selectByIdAndPw(String userId, String userPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		SmUser su = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "SELECT * FROM sm_user WHERE user_id = ? AND user_pw = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				su = new SmUser();
				su.setUserNo(rs.getInt("user_no"));
				su.setUserId(rs.getString("user_id"));
				su.setUserPw(rs.getString("user_pw"));
				su.setUserNickname(rs.getString("user_nickname"));
				su.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				su.setModDate(rs.getTimestamp("mod_date").toLocalDateTime());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return su;
	}
	
	// sm_product 테이블에 제품을 추가하는 로직
	public int insertProduct(String prodName, int prodPrice, int prodInven) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "INSERT INTO sm_product (prod_name ,prod_price ,prod_inven) VALUES (? ,? ,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, prodName);
			pstmt.setInt(2, prodPrice);
			pstmt.setInt(3, prodInven);
			
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// sm_product 테이블에 제품번호가 일치하는 행의 입고 개수를 수정하는 로직
	public int updateProduct(int prodNo, int amount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "UPDATE sm_product SET prod_inven = prod_inven + ? WHERE prod_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, amount);
			pstmt.setInt(2, prodNo);
			
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// sm_buy 테이블와 다른 테이블을 조인해서 필요한 모든 데이터를 리스트에 담아 반환하는 로직
	public List<SmBuy> selectSalesAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SmBuy> list = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "SELECT u.user_nickname ,p.prod_name ,b.buy_amount"
					+ " FROM sm_buy b"
					+ " JOIN sm_user u ON b.buy_userno = u.user_no"
					+ " JOIN sm_product p ON b.buy_prodno = p.prod_no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()) {
				SmBuy sb = new SmBuy();
				sb.setUserNickname(rs.getString("u.user_nickname"));
				sb.setProdName(rs.getString("p.prod_name"));
				sb.setBuyAmount(rs.getInt("b.buy_mount"));
				list.add(sb);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public List<SmProduct> selectProductAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SmProduct> list = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "SELECT * FROM sm_product";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()) {
				SmProduct sp = new SmProduct();
				sp.setProdNo(rs.getInt("prod_no"));
				sp.setProdName(rs.getString("prod_name"));
				sp.setProdPrice(rs.getInt("prod_price"));
				sp.setProdInven(rs.getInt("prod_inven"));
				list.add(sp);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int buyProduct(String userId, int prodNo, int amount) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/super_market";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			conn.setAutoCommit(false);
			
			String sql1 = "INSERT sm_buy (buy_userno ,buy_prodno ,buy_amount)"
					+ " VALUES ((SELECT user_no"
					+ " FROM sm_user"
					+ " WHERE user_id = ?), ?, ?)";
			pstmt1 = conn.prepareStatement(sql1);
			
			pstmt1.setString(1, userId);
			pstmt1.setInt(2, prodNo);
			pstmt1.setInt(3, amount);
			
			result = pstmt1.executeUpdate();
			
			String sql2 = "UPDATE sm_product"
					+ " SET prod_inven = prod_inven - ?"
					+ " WHERE prod_no = ?";
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setInt(1, amount);
			pstmt2.setInt(2, prodNo);
			
			result = pstmt2.executeUpdate();
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("재고가 부족합니다.");
			}
		} finally {
			try {
				pstmt2.close();
				pstmt1.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
