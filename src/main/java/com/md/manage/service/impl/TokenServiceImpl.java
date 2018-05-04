package com.md.manage.service.impl;

import com.md.manage.domain.Hr;
import com.md.manage.domain.Patient;
import com.md.manage.dto.User;
import com.md.manage.exception.HrException;
import com.md.manage.mapper.HrMapper;
import com.md.manage.mapper.PatientMapper;
import com.md.manage.model.LoginModel;
import com.md.manage.service.RedisService;
import com.md.manage.service.TokenService;
import com.md.manage.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private HrMapper hrMapper;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public String getToken(LoginModel loginModel) {
        String uid = null;
        String token = null;
        if(loginModel.getLoginType().equals("hr")){
            Hr hr = hrMapper.getHrByUsername(loginModel.getUsername());
            if (hr == null) {
                throw new HrException("用户账户不存在");
            }
            if (!hr.getPassword().equals(loginModel.getPassword())) {
                throw new HrException("密码错误");
            }
            uid = hr.getId().toString();
            try {
                token = CommonUtils.generatorToken(uid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (token != null) {
                User user = new User();
                BeanUtils.copyProperties(hr,user);
                user.setRoles(hr.getRoles());
                user.setLoginType(loginModel.getLoginType());
                redisService.set("token:" + token,user);
            }
        }else if(loginModel.getLoginType().equals("patient")){
            Patient patient = patientMapper.getPatientByIdCard(loginModel.getUsername());
            if (patient == null) {
                throw new HrException("用户账户不存在");
            }
            if (!patient.getPassword().equals(loginModel.getPassword())) {
                throw new HrException("密码错误");
            }
            uid = patient.getId().toString();
            try {
                token = CommonUtils.generatorToken(uid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (token != null) {
                User user = new User();
                BeanUtils.copyProperties(patient,user);
                user.setRoles(patient.getRoles());
                user.setLoginType(loginModel.getLoginType());
                redisService.set("token:" + token,user);
            }
        }
        return token;
    }


}
