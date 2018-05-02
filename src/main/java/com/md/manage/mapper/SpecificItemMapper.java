package com.md.manage.mapper;

import com.md.manage.domain.SpecificItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpecificItemMapper {


    List<SpecificItem> findAll();

    int insert(SpecificItem specificItem);

    int update(SpecificItem specificItem);

    int delete(@Param("id") Integer id);

}
