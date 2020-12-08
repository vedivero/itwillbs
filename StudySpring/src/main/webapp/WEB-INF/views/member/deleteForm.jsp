<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WEB-INF/views/member/deleteForm.jsp</h1>


    <h1> 회원 탈퇴 </h1>
    
    <fieldset>
      <form action="/member/delete" method="post" name="fr"> 
        아이디 : <input type="text" name="userid" value="<%=session.getAttribute("id") %>" readonly ><br>
        비밀번호 : <input type="password" name="userpw" ><br>
        <input type="submit" value=" 탈 퇴 하 기 ">      
      </form>
    </fieldset>



</body>
</html>