package com.md.manage.controller;


import com.md.manage.exception.BaseException;
import com.md.manage.json.JsonResult;
import com.md.manage.model.DoctorModel;
import com.md.manage.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DoctorController {


    @Autowired
    private DoctorService doctorService;

    @GetMapping("/api/doctor/{id}")
    public JsonResult getDoctorById(@PathVariable String id){
        return new JsonResult().success(doctorService.getDoctorById(id));
    }


    @PostMapping("/api/doctor/add")
    public JsonResult insertDoctor(@Valid DoctorModel doctorModel, BindingResult result){
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
        return new JsonResult().success(doctorService.insertDoctor(doctorModel));
    }

    @PostMapping("/api/doctor/update")
    public JsonResult updateDoctor(@Valid DoctorModel doctorModel, BindingResult result){
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
        return new JsonResult().success(doctorService.updateDoctor(doctorModel));
    }


    @GetMapping("/api/doctor/{id}/delete")
    public JsonResult deleteDoctor(@PathVariable String id){
        return new JsonResult().success(doctorService.deleteDoctor(id));
    }

}
