<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<link href="../resources/pay/detailPayment.css" rel="stylesheet">

<!-- 카카오페이 결제모듈 스크립트 -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
<style type="text/css">
.container {
	width: 900px !important;
}
</style>
</head>
<body>
<%
	String id = (String)session.getAttribute("id");	
	if(id == null){%>
		<script type="text/javascript">
			alert("로그인후 이용해주세요.");
			history.back();
		</script>
	<%}
%>
	<!-- 헤더 -->
	<jsp:include page="../inc/top.jsp" />
	<!-- 헤더 -->
	<form name="order_form">
		<div class="container login-container" style="margin-bottom: 50px;">
			<div class="row">

				<div class="col-md-6 login-form-1">
					<h3>주문자 정보</h3>

					<div class="form-group">
						<input type="text" class="form-control" id="username"
							name="username" value="${memVO.username}" readonly />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="phone" name="phone"
							value="${memVO.phone}" readonly />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="addr1" name="addr1"
							value="${memVO.addr1}" readonly />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="addr2" name="addr2"
							value="${memVO.addr2}" readonly />
					</div>

				</div>
				<div class="col-md-6 login-form-2">

					<h3>배송지 정보</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름을 입력해주세요"
							id="re_username" name="re_username" />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="연락처를 입력해주세요"
							id="re_phone" name="re_phone" />
					</div>
					<div class="form-group" style="display: flex;">
						<input id="postcode1" type="hidden" value="" style="width: 50px;"
							readonly /> <input id="postcode2" type="hidden" value=""
							style="width: 50px;" readonly /> <input id="zonecode"
							type="hidden" value="" style="width: 50px;" readonly /> <input
							type="text" class="form-control" placeholder="주소를 입력해주세요"
							style="float: left; width: 300px;" id="re_addr1" name="re_addr1"
							readonly /> <input type="button" class="payMove"
							onClick="openDaumZipAddress();" value="주소 찾기"
							style="float: right; width: 100px; padding: 8px 10px; font-size: 14px; margin: auto;">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="상세주소를 입력해주세요"
							id="re_addr2" name="re_addr2" />
					</div>
					<div class="form-group">
						<button type="button" class="payMove"
							onclick="javascript:same_info(document.order_form)"
							style="margin-left: 184px;">
							<span class="glyphicon glyphicon-plus"></span> 주문자 정보와 동일하게
						</button>
					</div>


				</div>



				<br> <br>
				<div class="col-md-12">
					<h1 class="my-4">상품 정보</h1>
					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane fade show active" id="nav-home"
							role="tabpanel" aria-labelledby="nav-home-tab">
							<table class="table" cellspacing="0">
								<thead>
									<tr>
										<th>판매물품 명</th>
										<th>판매물품 번호</th>
										<th>판매물품 가격</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${gvo.gname}</td>
										<td>${gvo.gno}</td>
										<td>${gvo.finalprice}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<h1 class="my-4">최종 결제</h1>
					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane fade show active" id="nav-home"
							role="tabpanel" aria-labelledby="nav-home-tab">
							<table class="table" cellspacing="0">
								<thead>
									<tr>
										<th>판매물품 명</th>																			
										<th>판매물품 최종가격</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${gvo.gname}</a></td>																				
										<td>${gvo.finalprice}원</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<br>
			<div class="pay_ok" style="margin-left: 40%;">
				<input type="hidden" name="gname" value="${gvo.gname}"> <input
					type="hidden" name="gno" value="${gvo.gno}"> <input
					type="hidden" name="finalprice" value="${gvo.finalprice}">
				<input type="button" value="결제하기" id="btn1" class="payMove">
				<input type="button" value="취소하기" onclick="back()"
					class="payMoveFail">
			</div>
		</div>
	</form>


	<!-- 푸터 -->
	<jsp:include page="../inc/bottom.jsp" />
	<!-- 푸터 -->

	<script type="text/javascript">		
	function back() {
		var check = confirm("결제를 취소하시겠습니까?");
		if(check){
			history.back();
			}
	}
	
	function same_info(f) {
			f.re_username.value = f.username.value;
			f.re_phone.value = f.phone.value;
			f.re_addr1.value = f.addr1.value;
			f.re_addr2.value = f.addr2.value;
	}	

	function openDaumZipAddress() {

		new daum.Postcode({

			oncomplete:function(data) {

				jQuery("#postcode1").val(data.postcode1);

				jQuery("#postcode2").val(data.postcode2);

				jQuery("#zonecode").val(data.zonecode);

				jQuery("#re_addr1").val(data.address);

				jQuery("#re_addr2").focus();

				console.log(data);

			}

		}).open();

	}	
