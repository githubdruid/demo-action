package com.hzslb.securitydemo.service.impl;

import com.hzslb.securitydemo.model.SysRole;
import com.hzslb.securitydemo.model.SysUser;
import com.hzslb.securitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private UserService service;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user=service.find(s);
        if(user==null)
            throw new UsernameNotFoundException("用户名不存在");
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (SysRole role:user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println(role.getName());
        }
        return new User(user.getUsername(),user.getPassword(),authorities);
    }
}
