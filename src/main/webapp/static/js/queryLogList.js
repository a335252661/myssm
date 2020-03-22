function operatorTransToStr(value, row, index) {
    if (0 == value) {
        return ' <a href="#" onclick="LinkCellClick()" style="color: rgba(0,0,0,0.59)" >处理中</a> '
    } else if (1 == value) {
        return ' <a href="#" onclick="LinkCellClick()" style="color: #ff0c05" >处理失败</a> '
    } else if (2 == value) {
        return ' <a href="#" onclick="LinkCellClick()" style="color: #3c32ff" >处理成功</a> '
    }

}

function LinkCellClick() {
    var getSelected = $('#queryLogListTable').datagrid('getSelected');
    var id = getSelected.id
    console.info(getSelected)

    $('#queryLogListDialog').dialog({'href': 'queryLogDetail?id=' + id}).dialog('open');

}

$('#queryLogListSearch').click(function () {
    console.info($("#queryLogListForm").serializeArray()); //输出数组
    var name = $("#operatorType").val();
    console.log(name);
    $('#queryLogListTable').datagrid('options').url = 'queryLogList/query';
    $('#queryLogListTable').datagrid('load', $('#queryLogListForm').serializeObject());

})

//table
// $('#queryLogListTable').datagrid({
//     url:'queryLogList/query',
//     onBeforeLoad:function(param){
//         var queryData = $('#queryLogListForm').serializeObject();
//         param = $.extend(param, queryData);
//         //清空定时器
//         destroyTimers("webLogList");
//     },
//     exOnClickLinkCell:function(rowIndex, field, value){
//         // 弹出编辑画面
//         var rows = $('#webLogListTable').datagrid('getRows');
//         var id = rows[rowIndex]['id'];
//         $('#webLogListDialog').dialog({'href' : 'fileImportLog?id=' + id}).dialog('open');
//
//     }
// });