<%@page import="com.itwillbs.admin.goods.db.GoodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
   <script type="text/javascript">
       /*  각각의 링크 클릭시 해당 페이지로의 이동
           + 정보를 가지고 이동(submit)       
       */
       
       function isBasket(){
    	   alert("장바구니 페이지로 이동~!");
    	   
    	   // 구매 수량
    	   if(document.goodsform.amount.value == ""){
    		   alert(" 구매수량을 입력하시오. ");
    		   document.goodsform.amount.focus();
    		   return;
    	   }
    	   // 구매수량이 남은수량보다 많은경우 제어
    	   // 구매 수량
    	   var buyCnt = document.goodsform.amount.value;
    	   alert("구매 수량 : "+buyCnt);
    	   
    	   // 남은 수량 (DTO정보) - EL 표현식 
    	   var DBCnt = '${gdto.amount}';
    	   <%-- <%=gdto.getAmount()%> --%>
    	   alert("남은 수량 : "+DBCnt);
    	   
    	   if(Number(buyCnt) > Number(DBCnt)){
    		   alert("구매 수량을 조절하시오");
    		   document.goodsform.amount.focus();
    		   return;
    	   }
    	   
    	   
    	   
    	   
    	   // 크기 선택
    	   if(document.goodsform.size.value == ""){
    		   alert(" 크기를 선택하시오. ");
    		   document.goodsform.size.focus();
    		   return;
    	   }
    	   // 색상 선택 
    	   if(document.goodsform.color.value == ""){
    		   alert(" 색상을 선택하시오. ");
    		   document.goodsform.color.focus();
    		   return;
    	   }
    	   
    	   // submit - "장바구니로 이동하시겠습니까?"
           // yes - 장바구니페이지 ("./BasketAdd.ba")
           // no - 현재 페이지 그대로 대기 
           var result = confirm("장바구니로 이동하시겠습니까?");
    	   
    	   if(result){
    		   // 상품번호(gno),구매수량(amount),옵션(color,size)
    		   document.goodsform.action = "./BasketAdd.ba";
    		   document.goodsform.submit();
    	   }else{
    		   return;
    	   } 
       } // isBasket();
   
   
   </script>
</head>
<body>

	<h1>WebContent/goods/goods_detail.jsp</h1>

	<%
	  // GoodsDetailAction 에서 저장한 상품 정보를 받아오기
	  GoodsDTO gdto = (GoodsDTO)request.getAttribute("gdto");
	
	%>
	<form action="" method="post" name="goodsform">
	 <!-- 상품번호를 저장후 전달  -->
	 <input type="hidden" name="gno" value="${gdto.gno }">
	 <%-- <input type="hidden" name="gno" value="<%=gdto.getGno()%>"> --%>
	
	<table border="1">
	  <tr>
	    <td>
	      <img src="./upload/<%=gdto.getImage().split(",")[0]%>"
	           width="300" height="300"
	      >
	    </td>
	    <td>
	       상품명 : <%=gdto.getName() %> <br>
	       판매가격 : <%=gdto.getPrice() %><br>
	       구매수량 : <input type="text" name="amount" ><br>
	       남은수량 : <%=gdto.getAmount() %><br>
	      
	       크기 : <select name="size">
	           <option value="">크기를 선택하시오</option>
	           <%-- <c:forTokens var="size" items="<%=gdto.getSize() %>" delims=","> --%>
	           <c:forTokens var="size" items="${gdto.size }" delims=",">
	             <option>${size}</option>
	           </c:forTokens>	           
	         </select> 
	      <br>
	       
	       색상 : 
	        <select name="color">
	           <option value="">색상을 선택하시오</option>
	           <c:forTokens var="color" items="${gdto.color }" delims=",">
	             <option>${color}</option>
	           </c:forTokens>	           
	         </select> 
	       <br>
	       
	      <!-- <a href="" onclick="isBasket();"> [장바구니 담기] </a> --> 
	      <a href="javascript:isBasket();"> [장바구니 담기] </a> 
	      <a href=""> [바로 구매하기] </a>
	    </td>  
	  </tr>	
	  <tr>
	    <td colspan="2">
	     <h2>상세페이지</h2>
	      <img src="./upload/<%=gdto.getImage().split(",")[0]%>"
	         width="400" height="600"
	      >
	    </td>
	  </tr>
	</table>
	</form>










</body>
</html>