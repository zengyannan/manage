package com.md.manage.mapper;

import com.md.manage.domain.Patient;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PatientMapper {


    List<Patient> findAll();

    int  insert(Patient patient);

    int update(Patient patient);

    int delete(@Param("id") Integer id);

    List<Patient> getAllPatient();
}
