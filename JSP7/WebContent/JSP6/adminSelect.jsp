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
	<h1>WebContent/JSP6/adminSelect.jsp</h1>

	<!-- DB에 저장되어 있는 모든 회원의 정보를 출력 -->
    <h1> 회원 목록 확인 (관리자 기능)</h1>
	<%
		// 로그인 체크 + 관리자(admin)만 사용가능
		// 관리자가 아닌경우 로그인페이지로 이동

		String id = (String) session.getAttribute("id");

		if (id == null || !id.equals("admin")) {
			response.sendRedirect("loginForm.jsp");
		}

		// 가져온 회원 정보 모두를 테이블을 사용해서 출력	
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
		
		// 3. SQL (테이블에 있는 모든 정보를 가져오기)
		String sql = "select * from itwill_member";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		// 4. 실행
		ResultSet rs = pstmt.executeQuery();
		
		// 5. 데이터 사용
	%>
	<table border="1">
		<tr>
			<td>id</td>
			<td>pw</td>
			<td>name</td>
			<td>age</td>
			<td>gender</td>
			<td>email</td>
			<td>reg_date</td>
		</tr>
		
		
		<% 
		 while(rs.next()){
			 if(rs.getString("id").equals("admin")) 
				 continue;
		%>
			<tr>
				<td><%=rs.getString("id") %></td>
				<td><%=rs.getString("pw") %></td>
				<td><%=rs.getString("name") %></td>
				<td><%=rs.getInt("age") %></td>
				<td><%=rs.getString("gender") %></td>
				<td><%=rs.getString("email") %></td>
				<td><%=rs.getString("reg_date") %></td>
			</tr>
		<%
		 }
		%>
	</table>
	
	<a href="main.jsp">Main 페이지 이동</a>















</body>
</html>