package com.bestpricemarket.persistence;

import org.springframework.transaction.annotation.Transactional;

import com.bestpricemarket.domain.MemberVO;

public interface MemberDAO {
	
	// 회원가입
	public void joinMember(MemberVO vo);
	
	//회원 정보 조회 - 사용자 ID 해당하는 정보 가져오기
	public MemberVO readMember(String userid) throws Exception;
	
	// 회원 정보 조회 - ID,PW정보에 해당하는 사용자 정보
	public MemberVO readMemberWithIDPW(String id,String pw) throws Exception;

	// 회원가입 아이디 중복확인
	public MemberVO idCheck(String id);
	
	// 회원정보수정
	public void updateMember(MemberVO vo) throws Exception;
		
	// 회원탈퇴
	public void deleteMember(MemberVO vo) throws Exception;
	
	// 비밀번호 변경
	public int updatePw(MemberVO vo) throws Exception;
}

