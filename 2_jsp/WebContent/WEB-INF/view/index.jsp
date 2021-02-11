<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>JSP 기초</h1>
<pre>
JSP는 기존에 서버용 자바언어인 Servlet에서 
화면 구현에 관련된 소스 부분을 별도로 분리하는 기술을 말한다
</pre>
<h2>지시자 태그</h2>
<h3><%=request.getAttribute("지시자태그")%></h3>
<pre>
지시자 태그란, 해당하는 페이지 전체에서 사용할 속성을 지정하는 JSP태그

1. page : 해당 페이지에서 사용할 속성을 지정
	1-1 : language : 사용할 프로그래밍 언어
	1-2 : import : 페이지에서 필요한 자바의 클래스를 import 할 때 사용
2.include : 다른 위치의 html/jsp를 현재 페이지에 삽일할때 사용
2.taglib  : 다른 라이브러리에서 제공하는 커스텀태그를 사용할 때 사용 
</pre>
<h2>선언 태그</h2>
<h3><%= request.getAttribute("선언태그")%></h3>
<pre>
선언태그는 메서드, 필드변수를 선언할 때 사용. 따라서 static 변수도 가능하다
클래스의 필드에 해당하기 때문에 함부로 사용하면 위험하다
</pre>
<%! 
public String declaration(String startDec, String endDec){
	return startDec  +" "+ endDec;
 }
%>
<h2>스크립틀릿(Scriptlet)태그</h2>
<h3>
<%
String arg1 = "&lt;%";
String arg2 = "%&gt"; 
out.write(declaration(arg1,arg2));
%>

</h3>
<pre>
페이지 내부에서 Java의 소스코드를 작성하는 영역을 나타내는 태그
스크립틀릿에 작성하는 코드는 _jspService() 메서드 내부에 작성된다
따라서 메서드 선언이나, static 변후는 사용할 수 없다
</pre>
<h2>표현식 태그</h2>
<h3><%
String exArg1 = "&lt;%=";
String exArg2 = "%&gt"; 
%>
<%= declaration(exArg1,exArg2)%>
</h3>
<pre>
표현식 태그란 특정 객체나, 변수의 값을 출력하는 용도로 사용
out.println() 메서드를 보다 편하게 사용할 수 있다
</pre>

</body>
</html>