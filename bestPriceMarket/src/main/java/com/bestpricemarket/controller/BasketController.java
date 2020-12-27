package com.bestpricemarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bestpricemarket.domain.BasketPager;
import com.bestpricemarket.domain.BasketVO;
import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.LikesVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.domain.MyActionVO;
import com.bestpricemarket.service.BasketService;
import com.bestpricemarket.service.MemberService;

@Controller
@RequestMapping(value ="/basket/*")
public class BasketController {

	@Inject
	private BasketService service;
	

	
	private static final Logger l = LoggerFactory.getLogger(BasketController.class);
	
	 // 관심상품 넣기
	 @RequestMapping(value="/insert") 
	 public String insert(@ModelAttribute BasketVO bv, HttpSession session){ 
		 l.info("C: vo 확인"+bv);
	 return "/basket/listPage"; }
	
	 // 관심상품 리스트
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public  String listGET(HttpSession session,Model model,int l_g_gno) throws Exception{
    	String id = (String)session.getAttribute("id");
    	
    	if(id == null) {
    		
    		return "/member/loginandjoin";
    	}
    	
    	BasketVO basketlist = null;
    	basketlist = service.Basketlist(l_g_gno);
    	
    	return"/basket/basket";
    } 

    //관심상품 삭제
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String deletePOST(@RequestParam(value="l_g_gno")int l_g_gno,HttpSession session,Model model) throws Exception{
    	
    	service.updateGlike(l_g_gno);
    	service.deleteBasket(l_g_gno);
       
    	System.out.println("C: 삭제 성공");
    	return "redirect:/basket/listPage";
    }

      // 관심상품 페이징+리스트 출력	
	  @RequestMapping(value="/listPage", method = RequestMethod.GET) 
	  public String getListPage(Model model, @RequestParam(value="num",defaultValue="1") int num,HttpSession session) throws Exception{ 
		
		// num = 페이지 번호
			String l_m_id = (String)session.getAttribute("id");
	
			// 등록된 글의 총 갯수
			int count = service.getCount(l_m_id);

			// 페이지당 출력할 글의 갯수
			int postNum = 5;

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
			int endPageNum_tmp = (int) (Math.ceil((double) count / (double) pageNum_cnt));

			if (endPageNum > endPageNum_tmp) {
				endPageNum = endPageNum_tmp;
			}

			boolean prev = startPageNum == 1 ? false : true;
			boolean next = endPageNum * pageNum_cnt >= count ? false : true;

			// 글 목록 가져오기
			List<BasketVO> basketlist = null;
			basketlist = service.listPage(displayPost, postNum, l_m_id);
			model.addAttribute("basketlist", basketlist);
			model.addAttribute("pageNum", pageNum);

			// 시작 및 끝 번호
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);

			// 이전 및 다음
			model.addAttribute("prev", prev);
			model.addAttribute("next", next);

			// 현재 페이지
			model.addAttribute("select", num);
	  
			 return "/basket/listPage"; 
	  }

}
	 


