$(function () {
    //动态树形菜单数据
    var treeData = [
        {
            "text": "商品",
            "children": [
                {
                    "text": "商品1",
                    "attributes": {
                        "url": "shuoming"
                    }
                }
            ]
        },
        {
            "text": "系统",
            "children": [
                {
                    "text": "系统1",
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
            //console.info(node);
            $('#indexTable').tabs('add', {
                title:  node.text,
                href: node.attributes.url,
                closable: true
            });
        }
    })

})