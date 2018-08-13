package com.jc.cluz.dataprovider.configInfo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by honglb on 2018/7/25.
 */
@Data
@ConfigurationProperties(prefix = "datasource")
@Component
public class DataBaseConfig {
    private String url;
    private String username;
    private String password;
    private String driver;
}
