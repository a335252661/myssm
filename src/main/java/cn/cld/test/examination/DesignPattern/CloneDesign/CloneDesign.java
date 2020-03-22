package cn.cld.test.examination.DesignPattern.CloneDesign;

/**
 * @author by cld
 * @date 2019/8/8  14:26
 * @description: 原形模式
 */
public class CloneDesign {

    public static void main(String[] args) throws Exception{
        Person person = new Person();
        person.setAge(23);

        Person per2 =  (Person)person.clone();

        System.out.println(per2.getAge());
    }
}
