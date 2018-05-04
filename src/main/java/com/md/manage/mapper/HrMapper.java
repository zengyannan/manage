package com.md.manage.mapper;

import com.md.manage.domain.Hr;
import com.md.manage.dto.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

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
     * @return
     */
//    int delete(@Param("id") Integer id, @Param("updateTime")Date updateTime);
     int delete(@Param("id") Integer id);

     int deleteHrRole(@Param("id") Integer id);

     List<Hr> findAll();


     Long count();

     List<Hr> getListByPage(@Param("page") Page page);


    int setRoles(@Param("uid") int uid,@Param("ids") List<Integer> ids);

}