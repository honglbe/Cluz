package com.jc.cluz.testcases;

import com.jc.cluz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

/**
 * Created by honglb on 2018/7/26.
 */
@SpringBootTest
@Component
public class LogingAppTest extends AbstractTestNGSpringContextTests {
    @Autowired
    LoginService loginService;
    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        loginService.connectIphonePhone();
    }

   @Test
    public void testApp() throws MalformedURLException {
           //loginService.firstPage();
   }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        //loginService.disconnectPhone();
    }
}
