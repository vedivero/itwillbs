<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 | 회원가입</title>
<!-- bootstrap css  -->
<link href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css' rel="stylesheet">
<link href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css' rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/loginandjoin/loginandjoin.css" rel="stylesheet">

<!-- Bootstrap js -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/loginandjoin/loginandjoin.js'></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
%>
<div class="modal login" id="loginModal" style="overflow:auto;">
    <div class="modal-dialog login animated">
        <div class="modal-content">
            <div class="modal-header"> <h4 class="modal-title">로그인</h4> </div>
            <div class="modal-body">
                <div class="box">
                    <div class="content">
                        <div class="social"> 
                         	<span id="google_login" class="circle google" onclick="init();"> <i class="fa fa-google-plus fa-fw"></i> </span> 
                         	<a class="circle github" href="#"> <i class="fa fa-github fa-fw"></i> </a> 
                        	<a id="facebook_login" class="circle facebook" href="#"> <i class="fa fa-facebook fa-fw"></i> </a> 
                        </div>
                        <div class="division">
                            <div class="line l"></div> <span> 또는 </span>
                            <div class="line r"></div>
                        </div>
                        <div class="error"></div>
                        <div class="form loginBox">
                            <form action="" method="post" accept-charset="UTF-8" name="frlogin"> 
                            	<input id="id" class="form-control" type="text" placeholder="아이디" name="id" required> 
                            	<input id="pw" class="form-control" type="password" placeholder="비밀번호" name="pw" required> 
                            </form>
                            	<input class="btn btn-default btn-login" type="submit" value="로그인" onclick="loginAjax()"> 
                            	<input class="btn btn-default btn-login" type="button" value="메인으로" onclick="location.href='/member/main'" style="width:157px;height:40px;margin-top:5px;padding:0;display:inline;"> 
                            	<input class="btn btn-default btn-login" type="button" value="회원가입" onclick="javascript: showRegisterForm();" style="width:157px;height:40px;margin-top:5px;padding:0;display:inline;"> 
                        </div>
                    </div>
                </div>
                <div class="box">
                    <div class="content registerBox" style="display:none;">
                        <div class="form">
                            <form action="/member/join" method="post" html="{:multipart=>true}" data-remote="true" accept-charset="UTF-8" name="frJoin"> 
                            	<input id="id" class="form-control" type="text" placeholder="아이디(영문,숫자혼용)" name="id" style="width:204px;margin:0 0 5px 0;padding:12 0;display:inline;" required> 
								<input id="idCheckBtn" type="button" value="아이디중복확인" class="btn btn-default btn-register" style="width:110px;height:50px;margin:0;padding:0;display:inline;"><br>
                            		<p class="idCheck" style="margin-bottom:0;"><span class="idCheckMsg" style="font-size:12px; font-weight:bold;"></span></p>
                            	<input id="username" class="form-control" type="text" placeholder="이름" name="username" required> 
                            	<input id="pw" class="form-control" type="password" onkeyup="pwValCheck()" placeholder="비밀번호(영문,숫자,특수문자혼용)" name="pw" required> 
                            		<span class="pwValCheckMsg" style="font-size:12px; font-weight:bold;"></span>
                            	<input id="pw_confirmation" class="form-control" type="password" onkeyup="pwCheck()" placeholder="비밀번호 재확인" name="password_confirmation" required> 
                            		<span class="pwCheckMsg" style="font-size:12px; font-weight:bold;"></span>
                            	<input id="email" class="form-control" type="email" placeholder="이메일" name="email" required style="height:48px;padding:13px 12px;margin-bottom:5px;color:black;">
                            	<input id="phone" class="form-control" type="text" placeholder="전화번호 예)01012345678" name="phone" required>                      
								<input type="text" id="sample4_postcode" class="form-control" placeholder="우편번호" style="width:204px;margin:0 0 5px 0;padding:12 0;display:inline;" readonly>
								<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" class="btn btn-default btn-register" style="width:110px;height:50px;margin:0;padding:0;display:inline;"><br>
								<input type="text" id="sample4_roadAddress" class="form-control" placeholder="도로명주소" name="addr1" readonly required>
								<input type="hidden" id="sample4_jibunAddress" class="form-control" placeholder="지번주소">
								<input type="text" id="sample4_detailAddress" class="form-control" placeholder="상세주소" name="addr2">
								<input type="hidden" id="sample4_extraAddress" class="form-control" placeholder="참고항목">
								<span id="guide" style="color:#999;display:none;visibility:hidden;"></span>
                            	<input class="btn btn-default btn-register" type="button" value="회원가입" name="commit" id="submitBtn" onclick="signUpValidation()" > 
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="forgot login-footer"> <span>비밀번호를 잊어버렸다면 </span><a href="/member/findpw" style="color:black">비밀번호찾기</a></div>
                <div class="forgot register-footer" style="display:none"> <span>이미 회원이라면 </span> <a href="javascript: showLoginForm();"style="color:black">로그인</a> </div>
            </div>
        </div>
    </div>
</div>

<!-- 아이디중복체크  -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<!-- 주소API  -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- google signin api -->
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
<!-- <script src="https://apis.google.com/js/platform.js" async defer></script> -->

