<%--
  Created by IntelliJ IDEA.
  User: wangs
  Date: 2019/11/18
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>添加问卷</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div style="width: 60%;margin: auto;padding: auto">

    <div class="row">
        <h1>发布问卷</h1>
    </div>

    <form role="form" class="row" method="post">

        <label for="name">问卷标题</label>
        <input type="text" class="form-control" id="name" name="title" placeholder="请输入问卷名称">

        <label for="name">问卷描述</label>
        <textarea class="form-control" rows="10" name="description"></textarea>
        <input type="hidden" name="isChecked" value="false">
        <input type="submit">
        <br>
        <p style="color: red">${msg}</p>
    </form>
</div>
</body>
</html>
