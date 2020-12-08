<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
   .high_0{background: yellow;}
   .high_1{background: orange;}
   .high_2{background: blue;}
   .high_3{background: red;}
   .high_4{background: green;}
</style>



<!-- 컴퓨터에 있는 제이쿼리 라이브러리 추가  -->
<script src="../js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		// addClass()
		// h2 태그마다 클래스 아이디를 지정(0,1,2,3,4,....)
		
		// each()-> 순차적 접근 함수
		$('h2').each(function(index){
			//alert("index : "+index);
			// 클래스 아이디를 지정
			$(this).addClass("high_"+index);
		});
		
		// this : 사용자가 페이지 방문시 등록된 이벤트를 처리할때마다
		// 해당 발생 요소를 가리키는 레퍼런스(참조변수)
		
		
		

	});
</script>


</head>
<body>
	
		<h1>WebContent/jq/Test8.jsp</h1>
	
	<!-- 	<h2 class="high_0">item-0</h2>
		<h2 class="high_1">item-1</h2>
		<h2 class="high_2">item-2</h2>
		<h2 class="high_3">item-3</h2>
		<h2 class="high_4">item-4</h2> -->
		<h2>item-0</h2>
		<h2>item-1</h2>
		<h2>item-2</h2>
		<h2>item-3</h2>
		<h2>item-4</h2>



</body>
</html>