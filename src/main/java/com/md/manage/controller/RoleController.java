package com.md.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.md.manage.domain.Menu;
import com.md.manage.domain.Role;
import com.md.manage.dto.Page;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.RoleException;
import com.md.manage.json.JsonResult;
import com.md.manage.model.PageModel;
import com.md.manage.model.RoleModel;
import com.md.manage.service.RoleService;
import com.md.manage.util.CommonUtils;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public JsonResult insertRole(@RequestBody @Valid RoleModel roleModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = roleService.insetRole(roleModel);
        return new JsonResult().success(effect);
    }

    @PostMapping("api/role/update")
    public JsonResult updateRole(@RequestBody @Valid RoleModel roleModel, BindingResult result){
        CommonUtils.validateParams(result);
        int effect = roleService.updateRole(roleModel);
        return new JsonResult().success(effect);
    }


    @PostMapping("api/role/menu/set")
    public JsonResult setRoleMenu(@RequestBody Map<String,Object> json){
        String rid = json.get("rid").toString();
        String ids =json.get("ids").toString();
        int effect = roleService.setRoleMenu(rid,ids);
        return new JsonResult().success(effect);
    }

    @GetMapping("/api/role/{id}/delete")
    public JsonResult deleteRole(@PathVariable("id") String id){
        int effect = roleService.deleteRole(id);
        return new JsonResult().success(effect);
    }

    @GetMapping("/api/role/list")
    public JsonResult getRoleListByPage(@Valid PageModel pageModel, BindingResult result){
        CommonUtils.validateParams(result);
        Page<Role> pageInfo = roleService.getRoleListByPage(pageModel);
        return new JsonResult().success(pageInfo);
    }

    @GetMapping("/api/role/all")
    public JsonResult getAllRole(){
//        CommonUtils.validateParams(result);
//        PageHelper.startPage(pageModel.getPageNum(),pageModel.getPageSize());
        List<Role> roles = roleService.findAll();
//        PageInfo<Role> pageInfo  = new PageInfo<>(roles);
        return new JsonResult().success(roles);
    }


}
