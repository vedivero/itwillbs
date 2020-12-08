<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>WebContent/EL/operator2.jsp</h1>
	
		<h2>EL 표현식 사용해서 전달받은 정보 출력</h2>

         num1 : ${ param.num1} <br>
         num2 : ${ param.num2} <br>
         
         <h2> EL 표현식을 사용해서 전달받은 2개의 값 합 출력 </h2>
         <h3> EL 표현식은 전달받은 데이터 자동 캐스팅(연산시 자동으로 숫자) </h3>
         num1 + num2 : ${ param.num1 + param.num2 }<br>
         
         <h2> EL 표현식을 사용해서 전달받은 2개의 값이 모두 양수 인가? </h2>
         
         ${ (param.num1 >= 0) && (param.num2 >= 0) }
         
         <h2> EL 표현식 사용해서 삼항조건연산 처리</h2>
         <!-- 전달한 2개의 데이터가 같으면 "같다",다르면 " 다르다 " -->
         
         ${ param.num1 == param.num2? "같다":"다르다" }
         
         <h2> EL 표현식 사용(값이 null, 컬렉션의 크기가 0 - true)</h2>
         <!-- empty 값   => t/f리턴 
             operator1.jsp페이지에서 ID값 전달 ,
             ID가 없을경우 "geuest"님! 환영 합니다
             ID가 있을경우 "  id  "님! 환영 합니다
         -->
         ${ empty param.id? "geuest " : param.id }님! 환영 합니다 <br>
         
         

















</body>
</html>