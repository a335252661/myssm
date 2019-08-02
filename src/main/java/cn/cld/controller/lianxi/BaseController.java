package cn.cld.controller.lianxi;

import cn.cld.pojo.basic.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author by cld
 * @date 2019/8/1  16:13
 * @description:
 */
public class BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 获取临时目录路径，并且不存在时将创建
     *
     * @param request
     * @return
     */
    protected String getTempDirPathAndMkIfNotExitsts(HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/template");
//		String path = "C:\\Users\\tes\\Desktop\\batch入库实绩sql";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }


    /**
     * 下载文件：根据要写入的数据，生成临时文件保存在服务器端，然后通过流写入客户端
     * @param file 文件
     * @param response
     * @return
     */
    protected JsonResult download (File file, HttpServletResponse response, HttpServletRequest request){
        try {
            logger.info(file.getAbsolutePath());
            // 设置数据类型
            //response.setContentType("application/x-javascript;charset=gb2312");
            // 设置头部
            String fileName = new String(file.getName().getBytes("gb2312"),"ISO8859_1");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName +"\"");
            // 输入流
            BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
            // 字节数组
            byte[] buf = new byte[1024];
            int len = 0;
            // 输出流把文件内容写到客户端
            OutputStream out = response.getOutputStream();
            // 读取数据
            while((len = br.read(buf)) >0)
                out.write(buf,0,len);
            // 关闭输入流
            br.close();
            // 关闭输出流
            out.close();
        } catch (IOException e) {
            logger.error("下载异常 ----", e);
            // 导出失败：返回
        }
        // 导出成功：返回
        JsonResult result = new JsonResult();
        result.setOk(true);
        return result;
    }

}
