<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<body>
<form action="${context}/member/loginimpl" method = "post">
	ID : <input type = "text" name = "id"><br>
	PW : <input type = "password" name = "pw"><br>
	<button>로그인</button>
</form>
</body>
</html>