package com.kh.toy.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;



@WebServlet("/index.do")
public class indexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public indexController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		
		/*
		 * HttpUtils http = new HttpUtils(); String res =
		 * http.get("https://www.naver.com", new HashMap<String, String>());
		 * System.out.println(res);
		 */
		
		/*
		 * HttpUtils http = new HttpUtils(); Map<String ,String> params = new HashMap();
		 * params.put("query", "로또싱어"); params.put("size","30");
		 * 
		 * String url =
		 * "Https://dapi.kakao.com/v2/search/vclip?"+http.urlEncodedForm(params);
		 * 
		 * Map<String ,String> headers = new HashMap();
		 * headers.put("Authorization","KakaoAK b71160635955dc2216e4cc8872ccd06f");
		 * String jsonData = http.get(url, headers); Map<String, Object> res = new
		 * Gson().fromJson(jsonData, Map.class); System.out.println(res);
		 * 
		 * 
		 * for (String key : res.keySet()) { System.out.println(key + ":" +
		 * res.get(key)); }
		 */

		request.getRequestDispatcher("/WEB-INF/view/index/index.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
