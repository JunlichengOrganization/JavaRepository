package com.junlc.admin.sys.controller;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.github.pagehelper.PageInfo;
import com.junlc.admin.core.config.PageConfig;
import com.junlc.admin.core.constant.Constast;
import com.junlc.admin.core.vo.DataGridView;
import com.junlc.admin.core.vo.ResultObj;
import com.junlc.admin.sys.domain.User;
import com.junlc.admin.sys.service.M_UserSerivce;
import com.junlc.admin.sys.service.UserService;
import com.junlc.admin.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private M_UserSerivce muserService;

    //查询所有
    @RequestMapping("selectUsers")
    public Map<String, Object> selectUsers(){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        List<User> list = this.userService.list(queryWrapper); //国内mybatisplus封装的方法
        map.put("list",list);
        return map;
    }

    //条件查询
    @RequestMapping("selectUsersByName")
    @ResponseBody
    public Map<String, Object> selectUsersByName(String loginname){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();

        queryWrapper.eq((StringUtils.isNotBlank(loginname)), "loginname", loginname);
        List<User> list = this.userService.list(queryWrapper); //国内mybatisplus封装的方法
        map.put("list",list);
        return map;
    }

    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo) {
        try {
            userVo.setDeptid(1);
            userVo.setType(Constast.USER_TYPE_NORMAL);//设置类型
            userVo.setHiredate(new Date());
            String salt= IdUtil.simpleUUID().toUpperCase();
            userVo.setSalt(salt);//设置盐
            //userVo.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());//设置密码
            userVo.setPwd(Constast.USER_DEFAULT_PWD);
            this.userService.save(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo) {
        try {
            userVo.setId(17);
            Integer orderno = userVo.getOrdernum();
            //为null的就不会更改 userVo传过来的是“”，数值或时间转不过来的 就是NULL，像字符串转过来的就是“”
            if (null == orderno){userVo.setOrdernum(0);}
            this.userService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    //分页查询
    @RequestMapping("loadUsers")
    public DataGridView loadUsers(UserVo userVo){
        IPage<User> page=new Page<>(userVo.getPage(), userVo.getLimit()); //mybatisplus的分页插件
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq((StringUtils.isNotBlank(loginname)), "loginname", loginname);
        queryWrapper.orderByDesc("id");
        userService.page(page, queryWrapper);

        List<User> records = page.getRecords();
        for (User user : records) {
//            Billtype billtype = this.billTypeService.getById(bills.getTypeid());
//            bills.setTypeName(billtype.getName());
        }
        return new DataGridView(page.getTotal(), records);
    }

    //多表查询
    @RequestMapping("loadUserDepts")
    public DataGridView loadUserDepts(UserVo userVo){
//        IPage<User> page=new Page<>(userVo.getPage(), userVo.getLimit()); //mybatisplus的分页插件
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
////        queryWrapper.eq((StringUtils.isNotBlank(loginname)), "loginname", loginname);
//        queryWrapper.orderByDesc("id");
//        userService.page(page, queryWrapper);
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("s.loginname", "ls");
        List<User> users = userService.loadUserDepts(wrapper);

        return new DataGridView(10L, users);
    }

    //多表自定义分页查询
    @RequestMapping("loadCustomerUserDepts")
    public DataGridView loadCustomerUserDepts(){
        Map<String, Object> map = new HashMap<>();
        map.put("loginname","l");
        map.put("limit",2);
        map.put("offset",1);
        List<User> users = userService.loadCustomerUserDepts(map); //分页后查询的集合
         Long count = 10L; // userService.loadCustomerUserDeptsNum(map);  //查询总条数
        return new DataGridView(count, users);
    }

//    @RequestMapping("/toApplicationList.html")
//    public String toApplicationList(@RequestParam(value="pageNo",defaultValue="1")int pageNo, @RequestParam(value="pageSize",defaultValue="10")int pageSize, Model model){
//        PageInfo<Applications> page = applicationsService.getApplicationList(pageNo,pageSize);
//        model.addAttribute("pageInfo", page);
//        return "backend/applications/application_list";
//    }

    //多表自定义分页查询
    @RequestMapping("loadUserDeptspagehelper")
    public DataGridView loadUserDeptspagehelper(){
        //PageInfo<User> page = muserService.loadUserDeptspagehelper(1,2);
        Map<String, Object> map = new HashMap<>();
        map.put("loginname","l");
        List<User> users = userService.loadCustomerUserDepts(map); //分页后查询的集合

        return new DataGridView(10L,users);
    }


    //自定义查询
    @RequestMapping("selectCustomerUsers")
    public Map<String, Object> selectCustomerUsers(){
        Map<String, Object> map = new HashMap<>();
        List<User> list = this.userService.selectCustomerUsers();
        map.put("list",list);
        return map;
    }

    @RequestMapping("helloUser")
    public Map<String,Object> helloUser(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("value", 1);
        return map;
    }
}
