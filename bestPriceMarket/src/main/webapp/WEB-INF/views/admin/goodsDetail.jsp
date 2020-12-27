 <%@page import="com.bestpricemarket.domain.GoodsVO"%>
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
        <h1 class="my-4">경매 관리</h1>
        <div class="list-group">
        	<a href="/admin/memberList?num=1" class="list-group-item">회원 목록</a>
          	<a href="/admin/goodsList?num=1" class="list-group-item">경매 관리</a>
        </div>
      </div>
	<div class="col-lg-9">

	<br>      
	<br>      
	<br>      
	<br>      
	
	<%String status = ""; %>
	<c:choose>
		<c:when test="${vo.actionstatus == 2}">
			<%status = "상품등록"; %>
		</c:when>
		<c:when test="${vo.actionstatus == 1}">
			<%status = "진행중"; %>
		</c:when>
		<c:otherwise>
			<%status = "종료"; %>
		</c:otherwise>
	</c:choose>

    <fieldset>
	    <legend style="margin-left: 250px;"><b>상품 상세보기</b></legend>
   		<br>
   		<br>
	        <span class="front" style="margin-right: 34px; ">상품번호</span>
		     	<input type="text" name="gno" class="form-control" value="${vo.gno}" style="width:180px;display:inline; text-align: center;"  readonly>
		     
		     <span class="front" style="margin-right: 82px; margin-left: 40px; "> ID </span>
		     	<input type="text" name="g_m_id" class="form-control" value="${vo.g_m_id}" style="width:180px;display:inline; text-align: center; "readonly> 
		     	<br>
		     	<br>
		      
		      <span class="front" style="margin-right: 34px; " >상품분류</span>
		      	<input type="text" name="category" class="form-control"  value="${vo.category}" style="width:180px;display:inline; text-align: center;"readonly> 
		     
		      <span class="front"  style="margin-right: 34px; margin-left: 40px; ">상품이름</span>
		      	<input type="text" name="gname" class="form-control" value="${vo.gname}" style="width:180px;display:inline; text-align: center; "readonly>
		      	<br>
		      	<br>
		      
		      <span class="front" style="margin-right: 34px; ">상품설명</span>
		      	<input type="text" name="content" class="form-control" value="${vo.content}" style="width:180px;display:inline; text-align: center;"readonly>
	
	
		      										
		      <span class="front" style="margin-right: 34px; margin-left: 40px;">경매상태</span>
		      	<input type="text" name="actionstatus" class="form-control" value="<%=status%>" style="width:180px;display:inline; text-align: center; "readonly>
		      	
		      	
		      	<br>			
		      	<br>
	
		      <span class="front" style="margin-right: 2px; "><b>경매시작시간</b></span>
		      	<input type="text" name="regDate" class="form-control" value="${vo.regDate}" style="width:180px;display:inline; text-align: center;"readonly>
	
		      <span class="front" style="margin-right: 2px; margin-left: 40px;"><b>경매종료시간</b></span>
		      	<input type="text" name="endDate" class="form-control" value="${vo.endDate}" style="width:180px;display:inline; text-align: center;  "readonly>
		     <br>
		     <br>
		     <br>
		     <br>
			 <button type="button" class="btn btn-danger btn-block" style="font-size: 20px; width:605px; text-align:center; margin-left: 20px;" onclick="removeCheck()">글 삭제하기</button>
	  	</fieldset>

      </div>
    </div>
  </div>
   <script type="text/javascript">
   function removeCheck() {

       if (confirm("해당 상품을 정말 삭제 하시겠습니까?") == true){    //확인
          location.href="/admin/delete?gno=${vo.gno}"

       }else{   //취소

           return false;

       }

      }
   </script>
  <!-- 푸터 -->
  <jsp:include page="../inc/bottom.jsp"/>
  <!-- 푸터 -->

  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>

