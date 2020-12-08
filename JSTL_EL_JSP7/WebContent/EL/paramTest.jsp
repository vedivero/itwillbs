<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>WebContent/EL/paramTest.jsp</h1>

	<%
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 각각의 정보저장후 출력 
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String msg1 = request.getParameter("msg");
		String msg2 = request.getParameter("msg");
	%>
	<%=id %><br>
	<%=pw %><br>
	<%=msg1 %><br>
	<%=msg2 %><br>
	<hr>
	<%
	 String[] str = request.getParameterValues("msg");
	%>
	<%=str[0] %>
	<%=str[1] %>
	<hr>
	<h1>체크 박스 결과</h1>
	<%
	 String[] arr = request.getParameterValues("hobby");
	%>
	<%-- <%=arr[0] %>
	<%=arr[1] %>
	<%=arr[2] %> --%>
	
	<%
	if(arr != null){
	 for(int i=0;i<arr.length;i++){
		 %>
		 <%=arr[i] %>
		 <%		 
	 }	
	}
	%>
	<h1> EL 표현식 사용 출력 </h1>
	 <!-- 
	 param 객체 : request.getParameter("이름")
     paramValues 객체 : request.getParameterValues("이름") 
      -->
      <!-- param 객체 -->
      <h3>아이디 : ${ param.id } </h3>
      <h3>비밀번호 : ${ param.pw }</h3>
      <!-- paramValues 객체 -->
      <h3>취미 : ${ paramValues.hobby[0] }</h3>
      <h3>취미 : ${ paramValues.hobby[1] }</h3>
      <h3>취미 : ${ paramValues.hobby[2] }</h3>
      
      <hr>
      <h2>JSTL 사용해서 반복 출력 </h2>
      <c:forEach var="tmp" items="${paramValues.hobby }">
       ${tmp }<br>
      </c:forEach>
      
      
      
	
	
	
	
	











</body>
</html>