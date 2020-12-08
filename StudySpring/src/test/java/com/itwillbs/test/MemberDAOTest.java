package com.itwillbs.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class MemberDAOTest {
	// 스프링을 사용해서 DAO 테스트 (컨트롤러,뷰)

	// DB처리객체 생성 MemberDAO
	// 의존 주입: root-context.xml->persistence패키지 연결
	// ->@Repository (해당객체를 DAO객체로 지정) (업캐스팅)
	@Inject
	private MemberDAO mdao;

	@Test
	public void testDAO() throws Exception {
		// DAO 생성 테스트
		System.out.println("TEST : DAO객체 생성 " + mdao);
	}

	@Test
	public void testGetTime() throws Exception {
		System.out.println("TEST : DAO 객체 주입 완료!!! ");
		System.out.println("TEST : DAO_getTime() 메서드 호출");
		System.out.println("-------------------------------------");
		// 디비의 시간정보 확인
		System.out.println(mdao.getTime());
		System.out.println("-------------------------------------");

	}

	// 회원가입 동작 실행
	// @Test
	public void testMemberInsert() {

		MemberVO vo = new MemberVO("itwill", "1234", "itwill", "itwill@itwillbs.co.kr", null, null);

		mdao.insertMember(vo);
	}

	// Read - 회원정보에 해당하는 값 가져오기
	@Test
	public void readMember() {
		// read동작 처리
		try {
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
			System.out.println(mdao.readMember("admin"));
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readMemberIDPW() {
		try {
			System.out.println(mdao.readMemberWithIDPW("admin", "5555"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
