package com.bestpricemarket.controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.service.MemberService;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {

	@Inject
	//@Autowired
	private MemberService service;
	
	private static final Logger l = LoggerFactory.getLogger(MemberController.class);
	
	/* 회원가입 */
	// http://localhost:8088/member/join
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String insertGET() throws Exception {
		return "/member/loginandjoin";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String insertPOST(MemberVO vo) throws Exception{
		l.info("C: 회원가입포스트메서드"+ vo);
		service.joinMember(vo);		
		return "redirect:/member/login";
	}
	
	//회원가입시 아이디중복확인
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public @ResponseBody int idCheck(@RequestParam("id") String id) throws Exception {
		MemberVO ck = service.idCheck(id);
		if(ck != null) return 1;
		else return 0;
	}
	
	/* 로그인 기능 */
	// http://localhost:8088/member/login
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGET() throws Exception{
		return "/member/loginandjoin";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPOST(MemberVO vo, HttpSession session, HttpServletResponse response, RedirectAttributes rttr, Model model) throws Exception{
		MemberVO returnVO = service.loginMember(vo);
		System.out.println("C: 리턴VO결과(서비스에서 예외처리를 진행했으므로 null이 출력되면 코드에 문제있다는 의미) "+returnVO);
		System.out.println("C: mvo-id "+returnVO.getId());
					
		if(returnVO != null) {
			session.setAttribute("id", returnVO.getId());			
			//rttr.addFlashAttribute("mvo", returnVO);
			model.addAttribute("mvo", returnVO);
			response.getWriter().print(true);
		}
	}
	
	/* 로그아웃 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logoutGET(HttpSession session) throws Exception{
		l.info("C: 로그아웃 GET");
		session.invalidate();
		// return "redirect:/member/main"; 얼럿창출력안하고싶을때 사용
	}
	
	/* 메인페이지 */
	// http://localhost:8088/member/main
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainGET(HttpSession session, Model model) throws Exception{
		l.info("C: 메인 출력페이지 GET");
		MemberVO vo = service.readMember((String)session.getAttribute("id"));
		model.addAttribute("memVO", vo);
		return "redirect:/main";
	}
	
	/* 회원정보보기 */
	// http://localhost:8088/member/info
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public void infoGET(HttpSession session, Model model) throws Exception{
		MemberVO vo = service.readMember((String)session.getAttribute("id"));
		model.addAttribute("memVO", vo);
		l.info("C: 회원정보보기 GET의 VO "+ vo);
	}
	
	/* 회원정보 수정(update) */
	// http://localhost:8088/member/update
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGET(HttpSession session,Model model) throws Exception{
		 model.addAttribute("memberVO",service.readMember((String)session.getAttribute("id")));
		return "/member/updateForm";
   }	
	
    //회원정보 수정
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePOST(MemberVO vo,HttpSession session,HttpServletResponse response) throws Exception{        
		 MemberVO mvo = service.loginMember(vo);
		 
		if(mvo != null) { 		
			service.updateMember(vo);
			return "redirect:/member/main";
		}else {
		MemberVO vo2 = service.readMember((String)session.getAttribute("id"));
		vo.setAddr1(vo2.getAddr1());
		vo.setAddr2(vo2.getAddr2());
		vo.setEmail(vo2.getEmail());
		vo.setPhone(vo2.getPhone());
		vo.setUsername(vo2.getUsername());
		vo.setBlock(vo2.getBlock());
		vo.setBlock_r(vo2.getBlock_r());
		vo.setScore(vo2.getScore());
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('비밀번호가 옳바르지않습니다');</script>");
		out.flush();
		return "/member/updateForm";			
		 }			
    }
    
    /* 회원탈퇴 */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGET(MemberVO vo,HttpSession session,Model model) throws Exception{
    		
	    	return "member/deleteForm";
    } 
    
    //회원 탈퇴
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deletePOST(MemberVO vo, HttpSession session,HttpServletResponse response) throws Exception{
    	    MemberVO mvo = service.loginMember(vo); 
    	    if(mvo != null) { 		
    	    		service.deleteMember(vo);
    	    		session.invalidate();
    	    		return "redirect:/member/main";
    	    }		
    	    response.setContentType("text/html; charset=UTF-8");
    	    
    	    PrintWriter out = response.getWriter();
    	     
    	    out.println("<script>alert('비밀번호가 옳바르지않습니다'); </script>");
    	    out.flush();
    	    return "/member/deleteForm";
    }
	
	/* 구글아이디로 로그인 */	
    @ResponseBody
	@RequestMapping(value = "/loginGoogle", method = RequestMethod.POST)
	public String loginGooglePOST(MemberVO vo, HttpSession session, RedirectAttributes rttr, MemberVO mvo) throws Exception{
		MemberVO returnVO = service.loginMemberByGoogle(vo);
		String mvo_ajaxid = mvo.getId(); 
		System.out.println("C: 구글아이디 포스트 db에서 가져온 vo "+ vo);
		System.out.println("C: 구글아이디 포스트 ajax에서 가져온 id "+ mvo_ajaxid);
		
		if(returnVO == null) { //아이디가 DB에 존재하지 않는 경우
			//구글 회원가입
			service.joinMemberByGoogle(vo);	
			
			//구글 로그인
			returnVO = service.loginMemberByGoogle(vo);
			session.setAttribute("id", returnVO.getId());			
			rttr.addFlashAttribute("mvo", returnVO);
		}
		
		if(mvo_ajaxid.equals(returnVO.getId())){ //아이디가 DB에 존재하는 경우
			//구글 로그인
			service.loginMemberByGoogle(vo);
			session.setAttribute("id", returnVO.getId());			
			rttr.addFlashAttribute("mvo", returnVO);
		}else {//아이디가 DB에 존재하지 않는 경우
			//구글 회원가입
			service.joinMemberByGoogle(vo);	
			
			//구글 로그인
			returnVO = service.loginMemberByGoogle(vo);
			session.setAttribute("id", returnVO.getId());			
			rttr.addFlashAttribute("mvo", returnVO);
		}
		
		return "redirect:/member/main";
	}
    
    /* 비밀번호 찾기 */
    @RequestMapping(value = "/findpw", method = RequestMethod.GET)
	public void findPwGET() throws Exception{
	}
 	@RequestMapping(value = "/findpw", method = RequestMethod.POST)
 	public void findPwPOST(@ModelAttribute MemberVO member, HttpServletResponse response) throws Exception{
 		service.findPw(response, member);
 	}
	
 	//pw-change 요청
 	@RequestMapping(value ="/changePw", method = RequestMethod.GET)
 	public String pwChange(Model model,HttpSession session) {
 		model.addAttribute("memberVO",service.readMember((String)session.getAttribute("id")));
 		return "/member/changePw1";
 	}
 	
 	
 	//비밀번호 변경 요청
 	@RequestMapping(value = "/changePw")
 	public String pwChange(MemberVO vo, HttpSession session,Model model,RedirectAttributes rttr,HttpServletResponse response) throws Exception {
 		l.info("비밀번호 변경 요청 발생!!!");
 		MemberVO mvo = service.loginMember(vo);
 		if( mvo == null) {
 			service.modifyPw(vo);
			rttr.addFlashAttribute("vo", vo);
			return "redirect:/member/main";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('기존의 비밀번호와 일치합니다');</script>");
			out.flush();
			return "/member/changePw2";
		}
 	}
 	
 	//비밀번호 확인
 	@RequestMapping(value = "/checkPw")
 	public String checkPw(MemberVO vo,HttpServletResponse response) throws Exception {
 		MemberVO mvo =service.loginMember(vo); 
 		// 비번이 일치 하는 경우
 		if(mvo != null) {
 			
 		return "/member/changePw2";
 	   }else {
 		
 		l.info("일치하지 않음!"+vo);
 		response.setContentType("text/html; charset=UTF-8");
	    
	    PrintWriter out = response.getWriter();
	     
	    out.println("<script>alert('비밀번호가 옳바르지않습니다'); </script>");
	    out.flush();
 		return "/member/changePw1";
 	  }
 	 }
 	
}
