package com.itwillbs.basket.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BasketFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doProcess() !");

		System.out.println("--------Controller 진입-------------");

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

		System.out.println("--------------@ 주소 비교 @--------------");
		Action action= null;
		ActionForward forward = null;
		
		if(command.equals("/BasketAdd.ba")){
			// 장바구니 추가
			// .jsp -> DB
			System.out.println("C : /BasketAdd.ba");
			// BasketAddAction() 객체 생성
			action = new BasketAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		else if(command.equals("/BasketList.ba")){
			// DB 정보를 가져와서 jsp 페이지 출력
			System.out.println("C : /BasketList.ba 호출");
			// BasketListAction 객체 생성
			action = new BasketListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}else if(command.equals("/BasketDelete.ba")){
			System.out.println("C : /BasketDelete.ba 호출");
			
			// ./ba -> DB(삭제) -> ./ba
			// BasketDeleteAction() 객체 생성
			action = new BasketDeleteAction();
			
			try {
			   forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		System.out.println("--------------@ 주소 비교 @--------------");

		System.out.println("--------------@ 주소 이동 @--------------");
		if (forward != null) { // 이동할 정보가 있다

			if (forward.isRedirect()) { // true - sendRedirect()
				// 가상주소(.bo -> .bo), 화면전환(주소변경,화면 변경)
				System.out.println("C : " + forward.getPath() + "주소로 이동(Redirect)");
				response.sendRedirect(forward.getPath());

			} else { // false - forward()
				System.out.println("C : " + forward.getPath() + "주소로 이동(forward)");
				// 가상주소 -> 실제페이지 (.bo -> .jsp) + reqeust 객체 정보를 가지고 이동
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());

				dis.forward(request, response);
			}

		}

		System.out.println("--------------@ 주소 이동 @--------------");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet() !");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost() !");
		doProcess(request, response);
	}

	

}
