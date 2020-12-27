package com.bestpricemarket.persistence;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.domain.MyActionVO;
import com.bestpricemarket.domain.PaymentVO;

//@Repository : DAO객체를 스프링에서 인식 할 수 있도록 처리
//DAO 객체를 구현한 객체로 사용하도록 지정

@Repository
public class PaymentDAOImpl implements PaymentDAO{
	// DAO 동작
	
	// 디비 연결 (의존 주입)
	@Inject
	private SqlSession sqlSession;
	// -> Mapper가 있는 위치까지 접근
	
	// Mapper를 구분하는 값
	private static final String namespace 
		= "com.bestpricemarket.mappers.payMapper"; 
	
	@Override
	public MemberVO getMember(String id) throws Exception {
		
		MemberVO mvo = sqlSession.selectOne(namespace + ".getMember",id);
		return mvo;
	}

	@Override
	public GoodsVO getGoods(int gno) throws Exception {
		GoodsVO gvo = sqlSession.selectOne(namespace + ".getGoods",gno);
		return gvo;
	}

	@Override
	public void insertParam(PaymentVO pvo) throws Exception {
		sqlSession.selectOne(namespace + ".insertParam",pvo);		
	}

	@Override
	public PaymentVO getPayment(int p_g_gno) throws Exception {
		
		return sqlSession.selectOne(namespace + ".getPayment",p_g_gno);
	}

	@Override
	public MyActionVO getMyAction(int a_g_gno) throws Exception {
		MyActionVO myVO = sqlSession.selectOne(namespace + ".getMyAction",a_g_gno);
		return myVO;
	}

	@Override
	public void updateMyAction(MyActionVO myVO) throws Exception {
		sqlSession.update(namespace + ".updateMyAction",myVO);		
	}
	
	
}
