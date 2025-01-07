package com.gn.wmmusic.model.vo;

public class Song {
	private int songNo;
	private String songName;
	private String songArtist;
	private int songPlay;
	
	public Song() {
		super();
	}

	public Song(int songNo, String songName, String songArtist, int songPlay) {
		super();
		this.songNo = songNo;
		this.songName = songName;
		this.songArtist = songArtist;
		this.songPlay = songPlay;
	}

	public int getSongNo() {
		return songNo;
	}
	public void setSongNo(int songNo) {
		this.songNo = songNo;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getSongArtist() {
		return songArtist;
	}
	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}
	public int getSongPlay() {
		return songPlay;
	}
	public void setSongPlay(int songPlay) {
		this.songPlay = songPlay;
	}

	@Override
	public String toString() {
		return songNo + ". " + songArtist + " - " + songName;
	}
}
