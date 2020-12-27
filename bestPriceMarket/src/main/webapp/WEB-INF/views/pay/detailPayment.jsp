<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
<style>
.payMove {
  background-color: #212529;
  font: bold;
  color: white;
  transition-duration: 0.4s;
  border: 2px solid;
  border-radius: 5px;
  padding: 10px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}

.payMove:hover {
  background-color: white; /* Green */
  color: black;
}
</style>
</head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!-- 카카오페이 결제모듈 스크립트 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<body>
<!-- 주문자 정보 -->
<form name="order_form">
<div class="container">
	<div class="row">
        <div class="col-sm-12">
            <legend>BestPrice</legend>
        </div>
        <!-- panel preview1 -->
        <div class="col-sm-5">
            <h4>주문자 정보</h4>
            <div class="panel panel-default">
                <div class="panel-body form-horizontal payment-form">
                    <div class="form-group">
                        <label for="concept" class="col-sm-3 control-label">이름</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="username" name="username" value="${memVO.username}" readonly>
                        </div>
                    </div>
                    <%-- <div class="form-group">
                        <label for="description" class="col-sm-3 control-label">ID</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="id" name="id" value="${memVO.id}" readonly>
                        </div>
                    </div>  --%>
                    <div class="form-group">
                        <label for="amount" class="col-sm-3 control-label">연락처</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="phone" name="phone" value="${memVO.phone}" readonly>
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label for="date" class="col-sm-3 control-label">주소</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="addr1" name="addr1" value="${memVO.addr1}" readonly>                            
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="date" class="col-sm-3 control-label">상세주소</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="addr2" name="addr2" value="${memVO.addr2}" readonly>                            
                        </div>
                    </div>                  
                </div>
            </div>            
        </div> <!-- / panel preview1 -->  
        <!-- panel preview2 -->
        <div class="col-sm-5">
            <h4>배송지 정보</h4>
            <div class="panel panel-default">
                <div class="panel-body form-horizontal payment-form">
                    <div class="form-group">
                        <label for="concept" class="col-sm-3 control-label">이름</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="re_username" name="re_username">
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label for="amount" class="col-sm-3 control-label">연락처</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="re_phone" name="re_phone">
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label for="date" class="col-sm-3 control-label">주소</label>
                        <div class="col-sm-9">
                           <input id="postcode1" type="hidden" value="" style="width:50px;" readonly/>
                           <input id="postcode2" type="hidden" value="" style="width:50px;" readonly/>
                           <input id="zonecode" type="hidden" value="" style="width:50px;" readonly/>
                           <input type="text" class="form-control" id="re_addr1" name="re_addr1" readonly style="width:218px;display: inline-block;">  
                           <input type="button" class="payMove" onClick="openDaumZipAddress();" value = "주소 찾기" style="padding: 8px 10px;font-size: 14px;">                              
                        </div>
                        
                    </div> 
                    <div class="form-group">
                        <label for="date" class="col-sm-3 control-label">상세주소</label>
                        <div class="col-sm-9">                        	
                            <input type="text" class="form-control" id="re_addr2" name="re_addr2">                           
                        </div>
                    </div>
                     <div class="form-group">
                        <div class="col-sm-12 text-right">
                            <button type="button" class="btn btn-default preview-add-button" onclick="javascript:same_info(document.order_form)">
                                <span class="glyphicon glyphicon-plus"></span> 주문자 정보와 동일하게
                            </button>
                        </div>
                    </div>                     
                </div>
            </div>            
        </div> <!-- / panel preview2 -->       
	</div>
</div><!-- 주문자 정보 -->

<!-- 우편번호 테스트 -->

<!-- 상품 정보 -->
<div class="container">

      <!-- Page Heading -->
      <h1 class="my-4">상품 정보</h1>

      <!-- Project One -->
      <div class="row">
        <div class="col-md-7">
          <a href="#">
            <img class="img-fluid rounded mb-3 mb-md-0" src="http://placehold.it/700x300" alt=""> <!-- goods테이블 사진 -->
          </a>
        </div>
    </div>
</div>
<br>
<div class="container">           
  <table class="table">
    <thead>
      <tr>
        <th>상품명</th>
        <th>상품번호</th>
        <th>금액</th>
      </tr>
    </thead>
    <tbody>
      <tr>      	
        <td>${gvo.gname}<!-- goods테이블 상품명 --></td>
        <td>${gvo.gno}<!-- goods테이블 상품번호 --></td>
        <td>${gvo.bidprice}원<!-- goods테이블 ???? --></td>
      </tr>      
    </tbody>
  </table>
</div><!-- 상품 정보 -->

<br>
<!-- 결제창  -->
<div class="container">
  <h2>최종결제</h2>             
  <table class="table">
    <thead>
      <tr>
        <th>상품명</th>
        <th>결제금액</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${gvo.gname}</td>
        <td>${gvo.bidprice}원</td>
        <td width="250px;">
        <div style="width: 270px; height: 50px;">
        <input type="hidden" name="gname" value="${gvo.gname}">
      	<input type="hidden" name="gno" value="${gvo.gno}">
      	<input type="hidden" name="lowestprice" value="${gvo.bidprice}">
        <input type="button" value="결제하기" id="btn1" class="payMove"> <input type="reset" value="취소하기" class="payMove">
         <!-- <a class="btn btn-primary" href="/pay/order">결제하기</a>&nbsp;<a class="btn btn-primary" href="#">취소하기</a> -->
        </div>
        </td>
      </tr>
     
    </tbody>
  </table>
</div><!-- 결제창  -->
</form>


<script type="text/javascript">
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
            amount : ${gvo.bidprice},       
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
    		    		p_g_bidprice : ${gvo.bidprice},
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
</html>
