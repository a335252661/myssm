package cn.cld.servlet;

import cn.cld.service.common.CommonServiceApi;
import cn.cld.untils.SpringUtils;
import cn.cld.untils.SystemConstants;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InitServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);
    @Override
    public void init(ServletConfig config) throws ServletException {

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());

        //servlet上下文
        //服务器会为每一个工程创建一个对象，这个对象就是ServletContext对象。这个对象全局唯一，
        // 而且工程内部的所有servlet都共享这个对象。所以叫全局应用程序共享对象。
        ServletContext servletContext=config.getServletContext();

        CommonServiceApi commonService = (CommonServiceApi)context.getBean("commonService");
//        commonService.readSystemAndSaveToApplication();

        //全局变量
//        WebApplicationContext webApplicationContext = (WebApplicationContext) SpringUtils.getApplicationContext();
//        ServletContext application = webApplicationContext.getServletContext();

        logger.info("InitServlet Start !!!!");

        /**
         *  将message读取成map放到session中
         */
        String contextPath = config.getServletContext().getRealPath("/");
        Map<String, String> saveRedisMap = new HashMap<String, String>();
        try {
            SAXReader reader = new SAXReader();
            Document document;

            //读xml文件
            document = reader.read(new File(contextPath,SystemConstants.MESSAGE_XML_PATH));
            Element root = document.getRootElement();

            for (Iterator<Element> i = root.elementIterator(); i.hasNext();) {
                Element element = i.next();
                String key = element.attributeValue("key");
                String value = element.attributeValue("value");
                saveRedisMap.put(key, value);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        servletContext.setAttribute(SystemConstants.MESSAGE_DATA, saveRedisMap);

    }
}
