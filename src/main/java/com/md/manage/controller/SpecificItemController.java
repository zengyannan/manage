package com.md.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.md.manage.domain.SpecificItem;
import com.md.manage.json.JsonResult;
import com.md.manage.model.PageModel;
import com.md.manage.model.SpecificItemModel;
import com.md.manage.service.SpecificItemService;
import com.md.manage.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SpecificItemController {


    @Autowired
    private SpecificItemService specificItemService;


    @GetMapping("/api/specificItem/list")
    public JsonResult getSpecificItemList(@Valid PageModel page, BindingResult result){
        CommonUtils.validateParams(result);
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<SpecificItem> specificItemList = specificItemService.findAll();
        PageInfo<SpecificItem> pageInfo  = new PageInfo<>(specificItemList);
        return new JsonResult().success(pageInfo);
    }
    @GetMapping("/api/specificItem/all")
    public JsonResult getAllSpecificItem(){
        List<SpecificItem> specificItemList = specificItemService.findAll();
        return new JsonResult().success(specificItemList);
    }

    @PostMapping("/api/specificItem/add")
    public JsonResult insertSpecificItem(@RequestBody @Valid SpecificItemModel specificItemModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = specificItemService.insertSpecificItem(specificItemModel);
        return new JsonResult().success(effect);
    }

    @PostMapping("api/specificItem/update")
    public JsonResult updateSpecificItem(@RequestBody @Valid SpecificItemModel specificItemModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = specificItemService.updateSpecificItem(specificItemModel);
        return new JsonResult().success(effect);
    }

    @GetMapping("/api/specificItem/{id}/delete")
    public JsonResult deleteSpecificItem(@PathVariable("id") String id){
        int effect = specificItemService.deleteSpecificItem(id);
        return new JsonResult().success(effect);
    }


}
