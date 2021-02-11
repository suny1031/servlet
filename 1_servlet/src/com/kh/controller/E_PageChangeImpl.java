package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class E_PageChangeRes
 */
@WebServlet("/page/impl")
public class E_PageChangeImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public E_PageChangeImpl() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html ; charset=utf-8");	
		PrintWriter pw = response.getWriter();
		pw.println("<h1> 안녕하세요 " + request.getParameter("name")+"님</h1>");
	
	
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
