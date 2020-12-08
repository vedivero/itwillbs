package com.itwill.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

public class TestServlet extends HttpServlet {

	//http://localhost:8088/JSTL_EL_JSP7/test (get)
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        // 페이지 요청시 GET방식일때 처리 되는 메서드
		
		System.out.println("doGet() 호출");
		
		// 서블릿 페이지 -> jsp페이지로 정보 전달해서 출력
		// request 영역에  cnt 값 100 저장해서 전달 후
		// AttributeTest.jsp 페이지에서 출력
		//request.setAttribute("cnt", 100);
		
		
		// session 영역에 cnt값 500 저장해서 전달 후 출력
		// AttributeTest.jsp 페이지에서 출력
		
		// Java 페이지에서는 session 내장객체 없음.
		
		HttpSession session = request.getSession();
		session.setAttribute("cnt", 1000);
		
		
		
		/*PrintWriter pr = response.getWriter();
		pr.write("<html>");
		pr.write("<head>");
		pr.write("</head>");
		pr.write("<body>");
		pr.write("<h1> Hello Servlet Test.</h1>");
		pr.write("</body>");
		pr.write("</html>");
		pr.close();*/
		
		// 특정 페이지로 이동 - forward
		RequestDispatcher dis =
				request.getRequestDispatcher("./EL/AttributeTest.jsp");
		
		dis.forward(request, response);
		
		
		}
  
}
