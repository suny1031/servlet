<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>
<body>
	<h1>회원 가입 양식</h1>
	<form action="${context}/member/joinimpl" method="post">
		<table>
			<tr>
				<td>ID :</td>
				<td><input type="text" name="id" size="10" />
					<button type="button" onclick="idCheck()">아이디 확인하기</button>
					<br> <span id="confirm"></span></td>
			</tr>
			<tr>
				<td>PASSWORD :</td>
				<td><input type="password" name="pw" size="10" /></td>
			</tr>

			<tr>
				<td>휴대폰번호 :</td>
				<td><input type="text" name="tell" size="10" /></td>
			</tr>
			<tr>
				<td>email :</td>
				<td><input type="text" name="email"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="가입" />
					<input type="reset" value="취소" /></td>
			</tr>
		</table>
	</form>
</body>
</html>