<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title"> ITWILL 수정하기 </h3>
				</div>
				<!-- /.box-header -->
				
				<form action="" role="form" method="post">
				  
				  <input type="hidden" name="page" value="${cri.page }">
				  <input type="hidden" name="pageSize" value="${cri.pageSize }">
				
				
				 <!-- /.box-body -->
				 <div class="box-body">
				  <div class="form-group">
				     <label for="exampleInputEmail">글번호</label>
				     <input type="text" name="bno" 
				           class="form-control" value="${boardVO.bno }"
				           readonly
				     >				     			   
				  </div>				 
				 
				   <div class="form-group">
				     <label for="exampleInputEmail">제목</label>
				     <input type="text" name="title" 
				           class="form-control" 
				           value="${boardVO.title }"
				     >				     			   
				  </div>
				  
				   <div class="form-group">
				     <label for="exampleInputPassword1">내용</label>
				     <textarea class="form-control" name="content" rows="3"
				      >${boardVO.content }</textarea>				     
				   </div>
				   
				   <div class="form-group">
				     <label for="exampleInputEmail">글쓴이</label>
				     <input type="text" name="writer" 
				           class="form-control" 
				           value="${boardVO.writer }"
				     > 				   
				   </div>				 
				 </div>
                </form> 
				 
				  <!-- /.box-body -->
				  
				  <div class="box-footer">
				    <button type="submit" class="btn btn-primary">수정하기</button>				  
				    <button type="submit" class="btn btn-warning">취소하기</button>				  
				  </div>
								
				

			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->

 <script>
   $(document).ready(function(){

       var formObj = $("form[role='form']");
       //console.log(formObj);
       //var page= '${cri.page}';
       // alert(page);

       // 수정하기
      $(".btn-primary").on("click",function(){
           formObj.submit();
          }); 
       // 취소하기
      $(".btn-warning").on("click",function(){
          location.href="/board/listPage?page=${cri.page}&pageSize=${cri.pageSize}";
          });
      

	   
   });
 </script>





<%@ include file="../include/footer.jsp" %>
