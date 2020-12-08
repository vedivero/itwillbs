<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/JSP1/TestForm2.jsp</h1>
	<!-- 사용자의 정보를 입력받아서 출력(form) -->
	<!-- TestForm2(입력) TestPro2.jsp(출력) -->
	<!-- 사용자의 이름,나이,사는곳 입력 -->
	
	<fieldset>
	  <legend>정보 입력페이지</legend>
	  
	  <form action="TestPro2.jsp?itwill=busan" method="post" name="fr" onsubmit="return fun1()">
		    이름 : <input type="text" name="name"><br>
		    나이 : <input type="text" name="age"><br>
		    사는곳 : <input type="text"	 name="addr"><br> 
		    <input type="submit" value="전송"> 
	  </form>
	
	</fieldset>
	<!-- 
	   자바스크립트를 사용해서 
	   이름,나이,사는곳을 모두 입력했을때만 전송
	   입력이 안된경우 전송X, 해당요소 focus()
	 -->
	<script type="text/javascript">
	   function fun1(){
		  // alert(document.fr.name.value);
		  if(document.fr.name.value == ""){
			  alert(" 이름을 입력하시오 ");
			  document.fr.name.focus();
			  return false;
		  }
		  if(document.fr.age.value == ""){
			  alert(" 나이를 입력하시오 ");
			  document.fr.age.focus();
			  return false;
		  }
		  if(document.fr.addr.value == ""){
			  alert(" 사는 곳을 입력하시오 ");
			  document.fr.addr.focus();
			  return false;
		  }
		  
		  
		  
	   }
	
	
	</script>
	
	
	
	
	
	

</body>
</html>