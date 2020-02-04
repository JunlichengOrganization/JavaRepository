package com.junlc.admin.core.config;

import com.junlc.admin.core.utils.WebUtils;
import com.junlc.admin.sys.domain.User;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@Component
@Log4j
public class ConfigInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       // HttpSession session = request.getSession();
        // 从session中获取用户信息
        User comUser = (User)WebUtils.getSession().getAttribute("user");

        // session过期
        if(comUser == null){
            log.info(">>>session过期, 跳至登录页");

            if(request.getHeader("X-Requested-With")!=null&&request.getHeader("X-Requested-With").equals("XMLHttpRequest"))
            {
                response.setHeader("sessionstatus", "timeout");
            }else {
                response.sendRedirect("/");
                //response.sendRedirect("/index.html");
            }

            return false;
        }else{
            return true;
        }
    }


}
