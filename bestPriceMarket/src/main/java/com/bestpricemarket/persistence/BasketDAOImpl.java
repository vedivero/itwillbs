package com.bestpricemarket.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bestpricemarket.domain.BasketVO;
import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MyActionVO;

@Repository
public class BasketDAOImpl implements BasketDAO {
  
	
	
	@Inject
	private SqlSession session; // mapper위치까지 접근 가능 but mapper가 여러개일수있음 => mapper구분필요

	private static final String namespace = "com.bestpricemarket.mappers.BasketMapper";

	// 관심상품 담기
	@Override
	public void insertBasket(BasketVO bv) {
        System.out.println("D: 장바구니 추가");
		
		session.insert(namespace+".insertBasket",bv);
		
	}
	// 관심상품 목록
	@Override
	public BasketVO Basketlist(int lno) throws Exception {
	 BasketVO bvo = session.selectOne(namespace+".listBasket");
		return bvo;
	}
	// 관심상품 삭제
	@Override
	public void deleteBasket(int l_g_gno) throws Exception {
		System.out.println("D: 삭제 동작!!");
	     session.delete(namespace+".deleteBasket",l_g_gno);
	}
	// 관심상품 세기
	@Override
	public int getCount(String l_m_id) throws Exception {
		
		return session.selectOne(namespace+".getCount",l_m_id);
	}
	// 게시물 목록 + 페이징
	@Override
	public List listPage(int displayPost, int postNum,String l_m_id) throws Exception {
		Map data = new HashMap();
		  
		 data.put("displayPost", displayPost);
		 data.put("postNum", postNum);
		 data.put("l_m_id", l_m_id); 
		 return session.selectList(namespace + ".listPage", data);
	}
	
	
	@Override
	public void updateGlike(int l_g_gno) throws Exception {
		session.update(namespace + ".updateMyAction",l_g_gno);	
	}
	@Override
	public GoodsVO getGoods(int gno) throws Exception {
		GoodsVO gvo = session.selectOne(namespace + ".getMyAction",gno);
		return gvo;
		
	}

	
    
  
	

}
