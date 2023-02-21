<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
    <%-- principal 이라는 변수에 Session이 존재하는 Current User를 넣어서 사용할 수 있게 해줌 --%>
    <sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>My Blog</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

  <%-- body 밑쪽에 있으면 jquery를 읽어오기 전에 user.js가 실행되기 때문에 다시 일단 위로 올림 --%>
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="/">블로그</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <c:choose> <%-- jstl 을 사용한 if-else 문이다 --%>
        <c:when test="${empty principal}"> <%-- session 비어있는지 있는지 확인 --%>
          <li class="nav-item">
            <a class="nav-link" href="/auth/loginForm">로그인</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/auth/joinForm">회원가입</a>
          </li>
        </c:when>
        <c:otherwise>
          <li class="nav-item">
            <a class="nav-link" href="/board/saveForm">글쓰기</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/user/updateForm">회원정보</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/logout">로그아웃</a>
          </li>
        </c:otherwise>
      </c:choose>
    </ul>
  </div>
</nav>
<br>
