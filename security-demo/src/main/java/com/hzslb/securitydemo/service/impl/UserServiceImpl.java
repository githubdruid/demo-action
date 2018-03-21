package com.hzslb.securitydemo.service.impl;

import com.hzslb.securitydemo.mapper.SysUserMapper;
import com.hzslb.securitydemo.model.SysRole;
import com.hzslb.securitydemo.model.SysUser;
import com.hzslb.securitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper userMapper;
    @Override
    public List<SysUser> list() {
        return userMapper.list();
    }

    @Override
    public SysUser find(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public List<SysRole> fingByrole(Integer id) {
        List<SysRole> roles=new ArrayList<>();
        List<Integer> role_id= userMapper.findRolesIdByUserId(id);
        System.out.println(role_id);
        roles=userMapper.findByRoleId(role_id);
        return roles;
    }
}
