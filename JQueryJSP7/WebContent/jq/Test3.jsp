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
	
	  // h2 태그의 속성값을 가져오기
	  var c = $('h2').css('color');
	  alert("변경전 : "+c); // rgb(0,0,0)-검정
	  //console.log(c);
	  // h2태그의 글자색을 변경
	  $('h2').css('color','red');
	  var c1 = $('h2').css('color');
	  alert("변경후 : "+c1);
	  
	  //////////////////////////////////////////////////
	  // 해당 요소의 속성을 한번에 돌아가면서 변경 가능
	  // 컬러정보를 저장하는 배열 준비
	  var col = ['red','green','blue','orange'];
	  $('h2').css('color',function(index){
		  //alert(index+"!!!!"); // 0,1,2,.....
		  return col[index];
	  });
	  
	  ////////////////////////////////////////////////////
	  // 속성을 여러개 사용 
	  // => 중괄호를 사용해서 여러개 동시에 처리 
	  
	  $('h2').css({
		  color:'red',
		  backgroundColor: "black"
	  });
	  
	  // 속성을 한번에 처리 
	  //  글자색 - 검색 & 배경색 - col배열 사용 각각 지정
	  
	  $('h2').css({
		  color : 'black',
		  backgroundColor: function(index){
			  return col[index];
		  }
	  });
	  
  });
	
</script>


</head>
<body>
	<h1>WebContent/jq/Test3.jsp</h1>

    <h2> head-0 </h2>
    <h2> head-1 </h2>
    <h2> head-2 </h2>
    <h2> head-3 </h2>






</body>
</html>