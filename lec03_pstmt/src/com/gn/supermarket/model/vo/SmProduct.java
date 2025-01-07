package com.gn.supermarket.model.vo;

public class SmProduct {
	// field
	private int prodNo;
	private String prodName;
	private int prodPrice;
	private int prodInven;
	
	// constructor
	public SmProduct() {
		super();
	}
	public SmProduct(String prodName, int prodPrice, int prodInven) {
		super();
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodInven = prodInven;
	}
	public SmProduct(int prodNo, String prodName, int prodPrice, int prodInven) {
		super();
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodInven = prodInven;
	}
	
	// getter, setter
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getProdInven() {
		return prodInven;
	}
	public void setProdInven(int prodInven) {
		this.prodInven = prodInven;
	}
	
	@Override
	public String toString() {
		return "[제품번호=" + prodNo + ", 제품명=" + prodName + ", 가격=" + prodPrice + ", 구매가능 개수="
				+ prodInven + "]";
	}
}
