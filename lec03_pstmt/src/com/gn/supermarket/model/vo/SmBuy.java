package com.gn.supermarket.model.vo;

public class SmBuy {
	// field
	private int buyNo;
	private int userNo;
	private String userNickname;
	private int prodNo;
	private String prodName;
	private int buyAmount;
	
	// constructor
	public SmBuy() {
		super();
	}
	public SmBuy(int buyNo, int userNo, int prodNo, int buyAmount) {
		super();
		this.buyNo = buyNo;
		this.userNo = userNo;
		this.prodNo = prodNo;
		this.buyAmount = buyAmount;
	}
	
	// getter, setter
	public int getBuyNo() {
		return buyNo;
	}
	public void setBuyNo(int buyNo) {
		this.buyNo = buyNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public int getBuyAmount() {
		return buyAmount;
	}
	public void setBuyAmount(int buyAmount) {
		this.buyAmount = buyAmount;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	@Override
	public String toString() {
		return "SmBuy [buyNo=" + buyNo + ", userNo=" + userNo + ", prodNo=" + prodNo + ", buyAmount=" + buyAmount + "]";
	}
}
