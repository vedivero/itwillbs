package com.itwillbs.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {
	
	// 로그정보 처리 객체 
	private static final Logger logger
	 = LoggerFactory.getLogger(SampleController.class);
	
    // 특정 주소에 따른 처리 -> 메서드
	// * 메서드의 리턴타입이 void인 경우 [주소.jsp] 페이지 호출
	@RequestMapping("doA")
	public void doA() {
		System.out.println("doA() 호출!!! ");
		logger.info("doA() 호출@@@");
	}
	
	// 주소 doB 에 따른 처리동작
	// "메세지 호출"
	@RequestMapping("doB")
	public void doB() {
		logger.info("doB 메서드 호출@@@");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
