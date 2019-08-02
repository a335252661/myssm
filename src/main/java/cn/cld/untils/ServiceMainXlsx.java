package cn.cld.untils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author by cld
 * @date 2019/8/1  16:44
 * @description:
 */
public class ServiceMainXlsx extends MainXlsxUtil {
//    public XSSFWorkbook wb;
//    public XSSFSheet currentSheet;
    /**
     * 初始化模板
     *
     * @param template
     * @param sheetNames
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ServiceMainXlsx(File template, List<String> sheetNames) throws InvalidFormatException, IOException {
        super(template, sheetNames);
    }

    public static XSSFWorkbook doTest(List<List<Map<String, Object>>> data,
                                      String fileLocation) {
        File file = new File(fileLocation);
        ServiceMainXlsx writer = null;
        try {
            writer = new ServiceMainXlsx(file, Arrays.asList("mm"));

            XSSFCell cell = getCell("F", 20);
            System.out.println(cell.getStringCellValue());

            int row2 = getCellColAndRow("F", 20).get(ROW_INDEX);
            int col2 = getCellColAndRow("F", 20).get(COL_INDEX);

            System.out.println(row2+"===="+col2);

           int row3 =  getCellPosition("{mydata}").get(ROW_INDEX);
           int col3 =  getCellPosition("{mydata}").get(COL_INDEX);

            System.out.println(row3+"===="+col3);

//            cell.setCellValue("哈哈");
            writerRows(data,"F",20);
//            writerRows(data,"{mydata}");

//            wb.setSheetName(0,"hahaha");
            String sheetName = wb.getSheetName(0);
            System.out.println("sheetName==="+sheetName);

            wb.setForceFormulaRecalculation(true);

        }catch (Exception e){
         e.printStackTrace();
        }

        return wb;
    }
}
