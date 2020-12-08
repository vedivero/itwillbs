package com.itwillbs.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.goods.db.GoodsDAO;

public class GoodsListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("M : GoodsListAction_execute() 호출");
        
        // 한글처리 
        request.setCharacterEncoding("UTF-8");
        
        // 카테고리 처리 추가
        // .jsp 링크 -> 카테고리정보 가지고 Action 
        // -> DB이동(카테고리) -> .jsp
        
        // 파라미터값 저장(카테고리)
        String item = request.getParameter("item");
        System.out.println("카테고리 값 : "+item);
        if( item == null ){
        	item = "all";
        }
        
        // GoodsDAO객체 생성 -> DB 이동
        // 상품 목록 전체를 가져오는 메서드 - GoodsListAll()
        GoodsDAO gdao = new GoodsDAO();
        //List<GoodsDTO> goodsList = gdao.GoodsListAll();
        List<GoodsDTO> goodsList = gdao.GoodsList(item);
        
        // 정보 request 영역에 저장
        request.setAttribute("goodsList", goodsList);
        
        // 페이지이동(view)
        // ./goods/goods_list.jsp
        ActionForward forward= new ActionForward();
        forward.setPath("./goods/goods_list.jsp");
        forward.setRedirect(false);
		
		return forward;
	}
	

}
