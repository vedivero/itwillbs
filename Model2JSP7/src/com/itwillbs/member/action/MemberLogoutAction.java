package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("@@@@ Action : MemberLogoutAction_execute() 실행 ");
		
		System.out.println("@@@@ Action : 세션 객체를 초기화");
		HttpSession session =  request.getSession();
		
		session.invalidate();		
		
		System.out.println("@@@@ Action : ./Main.me 페이지로 이동");
		System.out.println("@@@@ Action : JavaScript 사용 이동(+alert) ");
		
		// 자바스크립트 사용 페이지 이동하기 
		
		// 응답정보의 내용의 형식을 지정(mime타입)
		response.setContentType("text/html; charset=UTF-8");
		
		// 출력 스트림준비
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print(" alert(' 로그아웃 ! '); ");
		out.print(" location.href='./Main.me'; ");
		out.print("</script>");
		
		out.close();		
		
		return null;
	}

}
