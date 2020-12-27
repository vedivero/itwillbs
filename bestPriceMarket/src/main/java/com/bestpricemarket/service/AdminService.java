package com.bestpricemarket.service;

import java.util.List;

import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MemberVO;

public interface AdminService {

	//등록된 모든 회원 보기
	public int allMembersCount()throws Exception;
	
	//회원 목록
	public List<MemberVO> getMemberList(int displayPost,int postNum)throws Exception;
	
	//특정 회원 상세보기
	public MemberVO detail(String id)throws Exception;

	//제재 하기
	public void changeRestriction(MemberVO vo)throws Exception;
	
	//제한 해제
	public void liftingRestriction(MemberVO vo)throws Exception;
	
	//제재 사유
	public void restrinctionReason(MemberVO vo)throws Exception;
	
	//등록된 모든 상품
	public int allGoodsCount()throws Exception;
	
	//등록된 상품 목록 불러오기
	public List<GoodsVO> getGoodsList(int displayPost,int postNum)throws Exception;
	
	//특정 상품 상세보기
	public GoodsVO goodsDetail(int gno)throws Exception;
	
	//관리자-상품 삭제
	public void delete(int gno)throws Exception;
	
	//일반 회원 수(제재받지 않은 회원)
	public int generalMemberCount()throws Exception;
	
	//일반회원 리스트
	public List<MemberVO> getGeneralMemberList(int displayPost,int postNum)throws Exception;
	
	//제재중인 회원 수
	public int restrictionMemberCount()throws Exception;
	
	//제재중인 회원 리스트
	public List<MemberVO> getRestrictionMemberList(int displayPost,int postNum)throws Exception;
	
	//진행중인 경매 숫자
	public int underwayAuctionCount()throws Exception;
	
	public List<GoodsVO> getUnderwayAuctionList(int displayPost,int postNum)throws Exception;
	
	public int closedAuctionCount()throws Exception;
	
	public List<GoodsVO> getClosedAuctionList(int displayPost,int postNum)throws Exception;
	
}
