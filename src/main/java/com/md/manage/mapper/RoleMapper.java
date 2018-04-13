package com.md.manage.mapper;

import com.md.manage.domain.Hr;
import com.md.manage.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface RoleMapper {

    Role getRoleById(Integer id);

    int  insert(Role role);

    int update(Role role);

    int delete(@Param("id") Integer id, @Param("updateTime")Date updateTime);

}