package com.junlc.admin.core.config;

import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PageConfig {

    public  static Integer pageSize;

    @Value("${pageconfig.pageSize}")
    public void setPageSize(String pageSizeTmp) {
        pageSize= StringUtils.stringToInteger(pageSizeTmp);
    }

//    @Value("${pageconfig.pageSize}")
//    private String pageSizeTmp;
//
//    @PostConstruct
//    public void init(){
//        pageSize = StringUtils.stringToInteger(pageSizeTmp);
//    }


}
