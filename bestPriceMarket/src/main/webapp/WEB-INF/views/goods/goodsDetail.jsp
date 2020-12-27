<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
<!-- 본문 CSS -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Include the above in your HEAD tag -->
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
<link href="${pageContext.request.contextPath}/resources/goods/goods_css/goodsDetail.css" rel="stylesheet">
<!-- 본문 JS  -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<!-- 버튼 CSS -->
<link href="${pageContext.request.contextPath}/resources/goods/goods_css/goodsBtn.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/goods/goods_css/bid_css.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/goods/goods_css/likebtn.css" rel="stylesheet">
<script type="text/javascript">
// 수정/삭제 이동  
$(document).ready(function(){
	var gno = $('#gno').val();

	$(document).on("click","#modify",function(){
		location.href = '/goods/modify?gno=${goods.gno}&page=${cri.page}&pageSize=${cri.pageSize}';
	});

	$(document).on("click","#delete",function(){
		var con = confirm("정말로 삭제하시겠습니까?");
		if(con){
			location.href = '/goods/delete?category=${goods.category}&gno=${goods.gno}&page=${cri.page}&pageSize=${cri.pageSize}';
		}	
	});
});
// 수정/삭제 이동 

// 파일 다운로드
function fn_fileDown(fno){
	var formObj = $("form[name='form']");
	location.href='/goods/fileDown?fno='+fno;
}
</script>
</head>
<body>
<%
String id = (String) session.getAttribute("id");
String result = (String) session.getAttribute("0");
%>
<!-- 헤더 -->
<jsp:include page="../inc/top.jsp" />
<!-- 헤더 -->

