<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String id = (String) session.getAttribute("id"); 
%> 

<!-- 좋아요버튼  -->
<span>
    <input type="checkbox" id="checkbox"/>
    <label for="checkbox">
      <svg id="heart-svg" viewBox="467 392 58 57" xmlns="http://www.w3.org/2000/svg">
        <g id="Group" fill="none" fill-rule="evenodd" transform="translate(467 392)">
          <path d="M29.144 20.773c-.063-.13-4.227-8.67-11.44-2.59C7.63 28.795 28.94 43.256 29.143 43.394c.204-.138 21.513-14.6 11.44-25.213-7.214-6.08-11.377 2.46-11.44 2.59z" 
          		id="heart" fill="#AAB8C2"/>
          <circle id="main-circ" fill="#E2264D" opacity="0" cx="29.5" cy="29.5" r="1.5"/>

          <g id="grp7" opacity="0" transform="translate(7 6)">
            <circle id="oval1" fill="#9CD8C3" cx="2" cy="6" r="2"/>
            <circle id="oval2" fill="#8CE8C3" cx="5" cy="2" r="2"/>
          </g>

          <g id="grp6" opacity="0" transform="translate(0 28)">
            <circle id="oval1" fill="#CC8EF5" cx="2" cy="7" r="2"/>
            <circle id="oval2" fill="#91D2FA" cx="3" cy="2" r="2"/>
          </g>

          <g id="grp3" opacity="0" transform="translate(52 28)">
            <circle id="oval2" fill="#9CD8C3" cx="2" cy="7" r="2"/>
            <circle id="oval1" fill="#8CE8C3" cx="4" cy="2" r="2"/>
          </g>

          <g id="grp2" opacity="0" transform="translate(44 6)">
            <circle id="oval2" fill="#CC8EF5" cx="5" cy="6" r="2"/>
            <circle id="oval1" fill="#CC8EF5" cx="2" cy="2" r="2"/>
          </g>

          <g id="grp5" opacity="0" transform="translate(14 50)">
            <circle id="oval1" fill="#91D2FA" cx="6" cy="5" r="2"/>
            <circle id="oval2" fill="#91D2FA" cx="2" cy="2" r="2"/>
          </g>

          <g id="grp4" opacity="0" transform="translate(35 50)">
            <circle id="oval1" fill="#F48EA7" cx="6" cy="5" r="2"/>
            <circle id="oval2" fill="#F48EA7" cx="2" cy="2" r="2"/>
          </g>

          <g id="grp1" opacity="0" transform="translate(24)">
            <circle id="oval1" fill="#9FC7FA" cx="2.5" cy="3" r="2"/>
            <circle id="oval2" fill="#9FC7FA" cx="7.5" cy="2" r="2"/>
          </g>
        </g>
      </svg>
    </label>
</span>

<script type="text/javascript">
// 좋아요 버튼 처리
$(document).ready(function(){
	let gno = ${param.gno};

	//좋아요유지
	let isClickedLikeBtn = ${isClickedLikeBtn};
	if(isClickedLikeBtn > 0){
		$('#checkbox').prop("checked", true);
	}
	
	//좋아요버튼 클릭시
	$('#Group').on('click',function(){		
	let likeId = '<%=id %>';
		    
	  $.ajax({
	  type:'POST',
	  url: "/goods/likes", 
	  data: {
	   	   gno: gno,
		   l_g_gno : gno,
		   l_m_id : likeId,
		   l_m_actionstatus: 0,
	      },
		  success: function(data){
				if(data == "likeClick" ){  
					//alert("좋아요! 내경매에서 확인할 수 있습니다.");
				} else if(data == "cancel"){ 
					//alert("좋아요가 취소되었습니다.");
				}
			},  	  
		  error:function(error){
					alert("예기치 못한 에러가 발생했습니다. 재접속해주세요")
			}, 
		});//end of ajax
	});//end of click func
});
</script>












