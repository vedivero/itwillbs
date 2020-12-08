package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : BoardUpdateAction_execute() 호출 ");
		
		// bno=1&pageNum=1 파라미터 정보 저장
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		// DAO 객체 생성 -> bno 해당하는 정보 가져오기
		BoardDAO bdao = new BoardDAO();
		BoardDTO bdto = bdao.getBoard(bno);
		
		// request 영역에 정보 저장
		request.setAttribute("bdto", bdto);

		// get방식으로 pageNum 가지고이동
		// 페이지 이동(./board/updateForm.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./board/updateForm.jsp?pageNum="+pageNum);
		forward.setRedirect(false);
		return forward;
	}

}
