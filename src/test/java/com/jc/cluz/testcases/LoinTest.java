package com.jc.cluz.testcases;

import com.jc.cluz.dataprovider.configInfo.DataUtils;
import com.jc.cluz.dataprovider.configInfo.ExcelDual;
import com.jc.cluz.driver.mouthpiece.ApiActions;
import com.jc.cluz.service.LoginService;
import jxl.read.biff.BiffException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import com.jc.cluz.dataprovider.configInfo.DataUtils;

/**
 * Created by honglb on 2018/7/3.
 */
@SpringBootTest
public class LoinTest extends AbstractTestNGSpringContextTests {
//
    @Resource
    ApiActions apiActions;
//    LoginService loginService;

    @Autowired
    //DataUtils dataUtils;

//
//    @DataProvider(name = "loginProvider")
//    public Object[][] loginProvider() throws BiffException, IOException {
//        ExcelDual e = new ExcelDual("test", "xlsx", "test");
//        return e.getExcelData();
//    }
//
//    @Test(dataProvider = "loginProvider")
//    public void testLoin(HashMap<String, String> data) {
//        try {
//            String username = data.get("username").toString();
//            String password = data.get("password").toString();
//            loginService.loginPage();
//            loginService.loginPageCheck(username,password);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    @Test
    public void testLoin(){
        try {
            String url = "https://app.tanwan.com/htmlcode/10887.html";
            //apiActions.sendGet(url);
            apiActions.doGet(url);
            String osName = System.getProperty("os.name");
            System.out.print("操作系统是：" + osName);
        }catch (Exception e){
            e.printStackTrace();
        }

//        String sql = "SELECT userid, username, password, name FROM USER";
//        try {
//            System.out.print(dataUtils.dbSearch(sql));
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }
}
