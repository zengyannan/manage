package com.md.manage.controller;


import com.md.manage.domain.Hr;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.HrException;
import com.md.manage.json.JsonResult;
import com.md.manage.model.HrModel;
import com.md.manage.service.HrService;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HrController {


    @Autowired
    private HrService hrService;


    @GetMapping("/api/hr/{id}")
    public JsonResult getHrById(@PathVariable("id") String id){
        Hr hr= hrService.getHrById(id);
        return new JsonResult().success(hr);
    }

    /**
     * 添加管理员账户
     * @param hrModel
     * @param result
     * @return
     * @throws BaseException
     */
    @PostMapping("/api/hr/add")
    public JsonResult insertHr(@Valid HrModel hrModel, BindingResult result) throws BaseException{
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
        int effect = hrService.insertHr(hrModel);
        return new JsonResult().success(effect);
    }

    /**
     * 修改管理员账户
     * @param hrModel
     * @param result
     * @return
     * @throws BaseException
     */
    @PostMapping("/api/hr/update")
    public JsonResult updateHr(@Valid HrModel hrModel, BindingResult result) throws BaseException{
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
        return new JsonResult().success(hrService.updateHr(hrModel));
    }

    /**
     * 删除管理员账户
     * @param id
     * @return
     */
    @GetMapping("/api/hr/{id}/delete")
    public JsonResult deleteHr(@PathVariable("id") String id){
        return new JsonResult().success(hrService.deleteHr(id));
    }

}
