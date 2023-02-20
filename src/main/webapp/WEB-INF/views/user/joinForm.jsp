<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %> <%-- 내가 있는 위치에서 상대 경로로 찾아야 한다. --%>


<div class="container">
    <form>
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
    </form>

    <%-- form을 사용한 방법은 사용하지 않을 것이기 때문에 form 태그 밖으로 뺌 --%>
    <%-- id 값을 줘야지 user.js에서 $ 표기법으로 찾아낼 수 있음 --%>
    <button id="btn-save" class="btn btn-primary">회원가입</button>
</div>

<%-- joinForm.jsp에서 발생하는 이벤트는 user.js에서 처리를 하겠다는 코드 --%>
<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp" %>