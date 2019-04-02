package cn.cld.web;

import cn.cld.untils.SpringUtils;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import javax.servlet.ServletContextEvent;


/**
 * 系统启动加载类。所有需要初始化的数据请放在这里。
 * @author Tommy
 *
 */
public class SystemContextListener extends ContextLoaderListener {
	/**
	 * 系统上下文统初始化
	 *
	 */
	@Override
	public void contextInitialized ( ServletContextEvent sce )
	{
		super.contextInitialized(sce);
		SpringUtils.setApplicationContext((WebApplicationContext) sce.getServletContext().
				getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
		
	}
	
	/**
	 * 系统上下文销毁
	 *
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
