package com.bestpricemarket.persistence;

import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.bestpricemarket.domain.GoodsCommentVO;

@Repository
public class GoodsCommentDAOImpl implements GoodsCommentDAO {
	
	// DB 의존 주입
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.bestpricemarket.mappers.commentMapper";
	
	//댓글 목록
	@Override
	public List<GoodsCommentVO> commentList(Integer gno) throws Exception{
		return sqlSession.selectList(namespace + ".commentList", gno);
	}
	
	//댓글 목록 each
	@Override
	public GoodsCommentVO commentListEach(Integer c_num) throws Exception {
		return sqlSession.selectOne(namespace + ".commentListEach", c_num);
	}

	// AI제약조건 있는 c_num가져오기
	@Override
	public int getCnum() throws Exception {
		return sqlSession.selectOne(namespace + ".getCnum");
	}

	// 댓글 작성
	@Override
	public void commentInsert(GoodsCommentVO cmt) throws Exception{
		sqlSession.insert(namespace + ".commentInsert", cmt);
	}
	
	//댓글 수정
	@Override
	public void commentUpdate(GoodsCommentVO cmt) throws Exception {
		sqlSession.update(namespace+".commentUpdate", cmt);
	}

	//댓글 삭제
	@Override
	public void commentDelete(GoodsCommentVO cmt) throws Exception {
		sqlSession.update(namespace+".commentDelete", cmt);
	}

	//c_ref와 c_num 동기화
	@Override
	public void syncC_ref(Integer c_num) throws Exception {
		sqlSession.update(namespace+".syncC_ref", c_num);
	}
	
	//대댓글 등록
	@Override
	public void rereplyInsert(GoodsCommentVO cmt) throws Exception {
		sqlSession.insert(namespace + ".rereplyInsert", cmt);
	}
	
	
	

}