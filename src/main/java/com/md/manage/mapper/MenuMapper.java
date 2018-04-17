package com.md.manage.mapper;

import com.md.manage.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MenuMapper {

    Menu getMenuById(Integer id);

    int insert(Menu menu);

    int update(Menu menu);

    int delete(@Param("id")Integer id);

    Menu getMenuByName(String name);

    int setOrderPath(@Param("id")Integer id,@Param("orderPath") String orderPath);

    List<Menu> getMenuList(@Param("offset") Integer offset,@Param("number") Integer number);

    List<Menu> getMenusByRoles(@Param("ids") List<Integer> ids);
}