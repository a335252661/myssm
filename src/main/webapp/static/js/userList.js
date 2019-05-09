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


    //导入操作
    $('#userListUplode').click(function () {
        var fileName= $('#uploadExcel').filebox('getValue');
        $('#userUploadForm').form('submit',{
            url:'userListUplode',
            success:function (data) {
                var da = JSON.parse(data);
                //console.info(data)
                $.messager.alert('Warning',da.message);
            }
        })
    })
    
    //导出操作
    $('#userListExport').click(function () {
        $.messager.confirm('Confirm','是否按照条件进行导出操作?',function(r){
            if (r){
                var jsonDate = $("#userListForm").serializeObject(); //输出数组
                console.info(jsonDate);

                $('#userListForm').submit();

            }
        });
    })


    $('#userListExportAll').tooltip({
        position: 'bottom',
        content: '<span style="color:#fff">将所有用户进行导出</span>',
        onShow: function(){
            $(this).tooltip('tip').css({
                backgroundColor: '#666',
                borderColor: '#666'
            });
        }
    });



    //打印预览
    $('#userListPrint').click(function () {
        var selections = $("#userListDataTable").datagrid('getChecked');
        if(selections.length !=1){
           $.messager.alert('Warning',"请选择一条数据");
            return;
        }
        console.info(selections)
        var userId = selections[0].userId;

        window.open('userListPrint?userId='+userId);

    })



    //txt下载
    $('#userListDownLoad').click(function () {
        // 获取选中的row
        //是一个json数组
        var selections = $('#userListDataTable').datagrid('getChecked');

        console.info(selections[0]);
        console.info({
            userId: selections[0].userId,
            userName: selections[0].userName,
            passWord: selections[0].passWord
        });
        console.info(selectionToJson(selections[0]));

        // 未选中任何信息
        if(selections == null || selections.length == 0){
            $.messager.alert('Warning','未选择信息');
            return;
        }
        if (selections.length!=1){
            $.messager.alert('Warning','请选择一条数据');
            return;
        }


        downloadSubmit({
            url:'downLoadTxt',
            queryData: {
                userId: selections[0].userId,
                userName: selections[0].userName,
                passWord: selections[0].passWord
            },
            okCallback: function(data){

            }
        });


    })

    //txt下载
    $('#userListDownLoad2').click(function () {
        // 获取选中的row
        //是一个json数组
        var selections = $('#userListDataTable').datagrid('getChecked');

        console.info(selections[0]);
        console.info({
            userId: selections[0].userId,
            userName: selections[0].userName,
            passWord: selections[0].passWord
        });
        console.info(selectionToJson(selections[0]));

        // 未选中任何信息
        if(selections == null || selections.length == 0){
            $.messager.alert('Warning','未选择信息');
            return;
        }
        if (selections.length!=1){
            $.messager.alert('Warning','请选择一条数据');
            return;
        }


        downloadSubmit({
            url:'downLoadTxt2',
            queryData: {
                userId: selections[0].userId,
                userName: selections[0].userName,
                passWord: selections[0].passWord
            },
            okCallback: function(data){

            }
        });


    })

    //发送邮件
    $('#userListsendMils').click(function () {
        //封装的json请求
        $.requestJson('sendMils',null,function (data) {
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

    /**
     * 选中datagrid转json
     */
    function selectionToJson(selection) {
        var deleteObj = new Array();
        $.each(selection, function(key,value) {
            deleteObj.push({
                key : value
            });
        });
        return deleteObj;
    }


// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
 var   option = {
        backgroundColor: '#2c343c',

        title: {
            text: 'Customized Pie',
            left: 'center',
            top: 20,
            textStyle: {
                color: '#ccc'
            }
        },

        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },

        visualMap: {
            show: true,
            min: 80,
            max: 600,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : '55%',
                center: ['50%', '50%'],
                data:[
                    {value:335, name:'直接访问'},
                    {value:310, name:'邮件营销'},
                    {value:274, name:'联盟广告'},
                    {value:235, name:'视频广告'},
                    {value:420, name:'搜索引擎'}
                ].sort(function (a, b) { return a.value - b.value; }),
                roseType: 'radius',
                label: {
                    normal: {
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        },
                        smooth: 0.2,
                        length: 10,
                        length2: 20
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#c23531',
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },

                animationType: 'scale',
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
            }
        ]
    };
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);





})