<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %> <%-- 내가 있는 위치에서 상대 경로로 찾아야 한다. --%>


<div class="container">
    <form>
      <input type="hidden" id="id" value="${principal.user.id}" />
      <div class="form-group">
        <label for="username">Username</label>
        <input type="username" value="${principal.user.username}" class="form-control" placeholder="Enter Username" id="username" readOnly />
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter Email" id="email">
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" placeholder="Enter Password" id="password" />
      </div>
    </form>

    <button id="btn-update" class="btn btn-primary">회원수정완료</button>
</div>

<%-- joinForm.jsp에서 발생하는 이벤트는 user.js에서 처리를 하겠다는 코드 --%>
<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>