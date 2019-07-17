package cn.cld.test;

import cn.cld.untils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * @author by cld
 * @date 2019/7/17  16:08
 * @description:
 */
public class changeSQL {
    public static String sub(String str,String start,String end){
        String newstr = "";
        try{
            newstr =  str.substring(str.indexOf(start)+start.length(),str.indexOf(end));

        }catch (Exception e){
            String s = sub2(str, start);
            str = s;
            newstr =  str.substring(str.indexOf(start)+start.length(),str.indexOf(end));
        }

        return newstr;
    }

    public static String sub2(String str,String start){
        String newstr = "";
        newstr =  str.substring(str.indexOf(start),str.length());
        return newstr;
    }

    public static void main(String[] args) {

//        File file = new File()
        File file = new File("C:\\myssm\\src\\main\\resources\\note\\changeSQL.txt");
        String content="";
        try {
             content = FileUtils.readFileToString(file, Charset.forName("UTF-8"));
//            System.out.println(content);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


//        String sql = "DEBUG 07-17 15:59:04 ==>  Preparing: SELECT a.dept_no, SUM(a.q4) q4, SUM(a.q1) q1, SUM(a.q2) q2, SUM(a.q3) q3, SUM(a.mq4) mq4, SUM(a.current_first) current_first, SUM(a.current_second) current_second, SUM(a.January_forecast) January_forecast, SUM(a.February_forecast) February_forecast, SUM(a.March_forecast) March_forecast, SUM(a.April_forecast) April_forecast, SUM(a.May_forecast) May_forecast, SUM(a.June_forecast) June_forecast, SUM(a.July_forecast) July_forecast, SUM(a.August_forecast) August_forecast, SUM(a.September_forecast) September_forecast, SUM(a.October_forecast) October_forecast, SUM(a.November_forecast) November_forecast, SUM(a.December_forecast) December_forecast, SUM(a.January_forecast_b) January_forecast_b, SUM(a.February_forecast_b) February_forecast_b, SUM(a.March_forecast_b) March_forecast_b, SUM(a.next_first) next_first, SUM(a.next_second) next_second, SUM(a.following_first) following_first, SUM(a.following_second) following_second, SUM(a.next_first + a.next_second) next, SUM( a.following_first + a.following_second ) following FROM ( SELECT mpr.det_type income, w.content, ms.business_domain_no dept_no, ( mpr.April_forecast + mpr.May_forecast + mpr.June_forecast ) q1, ( mpr.July_forecast + mpr.August_forecast + mpr.September_forecast ) q2, ( mpr.October_forecast + mpr.November_forecast + mpr.December_forecast ) q3, ( mpr.January_forecast + mpr.February_forecast + mpr.March_forecast ) q4, ( mpr.January_forecast_b + mpr.February_forecast_b + mpr.March_forecast_b ) mq4, ( mpr.April_forecast + mpr.May_forecast + mpr.June_forecast + mpr.July_forecast + mpr.August_forecast + mpr.September_forecast ) current_first, ( mpr.October_forecast + mpr.November_forecast + mpr.December_forecast + mpr.January_forecast + mpr.February_forecast + mpr.March_forecast ) current_second, mpr.January_forecast, mpr.February_forecast, mpr.March_forecast, mpr.October_forecast, mpr.November_forecast, mpr.December_forecast, mpr.July_forecast, mpr.August_forecast, mpr.September_forecast, mpr.April_forecast, mpr.May_forecast, mpr.June_forecast, mpr.January_forecast_b, mpr.February_forecast_b, mpr.March_forecast_b, mpr.next_first, mpr.next_second, mpr.following_first, mpr.following_second FROM m_pc_apportionment mpr INNER JOIN m_profit_loss b ON b.dept_id_cn = mpr.dept_id INNER JOIN m_word_book w ON w.word_book_no = mpr.det_type INNER JOIN m_department mdt ON mdt.id = b.dept_id_cn INNER JOIN m_sectoral_relations ms ON ms.dept_id = mdt.id where mpr.update_number = ? AND b.id IN ( ? , ? , ? , ? , ? ) ) a GROUP BY a.dept_no   (BaseJdbcLogger.java:145) \n" +
//                "DEBUG 07-17 15:59:04 ==> Parameters: 20190716-946(String), 146(Integer), 147(Integer), 156(Integer), 157(Integer), 158(Integer)  (BaseJdbcLogger.java:145)  ";

        String dosql = sub(content,"Preparing:","(BaseJdbcLogger");

        String canshu = sub(content,"Parameters:","(BaseJdbcLogge");
        String[] split1 = canshu.split(",");


        List<Object> canList = new ArrayList<>();

        for(int i=0;i<split1.length;i++){
            String type = sub(split1[i],"(",")");

            String can = split1[i].replaceAll("\\("+type+"\\)","");
            if(type.equals("Integer")){
                canList.add(Integer.parseInt(can.trim()));
            }else {
                canList.add(can.trim());
            }

        }

//        System.out.println(canList);

        for(Object param :canList){
            if(Integer.class.isInstance(param)){
                dosql = dosql.replaceFirst("\\?",param.toString().trim());
            }else {
                dosql = dosql.replaceFirst("\\?","\\'"+param.toString().trim()+"\\'");
            }

        }


        System.out.println(dosql);
    }
}
