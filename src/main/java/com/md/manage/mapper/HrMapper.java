package com.md.manage.mapper;

import com.md.manage.domain.Hr;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface HrMapper {

    /**
     *根据ID查询管理员
     * @param id
     * @return
     */
    Hr getHrById(@Param("id") Integer id);

    /**
     *根据用户名查询管理员
     * @param username
     * @return
     */
    Hr getHrByUsername(@Param("username") String username);




    /**
     * 添加管理员账户
     * @param hr
     * @return
     */
    int insert(Hr hr);


    /**
     * 修改管理员信息
     * @param hr
     * @return
     */
    int update(Hr hr);

    /**
     * 删除管理员账户
     * @param id
     * @param updateTime
     * @return
     */
    int delete(@Param("id") Integer id, @Param("updateTime")Date updateTime);


}