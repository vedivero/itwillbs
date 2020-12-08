package com.itwill.member;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class SelectMemberAction implements Action {

	private MemberDAO mdao2;

	public void setMdao2(MemberDAO mdao2) {
		this.mdao2 = mdao2;
		System.out.println("M : xml 파일을 사용한 주입(setter)");
	}

	@Override
	public void execute() throws Exception {
		// Action 인터페이스를 구현 다형성사용
		System.out.println("M : SelectMemberAction_execute() 호출! ");

		// 파라미터,세션 아이디체크
		// DB접근 -> DAO객체 생성 - getMemberList()
		System.out.println("M : MemberDAO 생성");
		// 의존 관계
		// MemberDAO mdao = new MemberDAOImpl();
		// System.out.println("M : 외부 설정파일 추가");
		// BeanFactory fac =
		// new XmlBeanFactory(new FileSystemResource("member.xml"));

		// 의존 주입
		// System.out.println("M : 외부 설정파일을 사용해서 생성된 객체(DAO) 주입");
		// MemberDAO mdao
		// = (MemberDAO) fac.getBean("mdao");

		mdao2.getMemberList();

		System.out.println("M : 정보(LIST) 저장");
		System.out.println("M : 페이지 이동(view)");

	}
}
