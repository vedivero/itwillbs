package com.bestpricemarket.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.domain.MyActionVO;
import com.bestpricemarket.domain.PaymentVO;
import com.bestpricemarket.persistence.PaymentDAO;

//@Service : 해당 클래스를 서비스 객체로 처리 하겠다.(스프링에서 인식)

@Service
public class PaymentServiceImpl implements PaymentService{

	// DB처리하기위한 객체 주입
	@Inject
	private PaymentDAO pdao;
	
	@Override
	public MemberVO getMember(String id) throws Exception {
		MemberVO vo = pdao.getMember(id);	
		
		return vo;
	}

	@Override
	public GoodsVO getGoods(int gno) throws Exception {
		GoodsVO gvo = pdao.getGoods(gno);
		return gvo;
	}

	@Override
	public void insertParam(PaymentVO pvo) throws Exception {
		pdao.insertParam(pvo);		
	}

	@Override
	public PaymentVO getpayment(int p_g_gno) throws Exception {
		PaymentVO vo = pdao.getPayment(p_g_gno);
		return vo;
	}

	@Override
	public MyActionVO getMyAction(int a_g_gno) throws Exception {
		MyActionVO myVO = pdao.getMyAction(a_g_gno);
		return myVO;
	}

	@Override
	public void updateMyAction(MyActionVO myVO) throws Exception {
		pdao.updateMyAction(myVO);
		
	}
}
