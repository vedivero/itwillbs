package com.bestpricemarket.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bestpricemarket.domain.AnotherGoodsVO;
import com.bestpricemarket.domain.Criteria;
import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.LikesVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.domain.PricemonitoringVO;
import com.bestpricemarket.domain.ReportVO;
import com.bestpricemarket.domain.finalBidVO;
import com.bestpricemarket.persistence.GoodsDAO;
import com.bestpricemarket.utils.FileUtils;

@Service
public class GoodsServiceImpl implements GoodsService {

	// DAO 의존 주입
	@Inject
	private GoodsDAO gdao;

	// 지은 *****************************************************************************************************************
	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	// 상품등록
	@Override
	public void goodsRegister(GoodsVO vo, MultipartHttpServletRequest mpRequest) throws Exception {

		System.out.println("S : 상품 목록" + vo);

		gdao.registerGoods(vo);
		
		List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(vo, mpRequest);
		for (int i = 0; i < list.size(); i++) {
			gdao.insertFile(list.get(i));

		}

		// list에서 f_name값 가져와서 vo의 섬네일에 저장
		vo.setThumbnail(list.get(0).get("f_name").toString());
		
		// list에서 gno값을 가져와서 setGno 매개변수로 사용 
		gdao.setGno(Integer.parseInt(list.get(0).get("gno").toString()));

		System.out.println("@@@@@@@SSSSS " + vo);

	}


	// 카테고리별 상품 목록 + 페이징처리
	@Override
	public List<GoodsVO> goodsCategoryList(String category, Criteria cri) throws Exception {

		System.out.println("S : 카테고리 분류 ");

		System.out.println("S 카테고리 실행 ? " + gdao.goodsCategoryList(category, cri));

		return gdao.goodsCategoryList(category, cri);
	}

	
	// 카테고리별 글 개수 가져오는 처리
	@Override
	public int CategoryCount(String category) throws Exception {
		
		int result = gdao.CategoryCount(category);
		
		System.out.println("S : 카테고리글 개수 -> " + result);
		
		return result;
	}

	// 상품조회(상품상세페이지)
	@Override
	public GoodsVO goodsDetail(int gno) throws Exception {

		GoodsVO vo = gdao.goodsDetail(gno);

		return vo;
	}

	// 상품수정
	@Override
	public void goodsModify(GoodsVO vo, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest)
			throws Exception {
		System.out.println("S : 상품 수정");
		gdao.goodsModify(vo);

		System.out.println("S : 수정된 상품 정보 -> " + vo);

		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(vo, files, fileNames, mpRequest);
		Map<String, Object> tempMap = null;

		for (int i = 0; i < list.size(); i++) {
			tempMap = list.get(i);
			if (tempMap.get("IS_NEW").equals("Y")) {
				gdao.insertFile(tempMap);
			} else {
				gdao.updateFile(tempMap);
			}
		}
	}

	// 상품삭제
	@Override
	public void goodsDelete(int gno) throws Exception {
		System.out.println("S : 상품 삭제");
		gdao.goodsDelete(gno);
	}

	// 첨부파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int gno) throws Exception {

		System.out.println("S : 첨부파일 조회");

		return gdao.selectFileList(gno);
	}

	// 첨부파일 다운로드
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {

		System.out.println("S : 첨부파일 다운로드");

		return gdao.selectFileInfo(map);
	}

	
	// 현재 입찰가
	@Override
	public int finalPrice(int gno) throws Exception {

		Integer result = gdao.finalPrice(gno);
		
		return result;
	}
	
	// 상품 테이블 현재 입찰가 업데이트
	@Override
	public void finalpriceupdate(int gno) throws Exception {
		
		gdao.finalpriceupdate(gno);
		
	}

	// 블락된 회원 가져오기 
	@Override
	public MemberVO blockMember(String id) throws Exception {
		
		return gdao.blockMember(id);
	}
	
