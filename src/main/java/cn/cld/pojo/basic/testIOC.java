package cn.cld.pojo.basic;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


public class testIOC implements BeanNameAware {
    private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public void setBeanName(String name) {
        name="ss";
    }
}
