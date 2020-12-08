<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
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
	<h1>WebContent/JSP5/insertPro.jsp</h1>

	<%
		//한글처리 (post 방식)
		request.setCharacterEncoding("UTF-8");

		// 이전페이지에서 전달된 정보를 받아서 출력 (화면)

		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String ju1 = request.getParameter("ju1");
		String ju2 = request.getParameter("ju2");
		String jumin = ju1+"-"+ju2;
	%>

	이름 :<%=name%><br>
	 나이 :<%=age%><br>
	 성별 :<%=gender%><br> 
	 주민번호 :<%=ju1%>-<%=ju2%><br>
	<%=jumin %><br>
	
	<%
	 ///////////////////////////////////////////////////////
	 
	 final String DRIVER = "com.mysql.jdbc.Driver";
	 final String DBURL = "jdbc:mysql://localhost:3306/jspdb";
	 final String DBID = "root";
	 final String DBPW = "1234";
	 
	 // 전달받은 정보를 DB에 저장
	 // 1. 드라이버 로드
	 Class.forName(DRIVER);
	 
	 System.out.println(" 드라이버 로드 성공! ");
	 // 2. 디비 연결
	 Connection con
	    = DriverManager.getConnection(DBURL,DBID, DBPW);
	 
	 System.out.println(" 디비 연결 성공! ");
	 
      // 3. SQL구문 작성, stmt 객체 생성
      // itwill_member 테이블에 이름,나이,성별,주민번호를 저장하는 쿼리구문
//       String sql = "insert into itwill_member(idx,name,gender,age,jumin) "
//                    +"values(null,'테스트','남',22,'900909-1231231')";
//       String sql = "insert into itwill_member(idx,name,gender,age,jumin) "
//                    +"values(null,'"+name+"','"+gender+"',"+age+",'"+jumin+"')";
      
      String sql = "insert into itwill_member(idx,name,gender,age,jumin) "
              +"values(null,?,?,?,?)";
      // stmt 객체 생성 : SQL 구문을 실행
      //Statement stmt = con.createStatement();
      PreparedStatement pstmt = con.prepareStatement(sql);
      
      // 3-1 ? 값 채우기  
      //  pstmt.setXXXX(?위치,값); // XXXX 테이블의 타입에 따라서 변경
      // => 항상 쿼리구문의 ?개수와 동일하게 처리 
       pstmt.setString(1, name);
       pstmt.setString(2, gender);
       pstmt.setInt(3, age);
       pstmt.setString(4, jumin);

      // 4. 실행
      //pstmt.executeUpdate();
      // -> insert,update,delete
      // => DB에 조작을 가하는 구문
      //pstmt.executeQuery();
      // -> select
      // => DB에 조작없이 값만 사용하는 구문 
      
      //stmt.executeUpdate(sql);  
      pstmt.executeUpdate();
      
     
      

      System.out.println("디비 저장 완료!!");
	
	%>










</body>
</html>