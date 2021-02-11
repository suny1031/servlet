package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/page/*")
public class D_PageChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public D_PageChange() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		///servlet/page/redirect/name
		String[] uriArr = uri.split("/");
		System.out.println(Arrays.toString(uriArr));

		request.setCharacterEncoding("utf-8");
		//response.setHeader("Content-Type","text/html; charset=utf-8");

		switch (uriArr[uriArr.length - 2]) {
		case "forward":
			if (uriArr[uriArr.length - 1].equals("name")) {
				testForwardWithName(request, response);
			} else {
				testForwardWithImage(request, response);
			}
			break;
		case "redirect":
			if (uriArr[uriArr.length - 1].equals("name")) {
				testRedirectWithName(request, response);
			} else {
				testRedirectWithImage(request, response);
			}
			break;
		default:
			sendError(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void sendError(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("<h2> 404 : 요청하신 페이지는 존재하지 않습니다</h2>");
	}
	
	private void testForwardWithName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		///RequestDispatcher클래스의 forward 메서드는 받은 요청을 서블릿 안에 재지정해준다
		//사용자가 보낸 요청을 /page/impl 경로로 재지정하였다
		//사용자에게 응답을 하는 코드는 E_PageChangeImpl의 메서드에 정의 되어있다
		RequestDispatcher view = request.getRequestDispatcher("/page/impl");
		//매개변수로 전달 받은 request와 response를 전달
		view.forward(request, response);
			
	}
	private void testRedirectWithName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//respone.sendRedirect() : 브라우저에게 우리가 지정한 경로로 요청을 다시 하라고 응답
		//브라우저가 요청하기 때문에 컨택스트 루트가 들어가야됨 
		//response.sendRedirect("/servlet/page/impl");
		
		//content-type 헤더는 응답 바디에 들어가는 데이터에 대한 인코딩을 명시한다
		//아래의 경우 location 헤더에 담기는 값이기 때문에 content-type 헤더로는 인코딩 처리를 해줄 수 없다
		//따라서 URLEncoder.encode()를 통해 URI에서 사용할 수 없는 문자를 인코딩 처리헤주고
		//디코딩할 때 어떤 인코딩	방식으로 디코딩해야 하는 지 까지 명시해준다
		
		String encodingQuery = URLEncoder.encode(request.getParameter("name"), "UTF-8");
		response.sendRedirect("/servlet/page/impl?name="+encodingQuery);
	}
	private void testForwardWithImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/png");
		response.setHeader("content-disposition", "attachment; filename = love.png");
		
		RequestDispatcher view = request.getRequestDispatcher("/resources/image/c.png");
		view.forward(request, response);
		
		
	}
	private void testRedirectWithImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("content-disposition", "attachment; filename = love.png");
		response.sendRedirect("/servlet/resources/image/c.png");
		//redirect는 통신이 투번 요청을 받고 302로 응답
		//redirect는 전달받은 정보를 가지고 다시 요청 새로운 request, response
		//이때는 content-disposition이 설정이 안되어있음
	}

}
