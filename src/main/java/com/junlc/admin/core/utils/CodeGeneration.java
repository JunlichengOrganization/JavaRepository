package com.junlc.admin.core.utils;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
public class CodeGeneration {

    /*数据库类型*/
    private DbType dbType;
    /*数据库驱动*/
    private String driverName;
    /*数据库地址*/
    private String url;
    /*数据库用户名*/
    private String username;
    /*数据库密码*/
    private String password;
    /*生成数据库表*/
    private String[] tables;
    /*生成的包名称*/
    private String packageName;
    /*生成文件路径*/
    private String path;

    private String authhor;

    private IdType idType;

    private String tablePrefix;

    private String entity;

    private String mapper;

    private String mapperXml;

    private String service;

    private String serviceImpl;

    private String controller;

}
