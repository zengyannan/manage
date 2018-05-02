package com.md.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.md.manage.domain.Specific;
import com.md.manage.json.JsonResult;
import com.md.manage.model.PageModel;
import com.md.manage.model.SpecificModel;
import com.md.manage.service.SpecificService;
import com.md.manage.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SpecificController {


    @Autowired
    private SpecificService specificService;


    @GetMapping("/api/specific/list")
    public JsonResult getSpecificList(@Valid PageModel page, BindingResult result){
        CommonUtils.validateParams(result);
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Specific> specificList = specificService.findAll();
        PageInfo<Specific> pageInfo  = new PageInfo<>(specificList);
        return new JsonResult().success(pageInfo);
    }
    @GetMapping("/api/specific/all")
    public JsonResult getAllSpecific(){
        List<Specific> specificList = specificService.findAll();
        return new JsonResult().success(specificList);
    }

    @PostMapping("/api/specific/add")
    public JsonResult insertSpecific(@RequestBody @Valid SpecificModel specificModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = specificService.insertSpecific(specificModel);
        return new JsonResult().success(effect);
    }

    @PostMapping("api/specific/update")
    public JsonResult updateSpecific(@RequestBody @Valid SpecificModel specificModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = specificService.updateSpecific(specificModel);
        return new JsonResult().success(effect);
    }

    @GetMapping("/api/specific/{id}/delete")
    public JsonResult deleteSpecific(@PathVariable("id") String id){
        int effect = specificService.deleteSpecific(id);
        return new JsonResult().success(effect);
    }



}
