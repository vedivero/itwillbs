package com.itwill.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// 로깅 클래스 (advice)
public class LoggingAdvice implements MethodInterceptor {
    // 스프링API에서 MethodInterceptor 인터페이스를 구현해서
	// 메서드 실행전/후, 예외발생시 처리할수 있도록 생성	
	
	@Override
	public Object invoke(MethodInvocation inv) throws Throwable {

		//////////// 메서드 호출 전에 수행하는 구문//////////////////
		System.out.println(" 메서드 호출 전  : LoggingAdvice");
		System.out.println(inv.getMethod() +"메서드 호출 전!!! ");
		//////////////////////////////////////////////////////
		
		//////////// 메서드 호출 /////////////////
		 Object obj = inv.proceed();
		///////////////////////////////////////
		
		////////////메서드 호출 후에 수행하는 구문//////////////////
		System.out.println(" 메서드 호출 후  : LoggingAdvice");
		System.out.println(inv.getMethod() +"메서드 호출 후!!! ");
		//////////////////////////////////////////////////////
		
		return obj;
	}
	
	

}
