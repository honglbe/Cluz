package com.jc.cluz.driver.appium;

import java.net.MalformedURLException;

/**
 * Created by honglb on 2018/7/25.
 */
public interface AppiumActions {

    /**
     * 打开android app
     */
    void connectAndroidPhone() throws MalformedURLException;

    /**
     * 打开iphone app
     */
    void connectIosPhone() throws MalformedURLException;


    /**
     * 关闭android app
     */
    void disconnectPhone();

    /**
     * 点击： 支持 id, name
     * @param element
     */
    void click(String element);

    /**
     * 页面等待
     * * @param msec（毫秒）
     */
    void waitTimes(int msec);

    /**
     * 切换系统窗口
     */
    void switchSysWindows();

    /**
     * 绕过系统权限窗口
     */
    void permission();

    /**
     * 判断控件是否存在
     * @param element
     */
    boolean isNameElementExist(String element);

    /**
     * 向上滑屏
     * @params int during等待时间，int num滑屏次数
     */
    void swipeToUp(int num);
//
//    /**
//     * 向下滑屏
//     * @params int during等待时间，int num滑屏次数
//     */
//    void swipeToDown(int during,int num);
//
//    /**
//     * 向上右滑屏
//     * @params int during等待时间，int num滑屏次数
//     */
//    void swipeToRight(int during,int num);
//
//    /**
//     * 向左滑屏
//     * @params int during等待时间，int num滑屏次数
//     */
//    void swipeToLeft(int during,int num);
    String getContent(String element);

    void inputContent(String element, String content);
    /**
     * 断言控件内容是否为想要的内容
     * @param element,content
     */
    void assertShouldContain(String element,String expect);

    /**
     * 获取控件内容
     * @param element
     */
    void getText(String element);

    void swipeToUp1();
}
