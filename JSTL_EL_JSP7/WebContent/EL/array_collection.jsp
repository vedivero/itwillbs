<%@page import="java.util.ArrayList"%>
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
  <h1>WebContent/EL/array_collection.jsp</h1>
  
  <h2> 전달된 음식 정보 </h2>
  <%
    // 자바코드 사용 페이지 출력
    String[] food = (String[]) request.getAttribute("food");
    
    out.print(food+"<br>");
    
    for(int i=0;i<food.length;i++){
    	out.print(food[i]+"<br>");
    }
  %>
    <%=food[0] %> 
    <!-- EL표현식 (null값일때 공백문자) -->
    <h2>EL 표현식(반복문x)</h2>
    ${ requestScope.food[0] }
    ${ requestScope.food[1] }
    ${ requestScope.food[2] }
    
    <h2>JSTL 반복문 사용 출력</h2>
    
    <c:forEach var="f" items="${food }">
       ${f }<br>
    </c:forEach>
    
  <hr>
  
  <h2> ArrayList 정보 출력하기 </h2>
  
  <%
    // JAVA
    ArrayList<String> movie 
       = (ArrayList<String>) request.getAttribute("movie");
  
   for(String m : movie){
	   out.print("movie : "+m+"<br>");
   }
  %>
    <!-- JSP 표현식 -->
    <%=movie.get(0) %><br>
    
    <!-- EL 표현식 -->
    ${ requestScope.movie[0] }<br>
    <!-- JSTL 사용 -->
    
    <c:forEach var="m" items="${movie }">
       ${m }<br>
    </c:forEach>
  
  
  
  
  
  
  
  
  
  
  
  
  

</body>
</html>