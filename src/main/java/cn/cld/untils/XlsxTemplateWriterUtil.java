package cn.cld.untils;

//import com.tes.common.constants.SystemConstants;
//import com.tes.common.constants.WordBookConstant;

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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (templeteSheet != 1) {
            for (int i = 0; i < sheetNames.size(); i++) {
                wb.cloneSheet(templeteSheet);
                wb.setSheetName(templeteSheet + i + 1, sheetNames.get(i));
                new SheetWriter(sheetNames.get(i), wb.getSheetAt(templeteSheet + i + 1));
            }
            wb.removeSheetAt(templeteSheet);
        }
    }

    public static void writeAndExport(List<List<Map<String, Object>>> data, String fileLocation) {
        File file = new File(fileLocation);
        XlsxTemplateWriterUtil writer = null;
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList("mm"), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;
            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);

            //在F20位置开始写数据



        } catch (Exception e) {
          e.printStackTrace();
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

    public void replaceCell2(Map<String, Object> data) {
        new SheetWriter(wb.getSheetAt(0)).replaceCell(data);
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

    public void writeRowsWiththousand(List<List<Map<String, Object>>> data, int rowOffset, int colOffset) {
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
            curSheetWriter.writeRowWiththousand(d, rowOffset, colOffset, style);
            rowOffset++;
        }
    }

    public void writeRowsWiththousand2(XSSFSheet curretSheet, List<List<Map<String, Object>>> data, int rowOffset, int colOffset) {
        for (List<? extends Map<String, Object>> d : data) {
            curSheetWriter.writeRowWiththousand2(curretSheet, d, rowOffset, colOffset);
            rowOffset++;
        }
    }

    public void writeRows2(XSSFSheet curretSheet, List<List<Map<String, Object>>> data, int rowOffset, int colOffset) {
        for (List<? extends Map<String, Object>> d : data) {
            curSheetWriter.writeRow2(curretSheet, d, rowOffset, colOffset);
            rowOffset++;
        }
    }


    public void writeRowsWithmillion(List<List<Map<String, Object>>> data, int rowOffset, int colOffset) {
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
            curSheetWriter.writeRowWithmillion(d, rowOffset, colOffset, style);
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

        public void writeRowWiththousand(List<? extends Map<String, Object>> data, int rowOffset, int colOffset, XSSFCellStyle style) {
            for (int i = 0; i < data.size(); i++) {
                writeCellWiththousand(rowOffset, colOffset, data.get(i), style);
                if (data.get(i) != null && data.get(i).get(CELL_LENGTH) != null) {
                    int length = (int) data.get(i).get(CELL_LENGTH);
                    //return;
                    colOffset = colOffset + length;
                } else {
                    colOffset = colOffset + i;
                }
            }
        }

        public void writeRowWiththousand2(XSSFSheet curretSheet, List<? extends Map<String, Object>> data, int rowOffset, int colOffset) {
            for (int i = 0; i < data.size(); i++) {
                XSSFRow row = curretSheet.getRow(rowOffset);
                if (null == row) {
                    row = curretSheet.createRow(rowOffset);
                }
                XSSFCell cell = row.getCell(colOffset);
                if (null == cell) {
                    cell = row.createCell(colOffset);
                }
                XSSFCellStyle style2 = cell.getCellStyle();
                writeCellWiththousand(rowOffset, colOffset, data.get(i), style2);
                if (data.get(i) != null && data.get(i).get(CELL_LENGTH) != null) {
                    int length = (int) data.get(i).get(CELL_LENGTH);
                    //return;
                    colOffset = colOffset + length;
                } else {
                    colOffset = colOffset + i;
                }
            }
        }

        public void writeRow2(XSSFSheet curretSheet, List<? extends Map<String, Object>> data, int rowOffset, int colOffset) {
            for (int i = 0; i < data.size(); i++) {
                XSSFRow row = curretSheet.getRow(rowOffset);
                XSSFCell cell = row.getCell(colOffset);
                if (null == cell) {
                    cell = row.createCell(colOffset);
                }
                XSSFCellStyle style2 = cell.getCellStyle();
                writeCell(rowOffset, colOffset, data.get(i), style2);
                if (data.get(i) != null && data.get(i).get(CELL_LENGTH) != null) {
                    int length = (int) data.get(i).get(CELL_LENGTH);
                    //return;
                    colOffset = colOffset + length;
                } else {
                    colOffset = colOffset + i;
                }
            }
        }

        public void writeRowWithmillion(List<? extends Map<String, Object>> data, int rowOffset, int colOffset, XSSFCellStyle style) {
            for (int i = 0; i < data.size(); i++) {
                writeCellWithmillion(rowOffset, colOffset, data.get(i), style);
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
            if (!StringUtils.isStrNull(value.toString())) {
                if (map.get(CELL_TYPE) != null) {
                    XSSFDataFormat dataFormat = wb.createDataFormat();
//                    style.setDataFormat(dataFormat.getFormat(map.get(CELL_TYPE).toString()));
                }
//                style.setAlignment((map.get(CELL_ALIGN) != null ? (HorizontalAlignment) map.get(CELL_ALIGN) : HorizontalAlignment.RIGHT));
            }
            if (cellLength > 1) {
                writerMergeCell(row, column, row, column + cellLength - 1, value, style);
            } else {
                XSSFRow workRow = sheet.getRow(row);
                if (workRow == null) {
                    workRow = sheet.createRow(row);
                }
                XSSFCell cell = workRow.getCell(column);
                if (null == cell) {
                    cell = workRow.createCell(column);
                }


                Boolean isFormula = true;
                String cellFormula = "";
                try {
                    cellFormula = cell.getCellFormula();
                } catch (IllegalStateException e) {
                    isFormula = false;
                }

                if (!isFormula) { //没有公式填数据
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
                            XlsxWriterUtil.Formula formula = (XlsxWriterUtil.Formula) value;
                            cell.setCellFormula(formula.getFormulaStr());
                            cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
                        } else {
                            cell.setCellValue(new XSSFRichTextString((String.valueOf(value)).trim()));
                        }
                    }


                } else {
//                    System.out.println("没有公式");
                }


            }
        }


        public void writeCell2(int row, int column, Map<String, Object> map, XSSFCellStyle cellStyle) {
            XSSFCellStyle style = wb.createCellStyle();
            style.cloneStyleFrom(cellStyle);
            Object value;
            value = (map == null || map.get(CELL_VALUE) == null) ? "" : map.get(CELL_VALUE);
            int cellLength = (int) map.get(CELL_LENGTH);
            if (!StringUtils.isStrNull(value.toString())) {
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
                if (workRow == null) {
                    workRow = sheet.createRow(row);
                }
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
                        XlsxWriterUtil.Formula formula = (XlsxWriterUtil.Formula) value;
                        cell.setCellFormula(formula.getFormulaStr());
                        cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
                    } else {
                        cell.setCellValue(new XSSFRichTextString((String.valueOf(value)).trim()));
                    }
                }
            }
        }

        public void writeCellWiththousand(int row, int column, Map<String, Object> map, XSSFCellStyle cellStyle) {
            XSSFCellStyle style = wb.createCellStyle();
            style.cloneStyleFrom(cellStyle);
            Object value;
            value = (map == null || map.get(CELL_VALUE) == null) ? "" : map.get(CELL_VALUE);


            int cellLength = (int) map.get(CELL_LENGTH);
            if (!StringUtils.isStrNull(value.toString())) {
                if (map.get(CELL_TYPE) != null) {
//                    XSSFDataFormat dataFormat = wb.createDataFormat();
//                    style.setDataFormat(dataFormat.getFormat(map.get(CELL_TYPE).toString()));
                }
//                style.setAlignment((map.get(CELL_ALIGN) != null ? (HorizontalAlignment) map.get(CELL_ALIGN) : HorizontalAlignment.LEFT));
            }
            if (cellLength > 1) {
                writerMergeCell(row, column, row, column + cellLength - 1, value, style);
            } else {
                XSSFRow workRow = sheet.getRow(row);
                if (workRow == null) {
                    workRow = sheet.createRow(row);
                }
                XSSFCell cell = workRow.getCell(column);
                if (null == cell) {
                    cell = workRow.createCell(column);
                }

                if (!isExistFormula(cell)) {
                    if (value != null) {
                        if (String.class.isInstance(value)) {
//                            style.setAlignment(HorizontalAlignment.LEFT);
                            cell.setCellStyle(style);
                            cell.setCellValue(new XSSFRichTextString((String) value));
                        } else if (BigDecimal.class.isInstance(value)) {
                            cell.setCellStyle(style);
                            cell.setCellValue(Double.parseDouble(String.valueOf(value)) / 1000);
                        } else if (Double.class.isInstance(value)
                                || Integer.class.isInstance(value)
                                || Long.class.isInstance(value)
                                || Float.class.isInstance(value)) {
                            cell.setCellStyle(style);
                            cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                        } else if (Date.class.isInstance(value)) {
                            cell.setCellStyle(style);
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
        }

        public void writeCellWithmillion(int row, int column, Map<String, Object> map, XSSFCellStyle cellStyle) {
            XSSFCellStyle style = wb.createCellStyle();
            style.cloneStyleFrom(cellStyle);
            Object value;
            value = (map == null || map.get(CELL_VALUE) == null) ? "" : map.get(CELL_VALUE);
            int cellLength = (int) map.get(CELL_LENGTH);
            if (!StringUtils.isStrNull(value.toString())) {
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
                if (workRow == null) {
                    workRow = sheet.createRow(row);
                }
                XSSFCell cell = workRow.createCell(column);
                if (value != null) {
                    if (String.class.isInstance(value)) {
                        cell.setCellStyle(style);
                        cell.setCellValue(new XSSFRichTextString((String) value));
                    } else if (Double.class.isInstance(value)
                            || Integer.class.isInstance(value)
                            || Long.class.isInstance(value)
                            || Float.class.isInstance(value)) {
                        cell.setCellStyle(style);
                        cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                    } else if (Date.class.isInstance(value)) {
                        cell.setCellStyle(style);
                        cell.setCellValue((Date) value);
                    } else if (Calendar.class.isInstance(value)) {
                        cell.setCellValue((Calendar) value);
                    } else if (XlsxWriterUtil.Formula.class.isInstance(value)) {
                        XlsxWriterUtil.Formula formula = (XlsxWriterUtil.Formula) value;
                        cell.setCellFormula(formula.getFormulaStr());
                        cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
                    } else if (BigDecimal.class.isInstance(value)) {
                        cell.setCellStyle(style);
                        cell.setCellValue(Double.parseDouble(String.valueOf(value)) / (1000 * 1000));
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
            Map<String, Object> objectMap = (Map<String, Object>) value;
            if (HashMap.class.isInstance(value)) {
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
                    XlsxWriterUtil.Formula formula = (XlsxWriterUtil.Formula) value;
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
                if (null == row) {
                    row = sheet.createRow(i);
                }
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
                    XlsxWriterUtil.Formula formula = (XlsxWriterUtil.Formula) value;
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

    public static XSSFFont setFont2(XSSFWorkbook wb, boolean bold, String fontName, short size) {
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
                if ((!StringUtils.isStrNull(sheetName)) && sheetData.get(sheetName) != null) {
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
                    if (details != null && details.size() > 0) {
                        Integer endRowIndex = rebatePolicys.size();
                        if (endRowIndex < minRowNo) {
                            endRowIndex = minRowNo;
                        }
                        Integer rows = detailStartRow - endRowIndex;
                        writer.curSheetWriter.shiftRows(75, rows);
                        Map<String, Integer> cellPosition2 = writer.getCellPosition("{detailStart}");
                        writer.appendRows(details, cellPosition2.get(ROW_INDEX), cellPosition2.get(COL_INDEX));
                    } else {
                        for (int i = 0; i < 3; i++) {
                            Map<String, Integer> cellPosition2 = writer.getCellPosition("{detailStart}");
                            XSSFRow row = writer.curSheetWriter.sheet.getRow(cellPosition2.get(ROW_INDEX) - i);
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
                if (sheetData != null && sheetData.size() > 0 && !(list.contains(sheetName))) {
                    List<List<Object>> details = sheetData.get(sheetName);
                    if (details != null && details.size() > 0) {
                        //明细行起始行号
                        Integer detailStartRow = 75;
                        //明细行最小起始行
                        Integer minRowNo = 16;
                        Integer endRowIndex = rebatePolicys.size();
                        if (endRowIndex < minRowNo) {
                            endRowIndex = minRowNo;
                        }
                        Integer rows = detailStartRow - endRowIndex;
                        writer.curSheetWriter.shiftRows(75, rows);
                        Map<String, Integer> cellPosition2 = writer.getCellPosition("{detailStart}");
                        writer.appendRows(details, cellPosition2.get(ROW_INDEX), cellPosition2.get(COL_INDEX));
                    } else {
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

    public static void copyRow(XSSFRow fromRow, XSSFRow toRow, XSSFSheet curretSheet, XSSFWorkbook wb) {
        toRow.setHeight(fromRow.getHeight());
        for (Iterator cellIt = fromRow.cellIterator(); cellIt.hasNext(); ) {
            XSSFCell tmpCell = (XSSFCell) cellIt.next();
            XSSFCell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(tmpCell, newCell, true, wb);
        }
        Sheet worksheet = fromRow.getSheet();
        for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
            CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
            if (cellRangeAddress.getFirstRow() == fromRow.getRowNum()) {
                CellRangeAddress newCellRangeAddress = new CellRangeAddress(toRow.getRowNum(), (toRow.getRowNum() +
                        (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())), cellRangeAddress
                        .getFirstColumn(), cellRangeAddress.getLastColumn());
                worksheet.addMergedRegion(newCellRangeAddress);
            }
        }

        //删除行数据,保留行号
        curretSheet.removeRow(fromRow);
    }

    public static void copyCell(XSSFCell srcCell, XSSFCell distCell, boolean copyValueFlag, XSSFWorkbook wb) {
        XSSFCellStyle newStyle = wb.createCellStyle();
        XSSFCellStyle srcStyle = srcCell.getCellStyle();
        newStyle.cloneStyleFrom(srcStyle);
        newStyle.setFont(wb.getFontAt(srcStyle.getFontIndex()));
        //样式
        distCell.setCellStyle(newStyle);
        //评论
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        // 不同数据类型处理
        int srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);
        if (copyValueFlag) {
            if (srcCellType == XSSFCell.CELL_TYPE_NUMERIC) {
                if (DateUtil.isCellDateFormatted(srcCell)) {
                    distCell.setCellValue(srcCell.getDateCellValue());
                } else {
                    distCell.setCellValue(srcCell.getNumericCellValue());
                }
            } else if (srcCellType == XSSFCell.CELL_TYPE_STRING) {
                distCell.setCellValue(srcCell.getRichStringCellValue());
            } else if (srcCellType == XSSFCell.CELL_TYPE_BLANK) {

            } else if (srcCellType == XSSFCell.CELL_TYPE_BOOLEAN) {
                distCell.setCellValue(srcCell.getBooleanCellValue());
            } else if (srcCellType == XSSFCell.CELL_TYPE_ERROR) {
                distCell.setCellErrorValue(srcCell.getErrorCellValue());
            } else if (srcCellType == XSSFCell.CELL_TYPE_FORMULA) {
                distCell.setCellFormula(srcCell.getCellFormula());
            } else {
            }
        }
    }

    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 按照模板写数据
     */
    public static XlsxTemplateWriterUtil writeDataFromTem(String templateFile, String sheetName, int templeteSheet) {

        File file = new File(templateFile);

        XlsxTemplateWriterUtil writer = null;
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList(sheetName), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;

            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);
            XSSFCellStyle style = setAndReturnStyle(wb);

            Map<String, Integer> cellPosition = writer.getCellPosition("{time1}");


            int col = getCellPositionWithcr("L", 7).get(COL_INDEX);
            int row = getCellPositionWithcr("L", 7).get(ROW_INDEX);

            XSSFRow row1 = curretSheet.getRow(row);
            XSSFCell cell = row1.getCell(col);


            Boolean isFormula = true;
            String cellFormula = "";
            try {
                cellFormula = cell.getCellFormula();
            } catch (IllegalStateException e) {
                isFormula = false;
            }

            if (isFormula) {
                System.out.println("有公式");
            } else {
                System.out.println("没有公式");
            }
            System.out.println("cellFormula==" + cellFormula);


//            curretSheet.shiftRows();

//            doShiftRows(writer,curretSheet,16,"{data1}",
//                    3,curretSheet.getLastRowNum(),9);

            doRightCopy("J", 9, 21, 2, curretSheet, wb);

//            Map<String, Object> sheet = new HashMap<>();
//            sheet.put("test1", "44");
//            sheet.put("test2", "88");
//            //数据替换
//            writer.replaceCell(sheet);
//
//            //公式例子
//            String formula = "IF(C32=0,\"\",C31/C32)";
//            fillSequenceFormula(style,curretSheet,"C",30,formula,5);


            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return writer;
    }

    //    public static  Map<String, Integer> getCellPositionWithcr(String cols ,int rows){
    //        Map<String, Integer> result = new HashMap<>();
    //        result.put(ROW_INDEX, rows -1 );
    //        result.put(COL_INDEX, toNum(cols) -1);
    //        return result;
    //    }
    //向右复制单元格

    /**
     * @param startCol
     * @param startRow
     * @param endRow
     * @param copyAmout
     * @param curretSheet
     * @param wb
     */
    //j11--j21  向右复制多列
    public static void doRightCopy(String startCol, int startRow,
                                   int endRow,
                                   int copyAmout,
                                   XSSFSheet curretSheet, XSSFWorkbook wb) {

        if (copyAmout <= 0) {
            return;
        }


        XSSFCellStyle style2 = null;

        //endRow-startRow   总共需要复制的单元格的样式的个数
        for (int i = 0; i < endRow - startRow + 1; i++) {
            //下一行，行数加一
            int roww = getCellPositionWithcr(startCol, startRow).get(ROW_INDEX) + i;
            int colw = getCellPositionWithcr(startCol, startRow).get(COL_INDEX);


            //style2   :   表示最后获取到的单元格样式
            XSSFRow row = curretSheet.getRow(roww);
            if (null == row) {
                row = curretSheet.createRow(roww);
            }
            XSSFCell cell = row.getCell(colw);
            if (null == cell) {
                row.createCell(colw);
            }
            XSSFCellStyle cellStyle = cell.getCellStyle();

            //b3,c3
            style2 = wb.createCellStyle();
            style2.cloneStyleFrom(cellStyle);


            for (int mm = 0; mm < copyAmout; mm++) {


                //得到下一cell
//                XSSFRow row2 = curretSheet.createRow(roww);


                XSSFCell cell2 = row.getCell(colw + mm + 1);
                if (null == cell2) {
                    cell2 = row.createCell(colw + mm + 1);
                }
                cell2.setCellStyle(style2);
                cell2.setCellValue("");


//                System.out.println("cell2.getRowIndex()===="+cell2.getRowIndex());
//                System.out.println("cell2.getColumnIndex()===="+cell2.getColumnIndex());


            }


        }


    }

    //                                                                   C         30       5
    public static void fillSequenceFormula(XSSFCellStyle style, XSSFSheet curretSheet, String col, int row, String formula, int size) {

        for (int i = 0; i < size; i++) {
            writeFormula(style, curretSheet, col, row, formula);


            String fo = "";
            if (formula.contains("IF")) {
                String substring = formula.substring(2, formula.length());
                fo = substring.replaceAll(col, nextStr(col));
                formula = "IF".concat(fo);
            } else if (formula.contains("SUM")) {
                String substring = formula.substring(3, formula.length());
                fo = substring.replaceAll(col, nextStr(col));
                formula = "SUM".concat(fo);
            }
            col = nextStr(col);
        }
    }


    public static void writeFormula(XSSFCellStyle style, XSSFSheet curretSheet, String col, int row, String formulaStr) {//d 30
        XSSFRow curretrow = curretSheet.getRow(row - 1);

        if (null == curretrow) {
            curretrow = curretSheet.createRow(row - 1);
        }
        int i = toNum(col);//D--4
        int mm = i - 1;

        XSSFCell cell = curretrow.getCell(mm);
        if (null == cell) {
            cell = curretrow.createCell(mm);
        }
        cell.setCellFormula(formulaStr);
//        cell.setCellStyle(style);
    }


    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 预算费目别
     */
    public static XlsxTemplateWriterUtil writeDataFromTemBudgetaryFees(List<String> replayList, String yearStr,
                                                                       List<List<Map<String, Object>>> rebatePolicyOne,
                                                                       List<List<Map<String, Object>>> rebatePolicyTwo,
                                                                       List<List<Map<String, Object>>> rebatePolicyThree,
                                                                       String templateFile, String sheetName, int i) {
        File file = new File(templateFile);

        XlsxTemplateWriterUtil writer = null;
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList(sheetName), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;
            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);

            XSSFCellStyle style = setAndReturnStyle(wb);

            writeCurrentCell(wb, writer.getCellPosition("{year}"), curretSheet, yearStr);

            //预算费目明细
            writeCurrentCell(wb, getCellPositionWithcr("G", 2), curretSheet, replayList.get(0));
            //比较列1
            writeCurrentCell(wb, getCellPositionWithcr("L", 3), curretSheet, replayList.get(1));
            //比较列2
            writeCurrentCell(wb, getCellPositionWithcr("M", 3), curretSheet, replayList.get(2));


            //将后面数据复制下移
            //第一个表格
            if (rebatePolicyOne.size() == 0) {
                writeCurrentCell(wb, writer.getCellPosition("{rebatePolicy1}"), curretSheet, "");
            } else {
//                move(5, 50, rebatePolicyOne.size() - 1, curretSheet, wb);

                doShiftRows(writer, curretSheet, 120, "{rebatePolicy1}",
                        4, curretSheet.getLastRowNum(), rebatePolicyOne.size() - 1);

                replayAndWriterWiththousand2(curretSheet, rebatePolicyOne, "{rebatePolicy1}", writer);
            }

//
            if (rebatePolicyTwo.size() == 0) {
                writeCurrentCell(wb, writer.getCellPosition("{rebatePolicy2}"), curretSheet, "");
                writeCurrentCell(wb, writer.getCellPosition("{total2start}"), curretSheet, "");
                writeCurrentCell(wb, writer.getCellPosition("{total2End}"), curretSheet, "");
            } else {

                //total--统计
                Map<String, Integer> total2start = writer.getCellPosition("{total2start}");


                doShiftRows(writer, curretSheet, 120, "{rebatePolicy2}",
                        6 + rebatePolicyOne.size() - 1, curretSheet.getLastRowNum(), rebatePolicyTwo.size() - 1);

                replayAndWriterWiththousand2(curretSheet, rebatePolicyTwo, "{rebatePolicy2}", writer);

                //做统计
                Map<String, Integer> total2End = writer.getCellPosition("{total2End}");
                //向后统计多少行
                int totalCol = rebatePolicyTwo.size() == 0 ? 0 : rebatePolicyTwo.get(0).size();
                doTatal(total2start, total2End, writer, curretSheet, style, totalCol);

            }
//
            if (rebatePolicyThree.size() == 0) {
                writeCurrentCell(wb, writer.getCellPosition("{rebatePolicy3}"), curretSheet, "");
                writeCurrentCell(wb, writer.getCellPosition("{total3start}"), curretSheet, "");
                writeCurrentCell(wb, writer.getCellPosition("{total3End}"), curretSheet, "");
            } else {

                //total--统计
                Map<String, Integer> total3start = writer.getCellPosition("{total3start}");

                //将后面数据复制下移
//                move(11 + rebatePolicyOne.size() - 1 + rebatePolicyTwo.size() - 1, 50 + rebatePolicyOne.size() - 1 + rebatePolicyTwo.size() - 1, rebatePolicyThree.size() - 1, curretSheet, wb);

                doShiftRows(writer, curretSheet, 120, "{rebatePolicy3}",
                        10 + rebatePolicyOne.size() - 1 + rebatePolicyTwo.size() - 1, curretSheet.getLastRowNum(), rebatePolicyThree.size() - 1);

                replayAndWriterWiththousand2(curretSheet, rebatePolicyThree, "{rebatePolicy3}", writer);

                Map<String, Integer> total3End = writer.getCellPosition("{total3End}");
                //向后统计多少行
                int totalCol3 = rebatePolicyThree.size() == 0 ? 0 : rebatePolicyThree.get(0).size();
                doTatal(total3start, total3End, writer, curretSheet, style, totalCol3);
            }

//            XSSFCell sxxCell = creatCell(curretSheet, "AB", 37);
//            System.out.println("===="+sxxCell.getCellFormula());

            //=IF(AND(L4=0,M4>=0),"黑字化",IF(AND(L4=0,M4<0),"赤字化",IF(L4<0,2-M4/L4,M4/L4)))
//            XSSFCell sxxCell2 = creatCell(curretSheet, "O", 4);
//            System.out.println("===="+sxxCell2.getCellFormula());

            //修改错误公式
            //第四個表格
            int row3 = 0;
            int size = 0;
            for (int mm = 0; mm < 2; mm++) {
                if (mm == 0) {
                    row3 = 7 + rebatePolicyOne.size() - 1 - 1;
                    System.out.println("0====" + row3);
                    size = rebatePolicyTwo.size() - 1;
                }

                if (mm == 1) {
                    row3 = 11 + rebatePolicyOne.size() - 1 + rebatePolicyTwo.size() - 1 - 1;
                    System.out.println("1====" + row3);
                    size = rebatePolicyThree.size() - 1;
                }


                getFormulaAndWriterCol(curretSheet, "AB", row3, "M" + row3 + "+R" + row3 + "+W" + row3, size);
                getFormulaAndWriterCol(curretSheet, "AC", row3, "N" + row3 + "+S" + row3 + "+X" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "AU", row3, "AF" + row3 + "+AK" + row3 + "+AP" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "AV", row3, "AG" + row3 + "+AL" + row3 + "+AQ" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "AW", row3, "AH" + row3 + "+AM" + row3 + "+AR" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "AZ", row3, "AA" + row3 + "+AU" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "BA", row3, "AB" + row3 + "+AV" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "BB", row3, "AC" + row3 + "+AW" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "BT", row3, "BE" + row3 + "+BJ" + row3 + "+BO" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "BU", row3, "BF" + row3 + "+BK" + row3 + "+BP" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "BV", row3, "BG" + row3 + "+BL" + row3 + "+BQ" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "CN", row3, "BY" + row3 + "+CD" + row3 + "+CI" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "CO", row3, "BZ" + row3 + "+CE" + row3 + "+CJ" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "CP", row3, "CA" + row3 + "+CF" + row3 + "+CK" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "CS", row3, "BT" + row3 + "+CN" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "CT", row3, "BU" + row3 + "+CO" + row3 + "", size);
                getFormulaAndWriterCol(curretSheet, "CU", row3, "BV" + row3 + "+CP" + row3 + "", size);

            }


//            System.out.println("\"AB\",row3,rebatePolicyThree.size()==="+"AB--"+row3+"-"+rebatePolicyThree.size());

            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return writer;
    }


    public static void getFormulaAndWriterCol(XSSFSheet curretSheet, String col, int row, int writerRow) {
        XSSFCell sxxCell = creatCell(curretSheet, col, row);
        if (isExistFormula(sxxCell)) {//存在公式
            String cellFormula = sxxCell.getCellFormula();
            for (int i = 0; i < writerRow; i++) {
                String fore = automaticAdjustmentFormula(cellFormula, i + 1);
                //下一行的单元格写公式
                XSSFCell sxxCell2 = creatCell(curretSheet, col, row + 1 + i);
                sxxCell2.setCellFormula(fore);
                System.out.println("cellFormula===" + cellFormula);
                System.out.println("公式 +" + fore);
            }
        }
    }

    public static void getFormulaAndWriterCol(XSSFSheet curretSheet, String col, int row, String formu, int writerRow) {
        XSSFCell sxxCell = creatCell(curretSheet, col, row);
        if (isExistFormula(sxxCell)) {//存在公式
            String cellFormula = sxxCell.getCellFormula();
            for (int i = 0; i < writerRow; i++) {
                String fore = automaticAdjustmentFormula(formu, i + 1);
                //下一行的单元格写公式
                XSSFCell sxxCell2 = creatCell(curretSheet, col, row + 1 + i);
                sxxCell2.setCellFormula(fore);
//                System.out.println("cellFormula==="+cellFormula);
//                System.out.println("公式 +"+fore);
            }
        }
    }


    public static XSSFCell creatCell(XSSFSheet curretSheet, String col, int row) {
        int row2 = getCellPositionWithcr(col, row).get(ROW_INDEX);
        int col2 = getCellPositionWithcr(col, row).get(COL_INDEX);

        XSSFRow xssRow = curretSheet.getRow(row2);
        if (null == xssRow) {
            xssRow = curretSheet.createRow(row2);
        }
        XSSFCell sxxCell = xssRow.getCell(col2);
        if (null == sxxCell) {
            sxxCell = xssRow.createCell(col2);
        }
        return sxxCell;
    }

    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description:全社合计JP
     */
    public static XlsxTemplateWriterUtil writeDataFromTotaClub(String yeardata,
                                                               Map<String, List<List<Map<String, Object>>>> dataMap,
                                                               String templateFile,
                                                               String sheetName,
                                                               int m) {
        File file = new File(templateFile);

        XlsxTemplateWriterUtil writer = null;
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList(sheetName), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;
            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);

            XSSFCellStyle style = setAndReturnStyle(wb);

            List<String> replaylist = new ArrayList<>();
            for (int i = 1; i <= 23; i++) {
                replaylist.add("data".concat(i + ""));
            }

            for (String value : replaylist) {
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++" + value);
                List<List<Map<String, Object>>> D101 = dataMap.get(value);
                if (D101 == null || D101.size() == 0) {
                    continue;
                }
                replayAndWriterWiththousand2(curretSheet, D101, "{" + value + "}", writer);
            }

            //填时间
            writeCurrentCell(wb, writer.getCellPosition("{datayear}"), curretSheet, yeardata);

            List<List<Map<String, Object>>> data24 = dataMap.get("data24");
            if (data24 == null || data24.size() == 0) {
                writeCurrentCell(wb, writer.getCellPosition("{data24}"), curretSheet, "");
            } else {
//                move(84, 100, data24.size() - 1, curretSheet, wb);

//                System.out.println("data24.size()-1==="+(data24.size()-1));
                doShiftRows(writer, curretSheet, 120, "{data24}",
                        83, curretSheet.getLastRowNum(), data24.size() - 1);

                replayAndWriterWiththousand2(curretSheet, data24, "{data24}", writer);
            }

            List<List<Map<String, Object>>> data26 = dataMap.get("data26");
            if (data26 == null || data26.size() == 0) {
                writeCurrentCell(wb, writer.getCellPosition("{data26}"), curretSheet, "");
            } else {


                doShiftRows(writer, curretSheet, 120, "{data26}",
                        86 + data24.size() - 1, curretSheet.getLastRowNum(), data26.size() - 1);

                replayAndWriterWiththousand2(curretSheet, data26, "{data26}", writer);
            }


            List<List<Map<String, Object>>> data28 = dataMap.get("data28");
            if (data28 == null || data28.size() == 0) {
                writeCurrentCell(wb, writer.getCellPosition("{data28}"), curretSheet, "");
            } else {

                doShiftRows(writer, curretSheet, 120, "{data28}",
                        89 + data24.size() - 1 + data26.size() - 1, curretSheet.getLastRowNum(), data28.size() - 1);

                replayAndWriterWiththousand2(curretSheet, data28, "{data28}", writer);
            }


            int row3 = 84 + data24.size() - 1;
            String formula = "SUM(L" + "83" + ":L" + (row3 - 1) + ")";
            fillSequenceFormula(style, curretSheet, "L", row3, formula, data24.get(0).size() + 3);


            row3 = 87 + data24.size() - 1 + data26.size() - 1;
            int mm = 86 + data24.size() - 1;
            formula = "SUM(L" + mm + ":L" + (row3 - 1) + ")";
            fillSequenceFormula(style, curretSheet, "L", row3, formula, data24.get(0).size() + 3);

            row3 = 90 + data24.size() - 1 + data26.size() - 1 + data28.size() - 1;
            mm = 89 + data24.size() - 1 + data26.size() - 1;
            formula = "SUM(L" + mm + ":L" + (row3 - 1) + ")";
            fillSequenceFormula(style, curretSheet, "L", row3, formula, data24.get(0).size() + 3);


            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return writer;
    }

    public static XSSFCellStyle setAndReturnStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        style.setFillForegroundColor(IndexedColors.WHITE.index);


        return style;
    }

    /**
     * @author by wgj
     * @date 2019/6/7
     * @description:
     */
//    public static void initialGeneralManagerExcel(String templateFile, String sheetName, Integer year
//            , String outFile, List<Map<String, Object>> data
//            , Map<String, Map<Integer, List<Map<String, Object>>>> locations
//            , String reportType, String dataType) {
//        File file = new File(templateFile);
//        File outputFile = new File(outFile);
//        XlsxTemplateWriterUtil writer;
//        try {
//            writer = new XlsxTemplateWriterUtil(file, Arrays.asList(sheetName), 1);
//            //获取XSSFWorkbook 对象
//            XSSFWorkbook wb = writer.wb;
//            wb.getCTWorkbook().getSheets().getSheetArray(0).setName(sheetName);
//            //获取sheet
//            XSSFSheet sheet = wb.getSheetAt(0);
//            if (reportType.equals(WordBookConstant.WORD_BOOK_NO_E24)) {
//                sheet.getRow(1).getCell(2).setCellValue("部門：" + sheetName);
//                if (dataType.equals(WordBookConstant.WORD_BOOK_NO_C81)) {
//                    sheet.getRow(3).getCell(3).setCellValue("FY" + (year - 2));
//                    sheet.getRow(3).getCell(10).setCellValue("FY" + (year - 1));
//                    sheet.getRow(3).getCell(29).setCellValue("FY" + year);
//                    sheet.getRow(3).getCell(39).setCellValue("FY" + (year + 1) + "（中期）");
//                    sheet.getRow(3).getCell(45).setCellValue("FY" + (year + 2) + "（中期）");
//                } else {
//                    sheet.getRow(3).getCell(3).setCellValue("FY" + (year - 1));
//                    sheet.getRow(3).getCell(10).setCellValue("FY" + year);
//                }
//            } else if (reportType.equals(WordBookConstant.WORD_BOOK_NO_E23)) {
//                sheet.getRow(3).getCell(3).setCellValue("部門：" + sheetName);
//                if (dataType.equals(WordBookConstant.WORD_BOOK_NO_C81)) {
//
//                } else {
//                    sheet.getRow(5).getCell(4).setCellValue("FY" + (year - 2));
//                    sheet.getRow(5).getCell(5).setCellValue("FY" + (year - 1));
//                    sheet.getRow(5).getCell(12).setCellValue("FY" + year);
//                }
//            } else {
//                sheet.getRow(3).getCell(3).setCellValue("部門：" + sheetName);
//            }
//
//
//            if (data != null) {
//                // 循环取出所有数据，根据配置表填写Excel单元格
//                for (Map<String, Object> map : data) {
//                    Map<Integer, List<Map<String, Object>>> locMap = locations.get(map.get("type"));
//                    if (locMap != null) {
//                        for (Integer loc : locMap.keySet()) {
//                            // 利用循环比较出最小的行号  模板从最上面行开始填写
//                            List<Map<String, Object>> locList = locMap.get(loc);
//                            if (locList != null && locList.size() > 0) {
//                                for (Map<String, Object> info : locList) {
//                                    if (map.get(info.get("fieldName")) != null
//                                            && new BigDecimal(map.get(info.get("fieldName")).toString()).compareTo(BigDecimal.ZERO) != 0) {
//                                        sheet.getRow((Integer) info.get("cellRow")).getCell((Integer) info.get("cellColumn"))
//                                                .setCellValue(Double.parseDouble(map.get(info.get("fieldName")).toString()));
//                                    }
//                                }
//                            }
//                        }
//
//                    }
//                }
//            }
//
//            sheet.setForceFormulaRecalculation(true);
//
//            // 输出文件流到导出文件中
//            writer.writeExcel(new FileOutputStream(outputFile));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @author by wgj
//     * @date 2019/6/7
//     * @description:
//     */
//    public static void initialPLExcel(String templateFile, String sheetName, Map<String, String> param
//            , String outFile, List<Map<String, Object>> plData
//            , Map<String, Map<Integer, List<Map<String, Object>>>> locations
//            , List<Map<String, Object>> plDataFo
//            , Map<String, Map<Integer, List<Map<String, Object>>>> locationsFo) {
//        File file = new File(templateFile);
//        File outputFile = new File(outFile);
//        int year = Integer.parseInt(param.get("year"));
//        XlsxTemplateWriterUtil writer;
//        try {
//            writer = new XlsxTemplateWriterUtil(file, Arrays.asList(sheetName), 1);
//            //获取XSSFWorkbook 对象
//            XSSFWorkbook wb = writer.wb;
//            //获取sheet
//            XSSFSheet sheet = wb.getSheetAt(0);
//            wb.getCTWorkbook().getSheets().getSheetArray(0).setName(sheetName.replace("事业", ""));
//            if (param.get("deptType").equals(WordBookConstant.WORD_BOOK_NO_C81)) {
//                sheet.getRow(2).getCell(6).setCellValue("FY" + (year - 1) + "下期見込/FY" + year + "期首計画");
//            } else {
//                sheet.getRow(2).getCell(6).setCellValue("FY" + year + "上期見込/FY" + year + "見直計画");
//            }
//
//            sheet.getRow(3).getCell(12).setCellValue(param.get("mainDeptName"));
//            sheet.getRow(3).getCell(19).setCellValue("◆各間接部門提出納期：" + DateTimeUtils.format(
//                    DateTimeUtils.parseStr(param.get("indirectDepartDate")
//                            , DateTimeUtils.dateString), DateTimeUtils.dateTimeString4));
//            sheet.getRow(4).getCell(12).setCellValue(param.get("deptName"));
//            sheet.getRow(4).getCell(19).setCellValue("◆各事業部門提出納期：" + DateTimeUtils.format(
//                    DateTimeUtils.parseStr(param.get("undertakingsDate"),
//                            DateTimeUtils.dateString), DateTimeUtils.dateTimeString4));
//            sheet.getRow(6).getCell(11).setCellValue("FY" + (year - 1) + "年度（" + (year - 1) + ".04-" + year + ".03）");
//            sheet.getRow(6).getCell(31).setCellValue("FY" + year + "年度（" + year + ".04-" + (year + 1) + ".03）");
//            sheet.getRow(6).getCell(51).setCellValue("H" + (year + 1) + "年度（" + (year + 1) + ".04-" + (year + 2) + ".03）");
//            sheet.getRow(6).getCell(55).setCellValue("H" + (year + 2) + "年度（" + (year + 2) + ".04-" + (year + 3) + ".03）");
//
//            if (plData != null && locations != null) {
//                // 复制locations的值，预防根据key移除Value时，改变根数据
//                HashMap<String, Map<Integer, List<Map<String, Object>>>> copyLocations = new HashMap<>();
//                for (String key : locations.keySet()) {
//                    Map<Integer, List<Map<String, Object>>> nMap = new HashMap<>();
//                    nMap.putAll(locations.get(key));
//                    copyLocations.put(key, nMap);
//                }
//                // 循环取出所有数据，根据配置表填写Excel单元格
//                for (Map<String, Object> map : plData) {
//                    Map<Integer, List<Map<String, Object>>> locMap = copyLocations.get(map.get("type"));
//                    if (locMap != null) {
//                        Integer topRow = null;
//                        for (Integer loc : locMap.keySet()) {
//                            if (topRow == null || topRow > loc) topRow = loc;
//                        }
//                        // 利用循环比较出最小的行号  模板从最上面行开始填写
//                        List<Map<String, Object>> locList = locMap.get(topRow);
//                        if (locList != null && locList.size() > 0) {
//                            for (Map<String, Object> info : locList) {
//                                if (map.get(info.get("fieldName")) != null) {
//                                    if (info.get("cellType").equals(SystemConstants.CELL_TYPE_STRING)) {
//                                        sheet.getRow((Integer) info.get("cellRow")).getCell((Integer) info.get("cellColumn"))
//                                                .setCellValue(map.get(info.get("fieldName")).toString());
//                                    } else {
//                                        if (new BigDecimal(map.get(info.get("fieldName")).toString()).compareTo(BigDecimal.ZERO) != 0) {
//                                            sheet.getRow((Integer) info.get("cellRow")).getCell((Integer) info.get("cellColumn"))
//                                                    .setCellValue(Double.parseDouble(map.get(info.get("fieldName")).toString()));
//                                        }
//                                    }
//                                }
//                            }
//                            copyLocations.get(map.get("type")).remove(topRow);
//                        }
//                    }
//                }
//            }
//
//            if (plDataFo != null && locationsFo != null && locations != null) {
//                // 循环取出所有数据，根据配置表填写Excel单元格
//                for (Map<String, Object> map : plDataFo) {
//                    if (map.get("type") == null) break;
//                    Map<Integer, List<Map<String, Object>>> locMap = locationsFo.get(map.get("type"));
//                    if (locMap != null) {
//                        for (Integer loc : locMap.keySet()) {
//                            Integer row = loc;
//                            if (map.get("cellRow") != null && (Integer) map.get("cellRow") > 0)
//                                row = (Integer) map.get("cellRow");
//                            List<Map<String, Object>> locList = locMap.get(row);
//                            // 使用实绩的利润中心名称配置填写名称
//                            if (map.get("proName") != null) {
//                                List<Map<String, Object>> acLocList = null;
//                                if (locations.get(map.get("type")) != null)
//                                    acLocList = locations.get(map.get("type")).get(row);
//                                if (acLocList != null) {
//                                    for (Map<String, Object> info : acLocList) {
//                                        if (info.get("fieldName") != null && info.get("fieldName").equals("proName")) {
//                                            sheet.getRow((Integer) info.get("cellRow")).getCell((Integer) info.get("cellColumn"))
//                                                    .setCellValue(map.get(info.get("fieldName")).toString());
//                                        }
//                                    }
//                                }
//                            }
//                            // 根据配置表 逐个向单元格中填值
//                            if (locList != null && locList.size() > 0) {
//                                for (Map<String, Object> info : locList) {
//                                    if (map.get(info.get("fieldName")) != null) {
//                                        // 数值型时
//                                        if (info.get("cellType").equals(SystemConstants.CELL_TYPE_INT)) {
//                                            if (new BigDecimal(map.get(info.get("fieldName")).toString()).compareTo(BigDecimal.ZERO) != 0) {
//                                                sheet.getRow((Integer) info.get("cellRow")).getCell((Integer) info.get("cellColumn"))
//                                                        .setCellValue(Double.parseDouble(map.get(info.get("fieldName")).toString()));
//                                            }
//                                        } else {
//                                            // 字符串时
//                                            sheet.getRow((Integer) info.get("cellRow")).getCell((Integer) info.get("cellColumn"))
//                                                    .setCellValue(map.get(info.get("fieldName")).toString());
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            sheet.setForceFormulaRecalculation(true);
//
//            // 输出文件流到导出文件中
//            writer.writeExcel(new FileOutputStream(outputFile));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * @param writer
     * @param curretSheet
     * @param copyCol     复制多少列
     * @param data        替换的字符串
     * @param startRow    开始行
     * @param endRow      结束行
     * @param n           总共复制多少行
     */
    public static void doShiftRows(XlsxTemplateWriterUtil writer, XSSFSheet curretSheet, int copyCol, String data,
                                   int startRow, int endRow, int n) {


        if (n <= 0) {
            return;
        }
        XSSFWorkbook wb = writer.wb;

//        try{
        curretSheet.shiftRows(startRow, curretSheet.getLastRowNum(), n, true, true);
//        }catch (Exception e){
//
//        }


        XSSFCellStyle style2 = null;
        Integer roww = 0;
        Integer colw = 0;
        //循环复制多少行
        for (int rowt = 0; rowt < n; rowt++) {

            for (int colt = 0; colt < copyCol; colt++) {
                //b3作为模板开始复制
                Map<String, Integer> dataH = writer.getCellPosition(data);
//                XSSFSheet curretSheet
                roww = dataH.get(ROW_INDEX);
                colw = dataH.get(COL_INDEX) + colt;
                XSSFRow row = curretSheet.getRow(roww);
                if (null == row) {
                    row = curretSheet.createRow(roww);
                }
                XSSFCell cell = row.getCell(colw);
                if (null == cell) {
                    cell = row.createCell(colw);
                }
                XSSFCellStyle cellStyle = cell.getCellStyle();

                //b3,c3
                style2 = wb.createCellStyle();
                style2.cloneStyleFrom(cellStyle);

                //得到下一行
                XSSFRow row2 = curretSheet.getRow(roww + rowt + 1);
                if (null == row2) {
                    row2 = curretSheet.createRow(roww + rowt + 1);
                }


                XSSFCell cell2 = row2.getCell(colw);
                if (null == cell2) {
                    cell2 = row2.createCell(colw);
                }
                cell2.setCellStyle(style2);


//                cell2.setCellValue("");
                if (isExistFormula(cell)) {//存在公式
                    String cellFormula = cell.getCellFormula();
                    String fore = automaticAdjustmentFormula(cellFormula, 1 + rowt);
                    cell2.setCellFormula(fore);
                }

            }
        }


    }

    public static Map<String, Integer> getCellPositionWithcr(String cols, int rows) {
        Map<String, Integer> result = new HashMap<>();
        result.put(ROW_INDEX, rows - 1);
        result.put(COL_INDEX, toNum(cols) - 1);
        return result;
    }

    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 本社经费导出
     */
    public static XlsxTemplateWriterUtil writeDataFromFundsProfit(Map<String, List<List<Map<String, Object>>>> dataMap,
                                                                  String yearStr,
                                                                  String fileLocation,
                                                                  String budgetary_fees,
                                                                  String name,
                                                                  BigDecimal mdmd_E1,
                                                                  BigDecimal mdmd_D10) {
        File file = new File(fileLocation);

        XlsxTemplateWriterUtil writer = null;


        //只有一个sheet页
        try {
            if (dataMap.get("data3") == null) {
                writer = new XlsxTemplateWriterUtil(file, Arrays.asList("1"), 1);


                //获取XSSFWorkbook 对象
                XSSFWorkbook wb = writer.wb;
                XSSFCellStyle style = setAndReturnStyle(wb);

                //获取sheet
                XSSFSheet curretSheet = wb.getSheetAt(0);
                writer.selectSheet(0);


                List<List<Map<String, Object>>> data1 = dataMap.get("data1");
                List<List<Map<String, Object>>> data2 = dataMap.get("data2");

                writeCurrentCell(wb, writer.getCellPosition("{time1}"), curretSheet, yearStr);
                writeCurrentCell(wb, writer.getCellPosition("{time2}"), curretSheet, yearStr);
                writeCurrentCell(wb, writer.getCellPosition("{name1}"), curretSheet, name);

                doShiftRows(writer, curretSheet, 16, "{data1}",
                        3, curretSheet.getLastRowNum(), data1.size() - 1);

                System.out.println("data1.size()===" + data1.size());

                //将后面数据复制下移
                Map<String, Integer> total3start = writer.getCellPosition("{data1start}");
                replayAndWriterWiththousand2(curretSheet, data1, "{data1}", writer);
                Map<String, Integer> total3End = writer.getCellPosition("{data1end}");

                if ("Funds_Profit".equals(budgetary_fees)) {
                    writeCurrentCell(wb, writer.getCellPosition("{deptName}"), curretSheet, "MDMD:");
                    if (mdmd_E1 != null) {
                        writeCurrentCell2(wb, writer.getCellPosition("{E1}"), curretSheet, mdmd_E1.divide(new BigDecimal(1000)));
                    } else {
                        writeCurrentCell2(wb, writer.getCellPosition("{E1}"), curretSheet, BigDecimal.ZERO);
                    }
                    if (mdmd_D10 != null) {
                        writeCurrentCell2(wb, writer.getCellPosition("{D10}"), curretSheet, mdmd_D10.divide(new BigDecimal(1000)));
                    } else {
                        writeCurrentCell2(wb, writer.getCellPosition("{D10}"), curretSheet, BigDecimal.ZERO);
                    }
                }

                //向后统计多少行
                int totalCol3 = 10;
                doTatal(total3start, total3End, writer, curretSheet, style, totalCol3);


                doShiftRows(writer, curretSheet, 16, "{data2}",
                        9 + data1.size() - 1, curretSheet.getLastRowNum(), data2.size() - 1);

                Map<String, Integer> data2start = writer.getCellPosition("{data2start}");
                replayAndWriterWiththousand2(curretSheet, data2, "{data2}", writer);

                Map<String, Integer> data2end = writer.getCellPosition("{data2end}");
                //向后统计多少行
                int totalCol2 = 10;
                doTatal(data2start, data2end, writer, curretSheet, style, totalCol2);


                //最右侧使用公式合计
                //q3   SUM(H3:P3)    ,q4   SUM(H4:P4)   ,q5


                //数据行数
                for (int i = 0; i < data1.size(); i++) {
                    Map<String, Integer> cellPositions = getCellPositionWithcr("Q", 3 + i);
                    int currentRow = cellPositions.get(ROW_INDEX);
                    int currentCol = cellPositions.get(COL_INDEX);

                    XSSFRow row = curretSheet.getRow(currentRow);
                    XSSFCell cell = row.getCell(currentCol);
                    cell.setCellFormula("SUM(H" + (3 + i) + ":P" + (3 + i) + ")");
                }

                for (int i = 0; i < data2.size(); i++) {
                    Map<String, Integer> cellPositions = getCellPositionWithcr("Q", 3 + 5 + i + data1.size());
                    int currentRow = cellPositions.get(ROW_INDEX);
                    int currentCol = cellPositions.get(COL_INDEX);

                    XSSFRow row = curretSheet.getRow(currentRow);
                    XSSFCell cell = row.getCell(currentCol);
                    cell.setCellFormula("SUM(H" + (3 + 5 + i + data1.size()) + ":P" + (3 + 5 + i + data1.size()) + ")");
                }


                wb.setForceFormulaRecalculation(true);
            } else { //两个sheet页
                writer = new XlsxTemplateWriterUtil(file, Arrays.asList("1"), 1);

                //获取XSSFWorkbook 对象
                XSSFWorkbook wb = writer.wb;

                XSSFCellStyle style = wb.createCellStyle();
                style.setAlignment(HorizontalAlignment.RIGHT);
                style.setFont(setFont2(wb, true, "宋体", (short) 11));
                style.setBorderTop(CellStyle.BORDER_THIN);
                style.setBorderRight(CellStyle.BORDER_THIN);
                style.setBorderBottom(CellStyle.BORDER_THIN);
                style.setBorderLeft(CellStyle.BORDER_THIN);
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                style.setFillForegroundColor(IndexedColors.WHITE.index);

                wb.cloneSheet(0);

                //获取sheet
                XSSFSheet curretSheet = wb.getSheetAt(0);
                writer.selectSheet(0);

                List<List<Map<String, Object>>> data1 = dataMap.get("data1");
                List<List<Map<String, Object>>> data2 = dataMap.get("data2");

                writeCurrentCell(wb, writer.getCellPosition("{time1}"), curretSheet, yearStr);
                writeCurrentCell(wb, writer.getCellPosition("{time2}"), curretSheet, yearStr);


                doShiftRows(writer, curretSheet, 16, "{data1}",
                        3, curretSheet.getLastRowNum(), data1.size() - 1);

                System.out.println("data1.size()===" + data1.size());

                //将后面数据复制下移
                Map<String, Integer> total3start = writer.getCellPosition("{data1start}");
                replayAndWriterWiththousand2(curretSheet, data1, "{data1}", writer);
                Map<String, Integer> total3End = writer.getCellPosition("{data1end}");
                //向后统计多少行
                int totalCol3 = 10;
                doTatal(total3start, total3End, writer, curretSheet, style, totalCol3);


                doShiftRows(writer, curretSheet, 16, "{data2}",
                        9 + data1.size() - 1, curretSheet.getLastRowNum(), data2.size() - 1);

                Map<String, Integer> data2start = writer.getCellPosition("{data2start}");
                replayAndWriterWiththousand2(curretSheet, data2, "{data2}", writer);

                Map<String, Integer> data2end = writer.getCellPosition("{data2end}");
                //向后统计多少行
                int totalCol2 = 10;
                doTatal(data2start, data2end, writer, curretSheet, style, totalCol2);


//
//
//
//                //获取sheet
                writer.selectSheet(1);
                XSSFSheet curretSheet1 = wb.getSheetAt(1);
                List<List<Map<String, Object>>> data3 = dataMap.get("data3");
                List<List<Map<String, Object>>> data4 = dataMap.get("data4");

                writeCurrentCell(wb, writer.getCellPosition("{time1}"), curretSheet1, yearStr);
                writeCurrentCell(wb, writer.getCellPosition("{time2}"), curretSheet1, yearStr);

                doShiftRows(writer, curretSheet1, 16, "{data1}",
                        3, curretSheet1.getLastRowNum(), data1.size() - 1);

                //将后面数据复制下移

                Map<String, Integer> total3start2 = writer.getCellPosition("{data1start}");

//                move(4, 10, data1.size() - 1, curretSheet1, wb);
//                replayAndWriterWiththousand(data3, "{data1}", writer);
                replayAndWriterWiththousand2(curretSheet1, data3, "{data1}", writer);

                Map<String, Integer> total3End2 = writer.getCellPosition("{data1end}");
                //向后统计多少行
                int totalCol32 = data3.get(0).size() - 2;
                doTatal(total3start2, total3End2, writer, curretSheet1, style, totalCol32);


                doShiftRows(writer, curretSheet1, 16, "{data2}",
                        9 + data1.size() - 1, curretSheet1.getLastRowNum(), data2.size() - 1);

                Map<String, Integer> data2start3 = writer.getCellPosition("{data2start}");
                //将后面数据复制下移
//                move(10 + data1.size() - 1, 11 + data1.size() - 1, data2.size() - 1, curretSheet1, wb);
                replayAndWriterWiththousand2(curretSheet1, data4, "{data2}", writer);
//                replayAndWriterWiththousand(data4, "{data2}", writer);

                Map<String, Integer> data2end3 = writer.getCellPosition("{data2end}");
                //向后统计多少行
                int totalCol23 = data4.get(0).size() - 2;
                doTatal(data2start3, data2end3, writer, curretSheet1, style, totalCol23);


                //数据行数
                for (int i = 0; i < data1.size(); i++) {
                    Map<String, Integer> cellPositions = getCellPositionWithcr("Q", 3 + i);
                    int currentRow = cellPositions.get(ROW_INDEX);
                    int currentCol = cellPositions.get(COL_INDEX);

                    XSSFRow row = curretSheet.getRow(currentRow);
                    XSSFCell cell = row.getCell(currentCol);
                    cell.setCellFormula("SUM(H" + (3 + i) + ":P" + (3 + i) + ")");
                }

                for (int i = 0; i < data2.size(); i++) {
                    Map<String, Integer> cellPositions = getCellPositionWithcr("Q", 10 + i);
                    int currentRow = cellPositions.get(ROW_INDEX);
                    int currentCol = cellPositions.get(COL_INDEX);

                    XSSFRow row = curretSheet.getRow(currentRow);
                    XSSFCell cell = row.getCell(currentCol);
                    cell.setCellFormula("SUM(H" + (10 + i) + ":P" + (10 + i) + ")");
                }


                //数据行数
                for (int i = 0; i < data3.size(); i++) {
                    Map<String, Integer> cellPositions = getCellPositionWithcr("Q", 3 + i);
                    int currentRow = cellPositions.get(ROW_INDEX);
                    int currentCol = cellPositions.get(COL_INDEX);

                    XSSFRow row = curretSheet1.getRow(currentRow);
                    XSSFCell cell = row.getCell(currentCol);
                    cell.setCellFormula("SUM(H" + (3 + i) + ":P" + (3 + i) + ")");
                }

                for (int i = 0; i < data4.size(); i++) {
                    Map<String, Integer> cellPositions = getCellPositionWithcr("Q", 10 + i);
                    int currentRow = cellPositions.get(ROW_INDEX);
                    int currentCol = cellPositions.get(COL_INDEX);

                    XSSFRow row = curretSheet1.getRow(currentRow);
                    XSSFCell cell = row.getCell(currentCol);
                    cell.setCellFormula("SUM(H" + (10 + i) + ":P" + (10 + i) + ")");
                }


                wb.setForceFormulaRecalculation(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return writer;
    }


    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: BSB
     */
    public static XlsxTemplateWriterUtil writeDataFromBSB(String year, Map<String, List<List<Map<String, Object>>>> dataMap, String fileLocation, String bsb) {

        File file = new File(fileLocation);
        XlsxTemplateWriterUtil writer = null;
        //只有一个sheet页
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList("1"), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;
            XSSFCellStyle style = setAndReturnStyle(wb);

            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);

            XSSFRow row = curretSheet.getRow(2);
            XSSFCell cell = row.getCell(1);

            cell.setCellValue(year);


            List<List<Map<String, Object>>> data1 = dataMap.get("data1");


            doShiftRows(writer, curretSheet, 19, "{data1}",
                    4, curretSheet.getLastRowNum(), data1.size() - 1);

            replayAndWriterWiththousand2(curretSheet, data1, "{data1}", writer);

            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return writer;


    }

    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 总经理审议（事业部） --- 基本
     */
    public static XlsxTemplateWriterUtil writeDataFrombg_direct(String deptName,
                                                                Map<String, List<List<Map<String, Object>>>> dataMap,
                                                                Map<String, List<List<Map<String, Object>>>> dataMap2,
                                                                List<String> replayList,
                                                                List<String> replayList2,
                                                                String fileLocation,
                                                                String bg_direct,
                                                                int i) {
        File file = new File(fileLocation);
        XlsxTemplateWriterUtil writer = null;
        //只有一个sheet页
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList("1"), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;
            XSSFCellStyle style = setAndReturnStyle(wb);

            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);

            writeCurrentCell(wb, writer.getCellPosition("{deptName}"), curretSheet, deptName);

            for (String value : replayList) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++" + value);
                List<List<Map<String, Object>>> D101 = dataMap.get(value);
                if (D101 == null || D101.size() == 0) {
                    continue;
                }
                replayAndWriterWithmillion(D101, "{" + value + "}", writer);
            }

            if (null != dataMap2) {

                for (String value : replayList2) {
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++" + value);
                    List<List<Map<String, Object>>> D101 = dataMap2.get(value);
                    if (D101 == null || D101.size() == 0) {
                        continue;
                    }
                    replayAndWriterWithmillion(D101, "{" + value + "}", writer);
                }
            }

            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return writer;

    }

    public static XlsxTemplateWriterUtil writeDataFrombg_indirect(List<String> replayList,
                                                                  List<String> replayList2, Map<String, List<List<Map<String, Object>>>> dataMap,
                                                                  Map<String, List<List<Map<String, Object>>>> dataMap2,
                                                                  String fileLocation,
                                                                  String bg_direct,
                                                                  int i) {
        File file = new File(fileLocation);
        XlsxTemplateWriterUtil writer = null;
        //只有一个sheet页
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList("1"), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;
            XSSFCellStyle style = setAndReturnStyle(wb);
            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);

            for (String value : replayList) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++" + value);
                List<List<Map<String, Object>>> D101 = dataMap.get(value);
                if (D101 == null || D101.size() == 0) {
                    continue;
                }
                replayAndWriterWiththousand(D101, "{" + value + "}", writer);
            }
            if (null != dataMap2) {
                for (String value : replayList2) {
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++" + value);
                    List<List<Map<String, Object>>> D101 = dataMap2.get(value);
                    if (D101 == null || D101.size() == 0) {
                        continue;
                    }
                    replayAndWriterWiththousand(D101, "{" + value + "}", writer);
                }
            }


            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return writer;
    }


    public static XlsxTemplateWriterUtil writeDataFromInventory(List<String> replayList,
                                                                List<String> replayList2,//回转月数不除以1000
                                                                String year2,
                                                                Map<String, List<List<Map<String, Object>>>> dataMap,
                                                                String fileLocation,
                                                                String inventory) {
        File file = new File(fileLocation);
        XlsxTemplateWriterUtil writer = null;
        //只有一个sheet页
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList("1"), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;
            XSSFCellStyle style = setAndReturnStyle(wb);

            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);

            doRightCopy("M", 3, 28, dataMap.get("dataNo").get(0).size() - 1, curretSheet, wb);
            doRightCopy("M", 30, 55, dataMap.get("dataNo1").get(0).size() - 1, curretSheet, wb);
            doRightCopy("M", 57, 88, dataMap.get("businessNo1").get(0).size() - 1, curretSheet, wb);
            doRightCopy("M", 90, 115, dataMap.get("dataNo2").get(0).size() - 1, curretSheet, wb);
            doRightCopy("M", 117, 136, dataMap.get("businessNo2").get(0).size() - 1, curretSheet, wb);


            for (String value : replayList) {
//                System.out.println("value======"+value);
                List<List<Map<String, Object>>> D101 = dataMap.get(value);
                replayAndWriterWiththousand2(curretSheet, D101, "{" + value + "}", writer);
            }
            for (String value : replayList2) {
                List<List<Map<String, Object>>> D101 = dataMap.get(value);
                replayAndWriter2(curretSheet, D101, "{" + value + "}", writer);
            }

            //对公示特殊处理

            //=IF(M8=0,"",M9/M10)

//            int row = 8;
//            for (int i = 0; i < 7; i++) {
//                String formula = "IF(M" + (row + 2) + "=0,\"\",M" + (row + 1) + "/M" + (row + 2) + ")";
//                fillSequenceFormula(style, curretSheet, "M", row, formula, dataMap.get("dataNo").get(0).size());
//                row = row + 3;
//            }
//
//            int row2 = 35;
//            for (int i = 0; i < 7; i++) {
//                String formula = "IF(M" + (row2 + 2) + "=0,\"\",M" + (row2 + 1) + "/M" + (row2 + 2) + ")";
//                fillSequenceFormula(style, curretSheet, "M", row2, formula, dataMap.get("dataNo").get(0).size());
//                row2 = row2 + 3;
//            }

            //第三个表格
            int row3 = 63;
            for (int i = 0; i < 7; i++) {
                String formula = "SUM(M" + (row3 - 2) + ":M" + (row3 - 1) + ")";
                fillSequenceFormula(style, curretSheet, "M", row3, formula, dataMap.get("businessNo1").get(0).size());
                row3 = row3 + 4;
            }

            //第四個表格
            int row4 = 97;
            for (int i = 0; i < 7; i++) {
                String formula = "SUM(M" + (row4 - 2) + ":M" + (row4 - 1) + ")";
                fillSequenceFormula(style, curretSheet, "M", row4, formula, dataMap.get("dataNo").get(0).size());
                row4 = row4 + 3;
            }


            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return writer;
    }

    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 月度经费模板
     */
    public static XlsxTemplateWriterUtil writeDataFromMonthlyFee(List<String> replayList,
                                                                 String year2,
                                                                 Map<String, List<List<Map<String, Object>>>> dataMap,
                                                                 String fileLocation,
                                                                 String inventory) {
        File file = new File(fileLocation);
        XlsxTemplateWriterUtil writer = null;
        //只有一个sheet页
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList("1"), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;
            XSSFCellStyle style = setAndReturnStyle(wb);

            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);

            writeCurrentCell(wb, writer.getCellPosition("{time1}"), curretSheet, year2);

            for (String value : replayList) {
                List<List<Map<String, Object>>> D101 = dataMap.get(value);
                replayAndWriterWiththousand2(curretSheet, D101, "{" + value + "}", writer);
//                replayAndWriter2(curretSheet,D101, "{"+value+"}", writer);
            }
            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;


    }

    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 月度损益
     */
    public static XlsxTemplateWriterUtil writeDataFromMonthlyFeePL(List<String> replayList2,
                                                                   List<String> replayList,
                                                                   String monthStr,
                                                                   Map<String, List<List<Map<String, Object>>>> dataMap,
                                                                   String fileLocation,
                                                                   String dataType) {
        File file = new File(fileLocation);
        XlsxTemplateWriterUtil writer = null;
        //只有一个sheet页
        try {

            Map<String, String> dateMap = new HashMap<>();
            dateMap.put("January_forecast", "1月");
            dateMap.put("February_forecast", "2月");
            dateMap.put("March_forecast", "3月");
            dateMap.put("April_forecast", "4月");
            dateMap.put("May_forecast", "5月");
            dateMap.put("June_forecast", "6月");
            dateMap.put("July_forecast", "7月");
            dateMap.put("August_forecast", "8月");
            dateMap.put("September_forecast", "9月");
            dateMap.put("October_forecast", "10月");
            dateMap.put("November_forecast", "11月");
            dateMap.put("December_forecast", "12月");
            dateMap.put("January_forecast_b", "1月");
            dateMap.put("February_forecast_b", "2月");
            dateMap.put("March_forecast_b", "3月");
            String month = dateMap.get(monthStr);
            String sheetNameSuffix = "";

            writer = new XlsxTemplateWriterUtil(file, Arrays.asList("1"), 1);

            if (dataType.equals("B81")) {
                sheetNameSuffix = "基本预算";
                if (monthStr.equals("January_forecast") || monthStr.equals("February_forecast") || monthStr.equals("March_forecast")) {
                    sheetNameSuffix = "下期预测";
                }
            } else {
                sheetNameSuffix = "修正预算";
                if (month.equals("7月") || month.equals("8月") || month.equals("9月")) {
                    sheetNameSuffix = "上期预测";
                }
            }

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;

            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);

            curretSheet.getRow(420).getCell(6).setCellValue(month);
            curretSheet.getRow(423).getCell(6).setCellValue(sheetNameSuffix);
            wb.getCTWorkbook().getSheets().getSheetArray(0).setName(month + "损益" + sheetNameSuffix);

            for (String value : replayList) {
                List<List<Map<String, Object>>> D101 = dataMap.get(value);
                if (D101 == null || D101.size() == 0 || null == D101.get(0) || D101.get(0).size() == 0) {
                    writeCurrentCell(wb, writer.getCellPosition("{" + value + "}"), curretSheet, "");
                } else {
                    replayAndWriterWiththousand2(curretSheet, D101, "{" + value + "}", writer);
                }

            }

            for (String value : replayList2) {
                List<List<Map<String, Object>>> D101 = dataMap.get(value);
                if (D101 == null || D101.size() == 0 || null == D101.get(0) || D101.get(0).size() == 0) {
                    writeCurrentCell(wb, writer.getCellPosition("{" + value + "}"), curretSheet, "");
                } else {
                    replayAndWriterWiththousand2(curretSheet, D101, "{" + value + "}", writer);
                }

            }

            wb.setForceFormulaRecalculation(true);
            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;


    }


    public static void writeCurrentCell(XSSFWorkbook wb,
                                        Map<String, Integer> total2, XSSFSheet curretSheet, String value) {

        Integer roww = total2.get(ROW_INDEX);
        Integer colw = total2.get(COL_INDEX);

        XSSFRow row = curretSheet.getRow(roww);
        XSSFCell cell = row.getCell(colw);
        cell.setCellValue(value);
    }

    public static void writeCurrentCell2(XSSFWorkbook wb,
                                         Map<String, Integer> total2, XSSFSheet curretSheet, BigDecimal value) {

        Integer roww = total2.get(ROW_INDEX);
        Integer colw = total2.get(COL_INDEX);

        XSSFRow row = curretSheet.getRow(roww);
        XSSFCell cell = row.getCell(colw);
        cell.setCellValue(value.doubleValue());
    }

    public static XlsxTemplateWriterUtil writeDataFromSocietyProfit(String ipo, Map<String, List<List<Map<String, Object>>>> dataMap,
                                                                    String year,
                                                                    List<String> replayList,
                                                                    String fileLocation,
                                                                    String society_profit,
                                                                    int i,
                                                                    String name) {
        File file = new File(fileLocation);
        XlsxTemplateWriterUtil writer = null;
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList("1"), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;

            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);
            XSSFCellStyle style = setAndReturnStyle(wb);
//            writer.selectSheet(0);

            //替换时间
//            Map<String, Integer> total2 = writer.getCellPosition("{time1}");
//            Integer roww = total2.get(ROW_INDEX);
//            Integer colw = total2.get(COL_INDEX);
//
//            XSSFRow row = curretSheet.getRow(roww);
//            XSSFCell cell = row.getCell(colw);
//            cell.setCellValue(year);
//            curretSheet.shiftRows(4,50,2);

//            Map<String, Integer> total3start55 = writer.getCellPosition("{data1start}");

//            Map<String, Integer> cellPosition = writer.getCellPosition("{time1}");
            writeCurrentCell(wb, writer.getCellPosition("{time1}"), curretSheet, year);
            writeCurrentCell(wb, writer.getCellPosition("{time2}"), curretSheet, year);
            writeCurrentCell(wb, writer.getCellPosition("{name1}"), curretSheet, name);
            if (!ipo.equals("IPO损益")) {
                writeCurrentCell(wb, writer.getCellPosition("{time3}"), curretSheet, year);
            }


            List<List<Map<String, Object>>> data1 = dataMap.get("data1");
            List<List<Map<String, Object>>> data2 = dataMap.get("data2");
            List<List<Map<String, Object>>> data3 = dataMap.get("data3");


//            curretSheet.shiftRows(4,curretSheet.getLastRowNum(),9,true,false);


            doShiftRows(writer, curretSheet, 13, "{data1}",
                    4, curretSheet.getLastRowNum(), data1.size() - 1);

            //将后面数据复制下移

            Map<String, Integer> total3start = writer.getCellPosition("{data1start}");

//            move(5, 30, data1.size() - 1, curretSheet, wb);


            replayAndWriterWiththousand2(curretSheet, data1, "{data1}", writer);

            Map<String, Integer> total3End = writer.getCellPosition("{data1end}");
            //向后统计多少行
            int totalCol3 = 7;
            doTatal(total3start, total3End, writer, curretSheet, style, totalCol3);


            //第二个表格
            Map<String, Integer> data2start = writer.getCellPosition("{data2start}");

            //将后面数据复制下移
//            move(11 + data1.size() - 1, 30 + data1.size() - 1, data2.size() - 1, curretSheet, wb);

            doShiftRows(writer, curretSheet, 13, "{data2}",
                    10 + data1.size() - 1, curretSheet.getLastRowNum(), data2.size() - 1);

            replayAndWriterWiththousand2(curretSheet, data2, "{data2}", writer);

            Map<String, Integer> data2end = writer.getCellPosition("{data2end}");
            //向后统计多少行
            int totalCol2 = 7;
            doTatal(data2start, data2end, writer, curretSheet, style, totalCol2);


            if (!ipo.equals("IPO损益")) {
                replayAndWriterWiththousand2(curretSheet, data3, "{data3}", writer);
            }
            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;
    }

    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 人员汇总格式
     */
    public static XlsxTemplateWriterUtil writeDataFromNumber(List<String> replayList,
                                                             List<String> replayList2,
                                                             String yearStr, Map<String, List<List<Map<String, Object>>>> dataMap, String fileLocation, String number) {
        File file = new File(fileLocation);
        XlsxTemplateWriterUtil writer = null;
        try {
            writer = new XlsxTemplateWriterUtil(file, Arrays.asList("1"), 1);

            //获取XSSFWorkbook 对象
            XSSFWorkbook wb = writer.wb;
            XSSFCellStyle style = setAndReturnStyle(wb);

            //获取sheet
            XSSFSheet curretSheet = wb.getSheetAt(0);
            writer.selectSheet(0);

//            writeCurrentCell(wb, writer.getCellPosition("{time1}"),curretSheet ,year );
//            writeCurrentCell(wb, writer.getCellPosition("{time2}"),curretSheet ,year );
//            writeCurrentCell(wb, writer.getCellPosition("{time3}"),curretSheet ,year );


            List<List<Map<String, Object>>> data1 = dataMap.get("data1");
            if (data1 == null || data1.size() == 0) {
                writeCurrentCell(wb, writer.getCellPosition("{data1}"), curretSheet, "");
            } else {
                //将后面数据复制下移
//                move(7, 130, data1.size() - 1, curretSheet, wb);

                doRightCopy("I", 4, 6, dataMap.get("dataNo2").get(0).size() - 1, curretSheet, wb);

                doShiftRows(writer, curretSheet, 7 + (dataMap.get("dataNo2").get(0).size() - 1), "{data1}",
                        6, curretSheet.getLastRowNum(), data1.size() - 1);

                replayAndWriter2(curretSheet, data1, "{data1}", writer);
            }


            List<List<Map<String, Object>>> data2 = dataMap.get("data2");
            if (data2 == null || data2.size() == 0) {
                writeCurrentCell(wb, writer.getCellPosition("{data2}"), curretSheet, "");
            } else {
                //第二个表格
                //将后面数据复制下移
//                move(12 + data1.size() - 1, 130 + data1.size() - 1, data2.size() - 1, curretSheet, wb);
//                replayAndWriter(data2, "{data2}", writer);

                doRightCopy("I", 9 + data1.size() - 1, 11 + data1.size() - 1, dataMap.get("dataNo3").get(0).size() - 1, curretSheet, wb);

                doShiftRows(writer, curretSheet, 7 + (dataMap.get("dataNo3").get(0).size() - 1), "{data2}",
                        11 + data1.size() - 1, curretSheet.getLastRowNum(), data2.size() - 1);

                replayAndWriter2(curretSheet, data2, "{data2}", writer);


            }


            List<List<Map<String, Object>>> data3 = dataMap.get("data3");
            if (data3 == null || data3.size() == 0) {
                writeCurrentCell(wb, writer.getCellPosition("{data3}"), curretSheet, "");
            } else {
                //第三个表格
                //将后面数据复制下移
//                move(18 + data1.size() - 1 + data2.size() - 1, 130 + data1.size() - 1 + data2.size() - 1, data3.size() - 1, curretSheet, wb);
//                replayAndWriter(data3, "{data3}", writer);


                doRightCopy("I", 15 + data1.size() - 1 + data2.size() - 1, 17 + data1.size() - 1 + data2.size() - 1,
                        dataMap.get("dataNo4").get(0).size() - 1, curretSheet, wb);

                doShiftRows(writer, curretSheet, 7 + (dataMap.get("dataNo4").get(0).size() - 1), "{data3}",
                        17 + data1.size() - 1 + data2.size() - 1, curretSheet.getLastRowNum(), data3.size() - 1);

                replayAndWriter2(curretSheet, data3, "{data3}", writer);

            }


//            replayList

            doRightCopy("I", 22 + data1.size() + data2.size() + data3.size() - 3, 65 + data1.size() + data2.size() + data3.size() - 3,
                    dataMap.get("dataNo").get(0).size() - 1, curretSheet, wb);

            doRightCopy("I", 70 + data1.size() + data2.size() + data3.size() - 3, 113 + data1.size() + data2.size() + data3.size() - 3,
                    dataMap.get("dataNo1").get(0).size() - 1, curretSheet, wb);

            for (String value : replayList) {
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++"+value);
                List<List<Map<String, Object>>> D101 = dataMap.get(value);
                if (D101 == null || D101.size() == 0) {
                    writeCurrentCell(wb, writer.getCellPosition("{" + value + "}"), curretSheet, "");
                } else {
                    replayAndWriter2(curretSheet, D101, "{" + value + "}", writer);
                }

            }

            //公式统计
            for (String value : replayList2) {
                int totalCol = dataMap.get("q3_A1").get(0).size();
                Map<String, Integer> total2End = writer.getCellPosition("{" + value + "}");
                if (value.contains("_A4")) {
                    newDoTatal(3, total2End, writer, curretSheet, style, totalCol);
                } else if (value.contains("_A6")) {
                    newDoTatal(2, total2End, writer, curretSheet, style, totalCol);
                }
            }


            //doTatal

            //做统计
//            Map<String, Integer> total2s = writer.getCellPosition("{dosums}");
//            Map<String, Integer> total2End = writer.getCellPosition("{dosume}");
//            //向后统计多少行
//            int totalCol = 2;
//            newDoTatal(3,total2End, writer, curretSheet, style, totalCol);


            int row3 = 6;
            for (int i = 0; i < data1.size(); i++) {
                String formula = "SUM(I" + (row3) + ":AZ" + (row3) + ")";
                fillSequenceFormula(style, curretSheet, "H", row3, formula, 1);
                row3 = row3 + 1;
            }


            row3 = 11 + data1.size() - 1;  //118 11
            for (int i = 0; i < data2.size(); i++) {
                String formula = "SUM(I" + (row3) + ":AZ" + (row3) + ")";
                fillSequenceFormula(style, curretSheet, "H", row3, formula, 1);
                row3 = row3 + 1;
            }

            row3 = 17 + data1.size() - 1 + data2.size() - 1;  //118 11
            for (int i = 0; i < data3.size(); i++) {
                String formula = "SUM(I" + (row3) + ":AZ" + (row3) + ")";
                fillSequenceFormula(style, curretSheet, "H", row3, formula, 1);
                row3 = row3 + 1;
            }


            wb.setForceFormulaRecalculation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;
    }

    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 从某一行开始，到某一行结束，整体下移size个长度
     */
    private static void move(int from,
                             int end,
                             int size,
                             XSSFSheet curretSheet,
                             XSSFWorkbook wb) {

        System.out.println(" int from ," +
                "            int end," +
                "            int size," + from + "---" + end + "---" + size);

        if (size >= 1) {
            for (int m = end; m >= from; m--) {
                //获取行   27行--对应获取ECCEL26
                XSSFRow workRow5 = curretSheet.getRow(m - 1);
                if (null == workRow5) {
                    continue;
                }
                XSSFRow workRow10 = curretSheet.createRow(m - 1 + size);
                //复制行
                copyRow(workRow5, workRow10, curretSheet, wb);
            }
        }


    }


    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 替换并且写入
     */
    private static void replayAndWriter(List<List<Map<String, Object>>> data, String rebatePolicyOne, XlsxTemplateWriterUtil writer) {
        Map<String, Integer> cellPosition3 = writer.getCellPosition(rebatePolicyOne);
        if (null == cellPosition3) {
            return;
        }
        writer.writeRows(data, cellPosition3.get(ROW_INDEX), cellPosition3.get(COL_INDEX));
    }

    private static void replayAndWriter2(XSSFSheet curretSheet, List<List<Map<String, Object>>> data, String rebatePolicyOne, XlsxTemplateWriterUtil writer) {
        Map<String, Integer> cellPosition3 = writer.getCellPosition(rebatePolicyOne);
        if (null == cellPosition3) {
            return;
        }
        writer.writeRows2(curretSheet, data, cellPosition3.get(ROW_INDEX), cellPosition3.get(COL_INDEX));
    }


    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 替换并且写入
     */
    private static void replayAndWriterWiththousand(List<List<Map<String, Object>>> data, String rebatePolicyOne, XlsxTemplateWriterUtil writer) {
        Map<String, Integer> cellPosition3 = writer.getCellPosition(rebatePolicyOne);
        if (null == cellPosition3) {
            return;
        }
        writer.writeRowsWiththousand(data, cellPosition3.get(ROW_INDEX), cellPosition3.get(COL_INDEX));
    }

    private static void replayAndWriterWiththousand2(XSSFSheet curretSheet, List<List<Map<String, Object>>> data, String rebatePolicyOne, XlsxTemplateWriterUtil writer) {
        Map<String, Integer> cellPosition3 = writer.getCellPosition(rebatePolicyOne);
        if (null == cellPosition3) {
            return;
        }
        writer.writeRowsWiththousand2(curretSheet, data, cellPosition3.get(ROW_INDEX), cellPosition3.get(COL_INDEX));
    }

    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 替换并且写入
     */
    private static void replayAndWriterWithmillion(List<List<Map<String, Object>>> data, String rebatePolicyOne, XlsxTemplateWriterUtil writer) {
        Map<String, Integer> cellPosition3 = writer.getCellPosition(rebatePolicyOne);
        if (null == cellPosition3) {
            return;
        }
        writer.writeRowsWithmillion(data, cellPosition3.get(ROW_INDEX), cellPosition3.get(COL_INDEX));
    }

    /**
     * @author by cld
     * @date 2019/5/13 13:38
     * @description: 公式合计
     */
    private static void doTatal(Map<String, Integer> total2start,
                                Map<String, Integer> totalEnd,
                                XlsxTemplateWriterUtil writer,
                                XSSFSheet curretSheet,
                                XSSFCellStyle style,
                                int totalCol) {


        for (int i = 0; i < totalCol; i++) {
            //公式合计

            //从某一行开始
            Integer colStart = total2start.get(COL_INDEX) + 1 + i;
            String colString = toStr(colStart);
            Integer rowStart = total2start.get(ROW_INDEX) + 1;
//            System.out.println("colStart"+"-------"+colString);
//            System.out.println("rowStart"+"-------"+rowStart);

            String mulaFrom = colString.concat(rowStart + "");

            //结束位置
            Integer colEnd = totalEnd.get(COL_INDEX) + 1 + i;
            Integer rowEnd = totalEnd.get(ROW_INDEX) + 1 - 1;
            String colEndString = toStr(colEnd);
//            System.out.println("colEnd"+"-------"+colEndString);
//            System.out.println("rowEnd"+"-------"+rowEnd);

            String mulaEnd = colEndString.concat(rowEnd + "");

            String formula = "SUM(" + mulaFrom + ":" + mulaEnd + ")";

            //写的位置
            Integer colWriter = totalEnd.get(COL_INDEX) + 1 + i;
            Integer rowWriter = totalEnd.get(ROW_INDEX) + 1;


            XSSFRow row = curretSheet.getRow(rowWriter - 1);
            XSSFCell cell1 = row.getCell(colWriter - 1);
            if (cell1 == null) {
                cell1 = row.createCell(colWriter - 1);
            }


//            XSSFWorkbook wb = writer.wb;
//            XSSFCellStyle style2 = wb.createCellStyle();
//            style2.setAlignment(HorizontalAlignment.LEFT);
//            style2.setBorderTop(CellStyle.BORDER_THIN);
//            style2.setBorderRight(CellStyle.BORDER_THIN);
//            style2.setBorderBottom(CellStyle.BORDER_THIN);
//            style2.setBorderLeft(CellStyle.BORDER_THIN);

//            System.out.println("公式====="+formula);
//            cell1.setCellStyle(style2);
            cell1.setCellFormula(formula);

        }


    }

//newDoTatal

    private static void newDoTatal(
            int size,
            Map<String, Integer> totalEnd,
            XlsxTemplateWriterUtil writer,
            XSSFSheet curretSheet,
            XSSFCellStyle style,
            int totalCol) {
        int newsize = size - 1;

        for (int i = 0; i < totalCol; i++) {
            //公式合计
//            String mulaFrom = colString.concat(rowStart + "");

            //结束位置
            Integer colEnd = totalEnd.get(COL_INDEX) + 1 + i;
            Integer rowEnd = totalEnd.get(ROW_INDEX) + 1 - 1;
            String colEndString = toStr(colEnd);
//            System.out.println("colEnd"+"-------"+colEndString);
//            System.out.println("rowEnd"+"-------"+rowEnd);

            String mulaEnd = colEndString.concat(rowEnd + "");


            String mulaFrom = colEndString.concat(rowEnd - newsize + "");

            String formula = "SUM(" + mulaFrom + ":" + mulaEnd + ")";

            //写的位置
            Integer colWriter = totalEnd.get(COL_INDEX) + 1 + i;
            Integer rowWriter = totalEnd.get(ROW_INDEX) + 1;


            XSSFRow row = curretSheet.getRow(rowWriter - 1);
            XSSFCell cell1 = row.getCell(colWriter - 1);
            if (null == cell1) {
                cell1 = row.createCell(colWriter - 1);
            }

//            XSSFWorkbook wb = writer.wb;
//            XSSFCellStyle style2 = wb.createCellStyle();
//            style2.setAlignment(HorizontalAlignment.LEFT);
//            style2.setBorderTop(CellStyle.BORDER_THIN);
//            style2.setBorderRight(CellStyle.BORDER_THIN);
//            style2.setBorderBottom(CellStyle.BORDER_THIN);
//            style2.setBorderLeft(CellStyle.BORDER_THIN);

//            System.out.println("公式====="+formula);
//            cell1.setCellStyle(style2);

//            System.out.println("公式====="+formula);
//            cell1.setCellStyle(style);
            cell1.setCellFormula(formula);
        }


    }


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

    //c3,
    public static String nextStr(String str) {
        int i = toNum(str);
        return toStr(i + 1);
    }

    /**
     * @param formula
     * @return 自动调整公式
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

    public static void main(String[] args) {

        System.out.println(toNum("K"));


//        try {
//             File file = new File("D:\\_TES-work\\BMS\\2_3Code\\tes\\tes-manager\\src\\main\\webapp\\WEB-INF\\template\\Early_Initial_IPO.xlsx");
////            File file = new File("C:\\Users\\syanl\\Desktop\\Midterm_Direct.xlsx");
//
//            XlsxTemplateWriterUtil write = new XlsxTemplateWriterUtil(file, Arrays.asList("测试"), 1);
//            write.wb.getCTWorkbook().getSheets().getSheetArray(0).setName("哈哈");
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//            Date date = simpleDateFormat.parse("20180205");
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
//
//
//            Map<String, Map<Integer, List<Map<String, Object>>>> locations = new HashMap<>();
//            Map<Integer, List<Map<String, Object>>> locMap = new HashMap<>();
//            List<Map<String, Object>> list = new ArrayList<>();
//            Map<String, Object> map = new HashMap<>();
//            map.put("1", "2");
//            list.add(map);
//            locMap.put(1, list);
//            locations.put("xx", locMap);
//            Map<String, Map<Integer, List<Map<String, Object>>>> hashMap = new HashMap<>();
//            for(String key : locations.keySet()){
//                Map<Integer, List<Map<String, Object>>> llMap = new HashMap<>();
//                llMap.putAll(locations.get(key));
//                hashMap.put(key, llMap);
//            }
//            // hashMap.putAll(locations);
//            hashMap.put("ll", locMap);
//            // hashMap.remove("xx");
//            hashMap.get("xx").remove(1);
//            System.out.println(dateFormat.format(date));
//            System.out.println(hashMap);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
