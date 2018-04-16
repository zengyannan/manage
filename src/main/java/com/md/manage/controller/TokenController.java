package com.md.manage.controller;


import com.md.manage.exception.BaseException;
import com.md.manage.json.JsonResult;
import com.md.manage.model.LoginModel;
import com.md.manage.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;



    @PostMapping("/api/token/get")
    public JsonResult getToken(@RequestBody @Valid LoginModel loginModel, BindingResult result){
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
        Map json  =new HashMap();
        json.put("token",tokenService.getToken(loginModel));
        return new JsonResult().success(json);
    }



}
