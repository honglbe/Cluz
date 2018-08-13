package com.jc.cluz.driver.appium.impl;

import com.jc.cluz.dataprovider.configInfo.MobileDriverConfig;
import com.jc.cluz.driver.appium.AppiumActions;
import com.jc.cluz.driver.common.AndroidWebDriver;
import com.jc.cluz.driver.common.Common;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by honglb on 2018/7/25.
 */
@Configuration
@Service
@Slf4j
public class AppiumActionsImpl implements AppiumActions {

    public WebDriver iosDriver;
    public WebDriver androidDriver;
    private boolean isInstall = false;
    private Common commonDriver = new Common();
    @Autowired
    MobileDriverConfig mobileDriverConfig;
    //获取app路径
//        System.out.println(System.getProperty("user.dir"));
//        File classRootPath=new File(System.getProperty("user.dir"));
//        File appDir=new File(classRootPath,"apps");
//        File app =new File(appDir,appName);

    /**
     * 打开android app
     */
    public void connectAndroidPhone() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String deviceName = mobileDriverConfig.getDeviceName();
        String automationName = mobileDriverConfig.getAutomationName();
        String platformName = mobileDriverConfig.getPlatformName();
        String platformVersion = mobileDriverConfig.getPlatformVersion();
        String appPackage = mobileDriverConfig.getAppPackage();
        String appActivity = mobileDriverConfig.getAppActivity();
        String sessionOverride = mobileDriverConfig.getSessionOverride();
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("automationName", automationName);
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("platformVersion", platformVersion);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("sessionOverride", sessionOverride);    //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        //int version = Integer.parseInt(platformVersion.substring(0,1));
        androidDriver = new AndroidWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    public void connectIosPhone() throws MalformedURLException{
        String iosDeviceName = mobileDriverConfig.getIosDeviceName();
        String iosAutomationName = mobileDriverConfig.getIosAutomationName();
        String iosPlatformName = mobileDriverConfig.getIosPlatformName();
        String iosPlatformVersion = mobileDriverConfig.getIosPlatformVersion();
        String bundleId = mobileDriverConfig.getBundleId();
        String udid = mobileDriverConfig.getUdid();
        String xcodeOrgId = mobileDriverConfig.getXcodeOrgId();
        String xcodeSigningId = mobileDriverConfig.getXcodeSigningId();
        String wdaLocalPort = mobileDriverConfig.getWdaLocalPort();

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, iosDeviceName);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, iosAutomationName);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, iosPlatformName);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, iosPlatformVersion);
        desiredCapabilities.setCapability(MobileCapabilityType.APP, bundleId);
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);
        //desiredCapabilities.setCapability(MobileCapabilityType., xcodeOrgId);
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        iosDriver = new RemoteWebDriver(url, desiredCapabilities);

        //如果真机设备已经安装app，则不需要重新安装
