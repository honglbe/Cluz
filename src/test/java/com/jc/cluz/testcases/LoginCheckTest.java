package com.jc.cluz.testcases;

import com.jc.cluz.dataprovider.configInfo.ExcelData;
import com.jc.cluz.service.LoginService;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by honglb on 2018/6/28.
 */
@SpringBootTest
public class LoginCheckTest extends AbstractTestNGSpringContextTests {

    @Resource
    LoginService loginService;
    @DataProvider(name = "loginProvider")
    public Object[][] loginProvider() throws BiffException, IOException {
        ExcelData e = new ExcelData("loginpage", "loginpage");
        return e.getExcelData();
    }

    @Test(dataProvider = "loginProvider")
    public void testLoginCheck(HashMap<String, String> data){
        String username = data.get("username");
        String password = data.get("password");
        loginService.loginPage();
        loginService.loginPageCheck(username,password);
    }
}