<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/21
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/jquery-easyui-1.5.4.5/themes/default/easyui.css">
    <link rel="stylesheet" href="/static/jquery-easyui-1.5.4.5/themes/icon.css">
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <script src="/static/jquery-easyui-1.5.4.5/jquery.min.js"></script>
    <script src="/static/jquery-easyui-1.5.4.5/jquery.easyui.min.js"></script>
    <script src="/static/jquery-easyui-1.5.4.5/locale/easyui-lang-zh_CN.js"></script>
    <script src="/static/layui/layui.js"></script>
</head>
<body>


    <div class="easyui-panel" title="基础功能">
         <a href="#" style="margin: 10px" id="userListAdd" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
        <a href="#" style="margin: 10px"  id="userListSearch" class="easyui-linkbutton" data-options="iconCls:'icon-search' ">查询</a>
        <a href="#" style="margin: 10px" class="easyui-linkbutton " data-options="iconCls:'icon-remove' ">删除</a>
        <a href="#" style="margin: 10px" class="easyui-linkbutton" data-options="iconCls:'icon-redo' ">重置</a>
        <a href="#" style="margin: 10px" class="easyui-linkbutton" data-options="iconCls:'icon-clear' ">关闭</a>
    </div>

    <div class="easyui-panel" title="查询条件">
        <form action="" id="userListForm">

           <div class="" style="padding: 13px ;float: left">
               <lable class="">用户姓名：</lable>
               <input type="text" name="username" placeholder="请输入" class="easyui-textbox">
           </div>
            <div class="" style="padding: 13px;float: left">
                <lable class="">用户编号：</lable>
                <input type="text" name="userid"placeholder="请输入" class="easyui-textbox">
            </div>
            <div class="" style="padding: 13px;float: left">
                <lable class="">注册时间范围：</lable>
                <input type="text" name="username" class="easyui-datetimebox">
            </div>
            <div class="" style="padding: 13px;float: left">
                <lable class="">~~：</lable>
                <input type="text" name="username"class="easyui-datetimebox">
            </div>
            <div class="" style="padding: 13px ;float: left">
                <lable class="">是否有效：</lable>
                <select name="" id="" class="easyui-combobox" data-options="width:100,panelHeight:60">
                    <option value=""> </option>
                    <option value="">有效</option>
                    <option value="">无效</option>
                </select>
            </div>

        </form>
    </div>

    <div class="easyui-datagrid" title="用户列表" style="height: 300px" id="userListData"
         data-options=""></div>

    <script src="/static/js/userList.js"></script>
</body>
</html>
