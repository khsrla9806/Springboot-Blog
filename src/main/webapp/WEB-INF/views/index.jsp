<%@ page language = "java" contentType = "text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp" %> <!-- 내가 있는 위치에서 상대 경로로 찾아야 한다. -->

<div class="container">
    <c:forEach var="board" items="${boards.content}"> <%-- BoardController에서 가져온 boards이다. --%>
        <div class="card m-2">
          <div class="card-body">
            <h4 class="card-title">${board.title}</h4> <%-- getTitle()이 호출된다. --%>
            <a href="#" class="btn btn-primary">상세보기</a>
          </div>
        </div>
    </c:forEach>

    <ul class="pagination justify-content-center"> <%-- bootstrap에서 가운데 정렬 시 문법 --%>
      <c:choose>
        <c:when test="${boards.first}"> <%-- 첫 번째 페이지라면 true --%>
            <li class="page-item disabled"><a class="page-link" href="?page=${boards.number - 1}">Previous</a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item"><a class="page-link" href="?page=${boards.number - 1}">Previous</a></li>
        </c:otherwise>
      </c:choose>

      <c:choose>
        <c:when test="${boards.last}"> <%-- 마지막 페이지라면 true --%>
            <li class="page-item disabled"><a class="page-link" href="?page=${boards.number + 1}">Previous</a></li>
        </c:when>
        <c:otherwise>
            <li class="page-item"><a class="page-link" href="?page=${boards.number + 1}">Previous</a></li>
        </c:otherwise>
      </c:choose>
    </ul>
</div>

<%@ include file="layout/footer.jsp" %>