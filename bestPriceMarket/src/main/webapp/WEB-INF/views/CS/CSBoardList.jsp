<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>베프마켓 - 친구같은 경매플랫폼</title>
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

				<h1 class="my-4">Shop Name</h1>
				<div class="list-group">
					<a href="#" class="list-group-item">FAQ</a>
					<a href="#" class="list-group-item">1:1 문의</a>
					<a href="#" class="list-group-item">나의 문의 보기</a>

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
						<c:forEach items="${CSList }" var="CSVO">
					<tbody>
							<tr>
							
								<td><a href="/CS/content?csbno=${CSVO.csbno }">${CSVO.csbno}</a></td>
								
								<td>
								<a href="/CS/content?csbno=${CSVO.csbno }">${CSVO.cs_subject }</a>
								</td>
								<td>
								 <fmt:formatDate value="${CSVO.cs_regdate}" 
					             pattern="yyyy-MM-dd HH:mm"
					               />
								</td>
							</tr>
					</tbody>
						</c:forEach>
				</table>


				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="#">Previous</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">4</a></li>
					<li class="page-item"><a class="page-link" href="#">5</a></li>
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
				</ul>
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
	<script	src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>