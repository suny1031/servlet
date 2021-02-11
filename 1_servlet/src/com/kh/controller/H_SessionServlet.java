package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/session/*")
public class H_SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public H_SessionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setHeader("content-type","text/html; charset=utf-8");
		
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");

		System.out.println(Arrays.toString(uriArr));

		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type","text/html ; charset=utf-8");
		switch (uriArr[uriArr.length - 1]) {
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		default:
			sendError(request, response);
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nick = request.getParameter("nick");
		System.out.println("사용자가 입력한 닉네임 : "+nick);
		
		
		PrintWriter pw = response.getWriter();
		
		//session : 정보를 session scope동안 서버에 저장하는 객체
		//기본적으로 session은 object Map 형태이다(어떤 형태의 데이터든 저장이 가능)
		
		//1. HttpSession객체 생성
		HttpSession session = request.getSession();
		//	 HttpSession.setAttribute() : 세션에 원하는 정보를 저장
		//   session객체에 저장된 정보를 session scope동안 접근 할 수 있다
		//	 session scope : 사용자가 브라우저를 끄기 전까지, 브라우저를 끄면 session scope 종료
		
		//HttpSession은 JVM의 HEAP에 올라간다
		//request.getSession() 메서드는 클라이언트로 인해 생성된 session 객체를 반환
		//즉 클라이언트로 인해 생성된 session객체가 무엇인지 식별할 수 있는 무언가가 필요하다
		// JSESSIONID : 사용자가 요청으로 세션이 생성되면 자동으로 생성되는 쿠키, 응답헤더에 담겨 날아간다
		//				생성된 세션을 식별할 수 있는 키값이 들어있다
		//				만들어진 쿠키는  session scope를 가지게 되고, 매번 요청마다 요청헤더에 담겨서
		//				서버로 전송된다.
		//				request.getSession() 메서드는 request header에 담겨있는 JSESSIONID를 사용해
		//				해당 SESSIONID로 접근 가능한 Session객체를 반환해준다
		// 				브라우저를 종료할 경우 JSESSIONID를 가지고 있는 쿠키가 사라지게 되고
		//				따라서 Session객체에 접근할 방법도 사라지게 된다
		//				Session객체는 마지막으로 호출된지 30분이 지나면 JVM에서 삭제된다
		//				session.setMaxInactiveInterval(1800)이기 때문이다
		//				
		
		session.setAttribute("nick",nick);
		//////////////////////session test///////////////
		pw.println("<h1>"+session.getAttribute("nick")+"님"+"</h1>");
		pw.println("<a href = '/servlet/session/logout'>logout</a>");
		//   HttpSession.getAttribute() : 세션에 원하는 정보를 가져올 수 있다
		
		pw.println("<hr><a href ='/servlet/request'>RequestServlet에서 session 확인</a><br>");
		pw.println("<a href = '/servlet/response'>ResponseServlet에서 session 확인</a><br>");

	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		//Session객체 삭제
		//Session객체를 삭제해도 logout과 같은 효과가 발생하지만
		//Session객체에 저장한 다른 정보도 회원정보와 함께 사라진다 ex)장바구니, 최근검색어
		//session.invalidate();
		
		//매개변수로 넣어준 키값의 데이터를 삭제
		session.removeAttribute("nick");
		//삭ㅈ 이후 request 페이지 이동
		response.sendRedirect("/servlet/request");
		
	
	}
	private void sendError(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("<h2> 404 : 요청하신 페이지는 존재하지 않습니다</h2>");
	}

}
