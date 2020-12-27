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
 <style type="text/css">  
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
          	<a href="/admin/goodsList?num=1" class="list-group-item">경매 관리</a>
        </div>
      </div>
      <div class="col-lg-9">
      
      <br>
      <br>
      <br>
      <br>
      
	    <fieldset>
	    <legend style="margin-left: 50px;">회원정보 상세보기</legend>
		    <br>
		     <span class="front" style="margin-right: 34px; margin-top: 5px;">아이디 :</span>
		     	<input type="text" name="id" class="form-control" value="${vo.id}" style="width:170px;display:inline; text-align:center;"  readonly>

		     <span class="front" style="margin-right: 45px; margin-left:40px; margin-top: 5px;"> 이 름 : </span>
		     	<input type="text" name="username" class="form-control" value="${vo.username }" style="width:170px;display:inline; text-align:center;"readonly>
		     	<br>
		     	<br>

		      
		      <span class="front" style="margin-right: 34px; margin-top: 5px;" >이메일 :</span>
		      	<input type="text" name="email" class="form-control"  value="${vo.email }" style="width:170px;display:inline; "readonly>

		     
		      <span class="front"  style="margin-right: 34px; margin-left:40px; margin-top: 5px;">연락처 :</span>
		      	<input type="text" name="phone"  class="form-control" value="${vo.phone}" style="width:170px;display:inline; text-align:center; "readonly>
		      	<br> 
		      	<br> 

		      
		      <span class="front" style="margin-right: 43px; margin-top: 5px;">주 소 : </span>
		      	<input type="text" name="addr1" class="form-control" value="${vo.addr1} ${vo.addr2}" style="width:170px;display:inline; "readonly>

		      
		      <span class="front" style="margin-right: 15px; margin-top: 5px; margin-left:40px;">가입 일자 :</span>
		      	<input type="text" name="reg_date" class="form-control" value="${vo.reg_date}" style="width:170px;display:inline; "readonly><br>

	     <br>
	     <br>
	     <br>
<!-- 	       </form> -->
	  	</fieldset>
<!-- 		<br> -->
	       <h3 style="margin-left: 50px;">계정 상태</h3>
	       <br>
	      <span class="front" style="margin-right: 17px;">제재상태</span>
	      	<input type="text" class="form-control" value="${vo.block}" style="width:60px;display:inline; text-align: center;" readonly>
	      	( ※ 0 : 계정 활성 / 1 : 계정 제재 )
	      	<br>
	  		<form action="/admin/restrinctionReason?id=${vo.id}" method="GET">
	  		<br>
		      	<span class="front" style="margin-right: 17px;">상태변경</span>
<%-- 			<button type="button" onclick="location.href='/admin/restriction?id=${vo.id}'">제한하기</button> --%>
				<button type="button" onclick="location.href='/admin/restriction?id=${vo.id}'" class="btn btn-danger" style="width:150px; text-align: center;"><b>제한하기</b> </button>

<%-- 			<button type="button" onclick="location.href='/admin/liftingRestriction?id=${vo.id}'">제한 해제하기</button> --%>
				<button type="button" onclick="location.href='/admin/liftingRestriction?id=${vo.id}'" class="btn btn-secondary" style="width:150px; text-align: center;"><b>제한해제하기</b> </button>
				<br>
				<br>
				
		      	<span class="front" style="margin-right: 17px;">변경사유</span>
				<input type="text" name="block_r" placeholder="상태 변경 사유를 간략히 입력해 주세요" style="width:300px;display:inline;">
				<button type="submit" class="btn btn-secondary" style="width:150px; text-align: center; "><b>제재 사유 입력</b></button>
<!-- 				<input type="submit" value="제재 사유 입력"> -->

		      	<input type="hidden" name="score" value="${vo.score}">
		      	<input type="hidden" name="id" value="${vo.id}">
		      	<input type="hidden" name="addr1" value="${vo.addr1}">
		      	<input type="hidden" name="addr2" value="${vo.addr2}">
		      	<input type="hidden" name="username" value="${vo.username}">
		      	<input type="hidden" name="phone" value="${vo.phone}">
		      	<input type="hidden" name="reg_date" value="${vo.reg_date}">
		      	<input type="hidden" name="score" value="${vo.score}">
		      	<input type="hidden" name="block" value="${vo.block}">
				
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

