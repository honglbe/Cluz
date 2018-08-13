package com.jc.cluz.dataprovider.configInfo;

import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by honglb on 2018/7/20.
 */
public class ExcelExport {

    @DataProvider(name = "login")
    public Object[][] loginProvider() throws BiffException, IOException {
        ExcelData e = new ExcelData("login", "login");
        return e.getExcelData();
    }

    @Test(dataProvider = "login")
    public void excelData(LinkedHashMap<String, String> data) {
        String fieldname = data.get("fieldname").toString();
        String necessary = data.get("necessary").toString();
        String type = data.get("type").toString();
        int length = Integer.parseInt(data.get("length").toString());
    }



    public void excelExport(String excelname, String sheetname)
    {
        //第一步创建workbook
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步创建sheet
        HSSFSheet sheet = wb.createSheet(sheetname);
//        //第三步创建行row:添加表头0行
//        HSSFRow row = sheet.createRow(0);
//        HSSFCellStyle style = wb.createCellStyle();
//
//
//        //第四步创建单元格
//        HSSFCell cell = row.createCell(0);         //第一个单元格
//        cell.setCellValue("姓名");                  //设定值
//        cell.setCellStyle(style);                   //内容居中
//
//        cell = row.createCell(1);                   //第二个单元格
//        cell.setCellValue("身份证");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(2);                   //第三个单元格
//        cell.setCellValue("错误状态");
//        cell.setCellStyle(style);
//
//        cell = row.createCell(3);                   //第四个单元格
//        cell.setCellValue("错误信息");
//        cell.setCellStyle(style);

        //第五步插入数据

        //第六步将生成excel文件保存到指定路径下
        try {
            String rootPath = System.getProperty("user.dir");
            String sourceFile = rootPath + "/src/test/resources/testdata/"
                    + excelname + ".xls";
            FileOutputStream fout = new FileOutputStream(sourceFile);
            wb.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel文件生成成功...");
    }

}
