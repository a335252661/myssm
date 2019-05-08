package cn.cld.test;

import cn.cld.untils.DateTimeUtils;
import cn.cld.untils.FTPUtil;
import cn.cld.untils.PropertyUtils;
import cn.cld.untils.fastdfs.FastDFSClient;
import cn.cld.untils.fastdfs.FastDFSUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;


public class cldTest2 {
    private static final Logger logger = LoggerFactory.getLogger(cldTest2.class);
    public static void main(String[] args) {

        Date beg1 = new Date(System.currentTimeMillis());

        //度本地文件
        File file = new File("C:\\temp\\PurchaseOrderResult1904261529.txt");

        int filelen = 0;
        //创建byte字节数组
        byte[] strbuff = null;
        InputStream in = null;
        try{
            in= new FileInputStream(file);
            filelen=(int)file.length();
            strbuff = new byte[filelen];
            int read = in.read(strbuff, 0, filelen);
            System.out.println(read);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            try{
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        String data = new String(strbuff);
        System.out.println(data);
        Date end1 = new Date(System.currentTimeMillis());
        logger.info("读文件用时："+DateTimeUtils.getTimeDifference(end1,beg1));

        JSONArray arr = JSONArray.parseArray(data);
        JSONArray dataList  = arr.getJSONArray(0);

        String key = "";
        List<String> listkey = new ArrayList<String>();
        for(int i=0 ; i<dataList.size() ; i++) {


            JSONObject jsonObject = dataList.getJSONObject(i);
            String CenterCode = jsonObject.get("CenterCode") + "";
            String DenpyouNo = jsonObject.get("DenpyouNo") + "";
            String OrderCode = jsonObject.get("OrderCode") + "";
            key = CenterCode+"-"+DenpyouNo+"-"+OrderCode;
            listkey.add(key);

        }

        Set<String> set = new HashSet<>();
        List<String> newList = new ArrayList<>();

        for(String mm :listkey){
            if(!set.add(mm)){
                newList.add(mm);
            }
        }
        System.out.println(newList);


    }
}
