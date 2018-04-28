package com.md.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.md.manage.domain.Organ;
import com.md.manage.json.JsonResult;
import com.md.manage.model.OrganModel;
import com.md.manage.model.PageModel;
import com.md.manage.service.OrganService;

import java.util.List;

import com.md.manage.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrganController {


    @Autowired
    private OrganService organService;


    @GetMapping("/api/organ/list")
    public JsonResult getOrganList(@Valid PageModel page, BindingResult result){
        CommonUtils.validateParams(result);
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Organ> organList = organService.getOrganList();
        PageInfo<Organ> pageInfo  = new PageInfo<>(organList);
        return new JsonResult().success(pageInfo);
    }

    @PostMapping("/api/organ/add")
    public JsonResult insertOrgan(@RequestBody @Valid OrganModel organModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = organService.insertOrgan(organModel);
        return new JsonResult().success(effect);
    }

    @PostMapping("api/organ/update")
    public JsonResult updateOrgan(@RequestBody @Valid OrganModel organModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = organService.updateOrgan(organModel);
        return new JsonResult().success(effect);
    }

    @GetMapping("/api/organ/{id}/delete")
    public JsonResult deleteOrgan(@PathVariable("id") String id){
        int effect = organService.deleteOrgan(id);
        return new JsonResult().success(effect);
    }



}
