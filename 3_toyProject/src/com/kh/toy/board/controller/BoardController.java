package com.kh.toy.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.toy.board.model.service.BoardService;
import com.kh.toy.member.model.vo.Member;


@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();

	public BoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length - 1]) {
		case "write.do":
			writeBoard(request, response);
			break;
		case "upload.do":
			uploadBoard(request, response);
			break;
		case "download.do":
			download(request, response);
			break;
		case "detail.do":
			boardDetail(request, response);
			break;
			

		default:
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void writeBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/board/boardForm.jsp").forward(request, response);

	}

	private void uploadBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 제목, 본문, 작성자(session), 첨부파일
		// 파일테이블 : 원본파일명, 리네임파일명, 게시글 번호, 저장경로

		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("user");

		boardService.insertBoard(member.getUserId(), request);

		request.setAttribute("alertMsg", "게시글 등록이 완료되었습니다");
		request.setAttribute("url", "/index.do");

		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);

	}

	private void download(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String originFileName = request.getParameter("ofname");
		 String renameFileName = request.getParameter("rfname");
		 String savePath = request.getParameter("savePath");

		response.setHeader("Content-Disposition", "attachment; filename =" + originFileName);
		//response.setHeader("content-type", "charset=UTF-8");
		System.out.println("/file" + savePath + renameFileName);
		request.getRequestDispatcher("/file" + savePath + renameFileName).forward(request, response);
	}
	
	private void boardDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String bdIdx = request.getParameter("bdIdx");
		Map<String,Object> commandMap = boardService.selectBoardDetail(bdIdx);
		System.out.println(commandMap);
		
		request.setAttribute("data",commandMap);
		request.getRequestDispatcher("/WEB-INF/view/board/boardView.jsp").forward(request, response);
				
	}

}
