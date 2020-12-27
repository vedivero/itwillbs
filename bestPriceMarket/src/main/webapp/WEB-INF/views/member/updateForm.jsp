<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
.front{
 width: 202px;
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
      <div class="col-lg-9">
	    <fieldset style="margin-top: 50px;">
	    <legend> 회원정보 수정 </legend>
      	<div id="blockMsg" style="color:red;font-weight: bold;font-size: 15px;display:none;margin:10px 0;">정지된 회원입니다. 고객센터로 문의하시기바랍니다.</div>
	    <form action="/member/update" method="post" html="{:multipart=>true}" data-remote="true" accept-charset="UTF-8" name="frJoin">
	     <span class="front" style="margin-right: 34px;">아이디</span>  <input type="text" name="id" class="form-control" value="${memberVO.id }" style="width:226px;display:inline; text-align: center;" readonly><br>
	     <span class="front" style="margin-right: 17px; ">비밀번호</span>  <input id="pw" class="form-control" type="password" onkeyup="pwValCheck()" placeholder="비밀번호" name="pw" style="width:226px;display:inline;" required>  <br>
	     <span class="front" style="margin-right: 50px; "> 이름 </span><input type="text" name="username" class="form-control" value="${memberVO.username }" style="width:226px;display:inline; "> <br>
	     <span class="front" style="margin-right: 34px;" >이메일</span> <input type="text" name="email" class="form-control"  value="${memberVO.email }" style="width:226px;display:inline; "> <br>
	     <span class="front"  style="margin-right: 38px;">연락처</span><input type="text" name="phone"  class="form-control" value="${memberVO.phone}" style="width:226px;display:inline; "><br>
	     <span class="front" style="margin-right: 45px; ">주소: </span><input type="text" name="addr1" class="form-control" value="${memberVO.addr1}" style="width:226px;display:inline; "><br>
	     <span class="front" style="margin-right: 40px; ">주소2:</span><input type="text" name="addr2" class="form-control" value="${memberVO.addr2}" style="width:226px;display:inline; "><br>
	     <span class="front" style="margin-right: 17px; ">가입일자</span> <input type="text" name="reg_date" class="form-control" value="${memberVO.reg_date }"style="width:226px;display:inline; "readonly><br>
	     <input class="btn" type="submit" value="회원수정" name="commit" id="submitBtn" >          
	    </form>  
	  	</fieldset>
      </div>
    </div>
  </div>

<!-- 푸터 -->
<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터 -->

<script type="text/javascript">
if(${memberVO.block} != 0){
	$('#blockMsg').css('display', 'block');
}
</script>

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
