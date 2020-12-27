package com.bestpricemarket.persistence;

import java.util.List;

import com.bestpricemarket.domain.CSVO;

public interface CSDAO {

	//글쓰기
	public void create(CSVO vo)throws Exception;
	
	//글목록
	public List<CSVO> listAll() throws Exception;
	
	//글보기
	public CSVO content(Integer csbno) throws Exception;
	
	//글수정
	public void modify(CSVO vo)throws Exception;
	
	//글삭제
    public void delete(int csbno) throws Exception;
    
    //등록된 글의 총 갯수
    public int count() throws Exception;
    
    //글 목록 + 페이징처리
    public List<CSVO> listPage(int displayPost, int postNum)throws Exception;
    
    public void replyCreate(CSVO vo)throws Exception;
    
}
