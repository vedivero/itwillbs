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
	<h1>WebContent/core/choose.jsp</h1>
	
	<!-- switch-case 구문 유사함  -->
	
	<c:set var="num1" value="0" />
	
	<!-- 양수일때, 음수일때 둘다 아닐때 -->
	<c:choose>
	  <c:when test="${ num1 > 0 }">
	      양수! <br>
	  </c:when>
	  <c:when test="${ num1 < 0 }">
	      음수! <br>
	  </c:when>
	    <c:otherwise>
	       0~! <br>
	    </c:otherwise>
	</c:choose>
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>