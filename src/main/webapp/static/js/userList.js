$(function () {


    //查询
    $('#userListSearch').click(function () {
       // var data2 = {"total":3,"rows":[{"userId":2,"userName":"chengliude","passWord":"cld7758258","isUse":1,"creatDate":1541988122000},{"userId":3,"userName":"qq1","passWord":"qq1","isUse":1,"creatDate":1542002581000},{"userId":4,"userName":"qq2","passWord":"qq2","isUse":1,"creatDate":1542002588000}]}
        var jsonDate = $("#userListForm").serializeObject(); //输出数组
        console.info(jsonDate);
        $("#userListDataTable").datagrid({
            url:'query',
            title:'hahah',
            striped:'true',//奇偶行变色
            pagination:'true',//底部显示分页栏
            rownumbers:'true',//显示带有行号的列
            checkbox:'true',
           //data:data2,
            //额外的参数，json格式
            //datagrid,加载带额外的查询参数
            queryParams: jsonDate,
            columns:[[
                {field:'userId',title:'ID',width:80,align:'center',checkbox:'true'},
                {field:'userName',title:'姓名',width:80,align:'center',editor:'text'},
                {field:'passWord',title:'用户密码',width:80,align:'center',editor:'text'},
                {field:'isUse',title:'是否有效',width:80,align:'center'},
                {field:'creatDate',title:'创建时间',width:150,align:'center',
                    formatter:function(value,row,index){
                        return DateTimeFormatter(value);}
                },
                    ]],


            onClickCell:onClickCell,
            onAfterEdit:onAfterEdit
        });

    })
    var editIndex = undefined;


    function endEditing() {//该方法用于关闭上一个焦点的editing状态
        if (editIndex == undefined) {
            return true
        }
        if ($('#userListDataTable').datagrid('validateRow', editIndex)) {
            $('#userListDataTable').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    //点击行
    function onClickCell(index,field,value) {
        if (endEditing()) {
            if(field=="userName"){
                $(this).datagrid('beginEdit', index);
                var ed = $(this).datagrid('getEditor', {index:index,field:field});
                $(ed.target).focus();
            }
            editIndex = index;
        }
       // $('#userListDataTable').datagrid('onClickRow')
    }


//单元格失去焦点执行的方法
    function onAfterEdit(index, row, changes) {
        var updated = $('#userListDataTable').datagrid('getChanges', 'updated');
        var getRows = $('#userListDataTable').datagrid('getRows');
        if (updated.length < 1) {
            editRow = undefined;
            $('#userListDataTable').datagrid('unselectAll');
            return;
        } else {
            console.info(getRows);
            var arr = [];
            //var userName = null;
            $.each(getRows,function (index) {
                var userName = getRows[index].userName;
                arr.push(userName);
            })
            var result = $.inArray(updated[0].userName, arr)
            //-1表示不存在
            if(result!=-1){
                alert("用户名和其他用户相同")
                $('#userListDataTable').datagrid('beginEdit', index);
            }

            // 传值
            //submitForm(index, row, changes);
           //console.info(updated);
           //检查不得和其他用户名相同
           //$.requestJson('delete',updated,)
        }
    }
    $('#userListUpdate').click(function () {
        var updated = $('#userListDataTable').datagrid('getChanges', 'updated');
        console.info(updated);
    })


    //新增用户
    $('#userListAdd').click(function () {
        $('#userListDialog').dialog({'href' : 'userDetail'}).dialog('open');
    })

    //删除用户
    $('#userListDelete').click(function () {
        var getChecked = $('#userListDataTable').datagrid('getChecked');
        //console.info(getChecked);
        var selectionsToJson1 = selectionsToJson(getChecked);
        console.info(selectionsToJson1);

        //封装的json请求
        $.requestJson('delete',selectionsToJson1,function (data) {
            var result = data.result;
            if(result){
                $.messager.alert('Warning',data.message);
                $("#userListDataTable").datagrid('reload');
            }else {
                $.messager.alert('alert',data.message);
            }
        })



    })

    /**
     * 选中datagrid转json
     */
    function selectionsToJson(selections) {
        var deleteObj = new Array();
        $.each(selections, function(index) {
            deleteObj.push({
                "userId" : this.userId,
                "userName" : this.userName
            });
        });
        return deleteObj;
    }
    
})