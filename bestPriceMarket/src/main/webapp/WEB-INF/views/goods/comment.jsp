<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
.updateBtnCustom{
	width:60px;padding-right:0px;padding-left:0px;display:inline;background:#FFC107;border:solid 2px #FFC107;
}
.deleteBtnCustom{
	width:60px;padding-right:0px;padding-left:0px;display:inline;background:#DC3545;border:solid 2px #DC3545;
}
.collapseTextareaCss{
	width:100%; border: 0;
}
.collapseInputCss{
	width:100%; border: 0;
}
</style>
<body>
<!-- 상품문의 댓글리스트 -->
<hr>
<table id="cmtList" class="container">
	<colgroup>
		<col width="7%" />
		<col width="15%" />
		<col width="*" />
		<col width="15%" />
		<col width="20%" />
	</colgroup>
	<thead>
	<tr>
		<th></th>
		<th>아이디</th>
		<th>문의내용</th>
		<th>작성일</th>
		<th></th>
	</tr>
	</thead>
	<tbody id="ppap2">
	<c:forEach items="${cmtList}" var="cmt">
		<tr id="tr_${cmt.c_num}">
			<td><input type="hidden" id="td_c_num_${cmt.c_num}" class="collapseInputCss" value="${cmt.c_num}" readonly></td>
			<td>${fn:substring(cmt.c_m_id,0, 4)}*****</td>
			<td><textarea class="collapseTextareaCss" id="td_c_content_${cmt.c_num}" rows="3" readonly>${cmt.c_content}</textarea></td>
			<td><fmt:formatDate value="${cmt.c_regdate}" pattern="yyyy-MM-dd" /></td>
			<td>
				<c:if test="${id == cmt.c_m_id || id == 'admin' || id == goods.g_m_id}">
					<c:if test="${cmt.c_seq == 0 }">
					<button class="round-black-btn" id="rereplyBtn_${cmt.c_num}" onclick="rereplyCmt(${cmt.c_num});">댓글</button><br>
					</c:if>
				<button class="round-black-btn updateBtnCustom" id="updateBtn_${cmt.c_num}" onclick="updateCmt(${cmt.c_num});"> 수정 </button>
				<button class="round-black-btn deleteBtnCustom" onclick="deleteCmt(${cmt.c_num});">삭제</button>
				</c:if>
			</td>
		</tr> 
	</c:forEach>
	</tbody>
</table>

<script type="text/javascript">
//공통 변수 초기화
let gno = ${param.gno};
let ck_c_content = "";
let ck_c_num = 0;

//오늘일자 포맷팅
let today = new Date();
let dd = today.getDate();
let mm = today.getMonth()+1; //January is 0!
let yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 
	if(mm<10) {
	    mm='0'+mm
	} 

today = yyyy+'-'+mm+'-'+dd;

//문의하기 토글
$('#reviewToggle').hide();
$('#reviewToggleBtn').click(function(){
	$('#reviewToggle').slideToggle();
});

//대댓글 버튼 클릭시
function rereplyCmt(c_num){
	$('#reviewToggle').show();
	$('#cmtBtn').hide();
	$('#recmtBtn').show();	
	document.getElementById("review").scrollIntoView();
	ck_c_num = c_num;
	$("#recmtBtn").click({param: c_num}, rereplyParam);	
};//대댓글쓰기 버튼 끝

//대댓글등록 파라미터 가져가기
function rereplyParam(e, param){
	let c_content = document.frCmt.c_content.value;
	let c_m_idFormat = document.frCmt.c_m_id.value.substring(0,4).concat('*****');
	ck_c_num = e.data.param;

	//대댓글내용없는 경우
	if(c_content == ""){ 
		alert("댓글 내용을 입력해주세요");
		document.getElementById('c_content').focus();
		return false;	
	}

	let info = {
			c_m_id: c_m_idFormat,
			c_g_gno: gno,
			c_content: c_content,
	};
	
	$.ajax({
	  type: "post", //데이터를 보낼 방식
	  url: "/detail/comment/rereply", //데이터를 보낼 url
  	  data: {
  	  	c_ref: ck_c_num,
  		c_m_id: document.frCmt.c_m_id.value,
		c_g_gno: document.frCmt.c_g_gno.value,
		c_content: c_content
  	  	  },	 
	  success: function(data){//데이터를 보내는 것이 성공했을 시 출력되는 메시지
	  if(data){
		alert("댓글이 성공적으로 작성되었습니다.");
		$("#c_content").val("");
		$('#tr_'+ck_c_num).after("<tr><td> </td><td>"+info.c_m_id+"</td><td>┗답글:"+info.c_content+"</td><td>"+today+"</td></tr>");
      }else{
		alert("작성이 실패했습니다. 다시 시도하세요.")
	  }
    },
    error:function(request,status,error){
        alert("예상치 못한 에러가 발생했습니다. 다시 시도하세요");
    }
	});
};//대댓글등록 파라미터 가져가기 끝

