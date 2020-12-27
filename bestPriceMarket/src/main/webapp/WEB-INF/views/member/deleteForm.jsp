<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">
<!-- <link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css' rel="stylesheet"> -->
<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css' rel="stylesheet"> 
<link href='${pageContext.request.contextPath}/resources/loginandjoin/loginandjoin.css' rel="stylesheet">
<style>
  .btn {
    border-radius: 4px !important; 
    background: #212529 !important;
    color: #fff !important;
    padding: 7px 45px !important;
    display: inline-block !important;
    margin-top: 20px !important;
    border: solid 2px #212529 !important; 
    transition: all 0.5s ease-in-out 0s !important;
}
.btn:hover,
.btn:focus {
    background: transparent !important;
    color: #212529 !important;
    text-decoration: none !important;
}
.py-5  {
   width:100%;
   position:absolute;
   bottom:0;
  text-align: center;
}
.form {
  display: flex;
  height: 100vh;
  justify-content: center;
  align-items: center;
  border: solid 1px gray;
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
        <h1 class="my-4">My Page</h1>
        <div class="list-group">
      	  <a href="/basket/listPage" class="list-group-item">좋아요</a>
          <a href="/member/update" class="list-group-item">회원 수정</a>	
          <a href="/member/delete" class="list-group-item">회원 탈퇴</a>
          <a href="/member/changePw" class="list-group-item">비밀번호 수정</a>
        </div>
      </div>

  <!-- /.container -->
   <div class="col-lg-9">
	<fieldset style="margin-top: 50px;">
	    <legend> 회원탈퇴 </legend>
	    <form method="post" name="form1">
	       <span class="front" style="margin-right: 52px;">아이디 </span> <input type="text" name="id" class="form-control" value="${id }" style="width:226px;display:inline;" readonly> <br>
	      <span class="front" style="margin-right: 40px;">비밀번호</span><input type="password" class="form-control" name="pw"  style= "width: 226px; display: inline;"  required> <br>
	     <input type="submit" class="btn" style="background-color: #343A40; color:white; " value="회원탈퇴"  id="btnDelete"> 
	    </form>  
	</fieldset>
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
