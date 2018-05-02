package com.md.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.md.manage.domain.LaboratorySheet;
import com.md.manage.json.JsonResult;
import com.md.manage.model.PageModel;
import com.md.manage.service.LaboratorySheetService;
import com.md.manage.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LaboratorySheetController {

    @Autowired
    private LaboratorySheetService laboratorySheetService;

    @GetMapping("api/laboratorySheet/list")
    public JsonResult getLaboratorySheetList(@Valid PageModel page, BindingResult result){
        CommonUtils.validateParams(result);
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<LaboratorySheet> laboratorySheetList = laboratorySheetService.getLaboratorySheetList();
        PageInfo<LaboratorySheet> pageInfo  = new PageInfo<>(laboratorySheetList);
        return new JsonResult().success(pageInfo);
    }

    @GetMapping("api/laboratorySheet/all")
    public JsonResult getAllLaboratorySheet(){
        List<LaboratorySheet> laboratorySheetList = laboratorySheetService.getLaboratorySheetList();
        return new JsonResult().success(laboratorySheetList);
    }

}
