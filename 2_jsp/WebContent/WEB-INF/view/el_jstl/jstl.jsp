<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
span {
	display: block;
	font-size: 1.5vw;
	color: red;
}
</style>
</head>
<body>
	<h1>JSTL</h1>
	<pre>
	JSP에서 사용하는 스크립틀릿에서 복잡함을 해결하기 위해 등장한 사용자정의태그
	변수 생성, 변수 출력, 조건문 ,반복문, format
</pre>
	<h2>1. c:set 변수 생성 및 출력</h2>
	<pre>
	var : 변수명
	value : 값
	</pre>
	<c:set var="num1" value="100" />
	<c:set var="num2" value="200" />
	<c:set var="html" value="<a href = 'https://www.naver.com'>naver</a>" />
	<c:set var="js" value="<script>alert('el표현은 스크립트가 동작한다')</script>" />

	<span>num1 : <c:out value="${num1}" /></span>
	<span>num2 : ${num2}</span>

	<%--
	jstl의 c:out은 값을 무조건 문자열로 변환해 화면에 출력 
	--%>
	<span>jstl html : <c:out value="${html}" /></span>
	<span>el html : ${html}</span>

	<span>jstl js : <c:out value="${js}" /></span>
	<span>el js : ${js}</span>

	<c:remove var="num2" />

	<h2>jstl을 사용해 변수 삭제</h2>
	<c:remove var="num2" />
	<span>num2를 삭제하고 출력 : <c:out value="${num2}" /></span>

	<h2>jstl을 사용한 배열 생성</h2>
	<c:set var="jstlArr">
	red,blue,yellow,pink,green
	</c:set>
	<span>jstlArr : <c:out value="${jstlArr}" /></span>


	<h2>jstl 조건문</h2>
	<h3>c:if</h3>
	<c:if test="${num1 < 200}">
		<span>조건문의 조건식 결과가 true입니다 num1이 작습니다</span>
	</c:if>


	<c:if test="${num1 > 200}">
		<span>조건문의 조건식 결과가 false입니다 num1이 큽니다</span>
	</c:if>
	<h3>c:choose - when - otherwise</h3>
	<c:set var="score" value="100" />
	<c:choose>
		<c:when test="${score ge 90}">
			<span>당신의 학점은 A입니다</span>
		</c:when>
		<c:when test="${score ge 80}">
			<span>당신의 학점은 B입니다</span>
		</c:when>
		<c:when test="${score ge 70}">
			<span>당신의 학점은 C입니다</span>
		</c:when>
		<c:when test="${score ge 60}">
			<span>당신의 학점은 D입니다</span>
		</c:when>
		<c:otherwise>
			<span><span>당신의 학점은 E입니다</span></span>
		</c:otherwise>
	</c:choose>

	<h2>5. jstl 반복문</h2>
	<h3>c:forEach</h3>
	<h4>for문처럼 쓰기</h4>
	<pre>begin : 시작값, end:종료값, step:증가하는 크기</pre>
	<c:forEach var="i" begin="0" end="10" step="1">
		<c:out value="${i}"/>
	</c:forEach>
	<hr>
	<c:forEach var="i" begin="0" end="10" step="2">
		<c:out value="${i}" />
	</c:forEach>

	<h4>forEach문처럼 쓰기</h4>
	<pre>
	var : 배열 또는 리스트의 요소를 받을 변수
	items : forEach에서 탐색할 배열 또는 리스트
	varStatus : index와 count 속성을 가진 객체
				index : 현재 탐색중인 요소의 인덱스(위치)
				count : 현재 탐색중인 요소의 차례
	
	</pre>
	<c:forEach var="color" items="${jstlArr}" varStatus="status">
		<span>인덱스 : ${status.index}</span>
		<span>count : ${status.count}</span>
		<span>값 : ${color}</span>
		<hr>
	</c:forEach>

	<h4>c:forTokens : java.util.StringTokenizer와 유사한 기능</h4>
	<pre>
var : 자른 문자를 받을 변수
items : 자를 문자열
delims : 구분자
</pre>
	<ul>
		<c:forTokens var="lan" items="java html css javascript oracle"
			delims=" ">
		<li>${lan}</li>
		</c:forTokens>
	</ul>
</body>
</html>