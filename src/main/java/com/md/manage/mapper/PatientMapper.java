package com.md.manage.mapper;

import com.md.manage.domain.Patient;
import com.md.manage.dto.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PatientMapper {


    List<Patient> findAll();

    int  insert(Patient patient);

    int update(Patient patient);

    int delete(@Param("id") Integer id);

    List<Patient> getAllPatient();

    Patient getPatientByName(@Param("name")String name);

    Patient getPatientByIdCard(@Param("idCard")String idCard);

    List<Patient> getListByPage(@Param("page") Page page);

    long count();


    int setRoles(@Param("pid") int pid,@Param("ids") List<Integer> ids);

    int deletePatientRole(@Param("id") Integer id);

    Patient getPatientById(@Param("id") Integer id);
}
