package com.jc.cluz;

import com.jc.cluz.driver.selenide.Actions;
import com.jc.cluz.driver.selenium.SeleActions;
import com.jc.cluz.listener.CaseListener;
import com.jc.cluz.model.CluzFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;

import javax.annotation.Resource;


@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    static {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    @Resource
    Actions actions;
    @Resource
    SeleActions seleActions;

    @AfterMethod
    public void tearDown() {
        catchScreenshot();
        actions.closeBrowser();
    }

    /**
     * 失败用例重试3次依旧失败则截图保存
     */
    private void catchScreenshot(){
        CaseListener caseListener = new CaseListener();
        String failCaseName = caseListener.failCaseName;
        boolean caseResult = caseListener.failResult;
        if(caseResult) {
            try {
                if (CluzFactory.cluzModel.getDriverType() == 0) {
                    actions.catchScreenshot(failCaseName);
                } else if (CluzFactory.cluzModel.getDriverType() == 1) {
                    seleActions.catchScreenshot(failCaseName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            caseListener.failCaseName = null;
            caseListener.failResult = false;
        }
    }
}
