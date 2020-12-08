package com.itwillbs.board.db;

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
	private void getCon() throws Exception{
		// 커넥션 풀
		Context init = new InitialContext();
		
		DataSource ds =
				(DataSource) init.lookup("java:comp/env/jdbc/Model2DB");
		
		con = ds.getConnection();
		
		System.out.println("DAO : 디비연결 완료 "+con);
	}
	
	// 디비 자원해제
	public void closeDB(){
		try {
			if(rs != null){		rs.close();	}
			if(pstmt != null){	pstmt.close();	}
			if(con != null){	con.close(); }
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/*public void Test(){
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.executeUpdate();
		}catch (Exception e) {
		}
	}*/
	
	// insertBoard(bdto)
	public int insertBoard(BoardDTO bdto){
		int result = 0;
		int bno = 0;
		
		try {
			getCon(); // con 인스턴스 변수에 저장완료
			// 글번호 계산
			sql = "select max(bno) from itwill_board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bno = rs.getInt(1)+1; //인덱스 사용 호출
				//rs.getInt("max(bno)"); // 컬럼명 사용 호출
			}
			System.out.println("DAO : 글번호 "+bno);
			
			/////////////////////////////////////////////
			
			sql ="insert into itwill_board(bno,name,passwd,subject,content,"
					+ "readcount,re_ref,re_lev,re_seq,date,file,ip) "
					+ "values(?,?,?,?,?,"
					+ "?,?,?,?,now(),"
					+ "?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			pstmt.setString(2, bdto.getName());
			pstmt.setString(3, bdto.getPasswd());
			pstmt.setString(4, bdto.getSubject());
			pstmt.setString(5, bdto.getContent());
			pstmt.setInt(6, 0); //조회수 0 
			pstmt.setInt(7, bno); // 일반글 == re_ref
			pstmt.setInt(8, 0); // 일반글 lev = 0
			pstmt.setInt(9, 0); // 일반글 seq = 0
			pstmt.setString(10, bdto.getFile());
			pstmt.setString(11, bdto.getIp());
			
			// 실행			
			result = pstmt.executeUpdate();
			System.out.println("DAO : 글쓰기 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	// insertBoard(bdto)
	
	// getBoardCount()
	public int getBoardCount(){
		int count = 0;
		
		try {
			getCon();
			sql ="select count(*) from itwill_board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			
			System.out.println("DAO : DB에 저장된 글의 수 "+count);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}		
		
		return count;
	}
	// getBoardCount()

	// getBoardList()
	public ArrayList<BoardDTO> getBoardList() {
		ArrayList<BoardDTO> boardList =
				new ArrayList<BoardDTO>();
		
		try {
			getCon();
			
			sql = "select * from itwill_board";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardDTO bdto = new BoardDTO();
				
				bdto.setBno(rs.getInt("bno"));
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getDate("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setIp(rs.getString("ip"));
				bdto.setName(rs.getString("name"));
				bdto.setPasswd(rs.getString("passwd"));
				bdto.setRe_lev(rs.getInt("re_lev"));
				bdto.setRe_ref(rs.getInt("re_ref"));
				bdto.setRe_seq(rs.getInt("re_seq"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setSubject(rs.getString("subject"));
				
				boardList.add(bdto);				
				
			}
			
			System.out.println("DAO : 글 모두 저장완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return boardList;		
	}
	// getBoardList()
	
	// getBoardList(startRow,pageSize)
	public ArrayList<BoardDTO> getBoardList(int startRow,int pageSize) {
			ArrayList<BoardDTO> boardList =
					new ArrayList<BoardDTO>();
			
			try {
				getCon();
				// 테이블을 정렬 + 짤라가기
				//  re_ref (내림차순), re_seq (오름차순)
				//  limit(시작행-1,개수)
				sql = "select * from itwill_board "
						+ "order by re_ref desc,re_seq asc "
						+ "limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow-1); // 가져올 글의 시작위치
				pstmt.setInt(2, pageSize); // 가져올 글의 개수
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					BoardDTO bdto = new BoardDTO();
					
					bdto.setBno(rs.getInt("bno"));
					bdto.setContent(rs.getString("content"));
					bdto.setDate(rs.getDate("date"));
					bdto.setFile(rs.getString("file"));
					bdto.setIp(rs.getString("ip"));
					bdto.setName(rs.getString("name"));
					bdto.setPasswd(rs.getString("passwd"));
					bdto.setRe_lev(rs.getInt("re_lev"));
					bdto.setRe_ref(rs.getInt("re_ref"));
					bdto.setRe_seq(rs.getInt("re_seq"));
					bdto.setReadcount(rs.getInt("readcount"));
					bdto.setSubject(rs.getString("subject"));
					
					boardList.add(bdto);				
					
				}
				
				System.out.println("DAO : 글 모두 저장완료! ");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			return boardList;		
		}
		// getBoardList(startRow,pageSize)
	
	
	// updateReadCount()
	public void updateReadCount(int bno){
		
		try {
			// 디비연결
			getCon();
			// sql 
			sql="update itwill_board set "
					+ "readcount=readcount+1 where bno=?";
			pstmt = con.prepareStatement(sql);
			
			// 실행 	
			pstmt.setInt(1, bno);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글조회수 1증가 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// updateReadCount()
	
	// getBoard(bno)
	public BoardDTO getBoard(int bno){
		BoardDTO bdto = null;
		
		try {
			getCon();
			
			sql = "select * from itwill_board where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bdto = new BoardDTO();
				
				bdto.setBno(rs.getInt("bno"));
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getDate("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setIp(rs.getString("ip"));
				bdto.setName(rs.getString("name"));
				bdto.setPasswd(rs.getString("passwd"));
				bdto.setRe_lev(rs.getInt("re_lev"));
				bdto.setRe_ref(rs.getInt("re_ref"));
				bdto.setRe_seq(rs.getInt("re_seq"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setSubject(rs.getString("subject"));
			}
			System.out.println("DAO : 글정보 저장완료!!");			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return bdto;
	}
	// getBoard(bno)
	
    // updateBoard(bdto)
	public int updateBoard(BoardDTO bdto){
		int result = -1;
		
		try {
			getCon();
			
			// sql (select)
			sql = "select passwd from itwill_board where bno=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bdto.getBno());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				// 해당 글이 있음
				// sql (update)
				if(bdto.getPasswd().equals(rs.getString("passwd"))){
					sql ="update itwill_board set name=?,subject=?,content=? where bno=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, bdto.getName());
					pstmt.setString(2, bdto.getSubject());
					pstmt.setString(3, bdto.getContent());
					pstmt.setInt(4, bdto.getBno());
					
					result = pstmt.executeUpdate();
					
				}else {
					// 해당글은 있으나, 비밀번호 다른경우
					result = 0;
				}
			}else{
				// 해당 글이 없음
				result = -1;
			}
			System.out.println("DAO : 글정보 수정 처리 완료 -> "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;		
	}
	// updateBoard(bdto)
	
	// deleteBoard(bno,passwd)
	public int deleteBoard(int bno,String passwd){
		int result = -1;
		
		try {
			getCon();
			
			sql ="select passwd from itwill_board where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				// 삭제할 글이 있음
				if(passwd.equals(rs.getString("passwd"))){
					sql = "delete from itwill_board where bno=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, bno);
					
					result = pstmt.executeUpdate();							
					
				}else{
				   // 비밀번호 오류
					result = 0;
				}				
			}else{
				result = -1; // 삭제할 글이 없음
			}
			
			System.out.println("DAO : 삭제 처리 완료 -> "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// deleteBoard(bno,passwd)
	
	// reInsertBoard(bdto)
	public void reInsertBoard(BoardDTO bdto){
		int num = 0 ;
		
		try {
			getCon();
			// 1) 글 번호 생성 (지금 작성된 글번호 최대값 + 1)  
			sql = "select max(bno) from itwill_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1;
			}
			
			// 2) 답글 순서 재배치 
			// re_ref 같은 그룹 중에서 re_seq 기존의 값보다 큰게 있으면
			// re_seq + 1 증가  (순서 재배치)
			
			sql = "update itwill_board set re_seq = re_seq+1 "
					+ "where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getRe_ref());
			pstmt.setInt(2, bdto.getRe_seq());
			
			pstmt.executeUpdate();			
			
			// 3) 답글 정보 저장
			//   re_lev,re_seq  + 1씩 증가
			
			sql = "insert into itwill_board(bno,name,passwd,subject,content,"
					+ "readcount,re_ref,re_lev,re_seq,date,file,ip) "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bdto.getName());
			pstmt.setString(3, bdto.getPasswd());
			pstmt.setString(4, bdto.getSubject());
			pstmt.setString(5, bdto.getContent());
			pstmt.setInt(6, 0); // 조회수
			pstmt.setInt(7, bdto.getRe_ref());
			pstmt.setInt(8, bdto.getRe_lev()+1);
			pstmt.setInt(9, bdto.getRe_seq()+1);
			pstmt.setString(10, bdto.getFile());
			pstmt.setString(11, bdto.getIp());
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 답글 작성 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// reInsertBoard(bdto)
	
	
	
	

}
