<%--
  Created by IntelliJ IDEA.
  User: 童材
  Date: 2019/11/24
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
    <title>我的问卷</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
<body>
<a href="<c:url value="/user" />">返回首页</a>
<div class="paperList">

    <h1>问卷列表</h1>
    <table border="1" cellspacing="0">
        <tr>
            <th>问卷id</th>

            <th>问卷标题</th>

            <th>问卷描述</th>

            <th>是否审核</th>

            <th>创建时间</th>

            <th>查看问卷</th>
        </tr>
        <c:forEach items="${papers.list}" var="paper">
            <tr>
                <td>${paper.id}</td>
                <td>${paper.title}</td>
                <td>${paper.description}</td>
                <td>
                    <c:if test="${paper.isChecked == true}">
                        已审核
                    </c:if>
                    <c:if test="${paper.isChecked== false}">
                        未审核
                    </c:if>
                </td>
                <td><fmt:formatDate value="${paper.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                <td>
                    <a href="<c:url value="/user/myOnePaper?id=${paper.id}" />" >点击查看</a>
                </td>
            </tr>

        </c:forEach>
    </table>
    每页${papers.pageSize}条问卷，  第${papers.pageNum}/${papers.pages}页,共${papers.total}条问卷
    <c:if test="${papers.hasPreviousPage }">
        <a href="<c:url value="/user/myPapers?pageNo=${papers.pageNum-1}" />" >上一页</a>
    </c:if>
    <c:if test="${papers.hasNextPage}">
        <a href="<c:url value="/user/myPapers?pageNo=${papers.pageNum+1}" />" >下一页</a>
    </c:if>
</div>

</body>
</html>