<script type="text/javascript">
// 아이디 중복체크
let isIdCheckBtn = false;
$("#idCheckBtn").click(function(){  
	let d = document.frJoin.id.value;
	
	$.ajax({
	  url : "/member/idCheck",
	  type : "post",
	  data : { id : d },
	  success : function(data) {
		if(data == 1) {
			$(".idCheck .idCheckMsg").css({visibility: 'visible', display: 'block', color:'red'}).text("이미 사용중인 아이디입니다.");
		} else {
			if(d == ""){ 
				$(".idCheck .idCheckMsg").css({visibility: 'visible', display: 'block', color:'red'}).text("아이디를 입력하세요.");
			}else if(d.length < 4){
				$(".idCheck .idCheckMsg").css({visibility: 'visible', display: 'block', color:'red'}).text("아이디를 4자리이상 입력하세요.");
			}else{
				$(".idCheck .idCheckMsg").css({visibility: 'visible', display: 'block', color:'blue'}).text("사용 가능한 아이디입니다.");
				isIdCheckBtn = true;	
				console.log("아이디체크"+isIdCheckBtn);			
			}
	    }
	  }, error : function(){ console.log("아이디 중복확인 실패"); }
	 }); 
});

console.log("아이디체크"+isIdCheckBtn);

//비밀번호 체크 
let isPwValCheckF = false;
function pwValCheck(){
    let pwd1 = document.frJoin.pw.value;
    let reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
    
	//비밀번호 유효성체크
    if(pwd1.length < 8){
        $('.pwValCheckMsg').css({visibility: 'visible', display: 'block', color:'red'}).text("비밀번호 8자리이상 입력하세요.");
   	 }else if(reg.test(pwd1) == false){
		$('.pwValCheckMsg').css({visibility: 'visible', display: 'block', color:'red'}).text("비밀번호는 숫자/영문/특수문자를 모두 포함해야합니다.");           
   	 }else {
		$('.pwValCheckMsg').css({visibility: 'hidden', display: 'block'}).text("");
		isPwValCheckF = true;
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
    } else { //비번이 불일치할 경우
   		$('.pwCheckMsg').css({visibility: 'visible', display: 'block', color:'red'}).text("비밀번호가 일치하지않습니다.");           
	   	$('#submitBtn').css('background', 'rgb(33, 37, 41, .5)').attr("disabled", true);	
	}
}

console.log("비번체크"+isPwValCheckF);

// 회원가입 유효성 체크
function signUpValidation(){
	let f = document.frJoin;
	let userId = f.id.value;
	let userPw = f.pw.value;
	let userPwCheck = f.password_confirmation.value;
	let userName = f.username.value;
	let email = f.email.value;
	let phone = f.phone.value;
	let addr1 = f.addr1.value;
	
	if(!userId || isIdCheckBtn == false){
		alert("아이디 중복확인버튼을 눌러주세요.");
		$("#id").focus();
	}else if(userId.length < 4 ){
		alert("아이디는 4글자 이상이어야합니다.");
		$("#id").focus();
	}else if(!userName){
		alert("이름 입력은 필수입니다.");
		$("#username").focus();
	}else if(!userPw ){
		alert("비밀번호 입력은 필수입니다.");
		$("#pw").focus();
	}else if(userPw.length < 8 || isPwValCheckF == false){
		alert("유효한 비밀번호를 입력하세요.");
		$("#password_confirmation").focus();
	}else if(!email){
		alert("이메일 입력은 필수입니다.");
		$("#email").focus();
	}else if(!phone){
		alert("전화번호 입력은 필수입니다.");
		$("#phone").focus();
	}else if(!addr1){
		alert("우편번호찾기버튼을 눌러주세요.");
		$("#addr1").focus();
	}else {
		alert("성공적으로 회원가입 되었습니다.")
		document.frJoin.submit();
	}	
}

// google signin API
var googleUser = {};
function init() {
	 gapi.load('auth2', function() {
	  console.log("init()시작");
	  auth2 = gapi.auth2.init({
	        client_id: '344387180411-fk8bjqrfa46sk96fsl9jr0u7bn5rc3o9.apps.googleusercontent.com',
	        cookiepolicy: 'single_host_origin',
	      });
	      attachSignin(document.getElementById('google_login'));
	 });
}

//google signin API2
function attachSignin(element) {
    auth2.attachClickHandler(element, {},
        function(googleUser) {
    	var profile = googleUser.getBasicProfile();
    	var id_token = googleUser.getAuthResponse().id_token;
	  	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  	  console.log('ID토큰: ' + id_token);
	  	  console.log('Name: ' + profile.getName());
	  	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
			$(function() {
				$.ajax({
				    url: '/member/loginGoogle',
				    type: 'post',
				    data: {"id": profile.getEmail(),
				        "pw": profile.getId(),
				        "username": profile.getName(),
						"email": profile.getEmail()
					    },
				    success: function (data) {
				            alert("구글아이디로 로그인 되었습니다");
				            location.href="/member/main";
				        }
				});
			})
        }, function(error) {
          alert("구글아이디 로그인이 실패했습니다.");
        });
  }

// 카카오API
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                guideTextBox.style.display = 'block';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                guideTextBox.style.display = 'block';
            } else {
                guideTextBox.innerHTML = '';
                guideTextBox.style.display = 'none';
            }
        }
    }).open();
}
</script>
</body>
</html>