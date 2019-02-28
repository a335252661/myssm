$(function () {
    //动态树形菜单数据
    var treeData = [
        {
            "text": "商品",
            "id": "9003",
            "children": [
                {
                    "text": "商品1",
                    "id": "9003",
                    "attributes": {
                        "url": "shuoming"
                    }
                }
            ]
        },
        {
            "text": "系统",
            "id": "9003",
            "children": [
                {
                    "text": "系统1",
                    "id": "9003",
                    "attributes": {
                        "url": "shuoming"
                    }
                }
            ]
        }
    ];

    $('#indexTree').tree({
        url:"menu",
        //data:treeData,
        onLoadSuccess:function(node,data){
            console.info("目录加载成功");
        },
        onClick: function(node){
            console.info(node);

            //判断tab是否已经打开
            if($('#indexTable').tabs('exists',node.text)){
                $('#indexTable').tabs('select', node.text);//选中一个tab
            }else{
                $('#indexTable').tabs('add', {
                    title:  node.text,
                    href: node.attributes.url,
                    closable: true
                });
            }


        }
    })

})