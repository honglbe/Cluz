package com.jc.cluz.pageobject.loginPageObject.impl;

import com.jc.cluz.driver.selenide.Actions;
import com.jc.cluz.pageobject.loginPageObject.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Slf4j
@Service
public class LoginPageImpl implements LoginPage {

    private final static String username_control = "//*[@id=\"root\"]/div/form/div[1]/div/div/input";
    private final static String password_control = "//*[@id=\"root\"]/div/form/div[2]/div/div/input";
    private final static String loginbutton_control = "//*[@id=\"root\"]/div/form/div[3]/button";
    private final static String checkcotent_control = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[2]/div";
    private final static String title_control = "//*[@id=\"root\"]/div/div/span";
    private final static String menu_sso = "//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div/a";
    private final static String menu_houtai = "//html/body/div[2]/div/div/div/ul[1]/li[3]";
    private final static String menu_finance ="//html/body/div[2]/div/div/div/ul[2]/li[1]";
    private final static String menu_user ="//html/body/div[2]/div/div/div/ul[2]/li[4]";
    private final static String menu_jcy ="//html/body/div[2]/div/div/div/ul[2]/li[12]";

    @Resource
    Actions actions;
    public void loginPage(){
        String url = "http://test.admin.sso.jcease.com/login";
        actions.openUrl(url);
    }

    public void loginPageCheck(String username, String password){
        loginPage();
        actions.input(username_control, username, 0);
        actions.input(password_control, password, 0);
        actions.click(loginbutton_control, 0);
        actions.assertShouldContain(title_control, "业务运营平台管理系统");
    }

    public void loginSso(){
        loginPage();
        actions.input(username_control, "admin", 0);
        actions.input(password_control, "admin", 0);
        actions.click(loginbutton_control, 0);
        actions.assertShouldContain(checkcotent_control, "我是首页");
    }

    public void loginFinance(){
        loginSso();
        actions.click(menu_sso,0);
        actions.click(menu_houtai,0);
        actions.click(menu_finance,0);
    }

    public void loginUser(){
        loginSso();
        actions.click(menu_sso,0);
        actions.click(menu_houtai,0);
        actions.click(menu_user,0);
    }

    public void loginJcy(){
        loginSso();
        actions.click(menu_sso,0);
        actions.click(menu_houtai,0);
        actions.click(menu_jcy,0);
    }
}
