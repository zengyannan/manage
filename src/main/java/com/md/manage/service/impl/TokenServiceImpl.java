package com.md.manage.service.impl;

import com.md.manage.domain.Hr;
import com.md.manage.exception.HrException;
import com.md.manage.mapper.HrMapper;
import com.md.manage.model.LoginModel;
import com.md.manage.service.RedisService;
import com.md.manage.service.TokenService;
import com.md.manage.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private HrMapper hrMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public String getToken(LoginModel loginModel) {
        String uid = null;
        Hr hr = hrMapper.getHrByUsername(loginModel.getUsername());
        if (hr == null) {
            throw new HrException("用户账户不存在");
        }
        if (!hr.getPassword().equals(loginModel.getPassword())) {
            throw new HrException("密码错误");
        }
        uid = hr.getId().toString();
        String token = null;
        try {
            token = CommonUtils.generatorToken(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (token != null) {
            redisService.set("token:" + token,hr);
        }
        return token;
    }


}
