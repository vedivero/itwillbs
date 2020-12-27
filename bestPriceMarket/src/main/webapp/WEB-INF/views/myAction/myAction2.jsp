	<%@page import="com.bestpricemarket.domain.GoodsVO"%>
<%@page import="com.bestpricemarket.domain.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>베프마켓 - 친구같은 경매플랫폼</title>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/cs/cs_css/bootstrap.min.css" rel="stylesheet">
<!-- 버튼 CSS -->
<link href="${pageContext.request.contextPath}/resources/cs/cs_css/button-reg_goods.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/cs/cs_css/shop-homepage.css" rel="stylesheet">
<style type="text/css">
.list-group-item-a{
	float:left !important;
 	margin-top: 30px; 
}
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

.pay_btn {
    border-radius: 4px !important; 
    background:  #212529 !important;
    color: #fff !important;
    padding: 7px 7px !important;
    display: inline-block !important;
    margin-top: 20px !important;
    border: solid 2px  #212529 !important; 
    transition: all 0.5s ease-in-out 0s !important;
}
.pay_btn:hover,
.pay_:focus {
    background: transparent !important;
    color: #212529 !important;
}

</style>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 헤더 -->

	<!-- Page Content -->
	<div class="container" style="margin-bottom: 500px;">
    <div class="row">
      <div class="col-lg-3">
        <h1 class="my-4">내경매</h1>
        <div class="list-group">
      	  <a href="/myAction/actionlist2" class="list-group-item">입찰 내역</a>
          <a href="/myAction/paylist" class="list-group-item">낙찰 내역</a>	
        </div>
      </div>
      <div class="col-lg-9">
			<br>	          	
			<br>	          	
			<h3  style="text-align:center;"><b>낙찰</b> 경매내역</h3>   
			<br>	  
			<br>     	
				<!-- <button onclick="location.href='/myAction/actionlist?num=1'" class="btn btn-secondary" style="width:272px; text-align: center;"><b>입찰</b> 경매내역</button>
	          	<button onclick="location.href='/myAction/paylist?num=1'" class="btn btn-secondary" style="width:272px; text-align: center;"><b>낙찰</b> 경매내역</button> -->
			<br>     	
			<br>     	
				<table class="table table-hover" style="width: 1000px;">
					<thead>
						<tr>
							<td style="text-align: center;"><b>상품번호</b></td>
							<td style="text-align: center;">상품이름</td>
							<td style="text-align: center;">상품사진</td>
							<td style="text-align: center;">판매자</td>
							<td style="text-align: center;"><b>입찰</b></td>
							<td style="text-align: center;"><b>결제</b></td>
							<td style="text-align: center;"><b>배송</b></td>
						</tr>
					</thead>

					<c:forEach items="${paylist}" var="paylist">
							<tbody>
								<tr>
								 <td style="text-align: center;">
		                        	<b>${paylist.a_g_gno }</b>
		                        </td>
								 <td style="text-align: center;">
		                        	<img
										src="<c:url value="/imgUpload/${paylist.f_name}"/>"
										width="100px" height="100px" />
		                         </td>
								 <td style="text-align: center;">
		                        	<b>${paylist.gname}</b>
		                        </td>
								 <td style="text-align: center;">
		                        	${paylist.a_m_id}
		                        </td>
								<c:choose>
									   <c:when test="${paylist.a_g_actionstatus == -1 }">
											        <td style="text-align: center;"><b style="color:crimson">입찰실패</b></td>
										</c:when>
										<c:when test="${paylist.a_g_actionstatus == 0 }">
													<td style="text-align: center;">입찰 중</td>
										</c:when>
										<c:when test="${paylist.a_g_actionstatus == 1 }">
													<td style="text-align: center;">입찰종료</td>
										</c:when>
									 	<c:when test="${paylist.a_g_actionstatus == 2 }">
											       <td style="text-align: center;">입찰성공</td>
										</c:when>
									 </c:choose>
		                         	<c:choose>
		                         	<c:when test="${paylist.paystatus == 0 }">
		                         	<td style="text-align: center;"><b style="color:crimson">결제실패</b><br>
		                         	<div>
		                         	<input type="button" class="pay_btn" value="결제하기" onclick="fun1(${paylist.a_g_gno})">
		                         	</div>
		                         	</td>
		                         	</c:when>
								 	<c:when test="${paylist.paystatus == 1 }">
		                         	<td>결제성공</td>
		                         	</c:when>
									</c:choose>
									<c:choose>
									<c:when test="${paylist.deliverystatus == -1}">
										<td style="text-align: center;"><b style="color:crimson">배송실패</b></td>
									</c:when>
									<c:when test="${paylist.deliverystatus == 0}">
										<td>배송전</td>
									</c:when>
									
									<c:when test="${paylist.deliverystatus == 1}">
										<td>배송중</td>
									</c:when>
									<c:when test="${paylist.deliverystatus == 2}">
										<td>배송완료</td>
									</c:when>
									</c:choose>
								</tr>
							</tbody>
					</c:forEach>
				</table>
				
				<ul class="pagination justify-content-center">

					<c:if test="${prev}">
						<li class="page-item"><a class="page-link"
							href="/myAction/paylist?num=${startPageNum - 1}">Previous</a></li>
					</c:if>

					<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">

						<c:if test="${select!=num}">
							<li class="page-item"><a class="page-link"
								href="/myAction/paylist?num=${num}">${num}</a></li>
						</c:if>

						<c:if test="${select==num}">
							<a class="page-link" href="/myAction/paylist?num=${num}">${num}</a>
						</c:if>

					</c:forEach>

					<c:if test="${next}">
						<li class="page-item"><a class="page-link"
							href="/myAction/paylist?num=${endPageNum + 1}">Next</a></li>
					</c:if>
				</ul>
			</div>
			<!-- /.col-lg-9 -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->
	<!-- 푸터 -->
	<jsp:include page="../inc/bottom.jsp" />
	<!-- 상품 카테고리 메뉴바 --> 
	<!-- 상품 카테고리 메뉴바 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>   
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

function fun1(a_g_gno){
	var con = confirm("결제하러 가시겠습니까?");
	if(con){
	location.href="/pay/payment?gno="+a_g_gno;
	}
}
</script>

</body>