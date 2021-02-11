package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookie")
public class G_CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public G_CookieServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setHeader("Content-Type", "text/html; charset=utf-8");

		String search = request.getParameter("search");
		//서버에서 선택한 value값이 넘어옴 park, yu, lee, kim
		
		String prevSearch = "최근 검색어가 없습니다";
		//preSearch 기본 값
		
		// 쿠키 생성법
		// 1. 쿠키 객체 생성
		//response.addCookie(cookie);
		
		//헤더를 지정한다 이전 값이 있다면 덮어쓴다 = set
		//헤더를 추가한다 = add
		//Max-Age : 쿠키의 수명을 초단위로 지정
		//Path : Request Header에 쿠키가 담겨야하는 url을지정
		response.setHeader("Set-Cookie", "prevSearch="+search+"; Max-Age=10; Path=/servlet/cookie");
		//response.addHeader("Set-Cookie",  "test=test");

		
		//클라이언트의 요청헤더에 담겨온 쿠키를 확인하자
		//클라이언트의 요청헤더에 담긴 모든 쿠키를 쿠키배열로 받는다
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			//만약 쿠키배열이 null이 아니라면
			for (Cookie ck : cookies) {
				System.out.println(ck.getValue().toString());
				//넘어오는 쿠키 확인
				
				//cookie의 name이 prevSearch인 쿠키를 찾는다
				if (ck.getName().equals("prevSearch")) {
					prevSearch = ck.getValue();
				}
			}
		}
 		

		PrintWriter pw = response.getWriter();
					
		pw.println("<h1>검색결과</h1>");
		pw.println("<h3 style = 'color:red'>이전 검색어 : "+ prevSearch +"</h3>");
		switch (search) {
		case "JAVA":
			pw.println("<h2>자바는 객체 지향 프로그래밍 언어입니다</h2>");
			break;
		case "ORACLE":
			pw.println("<h2>오라클은  DBMS입니다 SQL 문법으로 질의 합니다</h2>");

			break;
		case "HTML":
			pw.println("<h2>HTML은 웹페이지 제작을 위한 마크업 언어입니다</h2>");

			break;
		case "CSS":
			pw.println("<h2>CSS는 웹페이지를 이쁘게 꾸며줍니다</h2>");

			break;
		case "JAVASCRIPT":
			pw.println("<h2>자바스크립트는 동적 웹페이지 제작을 위해 사용합니다</h2>");
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
