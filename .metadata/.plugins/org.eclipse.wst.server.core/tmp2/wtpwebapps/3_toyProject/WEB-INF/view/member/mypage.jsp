<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
<c:when test = "${sessionScope.user != null}">
<h1>로그인 한 회원의 정보</h1>
	<h2>아이디 : ${sessionScope.user.userId}</h2>
	<h2>비밀번호 : ${sessionScope.user.password}</h2>
	<h2>이메일 : ${sessionScope.user.email}</h2>
	<h2>전화번호 : ${user.tell}</h2>
	<h2>가입일자 : ${user.regDate}</h2>
	<h2>대출가능일자 : ${user.rentableDate}</h2>
</c:when>
<c:otherwise>
<h1>로그아웃 처리가 완료되었습니다</h1>
</c:otherwise>
</c:choose>
</body>
</html>