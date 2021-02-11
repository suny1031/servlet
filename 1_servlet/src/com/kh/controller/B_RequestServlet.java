package com.kh.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*1) "*" 모든 경우를 의미한다
// 	request/* : localhost:8787/servlet/request 로 시작하는 모든 요청

//2) "*.확장자" : 확장자 매칭을 나타낸다
// 	ex) *.do : .do로 URI가 끝나는 모든 요청을 해당 서블릿으로 받겠다

//3) "/" : default 서블릿, => servlet에 지정되지 않은 모든 요청을 처리*/

@WebServlet("/request/*")
public class B_RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public B_RequestServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	//URI : URI는 인터넷상의 자원을 식별하기위한 문자열
	//URL : URI의 일종, 자원의 위치를 나타내는 문자열
	//-> https:///localhost:8787/servlet/index.html?idx=312312
		//호스트가 localhost:8787인 웹 어플리케이션의 root폴더 아래에 위치한
		//idx가 312312인 index.html을 원한다 => URI
	//-> https:///localhost:8787/servlet/index.html
		//자원의 위치는 있지만, 정확하게 자원을 식별하기 위한 파라미터는 없음
		//따라서 자원의 위치를 나타내는 문자열의 URL
		
		
		
		
	//-> https:///localhost:8787/servlet/index.html?idx=312312
	//-> https:///localhost:8787/servlet/index.html?idx=456312
	//같은 URL 다른 URI
		
		
		
		
		
		// url요청에 따라 알맞은 메서드 호출
		// 사용자의 요청을 받아서 해당 요청을 수행할 적절한 메서드 수행
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");

		System.out.println(Arrays.toString(uriArr));

//		request.setCharacterEncoding("utf-8");
//		response.setHeader("content-type","text/html ; charset=utf-8");
		
		switch (uriArr[uriArr.length - 1]) {
		//uriArr에는 마지막 배열의 값을 꺼내온다
		// ex) {,servlet,request} = request
		//     {,servlet,request,post} = post
		case "request":
			testGet(request, response);
			break;
		case "post":
			testPost(request, response);
			break;
		case "multi":
			testMulti(request, response);
			break;
		default:
			sendError(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("post 요청이 도착했습니다");
		// post메서드로 요청이 올 경우 doGet 메서드를 호출
		// servlet으로 부터 전달받은 request 객체와 response 객체를 함께 넘겨준다
		doGet(request, response);
	}

	// request 로 요청이 왔을 때 호출 할 메서드
	private void testGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRequestURI() + "요청");
		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		PrintWriter pw = response.getWriter();
		
		HttpSession session = request.getSession();
		
		
		//session scope가 종료되어도 session객체에 접근 할 수 있도록
		//JSESSIONID의 쿠키의 Max-Age를 sessoin이 아니라 시간으로 잡아주자
		//JSESSIONID에 저장되는 value값인 session의 아이디는 HttpSession객체의 getId()로 받아올 수 있다
		
		response.setHeader("set-cookie", "JSESSIONID="+session.getId()+";Max-Age=3600;Path=/");
		
		
		//////////////////////session test///////////////
		//만약 생성된 session이 없다면 새로운 session을 생성
		//하지만 그 session에는 nick이라는 키 값으로 저장된 데이터가 없기 때문에 null이 출력된다
		//		HttpSession session = request.getSession(true) : 만약 생성된 session이 없다면 새로운 session 생성
		//		HttpSession session = request.getSession(false) : 만약 생성된 session이 없다면 null을 생성

		String nickName = (String) session.getAttribute("nick");
		nickName = nickName == null ?"Guest":nickName;
		pw.println("<h1>"+session.getAttribute("nick")+"님"+"</h1>");
		pw.println("<h1> GET 방식으로 요청하셨습니다</h1>");
		pw.println("<h2>" + name + "</h2>");
		pw.println("<h2>" + age + "</h2>");


	}

	// request/post 로 요청이 왔을 때 호출 할 메서드
	private void testPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRequestURI() + "요청");
		
		String postname = request.getParameter("postname");
		String postage = request.getParameter("postage");
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<h1> post 방식으로 요청하셨습니다</h1>");
		pw.println("<h2>" + postname + "</h2>");
		pw.println("<h2>" + postage + "</h2>");
		pw.println("<h2>" +"당신은 10년뒤"+ (Integer.parseInt(postage)+10) + "</h2>");

		
	}

	// request/multi 로 요청이 왔을 때 호출 할 메서드
	private void testMulti(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getRequestURI() + "요청");		
		//멀티는 getParameter 사용할 수 없음
		String res = request.getParameter("text");
		System.out.println(res);
		System.out.println(getServletContext().getRealPath("/"));
		
		InputStream is = request.getInputStream();
		
		//서버의 root폴더를 목적지로 하는 OutStream 생성
		OutputStream os = new FileOutputStream(
				new File(getServletContext().getRealPath("/")+"test.txt"));
		
		//읽어온 데이터로 파일 생성
		int check = 0;
		while((check=is.read())!=-1) {
			os.write(check);
		}
		os.close();
		is.close();
	}

	
	private void sendError(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("<h2> 404 : 요청하신 페이지는 존재하지 않습니다</h2>");
	}

}
