<%@page import="com.bestpricemarket.domain.BasketVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/BasketCSS/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.container{
 width: 100% !important; 
}
.btn {
    border-radius: 4px; 
    background: #212529;
    color: #fff;
    padding: 7px 45px;
    display: inline-block;
    margin-top: 20px;
    border: solid 2px #212529; 
    transition: all 0.5s ease-in-out 0s;
}
.btn:hover,
.btn:focus {
    background: transparent;
    color: #212529;
    text-decoration: none;
}

</style> 

<script type="text/javascript">
function basketlist(page){
	location.href="basket/basket?curPage=" + page
}

</script>

</head>
<body>

<jsp:include page="../inc/top.jsp"/> 
<!------ Include the above in your HEAD tag ---------->
    <!-- Page Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-3">
        <h1 class="my-4">회원정보보기</h1>
        <div class="list-group">
      	  <a href="/member/basket" class="list-group-item">좋아요</a>
          <a href="/member/update" class="list-group-item">회원 수정</a>	
          <a href="/member/delete" class="list-group-item">회원 탈퇴</a>
        </div>
      </div>
                   <div class="col-lg-9">
                     
     <div class="row"> 
         <div class="col-12">  
          <!--  <div class="card"> -->  
                   <h1 class="my-4" style="text-align: center;">좋아요</h1>   
                    <div class="row p-5" style="top:300px;"  >
                       <c:choose>
                      <c:when test="${basketlist == null }">
                       <h2>관심 상품이 없습니다 ㅠㅠ</h2>
                      </c:when>
                        <c:otherwise>
                         <div class="col-md-12">
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
                                    <c:forEach items="${basketlist }" var="BasketVO">
                                    <tr>
                                        <td>${BasketVO.lno }</td> 
                                        <!-- 업로드 위치 모르겠음 -->
                                        <td><img src="./upload/${ gdto.getImage().split[0]}"></td>
                                        <!--  -->
                                        <td><a href="goods/detail?lno=${BasketVO.lno }">${BasketVO.gname }</a></td>
                                        <c:choose>
                                        <c:when test="${BasketVO.actionstatus == -1 }">
                                          <td>입찰실패</td>
                                        </c:when>
                                        <c:when test="${BasketVO.actionstatus == 0 }">
                                          <td>입찰중</td>
                                        </c:when>
                                        <c:when test="${BasketVO.actionstatus == 1 }">
                                          <td>입찰종료</td>
                                        </c:when>
                                        <c:when test="${BasketVO.actionstatus == 2 }">
                                          <td>입찰성공</td>
                                        </c:when>
                                        </c:choose>
                                        <td>${BasketVO.lowestprice }</td>
                                        <td>${BasketVO.enddate }</td>
                                    	<td><a href="/basket/delete?lno=${BasketVO.lno }">삭제</a></td>
                                    	<td><a href="/pay/order">입찰</a></td>
                                    </tr>
                                    </c:forEach>
                                   
                                </tbody>
                            </table>
										<!-- 페이징 처리 -->
										<div>
											<c:forEach begin="1" end="${pageNum}" var="num">
												<span> <a href="/board/listPage?num=${num}">${num}</a>
												</span>
											</c:forEach>
										</div>

									</div>
                        </c:otherwise>
                        </c:choose>
                       
                        
                    </div>
                </div>
            </div> 
      </div>   
      </div>  
    
    <div class="text-light mt-5 mb-5 text-center small">by : <a class="text-light" target="_blank" href="http://totoprayogo.com">totoprayogo.com</a></div>
</div>
<!-- 상품 카테고리 메뉴바 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>   
</body>
