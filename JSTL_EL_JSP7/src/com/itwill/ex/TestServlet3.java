package com.itwill.ex;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("주소") : 어노테이션-해당 클래스가 서블릿 처럼 사용되도록지정
// -> web.xml 파일에 매핑했던 정보 없이 바로 지정한 주소로 접근 가능

@WebServlet("/test3")
public class TestServlet3 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
		System.out.println("/test3 주소 호출 - doGet() 호출");
		
		// 배열
		String[] Food = {
			"햄버거","라면","삼겹살","치킨"
		};
		
		// request 영역에 배열을 저장
		req.setAttribute("food", Food);
		
		// 컬렉션
		ArrayList<String> movie = new ArrayList<String>();
		movie.add("어벤져스");
		movie.add("겨울왕국");
		movie.add("아이언맨");
		movie.add("부산행");
		movie.add("국제시장");
		
		req.setAttribute("movie", movie);
		
		
		
		
		
		
		// 화면 페이지 array_collection.jsp 페이지 이동
		// forward 사용
		
		RequestDispatcher dis 
		= req.getRequestDispatcher("/EL/array_collection.jsp");
		dis.forward(req, resp);
		
		
	}
	

}
