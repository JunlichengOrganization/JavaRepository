package com.junlc.admin.core.enums;

import lombok.Getter;

@Getter
public enum ActionLogEnum {

    BUSINESS(1, "业务"),
    LOGIN(2, "登录"),
    SYSTEM(3, "系统"),
    INFO(4, "自定义");


    private Integer code;

    private String message;

    ActionLogEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}