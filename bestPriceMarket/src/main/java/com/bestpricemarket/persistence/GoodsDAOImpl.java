package com.bestpricemarket.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bestpricemarket.domain.AnotherGoodsVO;
import com.bestpricemarket.domain.Criteria;
import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.LikesVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.domain.PricemonitoringVO;
import com.bestpricemarket.domain.ReportVO;
import com.bestpricemarket.domain.finalBidVO;

@Repository
public class GoodsDAOImpl implements GoodsDAO {

	// DB 의존 주입
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.bestpricemarket.mappers.goodsMapper";

// 지은 *********************************************************************************************************************************
	// 상품등록
	@Override
	public void registerGoods(GoodsVO vo) throws Exception {
		System.out.println("DAO : 상품등록" + vo);
		sqlSession.insert(namespace + ".register", vo);
	}

	// SetGno
	@Override
	public void setGno(int gno) throws Exception {
		sqlSession.update(namespace + ".setGno", gno);
	}

	// 카테고리별 상품 목록 + 페이징처리 ????????????????????????????????????????????
	@Override
	public List<GoodsVO> goodsCategoryList(String category, Criteria cri) throws Exception {
		System.out.println("DAO : 카테고리 분류");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		map.put("cri", cri);
		return sqlSession.selectList(namespace + ".category", map);
	}

	// 카테고리별 글 개수 가져오는 처리
	@Override
	public int CategoryCount(String category) throws Exception {
		int result = sqlSession.selectOne(namespace+".categoryCount",category);
		System.out.println("DAO : 카테고리글 개수 -> " + result);
		return result;
	}
	
	// 상품조회(상품상세페이지)
	@Override
	public GoodsVO goodsDetail(int gno) throws Exception {
		System.out.println("DAO : 상품 조회");
		return sqlSession.selectOne(namespace + ".detailGoods", gno);
	}

	// 상품수정
	@Override
	public void goodsModify(GoodsVO vo) throws Exception {
		System.out.println("DAO : 상품 수정");
		sqlSession.update(namespace + ".modifyGoods", vo);
		System.out.println("DAO : 수정된 상품 정보 -> " + vo);
	}

	// 상품삭제
	@Override
	public void goodsDelete(int gno) throws Exception {
		System.out.println("DAO : 상품 삭제");
		sqlSession.delete(namespace + ".deleteGoods", gno);
	}

	// 첨부파일 업로드
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		System.out.println("DAO : 첨부 파일 업로드 -> " + map);
		sqlSession.insert(namespace + ".insertFile", map);
	}

	// 첨부파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int gno) throws Exception {
		System.out.println("DAO : 첨부 파일 조회 -> " + gno);
		return sqlSession.selectList(namespace + ".selectFileList", gno);
	}

	// 첨부파일 수정
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {
		System.out.println("DAO : 첨부파일 수정");
		sqlSession.update(namespace + ".updateFile", map);
	}
	
	
	// 첨부파일 다운로드
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		System.out.println("DAO : 첨부파일 다운로드");
		return sqlSession.selectOne(namespace + ".selectFileInfo", map);
	}

	// 현재 입찰가
	@Override
	public int finalPrice(int gno) throws Exception {
		int result = sqlSession.selectOne(namespace+".getMaxPrice",gno);
		return result;
	}
	
	//상품 테이블 현재 입찰가 업데이트
	@Override
	public void finalpriceupdate(int gno) throws Exception {
		sqlSession.update(namespace+".finalpriceupdate",gno);
		
	}

	// 블락된 회원 가져오기 
	@Override
	public MemberVO blockMember(String id) throws Exception {

		return sqlSession.selectOne(namespace+".block", id);
	}
	
// 재원 *******************************************************************************************************************************
	// 상품신고
	@Override
	public MemberVO myInfo(String id) throws Exception {
		return sqlSession.selectOne(namespace + ".myInfo", id);
	}

	@Override
	public ReportVO showReportDetail(int gno) throws Exception {
		return sqlSession.selectOne(namespace + ".showReportDetail", gno);
	}

	// 입찰하기
	@Override
	public List<PricemonitoringVO> getBidding(int pm_g_gno) throws Exception {
		List<PricemonitoringVO> prvo = sqlSession.selectList(namespace + ".getBidding", pm_g_gno);
		return prvo;
	}

	@Override
	public int getMaxPrice(int pm_g_gno) throws Exception {
		int maxVO = sqlSession.selectOne(namespace + ".getMaxPrice", pm_g_gno);
		return maxVO;
	}
	
	@Override
	public void upStatus(int gno) throws Exception {
		sqlSession.update(namespace + ".updateStatus",gno);		
	}

	@Override
	public void endStatus(int gno) throws Exception {
		sqlSession.update(namespace + ".endStatus",gno);
		
	}

	@Override
	public void insertBidding(PricemonitoringVO prvo) throws Exception {
		sqlSession.selectOne(namespace + ".insertBidding", prvo);
	}
	
	@Override
	public int getTotalCount(Criteria cri) throws Exception {
		
		return sqlSession.selectOne(namespace + ".pageCount",cri);
	}
	

	// *************** 2020/11/16/월요일 낙찰정보 **************************
	
		@Override
		public finalBidVO finalBid(int gno) throws Exception {
			return sqlSession.selectOne(namespace + ".getFinalBid",gno);
		}

		@Override
		public void insertMyAction(finalBidVO fivo) throws Exception {
			sqlSession.insert(namespace + ".insertMyAction",fivo);
			
		}
		
		// *************** 2020/11/16/월요일 낙찰정보끝 **************************
