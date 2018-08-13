package com.jc.cluz.service.impl;

import com.jc.cluz.appuiobject.LoginApp;
import com.jc.cluz.pageobject.loginPageObject.LoginPage;
import com.jc.cluz.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.MalformedURLException;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    LoginPage loginPage;

    @Autowired
    LoginApp loginApp;

    public void loginPage(){
        loginPage.loginPage();
    }

    public void loginSso(){
        loginPage.loginSso();
    }

    public void loginPageCheck(String username, String password){
        loginPage.loginPageCheck(username, password);
    }

    public void loginFinance(){
        loginPage.loginFinance();
    }

    public void loginUser(){
        loginPage.loginUser();
    }

    public void loginJcy(){
        loginPage.loginJcy();
    }

    public void connectAndroidPhone(){
        try {
            loginApp.connectAndroidPhone();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void connectIphonePhone(){
        try {
            loginApp.connectIosPhone();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void disconnectPhone(){
        loginApp.disconnectPhone();
    }

    public void firstPage(){
        loginApp.firstPage();
    }
}
