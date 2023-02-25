<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %> <!-- 내가 있는 위치에서 상대 경로로 찾아야 한다. -->


<div class="container">
  <button class="btn btn-secondary" onclick="history.back()">돌아가기</button> <%-- history.back() 뒤로 돌아가는 함수 --%>
  <c:if test="${board.user.id == principal.user.id}">
    <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
  </c:if>
  <br/><br/>
  <div>
    글 번호: <span id="id"><i>${board.id} </i></span>
    작성자: <span><i>${board.user.username} </i></span>
  </div>
  <br/>
  <div class="form-group">
    <label for="title">제목</label>
    <h3>${board.title}</h3>
  </div>
  <hr/>
  <div class="form-group">
    <label for="content">내용</label>
    <div>
        ${board.content}
    </div>
  </div>
  <hr/>

  <div class="card">
    <div class="card-body"><textarea class="form-control" rows="1"></textarea></div>
    <div class="card-footer"><button class="btn btn-primary">등록</button></div>
  </div>
  <br />
  <div class="card">
    <div class="card-header">댓글 리스트</div>
    <ul id="comment--box" class="list-group">
      <li id="comment--1" class="list-group-item d-flex justify-content-between">
        <div>
          댓글 내용입니다.
        </div>
        <div class="d-flex">
          <div class="font-italic">작성자: 누구누구 &nbsp;</div>
          <button class="badge">삭제</button>
        </div>
      </li>
    </ul>
  </div>
</div>

<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp" %>