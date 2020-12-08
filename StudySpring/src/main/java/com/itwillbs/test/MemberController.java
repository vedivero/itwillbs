package com.itwillbs.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	
	// 서비스 처리 객체를 주입(DI)
	@Inject
	private MemberService service;
	
	private static final Logger logger =
			LoggerFactory.getLogger(MemberController.class);
	
	
	// http://localhost:8088/test/insert (x)
	// http://localhost:8088/test/member/insert(X)
	// http://localhost:8088/member/insert
	// 회원 가입 처리동작
	@RequestMapping(value = "/insert",method = RequestMethod.GET)
	public String insertGET() throws Exception{
		
		logger.info("C: 회원가입 페이지(정보입력)");
		logger.info("C : /member/insert -> /member/insertMember.jsp 페이지 이동");
		
		return "/member/insertMember";
	}
	
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public String insertPOST(MemberVO vo) throws Exception{
		logger.info("C : 회원가입 처리 페이지(정보처리)");
		logger.info("C : /member/insertMember.jsp -> 처리");
		// 0. 한글처리 (web.xml)
		// 1. 전달되는 정보 저장하기(파라미터값)
		logger.info("C : "+vo);
		// 2. 서비스 객체 생성(의존주입)
		// 3. 서비스 회원가입 동작 호출
		service.insertMember(vo);		
		logger.info("C : 회원가입 완료!");
		// 4. 로그인 페이지로 이동(GET)
		//  [ /member/login ]
		
		return "redirect:/member/login";
	}
	
	// http://localhost:8088/member/login
	// 로그인 처리
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String loginGET() throws Exception{
		
		logger.info("C : 로그인 처리 페이지");
		logger.info("C : /member/login -> /member/loginForm.jsp 이동");
		
		return "/member/loginForm";
	}
	
	// http://localhost:8088/member/login (POST)
	// 로그인 처리(POST)
	@RequestMapping(value = "/login",method = RequestMethod.POST )
	public String loginPOST(MemberVO vo,HttpSession session,RedirectAttributes rttr /* ,@ModelAttribute("userid") String id */) throws Exception{
		
		logger.info("C : loginPOST 동작");
		logger.info("C : loginForm.jsp -> /login(POST) ");
		
		// 1. 전달정보를 저장(파라미터값:ID,PW)
		logger.info("C : "+vo);
//		logger.info("C : id ="+id);
		// 2. 서비스 객체 생성 => 주입완료
		// 3. 서비스 로그인 체크 동작
		MemberVO returnVO = service.loginMember(vo);
		logger.info("C : 결과 "+returnVO);
		// 4. 해당 결과에 따라 페이지 이동 제어
		//  - 해당정보가 있을경우 : -> main페이지
		//  -> 5.세션값 생성
		//  - 해당정보가 없을경우 : -> login페이지
		if(returnVO == null) {
			return "redirect:/member/login";
		}
		// 5. 세션값 생성
		session.setAttribute("id", returnVO.getUserid());
		
		// 6. 정보 저장해서 view페이지로 전달
		//model.addAttribute("m", returnVO);
		// * 페이지 리다이렉트 이동시 
		//  ( RedirectAttributes 객체 ) 사용 정보 전달
		rttr.addFlashAttribute("mvo", returnVO);
		//rttr.addAttribute("mvo", returnVO);(x)
		//rttr.addAttribute("mvo", "Hello1234");
		
		//logger.info("C: model-> "+model);
		
		return "redirect:/member/main";
	}
	
	// http://localhost:8088/member/main
	// main 페이지
	@RequestMapping(value = "/main",method = RequestMethod.GET)
    public String mainGET() throws Exception{
		logger.info("C : main페이지 ");
		logger.info("C : /main -> /member/main.jsp");
		
		return "/member/main";
    }
    
	// http://localhost:8088/member/logout
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpSession session) throws Exception{
		logger.info("C : 로그아웃 처리 ");
		// 세션초기화 -> 로그아웃
		session.invalidate();
		
		return "redirect:/member/main";
	}
	
	
	// 회원정보 확인(info) - 조회
	@RequestMapping(value ="/info" ,method = RequestMethod.GET)
	public void infoGET(HttpSession session,Model model) throws Exception{
		
		logger.info(" C: infoGET() 호출 ");
		logger.info(" C: /member/info -> /member/info.jsp (get)");
		
		// 세션 객체 안에 있는 ID 정보 저장
		String id = (String)session.getAttribute("id");
		logger.info("C : ID 저장 완료"+id);
		// 서비스 - 회원정보 가져오는 동작
		logger.info("C : 서비스-readMember() 호출");
		MemberVO vo =
		   service.readMember(id);
		
		logger.info("C: 서비스 처리 완료");
		logger.info("C: 결과 ->"+vo);
		
		// 정보 저장후 페이지 이동
		model.addAttribute("memVO", vo);
		logger.info("C : 모델 객체에 전달할 정보 저장완료");
		logger.info("C : 페이지 이동!! ");
		
	}
	
	// 회원정보 수정(update)
	@RequestMapping(value = "/update",method = RequestMethod.GET)
	public String updateGET(HttpSession session,Model model) throws Exception{
		logger.info("C : updateGET() 호출");
		logger.info("C : /update -> (GET)");
		
		// 해당 회원정보 가져오기 (id)
		// 뷰 페이지로 전달		
		model.addAttribute("memVO", service.readMember((String)session.getAttribute("id")));
		
		return "/member/updateForm";
	}
	
	// 회원정보 수정처리(update)
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String updatePOST(MemberVO vo) throws Exception {
		logger.info("C : updatePOST() ");
		logger.info("C : /updateForm.jsp -> /main");
		
		// 1. 전달된 파라미터값 저장
		logger.info("C : 수정할 정보 -> "+vo);
		// 2. 정보를 가지고 수정하기위해 이동(서비스 객체)
		// 3. 서비스 -> DAO 호출 -> Mapper 호출
		service.updateMember(vo);
		logger.info("C : 정보 수정완료! ");
		// 4. 정보수정 완료 후 메인페이지 이동		
		
		return "redirect:/member/main";
	}
	
	
	// 회원 탈퇴(get)
	// http://localhost:8088/member/delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGET(HttpSession session) throws Exception {
		
		logger.info("/member/delete -> /member/deleteForm.jsp");
		
		// 세션제어
		String id = (String) session.getAttribute("id");
		if(id == null) {
			return "redirect:/member/main";
		}
		
		return "/member/deleteForm";
	}
	
	// 회원 탈퇴처리 (POST)
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deletePOST(MemberVO vo,HttpSession session) throws Exception{
		logger.info("C : /member/delete(post) -> /member/main ");
		
		// 1. 파라미터값 저장
		logger.info("C : 전달정보 -> "+vo);
		// 2. 전달받은 정보를 가지고 삭제 동작 처리이동
		// 3. service 객체 - 동작
		service.deleteMember(vo);
		
		logger.info("C : 서비스 동작 처리완료 ");
		// 4. 세션초기화
		session.invalidate();
		// 5. 페이지 이동
		
		return "redirect:/member/main";
	}
	
	
	// 회원 정보 리스트(GET)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listGET(HttpSession session,Model model) throws Exception{
		// 관리자만 사용기능
		logger.info("C : /member/list -> /member/memberlist.jsp");
		// 1. 관리자 세션 제어
		String id = (String) session.getAttribute("id");
		if(id == null || !(id.equals("admin"))) {
			logger.info("C : 관리자 아닌 접근 ID:"+id);
			return "redirect:/member/main";
		}
		// 2. 서비스 - 회원 목록 가져오는 동작
		List<MemberVO> memberList 
		     = service.listMember();
		logger.info("C : "+memberList);
		
		// 3. 정보 저장 -> 뷰(/member/memberlist.jsp)
		//  (Model 객체 )
		model.addAttribute("memberList",memberList);
		
		return "/member/memberlist";
	}
	
	
	

}
