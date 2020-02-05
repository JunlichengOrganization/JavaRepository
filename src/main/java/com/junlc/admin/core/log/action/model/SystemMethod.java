package com.junlc.admin.core.log.action.model;

import com.junlc.admin.core.enums.ActionLogEnum;
import lombok.Getter;

@Getter
public class SystemMethod extends BusinessMethod{
    // 日志类型
    protected Integer type = ActionLogEnum.SYSTEM.getCode();

    public SystemMethod(String method) {
        super(method);
    }

    public SystemMethod(String name, String method) {
        super(name, method);
    }
}
