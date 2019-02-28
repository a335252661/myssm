package cn.cld.controller.lianxi;

import cn.cld.pojo.UserInfo;
import cn.cld.pojo.basic.MessageResult;
import cn.cld.pojo.basic.PageQueryResult;
import cn.cld.pojo.lianxi.UserInfoListVo;
import cn.cld.service.layui.LayuiBaseQueryApi;
import cn.cld.service.lianxi.LianxiDemoServiceApi;
import cn.cld.service.logs.AddLogsApi;
import cn.cld.untils.CldCommonUntils;
import cn.cld.untils.XlsxReaderUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LianxiDemoController {

    protected static final Logger logger = LoggerFactory.getLogger(LianxiDemoController.class);

    @Resource
    private LianxiDemoServiceApi lianxiDemoServiceApi;
    @Resource
    private AddLogsApi addLogsApi;

    @Autowired
    private Environment env;

//    @RequestMapping("")
//    public ModelAndView index(ModelAndView mav,HttpServletRequest request){
//        mav.setViewName("panel");
//        return  mav;
//    }

    @RequestMapping("lianxiEasyui")
    public String easyui(ModelAndView mav,HttpServletRequest request){
        return  "lianxiEasyui";
    }

    @RequestMapping("query")
    @ResponseBody
    public PageQueryResult<UserInfo> query(UserInfoListVo userInfo){
        System.out.println("开始查询");
        PageQueryResult<UserInfo> resultUserInfo = lianxiDemoServiceApi.queryUserInfo(userInfo);
        return resultUserInfo;
    }
    //delete
    @RequestMapping("delete")
    @ResponseBody
    public MessageResult delete(@RequestBody List<UserInfo> userInfoList){

        MessageResult result = lianxiDemoServiceApi.deleteUserInfo(userInfoList);
        return result;
    }

    /**
     * 添加导入功能
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "userListUplode",method=RequestMethod.POST/*,headers="Content-Type=multipart/form-data"*/)
    @ResponseBody
    public MessageResult userListUplode(@RequestParam("file") MultipartFile file,HttpServletRequest request){
        //导入功能添加日志
        int logsId = addLogsApi.insertLogs(1,"导入","用户导入");



        MessageResult result = new MessageResult();
        try{
            //文件内容
            List<Map<String,String>> myData = XlsxReaderUtil.getSheetData(file.getInputStream(),0);
             result = lianxiDemoServiceApi.userListUplode(myData , logsId);
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return result;
    }

    //userListExport
    @RequestMapping("userListExport")
    @ResponseBody
    public MessageResult userListExport(UserInfoListVo userInfoListVo,HttpServletResponse response){

        String[][] dataList = lianxiDemoServiceApi.userListExport(userInfoListVo);
        String sheetName = "用户";
        //标题
        String titleName = "用户统计";
        //下载文件名
        String fileName = "用户统计";
        //每列宽
        int[] columnWidth = { 10, 20, 30 ,40,50};
        //列名称
        String[] columnName = { "用户ID", "用户名称", "用户密码","是否有效","创建时间" };
        try{
            CldCommonUntils.ExportWithResponse2(50,37,sheetName, titleName, fileName,
                    columnWidth, columnName, dataList,response);
        }catch (Exception e){

        }

        return null;
    }

    //userListPrint
    @RequestMapping("userListPrint")
    public void userListPrint(int userId,HttpServletResponse response){
        System.out.println(userId);
       MessageResult result = lianxiDemoServiceApi.userListPrint(userId);
        UserInfo vo = (UserInfo)result.getData();
        List list = new ArrayList();
        list.add(vo);
//模板文件
        String fileName = "C:\\tmp\\userList.jasper";
        //将背景图路径传参给jasper
        Map<String, Object> param = new HashMap<String, Object>();

        //导出Vo
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(list);
        try{
            JasperPrint jasperPrint = JasperFillManager.fillReport(fileName, param, jrDataSource);
            OutputStream out = response.getOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
            exporter.exportReport();
            if(out != null){
                out.flush();
                out.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            return;
        }

    }

}
