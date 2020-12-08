<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/JSP4/cookieDel.jsp</h1>
   
   <h2> 쿠키값 삭제 페이지 </h2>
   
   <%
     // 쿠키값 정보를 가져온다(배열)
     Cookie[] cookies = request.getCookies();
     
     // 쿠키값이 있을때만 삭제 동작을 진행
     if(cookies != null){
    	 // 쿠키 배열의 처음부터 끝까지 비교
    	 for(int i=0;i<cookies.length;i++){ 
    		// 쿠키이름이 "name"인 대상을 찾아서 삭제
    		if(cookies[i].getName().equals("name")){
    			// 삭제
    			cookies[i].setMaxAge(0);
                // 변경된 쿠키 정보를 다시 클라이언트 쪽으로 전달  
                response.addCookie(cookies[i]);
    		}            
    	 }         
     }
   %>
   
   <script type="text/javascript">
       alert("쿠키값 삭제 완료!");
       location.href="cookieTest.jsp";   
   </script>
   
   
   
   
   
   
   
   
   
   
   
   
   

</body>
</html>