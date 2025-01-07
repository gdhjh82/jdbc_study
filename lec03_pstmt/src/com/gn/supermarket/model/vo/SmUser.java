package com.gn.supermarket.model.vo;

import java.time.LocalDateTime;

public class SmUser {
	// field
	private int userNo;
	private String userId;
	private String userPw;
	private String userNickname;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	
	// constructor
	public SmUser() {
		super();
	}
	public SmUser(String userId, String userPw, String userNickname) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userNickname = userNickname;
	}
	public SmUser(int userNo, String userId, String userPw, String userNickname, LocalDateTime regDate,
			LocalDateTime modDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userNickname = userNickname;
		this.regDate = regDate;
		this.modDate = modDate;
	}
	
	// getter, setter
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
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public LocalDateTime getModDate() {
		return modDate;
	}
	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
	
	@Override
	public String toString() {
		return "SmUser [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userNickname="
				+ userNickname + ", regDate=" + regDate + ", modDate=" + modDate + "]";
	}
	
}
