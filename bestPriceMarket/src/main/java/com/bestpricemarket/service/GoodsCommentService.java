package com.bestpricemarket.service;

import java.util.List;

import com.bestpricemarket.domain.GoodsCommentVO;

public interface GoodsCommentService {
	
	// 댓글 목록
	public List<GoodsCommentVO> commentList(Integer gno) throws Exception;
	
	// 댓글 목록 each
	public GoodsCommentVO commentListEach(Integer gno) throws Exception;
	
	//AI제약조건 있는 c_num가져오기
	public int getCnum() throws Exception;
	
	// 댓글 개수 
	// public int count(int c_num);  
	
	// 댓글 작성
	public void commentInsert(GoodsCommentVO cmt) throws Exception;
	
	// 댓글 수정
	public void commentUpdate(GoodsCommentVO cmt) throws Exception;
	
	// 댓글 삭제
	public void commentDelete(GoodsCommentVO cmt) throws Exception;
	
	//c_ref와 c_num 동기화
	public void syncC_ref(Integer c_num) throws Exception;
	
	//대댓글 등록
	public void rereplyInsert(GoodsCommentVO cmt) throws Exception;

}