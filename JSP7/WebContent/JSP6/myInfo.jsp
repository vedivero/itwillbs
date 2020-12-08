<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>WebContent/JSP6/myInfo.jsp</h1>

	<!-- 
        로그인한 회원 정보를 출력하는 페이지
        (비밀번호를 제외한 모든정보 출력)
    -->

	<%
		// 회원의 로그인 여부를 판단(로그인페이지로 이동)

		String id = (String) session.getAttribute("id");

		if (id == null) {
			response.sendRedirect("loginForm.jsp");
		}

		final String DRIVER = "com.mysql.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
		// 1. 드라이버 로드
		Class.forName(DRIVER);

		System.out.println(" 드라이버 로드 성공 ! ");

		// 2. DB연결
		Connection con 
		= DriverManager.getConnection(DBURL, DBID, DBPW);

		System.out.println(" 디비 연결 성공 ! ");

		// 3. SQL 문작성 (select) & pstmt
		String sql ="select * from itwill_member where id=?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		// ?
		pstmt.setString(1, id);
		
		// 4. 실행 -> 결과 저장 (rs)
		ResultSet rs = pstmt.executeQuery();
		// 5. 검색결과 사용하기 -> 테이블 생성후 추가
		
		//String id="";
		String name="";
		int age=0;
		String gender="";
		String email="";
		Timestamp reg_date=null;
		
		if(rs.next()){
			// DB정보를 가져오기 -> 화면에 출력
			// id = rs.getString("id");
			name = rs.getString("name");
			age = rs.getInt("age");
			gender = rs.getString("gender");
			email = rs.getString("email");
			reg_date = rs.getTimestamp("reg_date");
		}
		

		// 메인페이지로 이동 버튼/링크
	%>
	
	<table border="1">
	  <tr>
	     <td>아이디</td>
	     <td>이름</td>
	     <td>나이</td>
	     <td>성별</td>
	     <td>이메일</td>
	     <td>회원가입일</td>
	  </tr>
	  
	  <tr>
	     <td><%=id %></td>
	     <td><%=name %></td>
	     <td><%=age %></td>
	     <td><%=gender %></td>
	     <td><%=email %></td>
	     <td><%=reg_date %></td>
	  </tr>
	
	</table>
	
	
	<h3><a href='main.jsp'> Main 페이지로... </a></h3>















</body>
</html>