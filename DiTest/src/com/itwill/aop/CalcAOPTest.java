package com.itwill.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcAOPTest {

	public static void main(String[] args) {
		
		System.out.println(" 계산기 실행 ! ");
		
		// AOPTest.xml -> 생성된 객체정보를 가져다 사용
		
		ApplicationContext appCtx
		    = new ClassPathXmlApplicationContext("AOPTest.xml");
		
		Calculator cal2 
	    = (Calculator) appCtx.getBean("calcTarget");
		
		cal2.add(200, 200);
		
		System.out.println("-----------------------------");
		
		Calculator cal 
		    = (Calculator) appCtx.getBean("proxyCalc");
		
		cal.add(100, 200);
		
		System.out.println("-------------------------");
		
		cal.div(100, 20);
		
		
		
		

	}

}
