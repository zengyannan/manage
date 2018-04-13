package com.md.manage.service;

import com.md.manage.domain.Role;
import com.md.manage.model.RoleModel;

public interface RoleService {



    public Role getRoleById(String id);

    public int insetRole(RoleModel roleModel);

    public int updateRole(RoleModel roleModel);

    public int deleteRole(String id);




}
