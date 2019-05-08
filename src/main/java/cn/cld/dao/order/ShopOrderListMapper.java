package cn.cld.dao.order;


import cn.cld.pojo.order.ShopOrderListVo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ShopOrderListMapper {

    /**
     * 查询总数
     * @param shopOrderListVo
     * @return
     */
    int queryshopOrderListCount(ShopOrderListVo shopOrderListVo);

    /**
     * 查询list数据
     * @param queryVo
     * @param rowBounds
     * @return
     */
    List<ShopOrderListVo> queryshopOrderList(ShopOrderListVo queryVo, RowBounds rowBounds);
}