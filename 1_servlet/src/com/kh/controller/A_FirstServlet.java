package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//localhost:8787/servlet/first 경로로 get 또는 post로 요청하면 해당 servlet 호출
@WebServlet("/first")
public class A_FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public A_FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. 브라우저에게 문자열 데이터를 전송하기 위해 response의 getWriter() 메서드로
		// PrintWiter 객체 생성

		
//		//요청 데이터의 인코딩을 UTF-8로 처리
//		request.setCharacterEncoding("UTF-8");
//		
//		
//		//header
//		response.setHeader("Content-Type","text/html; charset=utf-8");
//		//타입은 자주 사용돼서 메서드로 따로 있다.
//		//response.setContentType("text/html; charset=utf-8");
//		
		
		//사용자가 요청에 담아 보낸 파라미터 확인하기
		String nickname = request.getParameter("nick");
		System.out.println("사용자가 nick이라는 파라미터명으로 보낸 파라미터 값 : " + nickname);
		
		
		//body
		PrintWriter writer = response.getWriter();
		writer.println("<h1 style = 'color:red'>Welcome My Server</h1>");
		writer.println("서버임");
		writer.println("<h2>"+ nickname +"님 어서오세요 </h2>");
	
	
	
	
	
	
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
