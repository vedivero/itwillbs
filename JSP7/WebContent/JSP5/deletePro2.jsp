<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/JSP5/deletePro2.jsp</h1>

	<!--  회원 정보 삭제   -->

	<%
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 회원 이름,주민번호 저장
		String name = request.getParameter("name");
		String jumin = request.getParameter("jumin");

		final String DRIVER = "com.mysql.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
		// 1. 드라이버 로드
		Class.forName(DRIVER);
		// 2. 디비 연결
		Connection con 
		   = DriverManager.getConnection(DBURL,DBID,DBPW);

		// 3. SQL 작성 &  pstmt 객체 생성
		
		//  select 구문 사용 -> 사용자 확인 -> 삭제
		String SQL ="select jumin from itwill_member where name=?";
		
		PreparedStatement pstmt = con.prepareStatement(SQL);
		// ?
		pstmt.setString(1, name);
		
		// 4. 실행
		ResultSet rs = pstmt.executeQuery();
		
		// 5. 데이터 처리
		if(rs.next()){ // 레코드셋 커서를 옮겨서 데이터가 있을때 실행
			// 이름과,주민번호가 있는 회원의 정보가 있다.
			// 삭제 (DB주민번호와 입력한 주민번호가 같을때만)
			if(jumin.equals(rs.getString("jumin"))){
				// 삭제
				// 3 SQL 구문
				SQL = "delete from itwill_member "
				      +"where jumin=?";
				pstmt = con.prepareStatement(SQL);
				// ?
				pstmt.setString(1, jumin);
				
				// 4. 실행
				pstmt.executeUpdate();	
				
				System.out.println("회원 삭제 완료");
				
			}else{
			   // 이름은 있지만, 주민번호가 다른경우
			   // 삭제동작 없이 이전페이지로 이동
			   %>
			     <script type="text/javascript">
			        alert("주민번호 오류!");
			        history.back();
			     </script>			   
			   <%
			}		
			
		}else{
			// 이름에 해당하는 주민번호가 없음.=> 비회원
			// 삭제동작 없이 이전페이지로 이동 		
			 %>
		     <script type="text/javascript">
		        alert("비회원 입니다!");
		        history.back();
		     </script>			   
		   <%
			
		}
		
		
		
		
		
		
		
		
		
	%>

















</body>
</html>