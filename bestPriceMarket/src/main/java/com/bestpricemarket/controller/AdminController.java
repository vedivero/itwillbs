package com.bestpricemarket.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.service.AdminService;

@Controller
@RequestMapping(value = "/admin/*")
public class AdminController {

	@Inject
	private AdminService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	
	//관리자 페이지 첫화면
	@RequestMapping(value = "/adminPage", method=RequestMethod.GET)
	public String indexGET(HttpSession session)throws Exception{
		logger.info("get index");
		return "/admin/index";
	}
	
	//회원 리스트 페이지
	@RequestMapping(value ="/memberList" ,method=RequestMethod.GET)
	public void MemberListGET(Model model, HttpSession session,@RequestParam("num")int num)throws Exception {
		
		int count = service.allMembersCount();
		System.out.println("count"+count);

		int postNum = 10;
		System.out.println("postNum"+postNum);

		int pageNum = (int)Math.ceil((double)count/postNum);
		System.out.println("pageNum"+pageNum);

		int displayPost = (num - 1) * postNum;
		System.out.println("displayPost"+displayPost);

		int pageNum_cnt = 5;
		System.out.println("pageNum_cnt"+pageNum_cnt);

		int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);
		System.out.println("endPageNum:"+endPageNum);

		int startPageNum = endPageNum - (pageNum_cnt - 1);
		System.out.println("startPageNum:"+startPageNum);

		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) postNum));
		System.out.println("endPageNum_tmp:"+endPageNum_tmp);
		
		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		System.out.println("prev:"+prev);
		
		boolean next = endPageNum * postNum >= count ? false : true;
		System.out.println("next:"+next);
		List<MemberVO> MemberList =null;
		 
		 MemberList = service.getMemberList(displayPost, postNum);
		 System.out.println("Controller에서 → service에 있는 getMemberList() 호출 완료");
		 model.addAttribute("MemberList", MemberList);   
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

	
	//특정 회원 정보 상세보기
	@RequestMapping(value = "/memberDetail", method=RequestMethod.GET)
	public void detailGET(@RequestParam("id")String id,Model model,HttpSession session)throws Exception{
		
		MemberVO vo = service.detail(id);
		System.out.println("vo:"+vo);
		model.addAttribute("vo", vo);
	}
	
	//계정 제재하기
	@RequestMapping(value ="/restriction",method=RequestMethod.GET)
	public String accountRestrictionGET(MemberVO vo,HttpSession session)throws Exception {
		
		service.changeRestriction(vo);
		
		return "redirect:/admin/memberDetail?id="+vo.getId();
	}
	
	//계정 제한 해제하기
	@RequestMapping(value = "/liftingRestriction", method=RequestMethod.GET)
	public String liftingRestrictionGET(MemberVO vo,HttpSession session)throws Exception{
		
		service.liftingRestriction(vo);
		return "redirect:/admin/memberDetail?id="+vo.getId();
		
	}

	//계정 제한 사유입력
	@RequestMapping(value = "/restrinctionReason", method=RequestMethod.GET)
	public String restrinctionReason(MemberVO vo,HttpSession session)throws Exception{
		
		service.restrinctionReason(vo);
		
		return "redirect:/admin/memberDetail?id="+vo.getId();
		
	}
	
	//제재되지 않은 일반회원 목록
	@RequestMapping(value = "/generalMemberList", method = RequestMethod.GET)
	public void generalMemberListGET(Model model,@RequestParam("num")int num,HttpSession session)throws Exception{

		System.out.println("         @@@@@@@@ 일반 회원 @@@@@@@@@        ");
		
		int count = service.generalMemberCount();
		System.out.println("general count"+count);

		int postNum = 10;
		System.out.println("postNum"+postNum);

		int pageNum = (int)Math.ceil((double)count/postNum);
		System.out.println("pageNum"+pageNum);

		int displayPost = (num - 1) * postNum;
		System.out.println("displayPost"+displayPost);

		int pageNum_cnt = 5;
		System.out.println("pageNum_cnt"+pageNum_cnt);

		int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);
		System.out.println("endPageNum:"+endPageNum);

		int startPageNum = endPageNum - (pageNum_cnt - 1);
		System.out.println("startPageNum:"+startPageNum);

		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) postNum));
		System.out.println("endPageNum_tmp:"+endPageNum_tmp);
		
		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		System.out.println("prev:"+prev);
		
		boolean next = endPageNum * postNum >= count ? false : true;
		System.out.println("next:"+next);
		
		List<MemberVO> generalMemberList =null;
		generalMemberList = service.getGeneralMemberList(displayPost, postNum);
		 System.out.println("Controller에서 → service에 있는 getGenralMemberList() 호출 완료");
		
		 model.addAttribute("generalMemberList", generalMemberList);   
		 model.addAttribute("pageNum", pageNum);
		 
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		// 이전 및 다음
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		System.out.println("            @@@@@@@@ 일반 회원 @@@@@@@@@          ");
		// 현재 페이지
		model.addAttribute("select", num);
		}

	
	
	//제재중인 계정 리스트
	@RequestMapping(value = "/restrictionMemberList", method = RequestMethod.GET)
	public void restrictionMemberListGET(Model model,@RequestParam("num")int num,HttpSession session)throws Exception{

		System.out.println("        @@@@@@ 제재중인 회원 @@@@@@@@@        ");
		
		int count = service.restrictionMemberCount();
		System.out.println("restricton count :"+count);

		int postNum = 10;
		System.out.println("postNum :"+postNum);

		int pageNum = (int)Math.ceil((double)count/postNum);
		System.out.println("pageNum :"+pageNum);

		int displayPost = (num - 1) * postNum;
		System.out.println("displayPost :"+displayPost);

		int pageNum_cnt = 5;
		System.out.println("pageNum_cnt :"+pageNum_cnt);

		int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);
		System.out.println("endPageNum :"+endPageNum);

		int startPageNum = endPageNum - (pageNum_cnt - 1);
		System.out.println("startPageNum :"+startPageNum);

		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) postNum));
		System.out.println("endPageNum_tmp :"+endPageNum_tmp);
		
		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * postNum >= count ? false : true;
		
		List<MemberVO> restrictionMemberList =null;
		restrictionMemberList = service.getRestrictionMemberList(displayPost, postNum);
		 System.out.println("Controller에서 → service에 있는 getRestrictionMemberList() 호출 완료");
		
		 model.addAttribute("restrictionMemberList", restrictionMemberList);   
		 model.addAttribute("pageNum :", pageNum);
		 
		// 시작 및 끝 번호
		model.addAttribute("startPageNum :", startPageNum);
		model.addAttribute("endPageNum :", endPageNum);

		// 이전 및 다음
		model.addAttribute("prev :", prev);
		model.addAttribute("next :", next);

		System.out.println("           @@@@@@ 제재중인 회원 @@@@@@@@@          ");
		// 현재 페이지
		model.addAttribute("select :", num);
		}
	
	
	//모든 경매리스트 출력하는 페이지
	@RequestMapping(value = "/goodsList", method=RequestMethod.GET)
	public void goodsListGET(Model model,@RequestParam("num")int num,HttpSession session)throws Exception {
		
		
		int count = service.allGoodsCount();

		int postNum = 10;

		int pageNum = (int) Math.ceil((double) count / postNum);

		int displayPost = (num - 1) * postNum;
		System.out.println("displayPost:"+displayPost);

		int pageNum_cnt = 5;

		int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);

		int startPageNum = endPageNum - (pageNum_cnt - 1);

		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) postNum));

		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * postNum >= count ? false : true;

		List<GoodsVO> goodsList = null;
		goodsList = service.getGoodsList(displayPost, postNum);
		model.addAttribute("goodsList", goodsList);
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
	
	//특정 상품 상세보기
	@RequestMapping(value = "/goodsDetail", method=RequestMethod.GET)
	public void detailGET(@RequestParam("gno")int gno,Model model,HttpSession session)throws Exception{
			
			GoodsVO vo = service.goodsDetail(gno);
			System.out.println("vo:"+vo);
			model.addAttribute("vo", vo);
	}
	
	@RequestMapping(value = "/underwayAuctionList",method = RequestMethod.GET)
	public void underwayAuction(Model model,@RequestParam("num")int num,HttpSession session) throws Exception {
		
		int count = service.underwayAuctionCount();
		
		int postNum = 10;

		int pageNum = (int) Math.ceil((double) count / postNum);

		int displayPost = (num - 1) * postNum;

		int pageNum_cnt = 5;

		int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);

		int startPageNum = endPageNum - (pageNum_cnt - 1);

		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) postNum));

		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * postNum >= count ? false : true;

		List<GoodsVO> underwayAuctionList = null;
		underwayAuctionList = service.getUnderwayAuctionList(displayPost, postNum);
		System.out.println("underwayAuctionList : "+ underwayAuctionList);
		
		model.addAttribute("underwayAuctionList", underwayAuctionList);
		model.addAttribute("pageNum", pageNum);

		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		model.addAttribute("select", num);
		
	}
	
	//상품 삭제하기
	@RequestMapping(value = "/delete" ,method = RequestMethod.GET)
	public String deleteGoods(@RequestParam("gno")int gno)throws Exception{

			service.delete(gno);
		
		return "redirect:/admin/goodsList?num=1";
	}
	
	//종료된 경매 내역
	@RequestMapping(value = "/closedAuctionList", method = RequestMethod.GET)
	public void closedAuctionList(@RequestParam("num")int num,Model model,HttpSession session)throws Exception{
		int count = service.closedAuctionCount();
		
		int postNum = 10;

		int pageNum = (int) Math.ceil((double) count / postNum);

		int displayPost = (num - 1) * postNum;

		int pageNum_cnt = 5;

		int endPageNum = (int) (Math.ceil((double) num / (double) pageNum_cnt) * pageNum_cnt);

		int startPageNum = endPageNum - (pageNum_cnt - 1);

		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) postNum));

		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * postNum >= count ? false : true;

		// 글 목록 가져오기
		List<GoodsVO> closedAuctionList = null;
		closedAuctionList = service.getClosedAuctionList(displayPost, postNum);
		System.out.println("closedAuctionList : "+ closedAuctionList);
		
		model.addAttribute("closedAuctionList", closedAuctionList);
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
	
}
