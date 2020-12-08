<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- 컴퓨터에 있는 제이쿼리 라이브러리 추가  -->
<script src="../js/jquery-3.5.1.min.js"></script>
<!-- innerfade 라이브러리(플러그인 추가) -->
<script src="../js/jquery.innerfade.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
    	$("#news").innerfade({
    		animationtype: "fade",
    		speed:750    		
    	});
    });
</script>


</head>
<body>

     <ul id="news"> 
       <li><img src="1.jpg"></li>
       <li><img src="2.jpg"></li>
       <li><img src="3.jpg"></li>
     </ul>


</body>
</html>