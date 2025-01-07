package com.gn.supermarket.view;

import java.util.List;
import java.util.Scanner;

import com.gn.supermarket.controller.SmController;
import com.gn.supermarket.model.vo.SmBuy;
import com.gn.supermarket.model.vo.SmProduct;
import com.gn.supermarket.model.vo.SmUser;

public class SmMenu {
	private Scanner scan = new Scanner(System.in);
	private SmController sc = new SmController();
	
	public void mainMenu() {
		while(true) {
			System.out.println("*** 환영합니다. 우리동네 슈퍼마켓입니다 ***");
			System.out.println("원하는 메뉴를 선택해주세요 (종료:99)");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.print("메뉴 선택 : ");
			int menu = scan.nextInt();
			scan.nextLine();
			
			switch(menu) {
			case 99:
				System.out.print("프로그램 종료");
				scan.close();
				return;
			case 1:
				signUp();
				break;
			case 2:
				logIn();
				break;
			default:
				System.out.println("올바른 메뉴를 선택해주세요");
				continue;
			}
		}
	}
	
	public void signUp() {
		System.out.println("*** 회원가입 ***");
		System.out.print("아이디 : ");
		String id = scan.nextLine();
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		System.out.print("닉네임 : ");
		String nickname = scan.nextLine();
		
		SmUser su = sc.chkUser(id);
		
		if(su == null) {
			int result = sc.insertUser(id, pw, nickname);
			
			if(result > 0) {
				System.out.println("회원가입이 성공적으로 완료되었습니다");
			} else {
				System.out.println("회원가입 중 문제가 발생했습니다");
			}
		} else {
			System.out.println("이미 동일한 아이디가 존재합니다");
		}
	}
	
	public void logIn() {
		System.out.println("*** 로그인 ***");
		System.out.print("아이디 : ");
		String id = scan.nextLine();
		System.out.print("비밀번호 : ");
		String pw = scan.nextLine();
		
		SmUser su = sc.selectByIdAndPw(id, pw);
		
		if(su != null) {
			if("admin".equals(su.getUserId())) {
				// 관리자 메뉴로 이동
				adminMenu();
			} else {
				// 사용자 메뉴로 이동
				System.out.println("'" + su.getUserNickname() + "'님 환영합니다!");
				userMenu(id);
			}
		} else {
			System.out.println("아이디와 비밃번호를 다시 확인해주세요.");
			return;
		}
	}
	
	public void adminMenu() {
		while(true) {
			System.out.println("*** 관리자 메뉴 ***");
			System.out.println("수행할 작업을 선택하세요 (로그아웃: 99)");
			System.out.println("1. 제품 등록");
			System.out.println("2. 제품 입고");
			System.out.println("3. 판매 현황");
			System.out.print("메뉴 선택 : ");
			int menu = scan.nextInt();
			scan.nextLine();
			
			switch(menu) {
			case 99:
				System.out.println("로그아웃 되었습니다.");
				return;
			case 1:
				regProduct();
				break;
			case 2:
				instockProduct();
				break;
			case 3:
				showSalesAll();
				break;
			default:
				System.out.println("올바른 메뉴를 선택해주세요.");
				continue;
			}
		}
	}
	
	public void userMenu(String id) {
		while(true) {
			System.out.println("*** 회원 메뉴 ***");
			System.out.println("원하는 메뉴를 선택해주세요 (로그아웃 : 99)");
			System.out.println("1. 제품 구매");
			System.out.println("2. 개인 정보 수정");
			System.out.println("3. 탈퇴");
			System.out.print("메뉴 선택 : ");
			int menu = scan.nextInt();
			scan.nextLine();
			
			switch(menu) {
			case 99:
				System.out.println("로그아웃 되었습니다.");
				return;
			case 1:
				buyProduct(id);
				break;
			case 2:
				break;
			case 3:
				break;
			default:
				System.out.println("올바른 메뉴를 선택해주세요.");
				continue;
			}
		}
	}
	
	public void regProduct() {
		System.out.println("등록하실 제품 정보를 입력해주세요");
		System.out.print("제품명 : ");
		String prodName = scan.nextLine();
		System.out.print("제품 가격 : ");
		int prodPrice = scan.nextInt();
		scan.nextLine();
		System.out.print("입고 개수 : ");
		int prodInven = scan.nextInt();
		scan.nextLine();
		
		int result = sc.insertProduct(prodName, prodPrice, prodInven);
		
		if(result > 0) {
			System.out.println("정상적으로 제품이 추가되었습니다.");
		} else {
			System.out.println("제품 추가 도중 문제가 발생했습니다.");
		}
	}
	
	public void instockProduct() {
		System.out.println("입고 정보를 입력해주세요");
		System.out.print("제품 번호 : ");
		int prodNo = scan.nextInt();
		scan.nextLine();
		System.out.print("입고 개수 : ");
		int amount = scan.nextInt();
		scan.nextLine();
		
		int result = sc.updateProduct(prodNo, amount);
		
		if(result > 0) {
			System.out.println("입고가 정상적으로 이루어졌습니다.");
		} else {
			System.out.println("입고 처리 중 문제가 발생했습니다.");
		}
	}
	
	public void showSalesAll() {
		System.out.println("*** 판매 현황 ***");
		List<SmBuy> sb = sc.selectSalesAll();
		
		for(int i=0; i<sb.size(); i++) {
			System.out.println("구매 사용자 닉네임 : "+sb.get(i).getUserNickname()+", 제품명 : "+sb.get(i).getProdName()+", 판매 개수 : "+sb.get(i).getBuyAmount());
		}
	}
	
	public void buyProduct(String userId) {
		showProductAll();
		System.out.println("다음 중 구매를 원하는 제품번호와 구매를 원하는 개수를 입력해주세요.");
		
		System.out.print("구매할 제품번호 : ");
		int prodNo = scan.nextInt();
		scan.nextLine();
		System.out.print("구매할 개수 : ");
		int amount = scan.nextInt();
		scan.nextLine();
		
		int result = sc.buyProduct(userId, prodNo, amount);
		
		if(result > 0) {
			System.out.println("제품 구매가 완료되었습니다.");
		} else {
			System.out.println("제품 구매 도중 문제가 발생하였습니다.");
		}
	}
	
	public void showProductAll() {
		List<SmProduct> list = sc.selectProductAll();
		printProductAll(list);
	}
	
	public void printProductAll(List<SmProduct> list) {
		if(list.isEmpty()) {
			System.out.println("제품 목록이 존재하지 않습니다");
		} else {
			for(SmProduct sp : list) {
				System.out.println(sp);
			}
		}
	}
}
