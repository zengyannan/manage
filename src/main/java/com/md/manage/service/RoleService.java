package com.md.manage.service;

import com.md.manage.domain.Role;
import com.md.manage.dto.Page;
import com.md.manage.model.PageModel;
import com.md.manage.model.RoleModel;

import java.util.List;

public interface RoleService {



    public Role getRoleById(String id);

    public int insetRole(RoleModel roleModel);

    public int updateRole(RoleModel roleModel);

    public int setRoleMenu(String  rid,String ids);

    public int deleteRole(String id);


    public List<Role> findAll();


    Page<Role> getRoleListByPage(PageModel pageModel);
}
