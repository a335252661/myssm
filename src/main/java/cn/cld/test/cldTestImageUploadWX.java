package cn.cld.test;

/**
 * @author by cld
 * @date 2020/3/22  9:08
 * @description:
 */
public class cldTestImageUploadWX {

    public static void main(String[] args) {

        try{
            String mm ="curl -F media=@test.jpg https://api.weixin.qq.com/cgi-bin/media/upload?" +
                    "access_token=ACCESS_TOKEN&type=TYPE";

//        Process exec = Runtime.getRuntime().exec("cmd /c ipconfig");
            Process exec = Runtime.getRuntime().exec("cmd /c start C:\\tmp\\ppp.jpg");
        }catch (Exception e){}


    }

}
