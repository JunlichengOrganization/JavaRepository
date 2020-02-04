package com.junlc.admin.core.shiro;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.junlc.admin.core.constant.Constast;
import com.junlc.admin.sys.domain.User;
import com.junlc.admin.sys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy  //只有使用的时候才会加载
    private UserService userService;

//    @Autowired
//    @Lazy
//    private PermissionService permissionService;
//
//    @Autowired
//    @Lazy
//    private RoleService roleService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("loginname", token.getPrincipal().toString());

        //模拟从数据库拿密码  先通过用户名loginname去数据库取出用户对象（不是通过用户名和密码去查的！）
        User user = new User();
        user.setLoginname("ls"); //ls是从token.getPrincipal().toString()来的
        user.setPwd("532ac00e86893901af5f0be6b704dbc7"); //123456的MD5散列加盐后的结果

        if (null != user) {
            user.setType(2);
            user.setName("李四");

            ActiverUser activerUser = new ActiverUser();
            activerUser.setUser(user);

            //根据用户ID查询percode
            //查询所有菜单
//            QueryWrapper<Permission> qw=new QueryWrapper<>();
//            //设置只能查询菜单
//            qw.eq("type",Constast.TYPE_PERMISSION);
//            qw.eq("available", Constast.AVAILABLE_TRUE);

            //根据用户ID+角色+权限去查询
//            Integer userId=user.getId();
//            //根据用户ID查询角色
//            List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(userId);
//            //根据角色ID取到权限和菜单ID
//            Set<Integer> pids=new HashSet<>();
//            for (Integer rid : currentUserRoleIds) {
//                List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
//                pids.addAll(permissionIds);
//            }
//            List<Permission> list=new ArrayList<>();
//            //根据角色ID查询权限
//            if(pids.size()>0) {
//                qw.in("id", pids);
//                list=permissionService.list(qw);
//            }


            List<String> percodes=new ArrayList<>();
//            for (Permission permission : list) {
//                percodes.add(permission.getPercode());
//            }

            percodes.add("sys:test"); //ls有访问sys/test的权限
            percodes.add("sys:test1"); //ls有访问sys/test的权限

            //放到
            activerUser.setPermissions(percodes);

            ByteSource credentialsSalt = ByteSource.Util.bytes("04A93C74C8294AA09A8B974FD1F4ECBB");
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser, user.getPwd(), credentialsSalt,
                    this.getName());

            //参数说明： 1.凭证（可以是任意对象） 2.从数据库查到的密码 3.类名
            //SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser, user.getPwd(), this.getName());
            return info;
        }
        return null;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        ActiverUser activerUser=(ActiverUser) principals.getPrimaryPrincipal();
        User user=activerUser.getUser();
        List<String> permissions = activerUser.getPermissions();
        if(user.getType()== 1) { //超管
            authorizationInfo.addStringPermission("*:*");
        }else {
            if(null!=permissions&&permissions.size()>0) {
                authorizationInfo.addStringPermissions(permissions);
            }
        }
        return authorizationInfo;
    }

}
