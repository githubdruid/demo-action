package com.hzslb.securitydemo.service;

import com.hzslb.securitydemo.model.SysRole;
import com.hzslb.securitydemo.model.SysUser;

import java.util.List;

public interface UserService {
    List<SysUser> list();

    SysUser find(String username);

    List<SysRole> fingByrole(Integer id);
}
