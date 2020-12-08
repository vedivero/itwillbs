package com.itwillbs.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.basket.db.BasketDTO;

public class GoodsDAO {

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
	
	// GoodsListAll()
	public List<GoodsDTO> GoodsListAll(){
		
		List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
		
		try {
			getCon();
			
			sql = "select * from itwill_goods";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				GoodsDTO gdto = new GoodsDTO();
				
				gdto.setAmount(rs.getInt("amount"));
				gdto.setBest(rs.getInt("best"));
				gdto.setCategory(rs.getString("category"));
				gdto.setColor(rs.getString("color"));
				gdto.setContent(rs.getString("content"));
				gdto.setDate(rs.getDate("date"));
				gdto.setGno(rs.getInt("gno"));
				gdto.setImage(rs.getString("image"));
				gdto.setName(rs.getString("name"));
				gdto.setPrice(rs.getInt("price"));
				gdto.setSize(rs.getString("size"));
				
				goodsList.add(gdto);				
				
			}
			System.out.println("DAO : 상품정보 저장 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return goodsList;
	}
	// GoodsListAll()
	
	// GoodsList(item)
	public List<GoodsDTO> GoodsList(String item){
		List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
		StringBuffer SQL = new StringBuffer();
		// <String>
		//sql ="abc";
		//sql.toUpperCase(); // ABC
		// sql = abc
		
		//String tmp = sql.toUpperCase(); // ABC
		// sql = tmp; => ABC
		// <StringBuffer>
		// SQL = "abc";
		// SQL.substring(1); // bc
		// SQL => bc
		
		try {
			getCon();
			// 전체, 카테고리, 인기상품
			/*sql = "select * from itwill_goods";
			sql = "select * from itwill_goods where category=?";
			sql = "select * from itwill_goods where best=?";*/
			
			//SQL="select * from itwill_goods"; (x)
			SQL.append("select * from itwill_goods");
			
			if(item.equals("all")){				
			}else if(item.equals("best")){
				SQL.append(" where best=?");
			}else {
				SQL.append(" where category=?");
			}
			
			pstmt = con.prepareStatement(SQL.toString());
			//pstmt = con.prepareStatement(SQL+"");
			
			// ?
			if(item.equals("all")){				
			}else if(item.equals("best")){
				pstmt.setInt(1, 1); // DB-best값이 1인 요소 검색
			}else{// 카테고리
				pstmt.setString(1, item);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				GoodsDTO gdto = new GoodsDTO();
				
				gdto.setAmount(rs.getInt("amount"));
				gdto.setBest(rs.getInt("best"));
				gdto.setCategory(rs.getString("category"));
				gdto.setColor(rs.getString("color"));
				gdto.setContent(rs.getString("content"));
				gdto.setDate(rs.getDate("date"));
				gdto.setGno(rs.getInt("gno"));
				gdto.setImage(rs.getString("image"));
				gdto.setName(rs.getString("name"));
				gdto.setPrice(rs.getInt("price"));
				gdto.setSize(rs.getString("size"));
				
				goodsList.add(gdto);				
				
			}
			System.out.println("DAO : 상품정보 저장 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return goodsList;
	}
	// GoodsList(item)
	
	// getGoods(gno)
	public GoodsDTO getGoods(int gno){
		GoodsDTO gdto = null;
		
		try {
			getCon();
			
			sql ="select * from itwill_goods where gno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				gdto = new GoodsDTO();
				
				//gdto.setAmount(rs.getInt(1));
				
				gdto.setAmount(rs.getInt("amount"));
				gdto.setBest(rs.getInt("best"));
				gdto.setCategory(rs.getString("category"));
				gdto.setColor(rs.getString("color"));
				gdto.setContent(rs.getString("content"));
				gdto.setDate(rs.getDate("date"));
				gdto.setGno(rs.getInt("gno"));
				gdto.setImage(rs.getString("image"));
				gdto.setName(rs.getString("name"));
				gdto.setPrice(rs.getInt("price"));
				gdto.setSize(rs.getString("size"));
				
			}
			
			System.out.println("DAO : 상품번호에 해당하는 상품정보 저장완료");
			System.out.println("DAO : "+gdto);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return gdto;
	}
	// getGoods(gno)
	
	// updateAmount(basketList)
	// 구매후 처리 하는 동작
	public void updateAmount(List<BasketDTO> basketList){
		
		// 구매한 상품 만큼 수량 차감
		try {
			getCon();
			
			for(int i=0;i<basketList.size();i++){
				BasketDTO bkdto = basketList.get(i);
				sql ="update itwill_goods set amount=amount-? "
				 	+ "where gno=?";
			
			    pstmt = con.prepareStatement(sql);
			    pstmt.setInt(1, bkdto.getB_g_amount());
			    pstmt.setInt(2, bkdto.getB_g_num());
			    
			    pstmt.executeUpdate();
			}
			System.out.println("DAO : 구매후 상품 수량 수정완료~");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// updateAmount(basketList)
	
	
	
	
	
	

}
