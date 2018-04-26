package com.md.manage.mapper;

import com.md.manage.domain.Role;
import com.md.manage.dto.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RoleMapper {

    Role getRoleById(Integer id);

    int  insert(Role role);

    int update(Role role);

    int delete(@Param("id") Integer id, @Param("updateTime")Date updateTime);

    List<Role> findAll();

    List<Role> getListByPage(@Param("page") Page page);

    long count();

}