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
      // setInterval("스크립트 실행문",시간(밀리세컨드1/1000))
      // => 일정 시간마다 코드를 실행 가능
      
      // CDATA : (UnParsed Character DATA)
      // => 코드가 태그를 포함하거나, 연산(부등호)을 포함할때
      //  일반 문자로 인식하도록 처리 ( 주로  '>' 부등호 사용시 )
      // PCDATA : (Parsed Character DATA)
      // => 코드가 태그를 포함하거나, 연산(부등호)을 포함할때
      //  코드 그대로 인식하도록 처리( 실제 태그 사용 )
      
      //<![CDATA[
      var i=0;
      var s = setInterval("console.log(i++)",1000);
      
      //]]>
      
      // clearInteval(실행중인 객체 정보)
      // => 작업 종료
      //clearInterval(s);
      
      $(document).ready(function() {
		   
    	  // setInterval() 사용 
    	  // 이미지 위치 변경
    	  
    	  // 이미지 태그의 가로길이 250 지정
    	  $('img').css('width',250);
    	  
    	  setInterval(function(){
    		  $('body').append($('img').first());
    	  },20);
    	  
    	  
    	  
    	  
    	  
    	  
	   });
  
  
  </script>

</head>
<body>
<!-- 
  <h1>WebContent/jq/Test7.jsp</h1>
  
  <input type="button" value="정지" onclick="clearInterval(s);">
   -->
   
   <img src="1.jpg">
   <img src="2.jpg">
   <img src="4.jpg">
   <img src="6.jpg">
   <img src="3.jpg">
   <img src="5.jpg">
   
  
  
  

</body>
</html>