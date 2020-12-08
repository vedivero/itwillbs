package com.itwill.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonTest {

	public static void main(String[] args) {
		// 자바에서 객체 사용하기!
		// 사람 객체 생성 -"홍길동"/20살 초기화 => 정보 확인
		Person hong = new Person();
		hong.setName("홍길동");
		hong.setAge(20);
		
		System.out.println(hong);
		System.out.println(hong.toString());
		
		// 인터페이스를 구현한 서브클래스 사용 (약한결합)
		// IPerson ip = new SubPerson(); (업캐스팅)
		SubPerson sp = new SubPerson();
		//sp.setName("홍길동1");
		sp.setAge(22);
		sp.sayHello();
		
		// 스프링을 사용해서 객체 사용 (xml)
		// 외부의 파일을 읽어와서 처리 
		BeanFactory factory 
		  = new XmlBeanFactory(new FileSystemResource("person.xml"));
		
		// 의존 주입
		IPerson IP = (IPerson)factory.getBean("personBean");
		
		IP.sayHello();
		
		// ["관리자",30] 사람 생성후 정보 출력
		
		// adminBean 객체 주입 
		//SubPerson admin = (SubPerson)factory.getBean("adminBean");
		IPerson admin = (IPerson)factory.getBean("adminBean");
		
		admin.sayHello();
		
		
		
		

	}

}
