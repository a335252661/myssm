package cn.cld.controller;


import cn.cld.pojo.basic.SystemSession;
import cn.cld.untils.RandomValidateCode;
import cn.cld.untils.constant.CommonConstants;
import org.apache.velocity.tools.view.servlet.VelocityViewServlet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController extends VelocityViewServlet {
    @RequestMapping("login")
    public ModelAndView userlogin(ModelAndView mav,HttpServletRequest request, Model model){
        System.out.println("登录系统++++++++++++");

        SystemSession session = getSystemSession(request);

        String captcha = request.getParameter("captcha");
        String user = request.getParameter("user");
        String password = request.getParameter("password");

        System.out.println(user);
        System.out.println(password);
        System.out.println(captcha);
        System.out.println("正确验证码： "+session.getAttribute(RandomValidateCode.RANDOMCODEKEY).toString());

        mav.setViewName("home");
        return mav;
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/captchaImage.htm")
    public void captchaImage(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("生成验证码");

        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            SystemSession session = getSystemSession(request);
            randomValidateCode.getRandcode(session, response);//输出图片方法
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    /**
     * 获取系统Session
     * @param request
     * @return
     */
    protected SystemSession getSystemSession(HttpServletRequest request){
        SystemSession session = (SystemSession)request.getAttribute(CommonConstants.SHOP_SESSION_KEY);
        return session;
    }

}
