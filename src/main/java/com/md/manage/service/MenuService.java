package com.md.manage.service;

import com.md.manage.domain.Menu;
import com.md.manage.dto.MenuTree;
import com.md.manage.dto.Page;
import com.md.manage.model.MenuModel;

import java.util.List;

public interface MenuService {



    public Menu getMenuById(String id);


    public int insertMenu(MenuModel menuModel);



    public int updateMenu(MenuModel menuModel);

    public int deleteMenu(String id);

    public List<Menu> getAllMenu();

    public Page<Menu> getMenuByPage(Page<Menu> page);


    public List<MenuTree> getMenuTree(String token);

    public List<Menu> findAll();
}
