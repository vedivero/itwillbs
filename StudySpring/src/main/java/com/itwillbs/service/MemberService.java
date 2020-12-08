package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	// 서비스 계층
	
	// 회원 가입 ( 일반회원가입 / SNS 계정-간단회원가입 )
	public void insertMember(MemberVO vo);
	
	// 회원 로그인체크
	public MemberVO loginMember(MemberVO vo);
	
	// ID를 사용해서 회원정보 가져오는 동작
	public MemberVO readMember(String id);
	
	// 회원 정보수정
	public void updateMember(MemberVO vo);
	
	// 회원 정보삭제
	public void deleteMember(MemberVO vo);
	
	// 회원 목록
	public List<MemberVO> listMember();
	
	
	
	

}
