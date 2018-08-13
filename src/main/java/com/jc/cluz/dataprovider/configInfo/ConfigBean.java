package com.jc.cluz.dataprovider.configInfo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by honglb on 2018/7/5.
 */
@Data
@ConfigurationProperties(prefix = "datasource")
@Component
public class ConfigBean {
    private String url;
    private String username;
    private String password;
    private String driver;
}
