package com.md.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.md.manage.domain.Patient;
import com.md.manage.dto.Page;
import com.md.manage.json.JsonResult;
import com.md.manage.model.PatientModel;
import com.md.manage.model.PageModel;
import com.md.manage.service.PatientService;
import com.md.manage.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController

public class PatientController {



    @Autowired
    private PatientService patientService;



    @GetMapping("/api/patient/list")
    public JsonResult getPatientList(@Valid PageModel page, BindingResult result){
        CommonUtils.validateParams(result);
        Page<Patient> pageInfo = patientService.getListByPage(page);
        return new JsonResult().success(pageInfo);
    }

    @GetMapping("/api/patient/all")
    public JsonResult getAllPatient(){
        List<Patient> patientList = patientService.getAllPatient();
        return new JsonResult().success(patientList);
    }


    @PostMapping("/api/patient/add")
    public JsonResult insertPatient(@RequestBody @Valid PatientModel patientModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = patientService.insertPatient(patientModel);
        return new JsonResult().success(effect);
    }

    @PostMapping("api/patient/update")
    public JsonResult updatePatient(@RequestBody @Valid PatientModel patientModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = patientService.updatePatient(patientModel);
        return new JsonResult().success(effect);
    }

    @GetMapping("/api/patient/{id}/delete")
    public JsonResult deletePatient(@PathVariable("id") String id){
        int effect = patientService.deletePatient(id);
        return new JsonResult().success(effect);
    }


    //患者端接口
    @PostMapping("/patient/getInfo")
    public JsonResult getPatientInfo(@RequestBody Map<String,Object> json){
        String name = json.get("name").toString();
        String idCard = json.get("idCard").toString();
        Patient patient =patientService.getPatientByNameAndIdCard(name,idCard);
        if(patient==null)
            return new JsonResult().failure();
        return new JsonResult().success(patient);
    }

}
