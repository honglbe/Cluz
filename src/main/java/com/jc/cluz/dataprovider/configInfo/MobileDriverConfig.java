package com.jc.cluz.dataprovider.configInfo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by honglb on 2018/7/25.
 */
@Component
@PropertySource("classpath:conf/mobile.properties")
@ConfigurationProperties(prefix = "mobile")
@Data
public class MobileDriverConfig {
    /**
    * android配置项
     */
    private String deviceName;
    private String automationName;
    private String platformName;
    private String platformVersion;
    private String appPackage;
    private String appActivity;
    private String sessionOverride;
    private String unicodeKeyboard;
    private String resetKeyboard;

    /**
     * iphone配置项
     */
    private String iosDeviceName;
    private String iosAutomationName;
    private String iosPlatformName;
    private String iosPlatformVersion;
    private String bundleId;
    private String udid;
    private String xcodeOrgId;
    private String xcodeSigningId;
    private String wdaLocalPort;
}