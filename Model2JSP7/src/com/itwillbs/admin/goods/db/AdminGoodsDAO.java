package com.itwillbs.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGoodsDAO {
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

	// insertGoods(gdto)
	public void insertGoods(GoodsDTO gdto) {
		int num = 0;

		try {
			getCon();

			// 1) 상품등록번호 계산 - 게시판 글번호 처리
			sql = "select max(gno) from itwill_goods";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			System.out.println("DAO : 상품 번호 -> " + num);

			// 2) 상품등록(goods 테이블)
			sql = "insert into itwill_goods (gno,category,name,content,size,color," + "amount,price,image,best,date) "
					+ "values(?,?,?,?,?,?,?,?,?,?,now())";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, gdto.getCategory());
			pstmt.setString(3, gdto.getName());
			pstmt.setString(4, gdto.getContent());
			pstmt.setString(5, gdto.getSize());
			pstmt.setString(6, gdto.getColor());
			pstmt.setInt(7, gdto.getAmount());
			pstmt.setInt(8, gdto.getPrice());
			pstmt.setString(9, gdto.getImage());
			pstmt.setInt(10, gdto.getBest());

			pstmt.executeUpdate();

			System.out.println("DAO : 상품 등록 완료!! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	// insertGoods(gdto)

	// getGoodsList()
	public List<GoodsDTO> getGoodsList() {
		List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();

		try {
			getCon();
			sql = "select * from itwill_goods";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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

				// 리스트 한칸에 저장
				goodsList.add(gdto);
			} // while

			System.out.println("DAO : 등록된 상품 리스트에 저장완료");
			System.out.println(goodsList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return goodsList;
	}
	// getGoodsList()

	// getGoods(gno)
	public GoodsDTO getGoods(int gno) {
		GoodsDTO gdto = null;

		try {
			getCon();
			// 상품번호에 해당하는 상품정보 전부를 저장
			sql = "select * from itwill_goods where gno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				gdto = new GoodsDTO();

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
			System.out.println("DAO : 상품 검색 성공 " + gdto);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return gdto;
	}
	// getGoods(gno)

	// modifyGoods(gdto)
	public void modifyGoods(GoodsDTO gdto) {
		try {
			getCon();
			
			sql="update itwill_goods set category=?,name=?,price=?,color=?,"
					+ "amount=?,size=?,content=?,best=? where gno=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, gdto.getCategory());
			pstmt.setString(2, gdto.getName());
			pstmt.setInt(3, gdto.getPrice());
			pstmt.setString(4, gdto.getColor());
			pstmt.setInt(5, gdto.getAmount());
			pstmt.setString(6, gdto.getSize());
			pstmt.setString(7, gdto.getContent());
			pstmt.setInt(8, gdto.getBest());
			pstmt.setInt(9, gdto.getGno());
			
			int tmp = pstmt.executeUpdate();
			
			System.out.println("DAO : 상품정보 수정완료! -> "+tmp);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	// modifyGoods(gdto)
	
	//deleteGoods(gno)
	public void deleteGoods(int gno){
		
		try {
			getCon();
			
			sql="delete from itwill_goods where gno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gno);
			
			int check = pstmt.executeUpdate();
			
			System.out.println("DAO : 상품 삭제 완료 -> "+check);		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}		
	}
	//deleteGoods(gno)
	
	
	
	
	
	

}
