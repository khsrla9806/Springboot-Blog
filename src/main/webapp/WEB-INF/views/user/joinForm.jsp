<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %> <!-- 내가 있는 위치에서 상대 경로로 찾아야 한다. -->


<div class="container">
    <form action="/action_page.php">
      <div class="form-group">
        <label for="username">Username</label>
        <input type="username" class="form-control" placeholder="Enter Username" id="username">
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" placeholder="Enter Email" id="email">
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="Enter Password" id="password">
      </div>

      <button type="submit" class="btn btn-primary">회원가입</button>
    </form>
</div>

<%@ include file="../layout/footer.jsp" %>