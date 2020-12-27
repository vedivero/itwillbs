package com.bestpricemarket.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bestpricemarket.domain.Criteria;
import com.bestpricemarket.domain.GoodsCommentVO;
import com.bestpricemarket.domain.GoodsVO;
import com.bestpricemarket.domain.LikesVO;
import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.domain.MyActionVO;
import com.bestpricemarket.domain.PageMaker;
import com.bestpricemarket.domain.PricemonitoringVO;
import com.bestpricemarket.domain.ReportVO;
import com.bestpricemarket.domain.finalBidVO;
import com.bestpricemarket.service.GoodsCommentService;
import com.bestpricemarket.service.GoodsService;
import com.bestpricemarket.service.MyActionService;

@Controller
@RequestMapping(value = "/goods/*")
public class GoodsController {

	private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

	// 서비스 의존 주입
	@Inject
	private GoodsService service;
	@Inject
	private GoodsCommentService cmtService;
    @Inject
    private MyActionService myService;
	// 재원 신고하기
	@Autowired
	private JavaMailSender mailSender;

// 지은 ***************************************************************************************************************************
	// 상품등록
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String goodsRegisterGET(Model model, HttpSession session, Criteria cri, @ModelAttribute("category") String category) throws Exception {

		// id 세션값
		model.addAttribute("id", (String) session.getAttribute("id"));
		model.addAttribute("category", category);
		model.addAttribute("cri", cri);

		return "/goods/goodsRegister";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String goodsRegisterPOST(GoodsVO vo, MultipartHttpServletRequest mpRequest, Criteria cri, @ModelAttribute("category") String category) throws Exception {

		// 상품 등록 서비스
		service.goodsRegister(vo, mpRequest);
		return "redirect:/goods/list";
	}

	// 상품목록 + 카테고리별 목록 + 페이징 처리
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String goodsListGET(Model model, HttpSession session, @ModelAttribute("cri") Criteria cri, String category) throws Exception {
		// id 세션값
		model.addAttribute("id", (String) session.getAttribute("id"));

		// 페이징 처리된 카테고리별 목록
		model.addAttribute("categoryList", service.goodsCategoryList(category, cri));

		// 카테고리
		model.addAttribute("category", category);
		
		// 하단부 페이징처리
		PageMaker pm = new PageMaker(cri);
		// pm.setCri(cri);
		pm.setTotalCount(service.CategoryCount(category));
		model.addAttribute("pm", pm);

		// 블락된 회원 가져오기
		MemberVO mvo = service.blockMember((String) session.getAttribute("id"));
		model.addAttribute("member", mvo);
		
		return "/goods/goodsList";
	}

	// 상품 상세페이지
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String goodsDetailGET(@ModelAttribute("cri") Criteria cri, @RequestParam("gno") int gno, Model model,
			HttpSession session, @ModelAttribute("category") String category) throws Exception {

		String id = (String) session.getAttribute("id");

		model.addAttribute("goods", service.goodsDetail(gno));
		model.addAttribute("id", (String) session.getAttribute("id"));

		// 파일이미지 출력
		List<Map<String, Object>> fileList = service.selectFileList(gno);
		model.addAttribute("file", fileList);

		// 댓글 조회 후 출력
		model.addAttribute("cmtList", cmtService.commentList(gno));

		// 입찰하기 목록 출력
		model.addAttribute("bidList", service.getBidding(gno));

		// 로그인된 회원정보 출력
		model.addAttribute("memberList", service.myInfo(id));

		// 카테고리
		model.addAttribute("category", category);
		model.addAttribute("cri", cri);
		// *************** 2020/11/16/월요일 낙찰정보 **************************
				// 마감시간이되면 goods테이블 actionstats 0으로 업데이트
				Date now = new Date(); // 현재시간
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 시간형식 지정
						System.out.println("현재시간 : " + sdf.format(now));
						GoodsVO gvo = service.goodsDetail(gno);
						Date endDate = gvo.getEndDate();
						if(now.getTime() >= endDate.getTime()) {
							service.endStatus(gno);					
							gvo = service.goodsDetail(gno);
							if(gvo.getActionstatus() == 0) {
								// 최종 낙찰 정해졌을때만 myaction에 값을 넣어준다.
								finalBidVO finalBid = service.finalBid(gno);	
								// 이미 이 상품에 대해 낙찰정보가 있는경우 중복을 막기위해 번호에 해당하는 정보를 가져와서 null이면 넣어준다.
								MyActionVO myVO = myService.myActionInfo(gno);						
								if(finalBid != null && myVO == null) {
									service.insertMyAction(finalBid);
								}					
							}
				}
				// *************** 2020/11/16/월요일 낙찰정보끝 **************************
		// 현재입찰가
		List<PricemonitoringVO> prvo = service.getBidding(gno);
		if (prvo.size() == 0) {
			model.addAttribute("finalPrice", gvo.getLowestprice());
		} else {
			model.addAttribute("finalPrice", service.finalPrice(gno));
			service.finalpriceupdate(gno);
		}
	
		// 판매자의 다른상품목록 출력
		GoodsVO vo = service.goodsDetail(gno);
		model.addAttribute("List", service.anothergoods(vo));

		//입찰자수 가져오기 pm_g_gno
		model.addAttribute("gd_bidCount", service.gd_bidCount(gno));
		
		//좋아요유지
		model.addAttribute("isClickedLikeBtn", service.isClickedLikeBtn(gno, id));
		System.out.println("좋아요유지 : "+service.isClickedLikeBtn(gno, id)+" gno랑 id는? "+gno+id);
		
		return "/goods/goodsDetail";
	}

	// 상품수정
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String goodsModifyGET(@RequestParam("gno") int gno, Model model, HttpSession session,
			@ModelAttribute("cri") Criteria cri, @ModelAttribute("category") String category) throws Exception {

		// 아이디 세션값
		model.addAttribute("id", (String) session.getAttribute("id"));

		GoodsVO goodsVO = service.goodsDetail(gno);

		// 상품 수정 페이지 입력된 값 서비스
		model.addAttribute("goodsVO", goodsVO);

		// 이미지 업로드 수정
		List<Map<String, Object>> fileList = service.selectFileList(goodsVO.getGno());
		model.addAttribute("file", fileList);

		// 페이징 정보
		model.addAttribute("cri", cri);
		model.addAttribute("category", category);

		return "/goods/goodsModify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String goodsModifyPOST(GoodsVO vo, @RequestParam(value = "fileNoDel[]") String[] files,
			@RequestParam(value = "fileNameDel[]") String[] fileNames, MultipartHttpServletRequest mpRequest,
			RedirectAttributes rttr, @ModelAttribute("category") String category, @ModelAttribute("cri") Criteria cri)
			throws Exception {

		// 상품 수정 서비스 호출
		service.goodsModify(vo, files, fileNames, mpRequest);

		// 수정 완료된 페이징 정보
		rttr.addAttribute("category", category);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("pageSize", cri.getPageSize());

		return "redirect:/goods/list";
	}

	// 상품삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String goodsDeletePOST(@RequestParam("gno") int gno, Model model, Criteria cri, RedirectAttributes rttr, @ModelAttribute("category") String category) throws Exception {

		// 상품 삭제 서비스 호출
		service.goodsDelete(gno);

		model.addAttribute("category", category);

		// 삭제 완료된 페이징 정보
		rttr.addAttribute("category", category);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("pageSize", cri.getPageSize());

		return "redirect:/goods/list";
	}

