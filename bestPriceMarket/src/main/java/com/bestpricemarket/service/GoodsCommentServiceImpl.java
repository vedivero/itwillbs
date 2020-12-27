package com.bestpricemarket.service;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.bestpricemarket.domain.GoodsCommentVO;
import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.persistence.GoodsCommentDAO;

@Service
public class GoodsCommentServiceImpl implements GoodsCommentService {
	
	// DAO 의존 주입
	@Inject
	private GoodsCommentDAO gcdao;
	
	//댓글 목록
	@Override
	public List<GoodsCommentVO> commentList(Integer gno) throws Exception {
		System.out.println("S : 댓글 목록 서비스");
		return gcdao.commentList(gno);
	}

	//댓글 목록 each
	@Override
	public GoodsCommentVO commentListEach(Integer c_num) throws Exception {
		return gcdao.commentListEach(c_num);
	}

	//AI제약조건 있는 c_num가져오기
	@Override
	public int getCnum() throws Exception {
		return gcdao.getCnum();
	}

	// 댓글 등록
	@Override
	public void commentInsert(GoodsCommentVO cmt){
		System.out.println("S : 댓글 등록 서비스");
		if(cmt==null) {
			System.out.println("S : cmt null임");
		}
		
		try { 
			gcdao.commentInsert(cmt);
		} catch (Exception e) {
			System.out.println("S : 댓글 등록 예외 발생");
			e.printStackTrace();
		}
	}

	// 댓글 수정
	@Override
	public void commentUpdate(GoodsCommentVO cmt) throws Exception {
		gcdao.commentUpdate(cmt);
	}

	//댓글 삭제
	@Override
	public void commentDelete(GoodsCommentVO cmt) throws Exception {
		gcdao.commentDelete(cmt); 
	}

	//c_ref와 c_num 동기화
	@Override
	public void syncC_ref(Integer c_num) throws Exception {
		gcdao.syncC_ref(c_num);
	}

	//대댓글 등록
	@Override
	public void rereplyInsert(GoodsCommentVO cmt) throws Exception {
		gcdao.rereplyInsert(cmt);
	}

	

}