package com.codebean.websiteui.errorHandling;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new NewCustomErrorDecoder();
    }

}
