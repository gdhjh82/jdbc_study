package com.gn.wmmusic.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gn.wmmusic.model.vo.Song;
import com.gn.wmmusic.model.vo.User;

public class WmDao {
	public User chkUser(String userId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		User u = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM wm_user WHERE user_id = '"+userId+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				u = new User();
				u.setUserNo(rs.getInt("user_no"));
				u.setUserId(rs.getString("user_id"));
				u.setUserPw(rs.getString("user_pw"));
				u.setUserName(rs.getString("user_name"));
				u.setUserReg(rs.getTimestamp("user_reg").toLocalDateTime());
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
		return u;
	}

	public int signUp(String memId, String memPw, String memName) {
		Connection conn = null;
		Statement stmt = null;
		
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String SQL = "INSERT INTO wm_user (user_id, user_pw ,user_name) VALUES ('"+memId+"' ,'"+memPw+"' ,'"+memName+"')";
			result = stmt.executeUpdate(SQL);		
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
		
		return result;
	}
	
	public User logIn(String memId, String memPw) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		User u = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM wm_user WHERE user_id = '"+memId+"' AND user_pw = '"+memPw+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				u = new User();
				u.setUserNo(rs.getInt("user_no"));
				u.setUserId(rs.getString("user_id"));
				u.setUserPw(rs.getString("user_pw"));
				u.setUserName(rs.getString("user_name"));
				u.setUserReg(rs.getTimestamp("user_reg").toLocalDateTime());
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
				
		return u;
	}
	
	public int insertSong(String title, String author) {
		Connection conn = null;
		Statement stmt = null;
		
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO wm_song (song_name ,song_artist) VALUES ('"+title+"', '"+author+"')";
			result = stmt.executeUpdate(sql);
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
		
		return result;
	}
	
	public List<Song> showTopTen() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Song> list = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM wm_song ORDER BY song_play DESC LIMIT 10";
			rs = stmt.executeQuery(sql);
			
			list = new ArrayList<>();
			while(rs.next()) {
				Song song = new Song();
				song.setSongNo(rs.getInt("song_no"));
				song.setSongName(rs.getString("song_name"));
				song.setSongArtist(rs.getString("song_artist"));
				song.setSongPlay(rs.getInt("song_play"));
				list.add(song);
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
		
		return list;
	}
	
	public List<Song> showAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Song> list = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT song_no ,song_name ,song_artist FROM wm_song";
			rs = stmt.executeQuery(sql);
			
			list = new ArrayList<>();
			while(rs.next()) {
				Song song = new Song();
				song.setSongNo(rs.getInt("song_no"));
				song.setSongName(rs.getString("song_name"));
				song.setSongArtist(rs.getString("song_artist"));
				list.add(song);
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
		return list;
	}
	
	public int play(String memId, int songNo) {
		Connection conn = null;
		Statement stmt = null;
		
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "UPDATE wm_song SET song_play = song_play + 1 WHERE song_no = '"+songNo+"'";
			result = stmt.executeUpdate(sql);
			String sql2 = "INSERT INTO wm_play (play_userno ,play_songno) VALUES ((SELECT user_no FROM wm_user WHERE user_id = '"+memId+"') ,"+songNo+")";
			stmt.executeUpdate(sql2);
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
		
		return result;
	}
	
	public User chkPw(String memId, String chkPw) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		User u = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM wm_user WHERE user_id = '"+memId+"' AND user_pw = '"+chkPw+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				u = new User();
				u.setUserNo(rs.getInt("user_no"));
				u.setUserId(rs.getString("user_id"));
				u.setUserPw(rs.getString("user_pw"));
				u.setUserName(rs.getString("user_name"));
				u.setUserReg(rs.getTimestamp("user_reg").toLocalDateTime());
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
		return u;
	}
	
	public int editUserName(String memId, String chkPw, String name) {
		Connection conn = null;
		Statement stmt = null;
		
		int result = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "UPDATE wm_user SET user_name = '"+name+"' WHERE user_id = '"+memId+"' AND user_pw = '"+chkPw+"'";
			result = stmt.executeUpdate(sql);
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
		
		return result;
	}
	
	public int deleteUser(String memId, String memPw) {
		Connection conn = null;
		Statement stmt = null;
		
		int result = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://127.0.0.1:3306/watermelon_music";
			String id = "scott";
			String pw = "tiger";
			conn = DriverManager.getConnection(url, id, pw);
			
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM wm_user WHERE user_id = '"+memId+"' AND user_pw = '"+memPw+"'";
			result = stmt.executeUpdate(sql);
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
		return result;
	}
}
