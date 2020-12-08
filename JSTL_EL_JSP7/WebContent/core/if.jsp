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
  	<h1>WebContent/core/if.jsp</h1>
  
    <!-- JSTL을 사용해서 변수 생성 -->
    <c:set var="i" value="itwill"/>
    <!-- 변수값 출력 -->
    <c:out value="${ i }"/>
  
    <!-- JSTL을 사용한 IF문 -->
   <%--  <c:if test="${조건문}">
       실행문장
    </c:if> --%>
    
    <!-- 변수 i에 들어있는 값이 itwill 일때, "아이티윌 입니다" 출력 -->
    <c:if test="${i=='itwill' }" >
           아이티윌 입니다.
    </c:if>
    
    <!-- 
       form 태그 사용해서 숫자 2개를 입력 -> submit -> if.jsp페이지로 전달
             전달된 2개의 숫자중에서 더 큰수를 구분해서 출력           
     -->
    <fieldset>
      <legend>큰수 찾기</legend>
      <form action="if.jsp">
              숫자 1 : <input type="text" name="num1"><br>
              숫자 2 : <input type="text" name="num2"><br>
       <input type="submit" value="큰수 찾기">      
      </form>    
    </fieldset>
    
    <hr>
    <%
       System.out.println(request.getParameter("num1"));
       System.out.println(request.getParameter("num2"));
    %>
    
    <!-- 파라미터값도 바로 접근해서 사용가능 (연산가능-숫자) -->
    <c:out value="${ param.num1 +100 }"/> <br>
    <c:out value="${ param.num2 +100 }"/> <br>
    
    <c:if test="${ 123 > 22 }" var="x">
           큰값 : ${ x }
    </c:if>
    
    
    <c:if test="${ param.num1 > param.num2 }">
           큰값 : ${ param.num1 }
    </c:if>
     
    <c:if test="${ param.num2 > param.num1 }">
           큰값 : ${ param.num2 }
    </c:if>
    
    <!-- 입력받은값 num1,num2 같으면서 
         num1 null 아닌경우
     "동일한 값!"   p517 el 연산자 -->
    <c:if test="${ (param.num1 == param.num2) and (param.num1 ne null)  }">
            <h2>동일한 값!</h2>
    </c:if>
    
    
    
    
    
    
    
  
  
  
  
  
  
  
  
  

</body>
</html>