// 태준 *******************************************************************************************************************************
	// 판매자의 다른상품보기
	@Override
	public List<AnotherGoodsVO> anothergoods(GoodsVO vo) throws Exception {
		return sqlSession.selectList(namespace+".anothergoods",vo);
	}

// 정현 *******************************************************************************************************************************
	// 좋아요 입력 -> 제품상세페이지(likes 테이블)
	@Override
	public int like(LikesVO vo) throws Exception {
		System.out.println("DAO : 좋아요 클릭(likes테이블)");
		return sqlSession.insert(namespace + ".create", vo);
	}
  
	// 좋아요 입력 -> 제품상세페이지(goods테이블의 like컬럼)
	@Override
	public void goodsLike(int gno) throws Exception {
		System.out.println("DAO : 좋아요 클릭(goods테이블 like컬럼)" + gno);
		sqlSession.update(namespace + ".goods_update", gno);
	}

	@Override
	public int countbyLike(String l_m_id, int gno){
		System.out.println("DAO : 좋아요 삭제(likes 테이블)" + l_m_id);
		Map<String, Object> likeInsertMap = new HashMap<String, Object>();
		likeInsertMap.put("l_m_id", l_m_id);
		likeInsertMap.put("gno", gno);
		return sqlSession.selectOne(namespace + ".countbyLike", likeInsertMap);
	}

	@Override
	public LikesVO read(LikesVO vo) {
		System.out.println("DAO : 좋아요 조회");
		LikesVO read = sqlSession.selectOne(namespace + ".read", vo);
		return read;
	}  

	// 좋아요 삭제 (goods테이블 glike컬럼)
	@Override
	public void deletebyGoods(int gno) {
		System.out.println("DAO : 좋아요 삭제(goods테이블 glike컬럼)"+gno);
		sqlSession.update(namespace + ".deletebyGoods", gno);
	}

	// 좋아요 삭제 (likes테이블) 
	@Override
	public void deletebyLikes(String l_m_id, int l_g_gno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("l_m_id", l_m_id);
		map.put("l_g_gno", l_g_gno);
		System.out.println("DAO : 좋아요 삭제(likes 테이블)의 파라미터 두개는 " + l_m_id + l_g_gno+" map은? "+map);
		sqlSession.delete(namespace + ".deletebyLikes", map);  
	}
	
	// 메인페이지 옵션바
	@Override
	public List<GoodsVO> orderbyNew(Criteria cri) throws Exception {
		System.out.println("DAO : 옵션바 - 신규등록순");
		return sqlSession.selectList(namespace + ".orderbyNew", cri);
	}
	
	@Override
	public List<GoodsVO> orderbyDuedate(Criteria cri) throws Exception {
		System.out.println("DAO : 옵션바 - 마감임박순");
		return sqlSession.selectList(namespace + ".orderbyDuedate", cri);
	}

	@Override
	public List<GoodsVO> orderbyBest(Criteria cri) throws Exception {
		System.out.println("DAO : 옵션바 - 인기경매순");
		return sqlSession.selectList(namespace + ".orderbyBest", cri);
	}

// 소원 ************************************************************************************************************************
	// 상품목록 + 페이징처리	
	@Override	
	public List<GoodsVO> listGoods(Criteria cri) throws Exception {	
		return sqlSession.selectList(namespace + ".listGoods", cri);	
	}
	
	// DB goods테이블에 있는 모든 상품글의 개수 가지고 오는 처리	
	@Override	
	public int pageCount() throws Exception {	
		int result = sqlSession.selectOne(namespace + ".pageCount");	
		return result;	
	}

	//입찰자수 가져오기
	@Override
	public int gd_bidCount(int gno) throws Exception {
		return sqlSession.selectOne(namespace+".gd_bidCount", gno);
	}

	//numofbid 입찰자수 전체
	@Override
	public void numofbid(int pm_g_gno) throws Exception {
		sqlSession.update(namespace+".numofbid", pm_g_gno);
	}

	//입찰수 높은 상품 3가지 슬라이드로 출력
	@Override
	public List<GoodsVO> top3goods(Criteria cri) throws Exception {
		System.out.println("DAO: 탑쓰리는? "+sqlSession.selectList(namespace + ".top3goods", cri));
		return sqlSession.selectList(namespace + ".top3goods", cri);	
	}

	//좋아요유지
	@Override
	public int isClickedLikeBtn(int gno, String id) throws Exception {
		Map<String, Object> isClickedLikeBtnMap = new HashMap<String, Object>();
		isClickedLikeBtnMap.put("gno", gno);
		isClickedLikeBtnMap.put("id", id);
		return sqlSession.selectOne(namespace+".isClickedLikeBtn", isClickedLikeBtnMap);
	}	
	
	
	
}