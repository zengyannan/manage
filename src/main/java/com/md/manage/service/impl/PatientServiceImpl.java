package com.md.manage.service.impl;

import com.md.manage.domain.Patient;
import com.md.manage.dto.Page;
import com.md.manage.dto.User;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.PatientException;
import com.md.manage.mapper.PatientMapper;
import com.md.manage.model.PageModel;
import com.md.manage.model.PatientModel;
import com.md.manage.service.PatientService;
import com.md.manage.service.RedisService;
import com.md.manage.util.CommonUtils;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{


    @Autowired
    private PatientMapper patientMapper;

    @Value("${md.patient.role.id}")
    private String patientRoleId;


    @Autowired
    private RedisService redisService;

    @Override
    public List<Patient> findAll() {
        return  patientMapper.findAll();
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientMapper.getAllPatient();
    }

    @Override
    public Page<Patient> getListByPage(PageModel pageModel) {
        Long count = patientMapper.count();
        Page<Patient> pageInfo = new Page(pageModel.getPageNum(),pageModel.getPageSize(),count);
        pageInfo.pagination();
        List<Patient> patients = patientMapper.getListByPage(pageInfo);
        pageInfo.setList(patients);
        return pageInfo;
    }

    @Override
    public int insertPatient(PatientModel patientModel) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientModel,patient);
        patient.setCreateTime(CommonUtils.getCurrentDate());
        patient.setUpdateTime(CommonUtils.getCurrentDate());
        patient.setPassword("123456");
        int effectNum = patientMapper.insert(patient);
        if(effectNum==0){
            throw new PatientException("操作失败");
        }
        List<Integer> ids = new ArrayList<>();
        ids.add(Integer.parseInt(patientRoleId));
        patientMapper.setRoles(patient.getId(),ids);
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
        patientMapper.deletePatientRole(Integer.parseInt(id));
        return effect;
    }


    @Override
    public Patient getPatientByNameAndIdCard(String name, String idCard) {
        Patient patient = patientMapper.getPatientByName(name.trim());
        if(patient.getIdCard().equals(idCard.trim())){
            return patient;
        }
        return null;
    }

    @Override
    public Patient getPatientByToken(String token) {
        User user = (User) redisService.get("token:" + token);
        if (user.getLoginType().equals("patient")) {
            Patient patient = patientMapper.getPatientById(user.getId());
            if (patient == null) {
                throw new PatientException("患者账户不存在");
            }
            return patient;
        }else {
            throw new PatientException("患者账户不存在");
        }
    }
}
