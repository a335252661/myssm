package cn.cld.test;

import cn.cld.untils.CldCommonUntils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class cldTestScreenshot {
    private String fileName;
    private String defaultName="GuiCamera";
    static int serialNum=0;
    private String imageFormat;//图像文件的格式
    private String defaultImageFormat="jpg";
    Dimension d=Toolkit.getDefaultToolkit().getScreenSize();

    public  cldTestScreenshot(){
        fileName=defaultName;
        imageFormat=defaultImageFormat;
    }

    public cldTestScreenshot(String s,String format) {
        fileName=s;
        imageFormat=format;
    }
    /**
     * 对屏幕进行拍照
     *
     * **/
    public void snapshot(){
        try {
            //拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot=(new Robot()).createScreenCapture(
                    new Rectangle(0,0,(int)d.getWidth(),(int)d.getHeight()));
            serialNum++;
            //根据文件前缀变量和文件格式变量，自动生成文件名
            String name=fileName+String.valueOf(serialNum)+"."+imageFormat;
            System.out.println(name);
            File f=new File(name);
            System.out.println("Save File-"+name);
            //将screenshot对象写入图像文件
            ImageIO.write(screenshot, imageFormat, f);
            System.out.println("..Finished");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws Exception{

//        Process exec = Runtime.getRuntime().exec("cmd /c start C:\\tmp\\ppp.jpg");
//        Thread.sleep(2000);



//        "C:\\sally\\bootstrap栅格"是根据自己随意找的一个图片形式，"png"是图片的格式
//        cldTestScreenshot cam=new cldTestScreenshot("C:\\tmp\\测试自动截图","jpg");
//        cam.snapshot();
//
//
//
//        Robot robot=new Robot();
//        //根据指定的区域抓取屏幕的指定区域，1300是长度，800是宽度。
//        BufferedImage bi=robot.createScreenCapture(new Rectangle(1300,800));
//        //把抓取到的内容写到一个jpg文件中
//        ImageIO.write(bi, "jpg", new File("C:\\tmp\\bootstrap栅格1.png"));

//        CldCommonUntils.screenshot("C:\\tmp","ceshi" , "jpg");


    }

}
