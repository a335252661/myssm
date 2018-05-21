<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/15
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="static/jquery-easyui-1.5.4.5/themes/default/easyui.css">
    <link rel="stylesheet" href="static/jquery-easyui-1.5.4.5/themes/icon.css">
    <link rel="stylesheet" href="static/layui/css/layui.css">
    <script src="static/jquery-easyui-1.5.4.5/jquery.min.js"></script>
    <script src="static/jquery-easyui-1.5.4.5/jquery.easyui.min.js"></script>
    <script src="static/jquery-easyui-1.5.4.5/locale/easyui-lang-zh_CN.js"></script>
    <script src="static/layui/layui.js"></script>
    <script src="static/js/index.js"></script>
</head>
<body>
<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
     <%--底部默认关闭   --%>
    <div data-options="region:'south',title:'South Title',split:true ,minimized:true"
         style="height:100px;"></div>

    <div data-options="region:'west',title:'West',split:true" style="width:200px;">
        <%--树组件--%>
        <ul id="indexTree"></ul>
    </div>

    <div data-options="region:'center'" style="padding:5px;background:#eee;">
        <div id="indexTable" data-options="fit:true" class="easyui-tabs" style="width:500px;height:250px;">
    </div>
</div>
</body>
</html>
