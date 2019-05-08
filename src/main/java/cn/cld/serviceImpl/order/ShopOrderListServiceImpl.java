package cn.cld.serviceImpl.order;

import cn.cld.dao.MyUserMapper;
import cn.cld.dao.order.ShopOrderListMapper;
import cn.cld.pojo.UserInfo;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.pojo.order.ShopOrderListVo;
import cn.cld.service.order.ShopOrderListServiceApi;
import org.apache.ibatis.session.RowBounds;

import javax.annotation.Resource;
import java.util.List;

public class ShopOrderListServiceImpl implements ShopOrderListServiceApi {

    @Resource
    private ShopOrderListMapper shopOrderListMapper;

    @Resource
    private MyUserMapper myUserMapper;
    /**
     * 查询订单list
     * @param queryVo
     * @return
     */
    @Override
    public PageQueryResult<ShopOrderListVo> shopOrderList(ShopOrderListVo queryVo) {
        // 总数
        int total = shopOrderListMapper.queryshopOrderListCount(queryVo);
        // 分页数据
        RowBounds rowBounds = new RowBounds((queryVo.getPage() - 1) * queryVo.getRows(), queryVo.getRows());
        List<ShopOrderListVo> queryResult = shopOrderListMapper.queryshopOrderList(queryVo, rowBounds);
        return new PageQueryResult<ShopOrderListVo>(total, queryResult);
    }
}
