package com.itwillbs.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		String formattedDate = dateFormat.format(date);
//		model.addAttribute("serverTime", formattedDate );
		// return "home";
		return "JSONtest";
	}
	
	
	// RestController 사용없이 REST로 처리 가능
	// 메서드에 @ResponseBody 사용할경우 
	// http://localhost:8088/res1
	@RequestMapping("/res1")
	@ResponseBody
	public Map<String, Object> res1(){
		System.out.println("res1() 호출! ");
		
		Map<String, Object> map =
				new HashMap<String, Object>();
		
		map.put("id", "itwill");
		map.put("name", "user01");		
		
		return map;
	}
	
	// http://localhost:8088/boardtest
	@RequestMapping(value = "/boardtest",
			        method = RequestMethod.GET)
	public String home2() {
		return "JSONtest2";
	}
	
	
	
	

}
