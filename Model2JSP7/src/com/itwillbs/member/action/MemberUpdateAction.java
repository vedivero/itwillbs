package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("MemberUpdateAction_execute() 호출");
		
		// 세션값 제어(id)
		 HttpSession session = request.getSession();
		 String id = (String) session.getAttribute("id");
		 
		 ActionForward forward = new ActionForward();
		 if(id == null){
			 forward.setPath("./MemberLogin.me");
			 forward.setRedirect(true);
			 return forward;			 			 
		 }
		 
		 // MemberDAO 객체 생성
		 MemberDAO mdao = new MemberDAO();
		 // 회원 가져오는 메서드 getMember(String id)
		 MemberBean mb = mdao.getMember(id);
		 // 회원정보를 저장(request 영역)
		 request.setAttribute("memberbean", mb);

		 // 페이지 이동 (./member/updateForm.jsp)
		forward.setPath("./member/updateForm.jsp");
		forward.setRedirect(false);		
		
		System.out.println("M : DB에서 회원정보 가져와서 저장, 페이지 이동");
		
		return forward;
	}
	
	

}
