package com.bestpricemarket.service;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bestpricemarket.domain.CSVO;
import com.bestpricemarket.persistence.CSDAO;

@Service
public class CSServiceImpl implements CSService{

	@Inject
	private CSDAO cdao;
	
	@Override
	public void regist(CSVO vo) throws Exception {

		cdao.create(vo);
	}

	@Override
	public List<CSVO> listAll() throws Exception {

		System.out.println("service에서 listAll()를 호출");
		
		List<CSVO> CSList = cdao.listAll();
	
		return CSList;
		
	}

	@Override
	public CSVO content(Integer csbno) throws Exception {

		System.out.println("S : content(csbno) 호출 ");
			System.out.println("S : DAO 객체 사용해서 해당 동작처리 ");
			
			CSVO vo = cdao.content(csbno);
			System.out.println("S : DAO 실행완료");
			System.out.println("S : 컨트롤러 페이지 이동");
			
			return vo;
		}

	@Override
	public void modify(CSVO vo) throws Exception {

		cdao.modify(vo);
		
	}
	
   @Override
   public void delete(int csbno) throws Exception {
      System.out.println("S : delete(csbno) 호출");
      cdao.delete(csbno);
      System.out.println("cdao에서 삭제 완료");
   }

	@Override
	public int count() throws Exception {
		
		int count = cdao.count();
		
		return count;
	}

	//글 목록 + 페이징처리
	@Override
	public List<CSVO> listPage(int displayPost, int postNum) throws Exception {

		List<CSVO> CSList = cdao.listPage(displayPost, postNum);
		return CSList;
	}

	//답글 등록
	@Override
	public void replyRegist(CSVO vo) throws Exception {
	
			cdao.replyCreate(vo);
		
	}
	
   
	
   
   
}

	

	
