package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/response")
public class C_ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public C_ResponseServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setHeader("content-type","text/html; charset=utf-8");
		//두개는 기본
		
		//Content-Disposition 헤더 : 응답이 브라우저 화면에 출력되어야 하는 응답인지
		//						사용자의 컴퓨터에 다운로드 되어야 하는 응답인지 알려주는 헤더
		//Content-Disposition=inline;
		//Content-Disposition=attachment [filename = 파일명];
		
		response.setHeader("Content-Disposition", "attachment");
		
		
		
		

		PrintWriter pw = response.getWriter();
		
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("nick") == null) {
			pw.println("<h1>"+"게스트"+"계정"+"</h1>");
		}
		//////////////////////session test///////////////
		pw.println("<h1>"+session.getAttribute("nick")+"님"+"</h1>");
		
		
		
		pw.println("<h3>getWriter : response body에 데이터를 작성</h3>");
		pw.println("<h3>getOutputStream : response body에 데이터를 작성</h3>");
		pw.println("<h3>sendRedirect : 응답코드 302전송, 브라우저에게 재 요청 할 것을 지시</h3>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
