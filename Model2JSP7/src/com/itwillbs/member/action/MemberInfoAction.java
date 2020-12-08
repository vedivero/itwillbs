package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("@@@@ Action : MemberInfoAction_execute() 실행");
		
		System.out.println("@@@@ Action : 세션값 처리");
		// 세션값을 가져오기
		// 없을경우 -> 로그인 페이지 이동
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// 페이지 이동정보 저장 객체
		ActionForward forward = new ActionForward();
		if(id == null){
			//response.sendRedirect(arg0); (x)
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true); 
			return forward;
		}
		
		System.out.println("@@@@ Action :  id -> "+id);
		
		System.out.println("@@@@ Action : MemberDAO 객체 생성");
		System.out.println("@@@@ Action : 회원정보 가져오는 메서드 생성");
		// DAO 객체를 생성해서 정보를 가져오기
		MemberDAO mdao = new MemberDAO();
		// id에 해당하는 회원 정보(MemberBean)를 가져오기
		MemberBean mb = mdao.getMember(id);
		
		// java -> jsp 데이터 전달
		// request 객체에 저장 / (session 객체)
		System.out.println("@@@@ Action : request 영역에 저장 페이지 이동");
		
		request.setAttribute("memberBean", mb);	
		
		System.out.println("@@@@ Action : 페이지 이동 ");
		
		// 페이지 이동
		// ./member/memberInfo.jsp 	
		forward.setPath("./member/memberInfo.jsp");
		forward.setRedirect(false);	
		
		return forward;
	}

}
