package com.kh.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


public class ViewResolver implements Filter {


    public ViewResolver() {
    }

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		ViewWrapper req = new ViewWrapper((HttpServletRequest)request);
		chain.doFilter(req, response);
		//서블릿으로 날라간다
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
