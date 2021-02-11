package com.kh.toy.member.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.ToAlertException;
import com.kh.toy.member.model.service.MemberService;
import com.kh.toy.member.model.vo.Member;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	public MemberController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		System.out.println(Arrays.toString(uriArr));

		
		switch (uriArr[uriArr.length - 1]) {
		
		case "join.do":
			join(request, response);
			break;
		case "mailauth.do":
			authenticateEmail(request, response);
			break;
		case "joinimpl.do":
			joinimpl(request, response);
			break;
		case "login.do":
			login(request, response);
			break;
		case "loginimpl.do":
			loginImpl(request, response);
			break;
		case "logout.do":
			logout(request, response);
			break;
		case "idcheck.do":
			idCheck(request, response);
			break;
		case "mypage.do":
			myPage(request, response);
			break;
		default: throw new ToAlertException(ErrorCode.CD_404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(request, response);

	}
	
	private void authenticateEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userId = request.getParameter("id");
		String password = request.getParameter("pw");
		String tell = request.getParameter("tell");
		String email = request.getParameter("email");

		Member member = new Member();

		member.setUserId(userId);
		member.setPassword(password);
		member.setTell(tell);
		member.setEmail(email);
		//member를 생성
		
		//이메일 발송 => 응답
		//사용자가 이메일의 링크를 통해서 member/join으로 다시 요청했을 때
		//회원 정보를 DB에 저장
		request.getSession().setAttribute("persistUser", member);
		
		//메일발송
		memberService.authenticateEmail(member);
		
		//사용자에게 보여줄 view page 지정
		request.setAttribute("alertMsg","회원가입을 위한 이메일이 발송되었습니다");
		request.setAttribute("url", "/index.do");
		
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		
	}

	private void joinimpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("persistUser");
		
		
		//persistUser가 없거나, 브라우저를 종료했거나, 재요청없이 30분이 지났을 경우
		//또는 회원가입이 완료된 경우에는 회원가입을 진행하지 않음
		

		// 성공시 1, 실패시 0이 반환
		int res = memberService.insertMember(member);
		
		// 성공!
			//가입에 성공하면 다시 메일의 링크를 클릭하저라도 회원가입이 진행되지
			//않게끔 회원가입 정보를 session에서 삭제
			request.getSession().removeAttribute("persistUser");
			request.setAttribute("alertMsg", "회원가입을 축하드립니다");
			request.setAttribute("url","/index.do");
			
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);

			// 실패 여러 예외가 터지면 순차적
			// controller단에서 예외를 처	리하지말고 모듈화 시켜서 web.xml에서 예외를 처리한다
		
		
		/*
		 * else { request.setAttribute("alertMsg", "회원가입 도중 에러가 발생하였습니다");
		 * request.setAttribute("url","/member/join");
		 * 
		 * request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(
		 * request, response); }
		 */

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request, response);
	}

	
	private void loginImpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자가 파라미터로 보낸 아이디와 비밀번호를 받아서
		// memverService의 memberAuthenticate 메서드로 전달
		// String userId = request.getParameter("id");
		// String password = request.getParameter("pw");

		String data = request.getParameter("data");
		//String -> String
		//array -> ArrayList
		//number -> Double
		//null -> null
	
		Gson gson = new Gson();//파싱 라이브러리
		
		Map parsedDate = gson.fromJson(data, Map.class);
		
		//한번에 날라와서 구분이 안됨 그래서 맵으로 받아야한다
		//FORM 받은파일을 형태 ? 타입으로 바꿔주는 
		//키 id : pw : arr

		//parseDate에 담겨져온
		//문자
		//숫자
		//배열
		//객체
		//출력해보기
		
		System.out.println(parsedDate);
		
		for(Object o : parsedDate.keySet()) {
		System.out.println(o);
		System.out.println(parsedDate.get(o));

		}
		
		String userId = (String) parsedDate.get("id");
		String password = (String) parsedDate.get("pw");

		// 결과값으로 회원정보가 반환,
	
		Member user = memberService.memberAuthenticate(userId, password);

		// 결과에 따라서 view(jsp) 페이지를 결정
		if (user != null) {
			// 회원 정보를 session에 저장 // 회원
			// 정보를 session scope동안 보관하기 위해서
			request.getSession().setAttribute("user", user);
			response.getWriter().print("success"); // 로그인 성공
			// request.getRequestDispatcher("/WEB-INF/view/member/member_info.jsp").forward(request,response);

		} else {
			// 로그인 실패
			response.getWriter().print("fail");
			// request.getRequestDispatcher("/WEB-INF/view/member/error_page.jsp").forward(request,response);
			//request.sendr....
			//인덱스가 안바뀜 
		}

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().removeAttribute("user");

		request.getRequestDispatcher("/WEB-INF/view/index/index.jsp").forward(request, response);

		
	}

	private void idCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 클아이언트가 x-www-form-urlencoded 컨텐츠 타입으로 보낼 것이기 때문에
		// 이전과 동일하게 서블릿 코드 작성 가능
		// a_submit.html

		String userId = request.getParameter("userId");
		System.out.println("비동기 통신으로 넘어온 값 " + userId);
		Member member = memberService.selectMemberByld(userId);

		// println은 줄바꿈문자가 뒤에 포함이되기 때문에
		// 실제로 넘어가는 값이 'success\n'이다

		if (member == null) {
			// 사용 가능한 아이디
			response.getWriter().print("success");
		} else {
			// 사용 불가능한 아이디
			response.getWriter().print("fail");
		}

	}
	
	
	private void myPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/mypage.jsp").forward(request, response);

	}

}
