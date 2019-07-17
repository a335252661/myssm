package cn.cld.test.examination;

import cn.cld.untils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by cld
 * @date 2019/7/17  11:13
 * @description:
 */
public class equra {

    private int mm = 0;

     class cld{
        public void in(){
            System.out.println(mm);
        }
    }

    public static void main(String[] args) {
String q1 = "zoooooo";
String pa = "z*";

//        Pattern compile = Pattern.compile("zo*");
//        Matcher matcher = compile.matcher(q1);
//        System.out.println(matcher.matches());

        // {n}  表示前面的字母出现n次
        System.out.println(Pattern.matches("zo{3}","zooo"));
        //  +  表示：出现一次或者多次
        System.out.println(Pattern.matches("zo+","zooo"));
        // .  匹配除换行符以外的任意字符
        System.out.println(Pattern.matches("zo..","zooo"));
        //  \w 匹配 字母，数字，下划线     不可以匹配中文
        System.out.println(Pattern.matches("zoo\\wppp\\wppp\\w","zoooppp5ppp_"));
        //  \d 匹配一个数字   + 出现一次或者多次
        System.out.println(Pattern.matches("\\d+","234"));
        System.out.println(Pattern.matches(".+([a-zA-Z])","B131(String)"));



         String pp = "B123(String)";
        System.out.println(pp.replaceAll("^([a-zA-Z])",""));


        String sql = "DEBUG 07-03 10:52:13 ==>  Preparing: SELECT distinct ms.business_domain_no dept_no, ms.business_domain dept_name, SUM(a.fourth_quarter) q4, SUM(a.first_quarter) q1, SUM(a.second_quarter) q2, SUM(a.third_quarter) q3, SUM(a.Mfourth_quarter) mq4, SUM(a.next_first) next_first, SUM(a.next_second) next_second, SUM(a.following_first) following_first, SUM(a.following_second) following_second FROM m_personnel_number a INNER JOIN m_profit_loss pl ON pl.id = a.profit_loss_id INNER JOIN m_department mdt ON mdt.id = pl.dept_id_cn INNER JOIN m_word_book wb ON wb.word_book_no = mdt.dept_classify INNER JOIN m_sectoral_relations ms ON ms.dept_id = mdt.id WHERE a.person_state = ? AND a.profit_loss_id IN ( ? , ? ) GROUP BY dept_no, dept_name   (BaseJdbcLogger.java:145) \n" +
                "DEBUG 07-03 10:52:13 ==> Parameters: B183(String), 33(Integer), 34(Integer)  (BaseJdbcLogger.java:145) ";



        String s = StringUtils.subStr(sql, "Preparing:", "(BaseJdbcLogger.java");
        System.out.println(s);


        String str ="qwertyuiopp";
        System.out.println(str.indexOf("qwe"));


        System.out.println(new StringBuilder("12345").reverse());

//        new equra().cld();

//        Integer i1 = 40;
//        Integer i2 = 40;
//        System.out.println(i1==i2);//输出TRUE

//        Integer i3 = 400;
//        Integer i4 = 400;
//        System.out.println(i3==i4);//输出false

//        int i5 = 400;
//        int i6 = 400;
//        System.out.println(i5==i6);//输出TRUE
//
//
//        System.out.println(i4==i6);//输出TRUE
    }
}
