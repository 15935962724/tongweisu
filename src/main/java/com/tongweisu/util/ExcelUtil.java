package com.tongfu.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

    public static void main(String[] args) {

        readExcel("C:/Users/83944/Desktop/1111.xlsx");

    }


    public static String readExcel(String path) {

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> activationCode = new ArrayList<>();
        File file = new File(path);
        FileInputStream fis = null;
        Workbook workBook = null;
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                workBook = WorkbookFactory.create(fis);
//                int numberOfSheets = workBook.getNumberOfSheets();
                int numberOfSheets = 1;//读取第一个sheet工作表的内容
                // sheet工作表
                for (int s = 0; s < numberOfSheets; s++) {
                    Sheet sheetAt = workBook.getSheetAt(s);
                    //获取工作表名称
                    String sheetName = sheetAt.getSheetName();
                    System.out.println("工作表名称：" + sheetName);
                    // 获取当前Sheet的总行数
                    int rowsOfSheet = sheetAt.getPhysicalNumberOfRows();
                    System.out.println("当前表格的总行数:" + rowsOfSheet);
                    // 第一行
                    Row row0 = sheetAt.getRow(0);
//                    int physicalNumberOfCells = sheetAt.getRow(0).getPhysicalNumberOfCells();
//                    String[] title = new String[physicalNumberOfCells];
//                    for (int i = 0; i < physicalNumberOfCells; i++) {
//                        title[i] = row0.getCell(i).getStringCellValue();
//                    }
                    String cellValue = "";
                    for (int r = 0; r < rowsOfSheet; r++) {
                        Row row = sheetAt.getRow(r);
                        if (row == null) {
                            continue;
                        } else {
                            int rowNum = row.getRowNum() + 1;
                            System.out.println("当前行:" + rowNum);
                            // 总列(格)
                            Cell cell = row.getCell(0);
                            switch (cell.getCellType()) {
                                case NUMERIC: // 数字
                                    //short s = cell.getCellStyle().getDataFormat();
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                                        SimpleDateFormat sdf = null;
                                        // 验证short值
                                        if (cell.getCellStyle().getDataFormat() == 14) {
                                            sdf = new SimpleDateFormat("yyyy/MM/dd");
                                        } else if (cell.getCellStyle().getDataFormat() == 21) {
                                            sdf = new SimpleDateFormat("HH:mm:ss");
                                        } else if (cell.getCellStyle().getDataFormat() == 22) {
                                            sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                        } else {
                                            throw new RuntimeException("日期格式错误!!!");
                                        }
                                        Date date = cell.getDateCellValue();
                                        cellValue = sdf.format(date);
                                    } else if (cell.getCellStyle().getDataFormat() == 0) {//处理数值格式
                                        cell.setCellType(CellType.STRING);
                                        cellValue = String.valueOf(cell.getRichStringCellValue().getString());
                                    }
                                    break;
                                case STRING: // 字符串
                                    cellValue = String.valueOf(cell.getStringCellValue());
                                    break;
                                case BOOLEAN: // Boolean
                                    cellValue = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                case FORMULA: // 公式
                                    cellValue = String.valueOf(cell.getCellFormula());
                                    break;
                                case BLANK: // 空值
                                    cellValue = "";
                                    break;
                                case ERROR: // 故障
                                    cellValue = "非法字符";
                                    break;
                                default:
                                    cellValue = "未知类型";
                                    break;
                            }

//                            System.out.println(cellValue);
                            activationCode.add(cellValue);
//                            System.out.println("第" + rowNum + "行，第一列[" + title[0] + "]数据错误！");

                        }
                    }
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println(JSON.toJSON(activationCode).toString());
        return JSON.toJSON(activationCode).toString();
    }

    public static String readExcel(File file) {

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> activationCode = new ArrayList<>();
//        File file = new File(path);
        FileInputStream fis = null;
        Workbook workBook = null;
        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                workBook = WorkbookFactory.create(fis);
//                int numberOfSheets = workBook.getNumberOfSheets();
                int numberOfSheets = 1;//读取第一个sheet工作表的内容
                // sheet工作表
                for (int s = 0; s < numberOfSheets; s++) {
                    Sheet sheetAt = workBook.getSheetAt(s);
                    //获取工作表名称
                    String sheetName = sheetAt.getSheetName();
                    System.out.println("工作表名称：" + sheetName);
                    // 获取当前Sheet的总行数
                    int rowsOfSheet = sheetAt.getPhysicalNumberOfRows();
                    System.out.println("当前表格的总行数:" + rowsOfSheet);
                    // 第一行
                    Row row0 = sheetAt.getRow(0);
//                    int physicalNumberOfCells = sheetAt.getRow(0).getPhysicalNumberOfCells();
//                    String[] title = new String[physicalNumberOfCells];
//                    for (int i = 0; i < physicalNumberOfCells; i++) {
//                        title[i] = row0.getCell(i).getStringCellValue();
//                    }
                    String cellValue = "";
                    for (int r = 0; r < rowsOfSheet; r++) {
                        Row row = sheetAt.getRow(r);
                        if (row == null) {
                            continue;
                        } else {
                            int rowNum = row.getRowNum() + 1;
                            System.out.println("当前行:" + rowNum);
                            // 总列(格)
                            Cell cell = row.getCell(0);
                            switch (cell.getCellType()) {
                                case NUMERIC: // 数字
                                    //short s = cell.getCellStyle().getDataFormat();
                                    if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                                        SimpleDateFormat sdf = null;
                                        // 验证short值
                                        if (cell.getCellStyle().getDataFormat() == 14) {
                                            sdf = new SimpleDateFormat("yyyy/MM/dd");
                                        } else if (cell.getCellStyle().getDataFormat() == 21) {
                                            sdf = new SimpleDateFormat("HH:mm:ss");
                                        } else if (cell.getCellStyle().getDataFormat() == 22) {
                                            sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                        } else {
                                            throw new RuntimeException("日期格式错误!!!");
                                        }
                                        Date date = cell.getDateCellValue();
                                        cellValue = sdf.format(date);
                                    } else if (cell.getCellStyle().getDataFormat() == 0) {//处理数值格式
                                        cell.setCellType(CellType.STRING);
                                        cellValue = String.valueOf(cell.getRichStringCellValue().getString());
                                    }
                                    break;
                                case STRING: // 字符串
                                    cellValue = String.valueOf(cell.getStringCellValue());
                                    break;
                                case BOOLEAN: // Boolean
                                    cellValue = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                case FORMULA: // 公式
                                    cellValue = String.valueOf(cell.getCellFormula());
                                    break;
                                case BLANK: // 空值
                                    cellValue = "";
                                    break;
                                case ERROR: // 故障
                                    cellValue = "非法字符";
                                    break;
                                default:
                                    cellValue = "未知类型";
                                    break;
                            }

//                            System.out.println(cellValue);
                            activationCode.add(cellValue);
//                            System.out.println("第" + rowNum + "行，第一列[" + title[0] + "]数据错误！");

                        }
                    }
                }
                if (fis != null) {
                    if (file != null) {
                        File del = new File(file.toURI());
                        del.delete();
                    }
                    fis.close();


                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println(JSON.toJSON(activationCode).toString());
        return JSON.toJSON(activationCode).toString();
    }



}
