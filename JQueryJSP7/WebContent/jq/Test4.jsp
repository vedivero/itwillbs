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
	  
	  // 선택한 요소의 속성을 생성,수정 하는 동작
	  // => attr(속성,값)
	  
	  // 이미지 태그에 들어가있는 src 속성값 출력
	  var src = $('img').attr('src');
	  
	  alert("이미지 파일 : "+src);	  
	  
	  // 이미지 태그에 파일의 경로를 변경(그림변경)
	  $('img').attr('src','6.jpg');
	  
	  // 태그 태두리(border)-5 지정
	  $('img').attr('border',5);
	  
	  // 태두리-> 5, 10, 15
	  $('img').attr('border',function(index){
		  //alert("index : "+index);
		  // 해당태그(선택요소)를 모두 접근하는 동작
		  // 그때 해당요소들을 순서(index)를 가진다.(0번 ~ )
		  return (index+1)*5;
	  });
	  
	  // 속성을 여러가지 적용 -attr()
	  // 태두리(border) - 5, 가로길이(width)-300, 세로길이(height)-150
	
	  $('img').attr({
		  border:5,
		  width:300,
		  height:150
	  });
	  
	  // 속성을 여러가지 적용 -attr()
	  // 태두리(border) - 5, 가로길이(width)-50,100,150
	  // 세로길이(height)-150
	  
	  $('img').attr({
		  border:5,
		  width:function(index){
			  return (index+1)*50;
		  },
		  height:150
	  });
	  
	  
  });



</script>


</head>
<body>
  <h1>WebContent/jq/Test4.jsp</h1>
  
  
  
  <img src="1.jpg">  
  <img src="2.jpg">  
  <img src="3.jpg">  
  
  
  
  
  
  
  
  
  
  
  
</body>
</html>