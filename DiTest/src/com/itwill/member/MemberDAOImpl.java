package com.itwill.member;

import java.util.List;

public class MemberDAOImpl implements MemberDAO {

	// 디비연결 
	 
	@Override
	public List getMemberList() {
		System.out.println("DAO : DB 연결");
		System.out.println("DAO : SQL 작성 & pstmt 객체 생성");
		System.out.println("DAO : ? 처리 & SQL 실행");
		System.out.println("DAO : RS에 가져온 정보를 List에 저장");
		// List arr = new ArrayList();
		return null;
	}

}
