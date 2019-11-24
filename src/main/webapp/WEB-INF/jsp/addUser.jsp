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
    <title>用户注册</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div style="width: 60%;margin: auto;padding: auto">

    <div class="row">
        <h1>用户注册</h1>
    </div>

    <form action="/register" method="post">
        用户名：<input type="username" name="username"><br>
          密码：   <input type="password" name="password"><br>
          邮箱：   <input type="text" name="email"><br>
        <input type="submit" value="注册">
        <div>${msg}</div>
    </form>
</div>
</body>
</html>
