package com.hzslb.securitydemo.mapper;

import com.hzslb.securitydemo.mapper.provider.SysUserProvider;
import com.hzslb.securitydemo.model.SysRole;
import com.hzslb.securitydemo.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserMapper {
    @Select("SELECT * FROM sys_user")
    List<SysUser> list();

    @Select("SELECT * FROM sys_user WHERE username=#{username} LIMIT 1")
    SysUser findByUserName(String username);

    @Select("SELECT sys_role_id FROM sys_role_user WHERE sys_user_id=#{id}")
    List<Integer> findRolesIdByUserId(Integer id);

//    @Select("SELECT * FROM sys_role WHERE id in (${role_id})")
    @SelectProvider(type = SysUserProvider.class,method = "findByRole_Id")
    List<SysRole> findByRoleId(@Param("role_id") List<Integer> role_id);
}
