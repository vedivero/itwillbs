<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">
<!-- <link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css' rel="stylesheet"> -->
<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css' rel="stylesheet">
<link href='${pageContext.request.contextPath}/resources/loginandjoin/loginandjoin.css' rel="stylesheet">
<style>

.btn {
    border-radius: 4px !important; 
    background: #212529 !important;
    color: #fff !important;
    padding: 7px 45px !important;
    display: inline-block !important;
    margin-top: 20px !important;
    border: solid 2px #212529 !important; 
    transition: all 0.5s ease-in-out 0s !important;
}
.btn:hover,
.btn:focus {
    background: transparent !important;
    color: #212529 !important;
    text-decoration: none !important;
}
.py-5  {
   width:100%;
   position:absolute;
   bottom:0;
  text-align: center;
}
.form {
  display: flex;
  height: 100vh;
  justify-content: center;
  align-items: center;
  border: solid 1px gray;
  
 }
.front{
 width: 202px;
}
</style>
</head>
<body>
<!-- 헤더 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더 -->
  
  <!-- Page Content -->
  <div class="container">
    <div class="row">
      <div class="col-lg-3">
        <h1 class="my-4">My Page</h1>
        <div class="list-group">
      	  <a href="/basket/listPage" class="list-group-item">좋아요</a>
          <a href="/member/update" class="list-group-item">회원 수정</a>	
          <a href="/member/delete" class="list-group-item">회원 탈퇴</a>
          <a href="/member/changePw" class="list-group-item">비밀번호 수정</a>
        </div>
      </div>
      
      <div class="col-lg-9">
	    <fieldset style="margin-top: 50px;">
	    <legend> 비밀번호 수정 </legend>
	    <form action="/member/changePw" method="post" name="frJoin">
	     <span class="front" style="margin-right: 34px;">아이디</span>  <input type="text" name="id" class="form-control" value="${memberVO.id }" style="width:226px;display:inline; text-align: center;" readonly><br>
	      <span class="front" style="margin-right: 17px; ">비밀번호</span>  <input id="pw" class="form-control" type="password" onkeyup="pwValCheck()" placeholder="비밀번호(영문,숫자,특수문자혼용)" name="pw" style="width:226px;display:inline;" required>  <br>
				<span class="pwValCheckMsg" style="font-size:12px; font-weight:bold;"></span>
		 <span class="front" style="margin-right: 85px; "></span>	<input id="pw_confirmation" class="form-control" type="password" onkeyup="pwCheck()" placeholder="비밀번호 재확인" name="password_confirmation" style="width:226px;display:inline;" disabled> 							     
	             <span class="pwCheckMsg" style="font-size:12px; font-weight:bold;"></span><br>
	      <input class="btn" type="submit" value="비밀번호수정" name="commit" id="submitBtn" disabled>          
	    </form>  
	  	</fieldset>
      </div>
    </div>
  </div>
  
  <script>
  function pwValCheck(){
      let pwd1 = document.frJoin.pw.value;
      let pwd2 = $("#pw_confirmation").val(); 	
      let reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
	  let message="";


		if(pwd2 != "" && pwd1 != ""){
			$('#submitBtn').css('background', 'rgb(33, 37, 41, .5)').attr("disabled", false);
		}
		
	//비밀번호 유효성체크
      if(pwd1.length < 8){
          $('.pwValCheckMsg').css({visibility: 'visible', display: 'block', color:'red'}).text("비밀번호 8자리이상 입력하세요.");
     	 }else if(reg.test(pwd1) == false){
  		  $('.pwValCheckMsg').css({visibility: 'visible', display: 'block', color:'red'}).text("비밀번호는 숫자/영문/특수문자를 모두 포함해야합니다.");           
  		 $('#pw_confirmation').attr("disabled", true);
    	 }else {
  		$('.pwValCheckMsg').css({visibility: 'hidden', display: 'block'}).text("");
  		 $('#pw_confirmation').removeAttr("disabled");
    	 }    
      }
 
  
// 비밀번호 일치여부 체크 
  function pwCheck(){
      let pwd1 = document.frJoin.pw.value;
      let pwd2 = $("#pw_confirmation").val();
      let reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

  	//비밀번호 일치여부체크
	  	if (pwd1 == pwd2) { //비번이 일치할 경우
      	$('.pwCheckMsg').css({visibility: 'visible', display: 'block', color:'blue'}).text("비밀번호가 일치합니다.");
  		$('#submitBtn').css({background: 'rgb(33, 37, 41, 1)', color:'#FFFFFF'}).attr("disabled", false);
	  	}else if(reg.test(pwd2) == false){
	  		$('.pwCheckMsg').css({visibility: 'visible', display: 'block', color:'red'}).text("비밀번호는 숫자/영문/특수문자를 모두 포함해야합니다.");           
	  		$('#submitBtn').css('background', 'rgb(33, 37, 41, .5)').attr("disabled", true);
		  	}else { //비번이 불일치할 경우
		  	$('.pwValCheckMsg').css({visibility: 'hidden', display: 'block'}).text("");
	     	$('.pwCheckMsg').css({visibility: 'visible', display: 'block', color:'red'}).text("비밀번호가 일치하지않습니다.");           
  	   	    $('#submitBtn').css('background', 'rgb(33, 37, 41, .5)').attr("disabled", true);	
  	   }
 
    }

  </script>
  			
  <!-- 푸터 -->
  <jsp:include page="../inc/bottom.jsp"/>
  <!-- 푸터 -->

  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

