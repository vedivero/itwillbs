package com.bestpricemarket.service;

import java.util.List;
import com.bestpricemarket.domain.MyActionVO;
import com.bestpricemarket.domain.MyBiddingVO;
import com.bestpricemarket.domain.PricemonitoringVO;

public interface MyActionService {
	//입찰 목록 담기
	public void insertAction(MyActionVO av) throws Exception;
	//입찰 목록 
	public List<MyBiddingVO> actionlist(int displayPost, int postNum,String pm_m_userid) throws Exception; 
    //낙찰 목록
	public List<MyActionVO> paylist(int displayPost, int postNum,String a_m_id) throws Exception;
	//입찰 세기
	public int getCount(String pm_m_userid) throws Exception;
	//낙찰 세기
	public int getPayCount(String a_m_id) throws Exception;
	//선택 삭제
	public void delete(MyBiddingVO bvo);
	
	// *************** 2020/11/16/월요일 낙찰정보 **************************
	// 입찰목록 리스트
	public List<MyBiddingVO> myBidding(String pm_m_userid) throws Exception;
	
	// 낙찰된 회원 체크
	public MyActionVO myActionInfo(int a_g_gno) throws Exception;
	// *************** 2020/11/16/월요일 낙찰정보끝 **************************
	public PricemonitoringVO pricemonitoringInfo(int pm_g_gno) throws Exception;


}
