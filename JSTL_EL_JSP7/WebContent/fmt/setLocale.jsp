<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/fmt/setLocale.jsp</h1>
   
   <!-- 형식화 (formatting) -->
   
   <!-- JSTL-core 사용 현재 시간정보를 저장하는 변수  -->
   <c:set var="date" value="<%=new Date() %>"/>
   
   <c:out value="${date }"/>
   <hr>
   <fmt:formatDate value="${date }"/>
   
   <hr>
   <h2>우리나라</h2>
   <fmt:setLocale value="ko_kr"/>
      금액 : <fmt:formatNumber value="100000000000" type="currency"/><br>
      날짜 : <fmt:formatDate value="${date }" dateStyle="full"/>
   
   <h2>미국</h2>
   <fmt:setLocale value="en_us"/>
      금액 : <fmt:formatNumber value="100000000000" type="currency"/><br>
      날짜 : <fmt:formatDate value="${date }" dateStyle="full"/>
   
   <h2>일본</h2>
   <fmt:setLocale value="ja_jp"/>
      금액 : <fmt:formatNumber value="100000000000" type="currency"/><br>
      날짜 : <fmt:formatDate value="${date }" dateStyle="full"/>
   
   
   
   
   
   
   
   
   
   
   

</body>
</html>