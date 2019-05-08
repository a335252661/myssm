package cn.cld.service.order;

import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.order.ShopOrderListVo;

public interface ShopOrderListServiceApi {

    /**
     * 查询订单list
     * @param shopOrderListVo
     * @return
     */
    PageQueryResult<ShopOrderListVo> shopOrderList(ShopOrderListVo shopOrderListVo);
}
