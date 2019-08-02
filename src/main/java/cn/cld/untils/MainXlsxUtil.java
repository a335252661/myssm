package cn.cld.untils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by cld
 * @date 2019/8/1  16:41
 * @description:
 */
public class MainXlsxUtil {
    private static Log log = LogFactory.getLog(MainXlsxUtil.class);

    public static XSSFWorkbook wb;
    public static XSSFSheet currentSheet;


    public static final String CELL_VALUE = "cell_value";
    public static final String CELL_LENGTH = "cell_length";
    public static final String CELL_ALIGN = "cell_align";
    public static final String CELL_TYPE = "cell_type";
    public static final String ROW_INDEX = "row_index";
    public static final String COL_INDEX = "col_index";
    public static final String CELL_STYLE = "cell_style";


    /**
     * 初始化模板
     *
     * @param template
     * @throws InvalidFormatException
     * @throws IOException
     */
    public MainXlsxUtil(File template, List<String> sheetNames) throws InvalidFormatException, IOException {
        //XSSFWorkbook
        try {
            wb = new XSSFWorkbook(OPCPackage.open(template));
            currentSheet = wb.getSheetAt(sheetNames.size()-1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


//        if (templeteSheet != 1) {
//            for (int i = 0; i < sheetNames.size(); i++) {
//                wb.cloneSheet(templeteSheet);
//                wb.setSheetName(templeteSheet + i + 1, sheetNames.get(i));
////                new MainXlsxUtil.SheetWriter(sheetNames.get(i), wb.getSheetAt(templeteSheet + i + 1));
//            }
//            wb.removeSheetAt(templeteSheet);
//        }
    }


    /**
     * 获取单元格
     *
     * @param col
     * @param row
     * @return
     */
    public static XSSFCell getCell(String col, int row) {
        int row2 = getCellColAndRow(col, row).get(ROW_INDEX);
        int col2 = getCellColAndRow(col, row).get(COL_INDEX);
        XSSFRow xssRow = currentSheet.getRow(row2);
        if (null == xssRow) {
            xssRow = currentSheet.createRow(row2);
        }
        XSSFCell sxxCell = xssRow.getCell(col2);
        if (null == sxxCell) {
            sxxCell = xssRow.createCell(col2);
        }
        return sxxCell;
    }

    /**
     * 获取单元格
     *
     * @param col
     * @param row
     * @return
     */
    public static XSSFCell getCell(int col, int row) {
        XSSFRow workRow = currentSheet.getRow(row);
        if (workRow == null) {
            workRow = currentSheet.createRow(row);
        }
        XSSFCell cell = workRow.getCell(col);
        if (null == cell) {
            cell = workRow.createCell(col);
        }
        return cell;
    }

//    public static void writefixedCell(Map<String, Integer> total2, String value) {
//        Integer roww = total2.get(ROW_INDEX);
//        Integer colw = total2.get(COL_INDEX);
//        XSSFRow row = currentSheet.getRow(roww);
//        XSSFCell cell = row.getCell(colw);
//        cell.setCellValue(value);
//    }
//    public static void writefixedCell(String colStr,int row, String value) {
//        Integer colw = toNum(colStr);
//        XSSFRow row = currentSheet.getRow(row);
//        XSSFCell cell = row.getCell(colw);
//        cell.setCellValue(value);
//    }

    /**
     * 获取单元格行和列
     *
     * @param cols
     * @param rows
     * @return
     */
    public static Map<String, Integer> getCellColAndRow(String cols, int rows) {
        Map<String, Integer> result = new HashMap<>();
        result.put(ROW_INDEX, rows - 1);
        result.put(COL_INDEX, toNum(cols) - 1);
        return result;
    }

    /**
     * 列字母转数字
     *
     * @param colStr
     * @return
     */
    public static int toNum(String colStr) {
        int num = 0;
        int result = 0;
        int length = colStr.length();
        for (int i = 0; i < length; i++) {
            char ch = colStr.charAt(length - i - 1);
            num = (int) (ch - 'A' + 1);
            num *= Math.pow(26, i);
            result += num;
        }
        return result;
    }


    /**
     * 数字转字母
     *
     * @param columnIndex
     * @return
     */
    public static String toStr(int columnIndex) {
        if (columnIndex <= 0) {
            return null;
        }
        String columnStr = "";
        columnIndex--;
        do {
            if (columnStr.length() > 0) {
                columnIndex--;
            }
            columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr;
            columnIndex = (int) ((columnIndex - columnIndex % 26) / 26);
        } while (columnIndex > 0);
        return columnStr;
    }

    /**
     * 获取下一个字母
     *
     * @param str
     * @return
     */
    public static String nextStr(String str) {
        int i = toNum(str);
        return toStr(i + 1);
    }


    /**
     * 判断该单元格是否存在公式
     *
     * @param cell
     * @return
     */
    public static Boolean isExistFormula(XSSFCell cell) {
        Boolean isFormula = true;
        try {
            cell.getCellFormula();
        } catch (IllegalStateException e) {
            isFormula = false;
        }
        return isFormula;
    }

    public static XSSFCellStyle setAndReturnStyle() {
        XSSFWorkbook newwb = new XSSFWorkbook();
        XSSFCellStyle style = newwb.createCellStyle();
//        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THICK);
        style.setBorderLeft(CellStyle.BORDER_THIN);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        style.setFillForegroundColor(IndexedColors.ROSE.index);
        return style;
    }


    /**
     * 自动调整公式
     *
     * @param formula 公式
     * @param n       调整几行
     * @return
     */
    public static String automaticAdjustmentFormula(String formula, int n) {
        Pattern p = Pattern.compile("[A-Z]+(\\d+)");

        Matcher m = p.matcher(formula);
        List<String> matcherStr = new ArrayList<String>();
        List<String> matcherStr1 = new ArrayList<String>();
        while (m.find()) {
            matcherStr.add(m.group());
        }
        //匹配到的字符串放在list
        matcherStr1.addAll(matcherStr);

        //加2

        for (int i = 0; i < matcherStr.size(); i++) {
            int count = 0;
            String str1 = matcherStr.get(i);
            for (int j = 0; j < str1.length(); j++) {
                char a = str1.charAt(j);
                if ((a >= '0' && a <= '9')) {
                    break;
                }
                count++;
            }
            int num = Integer.parseInt(str1.substring(count)) + n;
            matcherStr.set(i, str1.substring(0, count) + num);
        }

        //替换
        for (int i = 0; i < matcherStr1.size(); i++) {
            formula = formula.replace(matcherStr1.get(i), matcherStr.get(i));
        }
        return formula;
    }

    /**
     * 获取标志位所在行和列
     *
     * @param
     */
    public static Map<String, Integer> getCellPosition(String value) {
        Map<String, Integer> result = new HashMap<>();
        //迭代器迭代Row
        Iterator<Row> it = currentSheet.iterator();
        while (it.hasNext()) {
            //迭代单元格
            Iterator<Cell> itCell = it.next().iterator();
            while (itCell.hasNext()) {
                XSSFCell cell = (XSSFCell) itCell.next();
                //得到单元格的内容
                String cellValue = cell.getCellType() == XSSFCell.CELL_TYPE_STRING ? cell.getStringCellValue() : " ";
                if (value.equals(cellValue.trim())) {
                    result.put(ROW_INDEX, cell.getRowIndex());
                    result.put(COL_INDEX, cell.getColumnIndex());
                    return result;
                }

            }
        }
        return null;
    }

    /**
     * 按照标志位 写多行数据
     *
     * @param data
     * @param replayStr
     */
    public static void writerRows(List<List<Map<String, Object>>> data, String replayStr) {
        Map map = getCellPosition(replayStr);
        if (null == map) {
            return;
        }
        int row = (Integer) map.get(ROW_INDEX);
        int col = (Integer) map.get(COL_INDEX);

        for (List<? extends Map<String, Object>> d : data) {
            writeRow(d, row, col);
            row++;
        }
    }

    /**
     * 按照单元格所在位置写多行数据
     *
     * @param data
     * @param colStr
     * @param row
     */
    public static void writerRows(List<List<Map<String, Object>>> data, String colStr, int row) {
         int col =   toNum(colStr)-1;
        row = row-1;
        for (List<? extends Map<String, Object>> d : data) {
            writeRow(d, row, col);
            row++;
        }
    }

    /**
     * 按行写数据
     *
     * @param data
     * @param row
     * @param col
     */
    public static void writeRow(List<? extends Map<String, Object>> data, int row, int col) {
        for (int i = 0; i < data.size(); i++) {
            writeCell(row, col, data.get(i));
            if (data.get(i) != null && data.get(i).get(CELL_LENGTH) != null) {
                int length = (int) data.get(i).get(CELL_LENGTH);
                //return;
                col = col + length;
            } else {
                row = row + i;
            }
        }
    }

    /**
     * 在单元格中填入值
     *
     * @param row
     * @param column
     * @param map
     */
    public static void writeCell(int row, int column, Map<String, Object> map) {
        //看单元格长度，大于一则合并单元格
        int cellLength = (int) map.get(CELL_LENGTH);
        //获取集合中的值
        Object value = (map == null || map.get(CELL_VALUE) == null) ? "" : map.get(CELL_VALUE);
        //判断单元格是否设置了样式

        XSSFCellStyle style = null;


        if (map.get(CELL_STYLE) != null) {
            XSSFCellStyle mapSetStyle = (XSSFCellStyle) map.get(CELL_STYLE);
            style = wb.createCellStyle();
            style.cloneStyleFrom(mapSetStyle);
        }
        if (cellLength > 1) {
            //合并单元格
            writerMergeCell(row, column, row, column + cellLength - 1, value, style);
        } else {
            XSSFCell cell = getCell(column, row);
            writeCellForInstance(cell, style, value);
        }
    }


    /**
     * 写合并单元格
     *
     * @param rowFrom
     * @param colFrom
     * @param rowTo
     * @param colTo
     * @param value
     * @param style
     */
    public static void writerMergeCell(int rowFrom, int colFrom, int rowTo, int colTo,
                                       Object value, XSSFCellStyle style) {
        CellRangeAddress region = new CellRangeAddress(rowFrom, rowTo, colFrom, colTo);
        currentSheet.addMergedRegion(region);
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
            XSSFRow row = currentSheet.getRow(i);
            if (null == row) {
                row = currentSheet.createRow(i);
            }
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                XSSFCell cell = row.createCell(j);
                if (style != null) {
                    cell.setCellStyle(style);
                }
            }
        }
        XSSFRow workRow = currentSheet.getRow(rowFrom);
        XSSFCell cell = workRow.getCell(colFrom);
        if (style != null) {
            cell.setCellStyle(style);
        }
        writeCellForInstance(cell, style, value);
    }

