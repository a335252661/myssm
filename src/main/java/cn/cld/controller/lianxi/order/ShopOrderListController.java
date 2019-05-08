package cn.cld.controller.lianxi.order;

import cn.cld.controller.lianxi.LianxiDemoController;
import cn.cld.mq.messageVo.TestQueueMessage;
import cn.cld.pojo.UserInfo;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.pojo.order.ShopOrderListVo;
import cn.cld.service.logs.AddLogsApi;
import cn.cld.service.order.ShopOrderListServiceApi;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("shopOrderList")
public class ShopOrderListController {

    protected static final Logger logger = org.slf4j.LoggerFactory.getLogger(ShopOrderListController.class);

    @Resource
    private ShopOrderListServiceApi shopOrderListService;

    @RequestMapping("")
    public String shopOrderList(ModelAndView mav, HttpServletRequest request){

        return "order/ShopOrderList";
    }

    @RequestMapping("query")
    @ResponseBody
    public PageQueryResult<ShopOrderListVo> query(ShopOrderListVo shopOrderListVo){

        logger.info("query 方法执行");

        PageQueryResult<ShopOrderListVo> resultUserInfo = shopOrderListService.shopOrderList(shopOrderListVo);
        return resultUserInfo;
    }

}
