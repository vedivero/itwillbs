<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 컴퓨터에 있는 제이쿼리 라이브러리 추가  -->
<script src="../js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">
   // 자바스크립트 사용해서 입력값이 없을경우 페이지 전송 X
   
   function check(){
	   //var v = document.fr.txt.value;
	   var v = document.getElementById("data").value;
	   alert(v);
	   
	   if(v == ""){
		   alert(" 데이터를 입력하시오 ");
		   document.fr.txt.focus();
		   return false;
	   }
   }
   
   $(document).ready(function(){
	   
	   //$('#fr2').submit(false);
	   $('#fr2').submit(function(){
		   //alert("제이쿼리 사용 폼태그 제어");
		   var data = $('#data2').val();
		   alert(data);
		   
		   if(data == ""){
			   $('#data2').focus();
			   return false;		   
		   }
		   
	   });
	   
	   
   });
   
   
   


</script>
</head>
<body>
	<h1>WebContent/jq2/Test4.jsp</h1>
	
	<form action="a.jsp" method="get" id="fr" name="fr" onsubmit="return check()">
		데이터 : <input type="text" name="txt" id="data">
		<input type="submit" value="전송">	
	</form>
	
	<form action="b.jsp" method="get" id="fr2" >
		데이터 : <input type="text" name="txt2" id="data2">
		<input type="submit" value="전송2">	
	</form>
	
	
	
	
	
	

</body>
</html>