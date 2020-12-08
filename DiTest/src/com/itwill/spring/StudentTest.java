package com.itwill.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class StudentTest {

	public static void main(String[] args) {
		// 학생-아이티윌학생 객체 (약한결합)
		// Student(I) - ItwillStudent(C)
		// student.xml(스프링 역활)
		
		// "이름:홍길동,강의장:7" 정보 확인
		// setter 사용 객체 주입
		//////////////////////////////////////////
		// 자바 - 객체 구현/사용
		//Student Kim = new ITwillStudent(); (x)
		ITwillStudent Kim = new ITwillStudent();
		Kim.setName("김학생");
		Kim.setClassNum(7);
		Kim.hello();
		System.out.println("--------------------------------");
		
		// Spring 사용 - 객체 주입(DI) 
		
		// 외부 파일을 읽어오기
		BeanFactory fac 
		  = new XmlBeanFactory(new FileSystemResource("student.xml"));
		
		// 외부 정보를 사용해서 생성된 객체를 주입
		ITwillStudent user 
		   = (ITwillStudent)fac.getBean("itwill");
		Student user1 
		   = (Student) fac.getBean("itwill");
		
		user.hello();
		user1.hello();
		System.out.println("----------------------------");
		System.out.println(" 생성자 사용 객체 생성 ");
		// 생성자를 사용한 객체 생성
		ITwillStudent user2 = new ITwillStudent("사용자2");
		user2.hello();
		Student user3 = new ITwillStudent("사용자3", 3);
		user3.hello();
		
		// 생성자를 사용한 주입
		System.out.println("----------------------------");
		System.out.println(" 생성자 사용 객체 주입 - DI");
		
		//student.xml 파일에서 객체 생성 완료
		
		ITwillStudent user4 
		 = (ITwillStudent) fac.getBean("conBean1");
		user4.hello();
		
		// 객체 생성 [ 사용자2(생성자), 1강의장 ] - spring
		// 생성된 객체 주입 (DI)
		
		ITwillStudent user5 
		= (ITwillStudent) fac.getBean("conBean2");
		
		user5.hello();
		
		
		

	}

}
