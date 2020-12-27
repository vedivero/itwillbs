<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<!-- 버튼 CSS -->
<link href="${pageContext.request.contextPath}/resources/css/button-reg_goods.css" rel="stylesheet">
<!-- 버튼 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">
.list-group-item:active{
  background-color: #e9e9e9;
}
.my{
  text-align: center;
  font-weight: bold;
  margin-bottom: 10px;
  margin-top: 30px;
}            
</style>
</head>
<body>

  <!-- 헤더 -->
   <jsp:include page="../inc/top.jsp"/>
  <!-- 헤더 -->

  <!-- 본문 -->
   <div class="container">

    <div class="row">
	<!-- 상품 카테고리 메뉴바-->
	 
      <div class="col-lg-3" style="height:800px">
       <h2 class="my">${category}</h2>
        <c:if test="${id != null}">
        <a href="/goods/register"><button class="snip1535">내 상품팔기</button></a>
      </c:if>
      
    	<!-- 재원 -->
    	<div>검색</div>
    	
        <div class="list-group">
          <a href="/goods/list?category=컴퓨터" class="list-group-item">컴퓨터</a>
          <a href="/goods/list?category=디지털" class="list-group-item">디지털</a>
          <a href="/goods/list?category=생활가전" class="list-group-item">생활가전</a>
          <a href="/goods/list?category=생활문구" class="list-group-item">생활문구</a>
          <a href="/goods/list?category=생활가구" class="list-group-item">생활가구</a>
          <a href="/goods/list?category=스포츠" class="list-group-item">스포츠</a>
          <a href="/goods/list?category=패션의류" class="list-group-item">패션의류</a>
          <a href="/goods/list?category=패션잡화" class="list-group-item">패션잡화</a>
          <a href="/goods/list?category=화장품" class="list-group-item">화장품</a>
          <a href="/goods/list?category=쥬얼리" class="list-group-item">쥬얼리</a> 
        </div>
      </div> 
   <!-- 상품 카테고리 메뉴바-->
 	<!-- 상품 목록 -->
        <div class="col-lg-9">

        <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
        
        <!-- 상품 카드 목록(Category) -->
        <c:set var="cate" value="${category}" />
       <c:if test="${category eq cate}"> 
      <div class="row"> 
        <c:forEach var="category" items="${categoryList}" >
          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
				<a href="/goods/detail?gno=${category.gno}&page=${pm.cri.page}&pageSize=${pm.cri.pageSize}">
				<img src="<c:url value="/imgUpload/${category.thumbnail}"/>" width="410px" height="200px" class="card-img-top"/> 
               	</a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="/goods/detail?gno=${category.gno}&page=${pm.cri.page}&pageSize=${pm.cri.pageSize}">${category.gname}</a>
                </h4>
				<c:choose>
			 		<c:when test="${finalprice > 0}">
						<h6>현재가 <b><fmt:formatNumber type="number" maxFractionDigits="3" value="${category.finalprice}" /></b>원</h6>
					</c:when>
					<c:otherwise>
						<h6>현재가 <b><fmt:formatNumber type="number" maxFractionDigits="3" value="${category.lowestprice}" /></b>원</h6>
					</c:otherwise>
				</c:choose>
                <h6>입찰수 ${category.numofbid}</h6>
                <h6>경매마감일 ${category.endDate}</h6>
              </div>
            </div>
          </div>
          </c:forEach>
        </div>
        </c:if> 
     <!-- 상품 카드 목록(Category) 

        
        <!-- 페이징 하단부 처리(카테고리목록) -->
       <c:set var="cate" value="${category}" />
       <c:if test="${category eq cate}"> 
		<ul class="pagination justify-content-center">
		 <!-- 이전 -->
		  <c:if test="${pm.prev}">
   			<li class="page-item">
   				<a class="page-link" href="/goods/list?category=${category}&page=${pm.startPage-1}">Previous</a>
   			</li>
   		   </c:if>	
   		 <!-- 이전 -->	
   			
   		 <!-- 페이지 번호 -->	
   		  <c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="idx">
			 <li class="page-item"
				${pm.cri.page == idx? 'class=active':''}
			 >
				<a class="page-link" href="/goods/list?category=${category}&page=${idx}">${idx}</a>
			</li>		
		  </c:forEach>     
   		 <!-- 페이지 번호 -->	
   			
   		 <!-- 다음 -->
   		  <c:if test="${pm.next && pm.endPage > 0}">	
   			<li class="page-item">
   			  	<a class="page-link" href="/goods/list?category=${category}&page=${pm.endPage+1}">Next</a>
   			</li>
   		  </c:if>	
   		 <!-- 다음 -->	
		  </ul>
	     </c:if>
        <!-- 페이징 하단부 처리(카테고리목록) -->
      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- 상품 목록 -->

  </div>
  </div>
 <!-- 본문 -->

  <!-- 푸터 -->
  <jsp:include page="../inc/bottom.jsp"/>
  <!-- 푸터 -->
</body>

</html>