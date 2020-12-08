package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

// @Repository : DAO객체를 스프링에서 인식 할 수 있도록 처리
//              DAO 객체를 구현한 객체로 사용하도록 지정
@Repository
public class MemberDAOImpl implements MemberDAO {
	// DAO 동작
	
	// 디비 연결 (의존 주입)
	@Inject
	private SqlSession sqlSession;
	// -> Mapper가 있는 위치까지 접근
	
	// Mapper를 구분하는 값
	private static final String namespace
	   ="com.itwillbs.mapper.MemberMapper";
	

	@Override
	public String getTime() {
		System.out.println("DAO : DB접근 sqlSession객체를 주입!");
		System.out.println("DAO : TEST에서 해당메서드 호출!!! ");
		System.out.println("DAO : memberMapper.xml 이동");
		System.out.println("DAO : Select구문을 실행하는 메서드를 실행해서 SQL구문 실행");
		//"com.itwillbs.mapper.MemberMapper.getTime";
		String result = sqlSession.selectOne(namespace+".getTime");
		System.out.println("DAO : SQL 구문 실행완료! 값 리턴 후 테스트로 이동");	
		return result;
	}

	@Override
	public void insertMember(MemberVO vo) {
		
		sqlSession.insert(namespace+".insertMember",vo);
		
	}

	@Override
	public MemberVO readMember(String userid) throws Exception {

		System.out.println("DAO :  readMember(userid) 호출 ");
		// 테스트(컨트롤러) 호출 -> 정보를 저장해서 -> DB
		// DB에 접근 가능하도록 생성한 객체 
		MemberVO vo =
		sqlSession.selectOne(namespace+".readMember",userid);
		
		System.out.println("DAO : readMember 쿼리 실행 완료, 정보 리턴");
		
		return vo;
	}
	
	
    // 인터페이스 선언 -> 서브클래스 구현
	@Override
	public MemberVO readMemberWithIDPW(String userid, String userpw) throws Exception {
		
		Map<String, Object> paramMap 
		= new HashMap<String, Object>();
		
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		// DB로 정보를 전달하기 위해서는 sqlSeesion 객체 활용
		// * 1개 이상의 정보를 전달할때는 객체 단위로 전달
		// * 객체(VO) 안에 저장이 안되는 정보의 경우 Map을 사용
		// => key-value : 이때 key값은 sql구문의 #{ㅇㅇㅇ} 이름과 같아야함
		
		
		
		MemberVO vo =
		//sqlSession.selectOne(namespace+".readWithIDPW",userid,userpw);
		sqlSession.selectOne(namespace+".readWithIDPW",paramMap);
		
		return vo;
		//return sqlSession.selectOne(namespace+".readWithIDPW");
	}

	@Override
	public void updateMember(MemberVO vo) {
		System.out.println("DAO : updateMember(vo) 호출");
		System.out.println("DAO : 정보가지고 mapper 이동 ");
		
		sqlSession.update(namespace+".memberUpdate", vo);
		
		System.out.println("DAO : 수정완료 ! ");
		
	}

	@Override
	public void deleteMember(MemberVO vo) {
		System.out.println("DAO : deleteMember(vo) 호출 ");
		System.out.println("DAO : mapper 사용 sql 호출");
		
		int check = sqlSession.delete(namespace+".memberDelete",vo);
		System.out.println("DAO : check -> "+check);
		System.out.println("DAO : SQL 실행완료, 서비스로 이동");
		
	}

	@Override
	public List<MemberVO> listMember() {
		System.out.println("DAO : listMember(vo) 호출");
		System.out.println("DAO : mapper 사용 해당 동작 호출 ");
		
		List<MemberVO> memberList =
		  sqlSession.selectList(namespace+".memberList");
		
		System.out.println("DAO : SQL 실행완료 ");
		System.out.println("DAO : "+memberList);
		
		return memberList;
	}
	
	
	
	
	

}
