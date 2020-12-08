package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.Criteria;
import com.itwillbs.domain.PageMaker;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping("/board/*")
//@SessionAttributes("boardVO") - "해당 컨트롤러에 정보를 저장"
// -> 정상처리 완료후 메서드를 사용해서 사용완료 표시 
public class BoardController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(BoardController.class);
	
	// 서비스 객체 생성
	@Inject
	private BoardService service;
	
	// http://localhost:8088/board/register
	// 글쓰기(입력 GET)
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public void registerGET() throws Exception{
		logger.info("C : /register -> /register.jsp 이동");
		logger.info("C : registerGET() 호출! ");
		logger.info("C : 정보 입력창으로 이동");
	}
	
	// 글쓰기(처리 POST)
	//@RequestMapping(value = "/register" 
	// ,method = {RequestMethod.POST,RequestMethod.GET})
	// -> method 속성은 배열로 지정 가능(한번에 여러가지 속성값을 사용가능)
	@RequestMapping(value = "/register" ,method = RequestMethod.POST)
	public String registerPOST(BoardVO vo,RedirectAttributes rttr/* ,Model model */) throws Exception{
		logger.info("C : /register (get-submit) -> list 이동 ");
		logger.info("C : registerPOST() 호출! ");
		logger.info("C : 정보 처리 (글쓰기 동작) ");
		
		logger.info("C : 전달받은 정보(글정보 파라미터) "+vo);
		
		logger.info("C : 서비스 글쓰기 기능 호출 ");
	
		service.regist(vo);		
		logger.info("C : 글쓰기 처리 완료!");
		
		// 정상처리 일때-> 다음 페이지로 정보 전달 
		//model.addAttribute("result", "SUCCESS");
		rttr.addFlashAttribute("result", "SUCCESS");		
		
		// 페이지 이동 
		// /board/success.jsp
		// return "/board/success"; (F5 - 중복 글쓰기)
		return "redirect:/board/listAll";
	}
	
	// http://localhost:8088/board/listAll
	// 글 목록 보기 (listAll-GET)
	@RequestMapping(value = "/listAll",method = RequestMethod.GET)
	public void listAllGET(@ModelAttribute("result") String result,Model model) throws Exception{
		logger.info("C : /listAll -> listAll.jsp ");
		logger.info("C : listAllGET() 호출  ");
		
		logger.info("C : 전달정보 -> "+result);
		
		// 서비스 <-> DAO <-> mapper <-> DB
		List<BoardVO> boardList = service.listAll();
		
		logger.info("C : 글 목록 ");
		logger.info("C : "+boardList);
		// 글 정보를 가지고 오기	
		model.addAttribute("boardList", boardList);
		
		// 뷰페이지로 이동
	}
	
	
	// /board/read?bno=${boardVO.bno }
	// 글 상세내용 보기 
	@RequestMapping(value = "/read",method = RequestMethod.GET)
	public void readGET(@RequestParam("bno") int bno,Model model) throws Exception {
		// @RequestParam("bno")
		// => request.getparameter() 의미로 사용
		// => 타입 상관없이 처리 가능(문자열,정수형,날짜) 
		// => 단순 타입(알수있는 타입)은 어노테이션 생략 가능
		//    가능하면 앞에 어노테이션 명시하는것이 좋음
		logger.info("C : /read?bno=00 -> /read.jsp ");
		logger.info("C : readGET() 호출");
		
		logger.info("C : 글번호 -> "+(bno));
		
		// 서비스 객체(해당 글번호 사용 글정보 가지고 오기)
		BoardVO vo = service.read(bno);
		
		logger.info("C : 글 정보 -> "+vo);
		// 뷰페이지 이동
		model.addAttribute("vo", vo);
	}
	
	@RequestMapping(value = "/readPage",method = RequestMethod.GET)
	public void readPageGET(@ModelAttribute("cri") Criteria cri, @RequestParam("bno") int bno,Model model) throws Exception {
		// @RequestParam("bno")
		// => request.getparameter() 의미로 사용
		// => 타입 상관없이 처리 가능(문자열,정수형,날짜) 
		// => 단순 타입(알수있는 타입)은 어노테이션 생략 가능
		//    가능하면 앞에 어노테이션 명시하는것이 좋음
		logger.info("C : /readPage?bno=00 -> /readPage.jsp ");
		logger.info("C : readPageGET() 호출");
		
		logger.info("C : 글번호 -> "+(bno));
		logger.info("C : cri -> "+cri);
		
		// 서비스 객체(해당 글번호 사용 글정보 가지고 오기)
		BoardVO vo = service.read(bno);
		
		logger.info("C : 글 정보 -> "+vo);
		// 뷰페이지 이동
		model.addAttribute("vo", vo);
	}
	
	
	
	// 수정하기 
	// /board/modify?bno=458748
	@RequestMapping(value = "/modify",method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno,Model model) throws Exception{
		logger.info("C : /modify  -> /modify.jsp ");
		logger.info("C : modifyGET() 호출 ");
		
		// 글번호 (파라미터)
		logger.info("C : 글번호 -> "+bno);
		
		// DB이동해서 해당 정보가져오기 -> view 페이지 이동
		model.addAttribute(service.read(bno));
		
	}
	
	@RequestMapping(value = "/modifyPage",method = RequestMethod.GET)
	public void modifyPageGET(Criteria cri, @RequestParam("bno") int bno,Model model) throws Exception{
		logger.info("C : /modifyPage  -> /modifyPage.jsp ");
		logger.info("C : modifyPageGET() 호출 ");
		logger.info("C : @@@ "+cri);
		
		// 글번호 (파라미터)
		logger.info("C : 글번호 -> "+bno);
		
		// DB이동해서 해당 정보가져오기 -> view 페이지 이동
		model.addAttribute(service.read(bno));
		
		model.addAttribute("cri", cri);		
		
	}
	
	
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo,RedirectAttributes rttr) throws Exception{
		logger.info("C : /modify(post) -> /listAll ");
		logger.info("C : modifyPOST() 호출 ");
		
		// 수정할 데이터 저장
		logger.info("C : "+vo);
		// 데이터 수정 (서비스->DAO->DB)
		service.update(vo);
		// 수정완료 데이터 전달 
		rttr.addFlashAttribute("result", "upok");		
		
		// 페이지 이동
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/modifyPage",method = RequestMethod.POST)
	public String modifyPagePOST(Criteria cri ,BoardVO vo,RedirectAttributes rttr) throws Exception{
		logger.info("C : /modifyPage(post) -> /listPage ");
		logger.info("C : modifyPagePOST() 호출 ");
		
		// 수정할 데이터 저장
		logger.info("C : "+vo);
		// 데이터 수정 (서비스->DAO->DB)
		service.update(vo);
		// 수정완료 데이터 전달 
		rttr.addFlashAttribute("result", "upok");
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("pageSize", cri.getPageSize());
		
		// 페이지 이동
		return "redirect:/board/listAll";
	}
	
	
	
	
	
	// 삭제 동작
	@RequestMapping(value = "/remove",method = RequestMethod.POST )
	public String removePOST(@RequestParam("bno") int bno,RedirectAttributes rttr) throws Exception{
		logger.info("C : removePOST() 호출 ");
		logger.info("C : /remove -> /listAll ");
		// 글번호 저장
		logger.info("C : 삭제할 글번호 -> "+bno);
		// 서비스 객체 사용 글 삭제 
		service.remove(bno);
		
        // '삭제정보' 저장해서 이동
		rttr.addFlashAttribute("result", "delok");
		// 리스트 이동 ("삭제 완료" - 출력)
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/removePage",method = RequestMethod.POST )
	public String removePagePOST(Criteria cri, @RequestParam("bno") int bno,RedirectAttributes rttr) throws Exception{
		logger.info("C : removePagePOST() 호출 ");
		logger.info("C : /removePage -> /listPage ");
		// 글번호 저장
		logger.info("C : 삭제할 글번호 -> "+bno);
		// 서비스 객체 사용 글 삭제 
		service.remove(bno);
		
        // '삭제정보' 저장해서 이동
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("pageSize", cri.getPageSize());
		rttr.addFlashAttribute("result", "delok");
		// 리스트 이동 ("삭제 완료" - 출력)
		
		return "redirect:/board/listPage";
	}
	
	
	
	
	
	
	// 글 목록 보기(페이징처리)
	@RequestMapping(value = "/listCri",method = RequestMethod.GET)
	public void listCriGET(Criteria cri,Model model) throws Exception{
		logger.info("C : listCriGET() 호출 ");
		logger.info("C : cri->"+cri);
		
		model.addAttribute("boardList", service.listCri(cri));
	}
	
	// 글 목록 보기 (PageMaker 객체 사용)
	// http://localhost:8088/board/listPage
	@RequestMapping(value = "/listPage",method = RequestMethod.GET)
	public void listPageGET(Criteria cri,Model model) throws Exception{
		
		logger.info("C : cri -> "+cri);
		// 페이징 처리된 목록을 가져오기 
		model.addAttribute("boardList", service.listCri(cri));
	
		// 하단부 페이징처리 
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		// select count(*) from  tbl_board;
		// -> 사용 직접 계산후 입력
		//pm.setTotalCount(1152);
		pm.setTotalCount(service.listTotalCount());
		
		// -> 뷰페이지로 전달 
		model.addAttribute("pm", pm);
		
	}
	
	
	
	
	
	
	

}
