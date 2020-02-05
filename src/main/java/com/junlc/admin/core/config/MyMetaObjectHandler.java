package com.junlc.admin.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.junlc.admin.core.shiro.ActiverUser;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;

import java.util.Date;

/**
 * 自动填充公共值 比如添加人员 时间 修改人员时间 都有默认值 是当前用户和当前时间
 */
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        Object createDate = getFieldValByName("createDate", metaObject);
        Object updateDate = getFieldValByName("updateDate", metaObject);
        Object status = getFieldValByName("status", metaObject);
        Object createBy = getFieldValByName("createBy", metaObject);
        Object updateBy = getFieldValByName("updateBy", metaObject);
        if (createDate==null){
            this.setFieldValByName("createDate", new Date(), metaObject);
        }
        if (updateDate==null){
            this.setFieldValByName("updateDate", new Date(), metaObject);
        }
//        if (status==null){
//            this.setFieldValByName("status", StatusEnum.OK.getCode(), metaObject);
//        }
        if (createBy==null){
            ActiverUser activerUser=(ActiverUser)SecurityUtils.getSubject().getPrincipal();
            this.setFieldValByName("createBy", activerUser.getUser().getId(), metaObject);
        }
        if (updateBy==null){
            ActiverUser activerUser=(ActiverUser)SecurityUtils.getSubject().getPrincipal();
            this.setFieldValByName("updateBy", activerUser.getUser().getId(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateDate = getFieldValByName("updateDate", metaObject);
        Object updateBy = getFieldValByName("updateBy", metaObject);
        if (updateDate==null){
            setFieldValByName("updateDate", new Date(), metaObject);
        }
        if (updateBy==null){
            ActiverUser activerUser=(ActiverUser)SecurityUtils.getSubject().getPrincipal();
            this.setFieldValByName("updateBy", activerUser.getUser().getId(), metaObject);
        }
    }
}
