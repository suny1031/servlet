package com.kh.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@WebListener
public class SessionListener implements ServletContextListener, HttpSessionAttributeListener {

	
	//ServletContext
	//servlet과 톰캣컨테이너가 상호작용 하기 위해 필요한 메서드를 제공해주는 객체
	//하나의 웹 어플리케이션당 하나의 ServletContext만 존재
	//즉 모든 thread와 모든 servlet class가 공유하는 객체
	//application scope를 가진다
	//application scope : 서버가 실행될 때 메모리에 올라가서, 서버가 내려갈 때 파괴
	//따라서 ServletContext 객체의 setAttribute() 메서드를 사용해 저장한 정보는
	//서버가 종료 될 때 까지 삭제되지 않는다
	
	
	//Servlet scope
	//request scope : 요청과 응답이 완료되면 파괴 ex)request, response
	//session scope : 요청에 의해서 생성되고, 클라이언트의 브라우저가 종료되면 수명이 종료되면 파괴
	//application scope : 서버가 실행될 때 메모리에 올라가서, 서버가 내려갈 때 파괴
	
	private ServletContext context;
	
    public SessionListener() {
    }

    //서버에 로그인한 사용자 수를 카운트
    //ServletContext가 생성되는 시점에 사용자는 0명일 것이기 때문에
    //사용자 수를 0명으로 초기화
    public void contextInitialized(ServletContextEvent sce)  { 
    	context = sce.getServletContext();
    	//사용자 수를 0명으로 초기화
    	context.setAttribute("userCount", 0);
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
    	//HttpSession에 nick이라는 속성이 추가될 때 이벤트 발생
    	//arg0는 nick이 생성될때의 정보?를 가지고 있나봄
    	//1. HttpSession에 추가된 속성이 'nick'속성인지 확인
    	if (arg0.getName().equals("nick")) {
    		//nick이 맞다면 서블릿 컨텍스트의 userCount를 1 증가시킴
			int count = (int)context.getAttribute("userCount");
			context.setAttribute("userCount", ++count);
		}
    
    }


    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    	if (arg0.getName().equals("nick")) {
    		//nick이 맞다면 서블릿 컨텍스트의 userCount를 1 증가시킴
			int count = (int)context.getAttribute("userCount");
			context.setAttribute("userCount", --count);
		}
    
    }


    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }


	
}
