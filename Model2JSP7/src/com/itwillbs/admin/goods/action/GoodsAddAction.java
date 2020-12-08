package com.itwillbs.admin.goods.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GoodsAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : GoodsAddAction_execute() 호출");
		
		// 파일 업로드 준비 
		// ./upload 폴더 생성(가상경로)
		
		// 파일이 저장되는 실제위치
	    // 프로젝트의 정보
		ServletContext context = request.getServletContext();
	    System.out.println("context : "+context);
	    // 실제 저장 위치
	    String realPath = context.getRealPath("/upload");
	    System.out.println("realPath : "+realPath);
	    		
		// 크기
	    int maxSize = 10 * 1024 * 1024; //10mb
		
	    // Multipart 객체 생성
	    MultipartRequest multi =
	    		new MultipartRequest(
	    				request,
	    				realPath,
	    				maxSize,
	    				"UTF-8",
	    				new DefaultFileRenamePolicy()    				
	    				);
		System.out.println("M : 파일업로드 완료 "+multi);
				
		
		// 상품정보를 저장(파라미터)
		// GoodsDTO 객체 생성 -> 저장
		
		GoodsDTO gdto = new GoodsDTO();
		
		gdto.setCategory(multi.getParameter("category"));
		gdto.setName(multi.getParameter("name"));
		gdto.setPrice(Integer.parseInt(multi.getParameter("price")));
		gdto.setColor(multi.getParameter("color"));
		gdto.setAmount(Integer.parseInt(multi.getParameter("amount")));
		gdto.setSize(multi.getParameter("size"));
		gdto.setContent(multi.getParameter("content"));
		
		// 인기상품best
		gdto.setBest(0); // 0-일반상품 , 1-인기상품
		
		// 이미지 정보 처리 
		String img = multi.getFilesystemName("file1")+","
				+ multi.getFilesystemName("file2")+","
				+ multi.getFilesystemName("file3")+","
				+ multi.getFilesystemName("file4");
		
		System.out.println("img : "+img);
		gdto.setImage(img);
		
		
		// 파라미터값 저장 완료
		System.out.println("M : 정보 저장완료 GoodsDTO "+gdto);
		
		// AdminGoodsDAO 객체 생성 -> 메서드사용 
		// insertGoods()
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.insertGoods(gdto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);		
		return forward;
	}

}
