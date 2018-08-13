package com.jc.cluz.dataprovider.configInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by honglb on 2018/7/3.
 * 根据excel后缀选择不同方法实现excel内容的读取
 */
public class ExcelDual {
    public String fName;
    public String cName;
    int rows ;
    int columns;
    String suffix;
    //

    ArrayList<String> arrkey = new ArrayList<String>();



    public ExcelDual(String fileName, String suffix, String caseName) {
        super();
        this.fName = fileName;
        this.suffix =suffix;
        this.cName = caseName;
    }
        public File fileSelect(){
            String srcdocument = System.getProperty("user.dir") + "/src/test/resources/testdata/" + fName + "." + suffix;
            File srcFile = new File(srcdocument);
            return srcFile;
        }

        public FileInputStream selectFile() throws FileNotFoundException {
            File srcFile = fileSelect();
            // 加载文件
            FileInputStream fis = new FileInputStream(srcFile);
            return fis;
        }

        public  String fileNameJudge(){
            String fName = fileSelect().getName();
            String suffix = fName.substring(fName.lastIndexOf(".") + 1);
            return suffix;
        }

        public Object[][] getExcelData() throws BiffException, IOException {
            HashMap<String, String>[][] arrmap = null;
//            String suffix = fileNameJudge();
            try {
                // 指定excel的路径
                if (Objects.equals(suffix, "xls")) {
                    HSSFWorkbook wb = new HSSFWorkbook(selectFile());
                    HSSFSheet sh1 = wb.getSheet(cName);
                    rows = sh1.getLastRowNum();
                    columns = sh1.getRow(0).getPhysicalNumberOfCells();
                    // init data
                    arrmap = new HashMap[rows - 1][1];
                    // i 控制行
                    if (rows > 1) {
                        for (int i = 0; i < rows - 1; i++) {
                            arrmap[i][0] = new HashMap<>();
                        }
                    } else {
                        System.out.println("excel中没有数据");
                    }

                    // 获得首行的列名，作为hashmap的key值
                    for (int c = 0; c < columns; c++) {
                        String cellvalue = sh1.getRow(0).getCell(c).getStringCellValue();
                        arrkey.add(cellvalue);
                    }
                    // 遍历所有的单元格的值添加到hashmap中
                    for (int r = 1; r < rows; r++) {
                        for (int c = 0; c < columns; c++) {
                            HSSFCell cell = sh1.getRow(r).getCell(c);
                            String cellvalue = cell == null ? null : cell.getStringCellValue();
                            arrmap[r - 1][0].put(arrkey.get(c), cellvalue);
                        }
                    }
                } else if (Objects.equals(suffix, "xlsx")) {
                    XSSFWorkbook wb = new XSSFWorkbook(selectFile());
                    XSSFSheet sh1 = wb.getSheet(cName);
                    rows = sh1.getLastRowNum();
                    columns = sh1.getRow(0).getPhysicalNumberOfCells();
                    // init data
                    arrmap = new HashMap[rows - 1][1];
                    // i 控制行
                    if (rows > 1) {
                        for (int i = 0; i < rows - 1; i++) {
                            arrmap[i][0] = new HashMap<>();
                        }
                    } else {
                        System.out.println("excel中没有数据");
                    }

                    // 获得首行的列名，作为hashmap的key值
                    for (int c = 0; c < columns; c++) {
                        String cellvalue = sh1.getRow(0).getCell(c).getStringCellValue();
                        arrkey.add(cellvalue);
                    }
                    // 遍历所有的单元格的值添加到hashmap中
                    for (int r = 1; r < rows; r++) {
                        for (int c = 0; c < columns; c++) {
                            XSSFCell cell = sh1.getRow(r).getCell(c);
                            String cellvalue = cell == null ? null : cell.getStringCellValue();
                            arrmap[r - 1][0].put(arrkey.get(c), cellvalue);
                        }
                        System.out.println(arrmap[r - 1][0]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error:" + e.getMessage());
            }
            return arrmap;
        }
}

