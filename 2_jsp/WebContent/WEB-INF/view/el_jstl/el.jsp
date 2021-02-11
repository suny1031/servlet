<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<%-- 
	el 표기법 : 자바 bean의 값들을 편하게 사용하기 위해 사용하는 표기법
	자바 bean : 캡슐화, getter/setter 처리가 되어있는 객체
	request.setAttribute()
	session.setAttribute()
	ServletContext.setAttribute() 
	해당 메서드로 저장된 값들을 저장 할 때 당시 사용한 name값으로 바로 사용
	
	${} : 객체 프로퍼티에 값을 꺼낼 때 주로 사용
	#{} : 객체 프로퍼티에 값을 넣을 때 주로 사용 (x)
	
	el에서 접근 가능한 객체들 
	${requestScope} : request의 attribute(setAttribute())로 저장된 값들에 접근
	${sessionScope} : session의 attribute(setAttribute())로 저장된 값들에 접근
	${applicationScope} : application의 attribute(setAttribute())로 저장된 값들에 접근
	
	${param.파라미터이름} : 클라이언트가 요청을 보낼때 넘긴 파라미터
	${paramValues.파라미터이름} : 같은 파라미터로 여러개의 데이터를 보낸 경우 배열로 반환
	${cookie.쿠키명} : 쿠키값 조회
 --%>
	<h1>el 사용해보기</h1>
	<h2>requestScope.이름 == request.getAttribute("이름")</h2>

	<span>이름 : ${sessionScope.name}</span>
	<%-- request.getAttribute("sum") --%>
	<span>합계 : ${requestScope.sum}</span>
	<%-- requestScope를 생략하였다.
		이럴 경우 상위 스코프를 탐색하며 해당 속성명을 찾아 출력한다.
		만약 스코프에 같은 이름의 속성이 있다면,
		page에서 가까운 스코프의 속성값이 출력된다.
		pageScope, request, session, applicationScope
	 --%>
	<span>평균 : ${avg}</span>

	<h2>param.이름 == request.getParameter("이름")</h2>
	<span>국어 : ${param.kor}</span>
	<span>영어 : ${param.eng}</span>
	<span>수학 : ${param.math}</span>
	<span>코딩 : ${param.code}</span>

	<h2>cookie.이름</h2>
	<span>JSESSIONID : ${cookie.JSESSIONID.value}</span>

	<h1>el 리터럴 표현식</h1>
	<span>문자열 : ${"문자열 테스트"}</span>
	<span>문자열 : ${'test'}</span>
	<span>숫자 : ${20}</span>
	<span>실수 : ${20.5}</span>
	<span>boolean : ${true}</span>
	<%-- null이 담길 경우 "" 으로 출력 --%>
	<span>null : ${null}</span>

	<h1>el 연산자</h1>
	<pre>산술연산자, 논리연산자, 비교연산자, empty연산자, 삼항연산자</pre>
	<h2>산술 연산자</h2>
	<span> 1 + 1 : ${1+1}</span>
	<span> 1 - 1 : ${1-1}</span>
	<span> 1 * 1 : ${1*1}</span>
	<span> 2 / 1 : ${2/1}</span>
	<span> 2 % 1(mod) : ${2%1}, ${2 mod 1}</span>

	<h2>논리 연산자</h2>
	<span> true && false : ${true && false}</span>
	<span> true || false : ${true || false}</span>
	<span> !true : ${!true}</span>
	<span> true and false : ${true and false}</span>
	<span> true or false : ${true or false}</span>
	<span> not true : ${not true}</span>


	<h2>비교 연산자</h2>
	<span>1 == 2 : ${1 eq 2}</span>
	<span>1 !== 2 : ${1 != 2}</span>
	<!--ne-->
	<span>1 > 2 : ${1 gt 2}</span>
	<span>1 lt 2 : ${1 < 2}</span>
	<span>1 ge 2 : ${1 ge 2}</span>
	<!--기호를 써도 되고 문자를 써도됨-->
	<span>1 le 2 : ${1 <= 2}</span>


	<h2>삼항연산자</h2>
	<span> 1 > 2 ? "크다" : "작다" : ${1>2?"크다":"작다"} </span>

	<h2>empty 연산자</h2>
	<pre>
	값이 null이면 true
	문자, 배열, collection의 길이가 0이면 true
	이외에는 false
	</pre>
	<span> empty null : ${empty null} <!--true-->
	</span>
	<span> empty name : ${empty name}<!--session에 저장된 name 속성 false-->
	</span>


	<h2>el에서 객체 데이터 꺼내쓰기</h2>
	<h3>1. vo에서 데이터 꺼내쓰기</h3>
	<pre>
	객체에 접근한 다음 .getter메서드에서 get를 생략하고 앞글자는 소문자로 
	변형한 형태로 사용할 수 있다
	</pre>
	<span>이름 : ${sessionScope.name}</span>
	<span>국어 : ${student.kor}</span>
	<span>영어 : ${requestScope.student.eng}</span>
	<span>수학 : ${student.math}</span>
	<span>코딩 : ${requestScope.student.code}</span>
	<span>합계 : ${student.sum}</span>
	<span>평균 : ${requestScope.student.avg}</span>


	<h3>2. map에서 데이터 꺼내쓰기</h3>
	<pre>맵에 접근한 다음 .key 형태로 사용할 수 있다</pre>
	<span>이름 : ${requestScope.studentMap.name}</span>
	<span>국어 : ${studentMap.kor}</span>
	<span>영어 : ${requestScope.studentMap.eng}</span>
	<span>수학 : ${studentMap.math}</span>
	<span>코딩 : ${requestScope.studentMap.code}</span>
	<span>합계 : ${studentMap.sum}</span>
	<span>평균 : ${requestScope.studentMap.avg}</span>

 	<h3>3. list/array 에서 데이터 꺼내쓰기</h3>
	<pre>list에 접근 할 때 인덱스 형태로</pre>
	<span>이름 : ${requestScope.studentList[1].name}</span>
	<span>국어 : ${studentList[1].kor}</span>
	<span>영어 : ${requestScope.studentList[0].eng}</span>
	<span>수학 : ${studentList[1].math}</span>
	<span>코딩 : ${requestScope.studentList[0].code}</span>
	<span>합계 : ${studentList[1].sum}</span>
	<span>평균 : ${requestScope.studentList[0].avg}</span> 
	<!--지정을 하지 않아도 상위 스코프를 찾는다-->
	

</body>
</html>