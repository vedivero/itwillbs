<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>WebContent/member/insertForm.jsp</h1>
  
   <fieldset>
     <legend>정보 입력</legend>
     
     <form action="insertPro.jsp" method="post" name="fr">
          아이디 : <input type="text" name="id"><br>
       비밀번호 : <input type="password" name="pw"><br>
             이름 : <input type="text" name="name"><br>
             나이 : <input type="text" name="age"><br>
             성별 : <input type="radio" name="gender" value="남">남
              <input type="radio" name="gender" value="여">여<br>
       이메일 : <input type="text" name="email"><br>              
       <input type="submit" value="가입하기">
     </form>   
   </fieldset>
   
  
  
  
  
  
  
  
  
  
  
  
  
  
  

</body>
</html>