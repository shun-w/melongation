<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GreatKe
  Date: 2019/11/24
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户个人信息修改</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div style="width: 60%;margin: auto;padding: auto">
<%--    <div class="row">--%>
<%--        <h2>当前个人信息</h2>--%>
<%--    </div>--%>

<%--    用户名：<c:out value="${user.username}" /><br/><br/>--%>
<%--    密码：<c:out value="${user.password}" /><br/><br/>--%>
<%--    邮箱：<c:out value="${user.email}" /><br/><br/>--%>
    <div class="row">
        <h2>修改个人信息</h2>
    </div>

    <form action="/user/update" method="post">
        用户名：<input type="username" name="username" value="${user.username}"><br>
         密码：   <input type="password" name="password" value="${user.password}"><br>
         邮箱：   <input type="text" name="email" value="${user.email}"><br>
        <br>
        <input type="submit" value="确认修改"><br>
        <div style="color: red">${msg}</div>
    </form>

</div>
</body>
</html>
