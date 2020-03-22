package cn.cld.test.examination.NIOTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static groovy.xml.Entity.copy;

/**
 * @author by cld
 * @date 2019/8/5  10:34
 * @description:
 */
public  class NIOTest1 {
    public static void main(String[] args)throws Exception {
        NIOTest1.copyFile();
    }

    public static void copyFile()throws Exception{
        String source="E:\\Project\\myssm\\src\\main\\webapp\\WEB-INF\\template\\doTest.xlsx";
        String dest="E:\\Project\\myssm\\src\\main\\webapp\\WEB-INF\\tem\\22.xlsx";

        FileInputStream inputStream=new FileInputStream(source);
        FileOutputStream outputStream=new FileOutputStream(dest);

        //获取一个文件通道，文件以流的形式存在通道中
        FileChannel iChannel=inputStream.getChannel();
        FileChannel oChannel=outputStream.getChannel();

        //创建一个缓冲区
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        long l1=System.currentTimeMillis();
        while(true){
            buffer.clear();//pos=0,limit=capcity，作用是让ichannel从pos开始放数据
            //从通道中读取数据放到缓冲器
            int r=iChannel.read(buffer);
            if(r==-1)//到达文件末尾
                break;
            //buffer.position() 获取缓冲区中文件位置
//            buffer.limit(buffer.position());//
//            buffer.position(0);//这两步相当于 buffer.flip();

            buffer.flip();

            //从缓冲区拿0-limit 范围内的数据 写出
            oChannel.write(buffer);//它们的作用是让ochanel写入pos - limit之间的数据

        }
        inputStream.close();
        outputStream.close();
        System.out.println("complete:"+(System.currentTimeMillis()-l1));
    }

}
