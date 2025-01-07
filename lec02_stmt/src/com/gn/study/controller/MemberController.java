package com.gn.study.controller;

import java.util.List;

import com.gn.study.model.dao.MemberDao;
import com.gn.study.model.vo.Member;

public class MemberController {
	private MemberDao md = new MemberDao();
	
	public int insertMember(String memberId, String memberPw, String memberName, String memberEmail, String memberPhone, String memberGender) {
		Member m = new Member(memberId, memberPw, memberName, memberEmail, memberPhone, memberGender);
		int result = md.insertMember(m);
		return result;
	}
	public List<Member> selectMemberAll() {
		List<Member> list = md.selectMemberAll();
		
		return list;
	}
	
	public Member searchMemberOneById(String memberId) {
		Member m = md.searchMemberOneById(memberId);
		
		return m;
	}
	
	public List<Member> searchMemberByKeyword(String keyword) {
		return md.searchMemberByKeyword(keyword);
	}
	
	public Member selectMemberByIdAndPw(String id, String pw) {
		return md.selectMemberByIdAndPw(id, pw);
	}
	
	public int updateMemberInfo(String id, String pw, String name, String phone, String email) {
		return md.updateMemberInfo(id, pw, name, phone, email);
	}
	
	public int deleteMember(String id, String pw) {
		return md.deleteMember(id, pw);
	}
}
