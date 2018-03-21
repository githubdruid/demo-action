package com.hzslb.securitydemo.mapper.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;

public class SysUserProvider {
    public String listUser(@Param("username")String username){
        return "select * from sys_user where username = #{username}";
    }

    public String findByUsername(@Param("username")String username){
        System.out.println(username);
        String sql=new SQL(){{
            SELECT("u.*,r.name");
            FROM("sys_user as u");
            LEFT_OUTER_JOIN("sys_role_user sru ON u.id=sru.sys_user_id");
            LEFT_OUTER_JOIN("sys_role r ON sru.sys_role_id=r.id");
            if (StringUtils.hasLength(username)){
                WHERE("username = #{username}");
            }
        }}.toString();
        System.out.println(sql);
        return sql;
    }
    public String findByRole_Id(@Param("role_id") List<Integer> role_id){
        String sql=new SQL(){{
            SELECT("*");
            FROM("sys_role");
            if (role_id.size()>0){
                String str="";
                for (Integer id:role_id
                     ) {
                    str+=id+",";
                }
                if (str.length()>0)
                    str=str.substring(0,str.length()-1);
                WHERE("id in("+str+")");
            }
        }}.toString();
        System.out.println(sql);
        return sql;
    }
}
