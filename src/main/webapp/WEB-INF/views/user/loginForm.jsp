<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %> <%-- 내가 있는 위치에서 상대 경로로 찾아야 한다. --%>


<div class="container">
    <form action="#" method="post">
      <div class="form-group">
        <label for="username">Username</label>
        <input name="username" type="username" class="form-control" placeholder="Enter Username" id="username">
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input name="password" type="password" class="form-control" placeholder="Enter Password" id="password">
      </div>

      <div class="form-group form-check">
        <label class="form-check-label">
          <input name="remember" class="form-check-input" type="checkbox"> Remember me
        </label>
      </div>

      <button id="btn-login" class="btn btn-primary">로그인</button>
    </form>
</div>

<%-- <script src="/js/user.js"></script> 스프링 시큐리티에서는 사용하지 않음 --%>

<%@ include file="../layout/footer.jsp" %>