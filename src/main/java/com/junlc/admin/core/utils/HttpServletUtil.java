package com.junlc.admin.core.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取HttpServlet子对象
*/
public class HttpServletUtil {

    private static ServletRequestAttributes requestAttributes;

    /**
     * 获取HttpServletRequest对象
     */
    public static HttpServletRequest getRequest(){
        requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取HttpServletResponse对象
     */
    public static HttpServletResponse getResponse(){
        requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getResponse();
    }
}
