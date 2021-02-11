<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문판</title>
</head>
<body>
	<h1>메뉴 주문 결과 확인</h1>
	<hr>
	<ol>
		<c:forEach var="food" items="${orderList}">
			<li>${food.name}: ${food.price}</li>
		</c:forEach>
	</ol>
	<span> 주문하신 <c:forEach var="food" items="${orderList}"
			varStatus="status">
			<c:choose>
				<%-- 첫번째 음식명을 출력할때는 앞에 구분자(/)가 없다 --%>
				<c:when test="${status.count eq 1}">
  	${food.name}
  	</c:when>
				<c:otherwise>
  	/ ${food.name}
  	</c:otherwise>
			</c:choose>
		</c:forEach> 의 결재 금액은 ${payPrice} 입니다.
	</span>




	<h3>* 이용해 주셔서 감사합니다</h3>
</body>
</html>