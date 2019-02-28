 function operatorTransToStr(value,row,index){
    if(0==value){
        return ' <a href="#" onclick="LinkCellClick()" style="color: rgba(0,0,0,0.59)" >处理中</a> '
    }else if(1==value){
        return ' <a href="#" onclick="LinkCellClick()" style="color: #ff0c05" >处理失败</a> '
    }else if(2==value){
        return ' <a href="#" onclick="LinkCellClick()" style="color: #3c32ff" >处理成功</a> '
    }

 }

 function LinkCellClick() {
    var getSelected =  $('#queryLogListTable').datagrid('getSelected');
     var id = getSelected.id
    console.info(getSelected)

     $('#queryLogListDialog').dialog({'href' : 'queryLogDetail?id='+id}).dialog('open');

 }