package com.junlc.admin.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;

import com.junlc.admin.sys.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 老雷
 * @since 2019-09-20
 */
public interface UserService extends IService<User> {


//	void saveUserRole(Integer uid, Integer[] ids);

	List<User> selectCustomerUsers();

	List<User> loadUserDepts(@Param(Constants.WRAPPER) Wrapper<User> userWrapper);

	List<User> loadCustomerUserDepts(Map<String, Object> map);


}
