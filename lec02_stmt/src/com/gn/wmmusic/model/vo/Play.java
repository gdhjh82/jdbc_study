package com.gn.wmmusic.model.vo;

public class Play {
	private int playNo;
	private int userNo;
	private int songNo;
	
	public Play() {
		super();
	}	
	public Play(int playNo, int userNo, int songNo) {
		super();
		this.playNo = playNo;
		this.userNo = userNo;
		this.songNo = songNo;
	}

	public int getPlayNo() {
		return playNo;
	}
	public void setPlayNo(int playNo) {
		this.playNo = playNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getSongNo() {
		return songNo;
	}
	public void setSongNo(int songNo) {
		this.songNo = songNo;
	}
	
	@Override
	public String toString() {
		return "Play [playNo=" + playNo + ", userNo=" + userNo + ", songNo=" + songNo + "]";
	}
}
