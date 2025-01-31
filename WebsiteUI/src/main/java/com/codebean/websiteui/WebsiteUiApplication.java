package com.codebean.websiteui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WebsiteUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteUiApplication.class, args);
    }

}
