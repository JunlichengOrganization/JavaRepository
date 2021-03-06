package com.junlc.admin.core.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageAction {

    @RequestMapping(value = "/error400Page")
    public String error400Page() {
        return "404";
    }

    @RequestMapping(value = "/error401Page")
    public String error401Page() {
        return "401";
    }

    @RequestMapping(value = "/error404Page")
    public String error404Page() {
        return "404";
    }

    @RequestMapping(value = "/error500Page")
    public String error500Page() {
        return "500";
    }
}
