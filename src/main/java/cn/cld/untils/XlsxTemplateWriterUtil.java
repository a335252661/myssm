package cn.cld.untils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * 2007 Excel(xlsx)写入工具类
 *
 * @author
 */
public class XlsxTemplateWriterUtil {
    private static Log log = LogFactory.getLog(XlsxReaderUtil.class);

    private XSSFWorkbook wb;

    private Map<String, SheetWriter> sheetMap = new HashMap<>(); // sheet名称， sheet 总行数

    private SheetWriter curSheetWriter; // 当前Sheet，可能有多个Sheet

    public static final String CELL_VALUE = "cell_value";
    public static final String CELL_LENGTH = "cell_length";
    public static final String CELL_ALIGN = "cell_align";
    public static final String CELL_TYPE = "cell_type";
    private static final String ROW_INDEX = "row_index";
    private static final String COL_INDEX = "col_index";

    /**
     * 初始化模板
     *
     * @param template
     * @throws InvalidFormatException
     * @throws IOException
     */
    public XlsxTemplateWriterUtil(File template, List<String> sheetNames, Integer templeteSheet) throws InvalidFormatException, IOException {
        wb = new XSSFWorkbook(OPCPackage.open(template));
        if(templeteSheet != 1){
            for (int i = 0; i < sheetNames.size(); i++) {
                wb.cloneSheet(templeteSheet);
                wb.setSheetName(templeteSheet + i + 1, sheetNames.get(i));
                new SheetWriter(sheetNames.get(i), wb.getSheetAt(templeteSheet + i + 1));
            }
            wb.removeSheetAt(templeteSheet);
        }
    }


    public void changeSheet(String sheetName) {
        SheetWriter sheetWriter = sheetMap.get(sheetName);
        if (sheetWriter == null) {
            throw new IllegalStateException(" can not find sheet " + sheetName);
        }
        this.curSheetWriter = sheetWriter;
    }

    public void selectSheet(Integer index) {
        this.curSheetWriter = new SheetWriter(wb.getSheetAt(index));
    }

    public void replaceCell(Map<String, Object> data) {
        curSheetWriter.replaceCell(data);
    }

