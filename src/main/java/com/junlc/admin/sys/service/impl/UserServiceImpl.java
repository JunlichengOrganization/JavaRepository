package com.junlc.admin.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.junlc.admin.sys.domain.User;
import com.junlc.admin.sys.mapper.UserMapper;
import com.junlc.admin.sys.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private UserMapper userMapper; //报错没关系

	@Override
	public boolean save(User entity) {
		return super.save(entity);
	}
	
	@Override
	public boolean updateById(User entity) {
		return super.updateById(entity);
	}
	
	@Override
	public User getById(Serializable id) {
		return super.getById(id);
	}


	public List<User> selectCustomerUsers(){
		return this.userMapper.selectCustomerUsers();
	}

	public List<User> loadUserDepts(@Param(Constants.WRAPPER) Wrapper<User> userWrapper)
	{
		return this.userMapper.loadUserDepts(userWrapper);
	}


	public List<User> loadCustomerUserDepts(Map<String, Object> map){
		return this.userMapper.loadCustomerUserDepts(map);
	}


}
