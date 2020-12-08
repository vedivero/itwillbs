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
	<h1>WebContent/core/set1.jsp</h1>
	
	<%
	 // 자바 코드를 사용해서 변수 2개를 선언& 초기화
	 // 두 변수의 합을 출력(화면)
	 // => set1.jsp 페이지에서만 사용가능(단점)
	 int num1 = 100;
	 int num2 = 200;
	 int sum = num1 + num2;
	
	%>
	<%=sum %>
	
	<h2>JSTL 사용 변수 선언</h2>
	<!-- 변수 선언 -->
	<c:set var="num3" value="20"/>
	<c:set var="num4" value="50"/>
	
	<!-- 변수를 가져와서 연산을 처리할때 el 표현식 사용 -->
	<c:set var="result" value="${num3+num4}" />
	<!-- 변수의 값을 출력할때 el 표현식 사용 -->
	<c:out value="${result}"/><br>
	
	(html): ${num3 } + ${num4} = ${result }<br>
	(jstl): <c:out value="${num3 }"/> + <c:out value="${num4 }"/> 
	        =<c:out value="${result }"/><br>
	       
	        <c:out value="${num3 } + ${num4} = ${result }"/><br>
	         
	<hr>
	<h2>JSTL 사용해서 변수 선언시 특정 영역을 지정 가능</h2>
	<%-- <c:set var="name" value="홍길동" scope="page"/> --%>
	<!-- 기본 scope 지정없이 변수 선언하는 경우 page 영역에 지정  -->
	<c:set var="name" value="홍길동" scope="request"/>
	
	<!-- age : 20, email : itwill@naver.com 전달 (request)-->
	<!-- set2.jsp 페이지에서 나이,이메일 정보를 출력 -->
	<c:set var="age" value="20" scope="request" />
	<c:set var="email" value="itwill@naver.com" scope="request" />
	
	
	<!-- 페이지 이동시  request 영역의 값을 가지고 이동 -->
	<!-- 포워딩을 사용한 페이지 이동시 (request,response 객체를 가지고 이동) -->
	<!-- 이동하는 페이지의 주소가 변경 X (set1주소, set2 내용)  -->
	<jsp:forward page="set2.jsp" />
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>