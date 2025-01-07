package com.gn.practice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
	private int test_no;
	private String test_name;
	private LocalDateTime test_date;
	
	
	public Test() {
		super();
	}
	public Test(int test_no, String test_name, LocalDateTime test_date) {
		super();
		this.test_no = test_no;
		this.test_name = test_name;
		this.test_date = test_date;
	}
	
	public int getTest_no() {
		return test_no;
	}
	public void setTest_no(int test_no) {
		this.test_no = test_no;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public LocalDateTime getTest_date() {
		return test_date;
	}
	public void setTest_date(LocalDateTime test_date) {
		this.test_date = test_date;
	}
	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY년MM월dd일 HH시mm분ss초");
		return "번호:" + test_no + ", 이름:" + test_name + ", 등록일:" + test_date.format(dtf);
	}
}
