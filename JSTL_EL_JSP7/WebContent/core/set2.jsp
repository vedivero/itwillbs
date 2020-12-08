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
  <h1>WebContent/core/set2.jsp </h1>
  
  <%
    //request 영역에 저장된 값을 받아서 출력
    //String name = (String) request.getAttribute("name");
  %>
   
  <%--  <h2>set1.jsp에서 전달된 name : <%=name %></h2> --%>
  
  <!-- requestScope : el 표현식에서 사용되는 내장 객체 -->
   <h3>이름 : ${name }</h3>
   <h3>이름 : ${requestScope.name }</h3>
   <!-- 영역 객체 정보는 생략 가능하다 
       page,request,session,application 영역을 모두 찾아서 사용
        -> 순서대로 검색
   -->
   <h3>나이 : ${ requestScope.age }</h3>
   <h3>이메일 : ${ email } </h3>
   
   <hr>
   <h2> ItwillServlet에서 정보 전달 처리 </h2>
   
   <h2>ItwillBean 객체 정보 출력</h2>
   <!-- http://localhost:8088/JSTL_EL_JSP7/itwill -->
   
   <!-- JSTL 사용 변수 지정 -->
   <c:set var="itwill" value="${ requestScope.ib }"/>
   <!-- requestScope 영역의 값을 호출 -->
   <h3>ID : ${requestScope.ib.id } </h3>
   <h3>PW : ${requestScope.ib.pw }</h3>
   <!-- requestScope 생략후 호출 -->
   <h3>NAME : ${ ib.name }</h3>
   <!-- JSTL 사용 변수에 저장후 호출 -->
   <h3>AGE : ${ itwill.age }</h3>
   <h3>TEL : ${ itwill.tel }</h3>
   
   <hr>
   <h2> 백터에 저장된 결과를 출력 </h2>
   
 <%--   <h3>ID : ${ requestScope.vec.ib.id }</h3> --%>
   <h3>ID : ${ requestScope.vec[0].id }</h3>
   <h3>PW : ${ requestScope.vec[0].pw }</h3>
   <h3>NAME : ${ vec[0].name }</h3>
   
   <!-- 백터 전체를 저장해서 사용  -->
   <c:set var="v" value="${ requestScope.vec }" />
   <h3>AGE : ${v[0].age }</h3>
   <!-- 백터 요소중 하나만 저장해서 사용 -->
   <c:set var="v0" value="${ requestScope.vec[0] }" />
   <h3>TEL : ${ v0.tel }</h3>
   
   <hr>
   <!-- 특정 영역에 저장된 값을 삭제  -->
   <c:out value="<c:remove /> 사용해서 값 삭제"/>
   
   <!-- request 영역에 있는 name 값을 삭제  -->
   <c:remove var="name" scope="request"/>
   
   <h2> 이름 : ${name }</h2>
   
   
   
   
  
  
  
  
  
  
  
  
  
  
  
  
  
  

</body>
</html>