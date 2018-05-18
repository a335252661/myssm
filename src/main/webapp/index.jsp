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
    <%--顶部--%>
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
     <%--底部默认关闭   --%>
    <div data-options="region:'south',title:'South Title',split:true ,minimized:true"
         style="height:100px;"></div>

    <div data-options="region:'west',title:'West',split:true" style="width:200px;">
        <%--树组件--%>
        <ul id="indexTree"></ul>
    </div>

    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">

        <div id="indexTable" class="easyui-tabs" style="width:500px;height:250px;">
            <div title="首页">欢迎来到Hbase价格库存查询页面</div>
            <div title="Tab1" style="padding:20px;display:none;">
                tab1
            </div>
            <div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">
                tab2
            </div>
            <div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">
                tab3
            </div>
        </div>

    </div>
</div>
</body>
</html>
