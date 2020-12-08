<%@page import="com.itwillbs.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>WebContent/member/updateForm.jsp</h1>
	<%
		// 로그인 세션처리
		  String id = (String)session.getAttribute("id");
		  if(id == null){
			 response.sendRedirect("./MemberLogin.me");
		  }
		  
		  // MemberDAO 객체 생성-메서드getMember() 사용 (X)
		  
		 // Action 페이지에서 저장했던 회원 정보를 받아오기
		// 회원정보를 저장(request 영역)
		//request.setAttribute("memberbean", mb);
		  
		 MemberBean mb 
		  = (MemberBean)request.getAttribute("memberbean");
		 
		System.out.println("V : 액션페이지에서 정보를 전달받음");
		System.out.println("V : "+mb);
		  
		  
	%>
	
	<fieldset>
     <legend>정보 수정</legend>
     
     <form action="./MemberUpdatePro.me" method="post" name="fr">
          아이디 : <input type="text" name="id" value="<%=mb.getId() %>" readonly><br>
       비밀번호 : <input type="password" name="pw"><br>
             이름 : <input type="text" name="name" value="<%=mb.getName()%>"><br>
             나이 : <input type="text" name="age"  value="<%=mb.getAge()%>"><br>
             성별 : <input type="radio" name="gender" value="남"
             <%if(mb.getGender().equals("남")){ %>
               checked
             <%} %>
             >남
              <input type="radio" name="gender" value="여"
             <%if(mb.getGender().equals("여")){ %>
               checked
             <%} %>
              >여<br>
       이메일 : <input type="text" name="email" value="<%=mb.getEmail()%>"><br>              
       <input type="submit" value="가입하기">
     </form>   
   </fieldset>
   
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>