package com.jc.cluz.testcases;

import com.jc.cluz.service.LoginService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import javax.annotation.Resource;

/**
 * Created by honglb on 2018/6/30.
 */
@SpringBootTest
public class LoginFinanceTest extends AbstractTestNGSpringContextTests {
    @Resource
    LoginService loginService;

    @Test
    public void testLoginFinance() {
        loginService.loginFinance();
        try {
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
