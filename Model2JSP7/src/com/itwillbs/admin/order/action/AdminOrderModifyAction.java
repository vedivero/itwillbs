package com.itwillbs.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.order.db.AdminOrderDAO;
import com.itwillbs.admin.order.db.AdminOrderDAOImpl;
import com.itwillbs.order.db.OrderDTO;

public class AdminOrderModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : AdminOrderModifyAction_execute() 호출");
		
		// 세션제어 (관리자)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")){
			forward.setPath("./Main.me");
			forward.setRedirect(true);
			return forward;
		}
		// 전달정보 파라미터값 저장(trade_num,status,trans_num)
		String trade_num = request.getParameter("trade_num");
		String trans_num = request.getParameter("trans_num");
		int status = Integer.parseInt(request.getParameter("status"));
		
		OrderDTO odto = new OrderDTO();
		odto.setO_trade_num(trade_num);
		odto.setO_trans_num(trans_num);
		//odto.setO_status(status);
		odto.setO_status(Integer.parseInt(request.getParameter("status")));
		
		System.out.println("M : "+odto);
		
		
		// AdminOrderDAO 객체 생성
		// - updateOrder(trade_num);
		AdminOrderDAO aodao = 
				new AdminOrderDAOImpl();
		
		//aodao.updateOrder(trade_num);
		aodao.updateOrder(odto);
		
		// 페이지 이동
		forward.setPath("./AdminOrderList.ao");
		forward.setRedirect(true);	
		
		return forward;
	}

}
