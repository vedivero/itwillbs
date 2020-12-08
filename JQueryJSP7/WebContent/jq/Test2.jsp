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
    // 자바스크립트 - 본문 실행전에 실행

   $(document).ready(function () {
	   // 제이쿼리 - 본문 실행후에 실행
	   
	   // 대상.동작메서드();
	   // 선택자 : 특정 동작이 적용될 대상
	   // 직접선택자
	   // [*]-전체, [태그]-해당태그요소, [#id]-id요소만,[.class]-class요소
	   
	   // * -> 모든 요소(전체)
	   $('*').css('color','green');
	   // 태그 -> 특정 태그만 
	   $('h1').css('color','blue');
	   // id값 -> 특정 요소중 id값에 해당하는 요소만
	   //$('#t1').css('color','red');
	   $('h1#t1').css('color','red');
	   // 클래스 값 -> 특정 요수중에 클래스 값만 
	   $('.c1').css('color','orange');
	   
	   ////////////////////////////////////////////////////////
	   //$('input').val('홍길동');
	   // 속성 탐색 선택자
	   // 요소[속성=값]
	   //$('input[type=text]').val('홍길동');
	   $('input[type=password]').val('1234');
	   // 요소[속성^=값] : 값으로 시작하는 요소를 찾아서 지정
	   $('input[type^=te]').val('홍');
	   // 요소[속성$=값] : 값으로 끝나는 요소를 찾아서 지정
	   $('input[type$=t]').val('테스트');
	   
	   /////////////////////////////////////////////////////////
	   // 위치 탐색 선택자
	   // 요소의 태그:first (첫번째)
	   // 요소의 태그:last (마지막)	   
	   $('tr:first').css('background','yellow');
	   $('tr:last').css('background','green');
	   
	   // 요소의 태그:odd (홀수)
	   // 요소의 태그:even (짝수)
	   // * 테이블 0번부터 시작
	   $('tr:odd').css('background','blue');
	   $('tr:even').css('background','orange');
	   
	   $('tr:first').css('background','yellow');
	   
	   
	   
	});
</script>


</head>
<body>
	<h1>WebContent/jq/Test2.jsp</h1>
	
	
	<h1 id="t1"> 제목1 </h1>
	<h1 class="c1"> 제목2 </h1>
	<h2> 제목3 </h2>
	<h1> 제목4 </h1>
	
	<hr>
	내용
	<hr>
	
	아이디 : <input type="text" name="id"> <br>
	비밀번호 : <input type="password" name="pass"><br>
	
	<hr>
	
	<table border="1">
	  <tr>
	    <td>이름</td>
	    <td>혈액형</td>
	    <td>지역</td>
	  </tr>
	  <tr>
	    <td>사용자1</td><td>A</td><td>부산</td>
	  </tr>
	   <tr>
	    <td>사용자2</td><td>B</td><td>서울</td>
	  </tr>
	   <tr>
	    <td>사용자3</td><td>O</td><td>대구</td>
	  </tr>
	   <tr>
	    <td>사용자4</td><td>AB</td><td>제주</td>
	  </tr>
	
	</table>
	
	
	
	
	
	
	
</body>
</html>