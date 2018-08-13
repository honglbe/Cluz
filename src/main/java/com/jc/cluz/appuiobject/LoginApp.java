package com.jc.cluz.appuiobject;

import java.net.MalformedURLException;

/**
 * Created by honglb on 2018/7/26.
 */
public interface LoginApp {
        void connectAndroidPhone() throws MalformedURLException;
        void connectIosPhone() throws MalformedURLException;
        void disconnectPhone();
        void firstPage();
}
