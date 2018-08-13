package com.jc.cluz.driver.common;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Created by honglb on 2018/8/7.
 */
public class AndroidWebDriver extends RemoteWebDriver implements HasTouchScreen {
    private RemoteTouchScreen touch;

    public AndroidWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
        touch = new RemoteTouchScreen(getExecuteMethod());
    }

    public TouchScreen getTouch() {
        return touch;
    }
}
