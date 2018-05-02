package com.md.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.md.manage.domain.Inspection;
import com.md.manage.json.JsonResult;
import com.md.manage.model.InspectionModel;
import com.md.manage.model.PageModel;
import com.md.manage.service.InspectionService;
import com.md.manage.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InspectionController {


    @Autowired
    private InspectionService inspectionService;


    @GetMapping("/api/inspection/list")
    public JsonResult getInspectionList(@Valid PageModel page, BindingResult result){
        CommonUtils.validateParams(result);
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Inspection> inspectionList = inspectionService.findAll();
        PageInfo<Inspection> pageInfo  = new PageInfo<>(inspectionList);
        return new JsonResult().success(pageInfo);
    }

    @GetMapping("/api/inspection/all")
    public JsonResult getAllInspection(){
        List<Inspection> inspectionList = inspectionService.findAll();
        return new JsonResult().success(inspectionList);
    }

    @PostMapping("/api/inspection/add")
    public JsonResult insertInspection(@RequestBody @Valid InspectionModel inspectionModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = inspectionService.insertInspection(inspectionModel);
        return new JsonResult().success(effect);
    }

    @PostMapping("api/inspection/update")
    public JsonResult updateInspection(@RequestBody @Valid InspectionModel inspectionModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = inspectionService.updateInspection(inspectionModel);
        return new JsonResult().success(effect);
    }

    @GetMapping("/api/inspection/{id}/delete")
    public JsonResult deleteInspection(@PathVariable("id") String id){
        int effect = inspectionService.deleteInspection(id);
        return new JsonResult().success(effect);
    }


}
