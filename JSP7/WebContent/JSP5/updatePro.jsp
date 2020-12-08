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
	<h1>WebContent/JSP5/updatePro.jsp</h1>

	<%
	  // 한글처리
	  request.setCharacterEncoding("UTF-8");
	
	  //저장되는 파라미터값 받아오기
	  String name=request.getParameter("name");
	  String jumin=request.getParameter("ju1")+"-"+
	               request.getParameter("ju2");
	  
	  String gender = request.getParameter("gender");
	  int age = Integer.parseInt(request.getParameter("age"));
	
	  // 사용자가 입력 정보를 확인
	  // => 추후에 동작 추가
	  
	 final String DRIVER = "com.mysql.jdbc.Driver";
	 final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
	 final String DBID = "root";
	 final String DBPW = "1234";
	 
	  // 사용자 정보 수정
	  // 1. 드라이버 로드
	  Class.forName(DRIVER);
	  System.out.println("드라이버 로드 성공!");
	  // 2. 디비연결
	  Connection con = DriverManager.getConnection(DBURL,DBID,DBPW);
	  System.out.println("디비 연결 성공!");
	  // 3. SQL 작성 & pstmt 객체 생성
	  //  *  이름과 주민번호가 동일한 사람의 정보중 나이,성별을 수정
	  String sql = "update itwill_member set age=?, gender=? "
			       +" where name=? and jumin=?";
	  
	  PreparedStatement pstmt = con.prepareStatement(sql);
	  System.out.println("pstmt 객체 생성");
	  
	  // 3-1. ??값 추가
	  pstmt.setInt(1, age);
	  pstmt.setString(2, gender);
	  pstmt.setString(3, name);
	  pstmt.setString(4, jumin);
	  
	  System.out.println("? 값 추가");
			  
	  // 4. 실행	 
	  int value = pstmt.executeUpdate();
	  
	  if( value > 0 )
	    System.out.println("실행 완료 (수정완료) : "+value);
	  else
		System.out.println("실행 완료 (수정실패) : "+value);  
	  
	  // MYSQL 이동후 정보 수정 확인
	%>












</body>
</html>