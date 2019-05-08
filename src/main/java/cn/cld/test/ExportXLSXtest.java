package cn.cld.test;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

public class ExportXLSXtest {
    public static void main(String[] args)  throws IOException{
        //  关闭自动刷新，并且在内存中累积所有的行
        SXSSFWorkbook wb = new SXSSFWorkbook(-1);
        Sheet sheet = wb.createSheet();

        //设置列宽
        sheet.setColumnWidth(0,20*256);


        final CellStyle datacellStyle = wb.createCellStyle();
        datacellStyle.setWrapText(true);// 设置自动换行
        datacellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个上下居中格式
        datacellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中

        // 设置边框
        datacellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        datacellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        datacellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        datacellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        datacellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);



        for (int rownum = 0; rownum < 1000; rownum++) {
            Row row = sheet.createRow(rownum);
            for (int cellnum = 0; cellnum < 10; cellnum++) {
                //创建单元格
                Cell cell = row.createCell(cellnum);
                String adress = new CellReference(cell).formatAsString();
                //给单元格填充值
                cell.setCellValue(adress);
                //给单元格设置样式
                cell.setCellStyle(datacellStyle);
            }

            //  手动控制刷新多少行到磁盘
            if (rownum%10000==0){//一万行向磁盘写一次
                ((SXSSFSheet)sheet).flushRows(100);//保留最后100行并刷新所有其他行
            }
        }

        FileOutputStream fos = new FileOutputStream("C:\\temp\\sxssf3.xlsx");
        wb.write(fos);
        fos.close();

        //  删除产生的临时文件
        wb.dispose();

    }


}
