<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<!--모든 페이지에서 공통적으로 사용할 헤더 정보를 담은 헤더태그-->
<head>
<meta charset="UTF-8">
<title>음식 파는중</title>
<!--
contextPath를 context변수에 저장
ex) 이 프로젝트에서는 contextPath 가 jsp임으로 jsp가 context변수에 담긴다
-->
<c:set var = "context" value = "${pageContext.request.contextPath}"/>
</head>
