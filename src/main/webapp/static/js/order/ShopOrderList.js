


// table初始化
// $('#ShopOrderListTable').datagrid({
//     url:'shopOrderList/query',
//     exOnClickLinkCell:function(rowIndex, field, value){
//         // 弹出详细画面
//         $('#impForecastListDialog').dialog('open').dialog('refresh', 'impForecastDetail?forecastNo=' + value);
//     },
//     onBeforeLoad:function(param){
//         console.info(param);
//         var queryData = $('#ShopOrderListForm').serializeObject();
//         param = $.extend(param, queryData);
//         console.info(param);
//     }
// });

$("#ShopOrderListTable").datagrid({
    url:'shopOrderList/query',
    // title:'hahah',
    striped:'true',//奇偶行变色
    pagination:'true',//底部显示分页栏
    rownumbers:'true',//显示带有行号的列
    checkbox:'true',
    //datagrid,加载带额外的查询参数
    // queryParams: $("#ShopOrderListForm").serializeObject(),
     onBeforeLoad:function(param){
        var queryData = $('#ShopOrderListForm').serializeObject();
        param = $.extend(param, queryData);
    }

    //表头名称，现在在vm里面已经存在，不需要再加载
    // columns:[[
    //     {field:'userId',title:'ID',width:80,align:'center',checkbox:'true'},
    //     {field:'userName',title:'姓名',width:80,align:'center',editor:'text'},
    //     {field:'passWord',title:'用户密码',width:80,align:'center',editor:'text'},
    //     {field:'isUse',title:'是否有效',width:80,align:'center'},
    //     {field:'creatDate',title:'创建时间',width:150,align:'center',
    //         formatter:function(value,row,index){
    //             return DateTimeFormatter(value);}
    //     },
    // ]],


    // onClickCell:onClickCell,
    // onAfterEdit:onAfterEdit
});

//查询

$('#ShopOrderListSearch').click(function () {
    $("#ShopOrderListTable").datagrid("load");
})