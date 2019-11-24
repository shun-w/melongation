<%--
  Created by IntelliJ IDEA.
  User: GreatKe
  Date: 2019/11/21
  Time: 21:13
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
    <title>问卷</title>
    　

    <script>

        window.onload = function (ev) {
            setTimeout(function () {
                console.log("开始");

                var elements = document.getElementsByName("aa");
                for (var index = 0; index < elements.length; index++) {
                    var obj = JSON.parse(elements[index].innerHTML);
                    var content = "";
                    for (var i in obj) {
                        content = content + i + " <br>";

                        for (var j = 0; j < obj[i].length; j++) {

                            for (var k in obj[i][j]) {
                                content = content + "<br>" + k + ". " + obj[i][j][k] + "<br>";
                            }
                        }

                    }
                    console.log(index + "为" + content);
                    elements[index].innerHTML = content;
                }

            }, 2000)


        }
    </script>
</head>
<body>

<a href="<c:url value="/admin/getAllPaper?pageNo=1" />">返回问卷列表</a>

<h1>问卷信息</h1>
<h4 style="display: inline">问卷编号：</h4> <c:out value="${paper.id}"/><br/>
<h4 style="display: inline">问卷标题： </h4><c:out value="${paper.title}"/><br/>
<h4 style="display: inline">问卷描述： </h4><c:out value="${paper.description}"/><br/>

<h4>问题列表:</h4>
<table border="1" cellspacing="0">
    <tr>
        <th>问题id</th>

        <th>题目类型</th>

        <th>问题内容</th>

        <th>答题状况</th>
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


            <td>
                <c:if test="${question.type == 3}">
                    <div name="bb" style="">${question.content}</div>
                </c:if>
                <c:if test="${question.type != 3}">
                    <div name="aa">${question.content}</div>
                </c:if>


            </td>

            <td>
                <c:if test="${question.type == 3}">

                    <a href="paperAnalyze?tid=${question.id}&tTitle=${paper.title}">查看所有回答</a>
                </c:if>
                <c:if test="${question.type != 3}">
                    <a href="selectDetail?tid=${question.id}&tTitle=${paper.title}">查看选项分布</a>
                </c:if>
            </td>

        </tr>

    </c:forEach>


</table>


<h4 style="display: inline">问卷创建时间: </h4><fmt:formatDate value="${paper.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br/><br/>

</body>
</html>
