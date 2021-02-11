<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
h1{
color: red
}

</style>
</head>
<body>
	<h1>if문</h1>
	<%--
사용자가 파라미터로 서버에 전달한 숫자가 짝수이면 짝수입니다
홀수이면 홀수입이다를 출력하는 조건문
 --%>
	<%
		int num = Integer.parseInt(request.getParameter("num"));
	if (num % 2 == 0) {
		//짝수입니다 라고 브라우저에 출력
	%>
	<h2>짝수 입니다</h2>
	<%
		} else {
	%>
	<h2>홀수 입니다</h2>
	<%
		}
	%>

	<h1>for문</h1>
	<h2>1부터 사용자가 입력한 값까지 출력해보기</h2>
	<%
		for(int i = 1 ; i <= num ; i++){
			%>
	<span><%=i %></span>
	<%}%>

</body>
</html>