<%@page import="com.bestpricemarket.domain.BasketVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="${pageContext.request.contextPath}/resources/BasketCSS/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/loginandjoin/loginandjoin.css" rel="stylesheet">
<style type="text/css">
.container{
	width: 100% !important; 
}
.btn {
    border-radius: 4px !important; 
    background: #212529 !important;
    color: #fff !important;
    padding: 7px 7px !important;
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
.delete_btn {
    border-radius: 4px !important; 
    background: #DC3545 !important;
    color: #fff !important;
    padding: 7px 7px !important;
    display: inline-block !important;
    margin-top: 20px !important;
    border: solid 2px #DC3545 !important; 
    transition: all 0.5s ease-in-out 0s !important;
}
.delete_btn:hover,
.delete_btn:focus {
    background: transparent !important;
    color: #DC3545 !important;
    text-decoration: none !important;
}
</style>
</head>
<body>
<jsp:include page="../inc/top.jsp"/> 
    <!-- Page Content -->
  <div class="container" style="margin-bottom: 500px;">
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
     <div class="row"> 
         <div class="col-12">  
          	<!-- <div class="card"> -->
                   <h1 class="my-4" style="text-align: center;">좋아요</h1>   
                    <div class="row p-5" style="top:300px; width: 996px;"  >
                         <div class="col-md-12">
                            <form method="post" role="form">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th class="border-0 text-uppercase small font-weight-bold">찜한순서</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">상품</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">상품이름</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">경매상태</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">입찰가</th>
                                        <th class="border-0 text-uppercase small font-weight-bold">경매기간</th>
                                   	    <th class="border-0 text-uppercase small font-weight-bold" colspan="2">여부</th>
                                    </tr>
                                </thead>
                                <tbody>
				                       <c:choose>
				                      <c:when test="${basketlist == null }">
				                       <h2>관심 상품이 없습니다 ㅠㅠ</h2>
				                      </c:when>
				                        <c:otherwise>
                                    <c:forEach items="${basketlist }" var="BasketVO">
                                    <input type="hidden" name="lno"  value="${BasketVO.lno }">
                                    <input type="hidden" name="l_g_gno"  value="${BasketVO.l_g_gno}">
                                    <tr>
                                        <td>${BasketVO.lno}</td> 
                                        <!-- 업로드 위치 모르겠음 -->
                                        <td><img src="<c:url value="/imgUpload/${BasketVO.f_name}"/>" width="100px" height="100px"/></td>
                                        <!--  -->
                                        <td><a href="/goods/detail?gno=${BasketVO.lno }">${BasketVO.gname }</a></td>
                                        <c:choose>
                                        <c:when test="${BasketVO.actionstatus == -1 }">
                                          <td>경매실패</td>
                                        </c:when>
                                        <c:when test="${BasketVO.actionstatus == 0 }">
                                          <td>경매중</td>
                                        </c:when>
                                        <c:when test="${BasketVO.actionstatus == 1 }">
                                          <td>경매종료</td>
                                        </c:when>
                                        <c:when test="${BasketVO.actionstatus == 2 }">
                                          <td>경매성공</td>
                                        </c:when>
                                        </c:choose>
                                        <td>${BasketVO.lowestprice }</td>
                                        <td><fmt:formatDate value="${BasketVO.enddate }" pattern="yyyy-MM-dd"/></td>
                                    	<td><input type="button" class="delete_btn" id="delete_btn" name="delete_btn" value="삭제"></td>
                                    	<td><input type="button" class="btn" id="pay_btn" name="pay_btn" value="입찰" onclick="fun1(${BasketVO.l_g_gno})"> </td>
                                    </tr>
                                       </c:forEach>
                                </c:otherwise>
                        </c:choose>
                                </tbody>
                            </table>
							</form>
										<!-- 페이징 처리 -->
										
										<ul class="pagination justify-content-center">
										<c:if test="${page.prev}">
										 <li class="page-item">
										 <a class="page-link" href="/basket/listPage?num=${startPageNum - 1}">Previous</a>
										 </li>
										</c:if>
										
										<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
										 
										 
										  <c:if test="${select != num}">
										  <li class="page-item"> 
										  <a class="page-link" href="/basket/listPage?num=${num}">${num}</a>
										  </li>
										  </c:if>    
										  
										 <c:if test="${select == num}">
										  <li class="page-item">
										  <b><a class="page-link" href="/basket/listPage?num=${num}">${num}</a></b>
										  </li>
										  </c:if>
										    
										 
										</c:forEach>
										
										<c:if test="${page.next}">
										 <li class="page-item">
										 <a class="page-link" href="/basket/listPage?num=${endPageNum + 1}">Next</a>
										 </li>
										</c:if>
									</ul>
									
									</div>
					                    </div>
					                </div>
					            </div> 
					         </div>   
					        </div>  
					      </div>
<jsp:include page="../inc/bottom.jsp"/> 
<!-- 상품 카테고리 메뉴바 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>   
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

function fun1(l_g_gno){
	var con = confirm("입찰하러 가시겠습니까?");
	if(con){
	location.href="/goods/detail?gno="+l_g_gno;
	}
}


$(document).ready(function(){
	var formObj = $("form[role='form']");
               
$("#delete_btn").on("click",function(){
		var con = confirm("삭제 하시겠습니까?");
		if(con){
		alert("삭제 되었습니다");
		formObj.attr("action","/basket/delete");
		formObj.submit();
		}
		});	
});
</script>
</body>