package com.md.manage.service.impl;

import com.md.manage.domain.Patient;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.PatientException;
import com.md.manage.mapper.PatientMapper;
import com.md.manage.model.PatientModel;
import com.md.manage.service.PatientService;
import com.md.manage.util.CommonUtils;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{


    @Autowired
    private PatientMapper patientMapper;

    @Override
    public List<Patient> getPatientList() {
        return  patientMapper.findAll();
    }

    @Override
    public int insertPatient(PatientModel patientModel) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientModel,patient);
        patient.setCreateTime(CommonUtils.getCurrentDate());
        patient.setUpdateTime(CommonUtils.getCurrentDate());
        int effectNum = patientMapper.insert(patient);
        if(effectNum==0){
            throw new PatientException("操作失败");
        }
        return effectNum;
    }

    @Override
    public int updatePatient(PatientModel patientModel) {
        if(patientModel.getId()==null){
            throw new BaseException("必须传入ID");
        }else {
            Validate validate = new IdMustBePositiveInt(patientModel.getId());
            boolean r= validate.goCheck();
            if(!r){
                throw new BaseException("ID必须为正整数",404,10001);
            }
        }
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientModel,patient);
        patient.setId(Integer.parseInt(patientModel.getId()));
        patient.setUpdateTime(CommonUtils.getCurrentDate());
        int effect = patientMapper.update(patient);
        if(effect==0){
            throw new PatientException("操作失败");
        }
        return effect;
    }

    @Override
    public int deletePatient(String id) {
        Validate validate =new IdMustBePositiveInt(id);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("参数错误",404,10001);
        }
        int effect = patientMapper.delete(Integer.parseInt(id));
        if(effect==0){
            throw new PatientException("操作错误");
        }
        return effect;
    }
}
