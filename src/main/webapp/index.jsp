
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
</head>
<body>
<center style="margin-top: 300px">
<form action="/login" id="loginForm" method="post">
    <div class="userName">
        <%--<input type="text" class="" value="用户："/>--%>

        <laber>用户：</laber>
        <input type="text" id="user"  class="pwd" name="user"/>
    </div>
    <div class="userPwd">
        <%--<input type="text" class="" value="密码："/>--%>
        <laber>密码：</laber>
        <input type="text" id="password"  class="pwd" name="password"/>
    </div>
    <input type="submit" value="登录">
</form>
</center>
</body>
</html>
