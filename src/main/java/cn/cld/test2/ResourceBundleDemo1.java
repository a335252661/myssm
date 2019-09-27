package cn.cld.test2;

import java.util.ResourceBundle;

/**
 * @author ³ÌÁõµÂ
 * @version 1.0
 * @Description TODO
 * @date 2019/9/25
 */
public class ResourceBundleDemo1 {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("system");
        String url = bundle.getString("url");
        System.out.println(url);
    }
}
