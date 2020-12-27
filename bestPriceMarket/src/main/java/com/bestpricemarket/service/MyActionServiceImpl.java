package com.bestpricemarket.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bestpricemarket.controller.MemberController;
import com.bestpricemarket.domain.MyActionVO;
import com.bestpricemarket.domain.MyBiddingVO;
import com.bestpricemarket.domain.PricemonitoringVO;
import com.bestpricemarket.persistence.MyActionDAO;

@Service
public class MyActionServiceImpl implements MyActionService {
	private static final Logger l = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MyActionDAO adao;

	// 입찰 목록 담기
	@Override
	public void insertAction(MyActionVO av) throws Exception {
		adao.insertAction(av);
	}

	// 입찰 목록 
	@Override
	public List<MyBiddingVO> actionlist(int displayPost, int postNum,String pm_m_userid) throws Exception {
		return adao.actionlist(displayPost,postNum,pm_m_userid);
	}
	
	// 낙찰 목록
	@Override
	public List<MyActionVO> paylist(int displayPost, int postNum, String a_m_id) throws Exception {
		return adao.paylist(displayPost,postNum,a_m_id);
	}

	//입찰 세기
	@Override
	public int getCount(String pm_m_userid) throws Exception {
		return adao.getCount(pm_m_userid);
	}
	
	//낙찰 세기
	@Override
	public int getPayCount(String a_m_id) throws Exception {
		return adao.getPayCount(a_m_id);
	}
    
	//선택 삭제
	@Override
	public void delete(MyBiddingVO bvo) {
		adao.delete(bvo);
	}
	
	// *************** 2020/11/16/월요일 낙찰정보 **************************
	@Override
	public List<MyBiddingVO> myBidding(String pm_m_userid) throws Exception {
		
		return adao.myBidding(pm_m_userid);
	}

	@Override
	public MyActionVO myActionInfo(int a_g_gno) throws Exception {
		
		return adao.myActionInfo(a_g_gno);
	}
	// *************** 2020/11/16/월요일 낙찰정보끝 **************************

	@Override
	public PricemonitoringVO pricemonitoringInfo(int pm_g_gno) throws Exception {
		
		return adao.pricemotoringInfo(pm_g_gno);
	}
	
	
}
