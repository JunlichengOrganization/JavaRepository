package com.junlc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import java.util.Collections;


@SpringBootApplication
@MapperScan(basePackages= {"com.junlc.admin.*.mapper"})
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    //解决jsessionid的问题 因为页面检测没有cookie 所以就自动加上了jsessionid
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//
//        // This will set to use COOKIE only
//        servletContext.setSessionTrackingModes(
//                Collections.singleton(SessionTrackingMode.COOKIE)
//        );
//        // This will prevent any JS on the page from accessing the
//        // cookie - it will only be used/accessed by the HTTP transport
//        // mechanism in use
//        SessionCookieConfig sessionCookieConfig =
//                servletContext.getSessionCookieConfig();
//        sessionCookieConfig.setHttpOnly(true);
//    }

}
