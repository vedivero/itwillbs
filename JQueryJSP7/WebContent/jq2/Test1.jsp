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
   $(document).ready(function(){
	   // bind("이벤트 종류",기능) : 선택한 요소에 이벤트 여러개 등록
	   $('input').bind("click mouseover",function(){
		   alert("제이쿼리 클릭!");
	   });
	   $('input').click(function(){
		   alert("제이쿼리(click())");
	   });
	   
	   // h2 태그 클릭시 alert창 출력
	   // -> h2태그 클릭시 해당요소에만 + 문자 추가 
	   $('h2').click(function(){
		   //alert(" h2 클릭 !! ");
		   // this-> 클릭 이벤트가 발생한 h2태그
		   $(this).html(function(index,text){
			   //alert(text);
			   return text+"+";
		   });
	   });
	   
	   
	   
	   
   });
</script>
</head>
<body>

   <h1>WebContent/jq2/Test1.jsp</h1>
   
   <input type="button" value="버튼(자바스크립트 사용)" onclick="alert('클릭!!')">
   
   
   <h2> head-0 </h2>
   <h2> head-1 </h2>
   <h2> head-2 </h2>
   
   
   
   
   
   
   
   
   
   
   
   
   


</body>
</html>