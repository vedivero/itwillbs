<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/EL/AttributeTest.jsp</h1>
	
	<%
	   
	    //int cnt = (int) request.getAttribute("cnt");
	    //Integer cnt = (Integer)request.getAttribute("cnt");
	
	    int cnt = 1000;
	%>
	<h2> 스크립트 엘리먼트  (변수) </h2>
	전달값 : <%=cnt +100%><br>
	
	<h2> EL 표현식 사용 cnt값 출력 (속성-attribute) </h2>
	${ cnt+100 } ( requestScope 생략 )<br>
	${ requestScope.cnt +500 }<br>
	
	<h2> requestScope(영역의 표시)없을경우 어떤 순서로 처리되는가? </h2>
	<h3>
	    영역의 표시를 생략할경우 모든 영역을 순차적으로 돌아다니면서 검색<br>
	    pageScope > requestScope > sessionScope > applicationScope
	        1             2              3               4
	        
	    1번 영역에서 해당 요소를 검색 -> 2번 ...->3번...->4번..    
	</h3>
	
	session : ${ sessionScope.cnt }<br>
	
	<h3> EL 표현식은 영역에 있는 속성(attribute) 출력 <br> 
	       영역을 지정하지 않을경우 모든 영역을 순차적 검색 <br>
	   *이전 영역에서 해당 값을 발견한 경우 다음 영역의 값을 사용할수 없음. 
	</h3>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>