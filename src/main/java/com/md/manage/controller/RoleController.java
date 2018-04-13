package com.md.manage.controller;


import com.md.manage.domain.Role;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.RoleException;
import com.md.manage.json.JsonResult;
import com.md.manage.model.RoleModel;
import com.md.manage.service.RoleService;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
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
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("/api/role/{id}")
    public JsonResult getRoleById(@PathVariable("id") String id){
        Role role= roleService.getRoleById(id);
        return new JsonResult().success(role);
    }

    /**
     * 添加角色
     * @param roleModel
     * @return
     */
    @PostMapping("/api/role/add")
    public JsonResult insertRole(@Valid RoleModel roleModel, BindingResult result){
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
        int effect = roleService.insetRole(roleModel);
        return new JsonResult().success(effect);
    }

    @PostMapping("api/role/update")
    public JsonResult updateRole(@Valid RoleModel roleModel,BindingResult result){
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            StringBuilder str = new StringBuilder("");
            for (ObjectError error : list) {
                throw new BaseException("参数错误", 404, 10001);
            }
        }
        int effect = roleService.updateRole(roleModel);
        return new JsonResult().success(effect);
    }

    @GetMapping("/api/role/{id}/delete")
    public JsonResult deleteRole(@PathVariable("id") String id){
        int effect = roleService.deleteRole(id);
        return new JsonResult().success(effect);
    }

}
