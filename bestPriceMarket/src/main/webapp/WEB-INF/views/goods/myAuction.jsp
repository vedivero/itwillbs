<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">


</head>
<body>
 
  
  <jsp:include page="../inc/top.jsp"/>
  
   <div class="col-lg-3">
        <h1 class="my-4">My Page</h1>
        <div class="list-group">
      	  <a href="/basket/listPage" class="list-group-item">좋아요</a>
          <a href="/member/update" class="list-group-item">회원 수정</a>	
          <a href="/member/delete" class="list-group-item">회원 탈퇴</a>
          <a href="/member/changePw" class="list-group-item">비밀번호 수정</a>
        </div>
      </div>
  <div class="tea"
  style="margin-top: 50px; width: 1300px; margin-left: 500px; "
  >
  
   
   <h1>내 경매 현황</h1><br>
  <table class="table" border="3">
  	<h3>내 낙찰 현황</h3>
  	<thead>
  	
  		<tr>
  			<th>상품명</th>
  			<th>상품사진</th>
  			<th>판매자 </th>
  			<th>낙찰현황</th>
  		</tr>
  	</thead>
  	<tbody>
  		<tr>
  			<td>기타2	</td>
  			<td>기타사진 </td>
  			<td>도우너</td>
  			<td>배송중</td>
  		</tr>
  	</tbody>
  	<tbody>
  		<tr>
  			<td>기타4	</td>
  			<td>기타사진 </td>
  			<td>기영2</td>
  			<td>입금대기중</td>
  		</tr>
  	</tbody>
  	<tbody>
  		<tr>
  			<td>김치	</td>
  			<td>김치사진 </td>
  			<td>기영5</td>
  			<td>배송완료</td>
  		</tr>
  	</tbody>
  </table>
  <div class="text-center">
		<ul class="pagination">
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
		</ul>
	</div>
   <table class="table" border="3">
    <h3>내 판매 현황</h3>
  	<thead>
  		<tr>
  			<th>상품명</th>
  			<th>상품사진</th>
  			<th>경매현황</th>
  		</tr>
  	</thead>
  	<tbody>
  		<tr>
  			<td>피리	</td>
  			<td>피리사진 </td>
  			<td>경매종료</td>
  		</tr>
  	</tbody>
  	<tbody>
  		<tr>
  			<td>피리2	</td>
  			<td>피리사진2 </td>
  			<td>경매중</td>
  		</tr>
  	</tbody>
  </table>
  <div class="text-center">
		<ul class="pagination">
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
		</ul>
	</div>
    <table class="table" border="3">
    <h3>내 입찰 현황</h3>
  	<thead>
  		<tr>
  			<th>상품명</th>
  			<th>상품사진</th>
  			<th>판매자 </th>
  			<th>경매현황</th>
  		</tr>
  	</thead>
  	<tbody>
  		<tr>
  			<td>기타	</td>
  			<td>기타사진 </td>
  			<td>기철</td>
  			<td>경매중</td>
  		</tr>
  	</tbody>
  	<tbody>
  		<tr>
  			<td>기타2	</td>
  			<td>기타사진 </td>
  			<td>기영</td>
  			<td>경매종료</td>
  		</tr>
  	</tbody>
  </table>
  
  	<div class="text-center">
		<ul class="pagination"
		 style="margin-bottom: 200px;"
		>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
		</ul>
	</div>
	</div>
	<jsp:include page="../inc/bottom.jsp"/>
</body>
</html>