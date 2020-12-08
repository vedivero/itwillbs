<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>WEB-INF/views/member/updateForm.jsp</h1>
  <h1> 회원수정 페이지 </h1>
  
  <fieldset>
    <legend> ITWILL 회원수정 </legend>
    <form action="" method="post">
      ID : <input type="text" name="userid" value="${memVO.userid }" readonly> <br>
      PW : <input type="password" name="userpw"> <br>
      NAME : <input type="text" name="username" value="${memVO.username }"> <br>
      EMAIL : <input type="text" name="useremail" value=${memVO.useremail }> <br>
      
      <input type="submit" value="회원수정">          
    </form>  
  </fieldset>
  
  
  
</body>
</html>