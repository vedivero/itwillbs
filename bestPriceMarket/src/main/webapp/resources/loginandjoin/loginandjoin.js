$(document).ready(function(){
	openLoginModal();
});

function showRegisterForm(){
	$('.loginBox').fadeOut('fast',function(){
		$('.registerBox').fadeIn('fast');
		$('.login-footer').fadeOut('fast',function(){
			$('.register-footer').fadeIn('fast');
		});
		$('.modal-title').html('회원가입');
	});
	$('.error').removeClass('alert alert-danger').html('');
}

function showLoginForm(){
	$('#loginModal .registerBox').fadeOut('fast',function(){
		$('.loginBox').fadeIn('fast');
		$('.register-footer').fadeOut('fast',function(){
			$('.login-footer').fadeIn('fast');
		});
		$('.modal-title').html('로그인');
	});
	$('.error').removeClass('alert alert-danger').html('');
}

function openLoginModal(){
	showLoginForm();
/*	setTimeout(function(){
		$('#loginModal').modal('show');
	}, 230);*/
	$('#loginModal').show();
}

function openRegisterModal(){
	showRegisterForm();
/*	setTimeout(function(){
		$('#loginModal').modal('show');
	}, 230);*/
	$('#loginModal').show();
}

function loginAjax(){	
	$.ajax({
		method:"post",
		url: "/member/login",
		data: {
			id:document.frlogin.id.value,
			pw:document.frlogin.pw.value
		},
		success: function(data){
			if(data == "true"){
				alert("정상적으로 로그인되었습니다");
				window.location.replace("main");
			} else {
				shakeModal();
			}
		}, error: function(){ 
			shakeModal(); 
		}
	});
}

function shakeModal(){
	$('#loginModal .modal-dialog').addClass('shake');
	$('.error').addClass('alert alert-danger').html("잘못된 아이디/비밀번호입니다.");
	$('input[type="password"]').val('');
	setTimeout( function(){
		$('#loginModal .modal-dialog').removeClass('shake');
	}, 1000 );
}