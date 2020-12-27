<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<!-- Title Page-->
<title>베프마켓 - 친구같은 경매플랫폼</title>

<!-- Font special for pages-->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">

<!-- Main CSS-->
<link href="${pageContext.request.contextPath}/resources/goods/goods_css/goodsRegister.css" rel="stylesheet" media="all">
<link href="${pageContext.request.contextPath}/resources/goods/goods_css/goods_optionbar.css" rel="stylesheet">
    
<!-- Jquery JS-->
<script src='${pageContext.request.contextPath}/resources/goods/goods_js/jquery.min.js'></script>

<!-- Main JS-->
<script src='${pageContext.request.contextPath}/resources/goods/goods_js/global.js'></script>
<!-- <script src="js/global.js"></script> -->
    
<!-- 버튼 CSS -->
<link href="${pageContext.request.contextPath}/resources/goods/goods_css/goodsBtn.css" rel="stylesheet">
<!-- 버튼 CSS -->
    
<style type="text/css">  
#file{
	 border-radius: 4px; 
    background: #212529;
    color: #fff;
    padding: 7px 45px;
    display: inline-block;
    margin-top: 20px;
    border: solid 2px #212529; 
    transition: all 0.5s ease-in-out 0s;
}
</style>
    
<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
<!-- ck에디터 -->
<script src="/resources/ckeditor/ckeditor.js"></script>
<!-- ck에디터 -->
    
<script type="text/javascript">
	// 유효성 체크
	function goods_modify(){

		var category = document.fr.category.value;
		var gname = document.fr.gname.value;
		var content = document.fr.content.value;
		var lowestprice = document.fr.lowestprice.value;
		var endDate = document.fr.endDate.value;

		if(category == ""){
			alert("물품분류를 선택하세요.");
			document.fr.category.focus();
			return false;
		}
		

		if(gname == ""){
			alert("물품제목을 입력하세요.");
			document.fr.gname.focus();
			return false;
		}

		if(CKEDITOR.instances.content.getData() == '' 
			|| CKEDITOR.instances.content.getData().length == 0){
				alert("물품설명을 입력하세요.");
				$("#content").focus();
				return false;
		}


		if(file == ""){
			alert("섬네일 이미지를 등록하세요.");
			document.fr.file.focus();
			return false;
		}

		if(endDate == ""){
			alert("물품의 마감날짜를 입력하세요.");
			document.fr.endDate.focus();
			return false;
		}
		
		if(lowestprice == ""){
			alert("물품의 하한가를 입력하세요.");
			document.fr.lowestprice.focus();
			return false;
		}
	 
	}
	// 유효성 체크

	// category
	$(function(){
	  	$("#category").val("${goodsVO.category}").attr("selected","true");
    });
	// category

	// 파일 추가 삭제 동작
	 var fileNoArry = new Array();
     var fileNameArry = new Array();
     function fndel(value, name){

      fileNoArry.push(value);
      fileNameArry.push(name);
    	$("#fileNoDel").attr("value", fileNoArry);
    	 $("#fileNameDel").attr("value", fileNameArry);
    }
	// // 파일 추가 삭제 동작
</script>    
    
