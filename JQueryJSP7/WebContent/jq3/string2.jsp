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
      System.out.println("WebContent/jq3/string2.jsp 페이지 도착");
    
      // string2.html 페이지에서 전달된 정보를 저장 후 페이지 출력
      String name = request.getParameter("name");
      System.out.println("name : "+name);
      
      String id = request.getParameter("id");
      System.out.println("id : "+id);
      
    %>
      <%=name %> (string2.html 전달된 값!)<br>
      <%=id %> (아이디값)
    
    
    
   

</body>
</html>