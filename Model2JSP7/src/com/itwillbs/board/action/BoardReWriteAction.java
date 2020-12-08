package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardReWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	    System.out.println("M : BoardReWriteAction_execute() 호출");
		
	    // 한글처리 
	    request.setCharacterEncoding("UTF-8");
	    // 전달된 파라미터값 저장 
	    // (bno,re_ref,re_lev,re_seq)-hidden
	    // (name,passwd,subject,content)-입력값
	    
	    // BoardDTO 객체 생성후 저장
	    BoardDTO bdto = new BoardDTO();
	    bdto.setBno(Integer.parseInt(request.getParameter("bno")));
	    bdto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
	    bdto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
	    bdto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
	    
	    bdto.setName(request.getParameter("name"));
	    bdto.setPasswd(request.getParameter("passwd"));
	    bdto.setSubject(request.getParameter("subject"));
	    bdto.setContent(request.getParameter("content"));
	    
	    // BoardDAO 객체 생성후 - reInsertBoard(dto) 호출
	    BoardDAO bdao = new BoardDAO();
	    
	    bdao.reInsertBoard(bdto);
	    
	    System.out.println("M : 답글 작성 완료!!!");
	    
	    // 페이지 이동(./BoardList.bo)
	    ActionForward forward = new ActionForward();
	    forward.setPath("./BoardList.bo");
	    forward.setRedirect(true);		
		return forward;
	}

}