</script>



	<script>
	var re_username = document.order_form.re_username;
	var re_phone = document.order_form.re_phone;
	var re_addr1 = document.order_form.re_addr1;
	var re_addr2 = document.order_form.re_addr2;	
	
	
	
   $('#btn1').click(function(){
	   if(!re_username.value){
			alert("받는사람 이름을 입력해주세요.");
			$("#re_username").focus();
			return false;
	   }else if(!re_phone.value){
		   alert("연락가능한 연락처를 입력해주세요.");
			$("#re_phone").focus();
			return false;
	   }else if(!re_addr1.value){
		   alert("받으실 주소를 입력해주세요.");
			$("#re_addr1").focus();
			return false;	   		   
	   }else {
       		      	
    	var IMP = window.IMP; // 생략가능
        IMP.init('imp24656167'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용		
        // imp24656167 <- 내 식별코드
        var msg;
        
    	IMP.request_pay({
    	    pg : 'kakaopay',
    	    pay_method : 'card',
    	    merchant_uid : 'merchant_' + new Date().getTime(),
    	    name : '${gvo.gname}',
            amount : ${gvo.finalprice},       
            buyer_name : '${memVO.username}',
            buyer_tel : '${memVO.phone}',
            buyer_addr : '${memVO.addr1}'+' '+'${memVO.addr2}',
    	}, function(rsp) {
    	    if ( rsp.success ) {
    	    	//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
    	    	jQuery.ajax({
    	    		url: "/pay/payment", //cross-domain error가 발생하지 않도록 동일한 도메인으로 전송
    	    		type: 'POST',
    	    		dataType: 'json',
    	    		data: {
    		    		// imp_uid : rsp.imp_uid,
    		    		p_merchant_uid : 'merchant_' + new Date().getTime(),
    		    		p_g_gno : ${gvo.gno},
    		    		pg : 'kakaopay',
    		    		p_method : 'card',
    		    		p_buyer_name : '${memVO.username}',
    		    		p_buyer_tel : '${memVO.phone}',
    		    		p_g_gname : '${gvo.gname}',    		    		
    		    		p_g_bidprice : ${gvo.finalprice},
    		    		p_re_name : document.getElementById('re_username').value,
    		    		p_re_tel : document.getElementById('re_phone').value,
    		    		p_re_addr1 : document.getElementById('re_addr1').value,
    		    		p_re_addr2 : document.getElementById('re_addr2').value
    		    		//기타 필요한 데이터가 있으면 추가 전달
    	    		}
    	    	}).done(function(data) {
    	    		//[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
    	    		if ( everythings_fine ) {
    	    			var msg = '결제가 완료되었습니다.';
    	    			msg += '\n고유ID : ' + rsp.imp_uid;
    	    			msg += '\n상점 거래ID : ' + rsp.merchant_uid;
    	    			msg += '\결제 금액 : ' + rsp.paid_amount;
    	    			msg += '카드 승인번호 : ' + rsp.apply_num;

    	    			alert(msg);
    	    		} else {
    	    			//[3] 아직 제대로 결제가 되지 않았습니다.
    	    			//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
    	    		}    	    		
    	    	});    	    	    	    	
   
    	    	alert("결제 성공"); // 임시로 띄어놓음. alert없으면 DB에 insert처리하는 시간보다 빨리 처리되서 상품정보 출력X    	    	    		    	    	
        	    location.href="/pay/order?gno=" + ${gvo.gno}; 
        	           	    
    	    	
    	    } else {
    	        var msg = '결제에 실패하였습니다.';
    	        msg += '에러내용 : ' + rsp.error_msg;

    	        alert(msg);
    	    }     	     	   
    	});
	   }// else문 종료        
    });
    </script>
</body>
