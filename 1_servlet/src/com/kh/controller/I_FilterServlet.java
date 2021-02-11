package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.filter.ViewWrapper;


@WebServlet("/filter")
public class I_FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public I_FilterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	ViewWrapper req = (ViewWrapper) request;
	req.testPclassWrapper();
	
	//WEB-INF/view/h_filter.html
	RequestDispatcher rd = req.getRequestDispatcher("h_filter");
	rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
