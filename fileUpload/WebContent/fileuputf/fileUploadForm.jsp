<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/fileuputf/fileUploadForm.jsp</h1>
   
   <h1> 파일 업로드 </h1>
   
   <fieldset>
	   <form action="fileUploadPro.jsp" method="post" enctype="multipart/form-data">
	      작성자 : <input type="text" name="name"><br> 
	      제목 : <input type="text" name="subject"><br>
	      파일 : <input type="file" name="file"><br>
	      <input type="submit" value="전송(파일업로드)">
	      
	   </form>
   </fieldset>
   
   
   
   
   
   
   
   
   
   
   
   


</body>
</html>