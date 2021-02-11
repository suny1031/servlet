package com.kh.menu.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/menu/*")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MenuServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
	      String[] uriArr = uri.split("/");	      
	      System.out.println(Arrays.toString(uriArr));
	      switch(uriArr[uriArr.length-1]){
	         case "menu": 
	        	 request.getRequestDispatcher("/WEB-INF/view/menu/menu.jsp").forward(request, response);
	            break;
	         case "order" :	calcOrder(request,response);	 
	            break;
	         default : ;
	      }
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	private void calcOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트가 food라는 이름으로 보내는 파라미터를 받아서 String 배열에 저장
		//request.getParameter로 안됩니다. 메서드 찾아보기
		
		//사용자가 주문한 정보를 받아서 결제가격 계산하기
		// 피자 : 10000원, 햄버거:5000원, 회:7000원, 치킨:18000원
		
		//주문한 내역과, 상품별 가격, 결제가격을 order-res.jsp로 전달
		
		String[] orderMenus = request.getParameterValues("food");
		System.out.println(Arrays.toString(orderMenus));
		
		List orderList = new ArrayList<Map>();
		int payPrice = 0;
		for (String food : orderMenus) {
			int price = 0;
			Map commandMap = new HashMap<String, Object>();
			//사용자가 선택한 음식에 따라 가격을 변수에 담아주고
			switch (food) {
			case "피자":
				price = 10000;
				break;
			case "햄버거":
				price = 5000;
				break;
			case "회":
				price = 70000;
				break;
			case "치킨":
				price = 18000;
				break;
			}
			//선택한 음식과 가격을 map에 담아준다
			commandMap.put("name",food);
			commandMap.put("price",price);
			
			//반복문을 사용해 사용자가 주문한 메뉴 정보를 모두 리스트에 담아준다
			orderList.add(commandMap);
	
			//결재금액 구하기
			payPrice += price;
			
		}
		
		
		
		//주문한 내역과, 상품별 가격, 결제가격을 order-res.jsp로 전달
		//4. 실행 결과를 jsp에게 전송
		request.setAttribute("orderList",orderList);
		request.setAttribute("payPrice",payPrice);
		
		//5. 전달할 jsp를 선택
		 RequestDispatcher rd =
		 request.getRequestDispatcher("/WEB-INF/view/menu/order_res.jsp");
		 rd.forward(request, response);
		
		
		
		
		
		
		
		
		
		
		
		/*
		 * String[] food = request.getParameterValues("food");
		 * System.out.println(Arrays.toString(food)); ArrayList list = new ArrayList();
		 * ArrayList list2 = new ArrayList(); ArrayList list3 = new ArrayList(); Map map
		 * = new HashMap<String , Object>(); for (int i = 0; i < food.length; i++) {
		 * list.add(food[i]); if (list.get(i).equals("피자")) { list2.add(10000); } if
		 * (list.get(i).equals("햄버거")) { list2.add(5000); } if (list.get(i).equals("회"))
		 * { list2.add(7000); } if (list.get(i).equals("치킨")) { list2.add(18000);
		 * 
		 * }
		 * 
		 * 
		 * } request.setAttribute("list",list); request.setAttribute("list2",list2);
		 * 
		 * 
		 * RequestDispatcher rd =
		 * request.getRequestDispatcher("/WEB-INF/view/menu/order-res.jsp");
		 * rd.forward(request, response);
		 */
		
		
		
		
		
		
		
		
	
	}
	

}
