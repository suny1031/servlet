package com.kh.index.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/index")
public class indexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public indexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("IndexController 호출");
		request.setAttribute("지시자태그", "<%@ %>");
		request.setAttribute("선언태그", "<%! %>");
		request.setAttribute("스크립틀릿태그", "<% %>");
		request.setAttribute("표현식태그", "<%= %>");
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/index.jsp");
		rd.forward(request, response);
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
