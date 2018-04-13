package com.md.manage.mapper;

import com.md.manage.domain.Doctor;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface DoctorMapper {


    /**
     *根据ID查询医生
     * @param id
     * @return
     */
    Doctor getDoctorById(@Param("id") Integer id);

    /**
     *根据用户名查询医生
     * @param username
     * @return
     */
    Doctor getDoctorByUsername(@Param("username") String username);

    /**
     * 添加医生账户
     * @param doctor
     * @return
     */
    int insert(Doctor doctor);


    /**
     * 修改医生信息
     * @param doctor
     * @return
     */
    int update(Doctor doctor);

    /**
     * 删除医生账户
     * @param id
     * @param updateTime
     * @return
     */
    int delete(@Param("id") Integer id, @Param("updateTime")Date updateTime);



}
