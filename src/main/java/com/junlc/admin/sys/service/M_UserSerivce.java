package com.junlc.admin.sys.service;

import com.junlc.admin.sys.domain.User;

import java.util.List;
import java.util.Map;

public interface M_UserSerivce {

    List<User> loadUserDeptspagehelper(Map<String, Object> map);
}
