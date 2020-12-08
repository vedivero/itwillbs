package com.itwillbs.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.basket.db.BasketDTO;

public class BasketAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : BasketAddAction_execute() 호출");
		// 로그인 정보 (로그인 처리필요)
		
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;			
		}
		
		// 한글 데이터 처리
		request.setCharacterEncoding("UTF-8");
		// 장바구니 저장
		// 상품정보 저장가능 (GoodsDetail 페이지에서 전달)
		// BasketDTO / BasketDAO 객체 
		
		// 장바구니 객체 생성
		// 파라미터값 저장(상품번호,구매수량,옵션(크기,색상)) + ID
		BasketDTO bdto = new BasketDTO();
		bdto.setB_g_num(Integer.parseInt(request.getParameter("gno")));
		bdto.setB_g_amount(Integer.parseInt(request.getParameter("amount")));
		
		bdto.setB_g_color(request.getParameter("color"));
		bdto.setB_g_size(request.getParameter("size"));
		bdto.setB_m_id(id);
		
		System.out.println("장바구니 객체 정보 : "+bdto);
		
		// BasketDAO 객체 생성
		BasketDAO bdao = new BasketDAO();
		
		// 해당 상품(+옵션)이 장바구니에 있는지 체크
		// A - S, RED 상품
		// B - S, RED 상품
		// A - S, Black 상품
		int check = bdao.checkGoods(bdto);
				
		// basketAdd() - 추가
		if(check != 1){ //장바구니에 해당상품이 없다
			bdao.basketAdd(bdto);
		}
		
		// 장바구니 목록(./BasketList.ba)
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);	
		
		return forward;
	}

}
