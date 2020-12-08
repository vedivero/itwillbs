package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;

@RestController
@RequestMapping(value = "/boards")
public class BoardRestController {
	
	public static final Logger logger =
			LoggerFactory.getLogger(BoardRestController.class);
	
	// 특정 정보 조회(GET)
	// http://localhost:8088/boards/11
	@RequestMapping(
			value = "/{bno}",
			method = RequestMethod.GET   
			)
	public ResponseEntity<BoardVO> findBoard(@PathVariable("bno") int bno){
		
		logger.info("findBoard() 호출");
		
		logger.info("주소에서 제공되는 정보를 서비스로 전달");
		// 서비스 메서드(bno)
		BoardVO vo = new BoardVO();
		vo.setBno(bno);
		vo.setWriter("admin");
		vo.setTitle("TEST1");
		vo.setContent("testContent1");
		
		return new ResponseEntity<BoardVO>(vo,HttpStatus.OK); 
	}
	
	// (실행주소) -> http://localhost:8088/boardtest
	// http://localhost:8088/boards  (POST)
	// 신규 글쓰기(/boards)-POST방식
	@RequestMapping(value = "",
			      method= RequestMethod.POST)
	public ResponseEntity<String> addBoard(@RequestBody BoardVO vo){
		// @RequestBody : model 객체 처럼 사용(REST-JSON타입)
		logger.info("addBoard() 호출 : 글등록 하기");
		logger.info("vo ->"+vo);
		
		ResponseEntity<String> resEntity = null;
		
		try {
			logger.info("정보 전달 받아서 처리(서비스)");
			resEntity 
			  = new ResponseEntity<String>("글쓰기 성공",HttpStatus.OK);
		}catch (Exception e) {
			resEntity 
			 = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	
	// 글정보 수정 (/boards/번호) (PUT,PATCH)
	// http://localhost:8088/boards/{bno}
	@RequestMapping(
			value = "/{bno}",
			method = RequestMethod.PUT
			)
	public ResponseEntity<String> modBoard(@PathVariable("bno") int bno
			                             ,@RequestBody BoardVO updateVO){
		ResponseEntity<String> resEntity = null;
		
		try {
			logger.info("modBoard() 호출 ");
			logger.info("bno ->"+bno+", updateBoard ->"+updateVO);
			
			logger.info("서비스() 수정 처리");
			resEntity = new ResponseEntity<String>("수정성공",HttpStatus.OK);
		}catch (Exception e) {
			resEntity = new ResponseEntity<String>("수정실패",HttpStatus.BAD_REQUEST);
		}
		
		
		return resEntity;		
	}
	
	// 글정보 삭제(/boards/번호) (DELETE)
	@RequestMapping(value = "/{bno}",method = RequestMethod.DELETE)
	public ResponseEntity<String> delBoard(@PathVariable("bno") int bno){
		ResponseEntity<String> resEntity = null;
		
		try {
			logger.info("delBoard() 호출");
			logger.info("서비스 삭제 동작() "+bno);
			resEntity = new ResponseEntity<String>("delOK",HttpStatus.OK);
		}catch (Exception e) {
			resEntity = new ResponseEntity<String>("delNO",HttpStatus.BAD_REQUEST);
		}
		
		return resEntity;
	}
	
	
	
	
	
	
	
	
	
	

}
