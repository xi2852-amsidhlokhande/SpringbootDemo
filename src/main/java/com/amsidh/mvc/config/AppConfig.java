package com.amsidh.mvc.config;

import com.amsidh.mvc.filter.RequestHeaderInfoFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RequestHeaderInfoFilter getRequestHeaderInfoFilter(){
        return new RequestHeaderInfoFilter();
    }
}
