package com.itwill.member;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class MemberControllerTest {

	public static void main(String[] args) {
		// 컨트롤러 역활
		// SelectMemberAction 객체 생성 -> 동작 실행
		System.out.println("C : 컨트롤러 실행->주소 매핑");
		// 의존관계
		//Action action = new SelectMemberAction();
		
		// 의존 주입 (DI)
		System.out.println("C : 외부설정파일을 읽어오기-spring");
		BeanFactory fac = 
				new XmlBeanFactory(new FileSystemResource("member.xml"));
		

		System.out.println("C : 외부 설정파일을 사용해서 생성된 객체 주입(DI)");
		//SelectMemberAction action (o)
		Action action // (o)
		= (SelectMemberAction) fac.getBean("action");
		
		try {
			action.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("C : 페이지 이동(Redirect/forward)");

	}

}
