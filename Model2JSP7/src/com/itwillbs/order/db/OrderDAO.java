package com.itwillbs.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.collections4.bag.TreeBag;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.basket.db.BasketDTO;

public class OrderDAO {

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
	
	// addOrder(odto,basketList,goodsList)
	public void addOrder(OrderDTO odto,List basketList,List goodsList){
		
		int o_num = 0; // 일련번호
		int trade_num = 0; // 주문번호
		
		// 주문번호 계산시 사용
		Calendar cal = Calendar.getInstance(); // 시스템 시간정보
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		
		
		try {
			getCon();
			
			//1) 주문테이블(itwillbs_order) 번호 계산하기
			sql = "select max(o_num) from itwill_order";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				o_num = rs.getInt(1)+1;
			}
			
			trade_num = o_num;
			System.out.println("trade_num : "+trade_num);
			System.out.println("o_num : "+o_num);
			
			//2) 전달정보 사용해서 데이터베이스에 추가
			for(int i=0;i<basketList.size();i++){
				 BasketDTO bkdto = (BasketDTO)basketList.get(i);
				 GoodsDTO gdto = (GoodsDTO)goodsList.get(i);
				
				sql="insert into itwill_order "
						+ "values(?,?,?,?,?,"
						+ "?,?,?,?,?,"
						+ "?,?,?,?,?,"
						+ "?,now(),?,now(),?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, o_num);
				// 20200917-1,2,3....
				pstmt.setString(2, sdf.format(cal.getTime())+"-"+trade_num);
				pstmt.setInt(3, bkdto.getB_g_num()); //구매할 상품 번호
				pstmt.setString(4,gdto.getName()); // 상품이름
				pstmt.setInt(5, bkdto.getB_g_amount()); //구매할 상품 개수
				pstmt.setString(6, bkdto.getB_g_size()); // 구매할 상품 크기(옵션)
				pstmt.setString(7, bkdto.getB_g_color()); // 구매할 상품 색상(옵션)
				pstmt.setString(8, bkdto.getB_m_id()); //odto에도 ID정보가 있음
				
				pstmt.setString(9,odto.getO_receive_name()); // 받는사람이름
				pstmt.setString(10, odto.getO_receive_addr1()); // 주소
				pstmt.setString(11, odto.getO_receive_addr2()); // 주소
				pstmt.setString(12, odto.getO_receive_phone()); // 전화번호
				pstmt.setString(13, odto.getO_memo()); // 메모
				
				// 해당주문의 금액 = 상품의 금액 * 구매 수량
				pstmt.setInt(14, gdto.getPrice() * bkdto.getB_g_amount());
				
				pstmt.setString(15, odto.getO_trade_type()); // 구매방법
				pstmt.setString(16, odto.getO_trade_payer()); // 구매자정보
				pstmt.setString(17, ""); //운송장번호 - 추후 관리자가 변경
				pstmt.setInt(18, 0); // 주문상태 - 추후 관리자가 변경
				
				// 실행
				pstmt.executeUpdate();
				
				o_num++; // 일련변호를 증가
				// 한사람의 장바구니에 있는 모든 주문을 입력하기전 까지
				// 계속해서 1씩 증가  
				// -> 사람이 바뀌는 경우  SQL구문을 사용해서 시작 번호계산
				
			} //for

			System.out.println("주문 성공~!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	// addOrder(odto,basketList,goodsList)
	
	// getOrderList(id)
	public List getOrderList(String id){
		List orderList = new ArrayList();
		
		try {
			getCon();
			
			sql = "select o_trade_num,o_g_name,o_g_amount,"
					+ "o_g_size,o_g_color,sum(o_sum_money) as o_sum_money,"
					+ "o_trans_num,o_date,o_status,o_trade_type "
					+ "from itwill_order "
					+ "where o_m_id=? "
					+ "group by o_trade_num order by o_trade_num desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				OrderDTO ordto = new OrderDTO();
				
				ordto.setO_date(rs.getDate("o_date"));
				ordto.setO_g_amount(rs.getInt("o_g_amount"));
				ordto.setO_g_color(rs.getString("o_g_color"));
				ordto.setO_g_size(rs.getString("o_g_size"));
				ordto.setO_g_name(rs.getString("o_g_name"));
				ordto.setO_trade_num(rs.getString("o_trade_num"));
				ordto.setO_trans_num(rs.getString("o_trans_num"));
				ordto.setO_sum_money(rs.getInt("o_sum_money"));
				ordto.setO_status(rs.getInt("o_status"));
				ordto.setO_trade_type(rs.getString("o_trade_type"));
				
				orderList.add(ordto);
				
			}
			
		   System.out.println("DAO : 주문목록 저장완료");	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return orderList;
	}
	// getOrderList(id)
	
	// getOrderDetail(trade_num)
	public List<OrderDTO> getOrderDetail(String trade_num){
		List<OrderDTO> orderDetailList = new ArrayList<OrderDTO>();
		
		try {
			getCon();
			sql ="select * from itwill_order "
					+ "where o_trade_num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, trade_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				OrderDTO ordto = new OrderDTO();
				
				ordto.setO_date(rs.getDate("o_date"));
				ordto.setO_g_amount(rs.getInt("o_g_amount"));
				ordto.setO_g_color(rs.getString("o_g_color"));
				ordto.setO_g_size(rs.getString("o_g_size"));
				ordto.setO_g_name(rs.getString("o_g_name"));
				ordto.setO_trade_num(rs.getString("o_trade_num"));
				ordto.setO_trans_num(rs.getString("o_trans_num"));
				ordto.setO_sum_money(rs.getInt("o_sum_money"));
				ordto.setO_status(rs.getInt("o_status"));
				ordto.setO_trade_type(rs.getString("o_trade_type"));
				
				orderDetailList.add(ordto);
				
			}//while
			
			System.out.println("DAO : 주문 상세정보 저장완료! ");			
			
		} catch (Exception e) {
			System.out.println("DAO : 주문 상세정보 저장 실패! ");
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return orderDetailList;
	}
	// getOrderDetail(trade_num)
	
	
	
	
	
	
	
	
	

}