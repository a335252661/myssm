$('#userDetailBtnSave').click(function () {

    var param = $('#userDetailForm').serializeObject();
    $.post('userDetail/addUser',param,function (data) {
        console.info(data);
        var result = data.result;
        if(result){
            $.messager.alert('Warning',data.message);
            $('#userListDialog').dialog('close')
        }else {
            $.messager.alert('alert',data.message);
        }
    })
})

//退出按钮点击
$('#userDetailBtnQuit').click(function () {
    $('#userListDialog').dialog('close')
})