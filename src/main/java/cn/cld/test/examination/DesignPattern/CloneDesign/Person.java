package cn.cld.test.examination.DesignPattern.CloneDesign;

/**
 * @author by cld
 * @date 2019/8/8  14:25
 * @description:
 */
public class Person implements Cloneable{

    private int age;
    private String name ;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("克隆");
        return super.clone();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
