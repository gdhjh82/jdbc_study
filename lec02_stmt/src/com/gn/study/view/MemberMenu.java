package com.gn.study.view;

import java.util.List;
import java.util.Scanner;

import com.gn.study.controller.MemberController;
import com.gn.study.model.vo.Member;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	public void mainMenu() {
		while(true) {
			System.out.println("=== 회원 관리 프로그램 ===");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 회원 이름으로 키워드 검색");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 0:
				System.out.println("잘가요~안녕~");
				sc.close();
				return;
			case 1:
				createMember();
				break;
			case 2:
				selectMemberAll();
				break;
			case 3:
				searchMemberOneById();
				break;
			case 4:
				searchMemberByKeyword();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
				break;
			default:
				System.out.println("올바른 메뉴를 선택해주세요");
				continue;
			}
		}
	}
	
	// 회원 정보 추가
	public void createMember() {
		System.out.println("=== 회원 정보 추가 ===");
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String memberPw = sc.nextLine();
		System.out.print("이름 : ");
		String memberName = sc.nextLine();
		System.out.print("이메일 : ");
		String memberEmail = sc.nextLine();
		System.out.print("전화번호(-빼고 입력) : ");
		String memberPhone = sc.nextLine();
		System.out.print("성별 : ");
		String memberGender = sc.nextLine();
		
		int result = mc.insertMember(memberId, memberPw, memberName, memberEmail, memberPhone, memberGender);
		if(result > 0) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
	// 전체 회원 조회
	public void selectMemberAll() {
		System.out.println("=== 회원 전체 조회 ===");
		List<Member> list = mc.selectMemberAll();
		// 만약 list가 비어있다면?
		if(list.isEmpty()) System.out.println("조회된 결과가 없습니다.");
		else {
			for(Member l : list) {
				System.out.println(l);
			}
		}
	}
	
	public void searchMemberOneById() {
		System.out.println("=== 회원 아이디 검색 ===");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		
		Member m = mc.searchMemberOneById(id);
		if(m != null) {
			System.out.println(m);
		} else {
			System.out.println(id + "은(는) 존재하지 않는 정보입니다.");
		}
	}
	
	public void searchMemberByKeyword() {
		System.out.println("=== 이름 키워드 검색 ===");
		System.out.print("키워드 : ");
		String keyword = sc.nextLine();
		
		List<Member> list = mc.searchMemberByKeyword(keyword);
		
		if(list.isEmpty()) {
			System.out.println("해당하는 결과가 없습니다.");
		} else {
			for(Member l : list) {
				System.out.println(l);
			}
		}
	}
	
	public void updateMember() {
		// 관리자 -> 모든 회원의 정보 수정
		// 일반사용자 -> 본인의 정보만 수정
		// 사용자라고 가정하고 작업
		System.out.println("=== 회원 정보 수정 ===");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		
		Member m = mc.selectMemberByIdAndPw(id, pw);
		
		if(m != null) {
			// 이름, 전화번호, 이메일 -> 수정
			System.out.println("--- 변경사항 ---");
			System.out.print("이름 : ");
			String name = sc.nextLine();
			System.out.print("전화번호 : ");
			String phone = sc.nextLine();
			System.out.print("이메일 : ");
			String email = sc.nextLine();
			
			int result = mc.updateMemberInfo(id, pw, name, phone, email);
			if(result > 0) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
		} else {
			System.out.println("잘못된 아이디 혹은 비밀번호입니다.");
		}
	}
	public void deleteMember() {
		System.out.println("=== 회원탈퇴 ===");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		
		Member m = mc.selectMemberByIdAndPw(id, pw);
		
		if(m != null) {
			int result = mc.deleteMember(id, pw);
			
			if(result > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
		
		} else {
			System.out.println("해당 사용자가 존재하지 않습니다.");
		}
	}
}
