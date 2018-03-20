package com.hzslb.securitydemo.service.impl;

import com.hzslb.securitydemo.mapper.SysUserMapper;
import com.hzslb.securitydemo.model.SysUser;
import com.hzslb.securitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
