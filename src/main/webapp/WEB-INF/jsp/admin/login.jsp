<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
<form action="/login" method="post">
    <input type="username"><br>
    <input type="password"><br>
    请输入验证码：<input type="text" name="checkCode" style="width: 80px;"/>
    <img id="checkCodeImg" alt="验证码" src="#">
    <a href="#" onclick="changeCheckCode()">换一张</a><br/>
    <input type="reset" value="重置">
    <input type="submit" value="登录">
</form>
</body>
</html>