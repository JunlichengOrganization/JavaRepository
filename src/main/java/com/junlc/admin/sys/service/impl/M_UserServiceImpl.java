package com.junlc.admin.sys.service.impl;

//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
import com.junlc.admin.sys.domain.User;
import com.junlc.admin.sys.mapper.M_UserMapper;

import com.junlc.admin.sys.service.M_UserSerivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class M_UserServiceImpl  implements M_UserSerivce {

        @Autowired
        private M_UserMapper muserMapper; //报错没关系

    	public List<User> loadUserDeptspagehelper(Map<String, Object> map) {

			List<User> list = this.muserMapper.loadUserDeptspagehelper(map);

			return list;
		}
}
