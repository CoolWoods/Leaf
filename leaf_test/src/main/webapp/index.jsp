<%--
  Created by IntelliJ IDEA.
  User: Zongmin
  Date: 2020/9/3
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="http://localhost:6969/user/login">
        <label>
            <input type="text" name="mobile" placeholder="mobile"/>
        </label>
        <label>
            <input type="password" name="password" placeholder="password"/>
        </label>
        <input type="submit" value="submit"/>
    </form>
</body>
</html>
