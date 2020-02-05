package com.junlc.admin.core.log.action.base;

import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 自定义日志数据
 */
@Data
public class ResetLog {
    // Aop连接点信息对象
    private JoinPoint joinPoint;

    // 获取切入点方法指定名称的参数值
    public Object getParam(String name){
        Object[] args = joinPoint.getArgs();
        if(args.length > 0){
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String[] parameterNames = methodSignature.getParameterNames();
            for (int i = 0; i < parameterNames.length; i++) {
                if(parameterNames[i].equals(name)){
                    return args[i];
                }
            }
        }
        return null;
    }
}
