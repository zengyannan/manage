package com.md.manage.service;

import com.md.manage.domain.Patient;
import com.md.manage.dto.Page;
import com.md.manage.model.PageModel;
import com.md.manage.model.PatientModel;

import java.util.List;

public interface PatientService {


     List<Patient> findAll();

    List<Patient> getAllPatient();

     Page<Patient> getListByPage(PageModel pageModel);


    int insertPatient(PatientModel patientModel);

    int updatePatient(PatientModel patientModel);

    int deletePatient(String id);

    Patient getPatientByNameAndIdCard(String name,String idCard);

    Patient getPatientByToken(String token);
}
