package com.itwillbs.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.goods.db.GoodsDAO;

public class GoodsDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : GoodsDetailAction_execute() 호출");
		
		// gno 파라미터값 저장
		int gno = Integer.parseInt(request.getParameter("gno"));
		// DB에서 해당 상품의 정보를 가지고 오기
		GoodsDAO gdao = new GoodsDAO();
		
		GoodsDTO gdto = gdao.getGoods(gno);
		
        // 상품정보 저장
		request.setAttribute("gdto", gdto);
		//request.setAttribute("gdto", gdao.getGoods(gno));
		
		// 페이지 이동(./goods/goods_detail.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_detail.jsp");
		forward.setRedirect(false);	
		return forward;
	}

}
