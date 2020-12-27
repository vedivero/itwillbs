 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>베프마켓 - 친구같은 경매플랫폼</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">
<!-- <!-- <link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css' rel="stylesheet"> -->
<!-- <link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css' rel="stylesheet"> -->
<link href='${pageContext.request.contextPath}/resources/loginandjoin/loginandjoin.css' rel="stylesheet">
<style>
/*  .py-5  { */
/*    width:100%; */
/*    position:absolute; */
/*    bottom:0; */
/*   text-align: center; */
/* } */
/* .form { */
/*   display: flex; */
/*   height: 100vh; */
/*   justify-content: center; */
/*   align-items: center; */
/*   border: solid 1px gray; */
  
/*  } */
/* .front{ */
/*  width: 202px; */
 
.btn-secondary {
    border-radius: 4px !important;
    background: #212529 !important;
    color: #fff !important;
    padding: 7px 45px !important;
    display: inline-block !important;
    margin-top: 20px !important;
    border: solid 2px #212529 !important; 
    transition: all 0.5s ease-in-out 0s !important;
}
.btn-secondary:hover,
.btn-secondary:focus {
    background: transparent !important;
    color: #212529 !important;
    text-decoration: none !important;
}
}
</style>
</head>
<body>
  <!-- 헤더 -->
   <jsp:include page="../inc/top.jsp"/>
  <!-- 헤더 -->
  
  <!-- Page Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-3">
<!--         <h1 class="my-4"></h1> -->
        <div class="list-group">
	    	
        </div>
      </div>
      <div class="col-lg-9">
      </div>
    </div>
  </div>
  	<br>
  	<br>
  	<br>
  		<div style="text-align:center;">
			<button onclick="location.href='/admin/memberList?num=1'" class="btn btn-secondary" style="width:400px; height:250px; font-size:50px; text-align: center; margin-right: 120px; margin-top: 400px; margin-bottom: 200px;"><b>회 원<br> 관 리</button>
			<button onclick="location.href='/admin/goodsList?num=1'" class="btn btn-secondary" style="width:400px; height:250px; font-size:50px; text-align: center; margin-top: 400px; margin-bottom: 200px;"><b>상 품<br> 관 리</button>
			
 		</div>
 <div class="container">
	 <div class="row">
 		<div class="col-lg-9">
			<br>
			<br>
			<br>
		 </div>
	 </div>
 </div>
  <!-- 푸터 -->
  <jsp:include page="../inc/bottom.jsp"/>
  <!-- 푸터 -->

  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>

