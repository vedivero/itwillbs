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
   // 요소를 추가 - 앞:prepend() 뒤:append()
	   
   // 본문에 뒤쪽에 "h2- append()사용 추가"
   $('body').append("<h2> append()사용 추가 </h2>");
   // 본문 앞쪽 추가
   $('body').prepend("<h2> prepend()사용 추가 </h2>");
   
   // div 태그에 값 추가
   $('div').append("ITWILL");
   
   // div 태그에 순서(index)를 추가
//    $('div').append(function(index){
// 	    //alert("index : "+index);
// 	    return index;
//    });
   
   $('div').prepend(function(index){
	    //alert("index : "+index);
	    return index;
  });
   
   
	   // 배열
	   var arr = [
		   {name:"홍길동",region:"부산"},
		   {name:"사용자2",region:"대구"},
		   {name:"사용자1",region:"서울"}	   
	   ];
	   
	   alert("arr[0] : "+arr[0]);
	   
	   // each() / $(요소선택자).each()
	   // => 선택한 여러개의 요소를 순차적으로 접근할때 
	   
	   // 배열의 값을 각각 접근해서 태이블 행에 추가
	   // 태이블 태그에 배열의 값을 추가 - append()
	   
	   $.each(arr,function(index,item){
		   // index-위치, item-요소의 값
		   //alert("확인");
		   //alert("index : "+index+" / item : "+item.name);
		   
		   // 테이블에 추가 
		   $('table').append("<tr><td>"+item.name+"</td><td>"+item.region+"</td></tr>");
	   });
	   
	   // div - 3개 배열의 값 3개를 각각 하나씩 저장

	   $('div').append(function(index){
		   var item = arr[index];
		   
		   return item.name+" / "+item.region;		   
	   });
	   
	   
	   
	   
	   
	   });
</script>


</head>
<body>

	<h1>WebContent/jq/Test6.jsp</h1>
	<hr>
	<h2> 안녕하세요 JQuery 입니다. </h2>
    <hr>
    
    <div> 내용  </div>
    <div> 내용  </div>
    <div> 내용  </div>

    <hr>
    <table border="1">
      <tr>
        <td>이름</td><td>지역</td>
      </tr>
    
    </table>











</body>
</html>