// 재원 ************************************************************************************************************************
	// 상품신고
	@Override
	public MemberVO myInfo(String id) throws Exception {
		return gdao.myInfo(id);
	}

	@Override
	public ReportVO showReportDetail(int gno) throws Exception {
		ReportVO vo = gdao.showReportDetail(gno);
		return vo;
	}
	
	// 입찰하기
	@Override
	public List<PricemonitoringVO> getBidding(int pm_g_gno) throws Exception {
		List<PricemonitoringVO> prvo = gdao.getBidding(pm_g_gno);
		return prvo;
	}

	@Override
	public int getMaxPrice(int pm_g_gno) throws Exception {
		int maxVO = gdao.getMaxPrice(pm_g_gno);
		return maxVO;
	}

	@Override
	public void upStatus(int gno) throws Exception {
		gdao.upStatus(gno);
		
	}

	@Override
	public void endStatus(int gno) throws Exception {
		gdao.endStatus(gno);		
	}
	
	@Override
	public void insertBidding(PricemonitoringVO prvo) throws Exception {
		gdao.insertBidding(prvo);
		gdao.numofbid(prvo.getPm_g_gno());
		System.out.println("pm_g_gno가 뭐니?" + prvo.getPm_g_gno());
	}
	
	@Override
	public int getTotalCount(Criteria cri) throws Exception {
		
		return gdao.getTotalCount(cri);
	}

	// *************** 2020/11/16/월요일 낙찰정보 **************************
	@Override
	public finalBidVO finalBid(int gno) throws Exception {
		
		return gdao.finalBid(gno);
	}

	@Override
	public void insertMyAction(finalBidVO fivo) throws Exception {
		gdao.insertMyAction(fivo);
		
	}
	// *************** 2020/11/16/월요일 낙찰정보끝 **************************
		// *************** 2020/11/16/월요일 낙찰정보끝 **************************

// 태준 *******************************************************************************************************************************
	//판매자의 다른상품보기
	@Override
	public List<AnotherGoodsVO> anothergoods(GoodsVO vo) throws Exception {
		return gdao.anothergoods(vo);
	}
	
// 정현 *******************************************************************************************************************************
	@Override
    public int like (LikesVO vo) throws Exception {
		System.out.println("S : 좋아요 클릭(->likes 테이블)");
        return gdao.like(vo);
    }

	// 좋아요 입력 -> 제품상세페이지(goods테이블 like컬럼)
	@Override
	public void goodsLike(int gno) throws Exception {
		System.out.println("S : 좋아요 클릭(->goods 테이블 like 컬럼" + gno);
		gdao.goodsLike(gno);
	}  
	
	@Override
	public int countbyLike(String l_m_id, int gno){
	    return gdao.countbyLike(l_m_id, gno);
	}  

	@Override
	public LikesVO read(LikesVO vo) {
		System.out.println("S : 좋아요 조회");  
	    LikesVO read = gdao.read(vo);
	    return read;
	}  

	// 좋아요 취소(goods테이블 like컬럼)
	@Override
	public void deletebyGoods(int gno) {
		System.out.println("S : 좋아요 취소(->goods 테이블 glike 컬럼"+gno);  
	    gdao.deletebyGoods(gno);
	}

	// 좋아요 취소(likes테이블) 
	@Override
	public void deletebyLikes(String l_m_id, int l_g_gno) {
		System.out.println("S : 좋아요 취소(->likes테이블)"+l_m_id+l_g_gno);  
	    gdao.deletebyLikes(l_m_id, l_g_gno);
	}
	
	// 메인페이지 옵션바
    @Override
	public List<GoodsVO> orderbyNew(Criteria cri) throws Exception {
    	return gdao.orderbyNew(cri);
	}
	
	@Override
	public List<GoodsVO> orderbyDuedate(Criteria cri) throws Exception {
		return gdao.orderbyDuedate(cri);
	}
	
	@Override
	public List<GoodsVO> orderbyBest(Criteria cri) throws Exception {
		return gdao.orderbyBest(cri);
	}

// 소원 ************************************************************************************************************************
	// 상품목록 + 페이징처리	
	@Override	
	public List<GoodsVO> goodsList(Criteria cri) throws Exception {	
		return gdao.listGoods(cri);	
	}

	// 전체 글 개수 가져오는 처리	
	@Override	
	public int listTotalCount() throws Exception {	
		int result = gdao.pageCount();	
		return result;	
	}

	//입찰자수 가져오기
	@Override
	public int gd_bidCount(int gno) throws Exception {
		return gdao.gd_bidCount(gno);
	}

	//입찰수 높은 상품 3가지 슬라이드로 출력
	@Override
	public List<GoodsVO> top3goods(Criteria cri) throws Exception {
		System.out.println("탑쓰리는? " +gdao.top3goods(cri));
		return gdao.top3goods(cri);
	}

	//좋아요 유지
	@Override
	public int isClickedLikeBtn(int gno, String id) throws Exception {
		return gdao.isClickedLikeBtn(gno, id);
	}
	
	
}