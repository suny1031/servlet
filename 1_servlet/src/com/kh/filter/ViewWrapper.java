package com.kh.filter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

//HttpServletRequest를 구현하는
//HttpServletRequestWrapper를 상속
public class ViewWrapper extends HttpServletRequestWrapper{

		
	HttpServletRequest request;
	
	public ViewWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
		//매개변수로 넘어온 request객체를 사용하기 위해 전역으로 올린다
	}
	
	//servlet의 request로 전달 될 request 객체의 메서드의 기능을 변경
	//HttpServletRequest 객체를 servlet으로 전달하는 대신
	//HttpServletRequest 인터페이스를 구현하고 있는, 그래서 doGet, doPost 메서드의 request에
	//담길 수 있는 HttpServletRequestWrap 객체를 전달
	//해당 객체는 우리가 원하는 대로 메서드를 추가하거나, 메서드를 오버라이드를 하여 전달이 가능하다
	
	
	//1. method override
	//매개변수로 전달 받은 path에 앞에 /WEB-INF/view/를
	//뒤에는 .html 확장자를 붙여서 해당 경로로 요청을 제정해주는  requestDispatcher를 반환
	public RequestDispatcher getRequestDispatcher(String path) {
		RequestDispatcher rd
		= request.getRequestDispatcher("/WEB-INF/view/"+path+".html");
		return rd;
	}
	public void testPclassWrapper() {
		System.out.println("Wrapper에 추가한 메서드를 서블릿에서 호출이 가능할까?");
	}

}
