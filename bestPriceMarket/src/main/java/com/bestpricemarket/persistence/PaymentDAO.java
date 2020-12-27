package com.bestpricemarket.persistence;

import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.domain.MyActionVO;
import com.bestpricemarket.domain.PaymentVO;

public interface PaymentDAO {
	// 회원정보 가져오기
	public MemberVO getMember(String id) throws Exception;
			
	// 상품정보 가져오기
	public GoodsVO getGoods(int gno) throws Exception;
	
	// 결제내역 저장하기
	public void insertParam(PaymentVO pvo) throws Exception;
	
	// 해당번호에 맞는 결제한 정보 가져오기
	public PaymentVO getPayment(int p_g_gno) throws Exception;
	
	// 내경매 정보 불러와서 결제정보랑 비교하는 메서드
	public MyActionVO getMyAction(int a_g_gno) throws Exception;
	
	// 내 경매 물품이 결제정보에 있으면 paystatus 수정하는 메서드
	public void updateMyAction(MyActionVO myVO) throws Exception;
}
