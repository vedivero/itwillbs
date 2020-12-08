<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/JSP3/sessionLoginPro.jsp</h1>
	
	<%
	  // 로그인 처리를 하는 페이지 => 사용자가 볼수 없는 페이지
	  // 아이디 비밀번호를 체크해서 같은경우만 로그인
	  // 다른게 있을경우 로그인 실패
	  
	  
	  // 페이지 이동시 가져온 정보를 저장
	  // ID,PW (파라미터)
	  // DBID = "admin", DBPW=1234
	  final String DBID = "admin";
	  final String DBPW = "1234";
	  
	  //입력한 값
	  String id = request.getParameter("id");
	  String pw = request.getParameter("pw");
	  
	  // ==  : 두개의 문자열 객체가 같은지 비교
      // equals() : 두개의 문자열 객체의 문자 데이터값이 같은지 비교
      
	  if(id.equals(DBID)){
		  // 아이디가 같은경우
		  //out.print("있는 아이디 입니다!!");
		  if(pw.equals(DBPW)){
			  // 비밀번호가 같은경우 +(아이디는 같음) => 로그인
			  out.print(" 로그인 성공 !!! "+id);
			  
			  // 메인 페이지 이동
			  response.sendRedirect("sessionMain.jsp");
			  
			  // 세션값 생성
			  session.setAttribute("ID", id);
			  
		  }else{
			  // 비밀번호가 다른경우 +(아이디는 같음) => 로그인실패
			  //out.print("비밀번호가 틀렸습니다!!");		
			  %>
			     <script type="text/javascript">
			        alert(" 비밀번호 오류!!  ");
			        location.href="sessionLoginForm.jsp";
			     </script>		  
			  <%
		  }
	  }else{
		  // 아이디가 다른경우
		  //out.print("없는 아이디 입니다!!");
		  
		  // alert(" 회원 정보가 없습니다. ")
		  // 기존의 페이지(로그인페이지)이동
		  %>
		     <script type="text/javascript">
		        alert(" 회원 정보가 없습니다. ");
		        location.href="sessionLoginForm.jsp";
		     </script>		  
		  <%
	  }
	  
	%>















</body>
</html>