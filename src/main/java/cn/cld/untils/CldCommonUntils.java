package cn.cld.untils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public  class CldCommonUntils {

    /**
     * 在c盘文件夹中生成二维码
     * @param content
     * @return
     */
    public static Boolean makeQrCode(String content){
        String location = "C:\\QrCode";
        File file = new File(location);
        if(!file.exists()){
            file.mkdir();
        }
        return makeQrCode(content,location);
    }

    /**
     * 指定生成二维码位置
     * @param content
     * @param location
     * @return
     */
    public static Boolean makeQrCode(String content,String location ){
        int width=300;
        int height=300;
        return makeQrCode(width,height,content,location);
    }

    /**
     * 指定生成二维码位置以及大小
     * @param width
     * @param height
     * @param content
     * @param location
     * @return
     */
    public static Boolean makeQrCode(int width,int height,String content,String location ){
        //二维码格式
        String format = "jpg";
        //定义二维码参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN,2);
        //生成二维码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            Path file = new File(location+"\\img."+format).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 从大集合中取等量数据
     * @param list  大集合
     * @param num   每次取的个数
     * @return
     */
    public static List<List> subList(List list , int num){
        List<List> returnList =new ArrayList();
        for(int i=0;i<list.size();i=i+num){
            //最后一次截取集合
            if(i+num>list.size()){
                num = list.size()-i;
            }
            List newList = list.subList(i, i + num);
            returnList.add(newList);
        }
        return returnList;
    }

/**********************************************处理时间格式**********************************************************************/

    public static String yyyy_MM_dd_HH_mm_ss= "yyyy-MM-dd HH:mm:ss";
    public static String yyyy_MM_dd= "yyyy-MM-dd";

    /**
     * 格式化，yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dataToString(Date date) {
        return dataToString(date, yyyy_MM_dd_HH_mm_ss);
    }

    /**
     * 格式化
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dataToString(Date date, String pattern) {
        if (date == null || pattern == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        String result = df.format(date);
        if (result.equalsIgnoreCase("0001-01-01 00:00:00")) {
            result = "";
        }
        return result;
    }

}
