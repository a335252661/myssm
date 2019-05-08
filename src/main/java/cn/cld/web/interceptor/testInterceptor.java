package cn.cld.web.interceptor;

import cn.cld.pojo.basic.SystemSession;
import cn.cld.untils.SessionManager;
import cn.cld.untils.constant.CommonConstants;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class testInterceptor extends HandlerInterceptorAdapter {


    /**
     * preHandle在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制等处理；
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("我是"+"preHandle");

        SystemSession session = SessionManager.getSession(request, response);
        request.setAttribute(CommonConstants.SHOP_SESSION_KEY, session);
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();

        return super.preHandle(request, response, handler);
    }

    /**
     * postHandle在业务处理器处理请求执行完成后，生成视图之前执行。
     * 后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView；
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        System.out.println("我是"+"postHandle");
    }
}
