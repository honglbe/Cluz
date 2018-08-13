package com.jc.cluz.testcases;

import com.jc.cluz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by honglb on 2018/6/30.
 */
@SpringBootTest
public class LoginTest extends AbstractTestNGSpringContextTests {
    @Autowired
    LoginService loginService;

//    @Test
//    public void testLoginSso(){
//        loginService.loginSso();
//    }
//
//    @Test
//    public void testLoginUser() {
//        loginService.loginUser();
//        try {
//            Thread.sleep(30000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Test
    public void testLoginJcy(){
        loginService.loginJcy();
        try {
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
