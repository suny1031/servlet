package com.kh.member.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

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
		case "join":
			join(request, response);
			break;
		case "joinimpl":
			joinimpl(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "loginimpl":
			loginImpl(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		default:
			;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(request, response);

	}

	private void joinimpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자의 요청을 받아서, 권한 확인 및 데이터들을 가공하고 service로 넘겨준다
		String userId = request.getParameter("id");
		String password = request.getParameter("pw");
		String tell = request.getParameter("tell");
		String email = request.getParameter("email");

		Member member = new Member();

		member.setUserId(userId);
		member.setPassword(password);
		member.setTell(tell);
		member.setEmail(email);

		// 성공시 1, 실패시 0이 반환
		int res = memberService.insertMember(member);
		// 성공!
		if (res > 0) {
			request.getRequestDispatcher("/WEB-INF/view/member/join_complete.jsp").forward(request, response);

			// 실패 여러 예외가 터지면 순차적
			// controller단에서 예외를 처리하지말고 모듈화 시켜서 web.xml에서 예외를 처리한다
		}
//		else {
//			request.setAttribute("errorMsg","회원가입에 실패하였습니다");
//			request.getRequestDispatcher("/WEB-INF/view/common/error_page.jsp").forward(request, response);
//		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request, response);
	}

	private void loginImpl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//사용자가 파라미터로 보낸 아이디와 비밀번호를 받아서
		//memverService의 memberAuthenticate 메서드로 전달
		String userId = request.getParameter("id");
		String password = request.getParameter("pw");
		//결과값으로 회원정보가 반환,
		Member user  = memberService.memberAuthenticate(userId, password);
		
	
		
		//결과에 따라서 view(jsp) 페이지를 결정
		if (user != null) {
			//회원 정보를 session에 저장
			//회원 정보를 session scope동안 보관하기 위해서
			request.getSession().setAttribute("user", user);
			
			
			//로그인 성공
			request.getRequestDispatcher("/WEB-INF/view/member/member_info.jsp").forward(request, response);
		
		}else {
			//로그인 실패
			request.getRequestDispatcher("/WEB-INF/view/common/error_page.jsp").forward(request, response);

		}
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.getSession().removeAttribute("user");
		
		request.getRequestDispatcher("/WEB-INF/view/member/member_info.jsp").forward(request, response);

		
	}

}
