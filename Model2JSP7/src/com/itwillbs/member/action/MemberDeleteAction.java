package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션제어 
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null){
		   forward.setPath("./MemberLogin.me");
		   forward.setRedirect(true);
		   return forward;
		}
		
		// 전달정보 받아서 저장 (id,pw)
		// id는 세션값 사용
		String pw = request.getParameter("pw");
		
		// DAO 객체 생성 - deleteMember(id,pw)
		MemberDAO mdao = new MemberDAO();
		
		int check = mdao.deleteMember(id,pw);
		
		//  삭제 결과를 리턴에 따른 페이지 이동 결정		
		// 페이지 이동(Javascript)
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		
		if(check == 0){
			out.print("<script>");
			out.print(" alert('비밀번호 오류'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			return null;
		}else if(check == -1){
			out.print("<script>");
			out.print(" alert('아이디 없음'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			return null;
		}
		
		//check == 1
		// 삭제의미 -> 세션값 제어(삭제)
		session.invalidate();
		
		out.print("<script>");
		out.print(" alert('회원 삭제(탈퇴완료!)'); ");
		out.print(" location.href='./Main.me'; ");
		out.print("</script>");
		
		out.close();
		return null;
	}

}
