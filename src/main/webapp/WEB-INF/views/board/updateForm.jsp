<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %> <!-- 내가 있는 위치에서 상대 경로로 찾아야 한다. -->


<div class="container">
    <form>
      <input type="hidden" id="id" value="${board.id}"/>
      <div class="form-group">
        <label for="title">제목</label>
        <input value="${board.title}" type="text" class="form-control" placeholder="Enter title" id="title">
      </div>
      <div class="form-group">
        <label for="content">글 내용</label>
        <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
      </div>
    </form>

    <button id="btn-update" class="btn btn-primary">수정 완료</button>
</div>

<script src="/js/board.js"></script>

<script>
  $('.summernote').summernote({
    tabsize: 2,
    height: 300
  });
</script>

<%@ include file="../layout/footer.jsp" %>