<%--
  Created by IntelliJ IDEA.
  User: GreatKe
  Date: 2019/11/21
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<html>
<head>
    <title>问卷</title>
</head>
<body>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
</script>
<a href="<c:url value="/admin/getAllPaper?pageNo=1" />" >返回问卷列表</a>

<h1>问卷信息</h1>
    <h4>问卷编号： <c:out value="${paper.id}" /><br/> </h4>
    <h4>问卷标题： <c:out value="${paper.title}" /><br/></h4>
    <h4>问卷描述：  <c:out value="${paper.description}" /><br/></h4>
　

<h4>问题列表:</h4>
<table border="1" cellspacing="0">
    <tr>
        <th>问题id</th>

        <th>题目类型</th>

        <th>问题内容</th>
    </tr>
    <c:forEach items="${paper.questionList}" var="question">
        <tr>
            <td>${question.id}</td>
            <td>
                <c:if test="${question.type == 1}">
                单选题
            </c:if>
                <c:if test="${question.type == 2}">
                多选题
            </c:if>
                <c:if test="${question.type == 3}">
                   问答题
                </c:if>
            </td>

<%--            todo--%>
<%--            <script>  var jsonData = ${question.content};--%>
<%--            var data = $.parseJSON(jsonData);--%>
<%--            </script>--%>

            <td>${question.content}</td>
        </tr>

    </c:forEach>
</table>



问卷创建时间:  <fmt:formatDate value="${paper.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br/><br/>
<a href="<c:url value="/admin/checkPaper?id=${paper.id}" />" >点此通过审核</a>

</body>
</html>
