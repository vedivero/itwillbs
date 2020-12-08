package com.itwillbs.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.member.db.MemberBean;
import com.itwillbs.member.db.MemberDAO;

public class MemberJoinAction implements Action {
	// ~Action 객체는 항상 Action 인터페이스를 구현해서 사용

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("MemberJoinAction_execute() 실행!");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 전달정보(회원정보) 저장 - MemberBean
		MemberBean mb = new MemberBean();
		mb.setAge(Integer.parseInt(request.getParameter("age")));
		mb.setEmail(request.getParameter("email"));
		mb.setGender(request.getParameter("gender"));
		mb.setId(request.getParameter("id"));
		mb.setName(request.getParameter("name"));
		mb.setPw(request.getParameter("pw"));
		mb.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("@@@@ Action : 전달받은 정보 확인");
		System.out.println("@@@@ Action : MemberBean -> "+mb);
		
		// DB 처리 객체 - MemberDAO 
		MemberDAO mdao = new MemberDAO();
		// 회원가입 처리
		mdao.insertMember(mb);
		
		System.out.println("@@@@ Actoion : 디비 작업 처리 완료");
		System.out.println("@@@@ Actoion : 페이지 이동(컨트롤러 이동)");
		
		// 페이지이동
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);	
		
		return forward;
	}
	
	

}
