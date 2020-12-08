package com.itwillbs.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;

public class BasketDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : BasketDeleteAction_execute() 호출");
		
		// 로그인 정보 (로그인 처리필요)

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 파라미터 정보 저장(b_num)
		 int b_num = Integer.parseInt(request.getParameter("b_num"));
		 
		 // DAO 객체 생성후  - 장바구니 삭제()
		 BasketDAO bkdao = new BasketDAO();
		 bkdao.basketDelete(b_num);

		 // 페이지 이동 
		 forward.setPath("./BasketList.ba");
		 forward.setRedirect(true);	
		
		 return forward;
	}
	

}
