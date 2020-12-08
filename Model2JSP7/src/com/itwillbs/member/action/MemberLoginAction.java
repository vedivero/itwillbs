package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;

public class MemberLoginAction implements Action{
	// 처리 페이지 -> Action 인터페이스구현

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("@@@@ Action : controller -> Action 이동");
		System.out.println("@@@@ Action : MemberLoginAction_execute() 호출");

		System.out.println("@@@@ Action : 로그인 처리");
		System.out.println("@@@@ Action : 파라미터값 저장 ");
		// id,pw 
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("@@@@ Action : DAO 객체 생성 - 메서드 구현");
		MemberDAO mdao = new MemberDAO();
		
		int check = mdao.idCheck(id,pw);
		System.out.println("@@@@ Action : DB 처리 결과에 따른 페이지 이동");
		
		// check == 0 (비밀번호 오류)
		if(check == 0){
			// javascript 사용 alert()
			response.setContentType("text/html; charset=UTF-8");
			
			// 화면에 출력하는 출력스트림 생성
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print(" alert('비밀번호 오류!!');");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			
			return null;
		}
		// check == -1 (아이디 없음)
		else if( check == -1 ){
			// javascript 사용 alert()
			response.setContentType("text/html; charset=UTF-8");
			
			// 화면에 출력하는 출력스트림 생성
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print(" alert('아이디 없음!!');");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			// controller에서 이동X
			return null;
		}
		
		// check == 1 (정상처리)
		if(check == 1){
			// 아이디값을 세션객체에 저장
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			// main페이지 -> ./Main.me 이동
		}
		
		// 페이지 이동 객체 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);		
		return forward;
	}
	

}
