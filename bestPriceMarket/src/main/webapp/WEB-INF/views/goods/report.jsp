<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>
textarea {
	font-size: .8rem;
	letter-spacing: 1px;
}

textarea {
	padding: 10px;
	line-height: 1.5;
	border-radius: 5px;
	border: 1px solid #ccc;
	box-shadow: 1px 1px 1px #999;
}
</style>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 헤더 -->

	<!-- Page Content -->
	<div class="container">
		<br>
		<h2>신고하기</h2>
		<br>
		<form method="post" action="/goods/report">
			<input type="hidden" name="gno" value="${reportVO.gno}"> <input
				type="hidden" name="reporterEmail" value="${myInfo.email}">
			<div class="form-group">
				<label for="exampleInputEmail1">물품명</label> <input type="text"
					class="form-control" id="exampleInputEmail1" name="gname"
					style="width: 594px;" aria-describedby="emailHelp"
					value="${reportVO.gname }" readonly>
			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">판매자</label> <input type="text"
					class="form-control" id="exampleInputEmail1" name="g_m_id"
					style="width: 594px;" aria-describedby="emailHelp"
					value="${reportVO.g_m_id }" readonly>
			</div>

			<div class="form-group">
				<label for="exampleInputEmail1">신고자</label> <input type="text"
					class="form-control" id="exampleInputEmail1" name="reporter"
					style="width: 594px;" aria-describedby="emailHelp"
					value="${myInfo.id}" readonly>
			</div>

			<p class="question">
				<b>여러사유에 해당되는 경우, 대표적인 사유 1개를 선택해주세요.</b>
			</p>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="repo" id="r1"
					value="1" checked> <label class="form-check-label"
					for="gridRadios1"> 위법성 상품 <br>
				</label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="repo" id="r2"
					value="0"> <label class="form-check-label"
					for="gridRadios2"> 반복적인 상품게시(도배) </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="repo" id="r3"
					value="-1"> <label class="form-check-label"
					for="gridRadios2" id="etc"> 기타 </label>
				<div>
					<br>
					<textarea cols="30" rows="10" class="form-check-textarea"
						name="content" id="content" disabled></textarea>
				</div>
			</div>
			<br> <input type="submit" value="신고하기">
		</form>
	</div>
	<br>
	<br>
	<!-- 푸터 -->
	<jsp:include page="../inc/bottom.jsp" />
	<!-- 푸터 -->
	<script type="text/javascript">
		$(document).ready(function() {

			// 라디오버튼 클릭시 이벤트 발생
			$('input:radio[name=repo]').click(function() {
				if ($('input[name=repo]:checked').val() == "-1") {
					$("#content").attr("disabled", false);
				} else {
					$("#content").attr("disabled", true);
				}
			});
		});
	</script>


</body>
</html>