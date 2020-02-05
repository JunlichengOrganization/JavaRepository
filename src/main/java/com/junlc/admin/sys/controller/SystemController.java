package com.junlc.admin.sys.controller;

import com.junlc.admin.core.annotation.Log;
import com.junlc.admin.core.shiro.ActiverUser;
import com.junlc.admin.core.utils.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//每个模块都有一个控制跳转的Controller类
@Controller
@RequestMapping("sys")
@CrossOrigin(origins = "*",maxAge = 3600)
public class SystemController {

    /**
     * 跳转到登陆页面
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        Subject subject = SecurityUtils.getSubject();
        String loginname = "ls";
        String pwd = "123456";
        AuthenticationToken token=new UsernamePasswordToken(loginname, pwd);
//        User activerUser=new User();
//        activerUser.setName("ls");
//        activerUser.setLoginname("ls");
//        WebUtils.getSession().setAttribute("user", activerUser);

        try {
            subject.login(token);
            ActiverUser activerUser=(ActiverUser) subject.getPrincipal();
            WebUtils.getSession().setAttribute("user", activerUser.getUser());
            //记录登陆日志
//            Loginfo entity=new Loginfo();
//            entity.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
//            entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
//            entity.setLogintime(new Date());
//            loginfoService.save(entity);
            return "system/user/userManager";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "error11";
        }

    }

    @RequestMapping("logout")
    public String logout() {
        return "redirect:/";
    }

    /**
     * 权限不足页面
     */
    @GetMapping("noAuth")
    public String noAuth() {
        return "/system/user/no_auth";
    }

    @RequestMapping("test")
    @RequiresPermissions("sys:test")
    @Log(operationType="test操作:",operationName="SystemController测试")
    public String test() {
        //return "redirect:/";
        //return "redirect:/index.html";\

        return "system/user/test";
    }

    @RequestMapping("test3")
    public String test3() {
        Integer g= 0;
        double j = 5/g;
        return "system/user/test1";
    }

    @RequestMapping("test4")
    public String test4() {
        Integer k= 0;
        double j = 5/k;
        return "system/user/test1";
    }

    @RequestMapping("test2")
    @RequiresPermissions("sys:test2")
    public String test2() {
        //return "redirect:/";
        //return "redirect:/index.html";\

        return "system/user/test1";
    }

}
