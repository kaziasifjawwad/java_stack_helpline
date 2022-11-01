package com.jawwad.limitserver;

import com.jawwad.limitserver.pojo.LimitData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(LimitData.class)
public class LimitServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LimitServerApplication.class, args);
    }

}
