package com.jc.cluz.dataprovider.configInfo;

import com.codeborne.selenide.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataMocker {
    public String browser;
    public String phone;

    /**
     * 设置需要启动的浏览器
     * @param value
     * @return
     */
    @Value("${browser}")
    private void setBrowser(String value) {
        this.browser = value.trim();
        specifyBrowser(browser);
    }

    /**
     * specify browser
     * @param browser
     */
    private void specifyBrowser(String browser){
        String osName = System.getProperty("os.name");
        String rootPath = System.getProperty("user.dir");
        if(osName.contains("Mac")){
            if(browser.equals("chrome")) {
                Configuration.browser = browser;
                Configuration.startMaximized=true;
                System.setProperty("webdriver."+browser+".driver", rootPath+"/src/main/resources/" + browser + "driver");
            }else{
                log.info("No match browser: "+browser);
            }
            Configuration.screenshots=false;
        }else if(osName.contains("Windows")){
            if(browser.equals("chrome")) {
                Configuration.browser = browser;
                Configuration.startMaximized=true;
                System.setProperty("webdriver."+browser+".driver", rootPath+"/src/main/resources/" + browser + "driver.exe");
            }else{
                log.info("No match browser: "+browser);
            }
        }

    }
    /**
     * 设置手机号码
     * @param value
     * @return
     */
    @Value("${phone}")
    private void setPhone(String value) {
        this.phone = value.trim();
    }
}

