package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet {

	// 일반 클래스를 서블릿을 상속해서 컨트롤러 역활 할수있도록 설정

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doProcess() 호출 (페이지 GET/POST방식 모두 사용호출)");

		// 주소 비교 (주소 매핑)

		System.out.println("--------------@ 주소 계산 @-------------");
		// requestURI : /Model2JSP7/test.me
		// 프로젝트명 + 주소
		String requestURI = request.getRequestURI();
		System.out.println(" requestURI : " + requestURI);

		// contextPath : /Model2JSP7
		// 프로젝트명
		String contextPath = request.getContextPath();
		System.out.println(" contextPath : " + contextPath);

		// 가상주소
		// /test.me
		String command = requestURI.substring(contextPath.length());
		System.out.println(" command(가상주소) : " + command);

		System.out.println("--------------@ 주소 계산 @-------------");
		
		
		System.out.println("--------------@ 주소 비교후 처리 @-------------");
		
		Action action = null;
		ActionForward forward = null;
		
		// 주소에 따른 처리 구분 (주소 매핑후 이동)
		if(command.equals("/BoardWrite.bo")){
			System.out.println("C : /BoardWrite.bo 주소 요청");
			// 글쓰기 동작 -> 글입력 + 글저장(DB)
			
			// 글입력하는 페이지로 이동 (./board/writeForm.jsp)
			forward = new ActionForward();
			forward.setPath("./board/writeForm.jsp");
			forward.setRedirect(false);
			
			System.out.println("C : ./board/writeForm.jsp 주소 이동 준비 완료(forward) ");
		}
		else if(command.equals("/BoardWriteAction.bo")){
			// 글 저장 (.jsp -> servlet -> .java -> DB)
			
			// com.itwillbs.board.action 패키지 사용
			// BoardWriteAction 객체 생성
			System.out.println("C : BoardWriteAction 객체 생성후 execute() 호출");
			
			try {
				forward = new BoardWriteAction().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardList.bo")){
			System.out.println("C : /BoardList.bo 호출 ");
			
			// .bo -> Action_DAO_DB -> .jsp
			// BoardListAction 객체 생성
			action = new BoardListAction();
			System.out.println("C : BoardListAction객체 생성 execute() 호출");
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardContent.bo")){
			System.out.println("C : /BoardContent.bo 호출");
			
			// BoardContentAction 객체 -> DB -> .jsp 
			action = new BoardContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardUpdate.bo")){
			System.out.println("C : /BoardUpdate.bo 호출");
			
			// 글 수정 페이지 -> Action -> DB ->.jsp
			// BoardUpdateAction 객체 생성
			action = new BoardUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardUpdateProAction.bo")){
			System.out.println("C : /BoardUpdateProAction.bo 호출");
		    System.out.println("C : .jsp -> .bo");
		    // BoardUpdateProAction 객체 생성
		    
		    action = new BoardUpdateProAction();
		    
		    try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDelete.bo")){
			System.out.println("C : /BoardDelete.bo 호출");
			System.out.println("C : 화면-.jsp(주소 -.bo) -> .bo(주소).jsp(화면) ");
			// .bo -> .jsp (DB호출 x)
			
			// 페이지 이동 ("./WebContent/board/deleteForm.jsp")
			forward = new ActionForward();
			forward.setPath("./board/deleteForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/BoardDeleteAction.bo")){
			System.out.println("C : /BoardDeleteAction.bo 호출");
			
			// BoardDeleteAction 객체 생성
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardReWrite.bo")){
			System.out.println("C : /BoardReWrite.bo 호출");
			
			// DB이동 X -> view 페이지 이동
			forward = new ActionForward();
			forward.setPath("./board/reWriteForm.jsp");
			forward.setRedirect(false);		
		}
		else if(command.equals("/BoardReWriteAction.bo")){
			System.out.println("C : /BoardReWriteAction.bo 호출");
			
			// BoardReWriteAction 객체 생성 
			action = new BoardReWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/FileBoardWrite.bo")){
			System.out.println("C : /FileBoardWrite.bo 호출");
			
			// .bo -> .jsp 페이지 이동
			forward = new ActionForward();			
			forward.setPath("./board/fWriteForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/FileBoardWriteAction.bo")){
			System.out.println("C : /FileBoardWriteAction.bo 호출");
			
			//  .jsp -> Action -> DB
			// FileBoardWriteAction() 객체 생성
			action = new FileBoardWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		
		
		
		
		
		System.out.println("--------------@ 주소 비교후 처리 @-------------");
		
		System.out.println("-----------------@ 페이지 이동 @--------------");
		
		if(forward != null){ // 이동할 정보가 있다
			
			if(forward.isRedirect()){ // true - sendRedirect()
				// 가상주소(.bo -> .bo), 화면전환(주소변경,화면 변경)
				System.out.println("C : "+forward.getPath()+"주소로 이동(Redirect)");
				response.sendRedirect(forward.getPath());
				
			}else{ // false - forward()
				System.out.println("C : "+forward.getPath()+"주소로 이동(forward)");
				// 가상주소 -> 실제페이지 (.bo -> .jsp) + reqeust 객체 정보를 가지고 이동
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				
				dis.forward(request, response);			
			}
			
		}
		
		System.out.println("-----------------@ 페이지 이동 @--------------");
		
		
		
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet() 호출 (페이지 GET방식 호출)");
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost() 호출 (페이지 POST방식 호출)");
		doProcess(req, resp);
	}

}
