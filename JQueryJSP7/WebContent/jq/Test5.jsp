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
    	// html() : 선택한 요소의 값을 리턴, 속성변경
    	// text() : 선택한 요소의 테스트값을 리턴, 텍스트값 변경
    	
    	// html() 사용해서 h2 태그의 정보를 출력
    	var h = $('h2').html();
    	alert("html() 값 : "+h);
    	// => 가장 먼저 나오는 요소의 값 리턴
    	
    	var t = $('h2').text();
    	alert("text() 값 : "+t);
    	// => 해당하는 모든 요소의 값을 리턴
    	/////////////////////////////////////////////////////
    	// text() 사용해서 div태그에 값을 변경
    	//$('div').text('안녕');
    	//$('div').text('<h3>text() 사용중!! </h3>');
    	// => text() : 태그 요소가 출력되도 그대로 문자로 출력
    	
    	// html() 사용해서 div태그에 값을 변경
    	//$('div').html('안녕');
    	//$('div').html('<h3>html() 사용중!! </h3>');
    	// => html() : 태그 요소가 출력되면 해당 태그가 적용되서 사용
    	
    	
    	// 페이지 실행시 제목에 링크를 걸어서 자신페이지 호출
    	
    	// 제목을 읽어와서 저장
    	var value = $('h1').text();
    	alert("value : "+value);
    	// 태그 요소로 입력
    	$('h1').html("<a href='#'>"+value+"</a>");
//     	$('h1').text("<a href='#'>"+value+"</a>");
    	
    	// div에 기존의  내용을 가져와서 + 해당 요소의 인덱스를 추가 후 화면 출력
    	// 00 => [ 기존값 ***** 인덱스 ***** ]
    	$('div').html(function(index,html){
    		//alert("index : "+index+"\n html:"+html);
    		return html+" ***** "+index+" ***** ";
    	});
    	
    	
    });
  
  </script>

</head>
<body>
	<h1>WebContent/jq/Test5.jsp</h1>
	
	<h2> head - 0 </h2>
	<h2> head - 1 </h2>
	<h2> head - 2 </h2>
	<hr>
	
	<div>00</div>
	<div>11</div>
	<div>22</div>
	
	
	
	

</body>
</html>