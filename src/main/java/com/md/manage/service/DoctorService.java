package com.md.manage.service;

import com.md.manage.domain.Doctor;
import com.md.manage.model.DoctorModel;

public interface DoctorService {




    public Doctor getDoctorById(String id) ;

    public Doctor getDoctorByUsername(String username);

    public int insertDoctor(DoctorModel doctorModel);

    public int updateDoctor(DoctorModel doctorModel);


    public int deleteDoctor(String id);


}
