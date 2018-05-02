package com.md.manage.service;

import com.md.manage.domain.Patient;
import com.md.manage.model.PatientModel;

import java.util.List;

public interface PatientService {


    List<Patient> getPatientList();


    int insertPatient(PatientModel patientModel);

    int updatePatient(PatientModel patientModel);

    int deletePatient(String id);
    
}
