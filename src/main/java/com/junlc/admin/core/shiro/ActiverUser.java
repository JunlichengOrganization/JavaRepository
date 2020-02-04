package com.junlc.admin.core.shiro;

import com.junlc.admin.sys.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser {

    private User user;

    private List<String> roles;

    private List<String> permissions;
}

