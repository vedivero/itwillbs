<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   
    pageEncoding="UTF-8" %>
<%    	
   String userid= request.getParameter("userid");
   String psw = request.getParameter("passwd");
   String msg = "아이디와  패스워드를 확인하세요 _"+userid+","+psw;
   
   if(userid != null && psw != null){
   	String DRIVER="com.mysql.jdbc.Driver";
   	String DBURL="jdbc:mysql://localhost:3306/javadb";
   	String DBID="root";
   	String DBPW="1234";
 
   	// 1. 드라이버로드
   	Class.forName(DRIVER);
   	// 2. 디비연결
   	Connection con 
     	= DriverManager.getConnection(DBURL,DBID,DBPW);
   	// 3. sql 작성 & pstmt 객체 생성 ( select * )
   	String sql ="select * from member";
   
   	PreparedStatement pstmt =
   		con.prepareStatement(sql);
   
   	// 4. sql 실행
   	ResultSet rs = pstmt.executeQuery();
   
   	// 5. rs 데이터 처리
   	while(rs.next()){   	
   	 	String id =rs.getString("id_name");
   	 	String pw = rs.getString("pw_name");
   	 	//null값 비교를 위해 사용한 라이브러리
   	 	if(Objects.equals(id,userid) && Objects.equals(psw, pw)){
   		 	msg = "로그인 되었습니다. "+userid+","+psw;
   		 	break;
   	 	}
   	}  
   
   }//if절 끝
   
%>
<%=msg %>