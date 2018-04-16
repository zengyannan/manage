package com.md.manage.service.impl;

import com.md.manage.domain.Menu;
import com.md.manage.dto.Page;
import com.md.manage.exception.BaseException;
import com.md.manage.exception.MenuException;
import com.md.manage.mapper.MenuMapper;
import com.md.manage.model.MenuModel;
import com.md.manage.service.MenuService;
import com.md.manage.validate.IdMustBePositiveInt;
import com.md.manage.validate.Validate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Menu getMenuById(String id) {
        Validate validate = new IdMustBePositiveInt(id);
        boolean r  = validate.goCheck();
        if(!r){
            throw new BaseException("参数错误",404,10001);
        }
        Menu menu = menuMapper.getMenuById(Integer.parseInt(id));
        if(menu == null){
            throw new MenuException("管理员不存在");
        }
        return menu;
    }


    @Override
    public int insertMenu(MenuModel menuModel) {
        Menu eMenu = menuMapper.getMenuByName(menuModel.getName());
        if(eMenu!=null){
            throw new MenuException("菜单名重复了");
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuModel,menu);
        int effect =  menuMapper.insert(menu);
        if(effect==0) {
            throw new MenuException("操作失败");
        }
        Integer id = menu.getId();
        if(id!=null){
            String orderPath = menu.getParentId()+"-"+id;
            menuMapper.setOrderPath(id,orderPath);
        }
        return effect;
    }


    public int updateMenu(MenuModel menuModel){
        if(menuModel.getId()==null){
            throw new BaseException("必须传入id");
        }
        Validate validate = new IdMustBePositiveInt(menuModel.getId());
        if(!validate.goCheck()){
            throw new BaseException("Id必须为正整数");
        }
        Menu menu =new Menu();
        BeanUtils.copyProperties(menuModel,menu);
        menu.setId(Integer.parseInt(menuModel.getId()));
        menu.setOrderPath(menuModel.getParentId()+"-"+menuModel.getId());
        int effect = menuMapper.update(menu);
        if(effect==0){
            throw new MenuException("操作失败");
        }
        return effect;
    }

    public int deleteMenu(String id){
        Validate validate =new IdMustBePositiveInt(id);
        if(!validate.goCheck()){
            throw new BaseException("参数错误");
        }
        int effect = menuMapper.delete(Integer.parseInt(id));
        if(effect==0){
            throw new MenuException("操作失败");
        }
        return effect;
    }

    @Override
    public List<Menu> getAllMenu() {
        return menuMapper.getMenuList(null,null);
    }

    @Override
    public Page<Menu> getMenuByPage(Page<Menu> page) {
        return null;
    }
}
