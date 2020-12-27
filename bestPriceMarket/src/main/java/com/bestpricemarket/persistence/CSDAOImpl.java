package com.bestpricemarket.persistence;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bestpricemarket.domain.CSVO;

@Repository
public class CSDAOImpl implements CSDAO {

	@Inject
	private SqlSession session;
	
	
	private static final String namespace
		="com.bestpricemarket.mapper.CSMapper";
		
	//글쓰기
	@Override
	public void create(CSVO vo) throws Exception {
		
		System.out.println("@@@csbno : "+vo.getCsbno());
		System.out.println("@@@cs_ref : "+vo.getCs_ref());
		session.insert(namespace+".create", vo);
		int num=0;
		
		if(num == 0) {
			num = (int)session.selectOne(namespace+".maxbno");
		}

		session.update(namespace+".bnoup", num);	
	}

	//글목록
	@Override
	public List<CSVO> listAll() throws Exception {

		
		List<CSVO> CSList =
				session.selectList(namespace+".listAll");
		
		return CSList;
	}

	//글내용
	@Override
	public CSVO content(Integer csbno) throws Exception {
		
		CSVO vo = session.selectOne(namespace+".content",csbno);
		
		return vo;
	}

	@Override
	public void modify(CSVO vo) throws Exception {
		session.update(namespace+".modify",vo);
	}

	//글삭제
    @Override
    public void delete(int csbno) throws Exception {
      session.delete(namespace + ".delete", csbno);
   }

    //등록된 글의 총 갯수
	@Override
	public int count() throws Exception {
	
		int count = session.selectOne(namespace+".count");
		return count;
	}

	//글 목록 + 페이징처리
	@Override
	public List<CSVO> listPage(int displayPost, int postNum) throws Exception {

		HashMap data = new HashMap();
		
		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		
		List<CSVO> CSList = session.selectList(namespace+ ".listPage",data);
		
		return CSList;
	}

	@Override
	public void replyCreate(CSVO vo) throws Exception {
		
		session.insert(namespace+".replyCreate", vo);

		session.update(namespace+".united");
		System.out.println("bnoup : ref일치 완료");
		
	}

}
