<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/JSP6/updateForm.jsp</h1>
   
   <%
     // 로그인 여부 체크 (로그인X->로그인페이지로 이동)
     
     // DB연결 -> 로그인한 사용자의 정보를 가져오기
     // => 화면에 출력(각각의 요소)
     	// 회원의 로그인 여부를 판단(로그인페이지로 이동)

		String id = (String) session.getAttribute("id");

		if (id == null) {
			response.sendRedirect("loginForm.jsp");
		}

		final String DRIVER = "com.mysql.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
		final String DBID = "root";
		final String DBPW = "1234";
		// 1. 드라이버 로드
		Class.forName(DRIVER);

		System.out.println(" 드라이버 로드 성공 ! ");

		// 2. DB연결
		Connection con 
		= DriverManager.getConnection(DBURL, DBID, DBPW);

		System.out.println(" 디비 연결 성공 ! ");

		// 3. SQL 문작성 (select) & pstmt
		String sql ="select * from itwill_member where id=?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		// ?
		pstmt.setString(1, id);
		
		// 4. 실행 -> 결과 저장 (rs)
		ResultSet rs = pstmt.executeQuery();
		// 5. 검색결과 사용하기 -> 테이블 생성후 추가
		
		//String id="";
		String name="";
		int age=0;
		String gender="";
		String email="";
		Timestamp reg_date=null;
		
		if(rs.next()){
			// DB정보를 가져오기 -> 화면에 출력
			// id = rs.getString("id");
			name = rs.getString("name");
			age = rs.getInt("age");
			gender = rs.getString("gender");
			email = rs.getString("email");
			reg_date = rs.getTimestamp("reg_date");
		}
		
		if(gender == null){
			gender="남";
		}
		
		
		
   %>
   
   
   
   <h1> 회원 정보 수정 </h1>
   <fieldset>
     <legend>정보 수정</legend>
     <form action="updatePro.jsp" method="post" name="fr">
          아이디 : <input type="text" name="id" value="<%=id%>" readonly><br>
       비밀번호 : <input type="password" name="pw"><br>
             이름 : <input type="text" name="name" value="<%=name%>"><br>
             나이 : <input type="text" name="age" value="<%=age%>"><br>
             성별 : <input type="radio" name="gender" value="남" 
             <% if(gender.equals("남")){ %>
             checked
             <%} %>             
             >남
              <input type="radio" name="gender" value="여" 
             <% if(gender.equals("여")){ %>
             checked
             <%} %> 
              >여<br>
       이메일 : <input type="text" name="email" value="<%=email%>"><br>              
       <input type="submit" value="정보 수정하기">
     </form>   
   </fieldset>
   
   
   
   


</body>
</html>