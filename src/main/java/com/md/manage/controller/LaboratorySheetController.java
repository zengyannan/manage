package com.md.manage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.md.manage.domain.LaboratorySheet;
import com.md.manage.dto.Page;
import com.md.manage.json.JsonResult;
import com.md.manage.model.PageModel;
import com.md.manage.service.LaboratorySheetService;
import com.md.manage.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LaboratorySheetController {

    @Autowired
    private LaboratorySheetService laboratorySheetService;

    @GetMapping("api/laboratorySheet/list")
    public JsonResult getLaboratorySheetList(@Valid PageModel page, BindingResult result,@RequestHeader String token)throws Exception{
        CommonUtils.validateParams(result);
        Map json=null;
        try{
            json = new ObjectMapper().readValue(token,HashMap.class);
        }catch (Exception e){
            throw e;
        }
        Page<LaboratorySheet> pageInfo = laboratorySheetService.getLaboratorySheetList(page,json.get("token").toString());
        return new JsonResult().success(pageInfo);
    }

    @GetMapping("api/laboratorySheet/all")
    public JsonResult getAllLaboratorySheet(){
        List<LaboratorySheet> laboratorySheetList = laboratorySheetService.findAll();
        return new JsonResult().success(laboratorySheetList);
    }

    @PostMapping("api/laboratorySheet/suggest/set")
    public JsonResult setSuggest(@RequestBody Map<String,Object> json){
        int effect = laboratorySheetService.setSuggest(json.get("suggest").toString(),json.get("lsId").toString());
        return new JsonResult().success(effect);
    }


    @PostMapping("api/laboratorySheet/add/byDoctor")
    public JsonResult insertByDoctor(@RequestBody Map<String,Object> json,@RequestHeader String token)throws Exception{
        Map map=null;
        try{
            map = new ObjectMapper().readValue(token,HashMap.class);
        }catch (Exception e){
            throw e;
        }
        int effect = laboratorySheetService.insertByDoctorId(json.get("patientId").toString(),map.get("token").toString());
        return new JsonResult().success(effect);
    }


}
