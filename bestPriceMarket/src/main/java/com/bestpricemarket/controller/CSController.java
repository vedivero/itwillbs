package com.bestpricemarket.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bestpricemarket.domain.CSVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.persistence.MemberDAOImpl;
import com.bestpricemarket.service.CSService;

@Controller
@RequestMapping("/CS/*")
public class CSController {

	private static final Logger logger = LoggerFactory.getLogger(CSController.class);

	@Inject
	private CSService service;

	// 글 등록
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGET(HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");

		if (id == null) {
			return "redirect:/member/login";
		} else {
			return "/CS/register";
		}
	}

	// http://localhost:8088/CS/register
	// 글 등록
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(CSVO vo, RedirectAttributes rttr) throws Exception {
		// System.out.println("C:글쓰기 처리 완료+" + vo);

		service.regist(vo);
		// System.out.println("S: DB에가서 data저장 완료");

		rttr.addFlashAttribute("result", "content");
		return "redirect:/CS/CSBoardListPage?num=1";

	}

//	//글 목록
//	@RequestMapping(value = "/CSBoardList", method = RequestMethod.GET)
//	public void CSBoardListGET(@ModelAttribute("result") String result, Model model) throws Exception {
//
//		List<CSVO> CSList = service.listAll();
//
//		model.addAttribute("CSList", CSList);
//	}

	// 글 상세내용 보기
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public void contentGET(@RequestParam("csbno") int csbno, Model model ,HttpSession session) throws Exception {

		CSVO vo = service.content(csbno);
			
		model.addAttribute("vo", vo);
	}

	// 글 수정하기
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("csbno") int csbno, Model model) throws Exception {

		CSVO vo = service.content(csbno);
		model.addAttribute("vo", vo);
	}

	// 글 수정
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(CSVO vo) throws Exception {
		// System.out.println("수정하기 호출");
		service.modify(vo);
		// System.out.println("서비스의 modify호출");
		return "redirect:/CS/content?csbno=" + vo.getCsbno();

	}

	// 글 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGET(@RequestParam("csbno") int csbno) throws Exception {

		service.delete(csbno);

		return "redirect:/CS/CSBoardListPage?num=1";
	}

	// 글 목록 + 페이징처리
	@RequestMapping(value = "/CSBoardListPage", method = RequestMethod.GET)
	public void CSBoardListPageGET(Model model, @RequestParam("num") int num,HttpSession session) throws Exception {
		// num = 페이지 번호

		// 등록된 글의 총 갯수
		int count = service.count();

		// 페이지당 출력할 글의 갯수
		int postNum = 10;

		// 페이지 하단 페이징 번호 (등록된 글의 총 갯수 / 한 페이지에 출력할 갯수) + 소수점은 올림(Math.ceil)
		int pageNum = (int) Math.ceil((double) count / postNum);

		// 출력할 글
		int displayPost = (num - 1) * postNum;

		// 한번에 표시할 페이징 번호의 갯수
		int pageNum_cnt = 5;

		// 표시되는 페이지 번호 중 마지막 번호
		int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);

		// 표시되는 페이지 번호 중 첫번째 번호
		int startPageNum = endPageNum - (pageNum_cnt - 1);

		// 마지막 번호 재계산
		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) postNum));

		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * postNum >= count ? false : true;

		// 글 목록 가져오기
		List<CSVO> CSList = null;
		CSList = service.listPage(displayPost, postNum);
		model.addAttribute("CSList", CSList);
		model.addAttribute("pageNum", pageNum);

		// 시작 및 끝 번호
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		// 이전 및 다음
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		// 현재 페이지
		model.addAttribute("select", num);

	}

	// FAQ
	@RequestMapping(value = "/CSFAQ", method = RequestMethod.GET)
	public String csfaqGET(HttpSession session) throws Exception {
		return "/CS/CSFAQ";
	}

	
    //관리자 답변글 작성페이지로 가기
    @RequestMapping(value = "/replyRegister", method = RequestMethod.GET)
    public void replyGET(@RequestParam("csbno") int csbno,Model model)throws Exception{
       
       CSVO vo = service.content(csbno);
       
       System.out.println(vo);
       
       model.addAttribute("vo", vo);
    }

	// 글 등록
	@RequestMapping(value = "/replyRegister", method = RequestMethod.POST)
	public String replyRegisterPOST(@RequestParam("csbno") int csbno ,CSVO vo, RedirectAttributes rttr) throws Exception {

		CSVO vo2 = service.content(csbno);
		vo2.getCsbno();
		
		service.replyRegist(vo);

		rttr.addFlashAttribute("result", "content");
		
		return "redirect:/CS/CSBoardListPage?num=1";
	}

}