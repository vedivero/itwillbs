package com.bestpricemarket.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bestpricemarket.domain.BasketVO;
import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MyActionVO;



public interface BasketDAO {
	// 관심상품 담기
	public void insertBasket(BasketVO bv);
	// 관심상품 목록
	public BasketVO Basketlist(int lno) throws Exception;
	// 관심상품 삭제
	public void deleteBasket(int lno) throws Exception;
	// 관심상품 세기
	public int getCount(String l_m_id) throws Exception;
	// 게시물 목록 + 페이징
	public List listPage(int displayPost, int postNum,String l_m_id) throws Exception;
	// 좋아요 삭제되면 glike 변경
	public void updateGlike(int l_g_gno) throws Exception;
	// 내경매 정보 불러와서 결제정보랑 비교하는 메서드
    public GoodsVO getGoods(int gno) throws Exception;
} 
