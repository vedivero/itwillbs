<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/fmt/formatDate.jsp</h1>
   <h2> 날짜/시간 정보를 처리하는 formatting </h2>
   
   <h2> 현재 시간/날짜 정보 </h2>
   <c:set var="date" value="<%=new Date() %>" />
   
   ${date }<br>
   
     기본 포맷 : <fmt:formatDate value="${date }"/> <br>
   
     시간정보 : <fmt:formatDate value="${date }" type="time"/><br>
   
   날짜,시간정보 : <fmt:formatDate value="${date }" type="both"/><br>
   
   <hr>
   <h2>날짜 정보</h2>
   <fmt:formatDate value="${date }"/> <br>
   <fmt:formatDate value="${date }" dateStyle="full"/> <br>
   <fmt:formatDate value="${date }" dateStyle="long"/> <br>
   <fmt:formatDate value="${date }" dateStyle="medium"/> <br>
   <fmt:formatDate value="${date }" dateStyle="short"/> <br>
   
   <h2>시간 정보</h2>
   <fmt:formatDate value="${date }" type="time"/> <br>
   <fmt:formatDate value="${date }" timeStyle="full" type="time"/> <br>
   <fmt:formatDate value="${date }" timeStyle="long" type="time"/> <br>
   <fmt:formatDate value="${date }" timeStyle="medium" type="time"/> <br>
   <fmt:formatDate value="${date }" timeStyle="short" type="time"/> <br>
   
   
   <!-- 
    2020년 8월 12일 (수) 오후 4시 40분 00초
    -->
    <hr>
    <fmt:formatDate value="${date }" type="both" dateStyle="long" 
       timeStyle="long"
    />
    <!--
       2020/8/12(수) 
       yyyy/MM/dd
      -->
     <fmt:formatDate value="${date }" pattern="yy/MM/dd(E)"/> <br>
     <fmt:formatDate value="${date }" pattern="yy-MM-dd(E)"/> <br>
     
     <!-- 
            (오후) 05:00:00 
      -->
      
      <fmt:formatDate value="${date }" type="time" pattern="(a)hh:mm:ss"/><br>
     
   
   
   
   
   
</body>
</html>