<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/EL/BeanTest.jsp</h1>
   
   <h3> TestServlet에서 전달한 객체 정보 </h3>
	   이름 : ${ requestScope.itwillBean.name }<br>
	   아이디 : ${ requestScope.itwillBean.id }<br>
	   비밀번호 : ${ itwillBean.pw }<br>
	   나이 : ${itwillBean.age }<br>
	   전화번호 : ${itwillBean.tel }<br>
   
   <h3> 전달받은 ItwillUser 정보 </h3>
     이름 : ${ requestScope.kim.name }<br>
     나이 : ${ requestScope.kim.age }<br>
   전화번호 : ${ requestScope.kim.note20.tel }<br>
   모델 : ${ kim.note20.model }<br>
   
   
   
   
   
   
   
   
   
   
   
   
   
   

</body>
</html>