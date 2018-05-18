$(function () {
    //动态树形菜单数据
    var treeData = [{
        text : "Hbase查询功能列表",
        children : [{
            text : "具体数据查询",
            children : [{
                text : "单一商品价格库存",
                attributes : {
                    url : '<iframe width="100%" height="100%" frameborder="0"  src="jsp/queryPriceStock.jsp" style="width:100%;height:100%;margin:0px 0px;"></iframe>'
                }
            }, {
                text : "单一商品价格库存2",
                attributes : {
                    url : ''

                }
            }
            ]
        },{
            text : "数据量查询",
            children : [{
                text : "总量统计",
                attributes : {
                    url : '<iframe width="100%" height="100%" frameborder="0"  src="jsp/queryPriceStock.jsp" style="width:100%;height:100%;margin:0px 0px;"></iframe>'
                }
            }, {
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
        //url: 'static/js/tree_data.json',
        data:treeData,
        lines:true,
        onClick: function(node){
            console.info(node);
        }
    })










})