<!-- 본문 -->
<div class="pd-wrap">
	<div class="container">
	
		<!-- 태준 시작 -->
		<!-- 페이스북공유 -->			
		<button type="button" style="float:right;outline:none;border:none;width:90px;" value="페이스북" onclick="sharing();"><img src="../resources/goods/goods_img/facebook.png" style="border-radius: 3px;"></button>
		
		<!-- 트위터공유 -->
		<span style="float : right;">
			<a href="https://twitter.com/share?ref_src=twsrc%5Etfw" 
				class="twitter-share-button" data-size="large" data-lang="ko" data-show-count="false">트윗</a>
		<script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
		</span>
		<!-- 태준 끝 -->
	
		<div class="heading-section">경매상품 상세정보</div>		
		<div class="row">
		
			<!-- 경매정보 이미지 -->
			<div class="col-md-6">							
				<!-- 슬라이드 추가 -->
				<c:forEach var="file" items="${file}">
				<div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active">
							<img src="<c:url value="/imgUpload/${file.f_name}"/>"
								style="width: 600px; height: 400px; !important"
								class="d-block img-fluid" />
						</div>
					</div>
				</div>
				<a href="#" onclick="fn_fileDown('${file.fno}'); return false;">
				<i class="fas fa-arrow-alt-circle-down" style="color: black;"></i></a>
				</c:forEach>
			</div>
			<!-- 경매정보 이미지 끝-->

			<!-- 경매정보 테이블 -->
			<div class="col-md-6">
				<div class="product-dtl">
					<table>
						<tr>
							<th colspan="2" style="font-size: 40px;">${goods.gname}</th>
						</tr>
						<tr>
							<th>카테고리</th>
							<td>${goods.category}</td>
						</tr>
						<tr>
							<th>현재입찰가</th>
							<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${finalPrice}" />원</td>
						</tr>
						<tr>
							<th>입찰시작가</th>
							<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${goods.lowestprice}" />원</td>
						</tr>
						<tr>
							<th>경매기간</th>
							<td>${goods.regDate}  ~  ${goods.endDate}</td>
						</tr>
						<tr>
							<th>입찰마감</th>
							<td><div id="d-day"></div></td>
						</tr>
						<tr>
							<th>입찰수</th>
							<td>${gd_bidCount}</td>
						</tr>
						<tr>
							<th>판매자</th>
							<td>${goods.g_m_id}</td>
						</tr>
					</table>
					<!-- 경매정보 테이블 끝-->

					<!--************************************************* *입찰하기/관심상품(구매자들만 보이기) 끝 *********************************************************-->
					<c:if test="${ id != goods.g_m_id}">
						<!-- <form action="#" class="display-flex"> -->
							<div class="product-count">
								<!-- 좋아요버튼  -->
									<c:if test="${id != null && memberList.block == 0}">								
                                   <jsp:include page="../goods/likebtn.jsp"/>
								</c:if>
								<span id="wrap">
								 <c:if test="${id != null && memberList.block == 0}">
										<c:choose>
											<c:when test="${goods.actionstatus == 0}">
												<span style="font-size:2rem;"><strong>마감되었습니다.</strong></span>
											</c:when>												
											<c:otherwise>
												<a href="javascript:openModal('modal1');" class="round-black-btn">입찰하기</a>
											</c:otherwise>
										</c:choose>
								</c:if> 
								</span>
								<div id="modal"></div>
								<div class="modal-con modal1" style="z-index: 9999">
									<a href="javascript:;" class="close" style="color: white;">X</a>
									<p class="title">입찰현황</p>
									<div class="con">
										<table id="tablList">
											<thead>
												<tr id="ppap">
													<td class="mo1">입찰한 시간</td>
													<td class="mo1">아이디</td>
													<td class="mo1">입찰가</td>
												</tr>
												<c:forEach items="${bidList}" var="blist">
													<tr class="mo2">
														<td class="mo2">${blist.timelog}</td>
														<td class="mo2">${blist.pm_m_userid}</td>
														<td class="mo2">${blist.pm_g_bidprice}</td>
													</tr>
												</c:forEach>

											</thead>
										</table>
										<br> <input type="number" id="bidprice_input" class="btn_bidding" placeholder="숫자만 입력가능" step="1000"><br>
										<input type="button" id="bidding" class="btn_bidding" value="입찰하기">
									</div>
								</div>
								<c:if test="${id != null && memberList.block == 0}">								
								<a href="${path}/goods/report?gno=${goods.gno}" class="round-black-btn">신고하기</a>
								</c:if>
							</div>
						<!-- </form> -->
					</c:if>
					<!--************************************************* *입찰하기/관심상품(구매자들만 보이기) 끝 *********************************************************-->

					<!-- 수정/삭제(상품 올린 사람만 보이기) -->
					<c:if test="${ id == goods.g_m_id}">
						<form action="" role="form" name="form">
							<div class="product-count">
								<input type="hidden" name="gno" id="gno" value="${goods.gno}">
								<input type="hidden" name="page" value="${cri.page}"> 
								<input type="hidden" name="pageSize" value="${cri.pageSize}">
								<input type="hidden" name="category" value="${category}">
								<input type="hidden" id="fno" name="fno" value=""> 
								<input type="button" id="modify" value="수정" class="detail_modify-btn" style="margin-right: 20px;"> 
								<input type="button" id="delete" value="삭제" class="detail_delete-btn">
							</div>
						</form>
					</c:if>
					<!-- 수정/삭제(상품 올린 사람만 보이기) 끝 -->
				</div>
			</div>
		</div>
		<!-- row끝-->

		<!-- 상품 정보, 상품 문의 탭 -->
		<div class="product-info-tabs">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item">
				  <a href="#description" class="nav-link active" id="description-tab" data-toggle="tab"
			              role="tab" aria-controls="description" aria-selected="true">상품정보</a>
				</li>
				<li class="nav-item">
				  <a href="#review" class="nav-link" id="review-tab" data-toggle="tab" role="tab"
					aria-controls="review" aria-selected="false">상품문의</a>
				</li>
			</ul>

			<!-- 탭전환  -->
			<div class="tab-content" id="myTabContent">
				<!-- 상품정보 -->
				<div class="tab-pane fade show active" id="description" role="tabpanel" aria-labelledby="description-tab">
					${goods.content}
				</div>
				<!-- 상품정보 끝-->
				<div class="tab-pane fade" id="review" role="tabpanel" aria-labelledby="review-tab">
					<div class="review-heading"></div>
					<c:if test="${id != null}">								
					<button class="round-black-btn" id="reviewToggleBtn">문의하기</button>
					<div id="reviewToggle">
						<form name="frCmt" id="frCmt" method="post" class="review-form">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<input type="hidden" name="c_g_gno" value="${goods.gno }" />
										<label>작성자</label> 
										<input class="form-control" name="c_m_id" value="<%=id%>" readonly>
									</div>
								</div>
							</div>
							<label>문의내용</label>
							<textarea name="c_content" id="c_content" class="form-control" rows="5" placeholder="욕설, 비방 등 적절하지 않은 문의는 삭제될 수 있습니다."></textarea>
						</form>
						<button class="round-black-btn" id="cmtBtn" onclick="clickedCmtBtn();" style="margin-left: 81%;">등록</button>
						<button class="round-black-btn" id="recmtBtn" style="margin-left: 81%; display: none">댓글등록</button>
					</div>
					</c:if>
					<!-- 댓글리스트 -->
					<%@ include file="../goods/comment.jsp"%>
					<!-- 댓글리스트 끝 -->
				</div>
			</div>
			<!-- 탭전환끝 -->
		</div>
		<!-- 상품 정보, 상품 문의 탭 끝 -->
	</div>
	<!-- container 끝 -->
</div>
<!-- pd-wrap끝 -->

