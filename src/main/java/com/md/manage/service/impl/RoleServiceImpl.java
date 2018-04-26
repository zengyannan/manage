package com.md.manage.service.impl;

import com.md.manage.domain.Role;
import com.md.manage.dto.Page;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.RoleException;
import com.md.manage.mapper.RoleMapper;
import com.md.manage.model.PageModel;
import com.md.manage.model.RoleModel;
import com.md.manage.service.RoleService;
import com.md.manage.util.CommonUtils;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl  implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据id 查找role
     * @param id
     * @return
     */
    @Override
    public Role getRoleById(String id) throws BaseException{
        Validate validate =new IdMustBePositiveInt(id);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("参数错误",404,10001);
        }
        Role role= roleMapper.getRoleById(Integer.parseInt(id));
        if(role == null){
            throw new RoleException("角色不存在");
        }
        return role;
    }

    /**
     * 新增一条role数据
     * @param roleModel
     * @return
     */
    @Override
    public int insetRole(RoleModel roleModel) throws RoleException{
        Role role= new Role();
        BeanUtils.copyProperties(roleModel,role);
        role.setCreateTime(CommonUtils.getCurrentDate());
        role.setUpdateTime(CommonUtils.getCurrentDate());
        int effectNum = roleMapper.insert(role);
        if(effectNum==0){
            throw new RoleException("操作失败");
        }
        return effectNum;
    }

    /**
     * 修改role
     * @param roleModel
     * @return
     */
    @Override
    public int updateRole(RoleModel roleModel) {
        if(roleModel.getId()==null){
            throw new BaseException("必须传入ID");
        }else {
            Validate validate = new IdMustBePositiveInt(roleModel.getId());
            boolean r= validate.goCheck();
            if(!r){
                throw new BaseException("ID必须为正整数",404,10001);
            }
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleModel,role);
        role.setUpdateTime(CommonUtils.getCurrentDate());
        role.setId(Integer.parseInt(roleModel.getId()));
        int effect = roleMapper.update(role);
        if(effect==0){
            throw new RoleException("操作失败");
        }
        return effect;
    }

    /**
     * 删除role
     * @param id
     * @return
     */
    @Override
    public int deleteRole(String id) throws BaseException{
        Validate validate =new IdMustBePositiveInt(id);
        boolean r = validate.goCheck();
        if(!r){
            throw new BaseException("参数错误",404,10001);
        }
        int effect = roleMapper.delete(Integer.parseInt(id),CommonUtils.getCurrentDate());
        if(effect==0){
            throw new RoleException("操作错误");
        }
        return effect;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public Page<Role> getRoleListByPage(PageModel pageModel) {
        Long count = roleMapper.count();
        Page<Role> pageInfo = new Page(pageModel.getPageNum(),pageModel.getPageSize(),count);
        pageInfo.pagination();
        List<Role> roles = roleMapper.getListByPage(pageInfo);
        pageInfo.setList(roles);
        return pageInfo;
    }
}
