package com.md.manage.service.impl;

import com.md.manage.domain.Hr;
import com.md.manage.dto.User;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.HrException;
import com.md.manage.mapper.HrMapper;
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
    private RedisService redisService;

    @Override
    public String getToken(LoginModel loginModel) {
        String uid=null;
        User user=null;
        if(loginModel.getLoginType().equals("hr")){

            Hr hr = hrMapper.getHrByUsername(loginModel.getUsername());
            if(hr==null){
                throw new HrException("用户账户不存在");
            }
            if(!hr.getPassword().equals(loginModel.getPassword())){
                throw new HrException("密码错误");
            }
            uid = hr.getId().toString();
            user = new User();
            BeanUtils.copyProperties(hr,user);
            user.setUserType("hr");
            user.setRoles(hr.getRoles());
        }else{
            throw new BaseException("该账户类型不存在");
        }
        String token=null;
        try{
            token = CommonUtils.generatorToken(uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(token!=null){
            redisService.set("token:"+token,user);
        }
        return token;
    }



}
