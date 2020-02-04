package com.junlc.admin.sys.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.junlc.admin.core.config.PageConfig;
import com.junlc.admin.sys.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserVo extends User {

     private static final long serialVersionUID = 1L;

     private Integer page=1;

     private Integer limit= PageConfig.pageSize;

     private Integer[] ids;//接收多个ID

    //用于查询条件的都可以在Vo写
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    private Date startTime;
//
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    private Date endTime;



 }
