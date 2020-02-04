package com.junlc.admin.core.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class SessionFilter extends FormAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(SessionFilter.class);
    private final static String X_REQUESTED_WITH_STRING = "X-Requested-With";
    private final static String XML_HTTP_REQUEST_STRING = "XMLHttpRequest";
    private final static String SESSION_OUT_STIRNG = "sessionOut";

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        if (this.isLoginRequest(servletRequest, servletResponse)) {
            if (this.isLoginSubmission(servletRequest, servletResponse)) {
                return this.executeLogin(servletRequest, servletResponse);
            } else {
                return true;
            }
        } else {
            if (isAjax((HttpServletRequest) servletRequest)) {
                Map<String,String> map=new HashMap<String,String>();
                map.put("code",SESSION_OUT_STIRNG);
                ObjectMapper json = new ObjectMapper();
                servletResponse.getWriter().print(json.writeValueAsString(map));
                //servletResponse.getWriter().print(HttpStatus.UNAUTHORIZED.value());
            } else {
                this.saveRequestAndRedirectToLogin(servletRequest, servletResponse);
            }
            return false;
        }
    }

    public boolean isAjax(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader(X_REQUESTED_WITH_STRING);
        if (XML_HTTP_REQUEST_STRING.equalsIgnoreCase(header)) {
            logger.debug("当前请求为Ajax请求:{}", httpServletRequest.getRequestURI());
            return Boolean.TRUE;
        }
        logger.debug("当前请求非Ajax请求:{}", httpServletRequest.getRequestURI());
        return Boolean.FALSE;
    }

}
