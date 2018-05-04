package com.md.manage.mapper;

import com.md.manage.domain.SystemSuggest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemSuggestMapper {


    List<SystemSuggest> findAll();

    int  insert(SystemSuggest systemSuggest);

    int update(SystemSuggest systemSuggest);

    int delete(@Param("id") Integer id);

    SystemSuggest getBySpecificId(@Param("specificId") Integer specificId);

}
