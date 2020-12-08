<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/member/main.jsp</h1>
	
	<%
	  // 메인페이지는 로그인 없이 이동할수 없는 페이지
	  // 로그인이 없이 접근할경우 -> 로그인 페이지로 이동
	  String id = (String) session.getAttribute("id");
	  
	  if(id == null){
		  response.sendRedirect("./MemberLogin.me");
	  } 
	%>
	<h3>로그인 : <%=id %></h3>
	<h3>로그인(session-EL표현식) : ${ sessionScope.id } </h3>
	
	<input type="button" value="로그아웃!" onclick="location.href='./MemberLogout.me'">
	
	<hr>
	
	<h3><a href="./MemberInfo.me">회원 정보 조회</a></h3>
	<hr>
	
	<h3><a href="./MemberUpdate.me">회원 정보 수정</a></h3>
	<hr>
	
	<h3><a href="./MemberDelete.me">회원 정보 삭제(탈퇴)</a></h3>
	<hr>
	
	<h3><a href="./BoardList.bo">ITWILL 게시판</a></h3>
	<hr>
	
	<h3><a href="./GoodsList.go">ITWILL 쇼핑몰</a></h3>
	
	<hr>
	
	<h3><a href="./BasketList.ba">ITWILL 쇼핑몰 - 장바구니</a></h3>
	
	<hr>
	
	<h3><a href="./OrderList.or">ITWILL 쇼핑몰 - 주문목록</a></h3>
	
	<%
	//if(id != null){
	  if( id != null && id.equals("admin") ){
	%>
	<hr>
	<h3><a href="./MemberList.me">회원 전체 목록(관리자)</a></h3>
	<hr>
	<h3><a href="./AdminGoodsList.ag">상품 목록(관리자)</a></h3>
	<hr>
	<h3><a href="./AdminOrderList.ao">주문 목록(관리자)</a></h3>
	
	<%
	  }
	//}
	%>
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>