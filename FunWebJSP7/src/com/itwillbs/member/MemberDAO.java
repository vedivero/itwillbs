package com.itwillbs.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	// 정보를 처리해서 데이터 베이스에 저장

	// 디비연결에 필요한 변수 선언
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

	// 회원 가입 메서드 (insertMember())
	public void insertMember(MemberBean mb) {

		try {
			con = getCon();

			// sql
			sql = "insert into fun_member(id,pass,name,email,birth,gender,"
					+ "addr,phone,mobile,reg_date) values(?,?,?,?,?,?,?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			// ?
			pstmt.setString(1, mb.getId());
			pstmt.setString(2, mb.getPass());
			pstmt.setString(3, mb.getName());
			pstmt.setString(4, mb.getEmail());
			pstmt.setInt(5, mb.getBirth());
			pstmt.setString(6, mb.getGender());
			pstmt.setString(7, mb.getAddr());
			pstmt.setString(8, mb.getPhone());
			pstmt.setString(9, mb.getMobile());
			pstmt.setDate(10, mb.getReg_date());

			// 실행
			pstmt.executeUpdate();

			System.out.println("회원 정보 가입 완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	// 회원 가입 메서드 (insertMember())

	// 중복아이디 체크하는 메서드 joinIdCheck(ID);
	public int joinIdCheck(String ID) {
		int result = -1;

		try {
			// 디비연결
			getCon();
			// SQL(select) - pstmt
			// 전달받은 ID에 해당하는 정보가 있는지 판단
			sql ="select * from fun_member where id=?";
				
			pstmt = con.prepareStatement(sql);
			
			// ?
			pstmt.setString(1, ID);
			// 실행
			rs = pstmt.executeQuery();

			// rs데이터 처리
			// 1 - 사용 가능한 아이디
			// 0 - 사용 불가능한 아이디
			// -1 -> 에러 발생
			if(rs.next()){
				result = 0; //기존 데이터 있음 => 중복 아이디
			}else{
				result = 1; // 기존데이터 없음 => 중복X 아이디
			}
			
			System.out.println("아이디 중복체크 : "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// 중복아이디 체크하는 메서드 joinIdCheck(ID);
	
	// 로그인 체크 메서드 idCheck(id,pass)
	public int idCheck(String id,String pass){
		int result=-1;
		
		try {
			// 디비연결
			con = getCon();
			// sql(id에 해당하는 정보가 있는지 없는지 판단)
			// 아이디,비밀번호 같을때 로그인 처리 (1)
			// 아이디x(-1) 비밀번호x(0)
			sql ="select pass from fun_member where id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(pass.equals(rs.getString("pass"))){
					result = 1; // 로그인 성공
				}else{
					result = 0; // 비밀번호 오류
				}				
			}else{
				result = -1; // 아이디가 없음
			}
			
		System.out.println("로그인 처리 완료  : "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}	
	// 로그인 체크 메서드 idCheck(id,pass)
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
