package com.jc.cluz.testcases;

import com.jc.cluz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by honglb on 2018/6/27.
 */
@SpringBootTest
public class LoginSsoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    LoginService loginService;

    @Test
    public void testLoginSso(){
        loginService.loginPage();
        loginService.loginSso();
    }
}

