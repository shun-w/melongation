<%--
  Created by IntelliJ IDEA.
  User: GreatKe
  Date: 2019/11/21
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>全部回答</title>

</head>
<body>
<a href="<c:url value="/" />">返回首页</a>
<div class="answerList">
    <h1>全部回答</h1>
    <b>${tTitle}——问题${tid}</b>
    <c:if test="${not empty answerList}">
    <table border="1" cellspacing="0">
        <tr>
            <th>回答</th>
        </tr>
        <c:forEach items="${answerList}" var="Answer">
            <tr>
                <td>${Answer.answer}</td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
  <c:if test="${ empty answerList}">
      <h1>暂无回答，请耐心等待</h1>
  </c:if>
</div>
</body>
</html>