//        if (isInstall) {
//            File classpathRoot = new File(System.getProperty("user.dir"));
//            File appDir = new File(classpathRoot, "apps");
//            File app = new File(appDir, "zhihu.apk");
//            capabilities.setCapability("app", app.getAbsolutePath());
//        }
    }

    /**
     * 关闭android app
     */
    public void disconnectPhone() {
        if (null != androidDriver) {
            androidDriver.quit();
        }
    }

    /**
     * 页面点击
     */
    public void click(String element){
        if(element != null) {
            try {
                if (element.contains("com.jcgroup.ease:id")) {
                    androidDriver.findElement(By.id(element)).click();
                } else {
                    androidDriver.findElement(By.name(element)).click();
                }
            } catch (Error e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }else {
            log.error("控件不存在");
        }
    }

    /**
     * 页面等待
     */
    public void waitTimes(int msec){
        try {
            Thread.sleep(msec);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 切换系统窗口
     */
    public void switchSysWindows(){
        androidDriver.switchTo().alert().accept();
    }

    /**
     * 关闭系统权限窗口
     */
    public void permission() {
        if (androidDriver.getPageSource().contains("允许")) {// 出现权限提示
            try {
                androidDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();// 点击拒绝
            } catch (NoSuchElementException e1) {
                androidDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();// 点击允许
            }
        }
    }

    /**
     * 向上滑屏
     * @params int during等待时间，int num滑屏次数
     */
    public void swipeToUp(int num){
        int width = androidDriver.manage().window().getSize().width;//获取当前屏幕的宽度
        int height = androidDriver.manage().window().getSize().height;//获取当前屏幕的高度
//        TouchAction action1=new TouchAction(androidDriver).press(PointOption.point(width/2, height*3/4)).waitAction(WaitOptions.waitOptions(duration))
//                .moveTo(PointOption.point(width/2, height/4)).release();
//        action1.perform();
        TouchActions touchActions = new TouchActions(androidDriver);
        for(int i=0; i<num; i++) {
            touchActions.scroll(0, 400).build().perform();
        }
    }


    public void swipeToUp1(){
//        TouchActions action = new TouchActions(androidDriver);
//        action.down(10, 10);
//        action.move(50, 50);
//        action.perform();
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        js.executeScript("mobile: scroll", scrollObject);

    }
//
//    /**
//     * 向下滑屏
//     * @params int during等待时间，int num滑屏次数
//     */
//    public void swipeToDown(int during,int num){
//        int width = androidDriver.manage().window().getSize().width;//获取当前屏幕的宽度
//        int height = androidDriver.manage().window().getSize().height;//获取当前屏幕的高度
//        for(int i=0;i<num;i++){
//            androidDriver.swipe(width/2, height/4, width/2, height*3/4, during);
//        }
//    }
//
//    /**
//     * 向左滑屏
//     * @params int during等待时间，int num滑屏次数
//     */
//    public void swipeToLeft(int during,int num) {
//        int width = androidDriver.manage().window().getSize().width;//获取当前屏幕的宽度
//        int height = androidDriver.manage().window().getSize().height;//获取当前屏幕的高度
//        for(int i=0;i<num;i++){
//            androidDriver.swipe(width*3/4, height/2, width/4, height/2, during);
//        }
//    }
//
//    /**
//     * 向右滑屏
//     * @params int during等待时间，int num滑屏次数
//     */
//    public void swipeToRight(int during,int num) {
//        int width = androidDriver.manage().window().getSize().width;//获取当前屏幕的宽度
//        int height = androidDriver.manage().window().getSize().height;//获取当前屏幕的高度
//        for(int i=0;i<num;i++){
//            androidDriver.swipe(width/4, height/2, width*3/4, height/2, during);
//        }
//    }

    public boolean isNameElementExist(String element) {
        if (element != null){
            return true;
        }else {
            return false;
        }
    }

    public void inputContent(String element, String content){
        if(element != null) {
            try {
                if (element.contains("com.jcgroup.ease:id")) {
                    androidDriver.findElement(By.id(element)).sendKeys(content);
                } else {
                    androidDriver.findElement(By.name(element)).sendKeys(content);
                }
            } catch (Error e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }else {
            log.error("控件不存在");
        }
    }

    public String getContent(String element){
        String content;
        try {
            if (element.contains("com.jcgroup.ease:id")) {
                content = androidDriver.findElement(By.id(element)).getText();
            } else {
                content = androidDriver.findElement(By.name(element)).getText();
            }
        } catch (Error e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return content;
    }

    public void assertShouldContain(String element,String expect){
        if (element != null) {
            androidDriver.findElement(commonDriver.parseAndroidElement(element)).getText().equals(expect);
        }else {
            log.error("控件不存在");
        }
    }

    public void getText(String element){
        if (element != null) {
            androidDriver.findElement(commonDriver.parseAndroidElement(element)).getText();
        }else {
            log.error("控件不存在");
        }
    }

//    public String getScreen(){
//        String fileRoute="//liyu/testing/test/";
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
//        String picname=fileRoute+df.format(new Date()).toString()+".png";
//        File screen = androidDriver.get(OutputType.FILE);
//        System.out.println(picname);
//        File screenFile = new File(picname);
//        try {
//            FileUtils.copyFile(screen, screenFile);
//            String time=df.format(new Date()).toString();
//            System.out.println("当前时间"+time);
//            return time;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }
}
