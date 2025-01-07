package com.gn.wmmusic.view;

import java.util.List;
import java.util.Scanner;

import com.gn.wmmusic.controller.WmController;
import com.gn.wmmusic.model.vo.Song;
import com.gn.wmmusic.model.vo.User;

public class WmView {
	private Scanner sc = new Scanner(System.in);
	private WmController wc = new WmController();
	
	public void mainMenu() {
		while(true) {
			System.out.println("====== 초기메뉴 ======");
			System.out.println("원하시는 메뉴를 선택해주세요 (종료 : 99)");
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			
			System.out.print("메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();

			switch(menu) {
			case 99:
				System.out.print("프로그램이 종료되었습니다");
				sc.close();
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
		System.out.println("------ 회원가입 ------");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		User u = wc.chkUser(id);
		
		if(u != null) {
			System.out.println("동일한 아이디가 이미 존재합니다.");
		} else {
			int result = wc.signUp(id, pw, name);
			
			if(result > 0) {
				System.out.println("회원가입이 완료되었습니다.");
			} else {
				System.out.println("회원가입에 실패하였습니다.");
			}
		}
	}
	
	public void logIn() {
		System.out.println("------ 로그인 ------");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pw = sc.nextLine();
		
		User u = wc.logIn(id, pw);
		
		if(u != null) {
			System.out.println(u.getUserName() + " 님, 환영합니다!");
			
			if(u.getUserId().equals("admin")) {
				while(true) {	
					System.out.println("--- 관리자 메뉴 ---");
					System.out.println("원하시는 메뉴를 선택해주세요(로그아웃 : 99)");
					System.out.println("1. 음악 추가");
					System.out.println("2. 음악 인기 순위 조회");
					System.out.print("메뉴 선택 : ");
					int menu = sc.nextInt();
					sc.nextLine();
					
					switch(menu) {
					case 99:
						return;
					case 1:
						insertSong();
						break;
					case 2:
						showTopTen();
						break;
					default:
						System.out.println("올바른 메뉴를 선택해주세요");
						continue;
					}
				}
				
			} else {
				while(true) {
					System.out.println("--- 사용자 메뉴 ---");
					System.out.println("원하시는 메뉴를 선택해주세요(로그아웃 : 99)");
					System.out.println("1. 음악 재생");
					System.out.println("2. 개인 정보 수정");
					System.out.println("3. 회원 탈퇴");
					System.out.print("메뉴 선택 : ");
					
					int menu = sc.nextInt();
					sc.nextLine();
					
					switch(menu) {
					case 99:
						return;
					case 1:
						play(id);
						break;
					case 2:
						editUserName(id);
						break;
					case 3:
						if(deleteUser(id, pw) > 0) {
							return;
						} else {
							break;							
						}
					default:
						System.out.println("올바른 메뉴를 선택해주세요");
						continue;
					}
				}
			}
			
		} else {
			System.out.println("아이디와 비밀번호를 다시 확인해주세요.");
		}
	}
	
	public void insertSong() {
		System.out.println("--- 음악 추가 ---");
		System.out.print("음악 제목 : ");
		String title = sc.nextLine();
		System.out.print("아티스트명 : ");
		String author = sc.nextLine();
		
		int result = wc.insertSong(title, author);
		
		if(result > 0) {
			System.out.println("음악 추가 성공");
		} else {
			System.out.println("음악 추가 실패");
		}
	}
	
	public void showTopTen() {
		System.out.println("--- TOP 10 ---");
		List<Song> list = wc.showTopTen();
		for(Song s : list) {
			System.out.println(s);
		}
	}
	
	public void play(String id) {
		System.out.println("--- 전체 음악 목록 ---");
		List<Song> list = wc.showAll();
		for(Song s : list) {
			System.out.println(s);
		}
		
		System.out.print("재생할 음악의 번호를 입력해주세요 : ");
		int no = sc.nextInt();
		sc.nextLine();
		
		int result = wc.play(id, no);
		
		if(result > 0) {
			System.out.println("재생");
		} else {
			System.out.println("적절한 음악 번호를 입력해주세요.");
		}
	}
	
	public void editUserName(String id) {
		System.out.println("--- 사용자 이름 변경 ---");
		System.out.print("비밀번호 : ");
		String chkPw = sc.nextLine();
		
		User result = wc.chkPw(id, chkPw);
		
		if(result != null) {
			System.out.print("변경하실 사용자 이름 : ");
			String name = sc.nextLine();
			
			int cnt = wc.editUserName(id, chkPw, name);
			
			if(cnt > 0) {
				System.out.println("회원 정보 수정 완료");
			} else {
				System.out.println("회원 정보 수정 실패");
			}
			
		} else {
			System.out.println("비밀번호를 확인해주세요.");
		}
	}
	
	public int deleteUser(String id, String pw) {		
		System.out.println("--- 회원 탈퇴 ---");
		System.out.print("비밀번호 : ");
		String chkPw = sc.nextLine();
		
		int cnt = 0;
		
		User result = wc.chkPw(id, chkPw);
		if(result != null) {
			System.out.print("정말 탈퇴하시겠습니까?(y/n) : ");
			String input = sc.nextLine();
			
			if("y".equalsIgnoreCase(input)) {
				cnt = wc.deleteUser(id, pw);
				
				if(cnt > 0) {
					System.out.println("회원 탈퇴 완료");
				} else {
					System.out.println("회원 탈퇴 실패");
				}
			} else {
				System.out.println("회원 탈퇴 취소");
				return cnt;
			}
		} else {
			System.out.println("비밀번호를 확인해주세요.");
		}
		return cnt;
	}
}
