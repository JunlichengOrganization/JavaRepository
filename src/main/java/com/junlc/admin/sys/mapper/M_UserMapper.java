package com.junlc.admin.sys.mapper;

import com.junlc.admin.sys.domain.User;

import java.util.List;
import java.util.Map;

public interface M_UserMapper {

    List<User> loadUserDeptspagehelper(Map<String, Object> map);
}
