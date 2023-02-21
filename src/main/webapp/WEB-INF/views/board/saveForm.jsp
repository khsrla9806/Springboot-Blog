<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %> <!-- 내가 있는 위치에서 상대 경로로 찾아야 한다. -->


<div class="container">
    <form>
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" placeholder="Enter title" id="title">
      </div>
      <div class="form-group">
        <label for="content">Content</label>
        <textarea class="form-control summernote" rows="5" id="content"></textarea>
      </div>
    </form>

    <button id="save" class="btn btn-primary">글쓰기 완료</button>
</div>

<script src="/js/board.js"></script>

<script>
  $('.summernote').summernote({
    tabsize: 2,
    height: 300
  });
</script>

<%@ include file="../layout/footer.jsp" %>