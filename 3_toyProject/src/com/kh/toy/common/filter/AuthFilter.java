package com.kh.toy.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.ToAlertException;

public class AuthFilter implements Filter {

	public AuthFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		// 로그인이 안된 사용자일 경우 회원 정보 페이지에 접근할 수 없게 막기
		// 로그인이 안된 사용자 == session에 user라는 속성값이 없는 사용자
		String[] uriArr = httpRequest.getRequestURI().split("/");
		// 사용자가 접근한 url 경로 확인
		if (uriArr.length > 0) {
			// 빈배열이 넘어와서 0보다 클때 그리고 session에 값이 없을때
			// 1depth
			switch (uriArr[1]) {
			case "member":
				// 2depth
				switch (uriArr[2]) {
				case "mypage.do":
					if (session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.AUTH01);
					}
					break;
				case "joinimpl.do":
					if (session.getAttribute("persistUser") == null) {
						throw new ToAlertException(ErrorCode.AUTH02);
					}
					break;
				}

				break;
			case "board":
				switch (uriArr[2]) {
				case "write.do":
					if (session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.AUTH03);
					}

				case "upload.do":
					if (session.getAttribute("user") == null) {
						throw new ToAlertException(ErrorCode.AUTH03);
					}
				}
				break;
			}
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
