package com.jc.cluz.appuiobject.impl;

import com.jc.cluz.appuiobject.LoginApp;
import com.jc.cluz.driver.appium.AppiumActions;
import com.jc.cluz.driver.mouthpiece.ApiActions;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;

/**
 * Created by honglb on 2018/7/26.
 */
@Service
public class LoginAppImpl implements LoginApp {
    private final static String tiyang_button = "com.jcgroup.ease:id/tv_go";
    private final static String search_edit = "com.jcgroup.ease:id/tv_search_recommond";
    private final static String search_edit1 = "com.jcgroup.ease:id/et_search";
    private final static String search_button = "com.jcgroup.ease:id/tv_search";
    private final static String search_result_list = "com.jcgroup.ease:id/tvTitle";

    @Autowired
    private AppiumActions appiumActions;
    public void connectAndroidPhone() throws MalformedURLException {
        appiumActions.connectAndroidPhone();
    }

    public void connectIosPhone() throws MalformedURLException {
        appiumActions.connectIosPhone();
    }

    public void disconnectPhone(){
        appiumActions.disconnectPhone();
    }

    public void firstPage() {
        appiumActions.waitTimes(1000);
//        appiumActions.click(tiyang_button);
        appiumActions.waitTimes(8000);
        appiumActions.permission();
        appiumActions.waitTimes(8000);
//        appiumActions.click(search_edit);
//        appiumActions.inputContent(search_edit1, "浙江省");
//        appiumActions.click(search_button);
//        appiumActions.assertShouldContain(search_result_list, "正式开赛");
        appiumActions.swipeToUp1();
        appiumActions.waitTimes(58000);
    }
}