<!-- 판매자의 상품보기 -->
<jsp:include page="../goods/otherGoodsList.jsp" />
<!-- 판매자의 상품보기 끝 -->

<!-- 푸터 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터 -->

<!-- 입찰하기 js ************************************************************************************* -->
<script type="text/javascript">
var result = '${result}';
if(result == '1'){
	alert("신고가 접수되었습니다.");
}

function fillZeros(n, digits) {  
    var zero = '';  
    n = n.toString();  

    if (n.length < digits) {  
        for (i = 0; i < digits - n.length; i++)  
            zero += '0';  
    }  
    return zero + n;  
}  
  
function getNowTimeStamp() {  
    var d = new Date();  

    var s = fillZeros(d.getFullYear(), 4) + '-' +  
            fillZeros(d.getMonth() + 1, 2) + '-' +  
            fillZeros(d.getDate(), 2) + ' ' +  
            fillZeros(d.getHours(), 2) + ':' +  
            fillZeros(d.getMinutes(), 2) + ':' +  
            fillZeros(d.getSeconds(), 2);  
    return s;  
} 
 
function openModal(modalname){
  document.get	  
  $("#modal").fadeIn(300);
  $("."+modalname).fadeIn(300,function(){
	var list = new Array();		
	<c:forEach items="${addList}" var="alist">
		list.push('${alist.timelog}');
		list.push('${alist.pm_m_userid}');
		list.push('${alist.pm_g_bidprice}');
	</c:forEach>
	});
}

$("#modal, .close").on('click',function(){
  $("#modal").fadeOut(300);
  $(".modal-con").fadeOut(300);	  
});

var timeout = new Date("${goods.endDate}").getTime();
var nowTime = new Date().getTime();
var resultTime = timeout - nowTime;

if(resultTime < 0){
	alert("입찰이 마감된 상품입니다.");
} else {
	$(document).ready(function(){
		$("#bidding").click(function(){	
			var stack = document.getElementById('bidprice_input').value;
			if((stack%10) != 0){ // 최소 입찰가 단위 10원부터 배팅가능 (1~9원 배팅 X)
				alert("10단위 적어주세요");
				document.getElementById('bidprice_input').focus();
				return false;	
			}
			var info = {"pm_g_gno":${goods.gno},/* 입찰시간 */"pm_m_userid": '${id}'/* 현재 로그인되어있는 아이디(세션) */,"pm_g_bidprice": document.getElementById('bidprice_input').value /* 입찰가 */,"timelog": getNowTimeStamp()/* 입찰한 시간 */};
			var check = confirm("입찰하시겠습니까?");
			if(check){				
				$.ajax({
					url: '/goods/bidding',
					type: 'POST',				
					data: info,					
					success: function(data){															
						if(data.pm_g_bidprice == undefined){
							alert("현재 물품의 최대입찰가 보다 낮습니다.");
							document.getElementById('bidprice_input').focus();
							return;
						}
						/* if(data["pm_g_bidprice"] > info.pm_g_bidprice) */							
						/* alert(info.timelog); */	
						/* $('#ppap').before("<tr><td>"+ date.timelog + "</td><td>" + data["pm_m_userid"] + "</td><td>" + data.pm_g_bidprice +"</td></tr>"); */																
						$('#ppap').after("<tr class='mo2'><td>"+ (info.timelog+".0") + "</td><td>" + info.pm_m_userid + "</td><td>" + info.pm_g_bidprice +"</td></tr>");																
						// alert(data[0].pm_g_gno); 데이터 받아오는 코드					
					},
					error: function(data){
						alert("입찰가를 입력해주세요!");	
						document.getElementById('bidprice_input').focus();				
					}
				});
			} 
			/* alert("입찰테스트"); */			
		});				
	});
}


function getExit(){
	location.reload(true);
}

//마감 카운트다운
var countDownDate = new Date("${goods.endDate}").getTime(); // 1초간격 갱신 
var x = setInterval(function() { 
var now = new Date().getTime(); // 오늘 날짜 등록 
var distance = countDownDate - now; // 마감일자-현재일자
var isPause = false;
var d = Math.floor(distance / (1000 * 60 * 60 * 24)); 
var h = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
var m = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));  
var s = Math.floor((distance % (1000 * 60)) / 1000);

document.getElementById("d-day").innerHTML = "<span style='color: red;'>" + d +"일 " + h + "시간 " + m + "분 " + s + "초 </span>남았습니다."; 

// 마감기한 종료
if(distance < 0) {
	clearInterval(x);
    document.getElementById("d-day").innerHTML = "입찰이 마감되었습니다";
	}
}, 1000);
//태준 sns공유하기
function sharing(){
	window.open("http://www.facebook.com/sharer/sharer.php?u=http://itwillbs12.cafe24.com/goods/detail?gno="+${goods.gno});
}

</script>
</body>