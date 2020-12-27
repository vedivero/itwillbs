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
  .btn  {
    background-color :  #343A40;
    color :  white;
  }
  .btn:hover {
    background: #00BBFF;
    color : #007BFF;
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
        <h1 class="my-4">회원 관리</h1>
        <div class="list-group">
<!--           	<a href="/admin/adminPage" class="list-group-item">관리자 페이지</a> -->
        	<a href="/admin/memberList?num=1" class="list-group-item">회원 목록</a>
          	<a href="/" class="list-group-item">경매 관리</a>
          	<a href="/" class="list-group-item">신고 관리</a>
        </div>
      </div>
      <div class="col-lg-9">
      
      <br>
      <br>
      <br>
      <br>
      
	    <fieldset>
	    <legend>회원정보 상세보기</legend>
<!-- 	    <form action="/admin/restriction" method="POST"> -->
	     <span class="front" style="margin-right: 34px;">아이디</span>
	     	<input type="text" name="id" class="form-control" value="${vo.id}" style="width:226px;display:inline;"  readonly>
	     
	     <span class="front" style="margin-right: 50px; "> 이 름 </span>
	     	<input type="text" name="username" class="form-control" value="${vo.username }" style="width:170px;display:inline;"readonly> <br>
	      
	      <span class="front" style="margin-right: 34px;" >이메일</span>
	      	<input type="text" name="email" class="form-control"  value="${vo.email }" style="width:226px;display:inline; "readonly> 
	     
	      <span class="front"  style="margin-right: 38px;">연락처</span>
	      	<input type="text" name="phone"  class="form-control" value="${vo.phone}" style="width:170px;display:inline; "readonly> <br>
	      
	      <span class="front" style="margin-right: 45px; ">주소: </span>
	      	<input type="text" name="addr1" class="form-control" value="${vo.addr1} ${vo.addr2}" style="width:226px;display:inline; "readonly>
	      
<!-- 	      <span class="front" style="margin-right: 40px; ">주소2:</span> -->
<%-- 	      	<input type="text" name="addr2" class="form-control" value="${vo.addr2}" style="width:226px;display:inline; "readonly><br> --%>
	      
	      <span class="front" style="margin-right: 17px; ">가입 일자</span>
	      	<input type="text" name="reg_date" class="form-control" value="${vo.reg_date}" style="width:170px;display:inline; "readonly><br>

	     <br>
	     <br>
	     <br>
<!-- 	       </form> -->
	  	</fieldset>
<!-- 		<br> -->
	       <h3>계정 상태</h3>
	      <span class="front" style="margin-right: 17px;">제재상태</span>
	      	<input type="text" class="form-control" value="${vo.block}" style="width:60px;display:inline; text-align: center;" readonly>
	      	( ※ 0 : 계정 활성 / 1 : 계정 제재 )
	      	<br>
	  		<form action="/admin/restrinctionReason?id=${vo.id}" method="GET">
		      	<span class="front" style="margin-right: 17px;">상태변경</span>
				<button type="button" onclick="location.href='/admin/restriction?id=${vo.id}'">제한하기</button>
				<button type="button" onclick="location.href='/admin/liftingRestriction?id=${vo.id}'">제한 해제하기</button>
				<br>
				
		      	<span class="front" style="margin-right: 17px;">변경사유</span>
				<input type="text" name="block_r" placeholder="상태 변경 사유를 간략히 입력해 주세요" style="width:300px;display:inline;">

		      	<input type="hidden" name="score" value="${vo.score}">
		      	<input type="hidden" name="id" value="${vo.id}">
		      	<input type="hidden" name="addr1" value="${vo.addr1}">
		      	<input type="hidden" name="addr2" value="${vo.addr2}">
		      	<input type="hidden" name="username" value="${vo.username}">
		      	<input type="hidden" name="phone" value="${vo.phone}">
		      	<input type="hidden" name="reg_date" value="${vo.reg_date}">
		      	<input type="hidden" name="score" value="${vo.score}">
		      	<input type="hidden" name="block" value="${vo.block}">

				<input type="submit" value="제재 사유 입력">
				 
			</form>
	     <br>
	     
	     
	  
<!-- 	      <input type="submit" class="btn" value="회원수정" style="background-color: #343A40; color:white; " name="commit">           -->
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

