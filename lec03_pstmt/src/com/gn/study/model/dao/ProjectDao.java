package com.gn.study.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gn.study.model.vo.ProjectVo;

public class ProjectDao {
	public int insertProject(String projectName, String managerName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "INSERT INTO project (project_name ,project_manager)"
					+ " VALUES (? ,(SELECT emp_id"
					+ " FROM employee"
					+ " WHERE emp_name = ?))";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, projectName);
			pstmt.setString(2, managerName);
			
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
	
	public List<ProjectVo> selectProjectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ProjectVo> list = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "SELECT *"
					+ " FROM project p"
					+ " LEFT JOIN employee e ON p.project_manager = e.emp_id";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()) {
				ProjectVo vo = new ProjectVo();
				vo.setProjectId(rs.getInt("p.project_id"));
				vo.setProjectName(rs.getString("p.project_name"));
				vo.setProjectManager(rs.getInt("p.project_manager"));
				vo.setManagerName(rs.getString("e.emp_name"));
				vo.setReg_date(rs.getTimestamp("p.reg_date").toLocalDateTime());
				vo.setMod_date(rs.getTimestamp("p.mod_date").toLocalDateTime());
				list.add(vo);
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
		
		return list;
	}
	
	public List<ProjectVo> selectProjectAllByName(String projectName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ProjectVo> list = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "SELECT * FROM project WHERE project_name LIKE CONCAT('%', ?, '%')";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, projectName);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()) {
				ProjectVo vo = new ProjectVo();
				vo.setProjectId(rs.getInt("project_id"));
				vo.setProjectName(rs.getString("project_name"));
				vo.setProjectManager(rs.getInt("project_manager"));
				vo.setReg_date(rs.getTimestamp("reg_date").toLocalDateTime());
				vo.setMod_date(rs.getTimestamp("mod_date").toLocalDateTime());
				list.add(vo);
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
		
		return list;
	}
	
	public List<ProjectVo> selectProjectAllByManagerName(String managerName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<ProjectVo> list = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "SELECT *"
					+ " FROM project p"
					+ " LEFT JOIN employee e ON p.project_manager = e.emp_id"
					+ " WHERE e.emp_name = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, managerName);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()) {
				ProjectVo vo = new ProjectVo();
				vo.setProjectId(rs.getInt("p.project_id"));
				vo.setProjectName(rs.getString("p.project_name"));
				vo.setProjectManager(rs.getInt("p.project_manager"));
				vo.setManagerName(rs.getString("e.emp_name"));
				vo.setReg_date(rs.getTimestamp("p.reg_date").toLocalDateTime());
				vo.setMod_date(rs.getTimestamp("p.mod_date").toLocalDateTime());
				list.add(vo);
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
		
		return list;
	}
	
	public int updateProjectOne(int projectNo, String projectName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "UPDATE project SET project_name = ? WHERE project_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, projectName);
			pstmt.setInt(2, projectNo);
			
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
	
	public int deleteProjectOne(int projectNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/company_project";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "DELETE FROM project WHERE project_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, projectNo);
			
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
}
