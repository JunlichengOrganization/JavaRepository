package com.junlc.admin.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.junlc.admin.sys.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 老雷
 * @since 2019-09-20
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectCustomerUsers();

    List<User> loadUserDepts(@Param(Constants.WRAPPER) Wrapper<User> userWrapper);

    List<User> loadCustomerUserDepts(Map<String, Object> map);


}
