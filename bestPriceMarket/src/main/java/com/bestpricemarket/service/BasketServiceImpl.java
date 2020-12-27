package com.bestpricemarket.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bestpricemarket.controller.MemberController;
import com.bestpricemarket.domain.BasketVO;
import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MyActionVO;
import com.bestpricemarket.persistence.BasketDAO;

@Service
public class BasketServiceImpl implements BasketService {
	private static final Logger l = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	BasketDAO bkdao;

	@Override
	public void insertBasket(BasketVO bv) throws Exception {

		l.info("S: 장바구니 추가동작!");
		bkdao.insertBasket(bv);
		
	}

	@Override
	public BasketVO Basketlist(int lno) throws Exception {
		l.info("S: 장바구니 추가동작!");
	    BasketVO bvo = bkdao.Basketlist(lno);
		return bvo;
	}

	@Override
	public void deleteBasket(int l_g_gno) throws Exception {
		System.out.println("S: 삭제 동작");
	    bkdao.deleteBasket(l_g_gno);
	}

	@Override
	public int getCount(String l_m_id) throws Exception {
		
		return bkdao.getCount(l_m_id);
	}

	@Override
	public List listPage(int displayPost, int postNum,String l_m_id) throws Exception {
		
		return bkdao.listPage(displayPost, postNum,l_m_id);
	}

	@Override
	public void updateGlike(int l_g_gno) throws Exception {
		bkdao.updateGlike(l_g_gno);
		
	}

	@Override
	public GoodsVO getGoods(int gno) throws Exception {
	    GoodsVO gvo = bkdao.getGoods(gno);
		return gvo;
	}
	
}