//문의하기 버튼 클릭시
function clickedCmtBtn(){
	let c_content = document.frCmt.c_content.value;
	let c_m_idFormat = document.frCmt.c_m_id.value.substring(0,4).concat('*****');
	
	let info = {
			c_m_id: c_m_idFormat,
			c_g_gno: document.frCmt.c_g_gno.value,
			c_content: c_content,
	};
	
	//문의내용없는 경우
	if(c_content == ""){ 
		alert("내용을 입력해주세요");
		document.getElementById('c_content').focus();
		return false;	
	}

	$.ajax({
	  type: "post", //데이터를 보낼 방식
	  url: "/detail/comment/insert", //데이터를 보낼 url
  	  data: {
  		c_m_id: document.frCmt.c_m_id.value,
		c_g_gno: document.frCmt.c_g_gno.value,
		c_content: c_content
  	  	  },	 
	  success: function(data){//데이터를 보내는 것이 성공했을 시 출력되는 메시지
	  if(data){
		alert("문의글이 성공적으로 등록되었습니다.");
		$("#c_content").val("");
		$("#ppap2").after("<tr><td> </td><td>"+info.c_m_id+"</td><td>"+info.c_content+"</td><td>"+today+"</td>"
				+"<td><button class='round-black-btn updateBtnCustom' id='updateBtn_"+data+"' onclick='updateCmt("+data+");'> 수정 </button>"
				+"<button class='round-black-btn deleteBtnCustom' style='margin-left: 5px;' onclick='deleteCmt("+data+");'>삭제</button></td></tr>"
				);
      }else{
		alert("문의글 등록에 실패했습니다. 다시 시도하세요.")
	  }
    },
    error:function(request,status,error){
        alert("예상치 못한 에러가 발생했습니다. 재접속하세요");
    }
	});
};//문의하기 버튼 끝

//댓글수정버튼 클릭시
let isFirstClick = true;
function updateCmt(c_num){	
	if(isFirstClick){
		document.getElementById('updateBtn_'+c_num).innerHTML = '등록';
		document.getElementById('td_c_content_'+c_num).readOnly = false;
		isFirstClick = false;
	}else{
		ck_c_content = document.getElementById('td_c_content_'+c_num).value;
		ck_c_num = document.getElementById('td_c_num_'+c_num).value;

		$.ajax({
		  type: "post", //데이터를 보낼 방식
		  url: "/detail/comment/update", //데이터를 보낼 url
		  data: {
			gno: gno,
			c_g_gno: gno,
			c_num: ck_c_num,
			c_content: ck_c_content,
			},
	  	  success: function(data){//데이터를 보내는 것이 성공했을 시 출력되는 메시지
	  		  if(data){
	  			alert(data);
	          }else{
				alert("문의글 수정에 실패했습니다. 다시 시도하세요.")
		      }
	       },
	       error:function(data){
		       alert("예상치 못한 에러가 발생했습니다. 재접속하세요");
	       }
		});
		document.getElementById('updateBtn_'+c_num).innerHTML = '수정';
		document.getElementById('td_c_content_'+c_num).readOnly = true;
		isFirstClick = true;
	}
}//댓글수정버튼 끝

//삭제하기 버튼 클릭시
function deleteCmt(c_num){
	ck_c_num = document.getElementById('td_c_num_'+c_num).value;
	
	$.ajax({
		  type: "post", //데이터를 보낼 방식
		  url: "/detail/comment/delete", //데이터를 보낼 url
		  data: {
			gno: gno,
			c_g_gno: gno,
			c_num: ck_c_num,
			c_content: '관리자 또는 작성자에 의해 삭제된 댓글입니다.',
		  },
	  	  success: function(data){//데이터를 보내는 것이 성공했을 시 출력되는 메시지
	  		  if(data){
	  			alert(data);
	  			$("#td_c_content_"+ck_c_num).val("관리자 또는 작성자에 의해 삭제된 댓글입니다.");
	          }else{
				alert("문의글 삭제에 실패했습니다. 다시 시도하세요.");
		      }
	       },
	       error:function(data){
		       alert("예상치 못한 에러가 발생했습니다. 재접속하세요");
	       }
	});
}//삭제하기 버튼 끝
</script>

</body>