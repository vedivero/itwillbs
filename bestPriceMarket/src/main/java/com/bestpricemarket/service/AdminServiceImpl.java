package com.bestpricemarket.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService{

	@Inject
	private AdminDAO adao;
	
	
	
	@Override
	public int allMembersCount() throws Exception {

		int allMembersCount = adao.allMembersCount();
		System.out.println("admin service: 모든 맴버 숫자 계산완료");
		return allMembersCount;
	}
	
	@Override
	public List<MemberVO> getMemberList(int displayPost,int postNum) throws Exception {

		List<MemberVO> MemberList = adao.getMemberList(displayPost, postNum);
		System.out.println("AdminServiceImpl에서 : getmemberList()호출");

		return MemberList;
	}

	//특정 회원 정보 상세보기
	@Override
	public MemberVO detail(String id) throws Exception {
		
		MemberVO vo = adao.detail(id);
		return vo;
	}

	//제재 하기
	@Override
	public void changeRestriction(MemberVO vo) throws Exception {
		
		adao.changeRestriction(vo);
	}

	//제한 해제 하기
	@Override
	public void liftingRestriction(MemberVO vo) throws Exception {

		adao.liftingRestriction(vo);
		
	}

	//제재 사유 입력
	@Override
	public void restrinctionReason(MemberVO vo) throws Exception {
		adao.restrinctionReason(vo);
	}

	
	//등록된 모든 상품
	@Override
	public int allGoodsCount() throws Exception {

		int allGoodsCount = adao.allGoodsCount();
		return allGoodsCount;
	}

	//등록된 상품 목록 불러오기
	public List<GoodsVO> getGoodsList(int displayPost,int postNum) throws Exception {

		List<GoodsVO> goodsList = adao.getGoodsList(displayPost, postNum);
		System.out.println("AdminServiceImpl에서 : getGoodsList()호출");

		return goodsList;
	}

	//특정 상품 상세보기
	@Override
	public GoodsVO goodsDetail(int gno) throws Exception {

		GoodsVO vo = adao.goodsDetail(gno);
		
		return vo;
	}

	@Override
	public void delete(int gno) throws Exception {

		adao.delete(gno);
	}

	//일반회원 (제재 받지 않은 회원리스트)
	@Override
	public int generalMemberCount() throws Exception {

		int generalMemberCount =adao.generalMemberCount();
		
		return generalMemberCount;
	}

	//일반 회원 리스트
	@Override
	public List<MemberVO> getGeneralMemberList(int displayPost, int postNum) throws Exception {

		List<MemberVO> generalMemberList = adao.getGeneralMemberList(displayPost, postNum);
		
		return generalMemberList;
	}

	//제재중인 모든 회원
	@Override
	public int restrictionMemberCount() throws Exception {

		int restrictionMemberCount = adao.restrictionMemberCount();
		
		return restrictionMemberCount;
	}

	
	//제재중인 회원 리스트
	@Override
	public List<MemberVO> getRestrictionMemberList(int displayPost, int postNum) throws Exception {

		List<MemberVO> restrictionMemberList = adao.getRestrictionMemberList(displayPost, postNum);
		
		return restrictionMemberList;
	}

	//진행중인 경매 수
	@Override
	public int underwayAuctionCount() throws Exception {

		int underwayAuctionCount = adao.underwayAuctionCount();
		
		return underwayAuctionCount;
	}

	@Override
	public List<GoodsVO> getUnderwayAuctionList(int displayPost, int postNum) throws Exception {

		List<GoodsVO> underwayAuctionList = adao.getUnderwayAuctionList(displayPost,postNum);
		
		return underwayAuctionList;
	}

	@Override
	public int closedAuctionCount() throws Exception {
		int closedAuctionCount = adao.closedAuctionCount();
		
				
		return closedAuctionCount;
	}

	@Override
	public List<GoodsVO> getClosedAuctionList(int displayPost, int postNum) throws Exception {

		List<GoodsVO> closedAuctionList = adao.getClosedAuctionList(displayPost, postNum);
		
		return closedAuctionList;
	}


	
	
	


	
	
	

}
