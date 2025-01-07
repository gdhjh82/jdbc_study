package com.gn.wmmusic.model.vo;

import java.time.LocalDateTime;

public class User {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private LocalDateTime userReg;
	
	public User() {
		super();
	}

	public User(int userNo, String userId, String userPw, String userName, LocalDateTime userReg) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userReg = userReg;
	}

	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDateTime getUserReg() {
		return userReg;
	}
	public void setUserReg(LocalDateTime userReg) {
		this.userReg = userReg;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userReg=" + userReg + "]";
	}
}
