<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ include file="../include/header.jsp" %>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title"> ITWILL 게시판 목록 </h3>
				</div>
				<!-- /.box-header -->
				
				 <!-- /.box-body -->
				 <div class="box-body">
				   <%-- ${boardList } --%>
				 
				    <table class="table table-bodered">
				      <tr>
				        <th>번호</th>
				        <th>제목</th>
				        <th>글쓴이</th>
				        <th>날짜</th>
				        <th>조회수</th>
				      </tr>
				     
				     <c:forEach items="${boardList }" var="boardVO"> 
				      <tr>
				        <td>${boardVO.bno }</td>
				        <td>
				         <a href="/board/read?bno=${boardVO.bno }">${boardVO.title }</a>				        
				        </td>
				        <td>${boardVO.writer }</td>
				        <td>
				          <fmt:formatDate value="${boardVO.regdate }" 
				             pattern="yyyy-MM-dd HH:mm"
				               />
				              <!--    type="both"
				              timeStyle="short" -->
				        </td>
				        <td>
				          <span class="badge bg-red">
				            ${boardVO.viewcnt }
				          </span>				          
				        </td>
				      </tr>
				     </c:forEach>
				      
				      
				      
				    </table>
				 </div>
				  <!-- /.box-body -->
				  
				 <div class="box-footer">
				    <h3> 결과(글쓰기 여부) : ${result } </h3>
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
<script type="text/javascript">
   //alert("테스트");
   var result = '${result}';

   if(result == "SUCCESS" ){
	    alert("result : "+result);
	    alert("글쓰기 정상 처리 완료!");
	    //location.href="/board/register";
   }

   if(result == "upok"){
       alert("글 수정완료! ");
	}

	if(result == "delok"){
       alert("글 삭제 완료!");
	}

   
 
</script>



<%@ include file="../include/footer.jsp" %>
