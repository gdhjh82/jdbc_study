package com.gn.wmmusic.controller;

import java.util.List;

import com.gn.wmmusic.model.dao.WmDao;
import com.gn.wmmusic.model.vo.Song;
import com.gn.wmmusic.model.vo.User;

public class WmController {
	private WmDao wd = new WmDao();
	
	public User chkUser(String id) {
		return wd.chkUser(id);
	}
	
	public int signUp(String id, String pw, String name) {
		return wd.signUp(id, pw, name);
	}
	
	public User logIn(String id, String pw) {
		return wd.logIn(id, pw);
	}
	
	public int insertSong(String title, String author) {
		return wd.insertSong(title, author);
	}
	
	public List<Song> showTopTen() {
		return wd.showTopTen();
	}
	
	public List<Song> showAll() {
		return wd.showAll();
	}
	
	public int play(String id, int no) {
		return wd.play(id, no);
	}
	
	public User chkPw(String id, String chkPw) {
		return wd.chkPw(id, chkPw);
	}
	
	public int editUserName(String id, String chkPw, String name) {
		return wd.editUserName(id, chkPw, name);
	}
	
	public int deleteUser(String id, String pw) {
		return wd.deleteUser(id, pw);
	}
}
