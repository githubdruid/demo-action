package com.hzslb.securitydemo.mapper;

import com.hzslb.securitydemo.model.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "roles", column = "name",many = @Many(select = "com.hzslb.securitydemo.model.SysRole"))
    })
    @Select("SELECT\n" +
            "\tu.*, r.name\n" +
            "FROM\n" +
            "\tsys_user u\n" +
            "LEFT JOIN sys_role_user sru ON u.id = sru.sys_user_id\n" +
            "LEFT JOIN sys_role r ON sru.sys_role_id\n" +
            "WHERE\n" +
            "\tusername = #{username}")
    SysUser findByUserName(@Param("username") String username);


    @Select("select * from sys_user")
    List<SysUser> list();
}
