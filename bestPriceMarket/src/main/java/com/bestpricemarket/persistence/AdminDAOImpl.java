package com.bestpricemarket.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MemberVO;
import com.sun.mail.imap.protocol.Namespaces.Namespace;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Inject
	private SqlSession session;
	
	private static final String namespace
		="com.bestpricemarket.mapper.AdminMapper";

	
	@Override
	public int allMembersCount() throws Exception {

		int allMembersCount = session.selectOne(namespace+".allMembers");
		System.out.println("AdminDAOImpl+ allMembersCount메서드로 : 모든 회원 수 불러오기 성공");
		return allMembersCount;
	}
	
	@Override
	public List<MemberVO> getMemberList(int displayPost,int postNum) throws Exception {
	
		HashMap data = new HashMap();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		List<MemberVO> MemberList = session.selectList(namespace+".getMemberList",data);
		
		System.out.println("AdminDAOImpl에서 getMemberList()로: 모든 회원 수 List에 담기 성공");
		System.out.println("MemberList:"+MemberList);
		return MemberList;
	}

	//특정 회원 정보 상세보기
	@Override
	public MemberVO detail(String id) throws Exception {
		
		MemberVO vo = session.selectOne(namespace+".detail", id);
		
		return vo;
	}

	//계정 제한
	@Override
	public void changeRestriction(MemberVO vo) throws Exception {
		session.update(namespace+".changeRestriction", vo);
	}

	//계정 제한 해제
	@Override
	public void liftingRestriction(MemberVO vo) throws Exception {

		session.update(namespace+".liftingRestriction", vo);
		
	}

	//제재 사유 입력
	@Override
	public void restrinctionReason(MemberVO vo) throws Exception {

		session.update(namespace+".restrinctionReason",vo);
		
	}
	
	
	@Override
	public int allGoodsCount() throws Exception {

		int allGoodsCount = session.selectOne(namespace+".allGoodsCount");
		
		return allGoodsCount;
	}

	//등록된 상품 목록 불러오기
	@Override
	public List<GoodsVO> getGoodsList(int displayPost,int postNum)throws Exception {
		
		HashMap data = new HashMap();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		List<GoodsVO> goodsList = session.selectList(namespace+".getGoodsList",data);
		
		System.out.println("goodsList:"+goodsList);
		return goodsList;
	}

	//특정 상품 상세보기
	@Override
	public GoodsVO goodsDetail(int gno) throws Exception {

		GoodsVO vo = session.selectOne(namespace+".goodsDetail", gno);
		
		return vo;
	}

	//등록된 상품판매 글 삭제
	@Override
	public void delete(int gno) {

		session.delete(namespace+".delete",gno);
		
	}

	//제재받지 않은 모든 회원의 수
	@Override
	public int generalMemberCount() throws Exception {
	
		int generalMemberCount= session.selectOne(namespace+".generalMemberCount");
		return generalMemberCount;
	}

	@Override
	public List<MemberVO> getGeneralMemberList(int displayPost, int postNum) throws Exception {

		HashMap data = new HashMap();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		List<MemberVO> generalMemberList = session.selectList(namespace+".getGeneralMemberList", data);
		
		System.out.println("AdminDAOImpl에서 getGeneralMemberList()메서드 실행: 일반 회원 List에 담기 성공");
		System.out.println("generalMemberList:"+generalMemberList);
		
		return generalMemberList;
	}

	//제재중인 모든 회원
	@Override
	public int restrictionMemberCount() throws Exception {

		int restrictionMemberCount = session.selectOne(namespace+".restrictionMemberCount");
		
		return restrictionMemberCount;
	}

	//제재중인 회원 리스트
	@Override
	public List<MemberVO> getRestrictionMemberList(int displayPost, int postNum) throws Exception {

		HashMap data = new HashMap();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		List<MemberVO> restrictionMemberList = session.selectList(namespace+".getRestrictionMemberList", data); 
		
		System.out.println("AdminDAOImpl에서 getRestrictionMemberList()로: 제재중인 회원, List에 담기 성공");
		System.out.println("restrictionMemberList:"+restrictionMemberList);
		
		return restrictionMemberList;
	}

	@Override
	public int underwayAuctionCount() throws Exception {

		int underwayAuctionCount = session.selectOne(namespace+".underwayAuctionCount");
		
		return underwayAuctionCount;
	}

	@Override
	public List<GoodsVO> getUnderwayAuctionList(int displayPost, int postNum) throws Exception {

		HashMap data = new HashMap();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		List<GoodsVO> underwayAuctionList = session.selectList(namespace+".getUnderwayAuctionList", data);
		
		System.out.println("AdminDAOImpl에서 진행중인 상품 List에 담기 완료");
		System.out.println("AdminDAOImpl에서 → underwayAuctionList"+underwayAuctionList);
		return underwayAuctionList;
	}

	@Override
	public int closedAuctionCount() throws Exception {

		int closedAuctionCount = session.selectOne(namespace+".closedAuctionCount");
		return closedAuctionCount;
	}

	@Override
	public List<GoodsVO> getClosedAuctionList(int displayPost, int postNum) throws Exception {

		HashMap data = new HashMap();
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		List<GoodsVO> closedAuctionList = session.selectList(namespace+".getClosedAuctionList",data);
		
		return closedAuctionList;
	}

	
	
	
}
