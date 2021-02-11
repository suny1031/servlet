package com.kh.jstl.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jstl/*")
public class jstlController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public jstlController() {
        super();
    }

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
	      String[] uriArr = uri.split("/");	      
	      System.out.println(Arrays.toString(uriArr));
	      switch(uriArr[uriArr.length-1]){
	         case "jstl": 
	        	 request.getRequestDispatcher("/WEB-INF/view/el_jstl/testjstl.jsp").forward(request, response);
	            break;
	         case "jstlStudy" :
	        	 
	            break;
	         default : ;
	      }
		
	
	
	
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
