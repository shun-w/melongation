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
    <title>问卷列表</title>

</head>
<body>
<a href="<c:url value="/" />">返回首页</a>
<div class="paperList">
    <h1>问卷列表</h1>
    <table border="1" cellspacing="0">
        <tr>
            <th>问卷id</th>

            <th>问卷标题</th>

            <th>问卷描述</th>

            <th>是否审核</th>

            <th>创建时间</th>
        </tr>
        <c:forEach items="${papers.list}" var="paper">
            <tr>
                <td>${paper.id}</td>
                <td>${paper.title}</td>
                <td>${paper.description}</td>
                <td>${paper.isChecked}</td>
                <td><fmt:formatDate value="${paper.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

            </tr>

        </c:forEach>
    </table>
    每页${papers.pageSize}条问卷，  第${papers.pageNum}/${papers.pages}页,共${papers.total}条问卷
    <c:if test="${papers.hasPreviousPage }">
        <a href="<c:url value="/admin/getAllPaper?pageNo=${papers.pageNum-1}" />" >上一页</a>
    </c:if>
    <c:if test="${papers.hasNextPage}">
        <a href="<c:url value="/admin/getAllPaper?pageNo=${papers.pageNum+1}" />" >下一页</a>
    </c:if>
</div>
</body>
</html>