package com.gn.study.view;

import java.util.List;
import java.util.Scanner;

import com.gn.study.controller.ProjectController;
import com.gn.study.model.vo.ProjectVo;

public class ProjectMenu {
	private Scanner sc = new Scanner(System.in);	// 입력
	private ProjectController pc = new ProjectController();	// view <- controller
	
	public void mainMenu() {
		while(true) {
			System.out.println("=== (주)ABC 프로젝트 관리 시스템 ===");
			System.out.println("1. 프로젝트 추가");
			System.out.println("2. 프로젝트 전체 조회");
			System.out.println("3. 프로젝트 이름 검색");
			System.out.println("4. 프로젝트 담당자 검색");
			System.out.println("5. 프로젝트 정보 수정");
			System.out.println("6. 프로젝트 삭제");
			System.out.println("0. 종료");
			
			System.out.print("메뉴 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			switch(menu) {
				case 1:
					createProject();
					break;
				case 2:
					showProjectAll();
					break;
				case 3:
					searchByProjectName();
					break;
				case 4:
					searchByManagerName();
					break;
				case 5:
					editProjectOne();
					break;
				case 6:
					deleteProjectOne();
					break;
				case 0:
					System.out.println("이용해주셔서 감사합니다.");
					sc.close();
					return;
				default:
					System.out.println("메뉴를 잘못 입력하셨습니다.");
			}
		}
	}
	
	// 프로젝트를 새롭게 추가하는 로직
	public void createProject() {	// 프로젝트를 추가하는 메소드
		System.out.println("*** 프로젝트 추가 ***");
		System.out.println("'프로젝트명'과 '담당자이름'을 입력하세요. ");
		System.out.print("프로젝트명 : ");
		String projectName = sc.nextLine();
		System.out.print("담당자이름 : ");
		String managerName = sc.nextLine();
		
		int result = pc.insertProject(projectName, managerName);
		
		printProjectResult(result, "프로젝트 추가");
	}
	
	// 존재하는 모든 프로젝트를 조회하는 로직
	public void showProjectAll() {
		System.out.println("*** 프로젝트 전체 조회 ***");
		List<ProjectVo> list = pc.selectProjectAll();
		
		printListProject(list);
	}
	
	// 프로젝트명을 입력받아서 해당 문자열이 포함된 프로젝트를 조회하는 로직
	public void searchByProjectName() {
		System.out.println("*** 프로젝트명 기준 검색 ***");
		System.out.println("프로젝트명을 일부 입력하시면 관련된 프로젝트 정보를 조회해드립니다.");
		System.out.print("프로젝트명 : ");
		String projectName = sc.nextLine();
		
		List<ProjectVo> list = pc.selectProjectAllByName(projectName);
		
		printListProject(list);
	}
	
	// 담당자 이름을 입력받아서 해당 담당자가 담당하고 있는 프로젝트 조회
	public void searchByManagerName() {
		System.out.println("*** 담당자 이름 기준 검색 ***");
		System.out.println("담당자 이름을 입력하시면 담당하고 있는 프로젝트들의 정보를 조회해드립니다");
		System.out.print("담당자 이름 : ");
		String managerName = sc.nextLine();
		
		List<ProjectVo> list = pc.selectProjectAllByManagerName(managerName);
		
		System.out.println("※ " + managerName + "님이 담당하는 프로젝트 ※");
		printListProject(list);
	}
	
	// 프로젝트 번호와 새로운 프로젝트명을 입력하면 해당 프로젝트명이 수정되는 로직
	public void editProjectOne() {
		System.out.println("*** 프로젝트 정보 수정***");
		List<ProjectVo> list = pc.selectProjectAll();
		printListProject(list);
		
		System.out.println("수정하고자 하는 프로젝트 번호와 새로운 프로젝트명을 수정해드립니다.");
		System.out.print("프로젝트 번호 : ");
		int projectNo = sc.nextInt();
		sc.nextLine();
		System.out.print("새로운 프로젝트명 : ");
		String projectName = sc.nextLine();
		
		int result = pc.updateProjectOne(projectNo, projectName);
		
		printProjectResult(result, "프로젝트 정보 수정");
	}
	
	// 프로젝트 번호를 입력받아서 해당 프로젝트를 삭제하는 로직
	public void deleteProjectOne() {
		System.out.println("*** 프로젝트 삭제 ***");
		List<ProjectVo> list = pc.selectProjectAll();
		printListProject(list);
		
		System.out.println("삭제하고자하는 프로젝트 번호를 입력해주세요.");
		System.out.print("삭제할 프로젝트 번호 : ");
		int projectNo = sc.nextInt();
		sc.nextLine();
		
		int result = pc.deleteProjectOne(projectNo);
		
		printProjectResult(result, "프로젝트 삭제");
	}
	
	// 프로젝트 리스트를 받아서 표준출력 해주는 로직
	public void printListProject(List<ProjectVo> list) {
		if(list.isEmpty()) {
			System.out.println("조회된 프로젝트가 없습니다.");
		} else {
			for(ProjectVo vo : list) {
				System.out.println(vo);
			}
		}
	}
	
	// INSERT, UPDATE, DELETE의 결과를 표준출력 해주는 로직
	public void printProjectResult(int result, String menuName) {
		if(result > 0) {
			System.out.println(menuName + "이(가) 정상적으로 완료되었습니다.");
		} else {
			System.out.println(menuName + " 중 오류가 발생하였습니다.");
		}
	}
}
