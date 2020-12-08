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
	<h1>WebContent/fmt/formatNumber.jsp</h1>
	
	
	<h2> 숫자 데이터를 원하는 형태로 출력 가능한 formatting 라이브러리 </h2>
	
	HTML 숫자 : 10000000<br>
	
	<!-- fmt:formatNumber 기본 속성으로 3자리마다 ,찍는다 (groupingUsed="true" 생략) -->
	fmt 숫자 : <fmt:formatNumber value="10000000" /> (기본값) <br>
	fmt 숫자 : <fmt:formatNumber value="10000000" groupingUsed="true"/><br>
	fmt 숫자 : <fmt:formatNumber value="10000000" groupingUsed="false"/><br>
	
	<h2> 3.1234643 소수점 두번째 자리 까지만 표현 </h2>
	<h2> pattern 속성 사용 숫자에 대응하는 문자는 #, 자리수는 . </h2>
	소수점 처리 : <fmt:formatNumber value="3.1234643" /> (기본값) <br>
	소수점 처리 : <fmt:formatNumber value="3.1234643" pattern="#.##" /><br>
	소수점 처리 : <fmt:formatNumber value="3.1234643" pattern="#.#######" /><br>
	소수점 처리 : <fmt:formatNumber value="3.1234643" pattern="#.##########" /><br>
	소수점 처리 : <fmt:formatNumber value="3.1234643" pattern="####.#" /><br>
	
	<h2> 30.1 (소수점 첫번째 자리) -> 30.10 두번째 자리까지 표시 </h2>
	
	소수점 처리 : <fmt:formatNumber value="30.1" pattern="##.##" /><br>
	소수점 처리 : <fmt:formatNumber value="30.1" pattern="##.000" />(자릿수 증가)<br>
	소수점 처리 : <fmt:formatNumber value="30.1" pattern="##.222" /><br>
	
	<h2> 퍼센트 (백분율) </h2>
	백분율 : <fmt:formatNumber value="0.5"  /> <br>
	백분율 : <fmt:formatNumber value="0.5" type="percent" /> <br>
	백분율 : <fmt:formatNumber value="0.55" type="percent" /> <br>
	
	<h2> 통화량(currency) </h2>
	통화량 : <fmt:formatNumber value="2000000000" type="currency" /> (기본값)<br>
	통화량 : <fmt:formatNumber value="2000000000" type="currency" currencySymbol="&"/> (기호변경)<br>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>