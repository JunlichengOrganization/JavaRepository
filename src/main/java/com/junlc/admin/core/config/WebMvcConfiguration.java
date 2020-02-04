package com.junlc.admin.core.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;

//@Configuration
public class WebMvcConfiguration {

//    @Bean
    public FilterRegistrationBean spaceFilter() {
        FilterRegistrationBean fitler = new FilterRegistrationBean();
        fitler.setFilter(new SpaceFilter());
        fitler.addUrlPatterns("/*");
        fitler.setName("SpaceFilter");
        fitler.setDispatcherTypes(DispatcherType.REQUEST);
        return fitler;
    }

}
