package com.jc.cluz;

import com.jc.cluz.dataprovider.configInfo.ConfigBean;
import com.jc.cluz.dataprovider.configInfo.MobileDriverConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
//@EnableConfigurationProperties({ConfigBean.class, MobileDriverConfig.class})
@EnableConfigurationProperties
public class CluzApplication {

	public static void main(String[] args) {
		SpringApplication.run(CluzApplication.class, args);
	}
}
