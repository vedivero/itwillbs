package com.itwillbs.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비 연결
	private Connection getCon() throws Exception {

		// Connection con=null;
		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/funwebDB");

		con = ds.getConnection();

		return con;
	}

	// 디비 자원해제
	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// insertBoard(bb) : 글쓰기 처리
	public void insertBoard(BoardBean bb) {
		int num = 0;
		try {
			// 디비연결
			con = getCon();

			// sql(1) 글번호 계산,(2) 글정보를 저장
			sql = "select max(bno) from fun_board";

			pstmt = con.prepareStatement(sql);
			// 실행
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

			System.out.println("저장될 글번호 : " + num);

			// (2) 글정보를 저장
			// sql
			sql = "insert into fun_board (bno,name,pass,subject,content,"
					+ "readcount,re_ref,re_lev,re_seq,date,ip,file) " + "values(?,?,?,?,?,?,?,?,?,now(),?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPass());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setInt(6, 0); // readcount
			pstmt.setInt(7, num); // re_ref (답글 그룹번호 == 글번호)
			pstmt.setInt(8, 0); // re_lev 일반글 0
			pstmt.setInt(9, 0); // re_seq 일반글 0
			pstmt.setString(10, bb.getIp());
			pstmt.setString(11, bb.getFile());

			// 실행
			pstmt.executeUpdate();

			System.out.println(num + "번 글쓰기 완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	// insertBoard(bb) : 글쓰기 처리

	// getBoardCount() : 글 개수 계산
	public int getBoardCount() {
		int count = 0;

		try {
			// 디비연결
			con = getCon();
			// sql(글 개수 계산-count()) & pstmt
			sql = "select count(*) from fun_board";
			pstmt = con.prepareStatement(sql);
			
			// 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			
			System.out.println(" 글 개수 : "+count+"개");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		// 값 리턴
		return count;
	}
	// getBoardCount() : 글 개수 계산
	
	
	// getBoardList() : 글 전체 목록
	public List getBoardList(){
		// 리스트 객체(배열) 생성
		List boardList = new ArrayList();
		
		try {
			// 디비연결
			con = getCon();
			// sql (전체 글 정보 모두를 저장) & pstmt
			sql = "select * from fun_board";
			pstmt = con.prepareStatement(sql);			
			// 실행 -> 정보 저장
			rs = pstmt.executeQuery();
			
			// 데이터 처리(리스트에 저장)
			while(rs.next()){
				// 행의 정보를 저장하는 객체 (BoardBean)
				BoardBean bb = new BoardBean();
				
				bb.setBno(rs.getInt("bno"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setPass(rs.getString("pass"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));
				
				// 리스트 한칸에 행의 정보 하나를 저장
				boardList.add(bb);				
			}			
			
			//System.out.println(" 글 정보 저장 완료 : "+boardList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return boardList;
	}
	// getBoardList() : 글 전체 목록
	
	
	
	
	
	
	
	
	
	
	

}
