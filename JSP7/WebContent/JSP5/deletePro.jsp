<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/JSP5/deletePro.jsp</h1>

	<%
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 전달되는 파라미터값 저장
		String name = request.getParameter("name");
		String jumin = request.getParameter("jumin");

		final String DRIVER = "com.mysql.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";

		// 1. 드라이버 로드
		Class.forName(DRIVER);
		System.out.println("드라이버 로드 성공");
		// 2. 디비 연결
		Connection con =
		DriverManager.getConnection(DBURL,DBID,DBPW);
		System.out.println("디비 연결 성공");
		// 3. SQL 구문작성 & pstmt 객체 생성
		String sql = "delete from itwill_member "
		             +"where name=? and jumin=?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		// 3-1. ?? 값 추가
	    pstmt.setString(1, name);
		pstmt.setString(2,jumin);
				
		// 4. 실행
		pstmt.executeUpdate();
		
		System.out.println("삭제구문 실행 완료");
		
	%>


















</body>
</html>