<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel = "stylesheet" type="text/css" href = "/resources/css/index.css">
</head>
<body>
	<h1>구현한 기능들</h1>
	<ol>
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<!--비 로그인 상태-->
				<li><a href="/member/login.do">로그인</a></li>
				<li><a href="/member/join.do">회원가입</a></li>
			</c:when>
			<c:otherwise>
				<!--로그인 상태-->
				<li><a href="/member/logout.do">로그아웃</a></li>
				<li><a href="/member/mypage.do">마이페이지</a></li>
			</c:otherwise>
		</c:choose>
	</ol>
</body>
</html>