    /**
     * 按照不同的数据类型写入
     *
     * @param cell
     * @param style
     * @param value
     */
    public static void writeCellForInstance(XSSFCell cell, XSSFCellStyle style, Object value) {
        if (style != null) {
            cell.setCellStyle(style);
        }
        //不存在公式，直接写值
        if (!isExistFormula(cell)) {
            if (value != null) {
                if (String.class.isInstance(value)) {
                    cell.setCellValue(new XSSFRichTextString((String) value));
                } else if (BigDecimal.class.isInstance(value)) {
                    cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                } else if (Double.class.isInstance(value)
                        || Integer.class.isInstance(value)
                        || Long.class.isInstance(value)
                        || Float.class.isInstance(value)) {
                    cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                } else if (Date.class.isInstance(value)) {
                    cell.setCellValue((Date) value);
                } else if (Calendar.class.isInstance(value)) {
                    cell.setCellValue((Calendar) value);
                } else if (XlsxWriterUtil.Formula.class.isInstance(value)) {
                    XlsxWriterUtil.Formula formula = (XlsxWriterUtil.Formula) value;
                    cell.setCellFormula(formula.getFormulaStr());
                    cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
                } else {
                    cell.setCellValue(new XSSFRichTextString((String.valueOf(value)).trim()));
                }
            }
        }
    }


    public static void exportFilesFromBrowser(String fileName, HttpServletResponse response, XSSFWorkbook wb) {
        OutputStream out = null;
        try {
            // 第六步，将文件存到浏览器设置的下载位置
            String filename = fileName + DateTimeUtils.getNowTimeSSS() + ".xls";
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
            out = response.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            wb.write(out);// 将数据写出去
            String str = "导出" + fileName + "成功！";
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
            String str1 = "导出" + fileName + "失败！";
            System.out.println(str1);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
