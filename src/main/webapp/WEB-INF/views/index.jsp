<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp" %> <!-- 내가 있는 위치에서 상대 경로로 찾아야 한다. -->

<c:forEach var="board" items="${boards}"> <%-- BoardController에서 가져온 boards이다. --%>
    <div class="container">
        <div class="card m-2">
          <div class="card-body">
            <h4 class="card-title">${board.title}</h4> <%-- getTitle()이 호출된다. --%>
            <a href="#" class="btn btn-primary">상세보기</a>
          </div>
        </div>
    </div>
</c:forEach>

<%@ include file="layout/footer.jsp" %>