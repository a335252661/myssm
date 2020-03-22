package cn.cld.untils;

import java.io.File;

/**
 * @author by cld
 * @date 2020/3/22  14:10
 * @description:
 */
public class FileHelp {


    public static File isExistAndCreate(String fullPath){

        File file = null;
        try {
            file = new File(fullPath);
            if(!file.exists()){
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;

    }

    public static File isExistAndCreate(String dirLocation ,String fileName){
        return FileHelp.isExistAndCreate(dirLocation.concat(fileName));
    }
}
