<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/JSP1/TestPro3.jsp</h1>
	<h1>정보 출력페이지</h1>

	<%
		// 한글처리
		request.setCharacterEncoding("UTF-8");

		// 이름,성별,학년,취미 정보를 저장
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String grade = request.getParameter("grade");
		// 	   String hobby = request.getParameter("hobby");
		// 	   String hobby1 = request.getParameter("hobby");
		// 	   String hobby2 = request.getParameter("hobby");
		
		// 동일한 파라미터의 값을 가지는 데이터가 여러개 있을경우 
		// 배열을 통해서 저장
		String [] hobbyArr = request.getParameterValues("hobby");
		
	%>

	<h2>전달 정보 출력 !</h2>
	이름 :<%=name%><br>
	 성별 :<%=gender%><br>
	 학년 :<%=grade%><br>
	<%--  취미 1:<%=hobbyArr[0]%><br>
	 취미 2:<%=hobbyArr[1]%><br>
	 취미 3:<%=hobbyArr[2]%><br> --%>
	 <!-- 취미의 선택 결과에 따른 개수대로 출력  -->
	 <!-- 취미를 선택 안한경우 처리 -->
	<%
	   if(hobbyArr != null){
		   
		   for(int i=0;i<hobbyArr.length;i++){
			   %>
			     취미<%=i+1 %> : <%=hobbyArr[i] %><br>
			   <%
		   }
		   
	   }
	   
	%>	 
		 
	 
	 
	 













</body>
</html>