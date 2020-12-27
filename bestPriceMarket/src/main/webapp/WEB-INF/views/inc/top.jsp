<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>베프마켓 - 친구같은 경매플랫폼</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">
<!-- favicon -->
<link rel="shortcut icon" href="favicon.ico"/>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="/main"><img src="../resources/logoonly.PNG" width="35px"></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a class="nav-link" href="/main">HOME</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/myAction/actionlist">내경매</a>
          </li>
          <%
          String id = (String) session.getAttribute("id");
          if(id == null){
          %>
          <li class="nav-item">
            <a class="nav-link" href="/member/login">로그인 | 회원가입</a>
          </li>
          <%} else { %>
          <li class="nav-item">
            <a class="nav-link" href="/member/update">마이페이지</a>
          </li>
          <c:if test="${id=='admin'}">
          <li class="nav-item">
            <a class="nav-link" href="/admin/adminPage"><b>관리자페이지</b></a>
          </li>
          </c:if>
          <li class="nav-item">
            <a class="nav-link" href="/member/logout">로그아웃</a>
          </li>
		  <%} %>          
          <li class="nav-item">
            <a class="nav-link" href="/CS/CSBoardListPage?num=1">고객센터</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- Bootstrap core JavaScript -->
  <script src="../resources/vendor/jquery/jquery.min.js"></script>
  <script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <!-- Bootstrap core JavaScript -->
</body>
</html>