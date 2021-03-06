package cn.cld.untils;

import java.io.*;

/**
 * @author by cld
 * @date 2020/3/22  13:58
 * @description:
 */
public class TxtWriterHelp {

//    private String
    private static BufferedWriter bufferedWriter=null;

    /**
     *
     * @param dirLocation 文件生成位置
     * @param fileName      文件名字
     * @param fileFormate  文件格式
     * @return
     */
    public static  BufferedWriter createFile(String dirLocation ,String fileName,
                                          String fileFormate) {

        fileName = fileName.concat("_"+DateTimeUtils.getDateTimeString("MMddHHmm").concat("."+fileFormate));

        File f = FileHelp.isExistAndCreate(dirLocation, fileName);

        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            OutputStream os = new FileOutputStream(f);
            writer = new OutputStreamWriter(os);
            bw = new BufferedWriter(writer);
//                bw.write(str);
//            bw.flush();
            if(f.exists()){
                f.delete();
            }
            bufferedWriter = bw;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bw;
    }

    /**
     *  写文件
     * @param msg 文件内容
     * @param writeRow 是否按行写入
     */
    public static void writeMsg(String msg , Boolean writeRow){
        try {
            if(writeRow){
                bufferedWriter.write(msg.concat("\r\n"));
            }else {
                bufferedWriter.write(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param msg
     */
    public static void writeMsg(String msg){
        TxtWriterHelp.writeMsg(msg,true);
    }

    public static void close(){
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        TxtWriterHelp.createFile("D:\\project\\allLogs\\springbootLog\\",
                "springBoot_log",
                "txt");
        TxtWriterHelp.writeMsg("哈哈哈哈哈");
        TxtWriterHelp.writeMsg("哈哈哈哈哈");
        TxtWriterHelp.writeMsg("哈哈哈哈哈");
        TxtWriterHelp.writeMsg("哈哈哈哈哈");
        TxtWriterHelp.close();
    }
}
