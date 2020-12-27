package com.bestpricemarket.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.domain.MyActionVO;
import com.bestpricemarket.domain.PaymentVO;
import com.bestpricemarket.service.PaymentService;

@Controller
@RequestMapping(value = "/pay/*")
public class PaymentController {

	@Inject
	private PaymentService service;

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	// http://localhost:8088/pay/payment
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String detailPayment(Model model, HttpSession session,@RequestParam("gno")int gno) throws Exception {

		String id = (String) session.getAttribute("id");
		System.out.println("손님아이디 : " + id);
		
		MemberVO vo = service.getMember(id);
		logger.info("객체 : " + vo);

		// 현재 로그인되어있는 ID에 해당하는 정보를 객체저장
		model.addAttribute("memVO", vo);
		// 내경매에서 해당하는 상품번호 물건을 결제하기 눌렀을때 출력되는 상품정보 객체저장
		// 추후 사진추가예정(지금은 사진x)
		model.addAttribute("gvo", service.getGoods(gno));

		// 결제 유효성검사(DB에 중복된값 체크)
		PaymentVO result = service.getpayment(gno);
		model.addAttribute("result", result);

		return "/pay/paymentDetail";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	@ResponseBody
	public void kakao(HttpServletRequest req, /* RedirectAttributes rttr */Model model,
			/* @ModelAttribute */PaymentVO pvo) throws Exception {
		PaymentVO vo2 = service.getpayment(pvo.getP_g_gno());
		System.out.println("pvo : " + pvo);
		System.out.println("vo2 : " + vo2);

		// DB에 gno에 해당하는 물품이 없으면 결제정보 저장
		if (vo2 == null) {
			service.insertParam(pvo);
		}
	}

	// http://localhost:8088/pay/order
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String orderCompleted(int gno, Model model) throws Exception {
		PaymentVO pvo = service.getpayment(gno);
		model.addAttribute("pay", pvo);
		MyActionVO myVO = service.getMyAction(pvo.getP_g_gno());
		if(pvo.getP_g_gno() == myVO.getA_g_gno()) {
			service.updateMyAction(myVO);
		}
		return "/pay/orderCompleted";
	}

}