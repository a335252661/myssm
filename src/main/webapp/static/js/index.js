$(function () {
    //动态树形菜单数据
    var treeData = [{
        text : "Hbase查询功能列表",
        children : [{
            text : "具体数据查询",
            children : [{
                text : "用户管理",
                attributes : {
                    url :'user/query'
                }
            }
            ]
        },{
            text : "easy联系",
            children : [
                {
                text : "面板",
                attributes : {
                    url : 'easyui/panel'
                }
            },
                {
                text : "总量统计2",
                attributes : {
                    url : ''
                }
            }
            ]
        }
        ]
    }
    ];
    $('#indexTree').tree({
        data:treeData,
        onClick: function(node){
            console.info(node);
            $('#indexTable').tabs('add', {
                title:  node.text,
                href: node.attributes.url,
                closable: true
            });
        }
    })

})