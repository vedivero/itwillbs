<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
   $(function(){
       //alert("테스트 실행");

       // 1) 뷰에서 버튼 클릭
       $("#checkJson").click(function(){
    	   // 2) 전달할 데이터 생성 (JSON)
    	   var board ={bno:"99",
    	    	   writer:"itwill99",
    	    	   title:"title99",
    	    	   content:"content99"
    	    	   };
    	   var board2 ={bno:"99",
    	    	   writer:"itwill99",
    	    	   title:"title00",
    	    	   content:"content00"
    	    	   };
           // 3) RestController로 이동 (ajax-비동기방식)
           // 3-1) 전달할 데이터를 저장해서 전송-JSON타입
           $.ajax({
               // 글쓰기
               //type:"POST",
               //url:"${contextPath}/boards",
               // 글 수정
               type:"PUT",
               url:"${contextPath}/boards/99",
               contentType:"application/json",
               data: JSON.stringify(board2),
               success:function(){
                    alert("성공!");
                   },
               error:function(){
                    alert("실패!");
                   },
               complete:function(){
                     alert("성공/실패 후!");
                   }        
               

               });

           

           
           }); // 버튼클릭
      
       // 4) RestController 안에서 정보를 전달받는 동작을 구현
       //    정보 확인 
       

	   });


</script>


</head>
<body>
    <h1>WEB-INF/views/JSONtest2.jsp</h1>
    
    <input type="button" id="checkJson" value="회원 정보 전송(POST)">
    
    
    

</body>
</html>