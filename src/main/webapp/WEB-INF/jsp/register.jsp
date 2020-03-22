<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/15
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <script src="/static/layui/layui.js"></script>
</head>
<body>
<div class="layui-form-item">
    <label class="layui-form-label">短输入框</label>
    <div class="layui-input-inline">
        <input type="text" name="username" lay-verify="required" placeholder="请输入" autocomplete="off"
               class="layui-input">
    </div>
</div>

<div class="layui-inline">
    <label class="layui-form-label">日期选择</label>
    <div class="layui-input-block">
        <input type="text" name="date" id="date1" autocomplete="off" class="layui-input">
    </div>
</div>
</body>
</html>
