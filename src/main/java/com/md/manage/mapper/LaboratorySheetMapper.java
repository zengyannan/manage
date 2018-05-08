package com.md.manage.mapper;

import com.md.manage.domain.LaboratorySheet;
import com.md.manage.dto.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LaboratorySheetMapper {



    List<LaboratorySheet> findAll();

    List<LaboratorySheet> getListByPageAndDoctor(@Param("page") Page page,@Param("doctorId") Integer doctorId,@Param("organId") Integer organId);

    List<LaboratorySheet> getListByPageAndPatient(@Param("page") Page page,@Param("patientId") Integer patientId,@Param("organId") Integer organId);

    int setSuggest(@Param("suggest")String suggest,@Param("id") int id,@Param("status") int status);

    long countByDoctorId(@Param("doctorId") Integer doctorId);

    long countByPatientId(@Param("patientId") Integer patientId);

    int insert(LaboratorySheet laboratorySheet);

}
