package com.md.manage.service;

import com.md.manage.domain.Menu;
import com.md.manage.model.MenuModel;

public interface MenuService {



    public Menu getMenuById(String id);


    public int insertMenu(MenuModel menuModel);



    public int updateMenu(MenuModel menuModel);

    public int deleteMenu(String id);
}
