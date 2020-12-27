package com.bestpricemarket.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bestpricemarket.domain.GoodsCommentVO;
import com.bestpricemarket.service.GoodsCommentService;
import com.bestpricemarket.service.GoodsService;

@RestController
//@Controller
@RequestMapping("/detail/comment/*")
public class GoodsCommentController {
	@Inject
	private GoodsService gService;
	@Inject
	private GoodsCommentService cmtService;
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	// 댓글 등록
	//http://localhost:8088/goods/detail?gno=1
    @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int commentInsert(@ModelAttribute GoodsCommentVO cmt, RedirectAttributes rttr, HttpSession session) throws Exception {	
		System.out.println("C : commentInsert데이터"+cmt);
		
		cmtService.commentInsert(cmt);
		//c_ref와 c_num 동기화
		cmtService.syncC_ref(cmtService.getCnum());
		rttr.addFlashAttribute("result", cmt);
		
		rttr.addFlashAttribute("cmtVO", cmtService.commentListEach(cmtService.getCnum()));
		System.out.println("C: AI제약조건있는 c_num찾기 "+cmtService.getCnum());
		return cmtService.getCnum();
	}	   
    
	//댓글 수정
	@RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/text; charset=utf8")
	@ResponseBody
	public String updatePost(@ModelAttribute GoodsCommentVO cmt, HttpSession session) throws Exception{
		System.out.println("굿즈커맨트컨트롤러 update 포스트 cmtVO는 "+cmt);
		GoodsCommentVO gcvo = cmtService.commentListEach(cmt.getC_num());
		
		//이미 삭제된 댓글을 중복삭제못하게 제어
		if(gcvo.getIsDeleted() == 1 ) {
			return "이미 삭제된 문의글은 수정할 수 없습니다. 새로고침 후 이용해주세요.";
		}
		
		cmtService.commentUpdate(cmt);
		return "문의글이 성공적으로 수정되었습니다.";
	}
	
	//댓글삭제
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/text; charset=utf8")
	@ResponseBody
	public String deletePost(@ModelAttribute GoodsCommentVO cmt, HttpSession session) throws Exception{
		System.out.println("굿즈커맨트컨트롤러 deletePost 포스트 gno는 "+cmt.getC_g_gno());
		GoodsCommentVO gcvo = cmtService.commentListEach(cmt.getC_num());
		
		//이미 삭제된 댓글을 중복삭제못하게 제어
		if(gcvo.getIsDeleted() == 1 ) {
			return "이미 삭제된 문의글입니다.";
		}
		
		cmtService.commentDelete(cmt);
		return "문의글이 정상적으로 삭제되었습니다.";
	}

	// 대댓글 등록
	//http://localhost:8088/goods/detail?gno=1
    @RequestMapping(value = "/rereply", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int rereplyInsert(@ModelAttribute GoodsCommentVO cmtVO, RedirectAttributes rttr, HttpSession session) throws Exception {	
		System.out.println("C : rereplyInsert데이터"+cmtVO.getC_ref());
		
		cmtService.rereplyInsert(cmtVO);
		rttr.addFlashAttribute("result", cmtVO);
		rttr.addFlashAttribute("cmtVO", cmtService.commentListEach(cmtService.getCnum()));
		return cmtService.getCnum();
	}
	
}