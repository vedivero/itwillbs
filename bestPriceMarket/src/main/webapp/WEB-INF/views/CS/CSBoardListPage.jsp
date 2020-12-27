
<%@page import="com.bestpricemarket.domain.CSVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/cs/cs_css/bootstrap.min.css" rel="stylesheet">
<!-- 버튼 CSS -->
<link href="${pageContext.request.contextPath}/resources/cs/cs_css/button-reg_goods.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/cs/cs_css/shop-homepage.css" rel="stylesheet">
</head>
<body>
<!-- 헤더 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더 -->

   <!-- Page Content -->
   <div class="container">
      <div class="row">
         <!-- 상품 카테고리 메뉴바 -->
         <div class="col-lg-3">
            <h1 class="my-4">문의하기</h1>
            <div class="list-group">
               <a href="/CS/register" class="list-group-item">1:1 문의</a>
               <a href="/CS/CSBoardListPage?num=1" class="list-group-item">나의 문의 보기</a>
            </div>
         </div>
         <!-- 상품 카테고리 메뉴바 -->

         <div class="col-lg-9" style="height:800px">
            <br>
            <br>
            <h2>나의 문의 보기</h2> 
            <br>
            <br>
            <table class="table table-hover">
               <thead>
                  <tr>
                     <th>글 번호</th>
                     <th>제목</th>
                     <th>작성일</th>
                  </tr>
               </thead>
               
                  <c:forEach items="${CSList}" var="CSVO">
                  <%String id = (String)session.getAttribute("id"); %>
                  <c:if test="${(id eq CSVO.cs_m_id) or (id eq 'admin')}">
               <tbody>
                     <tr>
                     
                        <td><a href="/CS/content?csbno=${CSVO.csbno}">${CSVO.csbno}</a></td>
                        
                        <td>
                        <a href="/CS/content?csbno=${CSVO.csbno}">${CSVO.cs_subject }</a>
                        </td>
                        <td>
                         <fmt:formatDate value="${CSVO.cs_regdate}" 
                            pattern="yyyy-MM-dd HH:mm"
                              />
                        </td>
                     </tr>
               </tbody>
               </c:if>
                  </c:forEach>
            </table>
               <ul class="pagination justify-content-center">
               
         <c:if test="${prev}">
         <li class="page-item">
          <a class="page-link" href="/CS/CSBoardListPage?num=${startPageNum - 1}">Previous</a>
          </li>
         </c:if>
               
            <c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
                          
                 <c:if test="${select!=num}">
                    <li class="page-item">
                     <a class="page-link" href="/CS/CSBoardListPage?num=${num}">${num}</a>
                     </li>
                 </c:if>    
                 
                 <c:if test="${select==num}">
                 <a class="page-link" href="/CS/CSBoardListPage?num=${num}">${num}</a>
                 </c:if>
                
         </c:forEach>

         <c:if test="${next}">
             <li class="page-item"><a class="page-link" href="/CS/CSBoardListPage?num=${endPageNum + 1}">Next</a>
          </li>
         </c:if>
         </ul>

            
            <!--             <ul class="pagination justify-content-center"> -->
<!--                <li class="page-item"><a class="page-link" href="#">Previous</a></li> -->
<!--                <li class="page-item"><a class="page-link" href="#">1</a></li> -->
<!--                <li class="page-item"><a class="page-link" href="#">2</a></li> -->
<!--                <li class="page-item"><a class="page-link" href="#">3</a></li> -->
<!--                <li class="page-item"><a class="page-link" href="#">4</a></li> -->
<!--                <li class="page-item"><a class="page-link" href="#">5</a></li> -->
<!--                <li class="page-item"><a class="page-link" href="#">Next</a></li> -->
<!--             </ul> -->
         </div>

         <!-- /.col-lg-9 -->

      </div>
      <!-- /.row -->

   </div>
   <!-- /.container -->

   <!-- 푸터 -->
   <jsp:include page="../inc/bottom.jsp" />
   <!-- 푸터 -->
   <!-- Bootstrap core JavaScript -->
   <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
   <script   src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>