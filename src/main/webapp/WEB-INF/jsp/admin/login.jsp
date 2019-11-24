<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员登录</title>
    <script>
        url = "/getCode";
        window.onload=function (ev) {
            document.getElementById("checkCodeImg").src = url+"?s="+((new Date).valueOf());
        }
        function changeCheckCode() {
            document.getElementById("checkCodeImg").src = url+"?s="+((new Date).valueOf());
        }
    </script>
</head>
<body>
登录
<form action="/admin/login" method="post">
    <input type="username" name="username"><br>
    <input type="password" name="password"><br>
    请输入验证码：<input type="text" name="checkCode" style="width: 80px;"/>
    <img id="checkCodeImg" alt="验证码" src="#">
    <a href="#" onclick="changeCheckCode()">换一张</a><br/>
    <input type="reset" value="重置">
    <input type="submit" value="登录">
    <div>${msg}</div>
</form>
</body>
</html>