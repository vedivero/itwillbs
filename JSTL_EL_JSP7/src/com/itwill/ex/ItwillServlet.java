package com.itwill.ex;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 어노테이션 : 특정 기능, 특정 형태를 표현하는 방법
// @WebServlet("주소") : 클래스에 접근가능한 주소를 지정
@WebServlet("/itwill")
public class ItwillServlet extends HttpServlet {
	// 웹페이지 처리 가능

	// alt shift s v
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 페이지 처리 방식이 GET방식 일때 사용
	    System.out.println("doGet() 호출!");
	    
	    // 정보를 저장해서 이동 (request 내장객체에 저장)
	    // 변수 
	    request.setAttribute("name", "아이티윌");
	    
	    // 객체 
	    // ItwillBean 객체 생성후 전달
	    ItwillBean ib = new ItwillBean();
	    ib.setAge(20);
	    ib.setId("admin");
	    ib.setName("관리자");
	    ib.setPw("1234");
	    ib.setTel("010-1234-9876");
	    
	    request.setAttribute("ib", ib);
	    System.out.println("객체 정보 저장완료!");
	    
	    
	    // List 컬렉션 사용 - Vector 객체
	    Vector vec = new Vector();
	    
	    vec.add( ib );
	    
	    // set2.jsp 출력
	    // request 영역에 저장
	    request.setAttribute("vec", vec);
	    System.out.println(" 백터 저장 완료 !!! ");
	    
	    
	    
	    
	    // /itwill 주소 입력시 -> set2.jsp 화면을 보여주기

	    // RequestDispatcher 객체는 포워딩 처리 가능한 객체 
	    // request 내장 객체로 부터 전달받아서 사용
	    RequestDispatcher dis 
	       = request.getRequestDispatcher("/core/set2.jsp");// 이동할 주소

	    System.out.println("/core/set2.jsp 페이지로 포워딩! ");
	    // 포워딩 (request,response 가지고 이동)
	    dis.forward(request, response);
	    
	    
	    
	    
	    
	    
	}

}
