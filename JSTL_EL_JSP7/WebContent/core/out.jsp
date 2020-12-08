<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!--
       JSTL을 사용하기 위해서 해당 라이브러리가 있는 위치를 지정
       prefix를 C 지정(일반적으로 core-c)
       *반드시 태그라이브러리 추가 해야지만 사용가능
      -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- 컴퓨터에 있는 제이쿼리 라이브러리 추가  -->
<script src="../js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		$('body').append("4. Jquery 사용 출력");
	});


</script>

</head>
<body>

   <h1>WebContent/core/out.jsp</h1>
 
    1. html 사용 출력<br>
   <%
     // 2. out.print() 사용
     out.print("2. out.print() 사용<br>");
   %>
   <%=" 3.표현식 사용 출력<br>" %>
   
   ${"5.el 표현식 사용 출력<br>" }
   
   <c:out value="6.JSTL 사용해서 출력"/><br>
   <c:out value="${'7.JSTL 안에서 사용하는 EL 표현식'}"/><br>
   
   <!-- 
     c:out =화면에 value 값을 출력하는 기능(태그)   
    -->
    <hr><hr>
    
    <c:out value="10+20"/><br>
    <c:out value="${ 10+20 }"/><br>
    
    <!-- 값이 null 일경우 빈공백으로 출력
         빈 공백을 default 속성을 사용해서 특정문자 출력
     -->
    <c:out value="${itwill.name }"/><br>
    <c:out value="${null }"/><br>
    <c:out value="${null }" default="널값 대신해서 출력합니다."/><br>
    <c:out value="${null }" default="${ '안녕' }"/><br>
    
    <hr>
    
    <itwill>태그는 itwill 태그 입니다<br>
    
    &lt;itwill> 태그는  itwill태그 입니다.<br>
    
    <c:out value="<itwill>태그는 itwill 태그 입니다."/><br>
    
    
    
 
 
 
 
 
 

</body>
</html>