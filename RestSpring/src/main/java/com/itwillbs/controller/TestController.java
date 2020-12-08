package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.SampleVO;

// 스프링 4.x.x 이상부터 지원
//@Controller
@RestController
@RequestMapping(value = "/test/*")
public class TestController {

	// @ResponseBody : 스프링 3버전 ~
	// - 메서드/리턴타입에 사용가능. 스프링의 MessageConverter에 의해서 처리
	// 처리되는 정보가 브라우저로 전달
	// @RestController : 스프링 4버전 ~
	// -> jsp 뷰를 찾아가는게 아니라, 데이터를 생성하는것(REST방식)

	// * RestController안에 메서드는 @ResponseBody 없어도 동일하게
	// 처리됨 ( @ResponseBody 생략됨)

	// http://localhost:8088/test/hello
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		// 문자열타입의 데이터를 리턴/생성 목적
		// return "Hello ITWILL!!";
		// return "1234123123";
		return "안녕하세요 REST"; // (한글확인 불가-인코딩 오류)
	}

	// http://localhost:8088/test/sendVO
	@RequestMapping(value = "/sendVO")
	public SampleVO sendVO() {
		// 객체 생성
		SampleVO vo = new SampleVO();
		vo.setNum(1);
		vo.setName("홍길동");
		vo.setTel("010-1234-1234");

		return vo;
	}

	// 컬렉션 객체 활용
	// List
	// http://localhost:8088/test/sendList
	@RequestMapping(value = "/sendList")
	public List<SampleVO> sendList() {

		List<SampleVO> voList = new ArrayList<SampleVO>();

		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setNum(i);
			vo.setName("홍길동");
			vo.setTel("010-12" + i + "4-12" + i + "4");

			voList.add(vo);
		}
		return voList;
	}

	// Map
	// http://localhost:8088/test/sendMap
	@RequestMapping(value = "/sendMap")
	public Map<Integer, SampleVO> sendMap() {

		Map<Integer, SampleVO> map = new HashMap<Integer, SampleVO>();

		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setNum(i);
			vo.setName("홍길동");
			vo.setTel("010-12" + i + "4-12" + i + "4");

			map.put(i, vo);
		}
		return map;
	}

	// http://localhost:8088/test/board
	// http://localhost:8088/test/board?bno=123
	// http://localhost:8088/test/board/123
	@RequestMapping(value = "/board/{num}")
	public SampleVO board(@PathVariable("num") int num) {
		// 요청 URL에 있는 매개변수를 가져오기
		// 주소 {num}의 값이 저장해서 사용가능
		System.out.println(num);

		SampleVO vo = new SampleVO();
		vo.setNum(num);
		vo.setName("사용자");
		vo.setTel("010-9999-8888");

		// return num;
		// return 0;
		return vo;
	}

	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public void checkVO(@RequestBody SampleVO vo) {
		// @RequestBody : JSON 형태로 전달된 데이터를
		// 해당타입의 객체에 자동으로 설정

		System.out.println("checkVO() 호출");
		System.out.println("전달받은 정보 출력");
		System.out.println(vo);

	}

	// http://localhost:8088/test/sendErrorAuth
	@RequestMapping(value = "/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth() {
		System.out.println("sendListAuth() 호출!!");
//		System.out.println("HTTP 상태 리턴 "+HttpStatus.OK);
		// System.out.println("HTTP 상태 리턴 "+HttpStatus.BAD_REQUEST);
		System.out.println("HTTP 상태 리턴 " + HttpStatus.INTERNAL_SERVER_ERROR);
		System.out.println("HTTP 상태 리턴 " + HttpStatus.NOT_FOUND);

//		return new ResponseEntity<Void>(HttpStatus.OK);
		// return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		// return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	
	// http://localhost:8088/test/sendListError
	@RequestMapping(value = "/sendListError")
	public ResponseEntity<List<SampleVO>> sendListError() {
		System.out.println("sendListError() 호출 ");
		// list 생성
		List<SampleVO> voList = new ArrayList<SampleVO>();

		for (int i = 0; i < 10; i++) {
			SampleVO vo = new SampleVO();
			vo.setNum(i);
			vo.setName("홍길동");
			vo.setTel("010-12" + i + "4-12" + i + "4");

			voList.add(vo);
		}

		return new ResponseEntity<List<SampleVO>>(voList,HttpStatus.NOT_FOUND);
	}
	
	
	// * 단순하게 객체 정보를 전달 가능(JSON),
	//  HTML 정보도 전달가능, JavaScript 정보도 전달 가능
	// => 결과 확인, 오류메세지 체크 
	
	// http://localhost:8088/test/res2
	@RequestMapping("/res2")
    public ResponseEntity res2() {
    	System.out.println("res2() 호출");
    	
    	HttpHeaders responseHeaders = new HttpHeaders();
    	responseHeaders.add("Content-type", "text/html; charset=UTF-8");
    	
    	String msg ="<script>";
    	msg += " alert('결과 확인!'); ";
    	msg += " location.href='/res1'; ";
    	msg += "</script>";
    	
    	return new ResponseEntity(msg,responseHeaders,HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
	
	
	

}
