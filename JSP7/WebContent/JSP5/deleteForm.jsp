<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/JSP5/deleteForm.jsp</h1>
   
   <!--
         이름,주민번호를 입력해서 삭제 처리
         두개의 정보가 동일한 사람의 정보를 삭제
         deletePro.jsp  
    -->
    <fieldset>
      <legend> 회원정보 삭제 </legend>
      <form action="deletePro.jsp" method="post">
              이름 : <input type="text" name="name"><br>
        주민번호 : <input type="text" name="jumin"><br>
        <input type="submit" value="삭제하기"> 
      </form>      
    </fieldset>
    
    <fieldset>
      <legend> 회원정보 삭제2 </legend>
      <form action="deletePro2.jsp" method="post">
              이름 : <input type="text" name="name"><br>
        주민번호 : <input type="text" name="jumin"><br>
        <input type="submit" value="삭제하기"> 
      </form>      
    </fieldset>
   
   
   
   
   
   
   
   

</body>
</html>