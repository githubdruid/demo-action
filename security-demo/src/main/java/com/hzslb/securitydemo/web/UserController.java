package com.hzslb.securitydemo.web;

import com.hzslb.securitydemo.model.SysUser;
import com.hzslb.securitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("list")
    public String list(){
//        List<SysUser> list=userService.list();
        SysUser list=userService.find("admin");
        return list.toString();
    }
}
