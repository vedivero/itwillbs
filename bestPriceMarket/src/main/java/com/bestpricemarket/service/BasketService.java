package com.bestpricemarket.service;

import java.util.List;

import com.bestpricemarket.domain.BasketVO;
import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MyActionVO;

public interface BasketService {
	// 관심상품 담기
	public void insertBasket(BasketVO bv) throws Exception;
	// 관심상품 목록
	public BasketVO Basketlist(int lno) throws Exception;
	// 관심상품 삭제
    public void deleteBasket(int l_g_gno) throws Exception;
	// 관심상품 세기
    public int getCount(String l_m_id) throws Exception;
    // 게시물 목록 + 페이징
    public List listPage(int displayPost, int postNum,String l_m_id) throws Exception;

    public void updateGlike(int l_g_gno) throws Exception;
    
	// 내경매 정보 불러와서 결제정보랑 비교하는 메서드
	public GoodsVO getGoods(int gno) throws Exception;
}
