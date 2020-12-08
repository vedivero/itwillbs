package com.itwill.member;

import java.util.List;

public interface MemberDAO {
    // DAO 역활 객체 
	
	// 동작-> 추상클래스로 미리 선언
	// 회원 목록 가져오기
	public List getMemberList();
}
