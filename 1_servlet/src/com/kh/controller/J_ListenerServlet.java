package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class J_ListenerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public J_ListenerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 서블릿컨텍스트 객체를 불러온다
		ServletContext context = getServletContext();
		//2. 서블릿컨텍스트에서 userCount 라는 속성의 값을 가져온다
		int userCount = (int)context.getAttribute("userCount");
		//3. 사용자에게 로그인 중인 사용자수를 응답해준다
		response.getWriter().println("<h1>"+userCount+"명이 접속중입니다</h1>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
