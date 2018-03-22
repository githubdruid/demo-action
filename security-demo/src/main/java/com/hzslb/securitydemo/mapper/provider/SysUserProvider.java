package com.hzslb.securitydemo.mapper.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SysUserProvider {
    public String listRoles(@Param("username")String username){
        return new SQL(){{
                SELECT("role.id,role.name");
                FROM("sys_role_user as sru");
                INNER_JOIN("sys_role as role ON sru.sys_role_id=role.id");
                INNER_JOIN("sys_user as user ON sru.sys_user_id=user.id");
                WHERE("user.username = #{username}");
        }}.toString();
    }

}
