<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/JSP3/sessionInval.jsp</h1>
	<h2>세션 초기화 페이지</h2>
	
	<%
	   // 세션 초기화
	   session.invalidate();
	
	   // 페이지 이동
	   //response.sendRedirect("sessionTest.jsp");
	
	%>
	<script type="text/javascript">
	   alert("세션값 초기화");
	   location.href="sessionTest.jsp";	
	</script>
	
	










</body>
</html>