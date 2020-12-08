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
	<h1>WebContent/JSP6/deletePro.jsp</h1>

	<%
		// 세션값 (로그인 체크)
		String id = (String) session.getAttribute("id");

		if (id == null) {
			response.sendRedirect("loginForm.jsp");
		}

		// ID,PW 정보 전달받아서 저장
		//id = request.getParameter("id");
		String pw = request.getParameter("pw");

		// DB 연결해서 -> ID,PW에 해당하는 정보가 있을때만 삭제
		final String DRIVER = "com.mysql.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
		// 1. 드라이버 로드
		Class.forName(DRIVER);

		System.out.println(" 드라이버 로드 성공 ! ");

		// 2. DB연결
		Connection con = DriverManager.getConnection(DBURL, DBID, DBPW);

		System.out.println(" 디비 연결 성공 ! ");

		// 3. SQL
		String sql = "select pw from itwill_member where id=?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, id);

		// 4. 실행
		ResultSet rs = pstmt.executeQuery();

		// 5. 
		if (rs.next()) {
			if (pw.equals(rs.getString("pw"))) {
				// 3. 
				sql = "delete from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);

				// 4.
				pstmt.executeUpdate();
				
				// 삭제시 세션의 정보도 삭제(로그인정보)
				session.invalidate();

				// 페이지 이동
				//response.sendRedirect("main.jsp");
				%>
				  <script type="text/javascript">
				     alert(" 회원 탈퇴 ! ");
				     location.href="main.jsp";				     
				  </script>			
				<%	
			} else {
				// 비밀번호 다름
			%>
			<script type="text/javascript">
				alert(" 비밀번호 오류! 수정불가 ! ");
				history.back();
			</script>
			<%
		}
		} else {
			// 아이디 없음
			%>
			<script type="text/javascript">
				alert(" 아이디 없음! 수정불가 ! ");
				history.back();
			</script>
			<%
		}

		// 삭제 완료시 main.jsp 페이지로 이동
	%>












</body>
</html>