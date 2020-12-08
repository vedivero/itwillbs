package com.itwillbs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.itwillbs.board.BoardBean;

public class BoardDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비 연결 (커넥션 풀) - 디비 연결정보를 미리 생성해서 pool에다가 저장하고,
	// 필요시 사용/반납 형태로 처리
	private void getCon() throws Exception {

		// 1. 라이브러리 설치 (WEB-INF/lib/ 추가)
		// 2. /META-INF/context.xml 파일 생성 (1,2단계 내용)
		// 3. /WEB-INF/web.xml 파일 수정
		// 4. DAO 처리

		// Context 객체 생성
		Context init = new InitialContext();
		// 디비 연동 정보를 불러오기 (context.xml)
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/mysqlDB");
		// DataSource 객체 사용 연결 (멤버번수에 저장)
		con = ds.getConnection();

	}

	// 디비 연결 (커넥션 풀)
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
	// 디비 자원해제

	// insertBoard(글정보)
	public void insertBoard(BoardBean bb) {
		int num = 0; // 글번호 저장
		try {
			// 디비 연결
			getCon();

			// 3 SQL 작성 & pstmt
			// 글번호 계산
			sql = "select max(bno) from itwill_board";
			pstmt = con.prepareStatement(sql);

			// 4 실행
			rs = pstmt.executeQuery();

			// 5 데이터 처리( 글번호 계산 )
			if (rs.next()) {
				// num = rs.getInt("max(bno)")+1;
				num = rs.getInt(1) + 1;
				// getInt => 데이터 값을 리턴, 값이 SQL-NULL경우 0 리턴
			}

			System.out.println(" 글번호 : " + num);

			// 글 쓰기(저장)
			// 3 sql (insert)
			sql = "insert into itwill_board(bno,name,passwd,subject,content,"
					+ "readcount,re_ref,re_lev,re_seq,date,file,ip) values(" + "?,?,?,?,?,?,?,?,?,now(),?,?)";

			// now() : 자동으로 SQL 구문 실행시 시스템시간정보를 불러옴

			pstmt = con.prepareStatement(sql);

			// 3-1. ?
			pstmt.setInt(1, num);
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPasswd());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setInt(6, 0); // readcount 조회수 0 지정
			pstmt.setInt(7, num); // re_ref 답변글 그룹번호( 일반글 번호 동일 )
			pstmt.setInt(8, 0); // re_lev 답변글 들여쓰기 (일반글 0)
			pstmt.setInt(9, 0); // re_seq 답변글 순서 (일반글 제일위쪽)
			pstmt.setString(10, bb.getFile());
			pstmt.setString(11, bb.getIp());

			// 4 실행
			pstmt.executeUpdate();

			System.out.println("회원 글쓰기 완료!");

		} catch (Exception e) {
			System.out.println(" 회원 글쓰기 실패 !! ");
			e.printStackTrace();

		} finally {
			// 자원해제
			closeDB();
		}

	}
	// insertBoard(글정보)

	// getBoardCount() : 게시판 글 개수를 리턴하는 메서드
	public int getBoardCount() {
		int count = 0;

		try {
			// 디비 연결
			getCon();
			// 3. SQL작성 & pstmt
			sql = "select count(*) from itwill_board";
			pstmt = con.prepareStatement(sql);

			// 4. 실행 -> rs 저장
			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if (rs.next()) {
				count = rs.getInt(1);
				// rs.getInt("count(*)");
			}
			System.out.println(" 게시판 글 개수 확인  : " + count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return count;
	}
	// getBoardCount()

	// getBoardList()
	public ArrayList getBoardList() {

		ArrayList boardList = new ArrayList();

		try {
			// 디비연결
			getCon();
			// SQL / pstmt
			// sql ="select * from itwill_board";

			// 정렬(최신글이 가장 위쪽으로 보이게)
			sql = "select * from itwill_board " + "order by re_ref desc";

			pstmt = con.prepareStatement(sql);

			// 실행 -> rs
			rs = pstmt.executeQuery();

			// 데이터 처리 (검색된 모든 정보를 저장해서 이동)
			while (rs.next()) {
				// 글정보 하나를 BoardBean 객체 하나에 저장
				BoardBean bb = new BoardBean();

				bb.setBno(rs.getInt("bno"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setPasswd(rs.getString("passwd"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));

				// 글 하나의 정보를 arrayList 한칸에 저장
				boardList.add(bb);

			}

			System.out.println(" 모든 글 정보 저장 완료!");
			System.out.println(boardList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return boardList;
	}
	// getBoardList()

	// getBoardList(startRow,pageSize)
	public ArrayList getBoardList(int startRow, int pageSize) {
		ArrayList boardList = new ArrayList();

		try {
			// 디비연결
			getCon();
			// SQL / pstmt
			// 게시판 목록중에서 10개씩 가져오기 
			// 정렬  - re_ref(그룹번호) 내림차순,
			//       re_seq(답글순서) 오름차순
			// 데이터 짤라서 가져오기  limit 시작행-1,개수
			// => 해당 위치부터 개수만큼 가져오기
			
			sql = "select * from itwill_board "
					+ "order by re_ref desc,re_seq asc "
					+ "limit ?,?";

			pstmt = con.prepareStatement(sql);
			
			//?
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);

			// 실행 -> rs
			rs = pstmt.executeQuery();

			// 데이터 처리 (검색된 모든 정보를 저장해서 이동)
			while (rs.next()) {
				// 글정보 하나를 BoardBean 객체 하나에 저장
				BoardBean bb = new BoardBean();

				bb.setBno(rs.getInt("bno"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setPasswd(rs.getString("passwd"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));

				// 글 하나의 정보를 arrayList 한칸에 저장
				boardList.add(bb);
			}

			System.out.println(" 모든 글 정보 저장 완료!");
			System.out.println(boardList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return boardList;
	}
	// getBoardList(startRow,pageSize)
	
	// updateReadCount(bno)
	public void updateReadCount(int bno){
		
		try {
			// 디비연결
			getCon();
			
			// sql: 해당 글번호에 맞는 글에 조회수를 1증가  
			sql = "update itwill_board set readcount=readcount+1 "
					+ "where bno=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			// 실행	
			pstmt.executeUpdate();
			
			System.out.println(" 조회수 1증가 완료!! ");			
			
		} catch (Exception e) {
			System.out.println(" 조회수 1증가 실패!! ");
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	// updateReadCount(bno)
	
	// getBoard(bno)
	public BoardBean getBoard(int bno){
		BoardBean bb = null;
		
		try {
			// 디비 연결
			getCon();
			
			// SQL작성 , pstmt 객체생성
			// 글번호에 해당하는 모든 글의 정보를 가져오기(select)
			sql = "select * from itwill_board "
					+ "where bno=?";
			// pstmt 객체
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
						
			// 실행 -> rs
			rs = pstmt.executeQuery();
			
			// 데이터 처리	
			if(rs.next()){ // 데이터가 있으면 실행
				// DB->jsp 전달하기위해서 BoardBean객체에 저장
				bb = new BoardBean();
				
				bb.setBno(rs.getInt("bno"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setFile(rs.getString("file"));
				bb.setIp(rs.getString("ip"));
				bb.setName(rs.getString("name"));
				bb.setPasswd(rs.getString("passwd"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));

			}// if
			// 글정보 저장 완료
			System.out.println("해당 글 저장 완료! ");
			System.out.println(bb.toString());// 정보확인		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return bb;
	}
	// getBoard(bno)
	
	// updateBoard(bb)
	public int updateBoard(BoardBean bb){
		
		int check = -1;
		
		try {
			// 디비연결
			getCon();
			System.out.println("디비연결 완료");
			// SQL - (select)글쓴사람 본인 확인 
			// 글이 있는지 검색하는 sql 구문
			sql = "select passwd from itwill_board where bno=?";
			pstmt = con.prepareStatement(sql);
			System.out.println("pstmt 객체 생성 완료");
			// ?
			pstmt.setInt(1, bb.getBno());
			System.out.println("pstmt ? 추가완료 	"+bb.getBno());
			
			// 실행 
			rs = pstmt.executeQuery();
			System.out.println("pstmt 실행 및 rs 저장");
			
			// 데이터 처리 
			if(rs.next()){
				System.out.println("rs.next() 있음");
				if(bb.getPasswd().equals(rs.getString("passwd"))){
					// 비밀번호 비교(수정할때 저장한 비밀번호/디비저장된 비밀번호)
				    // 데이터 수정	
					// sql
					sql ="update itwill_board set name=?,subject=?,content=? "
							+ "where bno=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, bb.getName());
					pstmt.setString(2, bb.getSubject());
					pstmt.setString(3, bb.getContent());
					pstmt.setInt(4, bb.getBno());
					
					pstmt.executeUpdate();
					
				    System.out.println("게시판 글 수정 완료!");
					
					// 확인값 리턴
					check = 1;
				}else{
					check = 0;
				}
			}else{
				check = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return check;
	}
	// updateBoard(bb)
	
	// deleteBoard(bno,passwd)
	public int deleteBoard(int bno,String passwd) {
		int result = -1;
		
		try {
			// 디비연결
			getCon();
			// SQL & pstmt
			// -> 삭제 하려고 하는 글이 있는지 판단
			// -> 글이 있을경우 비밀번호 체크 삭제여부 판단
			sql = "select passwd from itwill_board where bno=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			// 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(passwd.equals(rs.getString("passwd"))){
					// 글삭제
					sql = "delete from itwill_board where bno=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, bno);
					
					// 실행
					pstmt.executeUpdate();
					System.out.println(" 글 삭제 완료 ! ");
					result = 1;
				}else{
				  result = 0;	
				  System.out.println(" 글 삭제 에러! "+result);
				}				
			}else{
				result = -1;
				System.out.println(" 글 삭제 에러! "+result);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	// deleteBoard(bno,passwd)
	
	// reInsertBoard(bb)
	public void reInsertBoard(BoardBean bb){
		int num = 0;
		
		try {
			// 디비 연결
			getCon();
			
			/*************답글 번호 계산***************/
			// sql작성(select-게시판의 글번호중 최대값 계산) & pstmt
			sql = "select max(bno) from itwill_board";
			pstmt = con.prepareStatement(sql);
			// 실행
			rs = pstmt.executeQuery();
			// 데이터 처리
			if(rs.next()){
				num = rs.getInt(1)+1;
			}
			System.out.println(" 답글 번호 : "+num);
			
			/***************************************/
			
			/*************답글 순서 재배치***************/
			// re_ref (같은 그룹 기준) re_seq 값이 기존의 값보다 큰게 있을경우
			// re_seq 값을 1증가
			sql = "update itwill_board set re_seq=re_seq+1 "
					+ "where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bb.getRe_ref());
			pstmt.setInt(2, bb.getRe_seq());
			
			pstmt.executeUpdate();			
			
			/***************************************/
			
		    /*************답글 추가 동작***************/
			sql="insert into itwill_board "
					+ "values(?,?,?,?,?"
					+ ",?,?,?,?,now()"
					+ ",?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num); // 계산한 글번호저장
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPasswd());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setInt(6, bb.getReadcount());
			pstmt.setInt(7, bb.getRe_ref()); //기존 원글의 그룹번호와 동일
			pstmt.setInt(8, bb.getRe_lev()+1); // 기존의 값 + 1
			pstmt.setInt(9, bb.getRe_seq()+1); // 기존의 값 + 1
			pstmt.setString(10, bb.getFile());
			pstmt.setString(11, bb.getIp());
			
			// 실행
			pstmt.executeUpdate();
			System.out.println(" 답글 저장 완료!");
			
			/***************************************/
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}	
	// reInsertBoard(bb)
	
	
	
	
	
	
	
	
	
	
	
	

}
