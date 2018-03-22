package com.hzslb.securitydemo.mapper;

import com.hzslb.securitydemo.mapper.provider.SysUserProvider;
import com.hzslb.securitydemo.model.SysRole;
import com.hzslb.securitydemo.model.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserMapper {
    @Select("SELECT * FROM sys_user")
    List<SysUser> list();

    /**
     *
     * @param username SYS_USER.username字段
     * @return
     */
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "username",property = "roles",many = @Many(select = "findRolesByUsername"))
    })
    @Select("SELECT * FROM sys_user WHERE username=#{username}")
    SysUser findByUserName(String username);

    /**
     * 使用SELECT增强根据username来查询 关联sys_role_user
     * @param username SYS_USER.username字段
     * @return 权限
     */
    @SelectProvider(type = SysUserProvider.class,method = "listRoles")
    List<SysRole> findRolesByUsername(@Param("username")String username);
}
