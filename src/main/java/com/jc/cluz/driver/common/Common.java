package com.jc.cluz.driver.common;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;


@Slf4j
public class Common {

    /**
     * 解析web ui元素
     * @param element
     * @param elementType: xpath,css,name
     * @return
     */
    public By parseElement(String element,String ... elementType){
        By anaElement;
        String curElementType="tmp";

        if(elementType.length>0) {
            curElementType = elementType[0].toString().toLowerCase();
        }

        try {
            if (element.contains("//") || curElementType.equals("xpath")) { //element is xpath
                anaElement = By.xpath(element);
            } else if (element.contains(".") || curElementType.equals("css")) { // element is css selector
                anaElement = By.cssSelector(element);
            } else if(curElementType.equals("name")){ // element is name
                anaElement = By.name(element);
            } else{ //element is id
                anaElement = By.id(element);
            }
            return anaElement;
        }catch (Error e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * drag web via down key
     * @param times
     */
    public void robotKey_Tab(int times){
        try {
            Robot robot = new Robot();
            for(int i=0;i<times; i++) {
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
        }catch (AWTException awt){
            throw new RuntimeException(awt);
        }
    }

    /**
     * 解析android ui元素
     * @param element
     * @param elementType: id,class,name
     * @return
     */
    public By parseAndroidElement(String element,String ... elementType){
        By anaElement;
        String curElementType="tmp";

        if(elementType.length>0) {
            curElementType = elementType[0].toString().toLowerCase();
        }

        try {
            if (element.contains("com.jcgroup.ease:id") || curElementType.equals("id")) {
                anaElement = By.id(element);
            } else{
                anaElement = By.name(element);
            }
            return anaElement;
        }catch (Error e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String appSystem(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent");
        String appsystem = null;
        if(userAgent.contains("Android")){
            System.out.println("Android终端访问~");
            appsystem = "Android";
        }else if(userAgent.contains("iPhone")){
            System.out.println("IOS终端访问~");
            appsystem = "iPhone";
        }
        return appsystem;
    }
}
