package el.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import el.model.vo.Student;

@WebServlet("/el/*")
public class elServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
  
    public elServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

 
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      String uri = request.getRequestURI();
      String[] uriArr = uri.split("/");
      
      switch(uriArr[uriArr.length-1]){
         case "el": request.getRequestDispatcher("/WEB-INF/view/el_jstl/el_inp.jsp").forward(request, response);
            break;
         case "elstudy" : testEL(request,response);
            break;
         default : ;
      }
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }
   
   private void testEL(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //파라미터로 넘어온 국영수코딩 과목의 평균점수와 총합계를 구해서 
      //request.setAtrribute로 저장
      String name = request.getParameter("name");
      int kor = Integer.parseInt(request.getParameter("kor"));
      int math = Integer.parseInt(request.getParameter("math"));
      int eng = Integer.parseInt(request.getParameter("eng"));
      int code = Integer.parseInt(request.getParameter("code"));
      
      int sum = kor + math + eng + code;
      int avg = sum/4;
      
      
      //1. request에 attribute로 합계와 평균을 담아준다
      request.setAttribute("sum", sum);
      request.setAttribute("avg", avg);
      
      
      /////////////////////////////////////////////////////////
      
      
      //2. vo에 학생정보를 저장하고, jsp에서 getter를 통해 꺼내어 쓴다
      Student student = new Student();
      student.setKor(kor);
      student.setMath(math);
      student.setEng(eng);
      student.setCode(code);
      
      //request에 student라는 속성명으로 담아준다
      request.setAttribute("student", student);//object Map
      
      //////////////////////////////////////////////////////////
      
      Map commandMap = new HashMap<String, Object>();
      commandMap.put("name","map에 담긴 이름 : " + name);
      commandMap.put("kor", kor);
      commandMap.put("math", math);
      commandMap.put("eng", eng);
      commandMap.put("code", code);
      commandMap.put("sum", sum);
      commandMap.put("avg", avg);
      
      
      request.setAttribute("studentMap",commandMap);
      
      //////////////////////////////////////////////////////////////
      
      
      List studentList = new ArrayList<>();
      //여러 정보를 보낼때 list를 쓴다
      //학생이 1명이 아니라 여러명일때
      studentList.add(student);
      studentList.add(commandMap);
      
      request.setAttribute("studentList",studentList);
      
      

      //파라미터로 넘어온 학생이름을 session에 저장
      HttpSession session = request.getSession();
      session.setAttribute("name",name);
      
      //el.jsp로 요청을 재지정
      RequestDispatcher rd 
         = request.getRequestDispatcher("/WEB-INF/view/el_jstl/el.jsp");
      rd.forward(request, response);
   }
   
     
   
   
   

}