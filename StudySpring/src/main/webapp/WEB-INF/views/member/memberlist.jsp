<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1> WEB-INF/views/member/memberlist.jsp </h1>
    
    <table border="1">
     <tr>
       <td>아이디</td>
       <td>비밀번호</td>
       <td>이름</td>
       <td>이메일</td>
       <td>가입일자</td>
       <td>정보수정일</td>       
     </tr>
     
     <c:forEach var="mList" items="${memberList }">
      <tr>
        <td>${mList.userid }</td>
        <td>${mList.userpw }</td>
        <td>${mList.username }</td>
        <td>${mList.useremail }</td>
        <td>${mList.regdate }</td>
        <td>${mList.updatedate }</td>
      </tr>     
     </c:forEach>    
    
    </table>
    
    <h2> <a href="/member/main">main 페이지</a> </h2>
    
    
    

</body>
</html>