package cn.cld.controller.vmView;

import org.springframework.web.servlet.view.InternalResourceView;

import java.io.File;
import java.util.Locale;

/**
 * @author 程刘德
 * @function
 * @date 2018/9/19 14:01
 */
public class VmResourceView extends InternalResourceView {
    @Override
    public boolean checkResource(Locale locale) {
        File file = new File(this.getServletContext().getRealPath("/") + getUrl());
        return file.exists();// 判断该页面是否存在
    }

}
