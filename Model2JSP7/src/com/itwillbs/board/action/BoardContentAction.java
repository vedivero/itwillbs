package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : BoardContentAction_execute() 호출");
		// DAO -> DB -> .jsp
		
		// 한글처리 (post방식 전달)
		
		// 전달받은 데이터 저장(bno, pageNum)
		int bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		// DB객체 생성 
		BoardDAO bdao = new BoardDAO();
		
		// 1) 조회수 1 증가 (updateReadCount())
		bdao.updateReadCount(bno);
		
		// 2) 특정 번호에 해당하는 글 정보를 가져오기 (getBoard())
		BoardDTO bdto = bdao.getBoard(bno);
		
		// 정보 저장(request영역)
		request.setAttribute("bdto", bdto);
		request.setAttribute("pageNum", pageNum);
		
		// 정보를 보여주는 페이지(view-.jsp) 전달
		// ./board/content.jsp 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./board/content.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
