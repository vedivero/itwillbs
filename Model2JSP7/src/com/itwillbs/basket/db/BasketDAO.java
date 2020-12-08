package com.itwillbs.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.admin.goods.db.GoodsDTO;

public class BasketDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// 디비 연결
	private void getCon() throws Exception {
		// 커넥션 풀
		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/Model2DB");

		con = ds.getConnection();

		System.out.println("DAO : 디비연결 완료 " + con);
	}

	// 디비 자원해제
	public void closeDB() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// checkGoods(bdto)
	public int checkGoods(BasketDTO bkdto){
		int check = 0;
		
		try {
			getCon();
			
			// sql - ID,gno,size,color 모두 만족하는 대상 검색
			sql ="select * from itwill_basket "
					+ "where b_m_id=? and b_g_num=? and "
					+ "b_g_size=? and b_g_color=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bkdto.getB_m_id());
			pstmt.setInt(2, bkdto.getB_g_num());
			pstmt.setString(3, bkdto.getB_g_size());
			pstmt.setString(4, bkdto.getB_g_color());

			rs = pstmt.executeQuery();
			
			if(rs.next()){
				// 테이블안에 기존의 옵션과 동일한 상품이 있다.
				check = 1;
				// 데이터 수정 (update) - 구매 수량 수정 
				sql="update itwill_basket set b_g_amount=b_g_amount+? "
						+ "where b_m_id=? and b_g_num=? and "
						+ "b_g_size=? and b_g_color=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, bkdto.getB_g_amount());
				pstmt.setString(2, bkdto.getB_m_id());
				pstmt.setInt(3, bkdto.getB_g_num());
				pstmt.setString(4, bkdto.getB_g_size());
				pstmt.setString(5, bkdto.getB_g_color());
				
				pstmt.executeUpdate();
				
				System.out.println("기존의 상품에 수량 변경완료!");
			}	

			System.out.println("기존의 상품 확인 결과 : "+(check==1? "상품있다":"상품없다"));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	    return check;		
	}
	// checkGoods(bdto)
	
	// basketAdd(bdto)
	public void basketAdd(BasketDTO bkdto){
		int b_num=0;
		try {
			getCon();
			// 1) 장바구니 번호 계산
			sql = "select max(b_num) from itwill_basket";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				b_num = rs.getInt(1)+1;
			}
			System.out.println("b_num : "+b_num);
			
			// 2) 나머지 전달정보 DB에 저장
			sql ="insert into itwill_basket values(?,?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			// 반드시 테이블의 컬럼 순서대로 전부 추가
			pstmt.setInt(1, b_num);
			pstmt.setString(2, bkdto.getB_m_id());
			pstmt.setInt(3, bkdto.getB_g_num());
			pstmt.setInt(4, bkdto.getB_g_amount());
			pstmt.setString(5, bkdto.getB_g_size());
			pstmt.setString(6, bkdto.getB_g_color());
			
			pstmt.executeUpdate();

			System.out.println("DAO : 장바구니 추가 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	// basketAdd(bdto)
	
	// getBasketList(id)
	public Vector getBasketList(String id){
		
		Vector totalData = new Vector();
		
		// 상품정보
		List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
		// 장바구니 정보
		List<BasketDTO> basketList = new ArrayList<BasketDTO>();
		
		
		try {
			getCon();
			// 1)  장바구니 정보를 검색 (ID값을 기준으로 해당 장바구니 전부를 가져오기)
			sql ="select * from itwill_basket where b_m_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				System.out.println("@@@@@@@ rs 시작 @@@@@@@");
				// ID에 해당하는 장바구니를 전부저장
				// 장바구니 상품정보(BasketDTO) -> BasketList 한칸에 저장
				
				BasketDTO bkdto = new BasketDTO();
				
				bkdto.setB_date(rs.getDate("b_date"));
				bkdto.setB_g_amount(rs.getInt("b_g_amount"));
				bkdto.setB_g_color(rs.getString("b_g_color"));
				bkdto.setB_g_num(rs.getInt("b_g_num"));
				bkdto.setB_g_size(rs.getString("b_g_size"));
				bkdto.setB_m_id(rs.getString("b_m_id"));
				bkdto.setB_num(rs.getInt("b_num"));
				
				// 리스트에 저장
				basketList.add(bkdto);
				
				// 장바구니 저장된 상품정보를 불러오기 
				sql = "select * from itwill_goods where gno=?";
				
				PreparedStatement pstmt2 
				        = con.prepareStatement(sql);
				pstmt2.setInt(1, bkdto.getB_g_num());
				
				ResultSet rs2 = pstmt2.executeQuery();
				
				if(rs2.next()){
					System.out.println("@@@@@@@ rs-if 시작 @@@@@@@");
					GoodsDTO gdto = new GoodsDTO();
					
					gdto.setImage(rs2.getString("image"));
					gdto.setName(rs2.getString("name"));
					gdto.setPrice(rs2.getInt("price"));
					// 나머지 정보는 필요에 따라 추가 가능
					
					// 상품 리스트에 저장
					goodsList.add(gdto);	
					System.out.println("@@@@@@@ rs-if 끝 @@@@@@@");
				} // if
				
				System.out.println("@@@@@@@ rs 끝 @@@@@@@");
			}// while구문
			
			System.out.println("상품 정보 : "+goodsList);
			System.out.println("장바구니 정보 : "+basketList);
			
			// 장바구니 정보, 상품정보를 모두 저장완료
			totalData.add(basketList); // totalData(0인덱스)
			totalData.add(goodsList); // totalData(1인덱스)
			
			System.out.println("백터 정보 확인 : "+totalData);			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return totalData;
	}
	// getBasketList(id)
	
	// basketDelete(b_num)
    public void basketDelete(int b_num){
    	
    	try {
			getCon();
			
			sql="delete from itwill_basket where b_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 장바구니 삭제 완료 "+b_num);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
    	
    }
	// basketDelete(b_num)
	
	// basketDelete(id)
    public void basketDelete(String id){
    	
    	try {
			getCon();
			// 구매후 장바구니 삭제 (본인 정보 삭제)
			sql = "delete from itwill_basket where b_m_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 구매후 본인 장바구니 비우기(삭제)");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
    	    	
    }
    // basketDelete(id)
    
	
	
}
