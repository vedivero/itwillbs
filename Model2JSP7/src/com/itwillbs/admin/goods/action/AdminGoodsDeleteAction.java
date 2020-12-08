package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;

public class AdminGoodsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : AdminGoodsDeleteAction_execute() 호출");
		// 관리자 계정 확인(세션ID)
		HttpSession session = request.getSession();

		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if (id == null || !id.equals("admin")) {
			// response.sendRedirect("./Main.me");
			forward.setPath("./Main.me");
			forward.setRedirect(true);
			return forward;
		}

		// 전달되는 정보 gno 저장
		int gno = Integer.parseInt(request.getParameter("gno"));

		// DAO 객체 생성 - deleteGoods(gno)
		AdminGoodsDAO agdao = new AdminGoodsDAO();

		agdao.deleteGoods(gno);

		// 페이지 이동
		//ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		return forward;
	}

}
