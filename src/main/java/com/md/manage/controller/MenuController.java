package com.md.manage.controller;


import com.md.manage.exception.BaseException;
import com.md.manage.json.JsonResult;
import com.md.manage.model.MenuModel;
import com.md.manage.service.MenuService;
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
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/api/menu/{id}")
    public JsonResult getMenuById(@PathVariable("id") String id){
        return  new JsonResult().success(menuService.getMenuById(id));
    }

    @PostMapping("/api/menu/add")
    public JsonResult insertMenu(@Valid MenuModel menuModel, BindingResult result){
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
        return  new JsonResult().success(menuService.insertMenu(menuModel));
    }

    @PostMapping("/api/menu/update")
    public JsonResult updateMenu(@Valid MenuModel menuModel,BindingResult result){
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
        return new JsonResult().success(menuService.updateMenu(menuModel));
    }

    @GetMapping("/api/menu/{id}/delete")
    public JsonResult deleteMenu(@PathVariable("id") String id){
        return new JsonResult().success(menuService.deleteMenu(id));
    }

    @GetMapping("/api/menu/list")
    public JsonResult getAllMenu(){
        return new JsonResult().success(menuService.getAllMenu());
    }
}