    public void writeRows(List<List<Map<String, Object>>> data, int rowOffset, int colOffset) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(setFont(true, "宋体", (short) 11));
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.WHITE.index);
        for (List<? extends Map<String, Object>> d : data) {
            curSheetWriter.writeRow(d, rowOffset, colOffset, style);
            rowOffset++;
        }
    }

    public void writeRows(List<List<Map<String, Object>>> data, int rowOffset, int colOffset, XSSFCellStyle style) {
        for (List<? extends Map<String, Object>> d : data) {
            curSheetWriter.writeRow(d, rowOffset, colOffset, style);
            rowOffset++;

        }
    }

    public void appendRows(List<List<Object>> data, int rowOffset, int colOffset) {
        for (List<? extends Object> d : data) {
            curSheetWriter.appendRow(d, rowOffset, colOffset, null);
            rowOffset++;
        }
    }

    public Map<String, Integer> getCellPosition(String value) {
        return curSheetWriter.getCellPosition(value);
    }

    public void writeExcel(OutputStream outputStream) {
        try {
            wb.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
    }

    // sheet 对象
    private class SheetWriter {
        @SuppressWarnings("unused")
        private String sheetName;

        private int totalRow;

        private XSSFSheet sheet;

        public SheetWriter(String sheetName, XSSFSheet sheet) {
            this.sheetName = sheetName;
            this.sheet = sheet;
            sheetMap.put(sheetName, this);
            totalRow = this.sheet.getLastRowNum();
        }


        public SheetWriter(XSSFSheet sheet) {
            this.sheet = sheet;
            this.sheetName = sheet.getSheetName();
        }

        private void createRowIfNecessary(int writeRow) {
            sheet.createRow(writeRow);
        }


        public void writeRow(List<? extends Map<String, Object>> data, int rowOffset, int colOffset) {
            writeRow(data, rowOffset, colOffset, null);
        }

        public void writeRow(List<? extends Map<String, Object>> data, int rowOffset, int colOffset, XSSFCellStyle style) {
            for (int i = 0; i < data.size(); i++) {
                writeCell(rowOffset, colOffset, data.get(i), style);
                if (data.get(i) != null && data.get(i).get(CELL_LENGTH) != null) {
                    int length = (int) data.get(i).get(CELL_LENGTH);
                    //return;
                    colOffset = colOffset + length;
                } else {
                    colOffset = colOffset + i;
                }
            }
        }

        public void appendRow(List<? extends Object> data, int rowOffset, int colOffset, XSSFCellStyle style) {
            createRowIfNecessary(rowOffset);
            for (int i = 0; i < data.size(); i++) {
                writeFormatterCell(rowOffset, i + colOffset, data.get(i), style);
            }
        }


        public void shiftRows(int startRow, int rows) {
            for (int i = 0; i < rows; i++) {
                sheet.shiftRows(startRow, sheet.getLastRowNum(), -1);
                startRow--;
            }
        }

        public void writeCell(int row, int column, Map<String, Object> map, XSSFCellStyle cellStyle) {
            XSSFCellStyle style = wb.createCellStyle();
            style.cloneStyleFrom(cellStyle);
            Object value;
            value = (map == null || map.get(CELL_VALUE) == null) ? "" : map.get(CELL_VALUE);
            int cellLength = (int) map.get(CELL_LENGTH);
            if(!StringUtils.isStrNull(value.toString())){
                if (map.get(CELL_TYPE) != null) {
                    XSSFDataFormat dataFormat = wb.createDataFormat();
                    style.setDataFormat(dataFormat.getFormat(map.get(CELL_TYPE).toString()));
                }
                style.setAlignment((map.get(CELL_ALIGN) != null ? (HorizontalAlignment) map.get(CELL_ALIGN) : HorizontalAlignment.RIGHT));
            }
            if (cellLength > 1) {
                writerMergeCell(row, column, row, column + cellLength - 1, value, style);
            } else {
                XSSFRow workRow = sheet.getRow(row);
                XSSFCell cell = workRow.createCell(column);
                if (value != null) {
                    if (String.class.isInstance(value)) {
                        cell.setCellStyle(style);
                        cell.setCellValue(new XSSFRichTextString((String) value));
                    } else if (Double.class.isInstance(value)
                            || Integer.class.isInstance(value)
                            || Long.class.isInstance(value)
                            || Float.class.isInstance(value)
                            || BigDecimal.class.isInstance(value)) {
                        cell.setCellStyle(style);
                        cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                    } else if (Date.class.isInstance(value)) {
                        cell.setCellStyle(style);
                        cell.setCellValue((Date) value);
                    } else if (Calendar.class.isInstance(value)) {
                        cell.setCellValue((Calendar) value);
                    } else if (XlsxWriterUtil.Formula.class.isInstance(value)) {
                        XlsxWriterUtil.Formula formula = (XlsxWriterUtil.Formula)value;
                        cell.setCellFormula(formula.getFormulaStr());
                        cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
                    } else {
                        cell.setCellValue(new XSSFRichTextString((String.valueOf(value)).trim()));
                    }
                }
            }
        }

        public void writeCell(int row, int column, Object value, XSSFCellStyle style) {
            XSSFRow workRow = sheet.getRow(row);
            XSSFCell cell = workRow.createCell(column);
            if (style != null) {
                cell.setCellStyle(style);
            }
            writeObjectValue(cell, value);
        }

        public void writeFormatterCell(int row, int column, Object value, XSSFCellStyle cellStyle) {
            XSSFCellStyle style = wb.createCellStyle();
            XSSFRow workRow = sheet.getRow(row);
            XSSFCell cell = workRow.createCell(column);
            Map<String,Object> objectMap = (Map<String, Object>) value;
            if(HashMap.class.isInstance(value)){
                value = objectMap.get(CELL_VALUE);
                if (objectMap.get(CELL_TYPE) != null && objectMap.get(CELL_VALUE) != null) {
                    XSSFDataFormat dataFormat = wb.createDataFormat();
                    style.setDataFormat(dataFormat.getFormat(objectMap.get(CELL_TYPE).toString()));
                }
            }
            if (value != null) {
                if (String.class.isInstance(value)) {
                    cell.setCellStyle(style);
                    cell.setCellValue(new XSSFRichTextString((String) value));
                } else if (Double.class.isInstance(value)
                        || Integer.class.isInstance(value)
                        || Long.class.isInstance(value)
                        || Float.class.isInstance(value)
                        || BigDecimal.class.isInstance(value)) {
                    cell.setCellStyle(style);
                    cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                } else if (Date.class.isInstance(value)) {
                    cell.setCellStyle(style);
                    cell.setCellValue((Date) value);
                } else if (Calendar.class.isInstance(value)) {
                    cell.setCellValue((Calendar) value);
                } else if (XlsxWriterUtil.Formula.class.isInstance(value)) {
                    XlsxWriterUtil.Formula formula = (XlsxWriterUtil.Formula)value;
                    cell.setCellFormula(formula.getFormulaStr());
                    cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
                } else {
                    cell.setCellValue(new XSSFRichTextString((String.valueOf(value)).trim()));
                }
            }
        }

        /**
         * @param rowFrom
         * @param colFrom
         * @param rowTo
         * @param colTo
         * @param value
         * @param style
         */
        public void writerMergeCell(int rowFrom, int colFrom, int rowTo, int colTo,
                                    Object value, XSSFCellStyle style) {
            CellRangeAddress region = new CellRangeAddress(rowFrom, rowTo, colFrom, colTo);
            sheet.addMergedRegion(region);

            for (int i = region.getFirstRow(); i <= region.getLastRow(); i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
                    XSSFCell cell = row.createCell(j);
                    if (style != null) {
                        cell.setCellStyle(style);
                    }
                }
            }

            XSSFRow workRow = sheet.getRow(rowFrom);
            XSSFCell cell = workRow.getCell(colFrom);

            if (value != null) {
                if (String.class.isInstance(value)) {
                    cell.setCellStyle(style);
                    cell.setCellValue(new XSSFRichTextString((String) value));
                } else if (Double.class.isInstance(value)
                        || Integer.class.isInstance(value)
                        || Long.class.isInstance(value)
                        || Float.class.isInstance(value)
                        || BigDecimal.class.isInstance(value)) {
                    cell.setCellStyle(style);
                    cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                } else if (Date.class.isInstance(value)) {
                    cell.setCellStyle(style);
                    cell.setCellValue((Date) value);
                } else if (Calendar.class.isInstance(value)) {
                    cell.setCellValue((Calendar) value);
                } else if (XlsxWriterUtil.Formula.class.isInstance(value)) {
                    XlsxWriterUtil.Formula formula = (XlsxWriterUtil.Formula)value;
                    cell.setCellFormula(formula.getFormulaStr());
                    cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
                } else {
                    cell.setCellValue(new XSSFRichTextString((String.valueOf(value)).trim()));
                }
            }

        }

        private void writeObjectValue(XSSFCell cell, Object value) {
            if (value != null) {
                if (String.class.isInstance(value)) {
                    CellStyle cellStyle = cell.getCellStyle();
                    CellStyle cellStyle2 = wb.createCellStyle();
                    BeanUtils.copyProperties(cellStyle, cellStyle2);
                    cell.setCellStyle(cellStyle2);
                    cell.setCellValue(new XSSFRichTextString((String) value));
                } else if (Double.class.isInstance(value)
                        || Integer.class.isInstance(value)
                        || Long.class.isInstance(value)
                        || Float.class.isInstance(value)
                        || BigDecimal.class.isInstance(value)) {
                    CellStyle cellStyle = cell.getCellStyle();
                    CellStyle cellStyle2 = wb.createCellStyle();
                    BeanUtils.copyProperties(cellStyle, cellStyle2);
                    cell.setCellStyle(cellStyle2);
                    cell.setCellValue(Double.parseDouble(String
                            .valueOf(value)));
                } else if (Date.class.isInstance(value)) {
                    CellStyle cellStyle = cell.getCellStyle();
                    CellStyle cellStyle2 = wb.createCellStyle();
                    BeanUtils.copyProperties(cellStyle, cellStyle2);
                    cell.setCellStyle(cellStyle2);
                    cell.setCellValue((Date) value);
                } else if (Calendar.class.isInstance(value)) {
                    cell.setCellValue((Calendar) value);
                } else if (Formula.class.isInstance(value)) {
                    Formula formula = (Formula) value;
                    cell.setCellFormula(formula.getFormulaStr());
                    cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
                } else {
                    cell.setCellValue(new XSSFRichTextString((String.valueOf(value)).trim()));
                }
            }
        }


        /**
         * 替换单元格数据
         *
         * @param map
         */
        public void replaceCell(Map<String, Object> map) {
            if (map == null) {
                return;
            }
            //迭代器迭代Row
            Iterator<Row> it = sheet.iterator();
            while (it.hasNext()) {
                //迭代单元格
                Iterator<Cell> itCell = it.next().iterator();
                while (itCell.hasNext()) {
                    XSSFCell cell = (XSSFCell) itCell.next();
                    //得到单元格的内容
                    String cellValue = cell.getCellType() == XSSFCell.CELL_TYPE_STRING ? cell.getStringCellValue() : " ";
                    if (cellValue.contains("{") && cellValue.contains("}")) {
                        String value = cellValue.substring(cellValue.indexOf("{") + 1, cellValue.indexOf("}"));
                        if (map.containsKey(value.trim())) {
                            //如果单元格的内容存在查找的内容就替换查找的内容
                            cell.setCellValue(cellValue.replace("{" + value.trim() + "}", map.get(value.trim()).toString()));
                        }

                    }
                }
            }
        }

        /**
         * 替换单元格数据
         *
         * @param
         */
        public Map<String, Integer> getCellPosition(String value) {
            Map<String, Integer> result = new HashMap<>();
            //迭代器迭代Row
            Iterator<Row> it = sheet.iterator();
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

    }

    // 公式对象
    public static class Formula implements Serializable {
        private static final long serialVersionUID = 1L;
        private String formulaStr;

        public Formula() {
            super();
        }

        public Formula(String formulaStr) {
            super();
            this.formulaStr = formulaStr;
        }

        public String getFormulaStr() {
            return formulaStr;
        }

        public void setFormulaStr(String formulaStr) {
            this.formulaStr = formulaStr;
        }

    }

    public static Formula makeFormula(String formula) {
        Formula f = new Formula(formula);
        return f;
    }

    public XSSFFont setFont(boolean bold, String fontName, short size) {
        XSSFFont font = wb.createFont();
        font.setBold(bold);
        font.setFontName(fontName);
        font.setFontHeightInPoints(size);
        return font;
    }

    /**
     * 方针多salesChannel模板导出
     *
     * @param sheetNames
     * @param sheetData    replace-------->需要替换的数据
     *                     rebatePolicy--->返点明细
     *                     List<Map>
     *                     CELL_VALUE:内容
     *                     CELL_LENGTH：行宽 1
     *                     details-------->销售明细
     * @param templateFile
     */
    public static void rebateSalesChannelExcel(List<String> sheetNames, Map<String, Map<String, Object>> sheetData, String outFilePath, String templateFile) {
        File file = new File(templateFile);
        File outFile = new File(outFilePath);
        try {

            XlsxTemplateWriterUtil writer = new XlsxTemplateWriterUtil(file, sheetNames, 0);
            for (String sheetName : sheetNames) {
                if((!StringUtils.isStrNull(sheetName)) && sheetData.get(sheetName) != null){
                    writer.changeSheet(sheetName);
                    Map<String, Object> data = sheetData.get(sheetName);
                    //数据替换
                    writer.replaceCell((Map<String, Object>) data.get("replace"));
                    //
                    List<List<Map<String, Object>>> rebatePolicys = (List<List<Map<String, Object>>>) data.get("rebatePolicy");
                    Map<String, Integer> cellPosition = writer.getCellPosition("{rebatePolicy}");
                    writer.writeRows(rebatePolicys, cellPosition.get(ROW_INDEX), cellPosition.get(COL_INDEX));

                    List<List<Object>> details = (List<List<Object>>) data.get("details");
                    //明细行起始行号
                    Integer detailStartRow = 75;
                    //明细行最小起始行
                    Integer minRowNo = 16;
                    if(details!=null && details.size()>0){
                        Integer endRowIndex = rebatePolicys.size();
                        if (endRowIndex < minRowNo) {
                            endRowIndex = minRowNo;
                        }
                        Integer rows = detailStartRow - endRowIndex;
                        writer.curSheetWriter.shiftRows(75, rows);
                        Map<String, Integer> cellPosition2 = writer.getCellPosition("{detailStart}");
                        writer.appendRows(details, cellPosition2.get(ROW_INDEX), cellPosition2.get(COL_INDEX));
                    }else {
                        for (int i = 0; i < 3 ;i ++){
                            Map<String, Integer> cellPosition2 = writer.getCellPosition("{detailStart}");
                            XSSFRow row =  writer.curSheetWriter.sheet.getRow(cellPosition2.get(ROW_INDEX)-i);
                            row.setZeroHeight(true);
                        }
                    }
                }
            }
            writer.writeExcel(new FileOutputStream(outFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 方针多salesChannel模板导出
     *
     * @param sheetNames
     * @param sheetData    replace-------->需要替换的数据
     *                     rebatePolicy--->返点明细
     *                     List<Map>
     *                     CELL_VALUE:内容
     *                     CELL_LENGTH：行宽 1
     *                     details-------->销售明细
     * @param templateFile
     */
    public static void rebateMergeSalesChannelExcel(Map<String, Object> data,
                                                    List<String> sheetNames,
                                                    Map<String, List<List<Object>>> sheetData,
                                                    String outFilePath,
                                                    String templateFile) {
        File file = new File(templateFile);
        File outFile = new File(outFilePath);
        try {
            XlsxTemplateWriterUtil writer = new XlsxTemplateWriterUtil(file, sheetNames, 1);
            writer.selectSheet(0);
            //数据替换
            writer.replaceCell((Map<String, Object>) data.get("replace"));
            //
            List<List<Map<String, Object>>> rebatePolicys = (List<List<Map<String, Object>>>) data.get("rebatePolicy");

            Map<String, Integer> cellPosition = writer.getCellPosition("{rebatePolicy}");
            writer.writeRows(rebatePolicys, cellPosition.get(ROW_INDEX), cellPosition.get(COL_INDEX));
            List<String> list = new ArrayList<>();
            for (String sheetName : sheetNames) {
                if(sheetData != null && sheetData.size()>0 && !(list.contains(sheetName))) {
                    List<List<Object>> details = sheetData.get(sheetName);
                    if(details != null && details.size()>0) {
                        //明细行起始行号
                        Integer detailStartRow = 75;
                        //明细行最小起始行
                        Integer minRowNo = 16;
                        Integer endRowIndex =rebatePolicys.size();
                        if (endRowIndex < minRowNo) {
                            endRowIndex = minRowNo;
                        }
                        Integer rows = detailStartRow - endRowIndex;
                        writer.curSheetWriter.shiftRows(75, rows);
                        Map<String, Integer> cellPosition2 = writer.getCellPosition("{detailStart}");
                        writer.appendRows(details, cellPosition2.get(ROW_INDEX), cellPosition2.get(COL_INDEX));
                    }else {
                        Map<String, Integer> cellPosition2 = writer.getCellPosition("{detailStart}");
                        if (cellPosition2 != null && cellPosition2.size() > 0) {
                            for (int i = 0; i < 3; i++) {
                                XSSFRow row = writer.curSheetWriter.sheet.getRow(cellPosition2.get(ROW_INDEX) - i);
                                row.setZeroHeight(true);
                            }
                        }
                    }
                } else {
                    Map<String, Integer> cellPosition2 = writer.getCellPosition("{detailStart}");
                    if (cellPosition2 != null && cellPosition2.size() > 0) {
                        for (int i = 0; i < 3; i++) {
                            XSSFRow row = writer.curSheetWriter.sheet.getRow(cellPosition2.get(ROW_INDEX) - i);
                            row.setZeroHeight(true);
                        }
                    }

                }
                list.add(sheetName);
            }
            writer.writeExcel(new FileOutputStream(outFile));
            log.debug(outFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File file = new File("C:\\project\\ebiss\\tes\\tes-manager\\target\\tes-manager\\WEB-INF\\template\\RebateMergeSalesChannelTemplete.xlsx");
//        File file3 = new File("C:\\project\\ebiss\\tes\\tes-manager\\target\\tes-manager\\WEB-INF\\template\\RebateSalesChannelTemplete.xlsx");
        String dateStr = DateTimeUtils.getDateTimeString();
        File file2 = new File("C:\\Users\\tes\\Desktop\\11.xlsx");
//        File file4 =  new File("C:\\Users\\tes\\Desktop\\生成.xlsx");
        try {
            List<String> sheetNames = new ArrayList<>();
            sheetNames.add("111");
            sheetNames.add("222");
//            sheetNames.add("333");
//            sheetNames.add("444");
//            sheetNames.add("555");
//            sheetNames.add("666");
//            sheetNames.add("777");

            Map<String, List<List<Object>>> map = new HashMap<>();



            Map<String, Object> sheet = new HashMap<>();
            sheet.put("customerName", "客户名称");
            sheet.put("customerNo", "12345678");


            Map<String, Object> datas = new HashMap<>();
            datas.put("replace", sheet);


            List<List<Map<String, Object>>> lists = new ArrayList<>();

            Map<String, Object> data = new HashMap<>();
//            data.put(CELL_ALIGN,"LEFT");
            data.put(CELL_LENGTH,2);
            data.put(CELL_VALUE,"2018年度");

            Map<String, Object> data2 = new HashMap<>();
//            data.put(CELL_ALIGN,"CENTER");
            data2.put(CELL_LENGTH,2);
            data2.put(CELL_VALUE,"全年");

            List<Map<String, Object>> list = new ArrayList<>();
            list.add(data);
            list.add(data2);

            lists.add(list);





            List<List<Object>> lists2 = new ArrayList<>();

            List<Object> list9 = new ArrayList<>();
            list9.add(0);

            lists2.add(list9);

//            for (int i = 0; i < 10; i++) {
//                List<Map<String, Object>> list = new ArrayList<>();
//                List<Object> list2 = new ArrayList<>();
//                for (int j = 0; j < 10; j++) {
//                    Map<String, Object> data = new HashMap<>();
//                    Map<String, Object> data1 = new HashMap<>();
//                    data.put(CELL_LENGTH, 1);
//                    data1.put(CELL_VALUE,i + j);
//                    data.put(CELL_VALUE, data1);
//                    if (i + j == 3) {
//                        data.put(CELL_LENGTH, 2);
//                        j++;
//                    }
//                    list.add(data);
//                    list2.add(data.get(CELL_VALUE));
//                }
//                lists.add(list);
//                lists2.add(list2);
//
//            }

            datas.put("rebatePolicy", lists);
//            datas.put("details", lists2);

            map.put("OA", lists2);

            XlsxTemplateWriterUtil.rebateMergeSalesChannelExcel(datas, sheetNames, map, file2.getPath(), file.getPath());

//            Map<String, Map<String, Object>> data = new HashMap<>();
//            for (String sheetName : sheetNames) {
//                data.put(sheetName, datas);
//            }
//            XlsxTemplateWriterUtil.rebateSalesChannelExcel(sheetNames, data, file4.getPath(), file3.getPath());
            System.out.println("**");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
