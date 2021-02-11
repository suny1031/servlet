package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cash")
public class F_CashServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public F_CashServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ETag : 컨텐츠가 바뀌었는지 검사할 수 있는 태그
		//		만약 컨텐츠가 1바이트라도 변경되었다면 ETag값이 수정되게 된다
		//   	또한 컨텐츠의 최종수정일이 변경되면 ETag값이 변경된다
		
		
		//Cache-Control 헤더 : 캐시정책을 설정
		//no-store : 캐시를 저장하지 않음
		//no-Cache : 매번 서버에서 해당 자원의 Etag을 확인한다
		//			만약 서버자원의 Etag와 캐시 저장소의 Etag가 다르다면 자원을 받고
		//			동일하다면 캐시저장소의 자원을 사용한다(서버 부담을 덜어준다)
		//max-age : 캐시 수명을 지정한다. 지정한 초가 지나기 전에는 서버에 Etag도 확인하지 않고
		//			캐시 저장소에 있는 자원을 사용한다. 수명이 지나고 나면 서버의 Etag를 확인
		
		//해당 이미지의 캐시를 저장하지 않는다
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("content-disposition", "attachment ; fileName= chun.png");
		response.setContentType("image/png");
		RequestDispatcher view = request.getRequestDispatcher("/resources/image/c.png");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