	// ck에디터 이미지 업로드
	@RequestMapping(value = "/ckUpload", method = RequestMethod.POST)
	public void imageUpload(HttpServletRequest request, HttpServletResponse response,
			MultipartHttpServletRequest multiFile, @RequestParam MultipartFile upload) throws Exception {

		// 랜덤 문자 생성
		UUID uid = UUID.randomUUID();

		OutputStream out = null;
		PrintWriter printWriter = null;

		// 인코딩
		response.setContentType("text/html;charset=utf-8");

		try {

			// 파일 이름 가져오기
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();

			// 이미지 경로 생성
			String path = "C:\\mp\\file\\";// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
			String ckUploadPath = path + uid + "_" + fileName;
			File folder = new File(path);

			// 해당 디렉토리 확인
			if (!folder.exists()) {
				try {
					folder.mkdirs(); // 폴더 생성
				} catch (Exception e) {
					e.getStackTrace();
				}
			}

			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화

			String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();
			String fileUrl = "ckImgSubmit?uid=" + uid + "&fileName=" + fileName; // 작성화면

			// 업로드시 메시지 출력
			printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
			printWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	@RequestMapping(value = "/ckImgSubmit")
	public void ckSubmit(@RequestParam(value = "uid") String uid, @RequestParam(value = "fileName") String fileName,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 서버에 저장된 이미지 경로
		String path = "C:\\mp\\file\\";

		String sDirPath = path + uid + "_" + fileName;

		File imgFile = new File(sDirPath);

		// 사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
		if (imgFile.isFile()) {
			byte[] buf = new byte[1024];
			int readByte = 0;
			int length = 0;
			byte[] imgBuf = null;

			FileInputStream fileInputStream = null;
			ByteArrayOutputStream outputStream = null;
			ServletOutputStream out = null;

			try {
				fileInputStream = new FileInputStream(imgFile);
				outputStream = new ByteArrayOutputStream();
				out = response.getOutputStream();

				while ((readByte = fileInputStream.read(buf)) != -1) {
					outputStream.write(buf, 0, readByte);
				}

				imgBuf = outputStream.toByteArray();
				length = imgBuf.length;
				out.write(imgBuf, 0, length);
				out.flush();

			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
	// ck 이미지 업로드

	// 첨부파일 다운로드
	@RequestMapping(value = "/fileDown")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = service.selectFileInfo(map);
		String storedFileName = (String) resultMap.get("f_name");
		String originalFileName = (String) resultMap.get("f_oname");

		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환
		byte fileByte[] = org.apache.commons.io.FileUtils
				.readFileToByteArray(new File("C:\\mp\\file\\" + storedFileName));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

// 재원 *******************************************************************************************************************************
	// 상품신고
	// http://localhost:8088/goods/report?gno=1
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public void reportGET(HttpSession session, @RequestParam("gno") int bno, Model model) throws Exception {
		String id = (String) session.getAttribute("id");
		model.addAttribute("reportVO", service.showReportDetail(bno));

		model.addAttribute("myInfo", service.myInfo(id));
	}

	// 상품신고
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public String reportPOST(ReportVO rvo, RedirectAttributes rttr,HttpServletRequest request)
			throws Exception {
		String text = request.getParameter("content");
		System.out.println("레포트 VO : " + rvo);
		System.out.println("기타사유 : " + text);
		
		// 메일 관련 정보(gmail)
		String setfrom = rvo.getReporterEmail();
		String tomail = "bestpricemarketnoreply@gmail.com"; // 받는 사람 이메일
		String title = rvo.getGname() + "(" + rvo.getGno() + ") 게시글 신고"; // 제목
		String content = "신고자 : " + rvo.getReporter() + "\n"; // 내용
		content += "신고 게시글 : http://localhost:8088/goods/detail?gno=" + rvo.getGno() + "\n";
		if (rvo.getRepo() == 1) {
			content += "사유 : 위법성 상품 \n";
		} else if (rvo.getRepo() == 0) {
			content += "사유 : 반복적인 상품게시(도배) \n";
		} else if (rvo.getRepo() == -1) {
			content += "사유 : " + text + "\n";
		}
	
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	
			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용
	
			mailSender.send(message); 		
	
			rttr.addFlashAttribute("result","1");
		} catch (Exception e) {
			System.out.println(e);
		}
	
		return "redirect:/goods/detail?gno=" + rvo.getGno();
	}
	
	//입찰하기
	@RequestMapping(value = "/bidding", method = RequestMethod.POST)
	@ResponseBody
	public PricemonitoringVO biddingPOST(PricemonitoringVO prvo, Model model, HttpServletResponse resp)
			throws Exception {
		System.out.println("객체 : " + prvo);
		List<PricemonitoringVO> getDB = service.getBidding(prvo.getPm_g_gno());
		System.out.println("객체리스트 : " + getDB);
		GoodsVO gvo = service.goodsDetail(prvo.getPm_g_gno());
		System.out.println("무슨값 : " + gvo);
		/* DB에서 가져온값이 없을때 db에 저장후 prvo 리턴 */
		if (getDB.size() == 0 && prvo.getPm_g_bidprice() >= gvo.getLowestprice()) {
			service.insertBidding(prvo);
			getDB = service.getBidding(prvo.getPm_g_gno());
			System.out.println("객체리스트2번쨰 : " + getDB);
			if (getDB.size() > 0) {
				service.upStatus(prvo.getPm_g_gno());
			}
			return prvo;
		} else if (getDB.size() != 0) {
			int maxPrice = service.getMaxPrice(prvo.getPm_g_gno());
			System.out.println("최소값 : " + gvo.getLowestprice());
			if (prvo.getPm_g_bidprice() > maxPrice && prvo.getPm_g_bidprice() > gvo.getLowestprice()) {
				service.insertBidding(prvo);
				return prvo;
			}
		
		}
		return null;
	}

// 정현 *******************************************************************************************************************************
	//좋아요 입력 -> 제품상세페이지
	@ResponseBody
	@RequestMapping(value="/likes", method= {RequestMethod.GET, RequestMethod.POST})
	public String likeGET(@RequestParam("gno") int gno,@RequestParam("l_g_gno") int l_g_gno, 
			  @RequestParam("l_m_id") String l_m_id,
			  @ModelAttribute LikesVO vo, HttpSession session) throws Exception{
		System.out.println("C : 좋아요 created의 vo"+vo+" param's gno는 "+gno);
	    int check = service.countbyLike(l_m_id, gno);

	    //변수찾기
	    System.out.println("l_m_id는 "+l_m_id+" vo.getL_m_id는 "+vo.getL_m_id()+" check는 "+check);
  	
	    // 좋아요 likes테이블 삭제, goods테이블의 glike컬럼 업데이트(delete, update)
		if(l_m_id == vo.getL_m_id() && check == 1) {
			System.out.println("C: 좋아요취소의 gno는 "+gno+" l_g_gno는? "+l_g_gno);
			service.deletebyLikes(l_m_id, l_g_gno); // likes
			service.deletebyGoods(gno);  // goods의 glike
			return "cancel";
		}else {
		    //좋아요 입력
	    	service.goodsLike(gno);
	    	service.like(vo);	
	    	return "likeClick";
		}			
	}//end of likeGet메서드  	  

}