<%@page import="com.itwillbs.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/board/deletePro.jsp</h1>

	<%
	    // 한글처리
	    request.setCharacterEncoding("UTF-8");
		// 전달되는 데이터 처리 bno,passwd,pageNum
		String pageNum = request.getParameter("pageNum");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String passwd = request.getParameter("passwd");
		

		BoardDAO bdao = new BoardDAO();
		int check = bdao.deleteBoard(bno,passwd);
		// DAO 처리객체 -> deleteBoard(글번호,비밀번호)
		// => 정수형타입 데이터로 리턴 ->  페이지 이동처리 

		if(check == 1){// 정상처리
			%>
			  <script type="text/javascript">
			      alert(" 글 삭제 완료 ! ");
			      location.href="boardList.jsp?pageNum=<%=pageNum%>";
			  </script>			
			<%			
		}else if(check == 0){
			%>
			  <script type="text/javascript">
			      alert(" 글 비밀번호 오류 ! ");
			      history.back();
			  </script>			
			<%
		}else{ //check == -1
			%>
			  <script type="text/javascript">
			      alert(" 글 번호 없음 ! ");
			      history.back();
			  </script>			
			<%
		}
		
	%>














</body>
</html>