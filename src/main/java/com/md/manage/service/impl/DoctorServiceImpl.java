package com.md.manage.service.impl;

import com.md.manage.domain.Doctor;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.DoctorException;
import com.md.manage.mapper.DoctorMapper;
import com.md.manage.model.DoctorModel;
import com.md.manage.service.DoctorService;
import com.md.manage.util.CommonUtils;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public Doctor getDoctorById(String id) {
        Validate validate =new IdMustBePositiveInt(id);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("参数错误");
        }
        return doctorMapper.getDoctorById(Integer.parseInt(id));
    }

    @Override
    public Doctor getDoctorByUsername(String username) {
        return null;
    }

    @Override
    public int insertDoctor(DoctorModel doctorModel) {
        Doctor eDoctor = doctorMapper.getDoctorByUsername(doctorModel.getUsername().trim());
        if(eDoctor!=null){
            throw new DoctorException("用户名重复了");
        }
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorModel,doctor);
        doctor.setCreateTime(CommonUtils.getCurrentDate());
        doctor.setUpdateTime(CommonUtils.getCurrentDate());
        int effect = doctorMapper.insert(doctor);
        if(effect==0){
            throw new DoctorException("操作错误");
        }
        return effect;
    }

    @Override
    public int updateDoctor(DoctorModel doctorModel) {
        if(doctorModel.getId()==null){
            throw new BaseException("必须传入ID");
        }else {
            Validate validate =new IdMustBePositiveInt(doctorModel.getId());
            boolean r =validate.goCheck();
            if(!r){
                throw new BaseException("Id必须为正整数",404,10001);
            }
        }
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorModel,doctor);
        doctor.setId(Integer.parseInt(doctorModel.getId()));
        doctor.setUpdateTime(CommonUtils.getCurrentDate());
        int effect = doctorMapper.update(doctor);
        if(effect==0){
            throw new DoctorException("操作失败");
        }
        return effect;
    }

    @Override
    public int deleteDoctor(String id) {
        Validate validate =new IdMustBePositiveInt(id);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("Id必须为正整数",404,10001);
        }
        int effectNum =  doctorMapper.delete(Integer.parseInt(id),CommonUtils.getCurrentDate());
        if(effectNum==0){
            throw new DoctorException("操作失败",401,20002);
        }
        return effectNum;
    }
}
