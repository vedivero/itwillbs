<%@page import="com.itwillbs.order.db.OrderDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>  
  <%
    System.out.println("V : 관리자 주문목록 처리 페이지");
  
    // request.setAttribute("adminOrderList", aodao.getAdminOrderList());
    
    List<OrderDTO> adminOrderList 
        = (List<OrderDTO>) request.getAttribute("adminOrderList");
    
  %>
   <h1>WebContent/adminorder/admin_order_list.jsp</h1>
   
   <h1> 관리자 주문 목록 </h1>
   
   <table border="1">
     <tr>
       <td>주문번호</td>
       <td>주문자</td>
       <td>결재방법</td>
       <td>주문금액</td>
       <td>주문상태</td>
       <td>주문일시</td>
       <td>수정 / 삭제</td>
     </tr>
     
     <% for(OrderDTO odto:adminOrderList){ %>
      <tr>
       <td><%=odto.getO_trade_num() %></td>
       <td><%=odto.getO_m_id() %></td>
       <td><%=odto.getO_trade_type() %></td>
       <td><%=odto.getO_sum_money() %></td>
       <%
	   	   // 주문상태
	       // 0 - "대기중"
	       // 1 - "발송준비"
	       // 2 - "발송완료"
	       // 3 - "배송중"
	       // 4 - "배송완료"
	       // 5 - "주문취소" 
	       String status ="";
           if(odto.getO_status() == 0){ status="대기중"; }
           else if(odto.getO_status() == 1){ status="발송준비"; }
           else if(odto.getO_status() == 2){ status="발송완료"; }
           else if(odto.getO_status() == 3){ status="배송중"; }
           else if(odto.getO_status() == 4){ status="배송완료"; }
           else if(odto.getO_status() == 5){ status="주문취소"; }
           else {  status="문제발생!";  }
	       
       %>
       <td>
       	<%=status %>
       </td>
       
       <td><%=odto.getO_date() %></td>
       <td>
        	<a href="./AdminOrderDetail.ao?trade_num=<%=odto.getO_trade_num()%>">수정</a> 
        	/
        	<a href="./AdminOrderDelete.ao?trade_num=<%=odto.getO_trade_num()%>">삭제</a> 
       </td>
      </tr>
     <%} %>
   </table>
   
   <h2> <a href="./Main.me">메인페이지</a> </h2>
   
   
   
   
   
   
   
   
   
   
   
   
   

</body>
</html>