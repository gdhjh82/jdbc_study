package com.gn.supermarket.controller;

import java.util.List;

import com.gn.supermarket.model.dao.SmDao;
import com.gn.supermarket.model.vo.SmBuy;
import com.gn.supermarket.model.vo.SmProduct;
import com.gn.supermarket.model.vo.SmUser;

public class SmController {
	private SmDao sd = new SmDao();
	
	// 동일한 아이디가 있는지 체크하는 로직
	public SmUser chkUser(String id) {
		return sd.chkUser(id);
	}
	
	// 회원가입 로직 - sm_uwer테이블에 레코드를 추가
	public int insertUser(String id, String pw, String nickname) {
		return sd.insertUser(id, pw, nickname);
	}
	
	// 아이디와 비밀번호가 맞다면 로그인하는 로직
	public SmUser selectByIdAndPw(String id, String pw) {
		return sd.selectByIdAndPw(id, pw);
	}
	
	// sm_product 테이블에 제품을 추가하는 로직
	public int insertProduct(String prodName, int prodPrice, int prodInven) {
		return sd.insertProduct(prodName, prodPrice, prodInven);
	}
	
	// sm_product 테이블에 제품번호가 일치하는 행의 입고 개수를 수정하는 로직
	public int updateProduct(int prodNo, int amount) {
		return sd.updateProduct(prodNo, amount);
	}
	
	// sm_buy 테이블와 다른 테이블을 조인해서 필요한 모든 데이터를 리스트에 담아 반환하는 로직
	public List<SmBuy> selectSalesAll() {
		return sd.selectSalesAll();
	}
	
	// sm_product 테이블의 모든 정보를 조회하는 로직
	public List<SmProduct> selectProductAll() {
		return sd.selectProductAll();
	}
	
	// prodNo, amount를 매개로 sm_buy와 sm_product를 수정하는 로직
	public int buyProduct(String userId, int prodNo, int amount) {
		return sd.buyProduct(userId, prodNo, amount);
	}
	
}
