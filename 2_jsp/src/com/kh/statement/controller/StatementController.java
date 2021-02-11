package com.kh.statement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/statement/*")
public class StatementController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StatementController() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");

		System.out.println(Arrays.toString(uriArr));

		switch (uriArr[uriArr.length - 1]) {
		case "statement":
			sendstatementInput(request, response);
			break;
		case "calc":
			sendstatement(request, response);
			break;
		default:
			sendError(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void sendstatementInput(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/statement_inp.jsp");
		rd.forward(request, response);
	}

	private void sendstatement(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/statement.jsp");
		rd.forward(request, response);

	}

	private void sendError(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("<h2> 404 : 요청하신 페이지는 존재하지 않습니다</h2>");
	}

}
