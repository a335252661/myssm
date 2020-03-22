package cn.cld.test.examination.DoSomeTest;


import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author by cld
 * @date 2019/8/21  9:13
 * @description:
 */
public class AnonymousInternal {
    public static void main(String[] args) {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };
        //接受lambda表达式的必须是一个函数式接口@FunctionalInterface
        Comparator<Integer> com2 = (x, y) -> 0;

//        "22".compareTo()

        List<Product> list = new ArrayList(10);
        for (int i = 0; i < 10; i++) {
            Product pro = new Product();
            pro.setPrice(i * 100);
            if (i == 0) {
                pro.setIncome("B00");
            }
            pro.setIncome("B11");
            list.add(pro);
        }


        Stream<Product> stream = list.stream();
        Stream<Product> productStream = stream.filter(product -> product.getPrice() > 500);
        productStream.forEach(System.out::println);


        list.stream().filter(product -> product.getPrice() > 200).forEach(System.out::println);

//        Map<String, Product> collect = list.stream().collect(Collectors.toMap(Product::getIncome, product -> product));
        Map<String, List<Product>> collect = list.stream().collect(Collectors.groupingBy(Product::getIncome));


//        Function<? super T, ? extends K> classifier = Product::getIncome;


        System.out.println("==============");
        Product product = new Product();
        Apple apple = new Apple();

        List<Apple> listpro = new ArrayList<>();
        listpro.add(apple);

//        List<Product> listpro=

        System.out.println("==============");

//        All<Product> all = new All<>(apple);
    }
}

class Product {
    private Integer price;

    private String income;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", income='" + income + '\'' +
                '}';
    }
}

class Apple extends Product{

}


class All<T>{
    private T item;

    public All(T item) {
        this.item = item;
    }
}
