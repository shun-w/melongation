<%@ page import="java.net.URLDecoder" %>
<%@ page import="org.springframework.util.StringUtils" %>
<%@ page import="org.springframework.ui.Model" %><%--
  Created by IntelliJ IDEA.
  User: wangs
  Date: 2019/11/4
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

欢迎您，${username}
<a href="/user/logout">注销</a>
<br>
<a href="/user/addPaper">发布新问卷</a>

<br>
</body>
</html>
