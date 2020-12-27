package com.bestpricemarket.service;

import java.util.List;

import com.bestpricemarket.domain.CSVO;

public interface CSService {

	//글등록
	public void regist(CSVO vo) throws Exception;
	
	//글목록
	public List<CSVO> listAll() throws Exception;
	
	//글내용
	public CSVO content(Integer csbno) throws Exception;
	
	//글수정
	public void modify(CSVO vo)throws Exception;
	
    // 게시물 삭제
    public void delete(int csbno) throws Exception;
   
    //등록된 글의 총 갯수
    public int count() throws Exception;
    
    //글 목록 + 페이징처리
    public List<CSVO> listPage(int displayPost, int postNum)throws Exception;
    
    //답글 등록
    public void replyRegist(CSVO vo)throws Exception;
    
}
