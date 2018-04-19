package com.md.manage.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.md.manage.domain.Menu;
import com.md.manage.dto.MenuTree;
import com.md.manage.exception.BaseException;
import com.md.manage.json.JsonResult;
import com.md.manage.model.MenuModel;
import com.md.manage.model.PageModel;
import com.md.manage.service.MenuService;
import com.md.manage.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;




    @GetMapping("/api/menu/{id}")
    public JsonResult getMenuById(@PathVariable("id") String id){
        return  new JsonResult().success(menuService.getMenuById(id));
    }

    @PostMapping("/api/menu/add")
    public JsonResult insertMenu(@Valid MenuModel menuModel, BindingResult result){
        CommonUtils.validateParams(result);
        return  new JsonResult().success(menuService.insertMenu(menuModel));
    }

    @PostMapping("/api/menu/update")
    public JsonResult updateMenu(@Valid MenuModel menuModel,BindingResult result){
        CommonUtils.validateParams(result);
        return new JsonResult().success(menuService.updateMenu(menuModel));
    }

    @GetMapping("/api/menu/{id}/delete")
    public JsonResult deleteMenu(@PathVariable("id") String id){
        return new JsonResult().success(menuService.deleteMenu(id));
    }

    @GetMapping("/api/menu/list")
    public JsonResult getAllMenu(@Valid PageModel page,BindingResult result){
        CommonUtils.validateParams(result);
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        List<Menu> menus = menuService.findAll();
        PageInfo<Menu> pageInfo  = new PageInfo<>(menus);
        return new JsonResult().success(pageInfo);
    }

    @GetMapping("/api/menu/tree")
    public JsonResult getMenuTree(@RequestHeader String token)throws Exception{
        Map json=null;
        try{
             json = new ObjectMapper().readValue(token,HashMap.class);
        }catch (Exception e){
            throw e;
        }
        List<MenuTree> trees = menuService.getMenuTree("token:"+json.get("token"));
        return new JsonResult().success(trees);
    }

}
