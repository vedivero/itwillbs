package com.bestpricemarket.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bestpricemarket.domain.BasketVO;
import com.bestpricemarket.domain.MyActionVO;
import com.bestpricemarket.domain.MyBiddingVO;
import com.bestpricemarket.domain.PricemonitoringVO;

@Repository
public class MyActionDAOImpl implements MyActionDAO {
  
	
	
	@Inject
	private SqlSession session; // mapper위치까지 접근 가능 but mapper가 여러개일수있음 => mapper구분필요

	private static final String namespace = "com.bestpricemarket.mappers.myActionMapper";
	
	//입찰 목록 담기
	@Override
	public void insertAction(MyActionVO av) throws Exception {
		session.selectOne(namespace+".insertAction",av);
		
	}
	// 입찰 목록
	@Override
	public List<MyBiddingVO> actionlist(int displayPost, int postNum,String pm_m_userid) throws Exception {
		Map data = new HashMap();
		  
		 data.put("displayPost", displayPost);
		 data.put("postNum", postNum);
		 data.put("pm_m_userid", pm_m_userid); 
		return session.selectList(namespace + ".actionlist",data);
	}
	// 낙찰 목록
	@Override
	public List<MyActionVO> paylist(int displayPost, int postNum, String a_m_id) throws Exception {
		Map map = new HashMap();
		map.put("displayPost", displayPost);
		map.put("postNum", postNum);
		map.put("a_m_id", a_m_id);
		return session.selectList(namespace+".paylist",map);
		
	}
	// 입찰 목록 세기
	@Override
	public int getCount(String pm_m_userid) throws Exception {
		
		return session.selectOne(namespace+".getCount",pm_m_userid);
	}
	// 낙찰 목록 세기
	@Override
	public int getPayCount(String a_m_id) throws Exception {
		
		return session.selectOne(namespace+".getPayCount",a_m_id);
	}
    // 선택 삭제
	@Override
	public void delete(MyBiddingVO bvo) {
		session.delete(namespace+".delete",bvo);
		
	}
	
	// *************** 2020/11/16/월요일 낙찰정보 **************************
	@Override
	public List<MyBiddingVO> myBidding(String pm_m_userid) throws Exception {
		
		return session.selectList(namespace + ".mybiddingInfo",pm_m_userid);
	}
	
	@Override
	public MyActionVO myActionInfo(int a_g_gno) throws Exception {
		
		return session.selectOne(namespace + ".myActionInfo",a_g_gno);
	}
	// *************** 2020/11/16/월요일 낙찰정보끝 **************************
	@Override
	public PricemonitoringVO pricemotoringInfo(int pm_g_gno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".getPricemonitoring",pm_g_gno);
	}
	
	
	
	
	
	
	

	

  
	

}
