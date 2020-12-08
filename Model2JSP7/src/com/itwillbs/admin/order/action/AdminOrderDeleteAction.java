package com.itwillbs.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.order.db.AdminOrderDAO;
import com.itwillbs.admin.order.db.AdminOrderDAOImpl;

public class AdminOrderDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : AdminOrderDeleteAction_execute() 호출");
		
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")){
			forward.setPath("./Main.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 파라미터값 저장(trade_num)
		String trade_num = request.getParameter("trade_num");
		
		// AdminOrderDAO 객체 생성
		// -> deleteOrder(trade_num);
		AdminOrderDAO aodao = new AdminOrderDAOImpl();
		
		aodao.deleteOrder(trade_num);
		
		// 페이지 이동 (리스트페이지)
		forward.setPath("./AdminOrderList.ao");
		forward.setRedirect(true);		
		return forward;
	}

}
