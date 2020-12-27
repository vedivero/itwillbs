package com.bestpricemarket.service;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import com.bestpricemarket.domain.MemberVO;
import com.bestpricemarket.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{

	//DB와 연결 (의존주입)
	@Inject
	//@Autowired
	private MemberDAO mdao;

	// 회원가입
	@Override
	public void joinMember(MemberVO vo) {
		mdao.joinMember(vo);		
	}

	// 로그인
	@Override
	public MemberVO loginMember(MemberVO vo) {
		MemberVO returnVO = null;
		try {
			returnVO = mdao.readMemberWithIDPW(vo.getId(), vo.getPw());
			System.out.println("S: 로그인 아디: "+vo.getId()+" 비번: "+vo.getPw()+" 이름: "+returnVO.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			returnVO = null; //실행하다 문제가 생겼을때 해당 데이터를 보내지않겠다는 의미 = 예외처리
		}
		return returnVO; 
	}
	
	// 회원가입시 아이디중복확인
	@Override
	public MemberVO idCheck(String id) {
		return mdao.idCheck(id);
	}

	// 회원정보보기
	@Override
	public MemberVO readMember(String id) {
		MemberVO vo = null;
		try {
			vo = mdao.readMember(id);
			System.out.println("S: 로그인 정보 리턴");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	// 회원정보 수정
	@Override
	public void updateMember(MemberVO vo) {
		try {
			mdao.updateMember(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	// 회원탈퇴
	@Override
	public void deleteMember(MemberVO vo) {
	    	 try {
			mdao.deleteMember(vo);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	    	System.out.println("S: id 확인"+vo);
	}

	
	//구글 회원가입
	@Override
	public void joinMemberByGoogle(MemberVO vo) {
		mdao.joinMember(vo);
	}

	

	//구글 로그인
	@Override
	public MemberVO loginMemberByGoogle(MemberVO vo) {
		MemberVO returnVO = null;
		try {
			returnVO = mdao.readMemberWithIDPW(vo.getId(), vo.getPw());
			System.out.println("S: 로그인 아디: "+vo.getId()+" 비번: "+vo.getPw()+" 이름: "+vo.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			returnVO = null; //실행하다 문제가 생겼을때 해당 데이터를 보내지않겠다는 의미 = 예외처리
		}
		return returnVO;
	}

	//비밀번호 찾기 이메일발송
	@Override
	public void sendEmail(MemberVO vo, String div) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "bestpricemarketnoreply@gmail.com";
		String hostSMTPpwd = "bestpricemarket123!";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "bestpricemarketnoreply@gmail.com";
		String fromName = "(주)베프마켓";
		String subject = "";
		String msg = "";
		
		if(div.equals("findpw")) {
			subject = "베프마켓 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getId() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += vo.getPw() + "</p></div>";
		}
		
		// 받는 사람 E-Mail 주소
		String mail = vo.getEmail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}

	//비밀번호찾기
	@Override
	public void findPw(HttpServletResponse response, MemberVO vo) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		MemberVO ck = mdao.readMember(vo.getId());
		PrintWriter out = response.getWriter();
		// 가입된 아이디가 없으면
		if(mdao.idCheck(vo.getId()) == null) {
			out.print("등록되지 않은 아이디입니다.");
			out.close();
		}
		// 가입된 이메일이 아니면
		else if(!vo.getEmail().equals(ck.getEmail())) {
			out.print("등록되지 않은 이메일입니다.");
			out.close();
		}else {
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			vo.setPw(pw);
			// 비밀번호 변경
			mdao.updatePw(vo);
			// 비밀번호 변경 메일 발송
			sendEmail(vo, "findpw");
			
			out.print("이메일로 임시 비밀번호를 발송하였습니다.");
			out.close();
		}
	}
   
	//비밀번경
	@Override
	public void modifyPw(MemberVO vo) throws Exception {
		mdao.updatePw(vo);
	}
	
	
	

}//end of MemberServiceImpl