</head>
<body>

    <div class="page-wrapper bg-dark p-t-100 p-b-50">
        <div class="wrapper wrapper--w900">
            <div class="card card-6">
                <div class="card-heading">
                    <h2 class="title">물품 수정</h2>
                </div>
                
                <div class="card-body">

                 <form action ="/goods/modify" method="post" enctype="multipart/form-data" name="fr" role="form">
                    <input type="hidden" name="gno"  value="${goodsVO.gno}">
                    <input type="hidden" name="page" value="${cri.page}">
                    <input type="hidden" name="pageSize" value="${cri.pageSize}">
                    <input type="hidden" id="fileNoDel" name="fileNoDel[]" value=""> 
					<input type="hidden" id="fileNameDel" name="fileNameDel[]" value=""> 
                  <div class="form-row">
                  
                     <div class="name">물품분류</div>
                        <select class="bo_w_select" name="category" id="category">
                      		<option value="" selected>카테고리 선택</option>
                      		<option value="컴퓨터">컴퓨터</option>
                      		<option value="디지털">디지털</option>
                      		<option value="생활가전">생활가전</option>
                      		<option value="생활문구">생활문구</option>
                      		<option value="생활가구">생활가구</option>
                      		<option value="스포츠">스포츠</option>
                      		<option value="패션의류">패션의류</option>
                      		<option value="패션잡화">패션잡화</option>
                      		<option value="화장품">화장품</option>
                      		<option value="쥬얼리">쥬얼리</option>
                      	</select>
                    </div>
                    
                    <div class="form-row">
                      <div class="name">판매자</div>
                         <div class="value">
                           <input class="input--style-6" type="text" name="g_m_id" value="${id}" readonly><br>
                         </div>
                    </div>
                      
                    <div class="form-row">
                       <div class="name">물품제목</div>
                          <div class="value">
                             <input class="input--style-6" type="text" name="gname" value="${goodsVO.gname}" >
                          </div>
                       </div>
                        
                    <div class="form-row">
                       <div class="name">물품설명</div>
                           <div class="value"> 
                             <div class="input-group"> 
                                      <textarea rows="30" cols="400" id="content" name="content" >${goodsVO.content}</textarea> 
                             </div>  
                           </div>
                       </div>
                       
                    <!-- ck에디터 -->   
                    <script>
 						var ckeditor_config = {
 							width: 1000,	
 							height: 500,	
   							resize_enaleb : false,
   							enterMode : CKEDITOR.ENTER_BR,
   							shiftEnterMode : CKEDITOR.ENTER_P,
   							uploadUrl: "/goods/ckUpload",
   							filebrowserUploadUrl : "/goods/ckUpload"
 						};
 
						 CKEDITOR.replace("content", ckeditor_config);

					</script>
					<!-- ck 에디터 -->
                       
                 <!-- 섬네일 이미지업로드 -->      
                     <div class="form-row">
                       <div class="name">섬네일 이미지 등록</div>
                           <div class="value">
                              <div class="input-group js-input-file">
                              		<div id="fileIndex">
                              		  <c:forEach var="file" items="${file}" varStatus="var">
										<div>
											<input type="hidden" id="fno" name="fno_${var.index}" value="${file.fno }">
											<input type="hidden" id="f_name" name="f_name" value="fno_${var.index}">
											<a href="#" onclick="fn_fileDown('${file.f_name}'); return false;">${file.f_oname}</a>
											<input type="button" id="fileDel" onclick="fndel(${file.fno},fno_${var.index});" value="삭제">
											<br>
										</div>
									  </c:forEach>
                              		</div>
                              </div>
                          <!-- <div class="label--desc">상품 이미지를 업로드 해주세요. 파일 크기 최대 50M</div> -->
                         
                         	<button  type="button" class="fileAdd" id="file" onclick="fn_addFile()">파일추가</button> 
                          	
                       </div>  
                    </div>  
                <!-- 섬네일 이미지업로드 -->
                
          <script type="text/javascript">
        	// 파일 추가
			$(document).ready(function(){
			  var fileIndex = 1;
				 $(".fileAdd").on("click", function(){
					$("#fileIndex").append("<div><input type='file' id='file' style='float:left;' name='file_"+(fileIndex++)+"'>"+"</button>"+"<button type='button' style='float:right;' id='fileDel'>"+"삭제"+"</button></div>");

					});

				$(document).on("click","#fileDel", function(){
					$(this).parent().remove();
				});
			});	
        	// 파일추가
         </script>
                    <div class="form-row">
                       <div class="name">마감 일자</div>
                         <div class="value">
                            <input class="input--style-6" type="date" name="endDate" value="${goodsVO.endDate}" style="height: 45px;">
                         </div>
                    </div>
                    <div class="form-row">
                       <div class="name">입찰시작가</div>
                         <div class="value">
                            <input class="input--style-6" type="text" name="lowestprice" value="${goodsVO.lowestprice}" style="height: 45px;">
                         </div>
                    </div>
                  
                      </form>
                      <div class="card-footer">
                    	<input type="submit" value="수정하기" id="modify" onclick="return goods_modify()" class="modify-btn" style="width: 100px; margin-left: 870px; !important">
                    	<input type="button" value="취소하기" id="back" class="delete-btn" style="width: 100px; !important" onclick="cancel()">
                      </div>
                </div>
            </div>
        </div>
    </div>

 <script type="text/javascript">

 // 수정,목록,취소 버튼
 $(document).ready(function(){
	 
 var formObj = $("form[role='form']");

		// 수정하기 
		$("#modify").on("click",function(){
			formObj.submit();
		});
 
 	
 });    

 	function cancel(){
		var result = confirm("취소하시겠습니까? 변경사항이 저장되지 않을 수 있습니다.");
		if(result){
		    history.back();
		}
	}
 // 수정,목록,취소 버튼
 </script> 

   




</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->

