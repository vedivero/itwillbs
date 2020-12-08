package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : AdminGoodsModifyProAction_execute() 호출");
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
		
		
		// 한글처리 
		request.setCharacterEncoding("UTF-8");
		// 전달되는 파라미터값 받아서 저장
		// GoodsDTO 사용
		GoodsDTO gdto = new GoodsDTO();
		
		gdto.setAmount(Integer.parseInt(request.getParameter("amount")));
		gdto.setBest(Integer.parseInt(request.getParameter("best")));
		gdto.setCategory(request.getParameter("category"));
		gdto.setColor(request.getParameter("color"));
		gdto.setContent(request.getParameter("content"));
		gdto.setGno(Integer.parseInt(request.getParameter("gno")));
		//gdto.setImage(request.getParameter("image"));
		gdto.setName(request.getParameter("name"));
		gdto.setPrice(Integer.parseInt(request.getParameter("price")));
		gdto.setSize(request.getParameter("size"));
		
		
		// DAO 객체 생성 - modifyGoods(dto)
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		
		agdao.modifyGoods(gdto);
		
		// 페이지 이동(AdminGoodsList.ag)
		//ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		return forward;
	}

}
